package biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


//practicando comandos2 remoto

public class BibliotecaPublica {

	private final static int AFORO_MAXIMO = 1000;
	private final static int CANT_MAX_LIBROS = 4000;
	private ArrayList<Usuario> usuarios;
	private ArrayList<Libro> libros;

	public BibliotecaPublica() {
		this.usuarios = new ArrayList<>();
		this.libros = new ArrayList<>();

	}

	public static void main(String[] args) throws Exception {
		/*
		 * Libro libro2 = new Libro(2, 1993, " Tomas Perez", " Anochecer"); Libro libro3
		 * = new Libro(4, 2003, " ANA fernandez", " Anochecer"); Libro libro1 = new
		 * Libro(1, 1999, " Maria Blanco", " Anochecer"); Libro libro4 = new Libro(3,
		 * 2023, " Ana ANACARDO", " MAR y luna"); Libro libro5 = new Libro(5, 2010,
		 * " Maria Blanco", " ANA kARENINA"); BibliotecaPublica b = new
		 * BibliotecaPublica(); b.ADDlibro(libro1); System.out.println("aniadimos " +
		 * libro1); b.ADDlibro(libro2); System.out.println("aniadimos " + libro2);
		 * b.ADDlibro(libro3); System.out.println("aniadimos " + libro3);
		 * b.ADDlibro(libro5); System.out.println("aniadimos " + libro5);
		 * b.ADDlibro(libro4); System.out.println("aniadimos " + libro4);
		 * b.eliminarLibro(); System.out.println("eliminar libro antiguo de " + b);
		 * 
		 * Usuario us1 = new Usuario("48768328W", "MARIA", " Gomez", "632074700");
		 * boolean esPosible = b.registrarUsuario(us1);
		 * System.out.println("registrar usuario " + us1 + esPosible);
		 */
		Scanner sc = new Scanner(System.in);
		System.out.println(" Menu inicial:\n 1. Iniciar sesion \n 2.Registrarse \n 3. Añadir un libro "
				+ "\n 4. Ver libros disponibles \n 5 Salir");
		System.out.println("eligue una opcion ");
		int respuesta = sc.nextInt();
		sc.nextLine();
		switch (respuesta) {
		case 1:
			iniciarSesion();
			break;
		case 2:
			registrarse();
			break;
		case 3:
			aniadirlibro();
		case 4:
			verLibrosDisponibles();
		}

	}

	private static Set<Libro> verLibrosDisponibles() throws ClassNotFoundException, SQLException {
		Connection c = iniciarConexion();
		Set<Libro> librosDisponibles = new HashSet<>();
		Statement s = c.createStatement();
		String consulta = "(SELECT DISTINCT  l.Titulo, l.Autor,l.ISBN FROM libro l) " + "MINUS "
				+ "(SELECT l.Titulo, l.Autor,l.ISBN  FROM Libro l,Prestamo WHERE l.ISBN=Prestamo.ISBN and Prestamo.devuelto = false);";
		ResultSet rs = s.executeQuery(consulta);
		if (rs.next()) {
		}
		return librosDisponibles;
	}

	private static void aniadirlibro() {
		Scanner sc = new Scanner(System.in);
		System.out.println(" introduce ISBN de libro");
		long ISBNintroducido = sc.nextLong();
		sc.nextLine();
		System.out.println(" introduce anio de libro");
		int anioIntroducido = sc.nextInt();
		sc.nextLine();
		System.out.println(" introduce el autor ");
		String autor = sc.nextLine();
		System.out.println(" introduce el titulo ");
		String titulo = sc.nextLine();
		System.out.println(" introduce las temas del libro:\n 1.amor y romance,\n 2.aventura y accion,\n 3.misterio, "
				+ "\n 4.fantasia,\n5.ciencia y ficcion,\n6. desarollo y autoayuda,\n7.drama,\n8.humor y satira,\n9.historia y evolucion,\n10.INDEFINIDO ");
		String tema = sc.nextLine();
		try {
			Libro libro1 = new Libro(ISBNintroducido, anioIntroducido, autor, titulo);
			Connection con = iniciarConexion();

			Statement s = con.createStatement();

			String consulta = "INSERT INTO Libro(ISBN,Anio, Autor,Titulo)VALUES('" + ISBNintroducido + "',"
					+ anioIntroducido + ",'" + autor + "','" + titulo + "')";
			s.executeUpdate(consulta);
			con.close();
			System.out.println("libro introducido");
			intoducirTemas(tema, ISBNintroducido);

		} catch (Exception e) {
			System.out.println(" formato incorecto: " + e.getMessage());
		}
	}

