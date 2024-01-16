package com.h2healing.schedule.services.produto;

import com.h2healing.schedule.model.produto.*;

public interface ProdutoService {
    ProdutoModel cadastrarProduto(RegistrarProdutoUnicoDTO registrarProdutoDTO);

    ProdutoModel cadastrarProdutoKit(RegistrarProdutoKitDTO registrarProdutoKitDTO);
}
