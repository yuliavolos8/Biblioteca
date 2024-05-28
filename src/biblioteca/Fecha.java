package biblioteca;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Fecha {
	private LocalDate fecha;

	public Fecha(int dia, int mes, int anio) {
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

		System.out.println(" Fecha actualizada" + miFecha.mesesPasados(14));

		Fecha f1 = new Fecha(13, 5, 2024);

		Fecha f2 = new Fecha(20, 5, 2024);
		long cant = calcularDiasTranscurridos(f1, f2);
		System.out.println(cant);

		Fecha siguiente = f2.AddDias(7);
		System.out.println(siguiente);

	}

	public Fecha mesesPasados(int cantMeses) {
		LocalDate fechaThis = this.getFecha();// Obtener la fecha actual
		int mesDevolucion = fechaThis.getMonthValue() + cantMeses; // Calcular la fecha despues de aniadir los meses
																	// parametro
		int anio = fechaThis.getYear();

		if (mesDevolucion >= 13) {
			anio += mesDevolucion / 12;
			mesDevolucion = mesDevolucion % 12;

		}

		return new Fecha(fechaThis.getDayOfMonth(), mesDevolucion, anio);
	}

	// calcular dias transcuridos en el caso que se tarde en devolver el libro
	public static long calcularDiasTranscurridos(Fecha fechaDevolucion, Fecha fechaActual) {
		LocalDate fecha1 = fechaDevolucion.getFecha();
		LocalDate fecha2 = fechaActual.getFecha();

		return ChronoUnit.DAYS.between(fecha1, fecha2);
	}

	public static Fecha fechaHoy() {
		Fecha fechaHoy = new Fecha(LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(),
				LocalDate.now().getYear());
		return fechaHoy;

	}

	public Fecha AddDias(long cantDias) {
		LocalDate fecha = this.getFecha();
		LocalDate fSiguiente = ChronoUnit.DAYS.addTo(fecha, cantDias);
		Fecha fechaSiguiente = new Fecha(fSiguiente.getDayOfMonth(), fSiguiente.getMonthValue(), fSiguiente.getYear());
		return fechaSiguiente;

	}
}
