package it.oc.delivery_service.mezzo_trasporto;

public class Taxi implements MoyenDeLocomotion {

    @Override
    public void deplace(String adresse) {
        System.out.println("Je suis un taxi et je vais à l'adresse : " + adresse);
    }

}
