package com.example.demo.controller;

import com.example.demo.model.Pelicula;
import com.example.demo.service.PeliculaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private final PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping
    public List<Pelicula> obtenerTodas() {
        return peliculaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> obtenerPorId(@PathVariable Long id) {
        Pelicula pelicula = peliculaService.obtenerPorId(id);

        if (pelicula == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pelicula);
    }
}