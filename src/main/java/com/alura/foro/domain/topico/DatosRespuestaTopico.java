package com.alura.foro.domain.topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(

        Long id,
        String  titulo,
        String mensaje,
        Status status,
        LocalDateTime fecha,
        String autor,
        String curso) {
}
