package Modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Prestamo {
    private Libro libro;
    private Usuario usuario;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private boolean devuelto;

    private static final int DURACION_PRESTAMO_DIAS = 2; // Duración del préstamo sin multa
    public Prestamo(Libro libro, Usuario usuario, LocalDate fechaPrestamo) {
        this.libro = libro;
        this.usuario = usuario;
        this.fechaPrestamo = fechaPrestamo;
        this.devuelto = false;
    }

    //getters and setters
    public Libro getLibro() {
        return libro;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }
    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }
    public boolean isDevuelto() {
        return devuelto;
    }
    public void marcarDevuelto(){
        this.devuelto=true;
        if (this.fechaDevolucion == null) {
            this.fechaDevolucion = LocalDate.now();
        }
    }
    public long calcularDiasRetraso() {
        if (!devuelto || fechaDevolucion == null) {
            return 0;
        }
        long diasTranscurridos = ChronoUnit.DAYS.between(fechaPrestamo, fechaDevolucion);
        long diasRetraso = diasTranscurridos - DURACION_PRESTAMO_DIAS;
        return diasRetraso > 0 ? diasRetraso : 0;
    }
    public void setFechaDevolucion(LocalDate fechaDevolucion){
        this.fechaDevolucion = fechaDevolucion;
    }



    @Override
    public String toString() {
        long diasRetraso = calcularDiasRetraso();

        return "Préstamo:\n" +
                "  Libro: " + libro.getTitulo() + "\n" +
                "  Usuario: " + usuario.getNombreCompleto() + "\n" +
                "  Fecha de Préstamo: " + fechaPrestamo + "\n" +
                "  Fecha de Devolución: " + (fechaDevolucion != null ? fechaDevolucion : "No devuelto aún") + "\n" +
                "  Estado: " + (devuelto ? "Devuelto" : "En préstamo") + "\n" +
                "  Dias de retraso: " + diasRetraso + "\n";
    }




}
