package it.harin.web;

import it.harin.Cliente;
import it.harin.Fattura;
import it.harin.prodotti.Frigo;
import it.harin.prodotti.Prodotto;
import it.harin.prodotti.Tv;
import it.harin.sistema_scrittura.Writer;
import it.harin.sistema_spedizione.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FatturaServlet  extends HttpServlet {
    //creo una lista di prodotti
    List<Prodotto> listaProdotti = new ArrayList<Prodotto>();
    /**
     * Metodo che andrá a richiamare jetty quando istanzierá un oggetto di tipo HttpServlet.
     * Da utilizzare una volta all'inizio in modo da inizzializzare il contesto.
     * verra utilizzato ler gestire la lista dei prodotti disponibili.
     *
     * @throws ServletException
     *
     */
    @Override
    public void init() throws ServletException {
        //riempio la lista prodotti
        listaProdotti.add( new Prodotto("Mocca HD7866/61", "Due tazze", 29.99) );
        listaProdotti.add( new Tv("TV Samsung UE49MU6292", "Smart TV LED curva 49\"", 599, 49, "LED"));
        listaProdotti.add( new Frigo("BEKO TSE 1042 F", "Congelatore BEKO 130L - Classe A+ - bianco", 369, 130, false));

    }


    /**
     * GET  é un verbo HTTP.
     * significa "dammi questa pagina"
     * @name doGet serve a gestire i messaggi di tipo GET
     * esistono altri verdi in HTTP come PUT o POST
     * @param richiesta
     * @param risposta
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest richiesta, HttpServletResponse risposta) throws ServletException, IOException {
        risposta.setContentType("text/html");   //questa riga precisa che utilizzeremo del HTML per le nostre risposte


        if (richiesta.getQueryString() == null) //se non ci sono dei parametri mostra il Formulario
            displayFormulario(risposta);
        else    //altrimenti fornisci la fattura
            displayFattura(richiesta, risposta);
    }

    private void displayFormulario(HttpServletResponse risposta) throws IOException {

        //stampo tutti i prodotti nella lista
        for ( int i = 0; i <  listaProdotti.size() ; i++){
            Prodotto prodotto = listaProdotti.get(i);
            //stampo prodotto
            risposta.getWriter().println( "<b>" + i + " - " + prodotto.getNome() + "</b> : " + prodotto.getPrezzo() + "<br/>" + prodotto.getDescrizione() + "<br/><br/>");
        }

        //scrivo il resto del formulario
        String form =
                "<form action=\"fattura\">" +
                        "<b>Nome completo :</b> <input name=\"nomeCompleto\"/><br/>" +
                        "<b>Indirizzo :</b> <input name=\"indirizzo\"/><br/><br/>" +
                        "<b>Tipo Spedizione :</b> <br/>" +
                        "Consegna Domicilio: <input type=\"radio\" name=\"deliveryMode\" value=\"consegnaDomicilio\"/><br/>" +
                        "Consegna Rapida : <input type=\"radio\" name=\"deliveryMode\" value=\"consegnaRapida\"/><br/>" +
                        "Punto Ritiro : <input type=\"radio\" name=\"deliveryMode\" value=\"puntoRitiro\"/><br/>" +
                        "Ritiro Negozio: <input type=\"radio\" name=\"deliveryMode\" value=\"ritiroNegozio\"/><br/>" +
                        "<b>Informazione Spedizione</b> (per punto di ritiro) : <input name=\"deliveryInfo\"/><br/><br/>" +
                        "<b>Lista prodotti </b> (prodotto:quantitá, un prodotto per linea) : <br/>" +
                        "<textarea name=\"listaProdotti\"></textarea><br/>" +
                        "<input type=\"submit\"/>" +
                        "</form>";

        risposta.getWriter().println(form); //stampo formulario


    }

    private void displayFattura(HttpServletRequest richiesta, HttpServletResponse risposta) {
        Map<String, String> parametri = splitParametri( richiesta.getQueryString());        //creo la Map con parametri e valori
        Cliente cliente = new Cliente( parametri.get("nomeCompleto")  , parametri.get("indirizzo")); //inserisco il valore del  parametro che ha come chiave "nomeCompleto" e "indirizzo"

        Spedizione spedizione = null;   //creo un oggetto interfaccia spedizioen e la specifico successivamente

        switch ( parametri.get("deliveryMode")) {   //verifico il valore della chiave e specializzo la spedizione
            case "consegnaDomicilio" :
                spedizione = new ConsegnaDomicilio();
                break;
            case "consegnaRapida" :
                spedizione = new ConsegnaRapida( parametri.get("deliveryInfo") );   //in questo caso devo mettere come argomento al metodo una stringa contenente la regione
                break;
            case "puntoRitiro" :
                spedizione = new PuntoRitiro( Integer.parseInt( parametri.get("deliveryInfo")) ); //in questo caso devo mettere come argomento al metodo un int che contenga il numero di punto ritiro
                break;
            case "ritiroNegozio" :
                spedizione = new RitiroInNegozio();
                break;
        }

        //Letti i dati vado a creare la nostra fattura

        Fattura fattura = new Fattura( cliente , spedizione);
        //recupero quantitá di ogni tipo di articolo
        String[] parametriProdotti = parametri.get("listaProdotti").split("%0D%0A"); //separo l'unica scritta tagliando dove sono andato a capo con "%0D%0A" che in HTTP vuol dire accapo
        for (String each : parametriProdotti){
            String[] productAndQuantity = each.split("%3A"); //in HTTP "%3A" sta per i due punti:
            Prodotto prodotto = listaProdotti.get(Integer.parseInt(productAndQuantity[0])); // inserisco numero prodotto
            Integer qunatita = Integer.parseInt(productAndQuantity[1]);
            fattura.addProdotto(prodotto , qunatita);
        }


        //creo un writer dedicato
        fattura.genera(new Writer() {
            @Override
            public void start() {

            }

            @Override
            public void scriviLinea(String linea) {
                try{
                    risposta.getWriter().println("<br/>" + linea);
                }catch (IOException e ) {
                    e.printStackTrace();
                }

            }

            @Override
            public void stop() {
            }
        });



    }



    public Map<String, String> splitParametri(String stringaRichiesta) {

        String[] blocchi = stringaRichiesta.split("&") ; // creo un array con un parametro e valore per riga

        Map<String, String> parametri = new HashMap<>();    // Map che conterá i parametri e i valori

        for (String each : blocchi) {   //
            String[] keyAndValue = each.split("="); //chiave e valore splittati e messi in un array di stringhe temporanei
            if (keyAndValue.length == 2)    //se la dimensione e 2 vuol dire che é stato splittato bene
                parametri.put(keyAndValue[0], keyAndValue[1]);  //prendo chiave e valore e li metto nella Map contenente i parametri
        }
        return parametri;   // ritorno la map dei parametri

    }



}
