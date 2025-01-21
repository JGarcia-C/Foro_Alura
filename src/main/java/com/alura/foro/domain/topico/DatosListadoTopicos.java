package com.alura.foro.domain.topico;

import java.time.LocalDateTime;

public record DatosListadoTopicos(
        Long id,
        String  titulo,
        String mensaje,
        Status status,
        LocalDateTime fecha,
        String autor,
        String curso) {
    public DatosListadoTopicos(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getStatus()
                ,topico.getFecha(), topico.getAutor(), topico.getCurso());
    }
}
