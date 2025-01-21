package com.alura.foro.controller;

import com.alura.foro.domain.ValidacionException;
import com.alura.foro.domain.topico.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> crearTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                                            UriComponentsBuilder uriComponentsBuilder) {

        Topico topico = new Topico(datosRegistroTopico);

        if (topicoRepository.existsByTitulo(topico.getTitulo())) {
            throw new ValidacionException("El título ya existe.");
        }

        if (topicoRepository.existsByMensaje(topico.getMensaje())) {
            throw new ValidacionException("El mensaje ya existe.");
        }

        topicoRepository.save(topico);

        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(
                topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getStatus(), topico.getFecha(), topico.getAutor(),
                topico.getCurso());

        URI url = uriComponentsBuilder.path("/topicos/{idTopico}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopicos>> listadoTopicos(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findAll(paginacion)
                .map(DatosListadoTopicos::new));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@RequestBody @Valid DatosActualizadosTopico datosActualizadosTopico,
                                                                 @PathVariable Long id) {
        if (!datosActualizadosTopico.id().equals(id)) {
            throw new ValidacionException("El ID en la URL no coincide con el ID en el cuerpo de la solicitud.");
        }
        Topico topico = topicoRepository.findById(id)
                .orElse(null);

        if (topico == null) {
            throw new ValidacionException("No se encontró un tópico con el ID proporcionado.");
        }

        topico.actualizarDatos(datosActualizadosTopico);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getStatus(),
                topico.getFecha(), topico.getAutor(), topico.getCurso()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@RequestBody @Valid DatosActualizadosTopico datosActualizadosTopico,
                                         @PathVariable Long id){
        if (!datosActualizadosTopico.id().equals(id)) {
            throw new ValidacionException("El ID en la URL no coincide con el ID en el cuerpo de la solicitud.");
        }
        Topico topico = topicoRepository.findById(id)
                .orElse(null);

        if (topico == null) {
            throw new ValidacionException("No se encontró un tópico con el ID proporcionado.");
        }
        topicoRepository.delete(topico);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> retornaDatosTopicos(@RequestBody @Valid DatosActualizadosTopico datosActualizadosTopico,
                                                                    @PathVariable Long id){
        if (!datosActualizadosTopico.id().equals(id)) {
            throw new ValidacionException("El ID en la URL no coincide con el ID en el cuerpo de la solicitud.");
        }
        Topico topico = topicoRepository.findById(id)
                .orElse(null);

        if (topico == null) {
            throw new ValidacionException("No se encontró un tópico con el ID proporcionado.");
        }

        var datosTopicos = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getStatus(),
                topico.getFecha(), topico.getAutor(), topico.getCurso());
        return ResponseEntity.ok(datosTopicos);
    }
}