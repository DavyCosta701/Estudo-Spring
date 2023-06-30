package com.company.davyc.api.DTO;

import com.company.davyc.domain.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoViewDTO {

    private Integer codigo;
    private String nomeCliente;
    private String cpfCliente;
    private BigDecimal total;
    private List<ItensPedidoViewDTO> items;
    private String dataPedido;
    private String statusPedido;

}
