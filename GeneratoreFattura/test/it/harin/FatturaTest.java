package it.harin;

import it.harin.prodotti.Frigo;
import it.harin.prodotti.Prodotto;
import it.harin.prodotti.Tv;
import it.harin.sistema_scrittura.Writer;
import it.harin.sistema_spedizione.PuntoRitiro;
import it.harin.sistema_spedizione.Spedizione;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FatturaTest {

    private String output;

    //vado a utilizzare il MOCK  di una classe
    //Consiste nel fare un'ereditarietà nel codice.
    //Cambiamo il comportamento di base della classe senza crearne una nuova.
    private Writer writerMock = new Writer() {

        @Override
        public void start() {
            output = ""; //inizzializzo la variabile String Output
            //NB che l'oggetto writerMock puo accedere al parametro output anche se privato
            //questo puo succedere perche writerMock é un membro di della classe FatturaTest
        }

        @Override
        public void scriviLinea(String linea) {
                output += linea + "%n"; //aggiungo alla String Output una linea e poi metto un %n che usero per segnare gli accapo
        }

        @Override
        public void stop() {
        }
    };


    //genero dei prodotti per il test
    Prodotto caffettiera = new Prodotto("Mocca HD7866/61", "Due tazze", 29.99);
    Tv tv = new Tv("TV Samsung UE49MU6292", "Smart TV LED curva 49\"", 599, 49, "LED");
    Frigo frigo = new Frigo("BEKO TSE 1042 F", "Congelatore BEKO 130L - Classe A+ - bianco", 369, 130, false);

    private Cliente cliente = new Cliente("Tizio di Prova" , "0 via dei matti , Milano");
    private Spedizione tipoSpedizione = new PuntoRitiro(38);

    @Test
    public void
    Give_3ProdottiMetodoSpedizione_When_vieneGenerataFattura_ThenGet_IlGiustoNumeroLinee(){

        //istanzio fattura cliente
        Fattura fattura = new Fattura(cliente , tipoSpedizione);
        //aggiungo articoli
        fattura.addProdotto(caffettiera, 1);
        fattura.addProdotto(frigo, 1);
        fattura.addProdotto(tv , 1);
        //genero fattura
        fattura.genera( writerMock );

        //conto il numero di linee che ci saranno nella stringa output
        int numeroLinee = output.split( "%n").length;   //separo il testo ogni volta che trovo "%n" e poi conto  la dimensione (ovvero il numero di stringhe generato da split() )

        assertEquals(23 , numeroLinee); // mi aspetto 25 linee
    }

    @Test
    public void
    Give_3ProdottiMetodoSpedizione_When_vieneGenerataFattura_ThenGet_IlGiustoTotale(){

        //istanzio fattura cliente
        Fattura fattura = new Fattura(cliente , tipoSpedizione);
        //aggiungo articoli
        fattura.addProdotto(caffettiera, 1);
        fattura.addProdotto(frigo, 1);
        fattura.addProdotto(tv , 1);

        assertEquals(997.99 , fattura.getTotale() ); // mi aspetto 25 linee
    }







}