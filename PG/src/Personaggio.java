import java.util.ArrayList;
import java.util.List;

public class Personaggio {

    private String nome;
    private double altezza;
    private String razza;
    private String classe;
    private List<String> inventario = new ArrayList<String>();

    //costruttore senza parametri che c'é anche di base
    public Personaggio( ){
        this.altezza = 1.60;
        this.razza = "Umano";
        this.classe = "Guerriero";
    }

    //costruttore con 3 parametri
    public Personaggio( double altezza , String razza , String classe){
        this.altezza = altezza;
        this.razza = razza;
        this.classe = classe;
    }


    //getters

    public double getAltezza(){
        return altezza;
    }

    public String getNome() {
        return nome;
    }

    public String getRazza() {
        return razza;
    }

    public String getClasse() {
        return classe;
    }

    public List<String> getInventario() {
        return inventario;
    }

    //setters

    public void setNome( String nome){
        this.nome = nome;
    }

    public void setAltezza(double altezza) {
        this.altezza = altezza;
    }

    public void setRazza(String razza) {
        this.razza = razza;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    //non é molto utile serve un metodo add
    public void setInventario(List<String> inventario) {
        this.inventario = inventario;
    }

    public void addInventario( String oggetto ){
        this.inventario.add( oggetto );
    }


}
