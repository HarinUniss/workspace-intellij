package it.harin.web;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FatturaServletTest {

    @Test
    public void Given_dueParametri_When_splittoIParametri_Then_laMapSiRiempeCorrettamente() {
        FatturaServlet fatturaServlet = new FatturaServlet();
        Map<String, String> parametri = fatturaServlet.splitParametri("p1=v1&p2=v2");
        assertEquals(2, parametri.size());  //verifico se il metodo splitParametri() mi ha fornito 2 parametri
        assertEquals("v1", parametri.get("p1")); //verifico se v1 contiene il parametro 1
        assertEquals("v2", parametri.get("p2"));    //verifico se v2 contiene il parametro v2
    }
    @Test
    public void Given_UnParametroVuotoEUnoPieno_When_splittoIParametri_Then_laMapSiRiempeCorrettamenteConSoloSecondoParametro() {
        FatturaServlet fatturaServlet = new FatturaServlet();
        Map<String, String> parametri = fatturaServlet.splitParametri("p1=&p2=v2");
        assertEquals(1, parametri.size());  //dimensione 1
        assertEquals("v2", parametri.get("p2"));    //secondo parametro presente
    }
    @Test
    public void Given_NessunParametro_When_splittoIParametri_Then_MapRisultaVuota() {
        FatturaServlet fatturaServlet = new FatturaServlet();
        Map<String, String> parametri = fatturaServlet.splitParametri("test");
        assertEquals(0, parametri.size());  //map risulta vuota
    }

}