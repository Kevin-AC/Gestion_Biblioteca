package edu.iudigital;

import java.util.List;

public class Biblioteca {
    private String nombre;
    private List<Libro> catalogo;
    private List<Usuario> usuarios;

    public Biblioteca(String nombre, List<Usuario> usuarios, List<Libro> catalogo) {
        this.nombre = nombre;
        this.usuarios = usuarios;
        this.catalogo = catalogo;
    }


}
