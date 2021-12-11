package it.harin.sistema_spedizione;

public class RitiroInNegozio implements Spedizione{
    @Override
    public double getPrezzo() {
        return 0;
    }

    @Override
    public String getInfo() {
        return "Ritiro in negozio Gratis";
    }
}