	private static void intoducirTemas(String tema, long ISBN) throws ClassNotFoundException, SQLException {
		Connection c = iniciarConexion();
		String[] numeros = tema.split(" ");
		List<String> nums = new ArrayList<>(Arrays.asList(numeros));
		Set<Integer> numerosEnteros = new HashSet<>();
		for (String num : nums) {

			try {
				int numConvertido = Integer.parseInt(num);
				if (numConvertido > 0 && numConvertido <= 10) {
					numerosEnteros.add(numConvertido);
				}

			} catch (Exception e) {

			}
		}
		if (numerosEnteros.isEmpty()) {
			Statement s = c.createStatement();
			String insertar = "INSERT INTO TrataT VALUES ('" + ISBN + "','" + "INDEFINIDO" + "')";
			s.executeUpdate(insertar);

		}

		for (Integer numero : numerosEnteros) {
			String t = "";
			switch (numero) {
			case 1:
				t = "amor y romance";
				break;
			case 2:
				t = "aventura y accion";
				break;
			case 3:
				t = "misterio";
				break;
			case 4:
				t = "fantasia";
				break;
			case 5:
				t = "ciencia y ficcion";
				break;
			case 6:
				t = "desarollo y autoayuda";
				break;
			case 7:
				t = "drama";
				break;
			case 8:
				t = "humor y satira ";
				break;
			case 9:
				t = "historia y evolucion";
				break;
			case 10:
				t = "INDEFINIDO";
				break;

			}
			Statement s = c.createStatement();
			String insertar = "INSERT INTO TrataT VALUES ('" + ISBN + "','" + t + "')";
			s.executeUpdate(insertar);
		}
		c.close();
	}

	private static void registrarse() throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println(" Introduce su nombre");
		String nombre = sc.nextLine();
		System.out.println(" Introduce su apellido");
		String apellido = sc.nextLine();
		String DNIIntroducido;
		String telefonoIntroducido = "";
		String contraseniaIntroducida = "";
		boolean dniCorrecto = false;
		boolean contraseniaCorrecta = false;
		boolean numCorrecto = false;
		do {
			System.out.println(" Introduce su DNI");
			DNIIntroducido = sc.nextLine();
			try {
				DNI dni = new DNI(DNIIntroducido);
				dniCorrecto = true;

				do {

					System.out.println(" Introduce su numero de telefono");
					telefonoIntroducido = sc.nextLine();
					try {
						Telefono telefono = new Telefono(telefonoIntroducido);
						numCorrecto = true;
						do {
							System.out.println("Crea la contrasenia, que tenga al menos 8 carcteres");
							contraseniaIntroducida = sc.nextLine();
							try {
								Contrasenia contrasenia = new Contrasenia(contraseniaIntroducida);
								contraseniaCorrecta = true;
								System.out.println(" te has registrado con exito");
							} catch (Exception E) {
								System.out.println(" Contrasenia no es segura");
							}
						} while (!contraseniaCorrecta);
					} catch (Exception E) {
						System.out.println("Numero de telefono es incorrecto");
					}
				} while (!numCorrecto);

			} catch (Exception E) {
				System.out.println("DNI introducido es incorrecto");
			}
		} while (!dniCorrecto);

