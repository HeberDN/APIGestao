package com.h2healing.schedule.model.produto;

import java.math.BigDecimal;

public record ProdutoDTO(
        String codigo,
        String nomeProduto,
        String unidade,
        BigDecimal custoUnitario,
        BigDecimal valorUnitario) {
}
