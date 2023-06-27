package com.company.davyc.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ID;
    private String Descricao;
    @Column(length = 20, precision = 2)
    private BigDecimal Preco_Unitario;

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    @OneToMany(mappedBy = "produto")
    private List<ItemPedido> itensPedido;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public BigDecimal getPreco_Unitario() {
        return Preco_Unitario;
    }

    public void setPreco_Unitario(BigDecimal preco_Unitario) {
        Preco_Unitario = preco_Unitario;
    }

    public Produto() {
    }

    public Produto(Integer ID, String descricao, BigDecimal preco_Unitario) {
        this.ID = ID;
        Descricao = descricao;
        Preco_Unitario = preco_Unitario;
    }

    public Produto(String descricao, BigDecimal preco_Unitario) {
        Descricao = descricao;
        Preco_Unitario = preco_Unitario;
    }
}
