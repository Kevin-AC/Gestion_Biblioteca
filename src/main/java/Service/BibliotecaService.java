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
    private  Biblioteca biblioteca;
    private  Scanner scanner;

    private CalcularMulta calculadorMulta = new MultaFija();

    public BibliotecaService(Biblioteca biblioteca, Scanner scanner) {
        this.biblioteca = biblioteca;
        this.scanner = scanner;
    }


    public void MenuCrearLibro(){
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

    public CalcularMulta getCalculadorMulta() {
        return calculadorMulta;
    }





}
