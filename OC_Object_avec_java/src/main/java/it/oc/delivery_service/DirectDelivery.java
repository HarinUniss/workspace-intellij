package it.oc.delivery_service;

public class DirectDelivery implements Delivery{



    @Override
    public double getPrice() {
        return 4.99;
    }

    @Override
    public String getInfo() {
        return "Consegna a domicilio 4.99e";
    }
}
