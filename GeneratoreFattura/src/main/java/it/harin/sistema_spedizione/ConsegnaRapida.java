package it.harin.sistema_spedizione;

public class ConsegnaRapida implements Spedizione{
    private String zona ;

    public ConsegnaRapida(String zona) {
        this.zona = zona;
    }

    @Override
    public double getPrezzo() {
        if(zona.equals("Italia-Continente")){
            return 6.99;
        }else if (zona.equals("Italia-Isole")){
            return 9.99;
        }else{
            return 19.99;
        }
    }

    @Override
    public String getInfo() {
        return "Consegna rapida a domicilio " + getPrezzo() +"€";
    }
}
