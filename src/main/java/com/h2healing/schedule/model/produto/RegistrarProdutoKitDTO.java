package com.h2healing.schedule.model.produto;

import java.math.BigDecimal;
import java.util.List;

public record RegistrarProdutoKitDTO (
        String codigo,
        String nomeProduto,
        String unidade,
        BigDecimal custoUnitario,
        BigDecimal valorVendaUnitario,
        ClassificacaoProduto classificacaoProduto,
        List<ProdutoUnicoModel> produtosNoKit
){}
