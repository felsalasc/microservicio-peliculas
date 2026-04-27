package com.example.demo.controller;

import com.example.demo.model.Pelicula;
import com.example.demo.service.PeliculaService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private final PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Pelicula>>> obtenerTodas() {
        List<EntityModel<Pelicula>> peliculas = peliculaService.obtenerTodas().stream()
                .map(p -> EntityModel.of(p,
                        linkTo(methodOn(PeliculaController.class).obtenerPorId(p.getId())).withSelfRel(),
                        linkTo(methodOn(PeliculaController.class).obtenerTodas()).withRel("peliculas")))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Pelicula>> collection = CollectionModel.of(peliculas,
                linkTo(methodOn(PeliculaController.class).obtenerTodas()).withSelfRel());

        return ResponseEntity.ok(collection);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Pelicula>> obtenerPorId(@PathVariable Long id) {
        return peliculaService.obtenerPorId(id)
                .map(p -> EntityModel.of(p,
                        linkTo(methodOn(PeliculaController.class).obtenerPorId(p.getId())).withSelfRel(),
                        linkTo(methodOn(PeliculaController.class).obtenerTodas()).withRel("peliculas")))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //  ingresar película
    @PostMapping
    public ResponseEntity<EntityModel<Pelicula>> crear(@RequestBody Pelicula pelicula) {
        Pelicula nueva = peliculaService.guardar(pelicula);
        EntityModel<Pelicula> model = EntityModel.of(nueva,
                linkTo(methodOn(PeliculaController.class).obtenerPorId(nueva.getId())).withSelfRel(),
                linkTo(methodOn(PeliculaController.class).obtenerTodas()).withRel("peliculas"));
        return ResponseEntity
                .created(URI.create("/peliculas/" + nueva.getId()))
                .body(model);
    }

    // modificar
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Pelicula>> actualizar(@PathVariable Long id, @RequestBody Pelicula pelicula) {
        try {
            Pelicula actualizada = peliculaService.actualizar(id, pelicula);
            EntityModel<Pelicula> model = EntityModel.of(actualizada,
                    linkTo(methodOn(PeliculaController.class).obtenerPorId(actualizada.getId())).withSelfRel(),
                    linkTo(methodOn(PeliculaController.class).obtenerTodas()).withRel("peliculas"));
            return ResponseEntity.ok(model);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //  eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            peliculaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}