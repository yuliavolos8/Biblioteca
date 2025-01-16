package biblioteca;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Prestamo {
	private Fecha fechaPrestamo;
	private Fecha fechaDevolucion;
	private Usuario usuario;
	private Libro libro;
	public final static int TIEMPO_PRESTAMO_MES = 1;
	private boolean devuelto;

	public Prestamo(Usuario us, Fecha f, Libro l) {
		this.usuario = us;
		this.fechaPrestamo = f;
		this.fechaDevolucion = f.mesesPasados(TIEMPO_PRESTAMO_MES);
		this.libro = l;
		this.devuelto = false;
	}

	public Fecha getFechaPrestamo() {
		return fechaPrestamo;
	}

	public Fecha getDevolucion() {
		return fechaDevolucion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Libro getLibro() {
		return libro;
	}

	public static int getTiempoPrestamoMes() {
		return TIEMPO_PRESTAMO_MES;
	}

	public Fecha getFechaDevolucion() {
		return fechaDevolucion;
	}

	public boolean getDevuelto() {
		return devuelto;
	}

}
