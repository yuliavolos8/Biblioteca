package biblioteca;

public class DNI {

	private String dni;

	public DNI(String palabra) throws Exception {
		SetDNI(palabra);

	}

	public boolean comprobarNumero(String palabra) {
		boolean valido = true;
		if (palabra.length() == 9) {

			String numero = palabra.substring(0, 8);
			try {
				Integer.parseInt(numero);
			} catch (NumberFormatException excepcion) {
				valido = false;
			}
		} else {
			valido = false;
		}
		return valido;
	}

	private char calcularLetraDNI(int numero) {
		String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
		int indice = numero % 23;
		return letras.charAt(indice);
	}

	private boolean comprobarDNI(String dni) {
		boolean valido = true;
		char letraCalculada = ' ';
		char letra = dni.charAt(dni.length() - 1);
		try {
			String numero = dni.substring(0, 8);
			int num = Integer.parseInt(numero);
			letraCalculada = calcularLetraDNI(num);
		} catch (NumberFormatException excepcion) {
			valido = false;
		}
		if (letra != letraCalculada) {
			valido = false;
		}
		return valido;
	}

	public String getDni() {
		return dni;
	}

	public void SetDNI(String dni) throws Exception {
		if (dni.length() != 9 || (!comprobarDNI(dni))) {
			Exception E = new Exception("ERROR: el formato no es valido");
			throw E;
		} else {
			this.dni = dni;
		}
	}

	public boolean equals(DNI dni) {
		boolean iguales = false;
		if (this.dni.equals(dni.getDni())) {
			iguales = true;
		}
		return iguales;
	}

	@Override
	public String toString() {
		return "DNI [dni=" + dni + "]";
	}

}
