package com.example.demo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PeliculaTest {

    private Pelicula pelicula;

    @BeforeEach
    void setUp() {
        pelicula = new Pelicula(1L, "Inception", 2010, "Christopher Nolan", "Ciencia Ficción",
                "Un ladrón que roba secretos a través de sueños.");
    }

    @Test
    void constructorCompleto_asignaTodosLosCampos() {
        assertEquals(1L, pelicula.getId());
        assertEquals("Inception", pelicula.getTitulo());
        assertEquals(2010, pelicula.getAnio());
        assertEquals("Christopher Nolan", pelicula.getDirector());
        assertEquals("Ciencia Ficción", pelicula.getGenero());
        assertEquals("Un ladrón que roba secretos a través de sueños.", pelicula.getSinopsis());
    }

    @Test
    void setters_actualizanCamposCorrectamente() {
        pelicula.setTitulo("Interstellar");
        pelicula.setAnio(2014);
        pelicula.setDirector("Christopher Nolan");
        pelicula.setGenero("Aventura");
        pelicula.setSinopsis("Un grupo de exploradores viaja a través de un agujero de gusano.");

        assertEquals("Interstellar", pelicula.getTitulo());
        assertEquals(2014, pelicula.getAnio());
        assertEquals("Aventura", pelicula.getGenero());
        assertEquals("Un grupo de exploradores viaja a través de un agujero de gusano.", pelicula.getSinopsis());
    }

    @Test
    void constructorVacio_creaInstanciaSinDatos() {
        Pelicula vacia = new Pelicula();
        assertNull(vacia.getId());
        assertNull(vacia.getTitulo());
        assertNull(vacia.getAnio());
    }
}
