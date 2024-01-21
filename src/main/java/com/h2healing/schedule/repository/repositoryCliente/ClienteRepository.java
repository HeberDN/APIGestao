package com.h2healing.schedule.repository.repositoryCliente;

import com.h2healing.schedule.model.cliente.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<ClienteModel, UUID> {
}
