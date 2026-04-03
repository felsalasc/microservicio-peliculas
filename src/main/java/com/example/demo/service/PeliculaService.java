package com.example.demo.service;

import com.example.demo.model.Pelicula;
import com.example.demo.repository.PeliculaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;

    public PeliculaService(PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }

    public List<Pelicula> obtenerTodas() {
        return peliculaRepository.findAll();
    }

    public Optional<Pelicula> obtenerPorId(Long id) {
        return peliculaRepository.findById(id);
    }

    public Pelicula guardar(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    public Pelicula actualizar(Long id, Pelicula peliculaActualizada) {
        return peliculaRepository.findById(id)
                .map(pelicula -> {
                    pelicula.setTitulo(peliculaActualizada.getTitulo());
                    pelicula.setAnio(peliculaActualizada.getAnio());
                    pelicula.setDirector(peliculaActualizada.getDirector());
                    pelicula.setGenero(peliculaActualizada.getGenero());
                    pelicula.setSinopsis(peliculaActualizada.getSinopsis());
                    return peliculaRepository.save(pelicula);
                })
                .orElseThrow(() -> new RuntimeException("Película no encontrada con id: " + id));
    }

    public void eliminar(Long id) {
        peliculaRepository.deleteById(id);
    }
}