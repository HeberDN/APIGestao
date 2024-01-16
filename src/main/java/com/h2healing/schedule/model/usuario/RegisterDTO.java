package com.h2healing.schedule.model.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
        @NotBlank(message = "O campo 'nome' não pode estar em branco") @JsonProperty("nome") String nome,
        @NotBlank(message = "O campo 'e-mail' não pode estar em branco") @JsonProperty("login") String login,
        @NotBlank(message = "O campo 'senha' não pode estar em branco") @JsonProperty("password") String password,
        @NotNull(message = "O campo 'role' não pode ser nulo") @JsonProperty("role") UsuarioRole role) {
}
