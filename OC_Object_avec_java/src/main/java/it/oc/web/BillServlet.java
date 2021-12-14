package it.oc.web;

import it.oc.delivery_service.*;
import it.oc.gestionefatture.Customer;
import it.oc.gestionefatture.Fattura;
import it.oc.gestionefatture.oggetti.Fridge;
import it.oc.gestionefatture.oggetti.Product;
import it.oc.gestionefatture.oggetti.Television;
import it.oc.gestionefatture.sistema_scrittura.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillServlet extends HttpServlet {

    List<Product> products = new ArrayList<Product>();


    @Override//utilizzo init per caricare la lista degli articoli
    public void init() throws ServletException {
        Product cafe = new Product("Philips HD7866/61", "Philips SENSEO Quadrante, Noir - 1 ou 2 tasses", 79.99);
        Product tv = new Television("TV Samsung UE49MU6292", "Smart TV LED incurvée 49\"", 599, 49, "LED");
        Fridge fridge = new Fridge("BEKO TSE 1042 F", "Réfrigérateur BEKO 130L - Classe A+ - blanc", 189, 130, false);
        products.add(cafe);
        products.add(tv);
        products.add(fridge);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        //verifico se ci sono dei parametri nella stringa Html
        if (req.getQueryString() == null)// req.getQueryString() riceve i parametri che ci sono nella barra degli indirizzi
            displayFrom(resp); //se non ci sono visualizzo il questionario
        else
            displayBill( req , resp); //se ci sono genero la fattura
    }


    private void displayFrom(HttpServletResponse resp) throws IOException {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            resp.getWriter().println(
                    "<b>" + i + " - " + product.getName() + "</b> : " + product.getPrice() + "<br/>" +
                            product.getDescription() + "<br/><br/>");
        }
        String form =
                "<form action=\"bill\">" +
                        "<b>nom complet :</b> <input name=\"fullname\"/><br/>" +    //da notare i nomi dei parametri "fullname"
                        "<b>adresse :</b> <input name=\"address\"/><br/><br/>" +    // "address"
                        "<b>livraison :</b> <br/>" +
                        "à domicile : <input type=\"radio\" name=\"deliveryMode\" value=\"direct\"/><br/>" + //"deliveryMode" che ha vari valori
                        "express : <input type=\"radio\" name=\"deliveryMode\" value=\"express\"/><br/>" +
                        "point relais : <input type=\"radio\" name=\"deliveryMode\" value=\"relay\"/><br/>" +
                        "à retirer : <input type=\"radio\" name=\"deliveryMode\" value=\"takeAway\"/><br/>" +
                        "<b>Informations livraison</b> (relay et express) : <input name=\"deliveryInfo\"/><br/><br/>" + // "deliveryInfo"
                        "<b>liste produits </b> (produit:quantité, un produit par ligne) : <br/>" +
                        "<textarea name=\"products\"></textarea><br/>" +    // "products"
                        "<input type=\"submit\"/>" +    //bottone
                "</form>";
        resp.getWriter().println(form);
    }


    private void displayBill( HttpServletRequest req , HttpServletResponse resp ){     //metodo che permette la visualizzazione della fattura compilata
        Map<String, String> params = splitParameters(req.getQueryString()); // recupero i parametri tramite il metodo creato "splitParameters"
        Customer customer = new Customer(params.get("fullname") , params.get("address")); //creo una variabile di tipo Customer "Cliente"
        Delivery delivery = null;   //creo anche una variabile delivery "spedizione"


        //parte fighisima pe rspecializzare l'oggetto delivery *_*
        switch (params.get("deliveryMode")){    //creo uno switch per verificare il metodo di spedizione
            case "direct":
                delivery = new DirectDelivery();
                break;
            case "express":
                //in questo caso devo prendere anche un parametro in piu "deliveryInfo"
                delivery = new ExpressDelivery(params.get("deliveryInfo")); break;
            case "relay":
                //in questo caso devo prendere anche un parametro in piu "deliveryInfo" ma sottoforma di intero
                delivery = new RelayDelivery(Integer.parseInt( params.get("deliveryInfo") )); break;
            case "takeAway":
                delivery = new TakeAwayDelivery();
                break;
        }

        Fattura bill = new Fattura( customer , delivery); //inizzializzo fattura

        String[] productsParams = params.get("products").split("%0D%0A");   //separo i prodotti che nel URL son separati con %0D%0A
        for ( String each : productsParams ){
            String[] productAndQuantity = each.split( "%3A"); //separo prodotto e quantitá
            // Recupero il valore contenuto nella prima casella convertendolo in int , e lo uso per recuperare il prodotto corrispondente
            Product product = products.get(Integer.parseInt(productAndQuantity[0]));
            Integer quantity = Integer.parseInt(productAndQuantity[1]);//recupero la seconda casella e la converto in int per avere al quantitá
            bill.addProduct(product ,  quantity);//aggiungo il tutto alla Fattura
        }
        bill.generate(new Writer() {
            @Override
            public void start() {

            }

            @Override
            public void writeLine(String line) {
                try{
                    resp.getWriter().println("<br/>"+ line);
                } catch (IOException e ) {
                    e.printStackTrace();
                }

            }

            @Override
            public void stop() {

            }
        });



    }
    //creo un metodo che prende i parametri scritti nel form
    public Map<String, String> splitParameters(String queryString){
        String[] brutParams = queryString.split("&"); //taglio la stringa nei punti dove ci sono le "&" e salvo tutto nell'array brutParams
        Map<String, String> params = new HashMap<>(); //crero la Map che sara ritornata

        //faccio una for each che mi analizza ogni parametro e valore
        for( String each : brutParams){
            String[] keyAndValue = each.split("=");//separo parametro da valore e salvo nell'array keyAndValue

            if (keyAndValue.length == 2){ //se la dimensione é 2 vuol dire che c'é sia parametro che valore
                params.put( keyAndValue[0] , keyAndValue[1]); //inserisco nella Map che sara ritornata il valore
            }
        }
        return params; //ritorno la Map
    }




}
