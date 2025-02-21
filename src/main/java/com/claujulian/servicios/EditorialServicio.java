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


    public void actualizarEditorial(int id, String nombre) {
        try {
            if (validarEditorialExistentePorId(id)) {
                Editorial editorial = editorialDAO.buscarEditorial(id);

                editorial.setNombre(nombre);

                editorialDAO.actualizarEditorial(editorial);

            } else {

                System.out.println(":: No existe editorial con el id suministrado");
            }
        } catch (Exception e) {
            System.out.println(":: No se pudo realizar la actualización solicitada");
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

    

    public Editorial buscarEditorial(int idEditorialAModificar) {
        try {
            Editorial editorial = editorialDAO.buscarEditorial(idEditorialAModificar);
            if (editorial != null) {
                return editorial;
            } else {
                System.out.println("No se encontró la editorial con ID: " + idEditorialAModificar);
            }
        } catch (Exception e) {
            System.out.println("Error al buscar la editorial: " + e.getMessage());
        }
        return null;
    }

    
    public Boolean validarEditorialExistentePorId(int id) {
        Boolean editorialExistente = true;

        Editorial editorial = editorialDAO.buscarEditorial(id);

        if (editorial == null) {
            editorialExistente = false;
        }
        return editorialExistente;
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
