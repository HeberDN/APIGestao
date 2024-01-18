package com.h2healing.schedule.model.produto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Composicao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "kit_id")
    private ProdutoKitModel kit;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoUnicoModel produto;
    @Column(nullable = false)
    private BigDecimal quantidadeNoKit;
}
