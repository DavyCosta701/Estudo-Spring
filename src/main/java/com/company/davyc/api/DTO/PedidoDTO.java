package com.company.davyc.api.DTO;

import com.company.davyc.validation.NotEmptyList;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    @NotNull(message = "Erro de validação, cliente não pode ser nulo")
    private Integer cliente;
    @NotNull(message = "Erro de validação, total não pode ser nulo")
    private BigDecimal total;
    @NotEmptyList(message = "Carrinho Vazio!")
    private List<ItemPedidoDTO> items;

}
