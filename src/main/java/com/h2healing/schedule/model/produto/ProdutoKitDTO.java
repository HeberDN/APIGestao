package com.h2healing.schedule.model.produto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
@JsonTypeName("produtoKit")
public record ProdutoKitDTO(
        String codigo,
        String nomeProduto,
        String unidade,
        BigDecimal custoUnitario,
        BigDecimal valorUnitario,
        List<InterfaceProdutoDTO> produtosNoKit) implements InterfaceProdutoDTO {

    @Override
    public String getTipo() {
        return "produtoKit";
    }

    @Override
    public String getCodigo() {
        return codigo;
    }

    @Override
    public String getNomeProduto() {
        return nomeProduto;
    }

    @Override
    public String getUnidade() {
        return unidade;
    }

    @Override
    public BigDecimal getCustoUnitario() {
        return custoUnitario;
    }

    @Override
    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }


    @Override
    public ProdutoModel toProdutoModel() {
        List<Composicao> composicoes = produtosNoKit.stream()
                .map(dto -> new Composicao(null, null, (ProdutoUnicoModel) dto.toProdutoModel(), BigDecimal.ZERO))
                .collect(Collectors.toList());

        ProdutoKitModel produtoKitModel = new ProdutoKitModel();
        produtoKitModel.setCodigo(codigo);
        produtoKitModel.setNomeProduto(nomeProduto);
        produtoKitModel.setUnidade(unidade);
        produtoKitModel.setCustoUnitario(custoUnitario);
        produtoKitModel.setValorVendaUnitario(valorUnitario);
        produtoKitModel.setProdutosNoKit(composicoes);

        return produtoKitModel;
    }
}
