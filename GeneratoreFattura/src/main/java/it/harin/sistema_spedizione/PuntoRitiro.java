package it.harin.sistema_spedizione;

public class PuntoRitiro implements Spedizione{

    private int numeroPuntoRitiro;

    public PuntoRitiro(int numeroPuntoRitiro) {
        this.numeroPuntoRitiro = numeroPuntoRitiro;
    }

    @Override
    public double getPrezzo() {
        if (numeroPuntoRitiro >= 1 && numeroPuntoRitiro <= 22){
            return 0;
        }else if(numeroPuntoRitiro >=23 && numeroPuntoRitiro <=47){
            return 2.99;
        }else {
            return 4.99;
        }
    }

    @Override
    public String getInfo() {
        return "Consegna nel punto ritiro numero" + numeroPuntoRitiro +" "+ getPrezzo() +"€";
    }
}
