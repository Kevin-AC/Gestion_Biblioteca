package Modelo;

public class LibroElectronico extends Libro {
    private String formatoLibro;
    private String url;

    public LibroElectronico (String titulo, String autor,String isbn, String editorial, String anioPublicacion, String genero,
                             String formatoLibro, String url){
        super (titulo, autor, isbn, editorial, anioPublicacion, genero);
        this.formatoLibro = formatoLibro;
        this.url = url;
    }

    public String getFormatoLibro() {
        return formatoLibro;
    }

    public void setFormatoLibro(String formatoLibro) {
        this.formatoLibro = formatoLibro;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    @Override
    public String toString(){
        return super.toString() + " | LibroElectronico"+
        "formato del libro='" + formatoLibro + '\'' +
                ", urlDescarga='" + url+ '\'' +
                '}';
    }
}
