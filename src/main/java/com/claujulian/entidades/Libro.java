package com.claujulian.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int isbn;

    @Column(name = "titulo", length = 255)
    private String titulo;

    private int anio;

    private int ejemplares;

    private boolean alta;
    
    @ManyToOne 
    @JoinColumn(name = "id_autor")
    private Autor autor;

    @ManyToOne 
    @JoinColumn(name = "id_editorial") 
    private Editorial editorial;

    //constructores
    public Libro() {
        this.alta = true;
    }
    
    public Libro(String titulo, int anio, int ejemplares, Autor autor, Editorial editorial) {
        this.titulo = titulo;
        this.anio = anio;
        this.ejemplares = ejemplares;
        this.alta = true;
        this.autor = autor;
        this.editorial = editorial;
    }


    //getters y setters
    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(int ejemplares) {
        this.ejemplares = ejemplares;
    }

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
    return String.format(
        "Libro:\n" +
        "  ISBN: %s\n" +
        "  Título: %s\n" +
        "  Año de publicación: %d\n" +
        "  Ejemplares disponibles: %d\n" +
        "  Alta: %b\n" +
        "  Autor: %s\n" +
        "  Editorial: %s\n",
        isbn, titulo, anio, ejemplares, alta, autor, editorial
    );
}
    
}

