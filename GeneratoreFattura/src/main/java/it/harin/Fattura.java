package it.harin;

import it.harin.prodotti.Prodotto;
import it.harin.sistema_scrittura.Writer;
import it.harin.sistema_spedizione.Spedizione;

import java.util.HashMap;
import java.util.Map;


public class Fattura {

    private Cliente cliente;
    private Map<Prodotto , Integer > listaProdotti = new HashMap<Prodotto , Integer>() ;  //genero una Map di prodotti
    private Spedizione tipoSpedizione;



    public Fattura(Cliente cliente , Spedizione tipoSpedizione ){
        this.cliente = cliente;
        this.tipoSpedizione = tipoSpedizione;
    }


    /**
     * Aggiunge un prodotto alla lista
     * @param prodotto
     * @param quantita
     */
    public void addProdotto(Prodotto prodotto, Integer quantita) {
        this.listaProdotti.put(prodotto, quantita);
    }

    /**
     * redige la fattura
     * @param writer oggetto per scrivrere la fattura
     */
    public void genera(Writer writer) {
        writer.start();
        writer.scriviLinea("HommeShop company");
        writer.scriviLinea("36 via gastone della vega , 402 Milano");
        writer.scriviLinea("");
        writer.scriviLinea("Fattura all'attenzione di : ");
        writer.scriviLinea(cliente.getNomeCompleto());
        writer.scriviLinea(cliente.getIndirizzo());
        writer.scriviLinea("");
        writer.scriviLinea("Tipo di spedizione : " + tipoSpedizione.getInfo());
        writer.scriviLinea("");
        writer.scriviLinea("Prodotti : ");
        writer.scriviLinea("----------------------------------------------------------");

        for(Map.Entry<Prodotto, Integer> entry : listaProdotti.entrySet() ){
            Prodotto prodotto = entry.getKey(); //prodotto conterra il prodotto con nome descrizione e prezzo
            Integer quantita = entry.getValue(); //quantitá prende il numero di prodotti

            writer.scriviLinea( prodotto.getNome() + " - " + prodotto.getPrezzo() + " - " + quantita + " unitá");
            writer.scriviLinea(prodotto.getDescrizione());
            writer.scriviLinea("");
        }
        writer.scriviLinea("");
        writer.scriviLinea("Spedizione " + tipoSpedizione.getInfo() );
        writer.scriviLinea("Totale : " + this.getTotale());
        writer.scriviLinea("");
        writer.stop();

    }

    public double getTotale(){
        double totale = 0;
        for(Map.Entry<Prodotto, Integer> entry : listaProdotti.entrySet() ){
             totale += entry.getKey().getPrezzo() * entry.getValue();   //recupero prezzo prodotto e lo moltiplico per la quantitá
        }
        return totale;

    }


    // metodi getter
    public Cliente getCustomer() {
        return cliente;
    }

    public Map<Prodotto, Integer> getProducts() {
        return listaProdotti;
    }



}