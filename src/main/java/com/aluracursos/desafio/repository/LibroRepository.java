package com.aluracursos.desafio.repository;

import com.aluracursos.desafio.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    
    Optional<Libro> findByTituloContainsIgnoreCase(String titulo);
    
    List<Libro> findByIdioma(String idioma);
    
    @Query("SELECT l FROM Libro l JOIN l.autor a WHERE a.nombre ILIKE %:nombre%")
    List<Libro> findByAutorNombreContainsIgnoreCase(@Param("nombre") String nombre);
}
