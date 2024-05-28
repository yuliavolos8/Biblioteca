package biblioteca;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FechaCopiaMain {
	private LocalDate fecha;

	public FechaCopiaMain(int dia, int mes, int anio) {
		this.fecha = LocalDate.of(anio, mes, dia);
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(int dia, int mes, int anio) {
		this.fecha = LocalDate.of(anio, mes, dia);
	}

	@Override
	public String toString() {
		return fecha.toString();
	}

	public static void main(String[] args) {
		Fecha miFecha = new Fecha(2, 3, 2024);
		System.out.println("Fecha: " + miFecha);
	}

	public Fecha devolverLibros(int cantMeses) {
		LocalDate fechaHoy = LocalDate.now(); // Obtener la fecha actual
		int mesDevolucion = fechaHoy.getMonthValue() + cantMeses; // Calcular el próximo mes
		int anio = fechaHoy.getYear();

		if (mesDevolucion == 13) {
			mesDevolucion = 1;
			anio++;
		}

		return new Fecha(fechaHoy.getDayOfMonth(), mesDevolucion, anio);
	}

}
