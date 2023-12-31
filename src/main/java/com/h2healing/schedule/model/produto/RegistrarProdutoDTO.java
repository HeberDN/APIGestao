package com.h2healing.schedule.model.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RegistrarProdutoDTO(

    String codigo,

    String nomeProduto,

    String unidade,

    BigDecimal custoUnitario,

    BigDecimal valorVendaUnitario,

    BigDecimal saldo
    ) { }
