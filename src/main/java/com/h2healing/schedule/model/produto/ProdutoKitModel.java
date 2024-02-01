package com.h2healing.schedule.model.produto;

import com.fasterxml.jackson.annotation.JsonTypeName;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class ProdutoKitModel extends ProdutoModel {

    @OneToMany(mappedBy = "kit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Composicao> produtosNoKit;
}