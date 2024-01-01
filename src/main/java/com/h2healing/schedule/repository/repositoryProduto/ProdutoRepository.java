package com.h2healing.schedule.repository.repositoryProduto;

import com.h2healing.schedule.model.produto.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, String> {
    Optional<ProdutoModel> findByCodigo(String codigo);
}
