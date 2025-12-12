package busqueda;

import java.util.Objects;
import java.util.Comparator;

public class Libro {
    private String isbn;
    private String titulo;
    private String autor;

    // Constructor
    public Libro (String isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
    }

    
    public String getIsbn () { return isbn; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }

    @Override
    public String toString() {
        return "Libro: " + titulo + " (ISBN: " + isbn + ", Autor: " + autor + ")";
    }
    

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
    
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Libro libro = (Libro) obj;

        return Objects.equals(isbn, libro.isbn);
    }
    

    public static Comparator<Libro> ISBN_COMPARATOR = new Comparator<Libro>() {
        @Override
        public int compare(Libro l1, Libro l2) {
            return l1.getIsbn().compareTo(l2.getIsbn());
        }
    };
}