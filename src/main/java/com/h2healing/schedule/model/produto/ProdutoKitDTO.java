package com.h2healing.schedule.model.produto;

import java.util.List;

public record ProdutoKitDTO(
        String nome,
        String descricao,
        List <String> codigosProdutos
) {}
