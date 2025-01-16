package biblioteca.infrastructure.controller;

import biblioteca.Libro;
import biblioteca.domain.LibroDomain;
import biblioteca.application.service.LibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libro")
@RequiredArgsConstructor
public class LibroController {
    private final LibroService libroService;

    @PostMapping("")
    public ResponseEntity<LibroDomain> addLibro(@RequestBody LibroDomain libro) {
        try {
            return new ResponseEntity<>(libroService.save(libro), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<LibroDomain>> listLibros() {
        List<LibroDomain> libros = libroService.getAllLibros();
        if (libros.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<LibroDomain> LibroISBN(@PathVariable long isbn) {
        try {
            LibroDomain libBuscado = libroService.getByIsbn(isbn);
            return ResponseEntity.ok(libBuscado);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<LibroDomain> actualizarLibro(@PathVariable long isbn, @RequestBody LibroDomain libro) {
        try {
            LibroDomain libroActualizar = libroService.getByIsbn(isbn);
            libroService.updateLibro(libro);

            return new ResponseEntity<>(libro, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<String> borrarLibro(@PathVariable long isbn) {
        try {
            libroService.removeByISBN(isbn);
            return new ResponseEntity<>("Libro eliminado exitosamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}


