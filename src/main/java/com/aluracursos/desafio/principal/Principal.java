package com.aluracursos.desafio.principal;

import com.aluracursos.desafio.model.*;
import com.aluracursos.desafio.service.ConsumAPI;
import com.aluracursos.desafio.service.ConvierteDatos;
import com.aluracursos.desafio.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

import com.aluracursos.desafio.model.*;


@Component
public class Principal {

    @Autowired
    private LibroService libroService;

    private Scanner teclado = new Scanner(System.in);

    public void muestraElMenu() {
            var opcion = -1;

            while (opcion != 0) {
                var menu = """
                        ******************************
                        ¡Bienvenido al Catálogo de Libros!
                        
                        Opciones:
                        1 - Buscar libro por título
                        2 - Listar los libros registrados
                        3 - Listar los autores registrados
                        4 - Listar autores vivos en determinado año
                        5 - Listar libros por idioma
                        
                        0 - Salir
                        ******************************
                        Seleccione una opción:
                        """;

                System.out.print(menu);
                opcion = teclado.nextInt();
                teclado.nextLine(); // Limpiar el buffer

                switch (opcion) {
                    case 1:
                        buscarLibroPorTitulo();
                        break;
                    case 2:
                        listarLibrosRegistrados();
                        break;
                    case 3:
                        listarAutoresRegistrados();
                        break;
                    case 4:
                        listarAutoresVivos();
                        break;
                    case 5:
                        listarLibrosPorIdioma();
                        break;
                    case 0:
                        System.out.println("Cerrando la aplicación...");
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor seleccione una opción válida.");
                }
            }
        }

    private void buscarLibroPorTitulo() {
        try {
            System.out.println("Escribe el nombre del libro que deseas buscar:");
            var tituloLibro = teclado.nextLine();

            DatosLibros datosLibros = libroService.buscarLibroEnAPI(tituloLibro);

            if (datosLibros != null) {
                mostrarInformacionLibro(datosLibros);

                // Guardar en la base de datos
                Libro libroGuardado = libroService.guardarLibro(datosLibros);
                System.out.println("📚 Libro guardado en la base de datos con ID: " + libroGuardado.getId());
            } else {
                System.out.println("❌ Libro no encontrado");
            }
        } catch (Exception e) {
            System.err.println("❌ Error inesperado en la búsqueda: " + e.getMessage());
            System.out.println("💡 Intenta nuevamente o selecciona otra opción del menú.");
        }
    }        private void listarLibrosRegistrados() {
            List<Libro> libros = libroService.listarTodosLosLibros();

            if (libros.isEmpty()) {
                System.out.println("📚 No hay libros registrados en la base de datos.");
                return;
            }

            System.out.println("\n===== LIBROS REGISTRADOS =====");
            libros.forEach(libro -> {
                System.out.println("📖 " + libro.getTitulo());
                System.out.println("👤 Autor: " + (libro.getAutor() != null ? libro.getAutor().getNombre() : "Sin autor"));
                System.out.println("🌐 Idioma: " + libro.getIdioma());
                System.out.println("⬇️ Descargas: " + (libro.getNumeroDeDescargas() != null ? libro.getNumeroDeDescargas().intValue() : "N/A"));
                System.out.println("─────────────────────────────");
            });
        }

        private void listarAutoresRegistrados() {
            List<Autor> autores = libroService.listarTodosLosAutores();

            if (autores.isEmpty()) {
                System.out.println("👤 No hay autores registrados en la base de datos.");
                return;
            }

            System.out.println("\n===== AUTORES REGISTRADOS =====");
            autores.forEach(autor -> {
                System.out.println("👤 " + autor.getNombre());
                System.out.println("🎂 Nacimiento: " + (autor.getFechaDeNacimiento() != null ? autor.getFechaDeNacimiento() : "Desconocido"));
                System.out.println("💀 Fallecimiento: " + (autor.getFechaDeFallecimiento() != null ? autor.getFechaDeFallecimiento() : "Vivo o desconocido"));
                System.out.println("─────────────────────────────");
            });
        }

        private void listarAutoresVivos() {
            System.out.println("Ingrese el año para buscar autores vivos:");
            try {
                var anio = teclado.nextInt();
                teclado.nextLine(); // Limpiar buffer

                List<Autor> autoresVivos = libroService.listarAutoresVivosEnAnio(anio);

                if (autoresVivos.isEmpty()) {
                    System.out.println("👤 No se encontraron autores vivos en el año " + anio);
                    return;
                }

                System.out.println("\n===== AUTORES VIVOS EN " + anio + " =====");
                autoresVivos.forEach(autor -> {
                    System.out.println("👤 " + autor.getNombre());
                    System.out.println("🎂 Nacimiento: " + (autor.getFechaDeNacimiento() != null ? autor.getFechaDeNacimiento() : "Desconocido"));
                    System.out.println("💀 Fallecimiento: " + (autor.getFechaDeFallecimiento() != null ? autor.getFechaDeFallecimiento() : "Vivo o desconocido"));
                    System.out.println("─────────────────────────────");
                });
            } catch (Exception e) {
                System.out.println("❌ Por favor ingrese un año válido.");
                teclado.nextLine(); // Limpiar buffer en caso de error
            }
        }

        private void listarLibrosPorIdioma() {
            System.out.println("""
                    Idiomas disponibles:
                    es - Español
                    en - Inglés
                    fr - Francés
                    pt - Portugués
                    
                    Ingrese el código del idioma:
                    """);

            var idioma = teclado.nextLine();
            List<Libro> libros = libroService.listarLibrosPorIdioma(idioma);

            if (libros.isEmpty()) {
                System.out.println("📚 No se encontraron libros en el idioma: " + idioma);
                return;
            }

            System.out.println("\n===== LIBROS EN IDIOMA: " + idioma.toUpperCase() + " =====");
            libros.forEach(libro -> {
                System.out.println("📖 " + libro.getTitulo());
                System.out.println("👤 Autor: " + (libro.getAutor() != null ? libro.getAutor().getNombre() : "Sin autor"));
                System.out.println("⬇️ Descargas: " + (libro.getNumeroDeDescargas() != null ? libro.getNumeroDeDescargas().intValue() : "N/A"));
                System.out.println("─────────────────────────────");
            });
        }

        private void mostrarInformacionLibro(DatosLibros libro) {
            System.out.println("\n************* LIBRO *************");
            System.out.println("Título: " + libro.titulo());

            // Mostrar autores
            if (libro.autor() != null && !libro.autor().isEmpty()) {
                System.out.print("Autor: ");
                for (int i = 0; i < libro.autor().size(); i++) {
                    System.out.print(libro.autor().get(i).nombre());
                    if (i < libro.autor().size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            } else {
                System.out.println("Autor: No disponible");
            }

            // Mostrar idiomas
            if (libro.idiomas() != null && !libro.idiomas().isEmpty()) {
                System.out.print("Idioma: ");
                for (int i = 0; i < libro.idiomas().size(); i++) {
                    System.out.print(libro.idiomas().get(i));
                    if (i < libro.idiomas().size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            } else {
                System.out.println("Idioma: No disponible");
            }

            // Mostrar número de descargas
            System.out.println("Número de descargas: " +
                    (libro.numeroDeDescargas() != null ? libro.numeroDeDescargas().intValue() : "No disponible"));

            System.out.println("**********************************\n");
        }
    }

