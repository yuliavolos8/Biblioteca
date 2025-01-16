package biblioteca.infrastructure.repository;

import biblioteca.domain.LibroDomain;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LibroRepository {
    private List<LibroDomain> libros;

    public LibroRepository() {
        libros = new ArrayList<>();
        libros.add(new LibroDomain(23452, 2000, "Tomas Perez", "Anochecer"));
        libros.add(new LibroDomain(678634, 2003, "Ana Fernandez", "Anochecer"));
        libros.add(new LibroDomain(3453, 2009, "Maria Blanco", "Anochecer"));
    }

    public LibroDomain save(LibroDomain libroDomain) {
        libros.add(libroDomain);
        return libroDomain;
    }

    public List<LibroDomain> getAll() {

        return new ArrayList<>(libros);
    }


    public LibroDomain getByIsbn(long ISBN) {
        return libros.stream()
                .filter(libro -> libro.getISBN() == ISBN)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Libro con el ISBN: " + ISBN + " no encontrado"));
    }


    public LibroDomain updateLibro(LibroDomain libro) {
        for (LibroDomain libroDomain : libros) {
            System.out.println("Comparando: " + libroDomain.getISBN() + " con " + libro.getISBN());
            if (libroDomain.getISBN() == libro.getISBN()) {
                libroDomain.setAnio(libro.getAnio());
                libroDomain.setAutor(libro.getAutor());
                libroDomain.setTitulo(libro.getTitulo());
                return libroDomain;
            }
        }
        throw new RuntimeException("Libro con el ISBN: " + libro.getISBN() + " no encontrado, por lo tanto no se pudo actualizar.");
    }


    public LibroDomain removeByISBN(long ISBN) {
        LibroDomain libro = libros.stream()
                .filter(libroDomain -> libroDomain.getISBN() == ISBN)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Libro con el ISBN: " + ISBN + " no encontrado"));

        libros.remove(libro);
        return libro;
    }
}