		Connection conexion = iniciarConexion();
		String consulta = "INSERT INTO Usuario (DNI, nombre, apellido, telefono, contrasenia) VALUES ('"
				+ DNIIntroducido + "', '" + nombre + "', '" + apellido + "', '" + telefonoIntroducido + "', '"
				+ contraseniaIntroducida + "')";

	}

	public static void iniciarSesion() throws ClassNotFoundException, SQLException {
		Connection con = iniciarConexion();
		Scanner sc = new Scanner(System.in);
		System.out.println(" Introduce el DNI");
		String respuestaDNI = sc.nextLine();

		Statement s = con.createStatement();
		String consulta = "SELECT DNI FROM Usuario WHERE DNI LIKE '" + respuestaDNI + "'";
		ResultSet rs = s.executeQuery(consulta);
		if (rs.next()) {
			System.out.println("El DNI esta registrado");
			System.out.println(" Introduce la contrasenia");
			String respuestaContrasenia = sc.nextLine();

			consulta = "SELECT DNI,Contrasenia FROM Usuario WHERE DNI LIKE '" + respuestaDNI
					+ "' and Contrasenia LIKE '" + respuestaContrasenia + "'";
			rs = s.executeQuery(consulta);
			if (rs.next()) {
				System.out.println("contrasenia correcta");
				menuUsuario();
			} else {
				System.out.println("contrasenia incorrecta");
			}
		} else {
			System.out.println("El DNI no esta registrado");
		}
		con.close();
	}

	public static void menuUsuario() {
		System.out.println(" Bienvenido a tu biblioteca");
	}

	public static Connection iniciarConexion() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/Biblioteca?useSSL=false";
		String userName = "root";
		String contrasenia = "medaigual1A.";
		Connection con = DriverManager.getConnection(url, userName, contrasenia);
		return con;

	}

	// aniadimos libro si no se supera cantidad maxima
	public void ADDlibro(Libro libroNuevo) {
		if (this.libros.size() < CANT_MAX_LIBROS) {
			this.libros.add(libroNuevo);
		}
	}

//	eliminar libro si es fuera de rango

	public void eliminarLibro() {
		for (int pos = this.libros.size() - 1; pos >= 0; pos--) {
			Libro libro = this.libros.get(pos);
			boolean anioValido = libro.comprobarAnio();
			if (!anioValido) {
				this.libros.remove(pos);
			}
		}
	}

// comprobar con ID si esta registrado
	public boolean estaRegistrado(int ID) {
		boolean registrado = false;
		for (Usuario usuario : this.usuarios) {
			int IDUsuario = usuario.getID();
			if (IDUsuario == ID) {
				registrado = true;
			}
		}
		return registrado;
	}

// comprobar si esta usuario en la lista
	public boolean estaRegistrado(Usuario usuario) {
		boolean registrado = false;
		if (this.usuarios.contains(usuario)) {
			registrado = true;
		}
		return registrado;
	}

	// registra usuario nuevo si es posible //
	public boolean registrarUsuario(Usuario us) {
		boolean esPosible = true;
		for (Usuario usuario : this.usuarios) {
			DNI dni = usuario.getDni();
			if (dni.equals(us.getDni())) {
				esPosible = false;
			}

		}
		if (esPosible == true) {
			this.usuarios.add(us);
		}
		return esPosible;
	}

// mostrar libros que ha cogido usuario 
	public Set<Libro> mostrarLibrosPrestados(int ID, long ISBNLibro) {
		Set<Libro> librosPrestados = new HashSet<>();
		for (Libro libro : this.libros) {
			long numISBN = libro.getISBN();
			if (numISBN == ISBNLibro) {
				librosPrestados.add(libro);
			}
		}

		return librosPrestados;
	}

	@Override
	public String toString() {
		return "BibliotecaPublica [usuarios=" + usuarios + ", libros=" + libros + "]";
	}

}
