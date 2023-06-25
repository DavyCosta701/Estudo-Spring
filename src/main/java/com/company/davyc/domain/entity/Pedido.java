package com.company.davyc.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class Pedido {

    private Integer ID;
    private Integer Cliente_ID;
    private LocalDate Data_Pedido;
    private BigDecimal Total;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getCliente_ID() {
        return Cliente_ID;
    }

    public void setCliente_ID(Integer cliente_ID) {
        Cliente_ID = cliente_ID;
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

    public Pedido(Integer ID, Integer cliente_ID, LocalDate data_Pedido, BigDecimal total) {
        this.ID = ID;
        Cliente_ID = cliente_ID;
        Data_Pedido = data_Pedido;
        Total = total;
    }

    public Pedido(Integer cliente_ID, LocalDate data_Pedido, BigDecimal total) {
        Cliente_ID = cliente_ID;
        Data_Pedido = data_Pedido;
        Total = total;
    }
}
