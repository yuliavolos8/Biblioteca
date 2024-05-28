package biblioteca;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;

public class Contrasenia {

	private String contrasenia;

	public Contrasenia(String combinacion) throws Exception {
		if (!ComprobarContrasenia(combinacion)) {
			throw new Exception("Contrasenia no es valida");
		}
		this.contrasenia = combinacion;
	}

	// Método para crear una contraseña aleatoria segura
	public static String crearContraseña() {
		String mayusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String minusculas = mayusculas.toLowerCase();
		String numeros = "0123456789";
		String simbolos = "!@#$%";
		String valores = mayusculas + minusculas + numeros + simbolos;

		// Lista para mantener los caracteres de la contraseña
		ArrayList<Character> contraseña = new ArrayList<>();
		SecureRandom random = new SecureRandom();

		// Añadir caracteres de cada grupo al menos una vez
		contraseña.add(mayusculas.charAt(random.nextInt(mayusculas.length())));
		contraseña.add(minusculas.charAt(random.nextInt(minusculas.length())));
		contraseña.add(numeros.charAt(random.nextInt(numeros.length())));
		contraseña.add(simbolos.charAt(random.nextInt(simbolos.length())));

		// Rellenar el resto de la contraseña hasta alcanzar la longitud deseada
		for (int i = 4; i < 8; i++) { // Genera una contraseña de 8 caracteres
			contraseña.add(valores.charAt(random.nextInt(valores.length())));
		}

		// Mezclar los caracteres para evitar patrones predecibles
		Collections.shuffle(contraseña, random);

		// Convertir la lista en una cadena
		StringBuilder contraseñaFinal = new StringBuilder();
		for (Character ch : contraseña) {
			contraseñaFinal.append(ch);
		}

		return contraseñaFinal.toString();
	}

	public static boolean ComprobarContrasenia(String contrasenia) {
		boolean valida = true;
		if (contrasenia.length() < 8) {
			valida = false;
		}
		return valida;
	}

	public static void main(String[] args) {
		String miContraseña = crearContraseña();
		System.out.println("Tu nueva contraseña es: " + miContraseña);
	}
}
