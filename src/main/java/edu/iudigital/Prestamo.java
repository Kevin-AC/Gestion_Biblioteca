package edu.iudigital;

import com.sun.source.doctree.RawTextTree;

public class Prestamo {
    private Libro libro;
    private Usuario usuario;
    private String fechaPrestamo;
    private String fechaDevolucion;
    private boolean devuelto;

    public Prestamo(Libro libro, Usuario usuario, String fechaPrestamo, String fechaDevolucion) {
        this.libro = libro;
        this.usuario = usuario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.devuelto = false;
    }

    //getters and setters

    public Libro getLibro() {
        return libro;
    }
    public void setLibro(Libro libro) {
        this.libro = libro;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public String getFechaPrestamo() {
        return fechaPrestamo;
    }
    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }
    public String getFechaDevolucion() {
        return fechaDevolucion;
    }
    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
    public boolean isDevuelto() {
        return devuelto;
    }
    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
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
