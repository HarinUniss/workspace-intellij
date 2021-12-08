package it.oc.delivery_service;

public class RelayDelivery implements Delivery{

    private int realayNumber;


    public RelayDelivery( int relayNumber) {
        this.realayNumber = relayNumber;
    }

    @Override
    public double getPrice() {
        if (realayNumber >= 1 && realayNumber <= 22){
            return 0;
        }else if(realayNumber >=23 && realayNumber <=47){
            return 2.99;
        }else {
            return 4.99;
        }

    }

    @Override
    public String getInfo() {
        return "Spedizione puntop ritiro " + getPrice() +"e";
    }
}
