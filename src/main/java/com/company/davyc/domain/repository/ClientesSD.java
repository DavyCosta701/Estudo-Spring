package com.company.davyc.domain.repository;

import com.company.davyc.domain.entity.Cliente;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientesSD extends JpaRepository<Cliente, Integer> {

    @Query(value = " select c from Cliente c where :nome = c.NOME")
    Cliente encontrarCliente(@Param("nome") String nome);

    @Transactional
    void deleteByNOME(String nome);

    @Query(value = "SELECT c from Cliente c left join fetch c.pedidos where c.ID = :id")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);


}
