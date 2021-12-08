package it.oc.gestionefatture.oggetti;

public class Television extends Product{

    private int size;       // dimensione schermo
    private String slabType;    //tecnologia usata

    public Television(String name, String description, double price, int size, String slabType) {
        super(name, description, price);    //parametri ereditati dalla classe madre
        this.size = size;
        this.slabType = slabType;
    }

    public int getSize() {
        return size;
    }

    public String getSlabType() {
        return slabType;
    }
}
