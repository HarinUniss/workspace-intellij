package it.oc.gestionefatture;


import it.oc.delivery_service.Delivery;
import it.oc.gestionefatture.oggetti.Product;
import it.oc.gestionefatture.sistema_scrittura.Writer;

import java.util.HashMap;
import java.util.Map;


public class Fattura {

    private Customer customer;
    private Map<Product, Integer > products = new HashMap<>();
    private Delivery delivery;


    public Fattura(Customer customer, Delivery delivery) {
        this.customer = customer;
        this.delivery = delivery;
    }





    /**
     * Agiunge un prodotto e la sua quantitá alla fattura
     * @param product  prodotto da aggiungere
     * @param quantity quantitá del prodotto
     */
    public void addProduct( Product product , Integer quantity ){
        this.products.put(product , quantity);
    }

    public Customer getCustomer() {
        return customer;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    private void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void generate( Writer writer){
        writer.start();
        writer.writeLine("HomeShop compagnie");
        writer.writeLine("1 Place Charles de Gaulle, 75008 Paris");
        writer.writeLine("");
        writer.writeLine("Facture à l'attention de : ");
        writer.writeLine(customer.getFullname());
        writer.writeLine(customer.getAddress());
        writer.writeLine("");
        writer.writeLine("Mode de livraison : " + delivery.getInfo());
        writer.writeLine("");
        writer.writeLine("Produits : ");
        writer.writeLine("-----------------------------------------------------");
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            writer.writeLine(product.getName() + " - " + product.getPrice() + " - " + quantity + " unité(s)");
            writer.writeLine(product.getDescription());
            writer.writeLine("");
        }
        writer.writeLine("Livraison : " + delivery.getPrice());
        writer.writeLine("-----------------------------------------------------");
        writer.writeLine("Total : " + this.getTotal());
        writer.stop();

    }

    public double getTotal(){
        double total = 0;
        //con Map accedo al metodo .entrySet()  che restituisce un set() che ha come elementi dei tipi Map.Entry
        for (Map.Entry<Product, Integer> entry : products.entrySet()){
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            total += product.getPrice() * quantity;
        }
        total += delivery.getPrice();
        return total;

    }

}
