package com.h2healing.schedule.model.usuario;

public record RegisterDTO(String login, String password, UsuarioRole role) {
}
