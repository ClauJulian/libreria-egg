package com.claujulian.utils;

import java.util.Scanner;

import com.claujulian.persistencia.AutorDAO;
import com.claujulian.persistencia.EditorialDAO;
import com.claujulian.persistencia.LibroDAO;
import com.claujulian.servicios.AutorServicio;
import com.claujulian.servicios.EditorialServicio;
import com.claujulian.servicios.LibroServicio;

public class Menu {

    private Scanner scanner;
    private LibroServicio libroServicio;
    private AutorServicio autorServicio;
    private EditorialServicio editorialServicio;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.libroServicio = new LibroServicio(new LibroDAO(), new AutorDAO(), new EditorialDAO());
        this.autorServicio = new AutorServicio();
        this.editorialServicio = new EditorialServicio();
    }

    public void mostrarMenu() {
        String menu = """
            
            Elije una opción: 

            1. Buscar un autor por nombre.
            2. Buscar un libro por ISBN.
            3. Buscar un libro por Título. 
            4. Buscar un libro/s por nombre de autor.
            5. Buscar un libro/s por nombre de Editorial.
            6. Ingresar un libro.
            7. Ingresar un autor.
            8. Ingresar una Editorial.
            9. Activar Autor.
            10. Listar todos los Autores registrados.
            11. Listar todas las Editoriales registradas.
            12. Listar todos los Libros registrados.
            13. Actualizar Libro.
            14. Actualizar Autor.
            15. Actualizar Editorial.

            0. Salir

            """;

        int opcion = 1;
        while (opcion != 0) {
            System.out.println(menu);
            opcion = scanner.nextInt();
            scanner.nextLine();
            ejecutarOpcion(opcion);
        }
    }

    private void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                System.out.println("\n -> Ingrese el nombre del Autor : ");
                String autor = scanner.nextLine();
                autorServicio.buscarAutorPorNombre(autor);
                break;
            case 2:
                System.out.println("\n -> Ingrese el ISBN del libro que desea buscar: ");
                int isbn = scanner.nextInt();
                System.out.println(libroServicio.buscarLibroPorIsbn(isbn).toString());
                break;
            case 3:
                System.out.println("\n -> Ingrese el titulo del libro : ");
                String titulo = scanner.nextLine();
                System.out.println(libroServicio.buscarLibroPorTitulo(titulo).toString());
                break;
            case 4:
                System.out.println("\n -> Ingrese el nombre del Autor : ");
                String nombreAutor = scanner.nextLine();
                libroServicio.buscarLibroPorAutor(nombreAutor);
                break;
            case 5:
                System.out.println("\n -> Ingrese el nombre de la Editorial a buscar : ");
                String nombreEditorial = scanner.nextLine();
                libroServicio.buscarLibroPorEditorial(nombreEditorial);
                break;
            case 6:
                ingresarLibro();
                break;
            case 7:
                System.out.println("\n -> Ingrese el nombre del autor : ");
                String autorNombre = scanner.nextLine();
                autorServicio.crearAutor(autorNombre);
                break;
            case 8:
                System.out.println("\n -> Ingrese el nombre de la editorial : ");
                String editorialNombre = scanner.nextLine();
                editorialServicio.crearEditorial(editorialNombre);
                break;
            case 9:
                System.out.println("\n -> Ingrese el id del autor a reactivar: ");
                int id = scanner.nextInt();
                autorServicio.reactivarAutor(id);
                break;
            case 10:
                autorServicio.listarAutores();
                break;
            case 11:
                editorialServicio.listarEditoriales();
                break;
            case 12:
                libroServicio.listarLibros();
                break;
            case 13:
                System.out.println("\n -> Ingrese el ISBN del libro a modificar: ");
                int isbnAModificar = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Datos actuales del libro a modificar: "
                        + libroServicio.buscarLibroPorIsbn(isbnAModificar).toString());

                modificarLibro(isbnAModificar);

                break;
            case 14:
                System.out.println("\n -> Ingrese el id del autor a modificar: ");
                int idAutorAModificar = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Datos actuales del autor a modificar: \n"
                        + autorServicio.buscarAutor(idAutorAModificar).toString());
                modificarAutor(idAutorAModificar);
                break;
            case 15:
                System.out.println("\n -> Ingrese el id de editorial a modificar: ");
                int idEditorialAModificar = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Datos actuales de la Editorial a modificar: \n"
                        + editorialServicio.buscarEditorial(idEditorialAModificar).toString());
                modificarEditorial(idEditorialAModificar);
                break;
            default:
                System.out.println(":: Gracias por tu visita!");
                break;
        }
    }

    private void ingresarLibro() {
        System.out.println("\n -> Ingrese el Titulo del libro : ");
        String titulo = scanner.nextLine();

        System.out.println("\n -> Ingrese el Año del libro : ");
        int anio = scanner.nextInt();

        System.out.println("\n -> Ingrese cantidad de ejemplares del libro : ");
        int ejemplares = scanner.nextInt();

        System.out.println("\n -> Ingrese el id del Autor del libro : ");
        int id_autor = scanner.nextInt();

        System.out.println("\n -> Ingrese el id de la Editorial del libro : ");
        int id_editorial = scanner.nextInt();

        libroServicio.crearLibro(titulo, anio, ejemplares, id_autor, id_editorial);
    }

    private void modificarLibro(int isbn) {
        System.out.println("\n -> Ingrese el nuevo Titulo : ");
        String titulo = scanner.nextLine();

        System.out.println("\n -> Ingrese el nuevo Año : ");
        int anio = scanner.nextInt();

        System.out.println("\n -> Ingrese la nueva cantidad de ejemplares : ");
        int ejemplares = scanner.nextInt();

        System.out.println("\n -> Ingrese el nuevo id del Autor : ");
        int id_autor = scanner.nextInt();

        System.out.println("\n -> Ingrese el nuevo id de la Editorial : ");
        int id_editorial = scanner.nextInt();

        libroServicio.actualizarLibro(isbn, titulo, anio, ejemplares, id_autor, id_editorial);

    }

    private void modificarAutor(int id) {
        System.out.println("\n -> Ingrese el nombre del Autor : ");
        String nombre = scanner.nextLine();
        autorServicio.actualizarAutor(id, nombre);

    }

    private void modificarEditorial(int id) {
        System.out.println("\n -> Ingrese el nombre de la Editorial : ");
        String nombre = scanner.nextLine();
        editorialServicio.actualizarEditorial(id, nombre);

    }
}
