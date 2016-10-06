package br.com.caelum.leilao.servico;

import static org.junit.Assert.*;

import org.junit.Test;

public class MatematicaMalucaTest {
	@Test
	public void testaNumeroMaiorQueTrinta() {
		MatematicaMaluca m = new MatematicaMaluca();
		assertEquals(124, m.contaMaluca(31));
	}

	@Test
	public void testaNumeroTrinta() {
		MatematicaMaluca m = new MatematicaMaluca();
		assertEquals(90, m.contaMaluca(30));
	}

	@Test
	public void testaNumeroMenorQueTrintaMaiorQueDez() {
		MatematicaMaluca m = new MatematicaMaluca();
		assertEquals(60, m.contaMaluca(20));
	}

	@Test
	public void testaNumeroDez() {
		MatematicaMaluca m = new MatematicaMaluca();
		assertEquals(20, m.contaMaluca(10));
	}

	@Test
	public void testaNumeroMenorQueDez() {
		MatematicaMaluca m = new MatematicaMaluca();
		assertEquals(10, m.contaMaluca(5));
	}
}
