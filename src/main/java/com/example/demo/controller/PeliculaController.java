package com.example.demo.controller;

import com.example.demo.model.Pelicula;
import com.example.demo.service.PeliculaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private final PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping
    public ResponseEntity<List<Pelicula>> obtenerTodas() {
        return ResponseEntity.ok(peliculaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> obtenerPorId(@PathVariable Long id) {
        return peliculaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pelicula> crear(@RequestBody Pelicula pelicula) {
        Pelicula nueva = peliculaService.guardar(pelicula);
        return ResponseEntity.created(URI.create("/peliculas/" + nueva.getId())).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> actualizar(@PathVariable Long id, @RequestBody Pelicula pelicula) {
        try {
            return ResponseEntity.ok(peliculaService.actualizar(id, pelicula));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        peliculaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}