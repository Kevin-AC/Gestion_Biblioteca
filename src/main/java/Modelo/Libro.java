package Modelo;

public class Libro {
    protected String titulo;
    protected String autor;
    protected String isbn;
    protected String editorial;
    protected String anioPublicacion;
    protected String genero;



     public Libro (String titulo, String autor, String isbn, String editorial, String anioPublicacion, String genero){
         this.titulo = titulo;
         this.autor = autor;
         this.isbn = isbn;
         this.editorial = editorial;
         this.anioPublicacion = anioPublicacion;

         this.genero = genero;
     }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(String anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }


    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }


    @Override

    public String toString(){
         return "------------------------" +
                 "\n📖 Libro" +
                 "\nTítulo       : " + titulo +
                 "\nAutor        : " + autor +
                 "\nISBN         : " + isbn +
                 "\nEditorial    : " + editorial +
                 "\nAño Publicación : " + anioPublicacion +
                 "\nGénero       : " + genero +
                 "\n-";

    }
}
