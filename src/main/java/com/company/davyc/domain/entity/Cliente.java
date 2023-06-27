package com.company.davyc.domain.entity;


import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "cliente")

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ID;
    @Column(length = 100)
    private String NOME;
    @OneToMany(mappedBy = "cliente")
    private Set<Pedido> pedidos;


    public Integer getid() {
        return ID;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void setid(Integer id) {
        this.ID = id;
    }

    public String getNome() {
        return NOME;
    }

    public void setNome(String nome) {
        NOME = nome;
    }

    public Cliente() {
    }

    public Cliente(Integer id, String nome) {
        this.ID = id;
        NOME = nome;
    }

    public Cliente(String nome) {
        NOME = nome;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "ID=" + ID +
                ", NOME='" + NOME + '\'' +
                ", pedidos=" + pedidos +
                '}';
    }
}
