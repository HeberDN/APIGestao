package com.h2healing.schedule.model.produto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.h2healing.schedule.model.estoque.MovimentacaoEstoqueModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produtos")
@Entity(name = "produtos")
@EqualsAndHashCode(of = "id")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ProdutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "codigo", unique = true, nullable = false)
    private String codigo;
    @Column(name = "nome_produto", nullable = false)
    private String nomeProduto;
    @Column(name = "unidade", nullable = false)
    private String unidade;
    @Column(name = "custo_unitario")
    private BigDecimal custoUnitario;
    @Column(name = "valor_venda")
    private BigDecimal valorVendaUnitario;
    @Enumerated(EnumType.STRING)
    @Column(name = "classificacao_produto")
    private ClassificacaoProduto classificacaoProduto;
}