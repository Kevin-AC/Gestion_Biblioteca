package Service;

import Modelo.Biblioteca;
import Modelo.Libro;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LibroService {
    private  Scanner scanner;
    private  Biblioteca biblioteca;
    public LibroService(Scanner scanner, Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        this.scanner = scanner;

    }

    public void listarLibrosPorGenero() {
        System.out.println("Ingrese el genero a buscar");
        String genero = scanner.nextLine();
        List<Libro> librosFiltrados = biblioteca.getCatalogo().stream()
                .filter(libro -> libro.getGenero().equalsIgnoreCase(genero))
                .collect(Collectors.toList());

        if(librosFiltrados.isEmpty()) {
            System.out.println("No se encontraron libros para el género: " + genero);
        } else {
            System.out.println("Libros encontrados:");
            librosFiltrados.forEach(libro -> System.out.println(libro));
        }
    }
    public void mostrarLibros(){
        List<Libro> catalogo = biblioteca.getCatalogo();
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
