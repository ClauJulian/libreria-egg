package com.claujulian.servicios;

import java.util.List;

import com.claujulian.entidades.Editorial;
import com.claujulian.persistencia.EditorialDAO;

public class EditorialServicio {

    private final EditorialDAO editorialDAO;

    public EditorialServicio() {
        this.editorialDAO = new EditorialDAO();
    }

    public void crearEditorial(String nombre) {
        try {
            Editorial editorial = new Editorial();
            editorial.setNombre(nombre);
            editorial.setAlta(true);

            Boolean editorialExistente = validarEditorialExistente(editorial.getNombre());

            if (!editorialExistente) {
                editorialDAO.guardarEditorial(editorial);
                System.out.println(":: La editorial fue ingresada correctamente.");
            } else {
                System.out.println(":: La editorial " + editorial.getNombre() + " ya existen en DB");
            }

        } catch (Exception e) {
            System.out.println("Error al crear la editorial: " + e.getMessage());
        }
    }

    public void actualizarEditorial(Editorial editorial) {
        try {
            editorialDAO.actualizarEditorial(editorial);
        } catch (Exception e) {
            System.out.println("Error al actualizar la editorial: " + e.getMessage());
        }
    }

    public void buscarEditorialPorNombre(String nombre) {
        try {
            Editorial editorial = editorialDAO.buscarEditorial(nombre);
            if (editorial != null) {
                System.out.println("Editorial encontrada: " + editorial.getNombre());
            } else {
                System.out.println("No se encontró la editorial con ID: " + nombre);
            }
        } catch (Exception e) {
            System.out.println("Error al buscar la editorial: " + e.getMessage());
        }
    }

    public void eliminarEditorial(Integer id) {
        try {
            editorialDAO.eliminarEditorial(id);
            System.out.println("Editorial eliminada correctamente.");
        } catch (Exception e) {
            System.out.println("Error al eliminar la editorial: " + e.getMessage());
        }
    }

    public void listarEditoriales() {
        try {
            List<Editorial> editoriales = editorialDAO.listarTodas();
            imprimirLista(editoriales);
        } catch (Exception e) {
            System.out.println("Error al listar editoriales: " + e.getMessage());
        }
    }

    private void imprimirLista(List<Editorial> editoriales) {
        for (Editorial editorial : editoriales) {
            System.out.println("ID: " + editorial.getId() + ", Nombre: " + editorial.getNombre());
        }
    }

    public void darBajaEditorial(Integer id) {
        try {
            editorialDAO.darBajaEditorial(id);
            System.out.println("Editorial con ID " + id + " ha sido dada de baja correctamente.");
        } catch (Exception e) {
            System.out.println("Error al dar de baja la editorial con ID " + id + ": " + e.getMessage());
        }
    }

    public Boolean validarEditorialExistente(String nombre) {
        Boolean editorialExistente = true;

        Editorial editorial = editorialDAO.buscarEditorialPorNombreCompleto(nombre);

        if (editorial == null) {
            editorialExistente = false;
        }
        return editorialExistente;
    }
}
