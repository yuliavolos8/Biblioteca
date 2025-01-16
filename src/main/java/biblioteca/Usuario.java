package biblioteca;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Usuario {

    private DNI dni;
    private String nombre;
    private String apellido;
    private Telefono NumTelefono;
    private int ID;
    private static int contador = 0;
    private Historial historial;

    public Usuario(DNI dni, String n, String ap, Telefono telefono) {
        this.dni = dni;
        this.nombre = n;
        this.apellido = ap;
        this.NumTelefono = telefono;
        this.contador++;
        this.ID = contador;
        this.historial = new Historial(this);
    }

    public Usuario(String dni, String n, String ap, String telefono) throws Exception {
        this.dni = new DNI(dni);
        this.nombre = n;
        this.apellido = ap;
        this.NumTelefono = new Telefono(telefono);
        this.contador++;
        this.ID = contador;
        this.historial = new Historial(this);
    }

    public String getNumDni() {
        return dni.toString();
    }

    public Telefono getNumTelefono() {
        return this.NumTelefono;
    }

    // Comprobar si usuario está registrado ya
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Usuario usuario = (Usuario) obj;

        return ID == usuario.ID && dni.equals(usuario.dni) && nombre.equals(usuario.nombre) &&
                apellido.equals(usuario.apellido) && NumTelefono.equals(usuario.NumTelefono);
    }

    @Override
    public int hashCode() {
        int result = dni.hashCode();
        result = 31 * result + nombre.hashCode();
        result = 31 * result + apellido.hashCode();
        result = 31 * result + NumTelefono.hashCode();
        result = 31 * result + ID;
        return result;
    }

    @Override
    public String toString() {
        return "Usuario [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", NumTelefono=" + NumTelefono
                + ", ID=" + ID + ", historial=" + historial + "]";
    }
}
