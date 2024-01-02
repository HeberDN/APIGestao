package com.h2healing.schedule.repository.repositoryEstoque;

import com.h2healing.schedule.model.estoque.MovimentacaoEstoqueModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface EstoqueRepository extends JpaRepository<MovimentacaoEstoqueModel, UUID> {
}
