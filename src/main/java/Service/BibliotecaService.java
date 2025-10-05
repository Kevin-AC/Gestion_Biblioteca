package Service;
import Modelo.Biblioteca;
import Modelo.Libro;
import Modelo.Prestamo;
import Modelo.Usuario;

import java.time.LocalDate;
import java.util.Scanner;

public class BibliotecaService {
    private static Biblioteca biblioteca;
    private static Scanner scanner;

    public BibliotecaService(Biblioteca biblioteca, Scanner scanner) {
        this.biblioteca = biblioteca;
        this.scanner = scanner;
    }
    public static void crearUsuario(){
        try {

            String nombre = validarNombre();

            String cedula;
            while (true) {
                System.out.println("Ingrese su Cedula");
                cedula = scanner.nextLine();
                if (!cedula.matches("[0-9]+")) {
                    System.out.println("Error: La cedula solo puede contener numeros. Intente de nuevo.");
                } else {
                    break;
                }
            }
            System.out.println("Ingrese el Correo");
            String correo = scanner.nextLine();
            String telefono;
            while (true) {
                System.out.println("Ingrese el Telefono");
                telefono = scanner.nextLine();
                if (!telefono.matches("[0-9]+")) {
                    System.out.println("Error: El telefono solo puede contener numeros. Intente de nuevo.");
                } else {
                    break;
                }
            }

            Usuario usuario = new Usuario(nombre,cedula,correo,telefono);
            biblioteca.agregarUsuario(usuario);
            System.out.println("Usuario creado con exito");

        }catch (Exception e){
            System.out.println("Error: "+ e.getMessage());

        }

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
        System.out.println("Libro creado con exito");
    }
    public static void prestarLibro() {
        System.out.println("Ingrese el Titulo del Libro a Prestar");
        String titulo = scanner.nextLine();
        String nombre = validarNombre();//llamada a metodo 1 vez para validar nombre

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
        Prestamo prestamo = new Prestamo(libro,usuario, LocalDate.now());
        biblioteca.agregarPrestamo(prestamo);
        usuario.prestarLibro(prestamo);
        System.out.println("Prestamo realizado con exito");
    }
    public static void listarPrestamos(){
        System.out.println("Lista de Prestamos");
        biblioteca.getPrestamosActivos();
        for(Prestamo prestamo : biblioteca.getPrestamosActivos()){
            System.out.println(prestamo);
        }
    }
    public static String validarNombre(){
        String nombre;
        while (true) {
            System.out.println("Ingrese el nombre de Usuario");
            nombre = scanner.nextLine();
            if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
                System.out.println("Error: El nombre solo puede contener letras y espacios. Intente de nuevo.");
            } else {
                break;
            }
        }
        return nombre;
    }
    public static void devolverLibro(){
        String nombre = validarNombre();
        Usuario usuario = biblioteca.buscarUsuarioPorNombre(nombre).stream().findFirst().orElse(null);
        if(usuario == null) {
            System.out.println("Usuario no encontrado");
            return;
        }
        System.out.println("Ingrese el titulo del libro a devolver");
        String titulo = scanner.nextLine();
        Prestamo prestamo = usuario.devolverLibro(titulo);
        if(prestamo == null){
            System.out.println("Prestamo no encontrado");
            return;
        }
        System.out.println("Prestamo devuelto con exito");






    }

}
