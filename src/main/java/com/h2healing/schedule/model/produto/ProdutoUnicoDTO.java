package com.h2healing.schedule.model.produto;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.math.BigDecimal;

@JsonTypeName("produtoUnico")
public class ProdutoUnicoDTO implements InterfaceProdutoDTO {

    private String codigo;
    private String nomeProduto;
    private String unidade;
    private BigDecimal custoUnitario;
    private BigDecimal valorUnitario;
    private BigDecimal saldo;

    public ProdutoUnicoDTO(String codigo, String nomeProduto, String unidade, BigDecimal custoUnitario, BigDecimal valorUnitario, BigDecimal saldo) {
        this.codigo = codigo;
        this.nomeProduto = nomeProduto;
        this.unidade = unidade;
        this.custoUnitario = custoUnitario;
        this.valorUnitario = valorUnitario;
        this.saldo = saldo;
    }

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
