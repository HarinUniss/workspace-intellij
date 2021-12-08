package it.oc.gestionefatture;

import it.oc.delivery_service.Delivery;
import it.oc.delivery_service.DirectDelivery;
import it.oc.delivery_service.RelayDelivery;
import it.oc.gestionefatture.oggetti.*;
import it.oc.gestionefatture.sistema_scrittura.FileWriter;
import it.oc.gestionefatture.sistema_scrittura.Writer;

public class Main {

    public static void main(String[] args) {

        Product cafe = new Product("Philips HD7866/61", "Philips SENSEO Quadrante, Noir - 1 ou 2 tasses", 79.99);
        Television tv = new Television("TV Samsung UE49MU6292", "Smart TV LED incurvée 49\"", 599, 49, "LED");
        Fridge fridge = new Fridge("BEKO TSE 1042 F", "Réfrigérateur BEKO 130L - Classe A+ - blanc", 189, 130, false);

        Customer customer = new Customer("Juste Leblanc", "19 rue Germain Pilon, Paris");


        Fattura fattura = new Fattura(customer , new RelayDelivery(27));
        fattura.addProduct(cafe, 1);
        fattura.addProduct(tv, 1);
        fattura.addProduct(fridge, 1);

        fattura.generate(new FileWriter("Fattura di Juste Leblanc"));

        //se voglio il risultato senza creare un fille mi basta gwnwrare un oggetto interno al main
        fattura.generate(new Writer() {
            @Override
            public void start() {

            }

            @Override
            public void writeLine(String line) {
                    System.out.println(line);

            }

            @Override
            public void stop() {

            }
        });


    }
}
