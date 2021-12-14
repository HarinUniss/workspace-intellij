package it.harin;

import it.harin.prodotti.Frigo;
import it.harin.prodotti.Prodotto;
import it.harin.prodotti.Tv;
import it.harin.sistema_scrittura.ScritturaFile;
import it.harin.sistema_spedizione.PuntoRitiro;
import it.harin.sistema_spedizione.Spedizione;

public class Main {


    public static void main(String[] args) {
        Prodotto caffettiera = new Prodotto("Mocca HD7866/61", "Due tazze", 29.99);
        Tv tv = new Tv("TV Samsung UE49MU6292", "Smart TV LED curva 49\"", 599, 49, "LED");
        Frigo frigo = new Frigo("BEKO TSE 1042 F", "Congelatore BEKO 130L - Classe A+ - bianco", 369, 130, false);

        Cliente cliente = new Cliente("Tizio di Prova" , "0 via dei matti , Milano");
        Spedizione tipoSpedizione = new PuntoRitiro(38);

        Fattura fattura = new Fattura( cliente , tipoSpedizione);

        fattura.addProdotto( caffettiera , 2);
        fattura.addProdotto( tv , 1);
        fattura.addProdotto( frigo  , 1);

        //genero fattura in un file
        fattura.genera( new ScritturaFile( "Fattura di " + cliente.getNomeCompleto() ) );

        //scrivo contenuto fattura anche nel terminale
        fattura.genera( new ScritturaFile() {

            @Override
            public void start() {
               // non serve
            }

            @Override
            public void scriviLinea(String linea) {
                System.out.println(linea); // é stato sufficiente modificare questo metodo
            }

            @Override
            public void stop() {
               // non serve
            }
        });

        // volendo avrei potuto fondere tute e due le operazioni in una

    }



}
