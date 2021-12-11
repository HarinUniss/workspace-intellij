package it.harin.sistema_spedizione;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsegnaRapidaTest {

    @Test
    public void
    Give_SpedizioneItaliaContinente_When_RichiestaPrezoConsegnaRapida_ThenGet_6e99(){
        Spedizione spedizione = new ConsegnaRapida("Italia-Continente");
        assertEquals( 6.99, spedizione.getPrezzo() ,0.01); //0.01 é per precisare che controllo fino a 2 cifre dopo la virgola
    }

    @Test
    public void
    Give_SpedizioneItaliaIsole_When_RichiestaPrezoConsegnaRapida_ThenGet_9e99(){
        Spedizione spedizione = new ConsegnaRapida("Italia-Isole");
        assertEquals(9.99, spedizione.getPrezzo() ,0.01); //0.01 é per precisare che controllo fino a 2 cifre dopo la virgola
    }

    @Test
    public void
    Give_SpedizioneEstero_When_RichiestaPrezoConsegnaRapida_ThenGet_19e99(){
        Spedizione spedizione = new ConsegnaRapida("");
        assertEquals( 19.99, spedizione.getPrezzo() , 0.01); //0.01 é per precisare che controllo fino a 2 cifre dopo la virgola
    }

}