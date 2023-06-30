package com.company.davyc.service;

import com.company.davyc.api.DTO.PedidoDTO;
import com.company.davyc.domain.entity.Pedido;
import com.company.davyc.domain.enums.StatusPedido;

import java.util.Optional;

public interface PedidoService {
    Pedido salvarPedido(PedidoDTO pedidoDTO);
    Optional<Pedido> mostrarPedido (Integer cod);

    void atualizaPedido(int id, StatusPedido statusPedido);
}
