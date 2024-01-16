package com.h2healing.schedule.repository.repositoryProduto;

import com.h2healing.schedule.model.produto.ProdutoKitModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoKitRepository extends JpaRepository<ProdutoKitModel, UUID> {
}
