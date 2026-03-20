package com.example.demo.service;

import com.example.demo.model.Pelicula;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeliculaService {

    private final List<Pelicula> peliculas = new ArrayList<>();

    public PeliculaService() {
        peliculas.add(new Pelicula(1L, "Inception", 2010, "Christopher Nolan", "Ciencia ficción",
                "Un ladrón especializado en robar secretos a través de los sueños."));

        peliculas.add(new Pelicula(2L, "The Godfather", 1972, "Francis Ford Coppola", "Drama",
                "La historia de una poderosa familia mafiosa italiana en Estados Unidos."));

        peliculas.add(new Pelicula(3L, "Titanic", 1997, "James Cameron", "Romance",
                "Una historia de amor a bordo del famoso transatlántico."));

        peliculas.add(new Pelicula(4L, "The Matrix", 1999, "Lana y Lilly Wachowski", "Acción",
                "Un programador descubre que la realidad es una simulación."));

        peliculas.add(new Pelicula(5L, "Interstellar", 2014, "Christopher Nolan", "Ciencia ficción",
                "Un grupo de exploradores viaja por el espacio para salvar a la humanidad."));
    }

    public List<Pelicula> obtenerTodas() {
        return peliculas;
    }

    public Pelicula obtenerPorId(Long id) {
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getId().equals(id)) {
                return pelicula;
            }
        }
        return null;
    }
}