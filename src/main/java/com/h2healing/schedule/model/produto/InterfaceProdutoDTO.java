package com.h2healing.schedule.model.produto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.math.BigDecimal;

public interface InterfaceProdutoDTO {
    String getTipo();
    String getCodigo();
    String getNomeProduto();
    String getUnidade();
    BigDecimal getCustoUnitario();
    BigDecimal getValorUnitario();
    ProdutoModel toProdutoModel();
}
