package Service;

import DAO.LibroDAO;
import Modelo.Biblioteca;
import Modelo.DatosLibro;
import Modelo.Libro;
import Modelo.LibroElectronico;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LibroService {
    private  Scanner scanner;
    private  Biblioteca biblioteca;
    public LibroService(Scanner scanner, Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        this.scanner = scanner;

    }

    public void listarLibrosPorGenero() {
        System.out.println("Ingrese el genero a buscar");
        String genero = scanner.nextLine();
        List<Libro> librosFiltrados = biblioteca.getCatalogo().stream()
                .filter(libro -> libro.getGenero().equalsIgnoreCase(genero))
                .collect(Collectors.toList());

        if(librosFiltrados.isEmpty()) {
            System.out.println("No se encontraron libros para el género: " + genero);
        } else {
            System.out.println("Libros encontrados:");
            librosFiltrados.forEach(libro -> System.out.println(libro));
        }
    }
    public void mostrarLibros(){
        List<Libro> catalogo = biblioteca.getCatalogo();
        if (catalogo.isEmpty()){
            System.out.println("No hay libros disponibles");
        }else {
            System.out.println("\nLista de libros");
            for(int i = 0; i < catalogo.size(); i++){
                System.out.println((i+1)+ "."+catalogo.get(i));
            }
        }

    }
    public void MenuCrearLibro(){

        System.out.println("Seleccione el tipo de libro a crear:");
        System.out.println("1. Libro Fisico");
        System.out.println("2. Libro Electronico");
        int seleccion = 0;
        try {
            LibroDAO dao = new LibroDAO();
            seleccion = scanner.nextInt();
            scanner.nextLine();
            switch (seleccion) {
                case 1:
                    System.out.println("Creando Libro Fisico");
                    DatosLibro datosLibro = datosComunesLibro();
                    Libro libroFisico = new Libro(datosLibro.titulo, datosLibro.autor, datosLibro.isbn, datosLibro.editorial, datosLibro.anioPublicacion, datosLibro.genero);
                    dao.agregarLibro(libroFisico);
                    biblioteca.agregarLibro(libroFisico);
                    System.out.println("Libro creado con exito");
                    break;
                case 2:
                    System.out.println("Creando Libro Electronico");
                    DatosLibro datosLibroE = datosComunesLibro();
                    System.out.println("Ingrese el formato del libro (e.g., PDF, EPUB)");
                    String formato = scanner.nextLine();

                    double tamanoMB;
                    while (true) {
                        System.out.println("Ingrese el tamaño del archivo en MB");
                        try {
                            tamanoMB = Double.parseDouble(scanner.nextLine());
                            if (tamanoMB <= 0) {
                                System.out.println("Error: El tamaño debe ser un número positivo. Intente de nuevo.");
                            } else {
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Debe ingresar un número válido para el tamaño. Intente de nuevo.");
                        }
                    }
                    System.out.println("Ingrese la URL de descarga del libro");
                    String urlDescarga = scanner.nextLine();
                    // Crear objeto LibroElectronico
                    LibroElectronico libroElectronico = new LibroElectronico(
                            datosLibroE.titulo,
                            datosLibroE.autor,
                            datosLibroE.isbn,
                            datosLibroE.editorial,
                            datosLibroE.anioPublicacion,
                            datosLibroE.genero,
                            formato,
                            tamanoMB,
                            urlDescarga
                    );
                    biblioteca.agregarLibro(libroElectronico);
                    System.out.println("Libro Electronico creado con exito");
                    break;
                default:
                    System.out.println("Opcion no valida.");

            }

        } catch (InputMismatchException e) {
            System.out.println("Opcion no valida.");
            scanner.nextLine();
            return;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public DatosLibro datosComunesLibro(){
        System.out.println("Ingrese el Titulo del Libro");
        String titulo = scanner.nextLine();
        System.out.println("Ingrese el Autor del Libro");
        String autor = scanner.nextLine();
        System.out.println("Ingrese isbn");
        String isbn = scanner.nextLine();
        System.out.println("Ingrese Editorial");
        String editorial = scanner.nextLine();
        System.out.println("Ingrese el Año de Publicacion");
        String anioPublicacion = scanner.nextLine();
        System.out.println("Ingrese el Genero");
        String genero = scanner.nextLine();
        return new DatosLibro(titulo,autor,isbn,editorial,anioPublicacion,genero);

    }
}
