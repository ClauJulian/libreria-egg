package com.claujulian.persistencia;

import java.util.List;

import com.claujulian.entidades.Editorial;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

public class EditorialDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Libreria");
    private final EntityManager em = emf.createEntityManager();

    public void guardarEditorial(Editorial editorial) {
        em.getTransaction().begin();
        em.persist(editorial);
        em.getTransaction().commit();
    }

    public Editorial buscarEditorial(Integer id) {
        return em.find(Editorial.class, id);
    }

    public Editorial buscarEditorial(String nombre) {
        return em.createQuery("SELECT e FROM Editorial e WHERE e.nombre LIKE :nombre", Editorial.class)
                .setParameter("nombre", "%" + nombre + "%")
                .getResultList()
                .get(0);
    }

    public void actualizarEditorial(Editorial editorial) {
        em.getTransaction().begin();
        em.merge(editorial);
        em.getTransaction().commit();
    }

    public void eliminarEditorial(Integer id) {
        Editorial editorial = em.find(Editorial.class, id);
        if (editorial != null) {
            em.getTransaction().begin();
            em.remove(editorial);
            em.getTransaction().commit();
        }
    }

    public void darBajaEditorial(int id) {
        Editorial editorial = em.find(Editorial.class, id);
        if (editorial != null) {
            editorial.setAlta(false);
            actualizarEditorial(editorial);
        }
    }

    public List<Editorial> listarTodas() {
        return em.createQuery("SELECT e FROM Editorial e", Editorial.class)
                .getResultList();
    }

    public Editorial buscarEditorialPorNombreCompleto(String nombre) {
        try {
            return em.createQuery("SELECT e FROM Editorial e WHERE e.nombre LIKE :nombre", Editorial.class)
                    .setParameter("nombre", nombre)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
