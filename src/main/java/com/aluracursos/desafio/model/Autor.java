package com.aluracursos.desafio.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String nombre;
    
    private Integer fechaDeNacimiento;
    
    private Integer fechaDeFallecimiento;
    
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Libro> libros;
    
    // Constructor por defecto
    public Autor() {}
    
    // Constructor con parámetros
    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        try {
            this.fechaDeNacimiento = datosAutor.fechaDeNacimiento() != null ? 
                Integer.valueOf(datosAutor.fechaDeNacimiento()) : null;
        } catch (NumberFormatException e) {
            this.fechaDeNacimiento = null;
        }
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }
    
    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }
    
    public Integer getFechaDeFallecimiento() {
        return fechaDeFallecimiento;
    }
    
    public void setFechaDeFallecimiento(Integer fechaDeFallecimiento) {
        this.fechaDeFallecimiento = fechaDeFallecimiento;
    }
    
    public List<Libro> getLibros() {
        return libros;
    }
    
    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
    
    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaDeNacimiento=" + fechaDeNacimiento +
                ", fechaDeFallecimiento=" + fechaDeFallecimiento +
                '}';
    }
}
