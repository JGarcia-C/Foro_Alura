package com.alura.foro.domain.topico;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Topico")
@Table(name = "topicos")
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    @Column(name = "fecha", updatable = false, nullable = false)
    @JsonFormat(pattern = "dd MMM yyyy HH:mm")
    private LocalDateTime fecha;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String autor;
    private String curso;

    public Topico(DatosRegistroTopico datosRegistroTopico) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.status = datosRegistroTopico.status();
        this.autor = datosRegistroTopico.autor();
        this.curso = datosRegistroTopico.curso();
    }

    public Topico() {
    }

    public void actualizarDatos(DatosActualizadosTopico datosActualizadosTopico) {
        if (datosActualizadosTopico.titulo() != null) {
            this.titulo = datosActualizadosTopico.titulo();
        }
        if (datosActualizadosTopico.mensaje() != null) {
            this.mensaje = datosActualizadosTopico.mensaje();
        }
        if (datosActualizadosTopico.status() != null) {
            this.status = datosActualizadosTopico.status();
        }
        if (datosActualizadosTopico.clase() != null){
            this.curso = datosActualizadosTopico.clase();
        }
    }

    @PrePersist
    public void prePersist() {
        this.fecha = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Status getStatus() {
        return status;
    }

    public String getAutor() {
        return autor;
    }

    public String getCurso() {
        return curso;
    }
}
