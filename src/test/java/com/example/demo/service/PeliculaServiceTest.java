package com.example.demo.service;

import com.example.demo.model.Pelicula;
import com.example.demo.repository.PeliculaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PeliculaServiceTest {

    @Mock
    private PeliculaRepository peliculaRepository;

    @InjectMocks
    private PeliculaService peliculaService;

    private Pelicula peliculaEjemplo;

    @BeforeEach
    void setUp() {
        peliculaEjemplo = new Pelicula(1L, "Inception", 2010, "Christopher Nolan",
                "Ciencia Ficción", "Un ladrón que roba secretos a través de sueños.");
    }

    @Test
    void obtenerTodas_retornaListaDePeliculas() {
        when(peliculaRepository.findAll()).thenReturn(List.of(peliculaEjemplo));

        List<Pelicula> resultado = peliculaService.obtenerTodas();

        assertEquals(1, resultado.size());
        assertEquals("Inception", resultado.get(0).getTitulo());
        verify(peliculaRepository).findAll();
    }

    @Test
    void obtenerPorId_cuandoExiste_retornaOptionalConPelicula() {
        when(peliculaRepository.findById(1L)).thenReturn(Optional.of(peliculaEjemplo));

        Optional<Pelicula> resultado = peliculaService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Inception", resultado.get().getTitulo());
        verify(peliculaRepository).findById(1L);
    }

    @Test
    void obtenerPorId_cuandoNoExiste_retornaOptionalVacio() {
        when(peliculaRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Pelicula> resultado = peliculaService.obtenerPorId(99L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void guardar_persisteYRetornaPelicula() {
        when(peliculaRepository.save(peliculaEjemplo)).thenReturn(peliculaEjemplo);

        Pelicula resultado = peliculaService.guardar(peliculaEjemplo);

        assertEquals("Inception", resultado.getTitulo());
        verify(peliculaRepository).save(peliculaEjemplo);
    }

    @Test
    void actualizar_cuandoExiste_actualizaCamposYGuarda() {
        Pelicula datos = new Pelicula(null, "Interstellar", 2014, "Christopher Nolan",
                "Aventura", "Un grupo de exploradores viaja entre galaxias.");
        when(peliculaRepository.findById(1L)).thenReturn(Optional.of(peliculaEjemplo));
        when(peliculaRepository.save(any(Pelicula.class))).thenReturn(peliculaEjemplo);

        Pelicula resultado = peliculaService.actualizar(1L, datos);

        assertNotNull(resultado);
        verify(peliculaRepository).findById(1L);
        verify(peliculaRepository).save(any(Pelicula.class));
    }

    @Test
    void actualizar_cuandoNoExiste_lanzaRuntimeException() {
        when(peliculaRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> peliculaService.actualizar(99L, peliculaEjemplo));

        assertTrue(ex.getMessage().contains("99"));
    }
}
