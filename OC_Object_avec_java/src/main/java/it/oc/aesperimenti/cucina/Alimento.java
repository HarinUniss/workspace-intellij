package it.oc.aesperimenti.cucina;

public class Alimento {

    private String nome;
    private boolean forseCotto;

    //costruttore 1
    public Alimento( ){
        setNome( "alimento" );
        setForseCotto( false);
    }

    //costruttore 2
    public Alimento( String nome, boolean forseCotto ){
        setNome( nome );
        setForseCotto( false);
    }

    public void mangiare() {
        if (getForseCotto()) {
            System.out.println("Miam miam questo alimento : " + getNome() + " é ben cotto");
        } else {
            System.out.println("Beeerk questo alimento : " + getNome() + " é crudo !");
        }
        System.out.println("");
    }

    public String getNome() {
        return nome;
    }

    public boolean getForseCotto() {
        return forseCotto;
    }

    public void setForseCotto(boolean forseCotto) {
        this.forseCotto = forseCotto;
    }

    private void setNome(String nome) {
        this.nome = nome;
    }
}
