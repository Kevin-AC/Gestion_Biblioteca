package edu.iudigital;

import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Biblioteca biblioteca = Biblioteca.getInstance("Biblioteca Central");
    public static void main(String[] args) {
        int opcion =0;
        while (opcion != 9){
            mostarMenu();
            try {
                opcion= scanner.nextInt();
                scanner.nextLine();
                switch (opcion){
                    case 1:
                        crearUsuario();
                        break;
                    case 2:
                        crearLibro();
                        break;
                    case 3:
                        prestarLibro();
                        break;
                    case 4:
                        System.out.println("Lista libros");
                        break;
                    case 5:
                        listarPrestamos();
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
        System.out.println("5. lista de prestamos");
        System.out.println("9. salir");
    }
    public static void crearUsuario(){

            System.out.println("Ingrese el nombre de Usuario");
            String nombre = scanner.nextLine();
            if (nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+")) {
                System.out.println("Error: El nombre no puede contener números.");
                return;
            }
            System.out.println("Ingrese la Cedula");
            String cedula = scanner.nextLine();
            System.out.println("Ingrese el Correo");
            String correo = scanner.nextLine();
            System.out.println("Ingrerse el Telefono");
            String telefono = scanner.nextLine();
            Usuario usuario = new Usuario(nombre,cedula,correo,telefono);
            biblioteca.agregarUsuario(usuario);

    }
    public static void crearLibro(){
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
        Libro libro = new Libro(titulo,autor,isbn,editorial,anioPublicacion,genero);
        biblioteca.agregarLibro(libro);
    }
    public static void prestarLibro() {
        System.out.println("Ingrese el Titulo del Libro a Prestar");
        String titulo = scanner.nextLine();
        System.out.println("Ingrese el Nombre del Usuario");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la Fecha de Prestamo");
        String fechaPrestamo = scanner.nextLine();
        System.out.println("Ingrese la Fecha de Devolucion");
        String fechaDevolucion = scanner.nextLine();
        //Buscar libro y usuario
        Libro libro = biblioteca.buscarPorTitulo(titulo).stream().findFirst().orElse(null);
        Usuario usuario = biblioteca.buscarUsuarioPorNombre(nombre).stream().findFirst().orElse(null);
        if (libro == null) {
            System.out.println("Libro no encontrado");
            return;
        }
        if(usuario == null){
            System.out.println("Usuario no encontrado");
            return;
        }
        Prestamo prestamo = new Prestamo(libro,usuario,fechaPrestamo,fechaDevolucion);
        biblioteca.agregarPrestamo(prestamo);
        usuario.prestarLibro(libro);
        System.out.println("Prestamo realizado con exito");
    }
    public static void listarPrestamos(){
        System.out.println("Lista de Prestamos");
        biblioteca.getPrestamos().forEach(System.out::println);
    }

}
