import stampaschermo.StampaSchermo;

public class Main {

    public static void main(String[] args) {

        //creo il primo personaggio con il costruttore vuoto
        Personaggio pg1 = new Personaggio();



        //creo personaggio con dei parametri
        Personaggio pg2 = new Personaggio( 1.80 , "Elfo" , "Arcere"  );

        System.out.println( "il persopnaggio pg1 é alto : " + pg1.getAltezza() );

        System.out.println( "il persopnaggio pg2 é alto : " + pg2.getAltezza() );


        //cerco il nome non settato di pg1
        System.out.println(pg1.getNome());
        //metto un nome a pg1
        pg1.setNome("Mario");
        System.out.println( pg1.getNome() );

        pg1.addInventario("melanzana");
        pg1.addInventario("cipolla");
        pg1.addInventario("pentola");
        pg1.addInventario("forchetta");

        System.out.println( pg1.getInventario() );

    }

}
