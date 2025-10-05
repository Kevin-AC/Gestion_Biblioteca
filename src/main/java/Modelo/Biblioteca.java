package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Biblioteca {
    private static Biblioteca instancia;
    private String nombre;
    private List<Libro> catalogo;
    private List<Usuario> usuarios;
    private List<Prestamo>prestamos;


    private Biblioteca(String nombre) {
        this.nombre = nombre;
        this.catalogo = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.prestamos = new ArrayList<>();
    }

    public static Biblioteca getInstance(String nombre) {
        if (instancia == null) {
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
    public List<Usuario>buscarUsuarioPorNombre(String nombre){
        return usuarios.stream().filter(usuario -> usuario.getNombreCompleto().equalsIgnoreCase(nombre))
                .collect(Collectors.toList());
    }
    public List<Prestamo>getPrestamosActivos(){
        return prestamos.stream().filter(prestamo -> !prestamo.isDevuelto()).collect(Collectors.toList());
    }
    public void agregarPrestamo(Prestamo prestamo){
        prestamos.add(prestamo);
    }

    public void agregarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }

    public String getNombre() {
        return nombre;
    }

    public void mostrarLibros(){
        if (catalogo.isEmpty()){
            System.out.println("No hay libros disponibles");
        }else {
            System.out.println("\nLista de libros");
            for(int i = 0; i < catalogo.size(); i++){
                System.out.println((i+1)+ "."+catalogo.get(i));
            }
        }

    }


}
