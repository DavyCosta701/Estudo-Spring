package com.company.davyc.domain.repository;

import com.company.davyc.domain.entity.Cliente;
import com.company.davyc.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface PedidoSD extends JpaRepository<Pedido, Integer> {

    Set<Pedido> getPedidosByCliente(Cliente cliente);

    @Query(" select p from Pedido p left join fetch p.itensPedido where p.ID = :id")
    Optional<Pedido> fetchItensPorID(Integer id);



}
