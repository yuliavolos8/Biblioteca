package biblioteca;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Historial {

	private Usuario usuario;
	private ArrayList<Prestamo> prestamos;
	private int cantLibrosPrestados;
	private int cantLibrosDevueltos;
	private int MAX_LIBROS_PRESTAR = 3;
	private Fecha proximaFechaPrestar;

	public Historial(Usuario usuario) {
		this.usuario = usuario;
		this.prestamos = new ArrayList<>();
		this.cantLibrosPrestados = 0;
		this.cantLibrosDevueltos = 0;
		this.proximaFechaPrestar = Fecha.fechaHoy();
	}

	public static void main(String args[]) throws Exception {
		Usuario us1 = new Usuario("48768328W", "MARIA", " Gomez", "632074700");

		Libro libro1 = new Libro(1, 1999, " Maria Blanco", " Anochecer");
		Libro libro2 = new Libro(2, 1993, " Tomas Perez", " Anochecer");
		Libro libro3 = new Libro(4, 2003, " ANA fernandez", " Anochecer");

		Historial historial = new Historial(us1);
		Fecha devolver = historial.prestarLibro(libro3);
		Fecha f1 = new Fecha(1, 3, 2024);
		Prestamo prestamo = new Prestamo(us1, f1, libro1);

		long cant = historial.devolverLibro(libro1);
		historial.getCantLibrosPrestados();
		System.out.println("dias pasados" + cant);
		System.out.println(devolver);
		Login L = new Login();
		L.show();
	}

	public int getCantLibrosPrestados() {
		return cantLibrosPrestados;
	}

	// metodo para prestar libro , si es posible y indicarle la fecha cuando tenga
	// que devolverlo.
	public Fecha prestarLibro(Libro l) {
		Fecha fechaDevolver = null;
		Fecha fechaHoy = Fecha.fechaHoy();
		if (cantLibrosPrestados < MAX_LIBROS_PRESTAR
				&& Fecha.calcularDiasTranscurridos(proximaFechaPrestar, fechaHoy) >= 0) {

			Prestamo nuevo = new Prestamo(this.usuario, fechaHoy, l);
			this.prestamos.add(nuevo);
			cantLibrosPrestados++;
			fechaDevolver = nuevo.getDevolucion();
		}
		return fechaDevolver;
	}

// buscar un libro en concreto, si estaba prestado por este usuario y si todavia no se habia devuelto.
	public Prestamo libroPrestado(Libro l) {
		Prestamo p = null;
		for (Prestamo prestamo : this.prestamos) {
			Libro libro = prestamo.getLibro();
			if (libro.equals(l) && (!prestamo.getDevuelto())) {
				p = prestamo;
			}
		}
		return p;
	}

//calcular la fecha cuando se pueda coger el proximo prestamo
	public long devolverLibro(Libro libro) throws Exception {
		long diasTranscuridos = 0;
		Prestamo prestamo = this.libroPrestado(libro);

		if (prestamo != null) {

			Fecha f = prestamo.getDevolucion();
			Fecha hoy = Fecha.fechaHoy();
			diasTranscuridos = Fecha.calcularDiasTranscurridos(hoy, f);
			if (diasTranscuridos >= 0) {
				diasTranscuridos = 0;
			} else {
				diasTranscuridos = -diasTranscuridos;
				this.proximaFechaPrestar = hoy.AddDias(diasTranscuridos);
			}
		} else {
			Exception E = new Exception(" prestamo no encontrado");
			throw E;
		}
		cantLibrosPrestados--;
		cantLibrosDevueltos++;
		return diasTranscuridos;
	}

	@Override
	public String toString() {
		return "Historial [usuario=" + usuario + ", prestamos=" + prestamos + ", cantLibrosPrestados="
				+ cantLibrosPrestados + ", cantLibrosDevueltos=" + cantLibrosDevueltos + ", MAX_LIBROS_PRESTAR="
				+ MAX_LIBROS_PRESTAR + ", proximaFechaPrestar=" + proximaFechaPrestar + "]";
	}

}
