package it.harin.sistema_spedizione;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PuntoRitiroTest {

    @Test
    public void
    Give_PuntoRitiroDa1a22_When_RichiestaPrezoPuntoRitiro_ThenGet_0gratis(){
        Spedizione spedizione = new PuntoRitiro(20);
        assertEquals( 0, spedizione.getPrezzo() ,0.01); //0.01 é per precisare che controllo fino a 2 cifre dopo la virgola
    }

    @Test
    public void
    Give_PuntoRitiroDa23a47_When_RichiestaPrezoPuntoRitiro_ThenGet_2e99(){
        Spedizione spedizione = new PuntoRitiro(40);
        assertEquals( 2.99, spedizione.getPrezzo() ,0.01); //0.01 é per precisare che controllo fino a 2 cifre dopo la virgola
    }

    @Test
    public void
    Give_PuntoRitiroDa48inPoi_When_RichiestaPrezoPuntoRitiro_ThenGet_4e99(){
        Spedizione spedizione = new PuntoRitiro(90);
        assertEquals( 4.99, spedizione.getPrezzo() ,0.01); //0.01 é per precisare che controllo fino a 2 cifre dopo la virgola
    }

}