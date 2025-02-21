package com.claujulian.servicios;

import java.util.List;

import com.claujulian.entidades.Autor;
import com.claujulian.persistencia.AutorDAO;

public class AutorServicio {

    private final AutorDAO autorDAO;

    public AutorServicio() {
        this.autorDAO = new AutorDAO();
    }

    public void crearAutor(String nombre) {

        try {
            Autor autorNuevo = new Autor();

            autorNuevo.setNombre(nombre);
            autorNuevo.setAlta(true);

            Boolean autorExistente = validarAutorExistente(autorNuevo.getNombre());

            if (!autorExistente) {
                autorDAO.guardarAutor(autorNuevo);
                System.out.println(":: El autor ha sido ingresado correctamente.");
            } else {
                System.out.println(":: El autor ya existe en la DB.");
            }

        } catch (Exception e) {
            System.out.println(e.toString() + ":: No se guardo el nuevo autor de manera correcta");
        }
    }

    public Autor buscarAutor(int id) {
        Autor autor = autorDAO.buscarAutor(id);
        try {
            if (autor == null) {
                System.out.println("No existe un autor con el ID proporcionado: " + id);
            }

            return autor;

        } catch (Exception e) {
            System.out.println("Ocurrió un error al buscar el autor: " + e.getMessage());
        }
        return null;
    }

    public void buscarAutorPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(":: El nombre del autor es obligatorio.");
        }
        try {
            Autor autor = autorDAO.buscarAutor(nombre);
            System.out.println("ID: " + autor.getId() + ", Nombre: " + autor.getNombre() + ", Alta: " + autor.getAlta());
        } catch (Exception e) {
            System.out.println(":: No se ha encontrado un autor con el nombre " + nombre);
        }
    }

    public void darBajaAutor(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID del autor es obligatorio.");
        }
        try {
            autorDAO.darBajaAUTOR(id);
            System.out.println("Autor dado de baja exitosamente.");
        } catch (Exception e) {
            System.out.println("No se pudo dar de baja el autor: " + e.getMessage());
        }
    }

    public void reactivarAutor(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID del autor es obligatorio.");
        }
        try {
            autorDAO.reactivarAutor(id);
            System.out.println("Autor reactivado exitosamente.");
        } catch (Exception e) {
            System.out.println("No se pudo reactivar el autor: " + e.getMessage());
        }
    }

    public void imprimirLista(List<Autor> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay autores para mostrar.");
        } else {
            for (Autor autor : lista) {
                System.out.println("ID: " + autor.getId() + ", Nombre: " + autor.getNombre() + ", Alta: " + autor.getAlta());
            }
        }
    }

    public void listarAutores() {
        try {
            List<Autor> autores = autorDAO.listarTodos();
            imprimirLista(autores);
        } catch (Exception e) {
            System.out.println("No se pudo listar autores: " + e.getMessage());
        }

    }

    public Boolean validarAutorExistente(String nombre) {
        Boolean autorExistente = true;

        Autor autor = autorDAO.buscarAutorPorNombreCompleto(nombre);

        if (autor == null) {
            autorExistente = false;
        }
        return autorExistente;
    }

    public Boolean validarAutorExistentePorId(int id) {
        Boolean autorExistente = true;

        Autor autor = autorDAO.buscarAutor(id);

        if (autor == null) {
            autorExistente = false;
        }
        return autorExistente;
    }

    public void actualizarAutor(int id, String nombre) {
        try {
            if (validarAutorExistentePorId(id)) {
                Autor autor = autorDAO.buscarAutor(id);

                autor.setNombre(nombre);

                autorDAO.actualizarAutor(autor);

            } else {

                System.out.println(":: No existe un autor con el id suministrado");
            }
        } catch (Exception e) {
            System.out.println(":: No se pudo realizar la actualización solicitada");
        }

    }
}
