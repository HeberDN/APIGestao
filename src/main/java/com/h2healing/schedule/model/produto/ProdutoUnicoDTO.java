package com.h2healing.schedule.model.produto;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.math.BigDecimal;
@JsonTypeName("produtoUnico")
public record ProdutoUnicoDTO(
        String codigo,
        String nomeProduto,
        String unidade,
        BigDecimal custoUnitario,
        BigDecimal valorUnitario,
        BigDecimal saldo
) implements InterfaceProdutoDTO{
    @Override
    public String getTipo() {
        return "produtoUnico";
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

    public BigDecimal getSaldo() {
        return saldo;
    }

    @Override
    public ProdutoModel toProdutoModel() {
        ProdutoUnicoModel produtoUnicoModel = new ProdutoUnicoModel();
        produtoUnicoModel.setCodigo(codigo);
        produtoUnicoModel.setNomeProduto(nomeProduto);
        produtoUnicoModel.setUnidade(unidade);
        produtoUnicoModel.setCustoUnitario(custoUnitario);
        produtoUnicoModel.setValorVendaUnitario(valorUnitario);
        produtoUnicoModel.setSaldo(saldo);
        return produtoUnicoModel;
    }
}
