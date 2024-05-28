package biblioteca;

public class Telefono {
	private String numero;

	public Telefono(String num) throws Exception {
		setNumero(num);

	}

	public Telefono() {
		this.numero = "000000000";
	}

// comprobar si el num de telefono esta correcto
	public void setNumero(String numero) throws Exception {
		if (numero.length() != 9) {
			Exception E = new Exception("Error: no tiene 9 digitos");
			throw E;
		}
		try {
			Integer.parseInt(numero);
			this.numero = numero;
		} catch (NumberFormatException excepcion) {
			throw excepcion;
		}

	}

	public String getNumero() {
		return numero;
	}

// comprobar si usuario esta registrado basandose en el numero de telefono como quey
	public boolean equals(Telefono telefono) {
		boolean iguales = false;
		if (this.numero.equals(telefono.getNumero())) {
			iguales = true;
		}

		return iguales;
	}

	@Override
	public String toString() {
		return "Telefono [numero=" + numero + "]";
	}
	

}