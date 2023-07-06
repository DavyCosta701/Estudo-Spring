package com.company.davyc.domain.repository;

import com.company.davyc.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginSD extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByUsername(String username);

}
