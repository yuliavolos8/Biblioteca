package testJunitTelefono;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import biblioteca.Telefono;

class TelefonoTest {

	@Test
	void testsetNumeroValido() {
		Telefono telefono = new Telefono();
		try {
			telefono.setNumero("632074700");
			assertEquals("632074700", telefono.getNumero());
		} catch (Exception e) {
			fail("no deberia lanzar error");
		}
	}

	@Test
	void testsetNumeroInvalido() {
		Telefono telefono = new Telefono();

		assertThrows(Exception.class, () -> telefono.setNumero("63207470"));

	}

}
