package Modelo;

import java.time.LocalDate;

public class Prestamo {
    private Libro libro;
    private Usuario usuario;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private boolean devuelto;

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
        this.fechaDevolucion = LocalDate.now();
    }


    @Override
    public String toString() {
        return "Préstado:\n" +
                "  Libro: " + libro.getTitulo() + "\n" +
                "  Usuario: " + usuario.getNombreCompleto() + "\n" +
                "  Fecha de Préstamo: " + fechaPrestamo + "\n" +
                "  Fecha de Devolución: " + (fechaDevolucion != null ? fechaDevolucion : "No devuelto aún") + "\n" +
                "  Estado: " + (devuelto ? "Devuelto" : "En préstamo");
    }



}
