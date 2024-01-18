package com.h2healing.schedule.model.produto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.h2healing.schedule.model.estoque.MovimentacaoEstoqueModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class ProdutoUnicoModel extends ProdutoModel {
    @JsonIgnore
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<MovimentacaoEstoqueModel> movimentacoesEstoque;
    public ProdutoUnicoModel (RegistrarProdutoUnicoDTO registrarProdutoDTO){
        this.setCodigo(registrarProdutoDTO.codigo());
        this.setNomeProduto(registrarProdutoDTO.nomeProduto());
        this.setUnidade(registrarProdutoDTO.unidade());
        this.setCustoUnitario(registrarProdutoDTO.custoUnitario());
        this.setValorVendaUnitario(registrarProdutoDTO.valorVendaUnitario());
        this.setSaldo(registrarProdutoDTO.saldo());
    }
}
