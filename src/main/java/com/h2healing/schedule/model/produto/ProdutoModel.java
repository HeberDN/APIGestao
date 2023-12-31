package com.h2healing.schedule.model.produto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produtos")
@Entity(name = "produtos")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class ProdutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "codigo", unique = true)
    private String codigo;
    @Column(name = "nome_produto", nullable = false)
    private String nomeProduto;
    @Column(name = "unidade", nullable = false)
    private String unidade;
    @Column(name = "custo_unitario", nullable = false)
    private BigDecimal custoUnitario;
    @Column(name = "valor_venda", nullable = false)
    private BigDecimal valorVendaUnitario;
    @Column(name = "saldo", nullable = false)
    private BigDecimal saldo;

    public ProdutoModel (RegistrarProdutoDTO registrarProdutoDTO){
        this.codigo = registrarProdutoDTO.codigo();
        this.nomeProduto = registrarProdutoDTO.nomeProduto();
        this.unidade = registrarProdutoDTO.unidade();
        this.custoUnitario = registrarProdutoDTO.custoUnitario();
        this.valorVendaUnitario = registrarProdutoDTO.valorVendaUnitario();
        this.saldo = registrarProdutoDTO.saldo();
    }
}
