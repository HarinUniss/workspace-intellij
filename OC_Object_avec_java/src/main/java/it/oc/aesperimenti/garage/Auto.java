package it.oc.aesperimenti.garage;

import java.util.ArrayList;
import java.util.List;

public class Auto extends Veicolo {
    private int door;
    private int litrePer100km;
    private String motor;
    private List<String> options = new ArrayList<String>();

    public Auto(String modelName, String description, String manufacturer, int year, String color, int speed, int[] dimensions, int weight, int door, int litrePer100km, String motor, List<String> options) {
        //super prende i parametri non presenti direttamente in Auto dalla classe madre (ereditarietá)
        super(modelName, description, manufacturer, year, color, speed, dimensions, weight);
        //altri argomenti gia presenti in Auto
        this.door = door;
        this.litrePer100km = litrePer100km;
        this.motor = motor;
        this.options = options;
    }

    @Override
    public void start() {
        System.out.println("Sono " + modelName + " consumo " + litrePer100km + "ogni 100km e sto partendo !" );
    }

    @Override
    public void stop() {
        System.out.println("Sono " + modelName + " e spengo il motore " + motor);
    }

    public void startHeadlight() {
        System.out.println("Accendo i fari");
    }

    public void openTrunk() {
        System.out.println("Apro il cofano");
    }



    //getters

        public int getDoor() {
            return door;
        }

        public int getLitrePer100km() {
            return litrePer100km;
        }

        public String getMotor() {
            return motor;
        }

        public List<String> getOptions() {
            return options;
        }
}
