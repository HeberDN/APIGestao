package com.h2healing.schedule.services.movimentacaoEstoque;

import com.h2healing.schedule.exception.dominio.produto.ProdutoNaoEncontradoException;
import com.h2healing.schedule.model.estoque.MovimentacaoEstoqueDTO;
import com.h2healing.schedule.model.estoque.MovimentacaoEstoqueModel;
import com.h2healing.schedule.model.estoque.TipoMovimentacao;
import com.h2healing.schedule.model.produto.ProdutoModel;
import com.h2healing.schedule.repository.repositoryEstoque.EstoqueRepository;
import com.h2healing.schedule.repository.repositoryProduto.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MovimentacaoEstoqueService {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private EstoqueRepository estoqueRepository;
    @Transactional
    public void registrarEntradaEstoque(MovimentacaoEstoqueDTO entradaEstoqueDTO){
        System.out.println(LocalDateTime.now() + " Chamado registrarEntradaEstoque");
        ProdutoModel produto = obterProduto (entradaEstoqueDTO.codigoProduto());
        MovimentacaoEstoqueModel movimentacaoEntrada = new MovimentacaoEstoqueModel();
        movimentacaoEntrada.setProduto(produto);
        movimentacaoEntrada.setCodigoProduto(entradaEstoqueDTO.codigoProduto());
        movimentacaoEntrada.setDataMovimentacao(LocalDateTime.now());
        movimentacaoEntrada.setQuantidade(entradaEstoqueDTO.quantidade());
        movimentacaoEntrada.setTipoMovimentacao(TipoMovimentacao.ENTRADA);
        // Persiste a movimentação de estoque
        estoqueRepository.save(movimentacaoEntrada);
        // Atualiza o saldo no produto
        produto.setSaldo(produto.getSaldo().add(entradaEstoqueDTO.quantidade()));
        produtoRepository.save(produto);
    }
    @Transactional
    public void registrarSaidaEstoque(MovimentacaoEstoqueDTO saidaEstoqueDTO){
        System.out.println(LocalDateTime.now() + " Chamado registrarSaidaEstoque");
        ProdutoModel produto = obterProduto(saidaEstoqueDTO.codigoProduto());
        MovimentacaoEstoqueModel movimentacaoSaida = new MovimentacaoEstoqueModel();
        movimentacaoSaida.setProduto(produto);
        movimentacaoSaida.setCodigoProduto(saidaEstoqueDTO.codigoProduto());
        movimentacaoSaida.setDataMovimentacao(LocalDateTime.now());
        movimentacaoSaida.setQuantidade(saidaEstoqueDTO.quantidade());
        movimentacaoSaida.setTipoMovimentacao(TipoMovimentacao.SAIDA);
        // Persiste a movimentação de estoque
        estoqueRepository.save(movimentacaoSaida);
        // Atualiza o saldo no produto
        produto.setSaldo(produto.getSaldo().subtract(saidaEstoqueDTO.quantidade()));
        produtoRepository.save(produto);
    }

    private ProdutoModel obterProduto(String codigoProduto)throws ProdutoNaoEncontradoException {
        Optional<ProdutoModel> produtoOptional = produtoRepository.findByCodigo(codigoProduto);
        if(produtoOptional.isPresent()){
            return produtoOptional.get();
        }else{
            throw new ProdutoNaoEncontradoException(codigoProduto);
        }
    }
}
