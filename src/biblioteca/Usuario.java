package biblioteca;

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

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setNombre(String nomb) {
		String nombre = nomb.substring(0, 1).toUpperCase() + nomb.substring(1).toLowerCase();
		this.nombre = nombre;
	}

	public void setApellido(String ap) {
		String apellido = ap.substring(0, 1).toUpperCase() + ap.substring(1).toLowerCase();
		this.apellido = apellido;
	}

	public int getID() {
		return this.ID;
	}

	public String getNumDni() {
		return dni.toString();
	}

	public DNI getDni() {
		return this.dni;
	}

	public String getTelefono() {
		return NumTelefono.toString();
	}

	public Telefono getNumTelefono() {
		return this.NumTelefono;
	}

// comprobar si usuario esta registrado ya
	public boolean equals(Usuario usuario) {
		boolean iguales = false;
		if (this.nombre.equals(usuario.getNombre()) && this.apellido.equals(usuario.getApellido())
				&& this.dni.equals(usuario.getDni()) && this.NumTelefono.equals(usuario.getTelefono())
				&& this.ID == usuario.getID()) {
			iguales = true;
		}
		return iguales;

	}

	@Override
	public String toString() {
		return "Usuario [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", NumTelefono=" + NumTelefono
				+ ", ID=" + ID + ", historial=" + historial + "]";
	}

}
