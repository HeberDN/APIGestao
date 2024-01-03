package com.h2healing.schedule.services.produto;

import com.h2healing.schedule.model.produto.ProdutoDTO;
import com.h2healing.schedule.model.produto.ProdutoModel;
import com.h2healing.schedule.repository.repositoryProduto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    @Autowired
    ProdutoRepository produtoRepository;
    public List<ProdutoDTO> getAllProdutosDTO(){
        List<ProdutoModel> produtos = produtoRepository.findAll();
        List<ProdutoDTO> produtosDTO = produtos.stream()
                .map(this::mapToProdutoDTO)
                .collect(Collectors.toList());
        return produtosDTO;
    }

    private ProdutoDTO mapToProdutoDTO (ProdutoModel produtoModel) {
        return new ProdutoDTO(
                produtoModel.getCodigo(),
                produtoModel.getNomeProduto(),
                produtoModel.getUnidade(),
                produtoModel.getCustoUnitario(),
                produtoModel.getValorVendaUnitario(),
                produtoModel.getSaldo());
    }
}
