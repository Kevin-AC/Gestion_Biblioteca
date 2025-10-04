package App;

import Modelo.Biblioteca;
import Service.BibliotecaService;

import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Biblioteca biblioteca = Biblioteca.getInstance("Biblioteca Central");
    static BibliotecaService service = new BibliotecaService(biblioteca,scanner);
    public static void main(String[] args) {
        int opcion =0;
        while (opcion != 9){
            mostarMenu();
            try {
                opcion= scanner.nextInt();
                scanner.nextLine();
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
                        System.out.println("Lista libros");
                        break;
                    case 5:
                        System.out.println("Devolver libro");
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

            }catch (InputMismatchException e){
                System.out.println("Error: Caracter no valido");
                scanner.nextLine();

            }
        }

    }
    public static void mostarMenu(){
        System.out.println("Bienvenido a la biblioteca "+ biblioteca.getNombre());
        System.out.println("1. Crear usuario");
        System.out.println("2. Crear libro");
        System.out.println("3. Prestar libro");
        System.out.println("4. Lista de libros");
        System.out.println("5. Devolver libro");
        System.out.println("6. Lista de prestamos");
        System.out.println("9. salir");
    }
}
