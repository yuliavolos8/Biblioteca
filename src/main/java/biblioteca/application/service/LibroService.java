package biblioteca.application.service;

import biblioteca.domain.LibroDomain;
import biblioteca.infrastructure.repository.LibroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LibroService {
    private final static int ANIO_MAX_ANTIGUEDAD = 30;
    private final LibroRepository libroRepository;

    public LibroDomain save(LibroDomain libroDomain) {
        LocalDate fechaHoy = LocalDate.now();
        int anio = fechaHoy.getYear();
        int cantAnios = anio - libroDomain.getAnio();

        if (cantAnios > ANIO_MAX_ANTIGUEDAD) {
            throw new IllegalArgumentException("El libro es demasiado antiguo.");
        }

        return libroRepository.save(libroDomain);
    }

    public List<LibroDomain> getAllLibros() {
        return libroRepository.getAll();
    }

    public LibroDomain getByIsbn(long ISBN) {
        return libroRepository.getByIsbn(ISBN);
    }

    public LibroDomain updateLibro(LibroDomain libroDomain) {
        return libroRepository.updateLibro(libroDomain);
    }


    public LibroDomain removeByISBN(long ISBN) {
        return libroRepository.removeByISBN(ISBN);
    }
}
