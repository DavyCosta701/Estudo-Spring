package com.company.davyc.domain.repository;

import com.company.davyc.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoSD extends JpaRepository<Produto, Integer> {

}
