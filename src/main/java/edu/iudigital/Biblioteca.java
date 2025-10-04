package edu.iudigital;

import java.util.List;
import java.util.stream.Collectors;

public class Biblioteca {
    private String nombre;
    private List<Libro> catalogo;
    private List<Usuario> usuarios;

    public Biblioteca(String nombre, List<Usuario> usuarios, List<Libro> catalogo) {
        this.nombre = nombre;
        this.usuarios = usuarios;
        this.catalogo = catalogo;
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
