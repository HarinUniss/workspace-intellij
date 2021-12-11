package it.harin.prodotti;

public class Tv extends Prodotto{

    private int dimensione;       // dimensione schermo
    private String tec;    //tecnologia usata

    public Tv(String nome, String descrizione, double prezzo, int dimensione, String tec) {
        super(nome, descrizione, prezzo);    //parametri ereditati dalla classe madre
        this.dimensione = dimensione;
        this.tec = tec;
    }

    public int getDimensione() {
        return dimensione;
    }

    public String getTec() {
        return tec;
    }

}
