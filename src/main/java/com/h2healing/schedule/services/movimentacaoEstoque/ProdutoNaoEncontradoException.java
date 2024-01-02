package com.h2healing.schedule.services.movimentacaoEstoque;

public class ProdutoNaoEncontradoException extends RuntimeException {
    public ProdutoNaoEncontradoException(String codigoProduto) {
        super("Produto não encontrado com codigo: "+codigoProduto);
    }
}
