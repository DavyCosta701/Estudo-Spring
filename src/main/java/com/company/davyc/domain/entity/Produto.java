package com.company.davyc.domain.entity;

import com.company.davyc.domain.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "produto")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ID;
    @Column(name = "descricao")
    private String Descricao;
    @Column(name = "preco_unitario",length = 2, precision = 20)
    private BigDecimal Preco_Unitario;
    @JsonIgnore

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }
    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    @OneToMany(mappedBy = "produto")
    @ToString.Exclude
    private List<ItemPedido> itensPedido;

}
