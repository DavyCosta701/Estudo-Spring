package com.company.davyc.api.DTO;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItensPedidoViewDTO {

    private String descricao;
    private BigDecimal valor;
    private int quantidade;
}
