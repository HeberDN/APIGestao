package com.h2healing.schedule.services.movimentacaoEstoque;

import com.h2healing.schedule.exception.dominio.produto.ProdutoNaoEncontradoException;
import com.h2healing.schedule.model.estoque.MovimentacaoEstoqueDTO;
import com.h2healing.schedule.model.estoque.MovimentacaoEstoqueModel;
import com.h2healing.schedule.model.estoque.TipoMovimentacao;
import com.h2healing.schedule.model.produto.Composicao;
import com.h2healing.schedule.model.produto.ProdutoKitModel;
import com.h2healing.schedule.model.produto.ProdutoModel;
import com.h2healing.schedule.model.produto.ProdutoUnicoModel;
import com.h2healing.schedule.repository.repositoryEstoque.EstoqueRepository;
import com.h2healing.schedule.repository.repositoryProduto.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
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
        movimentacaoEntrada.setOrigemMovimentacao(entradaEstoqueDTO.origemMovimentacao());
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
        movimentacaoSaida.setOrigemMovimentacao(saidaEstoqueDTO.origemMovimentacao());
        // Persiste a movimentação de estoque
        estoqueRepository.save(movimentacaoSaida);
        // Atualiza o saldo no produto
        produto.setSaldo(produto.getSaldo().subtract(saidaEstoqueDTO.quantidade()));
        produtoRepository.save(produto);
    }
    @Transactional
    public void registrarBaixaProdutoKit(MovimentacaoEstoqueDTO baixarProdutoKitDTO) {
        ProdutoModel produtoKit = obterProduto(baixarProdutoKitDTO.codigoProduto());

        if (produtoKit instanceof ProdutoKitModel) {
            ProdutoKitModel kit = (ProdutoKitModel) produtoKit;

            // Itera sobre os produtos no kit
            for (Composicao composicao : kit.getProdutosNoKit()) {
                BigDecimal quantidadeBaixada = baixarProdutoKitDTO.quantidade().multiply(composicao.getQuantidadeNoKit());

                MovimentacaoEstoqueModel movimentacaoBaixaProduto = new MovimentacaoEstoqueModel();
                movimentacaoBaixaProduto.setProduto(composicao.getProduto());
                movimentacaoBaixaProduto.setCodigoProduto(composicao.getProduto().getCodigo());
                movimentacaoBaixaProduto.setDataMovimentacao(LocalDateTime.now());
                movimentacaoBaixaProduto.setQuantidade(quantidadeBaixada);
                movimentacaoBaixaProduto.setTipoMovimentacao(TipoMovimentacao.SAIDA);
                movimentacaoBaixaProduto.setOrigemMovimentacao(movimentacaoBaixaProduto.getOrigemMovimentacao());

                // Persiste a movimentação
                estoqueRepository.save(movimentacaoBaixaProduto);

                // Atualiza o saldo no produto
                composicao.getProduto().setSaldo(composicao.getProduto().getSaldo().subtract(quantidadeBaixada));
                produtoRepository.save(composicao.getProduto());
            }
        } else {
            throw new RuntimeException("Produto não é um kit");
        }
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
