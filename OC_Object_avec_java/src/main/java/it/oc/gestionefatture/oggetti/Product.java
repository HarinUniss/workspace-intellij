package it.oc.gestionefatture.oggetti;


import it.oc.gestionefatture.Fattura;

public class Product {

    private String name;
    private String description;
    private double price;

    public Product(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }


    //metodi richiesti

    /**
     * Vedere descrizione completa prodotto
     */
    public void look(){
        System.out.println(String.format(getName() + " : " + getPrice() + "%n" + getDescription()));
    }

    /**
     * Aggiunge articolo e quantitá alla fattura
     * @param fattura
     * @param quantity
     */
    public void buy(Fattura fattura, Integer quantity ){

    }



    //metodi base
    private void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }



}
