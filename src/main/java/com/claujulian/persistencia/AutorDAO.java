package com.claujulian.persistencia;

import java.util.List;

import com.claujulian.entidades.Autor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

public class AutorDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Libreria");
    private final EntityManager em = emf.createEntityManager();

    public void guardarAutor(Autor autor) {
        em.getTransaction().begin();
        em.persist(autor);
        em.getTransaction().commit();
    }

    public Autor buscarAutor(int id) {
        return em.find(Autor.class, id);
    }

    public Autor buscarAutor(String nombre) {
        return em.createQuery("SELECT a FROM Autor a WHERE a.nombre LIKE :nombre", Autor.class)
                .setParameter("nombre", "%" + nombre + "%")
                .getResultList()
                .get(0);
    }

    public void darBajaAUTOR(int id) {
        Autor autor = em.find(Autor.class, id);
        if (autor != null) {
            autor.setAlta(false);
            actualizarAutor(autor);
        }
    }

    public void reactivarAutor(Integer id) {
        Autor autor = em.find(Autor.class, id);
        if (autor != null) {
            autor.setAlta(true);
            actualizarAutor(autor);
        }
    }

    public void actualizarAutor(Autor autor) {
        em.getTransaction().begin();
        em.merge(autor);
        em.getTransaction().commit();
    }

    public List<Autor> listarTodos() throws Exception {
        return em.createQuery("SELECT a FROM Autor a", Autor.class).getResultList();
    }

    public Autor buscarAutorPorNombreCompleto(String nombre) {
        try {
            return em.createQuery("SELECT a FROM Autor a WHERE a.nombre LIKE :nombre", Autor.class)
                    .setParameter("nombre", nombre)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
