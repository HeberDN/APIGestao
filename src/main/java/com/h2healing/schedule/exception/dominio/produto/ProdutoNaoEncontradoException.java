package com.h2healing.schedule.exception.dominio.produto;

public class ProdutoNaoEncontradoException extends RuntimeException {
    public ProdutoNaoEncontradoException(String codigoProduto) {
        super("Produto não encontrado com codigo: "+codigoProduto);
    }
}
