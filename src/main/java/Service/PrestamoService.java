package Service;

import Modelo.Biblioteca;
import Modelo.Libro;
import Modelo.Prestamo;
import Modelo.Usuario;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PrestamoService {
    private  Scanner scanner;
    private  Biblioteca biblioteca;
    private  BibliotecaService bibliotecaService;
    private  UsuarioService usuarioService;

    public PrestamoService(Biblioteca biblioteca, Scanner scanner,UsuarioService usuarioService) {
        this.biblioteca = biblioteca;
        this.scanner = scanner;
        this.usuarioService= usuarioService;
        this.bibliotecaService = new BibliotecaService(biblioteca, scanner);
    }

    public  void prestarLibro() {
        System.out.println("Ingrese el Titulo del Libro a Prestar");
        String titulo = scanner.nextLine();
        String nombre = usuarioService.validarNombre();//llamada a metodo 1 vez para validar nombre

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
    public  void listarPrestamos(){
        System.out.println("Lista de Prestamos");
        biblioteca.getPrestamosActivos();
        for(Prestamo prestamo : biblioteca.getPrestamosActivos()){
            System.out.println(prestamo);
        }
    }
    public void devolverLibro(){
        String nombre = usuarioService.validarNombre();
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
        // seleccionar multa
        bibliotecaService.seleccionarMulta();
        //simular retraso de 15 dias
        System.out.println("Ingrese los dias de retraso en la devolucion");
        int diasRetraso;
        try {
            diasRetraso = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer
            if (diasRetraso < 0) {
                System.out.println("El número de días no puede ser negativo.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Debe ingresar un número entero válido.");
            scanner.nextLine(); // limpiar buffer
            return;
        }
        prestamo.setFechaDevolucion(prestamo.getFechaPrestamo().plusDays(diasRetraso));
        prestamo.marcarDevuelto();

        double multa = bibliotecaService.calcularMulta(prestamo);
        System.out.println("Días de retraso ingresados: " + diasRetraso);
        System.out.println("Objeto calcularMulta es de tipo: " + bibliotecaService.getCalculadorMulta().getClass().getSimpleName());

        System.out.println("Prestamo devuelto con exito");
        System.out.println("Multa a pagar: " + multa);


    }


}
