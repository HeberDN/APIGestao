package com.h2healing.schedule.model.estoque;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record MovimentacaoEstoqueDTO(@NotBlank String codigoProduto, @NotNull BigDecimal quantidade) {
}
