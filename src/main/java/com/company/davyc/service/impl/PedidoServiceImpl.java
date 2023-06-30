package com.company.davyc.service.impl;

import com.company.davyc.api.DTO.ItemPedidoDTO;
import com.company.davyc.api.DTO.PedidoDTO;
import com.company.davyc.domain.entity.Cliente;
import com.company.davyc.domain.entity.ItemPedido;
import com.company.davyc.domain.entity.Pedido;
import com.company.davyc.domain.enums.StatusPedido;
import com.company.davyc.domain.repository.ClientesSD;
import com.company.davyc.domain.repository.ItemPedidoSD;
import com.company.davyc.domain.repository.PedidoSD;
import com.company.davyc.domain.repository.ProdutoSD;
import com.company.davyc.exception.BuscaException;
import com.company.davyc.exception.RegraNegocioException;
import com.company.davyc.service.PedidoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService{

    private final PedidoSD repoPedido;
    private final ClientesSD repoCliente;
    private final ProdutoSD repoProduto;
    private final ItemPedidoSD repoItemPedido;


    @Transactional
    @Override
    public Pedido salvarPedido(PedidoDTO pedidoDTO) {
        Cliente cliente = repoCliente.findById(pedidoDTO.getCliente()).orElseThrow(() -> new RegraNegocioException("Cliente não encontrado!"));
        Pedido pedido = new Pedido();
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setData_Pedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatusPedido(StatusPedido.REALIZADO);
        repoPedido.save(pedido);
        repoItemPedido.saveAll(converterItems(pedido, pedidoDTO.getItems()));
        return pedido;
    }

    @Override
    public Optional<Pedido> mostrarPedido(Integer cod) {
        return repoPedido.fetchItensPorID(cod);
    }

    @Override
    @Transactional
    public void atualizaPedido(int id, StatusPedido statusPedido) {
        repoPedido.findById(id).map(
                pedido -> {
                    pedido.setStatusPedido(statusPedido);
                    return repoPedido.save(pedido);

                }
        ).orElseThrow(() -> new BuscaException("Pedido não encontrado"))
        ;
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> itens){
        if (itens.isEmpty()){
            throw new RegraNegocioException("Carrinho Vazio!");
        }

        return itens
                .stream()
                .map( dto -> {
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setPedido(pedido);
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setProduto(repoProduto.findById
                            (dto.getProduto()).orElseThrow(
                            () -> new RegraNegocioException("Pedido Indisponível"))
                    );
                    return itemPedido;
                }).toList();

    }




}
