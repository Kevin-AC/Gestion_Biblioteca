package edu.iudigital;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombreCompleto;
    private String cedula;
    private String correo;
    private String telefono;
    private List<Libro> librosPrestados;


    public Usuario(String nombreCompleto, String cedula, String correo, String telefono) {
        this.nombreCompleto = nombreCompleto;
        this.cedula = cedula;
        this.correo = correo;
        this.telefono = telefono;
        this.librosPrestados = new ArrayList<>();
    }

        public void prestarLibro (Libro libro){
            librosPrestados.add(libro);
        }

        public void devolverLibro (Libro libro){
        librosPrestados.remove(libro);
        } public List<Libro> getLibrosPrestados(){
             return librosPrestados;
         }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setLibrosPrestados(List<Libro> librosPrestados) {
        this.librosPrestados = librosPrestados;
    }

    @Override
     public String toString(){
        return "Usuario" +
                "Nombre Completo=" +nombreCompleto+
                ", Cedula='" + cedula+ '\'' +
                ", Correo='" + correo+ '\'' +
                ", Telefono='" + telefono + '\'' +
                ", Libros Prestados=" + librosPrestados
                ;
    }
}





