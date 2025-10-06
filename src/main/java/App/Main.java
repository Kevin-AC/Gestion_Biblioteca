package App;

import Modelo.Biblioteca;
import Modelo.Libro;
import Modelo.LibroElectronico;
import Modelo.Usuario;
import Service.BibliotecaService;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Biblioteca biblioteca;
    static BibliotecaService service;
    public static void main(String[] args) {
        biblioteca = Biblioteca.getInstance("Biblioteca Central");
        service = new BibliotecaService(biblioteca, scanner);
        datosDeprueba();

        int opcion =0;
        while (opcion != 9){
            mostrarMenu();
            opcion= leerOpcion();
            ejecutarOpciones(opcion);

        }

    }
    public static void mostrarMenu(){
        System.out.println("Bienvenido a la biblioteca "+ biblioteca.getNombre());
        System.out.println("1. Crear usuario");
        System.out.println("2. Crear libro");
        System.out.println("3. Prestar libro");
        System.out.println("4. Lista de libros");
        System.out.println("5. Devolver libro");
        System.out.println("6. Lista de prestamos");
        System.out.println("7. Listar libros por genero");
        System.out.println("9. salir");
    }
    public static void datosDeprueba(){
        biblioteca.agregarLibro(new Libro("Cien años de soledad", "Gabriel García Márquez", "9780060883287", "Sudamericana", "1967", "Realismo magico"));
        biblioteca.agregarLibro(new Libro("El codigo Da Vinci", "Dan Brown", "9780307474278", "Doubleday", "2003", "Thriller"));
        biblioteca.agregarLibro(new Libro("El principito", "Antoine de Saint-Exupéry", "9780156012195", "Reynal & Hitchcock", "1943", "Fantasía"));
        biblioteca.agregarLibro(new Libro("1984", "George Orwell", "9780451524935", "Secker & Warburg", "1949", "Distopia"));
        biblioteca.agregarLibro(new LibroElectronico("Java para principiantes", "John Doe", "123456789", "TechPress", "2020", "Educativo", "PDF", 3.8, "http://bibliotecacentral/libroselectronicos/java"));
        biblioteca.agregarLibro(new Libro("La saga de Geralt de Rivia", "Andrzej Sapkowski", "9788496206250", "Plaza & Janés", "1993", "Fantasia"));
        biblioteca.agregarLibro(new Libro("El nombre del viento", "Patrick Rothfuss", "9788408150837", "Plaza & Janés", "2007", "Fantasia"));
        biblioteca.agregarLibro(new Libro("Orgullo y prejuicio", "Jane Austen", "9788491056464", "Debolsillo", "1813", "Novela"));
        biblioteca.agregarLibro(new Libro("Cumbres Borrascosas", "Emily Brontë", "9788491051802", "Debolsillo", "1847", "Novela"));
        //usuarios de prueba
        biblioteca.agregarUsuario(new Usuario("Ana Martinez", "12345678", "ana@mail.com", "5551234"));
        biblioteca.agregarUsuario(new Usuario("Luis Gomez", "87654321", "luis@mail.com", "5554321"));
        biblioteca.agregarUsuario(new Usuario("María Rodriguez", "23456789", "maria@mail.com", "5556789"));
        biblioteca.agregarUsuario(new Usuario("Carlos Perez", "98765432", "carlos@mail.com", "5559876"));
        biblioteca.agregarUsuario(new Usuario("Lucía Sanchez", "34567890", "lucia@mail.com", "5553456"));


    }
    public static int leerOpcion(){
        int opcion = 0;
        try {
            opcion = scanner.nextInt();
            scanner.nextLine();
        }catch (InputMismatchException e ){
            System.out.println("Error: Caracter no valido");
            scanner.nextLine(); // Limpiar buffer
        }
        return opcion;
    }
    public static void ejecutarOpciones(int opcion){
        switch (opcion){
            case 1:
                service.crearUsuario();
                break;
            case 2:
                service.crearLibro();
                break;
            case 3:
                service.prestarLibro();
                break;
            case 4:
                biblioteca.mostrarLibros();
                break;
            case 5:
                service.devolverLibro();
                break;
            case 6:
                service.listarPrestamos();
                break;
            case  7:
                service.listarLibrosPorGenero();
                break;
            case 9:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opcion no valida");
        }

    }
}
