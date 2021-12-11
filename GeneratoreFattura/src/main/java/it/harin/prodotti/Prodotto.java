package it.harin.prodotti;

import it.harin.Fattura;

public class Prodotto {

    private String nome;
    private String descrizione;
    private double prezzo;

    public Prodotto(String nome, String descrizione, double prezzo) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
    }


    /**
     * Vedere nome , presso e descrizione completa di un prodotto
     *
     */
    public void visualizzaProdotto( ){
        System.out.println( String.format(getNome() + " : " + getPrezzo() + "%n" + getDescrizione()) ); // "%n" lo utilizzeró per capire quando devo andare accapo , avrei potuto usare un altro simbolo o combinazione di simboli
    }

    /**
     * Aggiunge articolo e quantitá alla fattura
     * @param fattura
     * @param quantita
     */
    public void compra(Fattura fattura, Integer quantita ){

    }



    //metodi base
    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

}
