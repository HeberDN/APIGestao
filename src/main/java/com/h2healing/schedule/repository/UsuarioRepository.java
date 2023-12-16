package com.h2healing.schedule.repository;

import com.h2healing.schedule.model.usuario.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, String> {

    UserDetails findByLogin (String login);
}
