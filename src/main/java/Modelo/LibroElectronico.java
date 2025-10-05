package Modelo;

public class LibroElectronico extends Libro {
    private String formatoLibro;
    private double tamanio;
    private String url;

    public LibroElectronico (String titulo, String autor,String isbn, String editorial, String anioPublicacion, String genero,
                             String formatoLibro, double tamanio, String url){
        super (titulo, autor, isbn, editorial, anioPublicacion, genero);
        this.formatoLibro = formatoLibro;
        this.tamanio = tamanio;
        this.url = url;
    }

    public String getFormatoLibro() {
        return formatoLibro;
    }

    public void setFormatoLibro(String formatoLibro) {
        this.formatoLibro = formatoLibro;
    }

    public double getTamanio() {
        return tamanio;
    }

    public void setTamanio(double tamanio) {
        this.tamanio = tamanio;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    @Override
    public String toString(){
        return super.toString() + "\nFormato del libro : " + formatoLibro +
                "\nTamaño (MB)       : " + tamanio +
                "\nEnlace            : " + url +
                "\n---------------------------------";
    }
}
