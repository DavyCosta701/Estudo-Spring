package com.company.davyc.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ID;
    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID")
    private Cliente cliente;
    private LocalDate Data_Pedido;
    @Column(precision = 20, scale = 2)
    private BigDecimal Total;
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itensPedido;

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getData_Pedido() {
        return Data_Pedido;
    }

    public void setData_Pedido(LocalDate data_Pedido) {
        Data_Pedido = data_Pedido;
    }

    public BigDecimal getTotal() {
        return Total;
    }

    public void setTotal(BigDecimal total) {
        Total = total;
    }

    public Pedido() {
    }

    public Pedido(Integer ID, Cliente cliente, LocalDate data_Pedido, BigDecimal total) {
        this.ID = ID;
        this.cliente = cliente;
        Data_Pedido = data_Pedido;
        Total = total;
    }

    public Pedido(Cliente cliente, LocalDate data_Pedido, BigDecimal total) {
        this.cliente = cliente;
        Data_Pedido = data_Pedido;
        Total = total;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "Data_Pedido=" + Data_Pedido +
                ", Total=" + Total +
                '}';
    }
}
