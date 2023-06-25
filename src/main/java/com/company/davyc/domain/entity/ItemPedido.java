package com.company.davyc.domain.entity;

public class ItemPedido {

    private Integer ID;
    private Integer Pedido_ID;
    private Integer Produto_ID;
    private Integer Quantidade;

    public ItemPedido() {
    }

    public ItemPedido(Integer pedido_ID, Integer produto_ID, Integer quantidade) {
        Pedido_ID = pedido_ID;
        Produto_ID = produto_ID;
        Quantidade = quantidade;
    }

    public ItemPedido(Integer ID, Integer pedido_ID, Integer produto_ID, Integer quantidade) {
        this.ID = ID;
        Pedido_ID = pedido_ID;
        Produto_ID = produto_ID;
        Quantidade = quantidade;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getPedido_ID() {
        return Pedido_ID;
    }

    public void setPedido_ID(Integer pedido_ID) {
        Pedido_ID = pedido_ID;
    }

    public Integer getProduto_ID() {
        return Produto_ID;
    }

    public void setProduto_ID(Integer produto_ID) {
        Produto_ID = produto_ID;
    }

    public Integer getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        Quantidade = quantidade;
    }
}
