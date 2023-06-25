package com.company.davyc.domain.entity;

import java.math.BigDecimal;

public class Produto {

    private Integer ID;
    private String Descricao;
    private BigDecimal Preco_Unitario;

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
