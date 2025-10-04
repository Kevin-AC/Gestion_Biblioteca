package edu.iudigital;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Biblioteca {
    private static Biblioteca instancia;
    private String nombre;
    private List<Libro> catalogo;
    private List<Usuario> usuarios;

    private Biblioteca(String nombre) {
        this.nombre = nombre;
        this.catalogo = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    public static Biblioteca getInstance(String nombre){
        if(instancia == null){
            instancia = new Biblioteca(nombre);
        }
        return instancia;
    }


    public void agregarLibro(Libro libro) {
        catalogo.add(libro);
    }
    public List<Libro> buscarPorTitulo(String titulo){
        return catalogo.stream().filter(libro -> libro.getTitulo().equalsIgnoreCase(titulo))
                .collect(Collectors.toList());
    }
    public void agregarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }



}
