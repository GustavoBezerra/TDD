package br.com.caelum.leilao.dominio;

import static org.junit.Assert.*;

import org.junit.Test;

public class LeilaoTest {
	
	@Test
    public void deveReceberUmLance() {
        Leilao leilao = new Leilao("Macbook Pro 15");
        assertEquals(0, leilao.getLances().size());

        leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000));

        assertEquals(1, leilao.getLances().size());
        assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
    }

    @Test
    public void deveReceberVariosLances() {
        Leilao leilao = new Leilao("Macbook Pro 15");
        leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000));
        leilao.propoe(new Lance(new Usuario("Steve Wozniak"), 3000));

        assertEquals(2, leilao.getLances().size());
        assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
        assertEquals(3000, leilao.getLances().get(1).getValor(), 0.00001);
    }
    
    @Test
    public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
        Leilao leilao = new Leilao("Macbook Pro 15");
        Usuario steveJobs = new Usuario("Steve Jobs");

        leilao.propoe(new Lance(steveJobs, 2000));
        leilao.propoe(new Lance(steveJobs, 3000));

        assertEquals(1, leilao.getLances().size());
        assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
    }
    
    @Test
    public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
        Leilao leilao = new Leilao("Macbook Pro 15");
        Usuario steveJobs = new Usuario("Steve Jobs");
        Usuario billGates = new Usuario("Bill Gates");

        leilao.propoe(new Lance(steveJobs, 2000));
        leilao.propoe(new Lance(billGates, 3000));
        leilao.propoe(new Lance(steveJobs, 4000));
        leilao.propoe(new Lance(billGates, 5000));
        leilao.propoe(new Lance(steveJobs, 6000));
        leilao.propoe(new Lance(billGates, 7000));
        leilao.propoe(new Lance(steveJobs, 8000));
        leilao.propoe(new Lance(billGates, 9000));
        leilao.propoe(new Lance(steveJobs, 10000));
        leilao.propoe(new Lance(billGates, 11000));

        // deve ser ignorado
        leilao.propoe(new Lance(steveJobs, 12000));

        assertEquals(10, leilao.getLances().size());
        int ultimo = leilao.getLances().size() - 1;
        Lance ultimoLance = leilao.getLances().get(ultimo);
        assertEquals(11000.0, ultimoLance.getValor(), 0.00001);
    }

    @Test
    public void deveDobrarLance(){
    	Usuario gustavo = new Usuario("gustavo");
    	Usuario maria= new Usuario("maria");
    	
    	Leilao l = new Leilao("PS3");
    	
    	assertEquals(true, l.getLances().isEmpty());
    	
    	l.propoe(new Lance(gustavo, 200.0));
    	assertEquals(1, l.getLances().size());
    	assertEquals(200.0, l.getLances().get(0).getValor(), 0.0001);
    	
    	l.propoe(new Lance(maria, 900.0));
    	assertEquals(2, l.getLances().size());
    	assertEquals(900.0, l.getLances().get(1).getValor(), 0.0001);
    	
    	l.propoe(new Lance(gustavo, 800.0));
    	l.propoe(new Lance(maria, 100.0));
    	
    	l.dobraLance(gustavo);
    	assertEquals(5, l.getLances().size());
    	assertEquals(200.0, l.getLances().get(0).getValor(), 0.0001);
    	assertEquals(900.0, l.getLances().get(1).getValor(), 0.0001);
    	assertEquals(800.0, l.getLances().get(2).getValor(), 0.0001);
    	assertEquals(100.0, l.getLances().get(3).getValor(), 0.0001);
    	assertEquals(1600.0, l.getLances().get(4).getValor(), 0.0001);    	
    }
    
    @Test
    public void naoDeveDobrarLance(){
    	Usuario gustavo = new Usuario("gustavo");
    	Usuario maria= new Usuario("maria");
    	
    	Leilao l = new Leilao("PS3");
    	l.propoe(new Lance(maria, 200.0));
    	assertEquals(1, l.getLances().size());
    	
    	l.dobraLance(gustavo);
    	assertEquals(1, l.getLances().size());
    	
    }
}
