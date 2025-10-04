package edu.iudigital;

import com.sun.source.doctree.RawTextTree;

public class Prestamo {
    private Libro libro;
    private Usuario usuario;
    private String fechaPrestamo;
    private String fehcaDevolucion;
    private boolean devuelto;

    public Prestamo(Libro libro, Usuario usuario, String fechaPrestamo, String fehcaDevolucion) {
        this.libro = libro;
        this.usuario = usuario;
        this.fechaPrestamo = fechaPrestamo;
        this.fehcaDevolucion = fehcaDevolucion;
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
    public String getFehcaDevolucion() {
        return fehcaDevolucion;
    }
    public void setFehcaDevolucion(String fehcaDevolucion) {
        this.fehcaDevolucion = fehcaDevolucion;
    }
    public boolean isDevuelto() {
        return devuelto;
    }
    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }
    @Override
    public String toString() {
        return "Prestamo{" +
                "libro=" + libro.getTitulo() +
                ", usuario=" + usuario.getNombre() +
                ", fechaPrestamo=" + fechaPrestamo +
                ", devuelto=" + devuelto +
                '}';
    }


}
