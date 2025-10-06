package Transacciones;

public class MultaFija implements CalcularMulta {
    @Override
    public double calcularMulta(int diasAtraso) {
        return diasAtraso * 2.0; // Multa fija de $2 por día de retraso
    }
}
