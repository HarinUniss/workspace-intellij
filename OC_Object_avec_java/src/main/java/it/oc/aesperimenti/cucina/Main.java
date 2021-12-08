package it.oc.aesperimenti.cucina;

public class Main {

    public static void main(String[] args) {

        Forno grandeForno = new Forno(260 , 55);

        Alimento cake = new Alimento( "cake" , false);

        cake.mangiare();

        grandeForno.cucinare( cake , 150 , 600 );

        cake.mangiare();



    }
}
