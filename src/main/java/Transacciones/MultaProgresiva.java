package Transacciones;

public class MultaProgresiva implements CalcularMulta {
    @Override
    public double calcularMulta(int diasAtraso) {
        double multa = 0.0;
        for (int i = 1; i <= diasAtraso; i++) {
            multa += i * 1.0; // Multa progresiva: $1 el primer día, $2 el segundo, etc.
        }
        return multa;
    }
}
