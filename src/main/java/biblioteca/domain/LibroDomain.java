package biblioteca.domain;

import java.time.Year;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibroDomain {
    private long ISBN;
    private int anio;
    private String autor;
    private String titulo;

    // Método para comprobar si el año del libro es válido
    public boolean comprobarAnio() {
        return Year.now().getValue() - anio <= 30;
    }
}
