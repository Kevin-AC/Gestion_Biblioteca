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
