package com.aluracursos.desafio.service;

import com.aluracursos.desafio.model.*;
import com.aluracursos.desafio.repository.LibroRepository;
import com.aluracursos.desafio.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private ConsumAPI consumAPI;

    @Autowired
    private ConvierteDatos conversor;

    private static final String URL_BASE = "https://gutendex.com/books/";

    public DatosLibros buscarLibroEnAPI(String titulo) {
        var json = consumAPI.obtenerDatos(URL_BASE + "?search=" + titulo.replace(" ", "+"));
        
        if (json == null || json.isEmpty()) {
            System.out.println("❌ No se pudo conectar con la API de Gutendex.");
            System.out.println("💡 Usando datos de ejemplo para continuar con la demostración...");
            return crearLibroEjemplo(titulo);
        }
        
        try {
            var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
            
            Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                    .filter(l -> l.titulo().toUpperCase().contains(titulo.toUpperCase()))
                    .findFirst();
            
            return libroBuscado.orElse(null);
        } catch (Exception e) {
            System.out.println("❌ Error al procesar la respuesta de la API: " + e.getMessage());
            System.out.println("💡 Usando datos de ejemplo para continuar...");
            return crearLibroEjemplo(titulo);
        }
    }
    
    private DatosLibros crearLibroEjemplo(String titulo) {
        // Crear datos de ejemplo basados en el título buscado
        List<DatosAutor> autores = List.of(new DatosAutor("Autor Ejemplo", "1800"));
        List<String> idiomas = List.of("en");
        
        String tituloEjemplo = titulo.toLowerCase().contains("pride") ? "Pride and Prejudice" :
                              titulo.toLowerCase().contains("romeo") ? "Romeo and Juliet" :
                              titulo.toLowerCase().contains("alice") ? "Alice's Adventures in Wonderland" :
                              titulo.toLowerCase().contains("frankenstein") ? "Frankenstein" :
                              "Libro Ejemplo: " + titulo;
        
        return new DatosLibros(tituloEjemplo, autores, idiomas, 1000.0);
    }

    public Libro guardarLibro(DatosLibros datosLibros) {
        // Verificar si el libro ya existe
        Optional<Libro> libroExistente = libroRepository.findByTituloContainsIgnoreCase(datosLibros.titulo());
        if (libroExistente.isPresent()) {
            return libroExistente.get();
        }

        // Crear el libro
        Libro libro = new Libro(datosLibros);

        // Procesar el autor
        if (datosLibros.autor() != null && !datosLibros.autor().isEmpty()) {
            DatosAutor datosAutor = datosLibros.autor().get(0); // Tomamos el primer autor

            // Buscar si el autor ya existe
            Optional<Autor> autorExistente = autorRepository.findByNombreContainsIgnoreCase(datosAutor.nombre());

            Autor autor;
            if (autorExistente.isPresent()) {
                autor = autorExistente.get();
            } else {
                // Crear nuevo autor
                autor = new Autor(datosAutor);
                autor = autorRepository.save(autor);
            }

            libro.setAutor(autor);
        }

        return libroRepository.save(libro);
    }

    public List<Libro> listarTodosLosLibros() {
        return libroRepository.findAll();
    }

    public List<Autor> listarTodosLosAutores() {
        return autorRepository.findAll();
    }

    public List<Autor> listarAutoresVivosEnAnio(Integer anio) {
        return autorRepository.findAutoresVivosEnAnio(anio);
    }

    public List<Libro> listarLibrosPorIdioma(String idioma) {
        return libroRepository.findByIdioma(idioma);
    }
}