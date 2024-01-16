package com.h2healing.schedule.model.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RegistrarProdutoUnicoDTO(
        @NotNull
        String codigo,
        @NotBlank
        String nomeProduto,
        @NotBlank
        String unidade,
        @NotNull
        BigDecimal custoUnitario,
        @NotNull
        BigDecimal valorVendaUnitario,
        @NotNull
        BigDecimal saldo
) {
}
