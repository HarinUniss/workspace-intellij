package it.harin;

import it.harin.prodotti.Frigo;
import it.harin.prodotti.Prodotto;
import it.harin.prodotti.Tv;
import it.harin.sistema_spedizione.PuntoRitiro;
import it.harin.sistema_spedizione.Spedizione;

public class Main {


    public static void main(String[] args) {
        Prodotto caffettiera = new Prodotto("Mocca HD7866/61", "Due tazze", 29.99);
        Tv tv = new Tv("TV Samsung UE49MU6292", "Smart TV LED curva 49\"", 599, 49, "LED");
        Frigo frigo = new Frigo("BEKO TSE 1042 F", "Congelatore BEKO 130L - Classe A+ - bianco", 369, 130, false);

        Cliente cliente = new Cliente("Juste Leblanc", "19 rue Germain Pilon, Paris");
        Spedizione tipoSpedizione = new PuntoRitiro(38);

        Fattura fattura = new Fattura( cliente , tipoSpedizione);

        fattura.addProdotto( caffettiera , 2);
        fattura.addProdotto( tv , 1);
        fattura.addProdotto( frigo  , 1);
    }



}
