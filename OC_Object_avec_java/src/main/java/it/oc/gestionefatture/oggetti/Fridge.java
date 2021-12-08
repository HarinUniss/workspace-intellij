package it.oc.gestionefatture.oggetti;

public class Fridge extends Product{

    private int liter;  //capienza
    private boolean freezer;    //dotato di freezer?

    public Fridge(String name, String description, double price, int liter, boolean freezer) {
        super(name, description, price);    //parametri presi dalla classe madre
        this.liter = liter;
        this.freezer = freezer;
    }

    public int getLiter() {
        return liter;
    }

    public boolean isFreezer() {
        return freezer;
    }

}
