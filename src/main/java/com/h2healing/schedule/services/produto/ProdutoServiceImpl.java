package com.h2healing.schedule.services.produto;

import com.h2healing.schedule.exception.dominio.produto.ProdutoException;
import com.h2healing.schedule.exception.dominio.produto.ProdutoPersistenciaException;
import com.h2healing.schedule.model.produto.*;
import com.h2healing.schedule.repository.repositoryProduto.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    @Transactional
    public ProdutoUnicoModel cadastrarProduto (RegistrarProdutoUnicoDTO data){
        if (produtoRepository.findByCodigo(data.codigo()).isPresent()) {
            throw new ProdutoException("Código de produto já existe: " + data.codigo());
        }
        ProdutoUnicoModel produto = new ProdutoUnicoModel(data);
        return produtoRepository.save(produto);
    }
    @Override
    @Transactional
    public ProdutoModel cadastrarProdutoKit(RegistrarProdutoKitDTO data) {
        // Verifica se o código já existe
        if (produtoRepository.findByCodigo(data.codigo()).isPresent()) {
            throw new ProdutoException("Código de produto já existe: " + data.codigo());
        }
        // Cria a lista de produtos no kit
        List<Composicao> produtosNoKit = new ArrayList<>();

        // Cria um novo ProdutoKitModel
        ProdutoKitModel produtoKit = new ProdutoKitModel();
        produtoKit.setCodigo(data.codigo());
        produtoKit.setNomeProduto(data.nomeProduto());
        produtoKit.setUnidade(data.unidade());
        produtoKit.setCustoUnitario(data.custoUnitario());
        produtoKit.setValorVendaUnitario(data.valorVendaUnitario());
        produtoKit.setClassificacaoProduto(data.classificacaoProduto());
        produtoKit.setProdutosNoKit(produtosNoKit);

        for (RegistrarProdutoKitDTO.ItemProdutoKitDTO itemDTO : data.produtosNoKit()) {
            Optional<ProdutoModel> optionalProduto = produtoRepository.findByCodigo(itemDTO.codigo());

            if (optionalProduto.isPresent() && optionalProduto.get() instanceof ProdutoUnicoModel) {
                ProdutoUnicoModel produtoExistente = (ProdutoUnicoModel) optionalProduto.get();

                // Verifica se a quantidadeNoKit é fornecida no DTO
                BigDecimal quantidadeNoKit = itemDTO.quantidadeNoKit();
                if (quantidadeNoKit == null) {
                    throw new ProdutoException("A quantidadeNoKit não pode ser nula para o produto: " + itemDTO.codigo());
                }

                Composicao produtoNoKit = Composicao.builder()
                        .kit(produtoKit)
                        .produto(produtoExistente)
                        .quantidadeNoKit(itemDTO.quantidadeNoKit())
                        .build();

                // Adiciona à lista de produtos no kit
                produtosNoKit.add(produtoNoKit);
            } else {
                throw new ProdutoException("Produto não encontrado ou não é válido: " + itemDTO.codigo());
            }
        }

        // Define a lista de produtos no kit
        produtoKit.setProdutosNoKit(produtosNoKit);
        try{
            // Persiste o produto kit no banco de dados
            ProdutoKitModel produtoKitSalvo = produtoRepository.save(produtoKit);
            return produtoKitSalvo;
        } catch (DataIntegrityViolationException e){
            throw new ProdutoPersistenciaException("Erro ao salvar o produto kit no banco de dados ", e);
        }

    }

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
                produtoModel.getValorVendaUnitario());
    }
}
