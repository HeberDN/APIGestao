package com.h2healing.schedule.model.venda;

import com.h2healing.schedule.model.cliente.ClienteModel;
import com.h2healing.schedule.model.produto.ProdutoModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vendas")
public class VendaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "numero_sequencial")
    private Long numeroSequencial;
    private int ano;

    private LocalDateTime data;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteModel cliente;
    @ManyToMany
    @JoinTable(
            name = "venda_produto",
            joinColumns = @JoinColumn(name = "venda_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<ProdutoModel> produtos;
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaDePagamento;
    private String observacao;
}
