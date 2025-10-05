package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombreCompleto;
    private String cedula;
    private String correo;
    private String telefono;
    private List<Prestamo> librosPrestados;


    public Usuario(String nombreCompleto, String cedula, String correo, String telefono) {
        this.nombreCompleto = nombreCompleto;
        this.cedula = cedula;
        this.correo = correo;
        this.telefono = telefono;
        this.librosPrestados = new ArrayList<>();
    }

    public void prestarLibro (Prestamo prestamo){
        librosPrestados.add(prestamo);
    }
    public Prestamo devolverLibro (String titulo){
        for (Prestamo prestamo : librosPrestados){
            if(!prestamo.isDevuelto() && prestamo.getLibro().getTitulo().equalsIgnoreCase(titulo)){
                prestamo.marcarDevuelto();

                 return prestamo;
            }
        }
        return null;
    }
    public List<Prestamo> getPrestamos() {
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





