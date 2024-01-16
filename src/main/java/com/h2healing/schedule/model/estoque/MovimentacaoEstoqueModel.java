package com.h2healing.schedule.model.estoque;

import com.h2healing.schedule.model.produto.ProdutoModel;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity (name = "movimentacao_estoque")
public class MovimentacaoEstoqueModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoModel produto;

    @JoinColumn(name = "produto_codigo", nullable = false)
    private String codigoProduto;

    @Column(nullable = false)
    private LocalDateTime dataMovimentacao;

    @Column(nullable = false)
    private BigDecimal quantidade;

    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipoMovimentacao;

    @Enumerated(EnumType.STRING)
    private OrigemMovimentacao origemMovimentacao;
}