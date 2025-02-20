package com.claujulian.persistencia;

import java.util.List;

import com.claujulian.entidades.Libro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

public class LibroDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Libreria");
    private final EntityManager em = emf.createEntityManager();

    public void guardarLibro(Libro libro) {
        em.getTransaction().begin();
        em.persist(libro);
        em.getTransaction().commit();
    }

    public Libro buscarLibro(int isbn) {
        return em.find(Libro.class, isbn);
    }

    public Libro buscarLibro(String titulo) {
        try {
            return em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :titulo", Libro.class)
                    .setParameter("titulo", "%" + titulo + "%")
                    .getResultList()
                    .get(0);
        } catch (NoResultException e) {
            System.out.println("No se encontró un libro con el título: " + titulo);
            return null; // O podrías lanzar una excepción personalizada
        }
    }

    public Libro buscarLibroPorTituloCompleto(String titulo) {
        try {
            return em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :titulo", Libro.class)
                    .setParameter("titulo",  titulo )
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // O podrías lanzar una excepción personalizada
        }
    }

    public List<Libro> buscarLibroPorAutor(String autor) {
        try {
            return em.createQuery("SELECT l FROM Libro l WHERE l.autor.nombre LIKE :titulo", Libro.class)
                    .setParameter("titulo", "%" + autor + "%")
                    .getResultList();
        } catch (Exception e) {
            System.out.println("No se encontraron libros con el autor: " + autor);
            return null; // O podrías lanzar una excepción personalizada
        }
    }

    public List<Libro> buscarLibroPorEditorial(String editorial) {
        return em.createQuery("SELECT l FROM Libro l WHERE l.editorial.nombre LIKE :editorial", Libro.class)
                .setParameter("editorial", "%" + editorial + "%")
                .getResultList();
    }

    public void actualizarLibro(Libro libro) {
        em.getTransaction().begin();
        em.merge(libro);
        em.getTransaction().commit();
    }

    public void eliminarLibro(int isbn) {
        Libro libro = em.find(Libro.class, isbn);
        if (libro != null) {
            em.getTransaction().begin();
            em.remove(libro);
            em.getTransaction().commit();
        }
    }

    public void darBajaLibro(int isbn) {
        Libro libro = em.find(Libro.class, isbn);
        if (libro != null) {
            libro.setAlta(false);
            actualizarLibro(libro);
        }
    }

    public List<Libro> listarTodos() throws Exception {
        return em.createQuery("SELECT l FROM Libro l", Libro.class).getResultList();
    }
}
