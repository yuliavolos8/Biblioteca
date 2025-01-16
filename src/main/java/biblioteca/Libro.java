package biblioteca;

import java.util.HashSet;
import java.util.Set;

public class Libro {
    private long ISBN;
    private int anio;
    private String autor;
    private String titulo;
    private Set<String> temas = new HashSet<>();

    // Constructor completo
    public Libro(long ISBN, int anio, String autor, String titulo) {
        this.ISBN = ISBN;
        this.anio = anio;
        this.autor = autor;
        this.titulo = titulo;

    }

    // Getters y setters
    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Set<String> getTemas() {
        return temas;
    }

    public void setTemas(Set<String> temas) {
        this.temas = temas;
    }

    @Override
    public String toString() {
        return "Libro [ISBN=" + ISBN + ", anio=" + anio + ", autor=" + autor + ", titulo=" + titulo + ", temas=" + temas + "]";
    }


}
