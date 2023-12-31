package com.h2healing.schedule.repository.repositoryProduto;

import com.h2healing.schedule.model.produto.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, String> {
}
