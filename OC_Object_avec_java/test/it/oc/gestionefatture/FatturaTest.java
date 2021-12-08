package it.oc.gestionefatture;

import it.oc.delivery_service.Delivery;
import it.oc.delivery_service.RelayDelivery;
import it.oc.gestionefatture.oggetti.Fridge;
import it.oc.gestionefatture.oggetti.Product;
import it.oc.gestionefatture.oggetti.Television;
import it.oc.gestionefatture.sistema_scrittura.Writer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FatturaTest {
    private String output;
    //creo una variabile di tipo Writer che al suo interno avrá dei metodi implementabili e personalizzati per questa classe
    //inoltre visto che writerMock é un membro di FatturaTest puó accedere al suo parametro interno output anche se privato
    private Writer writerMock = new Writer() {
        @Override
        public void start() {
            output = "";    //all'avvio svuotiamo la variabile output
        }

        @Override
        public void writeLine(String line) {
            output += line + "%n";  //ad ogni linea aggiunta aggiungiamo alla fine  "%n" per poter utilizzare String.format e nadare accapo ogni volta che trovo %n
        }

        @Override
        public void stop() {

        }
    };

    private Product cafe = new Product("Philips HD7866/61", "Philips SENSEO Quadrante, Noir - 1 ou 2 tasses", 79.99);
    private Product tv = new Television("TV Samsung UE49MU6292", "Smart TV LED incurvée 49\"", 599, 49, "LED");
    private Fridge fridge = new Fridge("BEKO TSE 1042 F", "Réfrigérateur BEKO 130L - Classe A+ - blanc", 189, 130, false);
    private Customer customer = new Customer("Juste Leblanc", "19 rue Germain Pilon, Paris");
    private Delivery lowCostRelayDelivery = new RelayDelivery(27);

    @Test
    public void Given_2productsAndDelivery_When_generatingBill_Then_getGoodLineNumber() {
        Fattura bill = new Fattura(customer, lowCostRelayDelivery);
        bill.addProduct(cafe, 1);
        bill.addProduct(tv, 1);
        bill.generate(writerMock);
        int lineNumber = output.split("%n").length;
        assertEquals(20, lineNumber);
    }

    @Test
    public void Given_3productsAndDelivery_When_generatingBill_Then_getGoodTotal() {
        Fattura bill = new Fattura(customer, lowCostRelayDelivery);
        bill.addProduct(cafe, 1);
        bill.addProduct(tv, 1);
        bill.addProduct(fridge, 1);
        assertEquals(870.98, bill.getTotal(), 0.01);
    }


}