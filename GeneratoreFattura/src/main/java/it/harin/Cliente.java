package it.harin;

public class Cliente {

    private String nomeCompleto;
    private String indirizzo;

    public Cliente(String nomeCompleto , String indirizzo ) {
        this.nomeCompleto = nomeCompleto;
        this.indirizzo = indirizzo;
    }


    //metodi base
    public String getIndirizzo() {
        return indirizzo;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setnomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }


}
