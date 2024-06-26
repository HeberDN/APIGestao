package com.h2healing.schedule.repository.repositoryUsuario;

import com.h2healing.schedule.model.usuario.UsuarioModel;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@DependsOn("entityManagerFactory")
public interface UsuarioRepository extends JpaRepository<UsuarioModel, String> {

    UserDetails findByLogin (String login);
}
