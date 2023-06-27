package com.company.davyc.domain.repository;

import com.company.davyc.domain.entity.Cliente;
import com.company.davyc.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface PedidoSD extends JpaRepository<Pedido, Integer> {

    Set<Pedido> getPedidosByCliente(Cliente cliente);

}
