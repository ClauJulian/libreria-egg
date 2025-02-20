package com.claujulian;

import java.util.Scanner;

import com.claujulian.persistencia.AutorDAO;
import com.claujulian.persistencia.EditorialDAO;
import com.claujulian.persistencia.LibroDAO;
import com.claujulian.servicios.AutorServicio;
import com.claujulian.servicios.EditorialServicio;
import com.claujulian.servicios.LibroServicio;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        LibroDAO libroDAO = new LibroDAO();
        AutorDAO autorDAO = new AutorDAO();
        EditorialDAO editorialDAO = new EditorialDAO();

        AutorServicio autorServicio = new AutorServicio();
        EditorialServicio editorialServicio = new EditorialServicio();
        LibroServicio libroServicio = new LibroServicio(libroDAO, autorDAO, editorialDAO);

        String menu = """
            Elije una opcion: 
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

            0. Salir
            """;

        int opcion = 1;

        while (opcion != 0) {
            System.out.println(menu);
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nombre del Autor : ");
                    String autor = scanner.nextLine();
                    autorServicio.buscarAutorPorNombre(autor);
                    break;
                case 2:
                    System.out.println("Ingrese el ISBN del libro que desea buscar: ");
                    int isbn = scanner.nextInt();
                    System.out.println((libroServicio.buscarLibroPorIsbn(isbn)).toString());
                    break;
                case 3:
                    System.out.println("Ingrese el titulo del libro : ");
                    String titulo = scanner.nextLine();
                    System.out.println((libroServicio.buscarLibroPorTitulo(titulo).toString()));
                    break;
                case 4:
                    System.out.println("Ingrese el nombre del Autor : ");
                    String nombreAutor = scanner.nextLine();
                    System.out.println();
                    libroServicio.buscarLibroPorAutor(nombreAutor);
                    break;
                case 5:
                    System.out.println("Ingrese el nombre de la Editorial a buscar : ");
                    String nombreEditorial = scanner.nextLine();
                    libroServicio.buscarLibroPorEditorial(nombreEditorial);
                    break;

                case 6:
                    
                    System.out.println("Ingrese el Titulo del libro : ");
                    String titulo1 = scanner.nextLine();

                    System.out.println("Ingrese el Año del libro : ");
                    int anio = scanner.nextInt();

                    System.out.println("Ingrese cantidad de ejemplares del libro : ");
                    int ejemplares = scanner.nextInt();

                    System.out.println("Ingrese el id del Autor del libro : ");
                    int id_autor = scanner.nextInt();

                    System.out.println("Ingrese el id de la Editorial del libro : ");
                    int id_editorial = scanner.nextInt();

                    libroServicio.crearLibro(titulo1, anio, ejemplares, id_autor, id_editorial);
                    
                    break;
                case 7:
                        System.out.println("Ingrese el nombre del autor : ");
                        String autorNombre = scanner.nextLine();
                        autorServicio.crearAutor(autorNombre);
                   
                    break;
                case 8:
                        System.out.println("Ingrese el nombre de la editorial : ");
                        String editorialNombre = scanner.nextLine();
                        editorialServicio.crearEditorial(editorialNombre);
                    break;
                case 9:
                        System.out.println("Ingrese el id del autor a reactivar: ");
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
                default:
                    System.out.println("Gracias por tu visita");
                    break;

            }

        }

    }
}
