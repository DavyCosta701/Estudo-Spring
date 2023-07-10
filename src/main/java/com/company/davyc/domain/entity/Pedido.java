package com.company.davyc.domain.entity;

import com.company.davyc.domain.enums.StatusPedido;
import jakarta.persistence.*;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID")
    private Cliente cliente;
    private LocalDate Data_Pedido;
    @Column(precision = 20, scale = 2)
    private BigDecimal Total;
    @OneToMany(mappedBy = "pedido")
    @ToString.Exclude
    private List<ItemPedido> itensPedido;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPedido statusPedido;

}
