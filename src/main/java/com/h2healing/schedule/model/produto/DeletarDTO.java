package com.h2healing.schedule.model.produto;

import jakarta.validation.constraints.NotBlank;

public record DeletarDTO(
        @NotBlank
        String codigo
) {}
