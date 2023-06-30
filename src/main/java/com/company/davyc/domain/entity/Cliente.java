package com.company.davyc.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "cliente")


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cliente implements ClienteInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ID;

    @Column(length = 100)
    private String NOME;
    @Column(name = "cpf", length = 11)
    private String cpf;

    public Cliente(String NOME, String cpf) {
        this.NOME = NOME;
        this.cpf = cpf;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    @ToString.Exclude
    private Set<Pedido> pedidos;

}


