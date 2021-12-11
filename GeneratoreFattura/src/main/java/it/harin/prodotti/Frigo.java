package it.harin.prodotti;

public class Frigo extends Prodotto{

    private int litri;  //capienza
    private boolean freezer;    //dotato di freezer?

    public Frigo(String name, String descrizione, double prezzo, int litri, boolean freezer) {
        super(name, descrizione, prezzo);    //parametri presi dalla classe madre
        this.litri = litri;
        this.freezer = freezer;
    }

    public int getLiter() {
        return litri;
    }

    /**
     * Verifico se c'é il freezer
     * @return ritorno un boleano
     */
    public boolean isFreezer() {
        return freezer;
    }

}
