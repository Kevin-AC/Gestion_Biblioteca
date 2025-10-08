package Service;
import Modelo.*;
import Transacciones.CalcularMulta;
import Transacciones.MultaFija;
import Transacciones.MultaProgresiva;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BibliotecaService {
    private static Biblioteca biblioteca;
    private static Scanner scanner;

    private CalcularMulta calculadorMulta = new MultaFija();

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

    public static void MenuCrearLibro(){
        System.out.println("Seleccione el tipo de libro a crear:");
        System.out.println("1. Libro Fisico");
        System.out.println("2. Libro Electronico");
        int seleccion = 0;
        try {
            seleccion = scanner.nextInt();
            scanner.nextLine();
            switch (seleccion) {
                case 1:
                    System.out.println("Creando Libro Fisico");
                    DatosLibro datosLibro = datosComunesLibro();
                    Libro libroFisico = new Libro(datosLibro.titulo, datosLibro.autor, datosLibro.isbn, datosLibro.editorial, datosLibro.anioPublicacion, datosLibro.genero);
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
        }

    }
    public  static DatosLibro datosComunesLibro(){
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
            if (!nombre.matches("^[\\p{L} ]+$")) {
                System.out.println("Error: El nombre solo puede contener letras y espacios. Intente de nuevo.");
            } else {
                break;
            }
        }
        return nombre;
    }
    public  void devolverLibro(){
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
        // seleccionar multa
        seleccionarMulta();
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

        double multa = calcularMulta(prestamo);
        System.out.println("Días de retraso ingresados: " + diasRetraso);
        System.out.println("Objeto calcularMulta es de tipo: " + calculadorMulta.getClass().getSimpleName());

        System.out.println("Prestamo devuelto con exito");
        System.out.println("Multa a pagar: " + multa);


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
    public void setCalcularMulta(CalcularMulta calcularMulta) {
        this.calculadorMulta = calcularMulta;
    }
    public double calcularMulta(int diasRetraso) {
        return calculadorMulta.calcularMulta(diasRetraso);
    }
    public double calcularMulta(Prestamo prestamo){
        long diasRetraso = prestamo.calcularDiasRetraso();
        return calcularMulta((int)diasRetraso);
        }
    public void seleccionarMulta() {
        System.out.println("Seleccione el tipo de multa:");
        System.out.println("1. Multa fija");
        System.out.println("2. Multa progresiva");
        int seleccion = 0;
        try {
            seleccion = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Opcion no valida.");
            scanner.nextLine();
            return;
        }
        switch (seleccion) {
            case 1:
                setCalcularMulta(new MultaFija());
                System.out.println("Multa fija seleccionada.");
                break;
            case 2:
                setCalcularMulta(new MultaProgresiva());
                System.out.println("Multa progresiva seleccionada.");
                break;
            default:
                System.out.println("Opcion no valida.");
        }
    }//menu seleccion de multa





}
