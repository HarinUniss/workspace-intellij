package it.harin.sistema_spedizione;

public class ConsegnaDomicilio  implements Spedizione {


    @Override
    public double getPrezzo() {
        return 4.99;
    }

    @Override
    public String getInfo() {
        return "Consegna a domicilio 4.99€";
    }
}
