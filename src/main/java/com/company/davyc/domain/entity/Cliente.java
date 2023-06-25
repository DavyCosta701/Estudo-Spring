package com.company.davyc.domain.entity;

public class Cliente {
    private Integer ID;
    private String Nome;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public Cliente() {
    }

    public Cliente(Integer ID, String nome) {
        this.ID = ID;
        Nome = nome;
    }

    public Cliente(String nome) {
        Nome = nome;
    }

    @Override
    public String toString() {
        return ID + " "  + Nome;
    }
}
