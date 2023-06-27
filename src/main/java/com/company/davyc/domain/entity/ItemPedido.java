package com.company.davyc.domain.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ID;
    @ManyToOne
    @JoinColumn(name = "PEDIDO_ID")
    private Pedido pedido;
    @ManyToOne
    @JoinColumn(name = "PRODUTO_ID")
    private Produto produto;
    private Integer Quantidade;


    public ItemPedido() {
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public ItemPedido(Integer ID, Pedido pedido, Produto produto, Integer quantidade) {
        this.ID = ID;
        this.pedido = pedido;
        this.produto = produto;
        Quantidade = quantidade;
    }

    public ItemPedido(Pedido pedido, Produto produto, Integer quantidade) {
        this.pedido = pedido;
        this.produto = produto;
        Quantidade = quantidade;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        Quantidade = quantidade;
    }
}
