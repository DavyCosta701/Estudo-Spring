package com.company.davyc.domain.repository;

import com.company.davyc.domain.entity.ItemPedido;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface ItemPedidoSD extends JpaRepository<ItemPedido, Integer> {


}
