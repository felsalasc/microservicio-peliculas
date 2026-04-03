package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PELICULAS")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITULO", nullable = false, length = 150)
    private String titulo;

    @Column(name = "ANIO", nullable = false)
    private Integer anio;

    @Column(name = "DIRECTOR", nullable = false, length = 120)
    private String director;

    @Column(name = "GENERO", nullable = false, length = 80)
    private String genero;

    @Column(name = "SINOPSIS", length = 500)
    private String sinopsis;

    public Pelicula() {
    }

    public Pelicula(Long id, String titulo, Integer anio, String director, String genero, String sinopsis) {
        this.id = id;
        this.titulo = titulo;
        this.anio = anio;
        this.director = director;
        this.genero = genero;
        this.sinopsis = sinopsis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
}