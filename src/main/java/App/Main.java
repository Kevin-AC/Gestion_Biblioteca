package App;

import Modelo.Biblioteca;
import Modelo.Libro;
import Modelo.LibroElectronico;
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
        System.out.println("9. salir");
    }
    public static void datosDeprueba(){
        biblioteca.agregarLibro(new Libro("Fudamentos de java","Herbert Schildt","9788426723987","Marcombo","2011","Educativo"));
        biblioteca.agregarLibro(new Libro("Alas de sangre","Rebecca Yarros","97860739909876","Planeta","2023","Fantasia"));
        biblioteca.agregarLibro(new LibroElectronico("Alas de hierro","Rebecca Yarros","987654321","Planeta","2024","Fantasia","Epub",4.5,"http:bibliotecacentral/libroselectronicos/descarga"));
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
            case 9:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opcion no valida");
        }

    }
}
