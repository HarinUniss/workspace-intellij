package it.oc.delivery_service;

public class TakeAwayDelivery implements Delivery{
    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public String getInfo() {
        return "Ritiro in sede 0.00e";
    }
}
