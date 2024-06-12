package biblioteca;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;

public class Libro {

	private long ISBN;
	private int anio;
	private final static int ANIO_MAX_ANTIGUEDAD = 30;

	private String autor;
	private String titulo;
	private Set<Tema> temas;

	public Libro(long codigo, int anio, String autor, String titulo) throws Exception {
		this.ISBN = codigo;
		setAnio(anio);
		setAutor(autor);
		this.titulo = titulo;
		this.temas = new HashSet<Tema>();
	}

	public String getTema(Tema t) {
		String tema;

		switch (t) {
		case AMOR_Y_ROMANCE:
			tema = " amor y romance";
			break;
		case AVENTURA_Y_ACCION:
			tema = " aventura y accion";
			break;
		case MISTERIO:
			tema = " misterio";
			break;
		case FANTASIA:
			tema = "fantasia";
			break;
		case CIENCIA_Y_FICCION:
			tema = " cienciay ficcion";
			break;
		case DESARROLLO_PERSONAL_Y_AUTOAYUDA:
			tema = " desarollo y autoayuda";
			break;
		case DRAMA:
			tema = " drama";
			break;
		case HUMOR_Y_SATIRA:
			tema = " humor y satira";
			break;
		case HISTORIA_y_EVOLUCION:
			tema = " historia y evolucion";
			break;
		default:
			tema = "INDEFINIDO";
		}
		return tema;
	}

	public String getTemas() {
		String resultado = "";
		for (Tema tema : this.temas) {
			resultado += getTema(tema) + ", ";
		}
		return resultado;
	}

	public String getAutor() {
		return autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public long getISBN() {
		return ISBN;
	}

	public int getANIO_MAX_ANTIGUEDAD() {
		return ANIO_MAX_ANTIGUEDAD;
	}

	
	public boolean setAnio(int anio) throws IllegalArgumentException {
		boolean valido = false;
		if (Year.now().getValue() - anio <= ANIO_MAX_ANTIGUEDAD) {
			valido = true;
			this.anio = anio;
		} else {
			throw new IllegalArgumentException("libro demciado antiguo");

		}
		return valido;
	}

	public boolean comprobarAnio() {
		boolean valido = false;

		if (Year.now().getValue() - anio <= ANIO_MAX_ANTIGUEDAD) {
			valido = true;
		}
		return valido;
	}

	public int getAnio() {
		return this.anio;
	}

// para comprobar que haya al menos un nombre y un apellido
	public void setAutor(String Autor) throws Exception {

		for (Character caracter : Autor.toCharArray()) {
			if (!Character.isAlphabetic(caracter) && caracter != ' ') {

				Exception e = new Exception(" Nombres y apellidos son letras");
				throw e;
			}

		}
		this.autor = Autor;
	}

	public void aniadirTema(Tema t) {
		this.temas.add(t);

	}

	@Override
	public String toString() {
		return "Libro [ISBN=" + ISBN + ", anio=" + anio + ", autor=" + autor + ", titulo=" + titulo + ", temas=" + temas
				+ "]";
	}

}
