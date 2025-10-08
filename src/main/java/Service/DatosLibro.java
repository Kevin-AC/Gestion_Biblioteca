package Service;

import java.util.stream.Stream;

public class DatosLibro {
    String titulo;
    String autor;
    String isbn;
    String editorial;
    String anioPublicacion;
    String genero;

    public DatosLibro(String titulo, String autor, String isbn, String editorial, String anioPublicacion,String genero){
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.editorial = editorial;
        this.anioPublicacion = anioPublicacion;
        this.genero = genero;
    }

}
