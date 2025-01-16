package biblioteca;

import biblioteca.domain.LibroDomain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
public class BibliotecaPublica {

    private final static int CANT_MAX_LIBROS = 4000;
    private ArrayList<Usuario> usuarios;
    private ArrayList<LibroDomain> libros;

    public BibliotecaPublica() {
        this.usuarios = new ArrayList<>();
        this.libros = new ArrayList<>();
    }

    public static void main(String[] args) {
        SpringApplication.run(BibliotecaPublica.class, args);
    }

    public void eliminarLibro() {
        for (int pos = this.libros.size() - 1; pos >= 0; pos--) {
            LibroDomain libro = this.libros.get(pos);
            boolean anioValido = libro.comprobarAnio();
            if (!anioValido) {
                this.libros.remove(pos);
            }
        }
    }

    public boolean estaRegistrado(int ID) {
        return this.usuarios.stream().anyMatch(usuario -> usuario.getID() == ID);
    }

    public boolean estaRegistrado(Usuario usuario) {
        return this.usuarios.contains(usuario);
    }

    public boolean registrarUsuario(Usuario us) {
        if (this.usuarios.stream().anyMatch(usuario -> usuario.getDni().equals(us.getDni()))) {
            return false;
        }
        this.usuarios.add(us);
        return true;
    }

    public Set<LibroDomain> mostrarLibrosPrestados(int ID, long ISBNLibro) {
        return new HashSet<>(this.libros.stream()
                .filter(libro -> libro.getISBN() == ISBNLibro)
                .collect(Collectors.toList())); // Utiliza collect(Collectors.toList()) en lugar de toList()
    }

    @Override
    public String toString() {
        return "BibliotecaPublica [usuarios=" + usuarios + ", libros=" + libros + "]";
    }
}
