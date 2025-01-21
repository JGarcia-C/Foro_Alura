package com.alura.foro.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DatosActualizadosTopico(
        @NotNull
        Long id,
        String titulo,
        String mensaje,
        Status status,
        String clase) {
}
