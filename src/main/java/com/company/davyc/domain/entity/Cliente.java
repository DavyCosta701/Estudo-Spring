package com.company.davyc.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

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
    @NotEmpty(message = "campo.codigo-cliente.obrigatorio")
    private String NOME;
    @Column(name = "cpf", length = 11)
    @CPF(message = "campo.cpf.obrigatorio")
    @NotEmpty(message = "")
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


