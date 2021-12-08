package it.oc.aesperimenti.cucina;

public class Forno {
    private int potenza;
    private int capacita;

    public Forno( int potenza , int capacita){
        setCapacita(capacita);
        setPotenza(potenza);
    }

    public void cucinare( Alimento alimento , int temperatura , int durata ) {
        System.out.println("Cucino l'alimento");
        System.out.println("Con una capacitá di " + getCapacita() + " litri");
        System.out.println("e una potenza di  " + getPotenza() + " gradi.");
        alimento.setForseCotto(true);
        System.out.println("");
    }

    public int getCapacita() {
        return capacita;
    }

    public int getPotenza() {
        return potenza;
    }

    private void setCapacita(int capacita) {
        this.capacita = capacita;
    }

    public void setPotenza(int potenza) {
        this.potenza = potenza;
    }
}
