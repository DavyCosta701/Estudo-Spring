package com.company.davyc.api.controller;

import com.company.davyc.api.DTO.*;
import com.company.davyc.domain.entity.ItemPedido;
import com.company.davyc.domain.entity.Pedido;
import com.company.davyc.domain.enums.StatusPedido;
import com.company.davyc.exception.BuscaException;
import com.company.davyc.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public int save(@RequestBody PedidoDTO pedidoDTO){
        return pedidoService
                .salvarPedido(pedidoDTO)
                .getID();
    }

    @GetMapping("/get/{id}")
    public PedidoViewDTO pedidoByID(@PathVariable Integer id){
        return pedidoService.
                mostrarPedido(id).
                map(this::converter).
                orElseThrow(() -> new BuscaException("Pedido não encontrado"));
    }

    @PatchMapping("/patch/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void statusPedido(@RequestBody  AtualizarPedidoDTO dto, @PathVariable int id){
        pedidoService.atualizaPedido(id, StatusPedido.valueOf(dto.getNewStatus()));

    }

    private PedidoViewDTO converter(Pedido p){

        return PedidoViewDTO.builder().codigo(p.getID())
                .codigo(p.getID())
                .dataPedido(p.getData_Pedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpfCliente(p.getCliente().getCpf())
                .nomeCliente(p.getCliente().getNOME())
                .total(p.getTotal())
                .items(pegarItensPedido(p.getItensPedido()))
                .statusPedido(p.getStatusPedido().name())
                .build();

    }

    private List<ItensPedidoViewDTO> pegarItensPedido(List<ItemPedido> itens){
        if (CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }
        return itens.stream().map(itemPedido ->
            ItensPedidoViewDTO.builder()
                    .quantidade(itemPedido.getQuantidade())
                    .valor(itemPedido.getProduto().getPreco_Unitario())
                    .descricao(itemPedido.getProduto().getDescricao())
                    .build()).toList();
    }





}
