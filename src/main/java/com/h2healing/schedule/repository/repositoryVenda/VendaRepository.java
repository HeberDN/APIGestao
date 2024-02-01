package com.h2healing.schedule.repository.repositoryVenda;

import com.h2healing.schedule.model.venda.VendaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface VendaRepository extends JpaRepository<VendaModel, UUID> {
    @Query("SELECT MAX(v.numeroSequencial) FROM VendaModel v WHERE v.ano = :ano")
    Optional<Long> findMaxNumeroSequencial(@Param("ano") int ano);
    Optional<VendaModel> findByNumeroSequencialAndAno(Long numeroSequencial, int ano);
}
