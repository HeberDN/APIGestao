package com.h2healing.schedule.model.usuario;

import java.time.LocalDate;

public record UsuarioDTO(
        String nome,
        String cpf,
        String login,
        String password,
        LocalDate dataNascimento,
        StatusUsuario status,
        UsuarioRole role
) {}
