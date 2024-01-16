package com.h2healing.schedule.model.produto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.h2healing.schedule.model.estoque.MovimentacaoEstoqueModel;
import com.h2healing.schedule.repository.repositoryProduto.ProdutoRepository;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.access.method.P;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProdutoKitModel extends ProdutoModel {
    @ManyToMany
    @JoinTable(
            name = "produto_kit",
            joinColumns = @JoinColumn(name = "kit_id"),
            inverseJoinColumns =@JoinColumn(name = "produto_id")
    )
    private List<ProdutoUnicoModel> produtosNoKit;
}