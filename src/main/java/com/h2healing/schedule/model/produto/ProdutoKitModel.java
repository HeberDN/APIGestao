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
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class ProdutoKitModel extends ProdutoModel {

    @OneToMany(mappedBy = "kit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Composicao> produtosNoKit;
}