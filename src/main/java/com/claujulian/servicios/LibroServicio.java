package com.claujulian.servicios;

import java.util.List;

import com.claujulian.entidades.Autor;
import com.claujulian.entidades.Editorial;
import com.claujulian.entidades.Libro;
import com.claujulian.persistencia.AutorDAO;
import com.claujulian.persistencia.EditorialDAO;
import com.claujulian.persistencia.LibroDAO;

public class LibroServicio {

    LibroDAO libroDAO;
    AutorDAO autorDAO;
    EditorialDAO editorialDAO;

    /*  public LibroServicio() {
        this.libroDAO = new LibroDAO();
       
    }*/
    public LibroServicio(LibroDAO libroDAO, AutorDAO autorDAO, EditorialDAO editorialDAO) {
        this.libroDAO = new LibroDAO();
        this.autorDAO = new AutorDAO();
        this.editorialDAO = new EditorialDAO();
    }

    //creacion
    public void crearLibro(String titulo, int anio, int ejemplares, int idAutor, int idEditorial) {

        try {

            Autor autor = autorDAO.buscarAutor(idAutor);
            Editorial editorial = editorialDAO.buscarEditorial(idEditorial);

            Libro libroNuevo = new Libro();

            libroNuevo.setTitulo(titulo);
            libroNuevo.setAnio(anio);
            libroNuevo.setEjemplares(ejemplares);
            libroNuevo.setAutor(autor);
            libroNuevo.setEditorial(editorial);
            libroNuevo.setAlta(true);

            boolean tituloExistente = validarTituloExistente(titulo);

            if (!tituloExistente) {
                libroDAO.guardarLibro(libroNuevo);
                System.out.println(":: El Libro ha sido ingresado correctamente");
            } else {
                System.out.println(":: El libro " + libroNuevo.getTitulo() + " ya se encuentra ingresado.");
            }

        } catch (Exception e) {

            System.out.println(e.toString() + ":: No se pudo guardar el nuevo Libro correctamente");
        }
    }

    public void actualizarLibro(int isbn, String titulo, int anio, int ejemplares, int idAutor, int idEditorial) {
        try {
            boolean libroExistente = validarLibroExistentePorIsbn(isbn);

            if (libroExistente) {
                Autor autor = autorDAO.buscarAutor(idAutor);
                Editorial editorial = editorialDAO.buscarEditorial(idEditorial);

                Libro libro = libroDAO.buscarLibro(isbn);

                libro.setTitulo(titulo);
                libro.setAnio(anio);
                libro.setEjemplares(ejemplares);
                libro.setAutor(autor);
                libro.setEditorial(editorial);
                libro.setAlta(true);

                libroDAO.actualizarLibro(libro);

            } else {
                crearLibro(titulo, anio, ejemplares, idAutor, idEditorial);
            }

        } catch (Exception e) {
            System.out.println(e.toString() + ":: No se pudo actualizar el Libro correctamente");
        }
    }

    //consultas
    public Libro buscarLibroPorIsbn(int isbn) {
        Libro libro = libroDAO.buscarLibro(isbn);
        try {
            if (libro == null) {
                System.out.println("No existe un libro con el isbn proporcionado: " + isbn);
            }
            return libro;
        } catch (Exception e) {
            System.out.println("Se produjo un error al buscar el libro: " + e.getMessage());
        }
        return null;
    }

    public Libro buscarLibroPorTitulo(String titulo) {
        Libro libro = libroDAO.buscarLibro(titulo);

        try {
            if (libro == null) {
                System.out.println("No existe un libro con el titulo proporcionado: " + titulo);
            }
            return libro;
        } catch (Exception e) {
            System.out.println("Se produjo un error al buscar el libro: " + e.getMessage());
        }
        return null;
    }

    public void buscarLibroPorAutor(String autor) {
        List<Libro> libros = libroDAO.buscarLibroPorAutor(autor);
        try {
            if (libros.isEmpty()) {
                System.out.println(":: No existen libros con el autor proporcionado: " + autor);
            }
            imprimirLista(libros);
        } catch (Exception e) {
            System.out.println(":: Se produjo un error al buscar el libro: " + e.getMessage());
        }
    }

    public void buscarLibroPorEditorial(String editorial) {
        List<Libro> libros = libroDAO.buscarLibroPorEditorial(editorial);
        try {
            if (libros.isEmpty()) {
                System.out.println(":: No existen libros con la editorial proporcionada: " + editorial);
            }
            imprimirLista(libros);
        } catch (Exception e) {
            System.out.println(":: Se produjo un error al buscar el libro: " + e.getMessage());
        }
    }

    public void modificarLibro(Libro libro) {
        try {
            libroDAO.actualizarLibro(libro);
        } catch (Exception e) {
            System.out.println("Se produjo un error al modificar el libro:" + e.getMessage());
        }
    }

    public void darBajaLibro(int isbn) {
        try {
            libroDAO.darBajaLibro(isbn);
        } catch (Exception e) {
            System.out.println("Se produjo un error al dar de baja el libro: " + e.getMessage());
        }
    }

    public void listarLibros() {
        try {
            List<Libro> libros = libroDAO.listarTodos();
            imprimirLista(libros);
        } catch (Exception e) {
            System.out.println(":: No se pudo ejecutar el pedido" + e.getMessage());
        }
    }

    public void imprimirLista(List<Libro> listaRecibida) throws Exception {

        for (Libro unLibro : listaRecibida) {
            System.out.println(unLibro.toString());
        }

    }

    public Boolean validarTituloExistente(String titulo) {
        Boolean tituloExistente = true;

        Libro libro = libroDAO.buscarLibroPorTituloCompleto(titulo);

        if (libro == null) {
            tituloExistente = false;
        }
        return tituloExistente;
    }

    public Boolean validarLibroExistentePorIsbn(int isbn) {
        Boolean libroExistente = true;

        Libro libro = libroDAO.buscarLibro(isbn);

        if (libro == null) {
            libroExistente = false;
        }
        return libroExistente;
    }

}
