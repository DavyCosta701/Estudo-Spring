package com.company.davyc.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "item_pedido")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
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

}
