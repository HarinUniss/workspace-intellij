package it.harin.sistema_scrittura;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScritturaFile implements Writer{
    private String nomeFile;
    private Path percorso;
    private String contenuto;

    public ScritturaFile(String nomeFile) {
        this.nomeFile = nomeFile;
    }

    public ScritturaFile() {
        //non genera il file
    }


    @Override
    public void start() {
        System.out.println("Scrittura fattura : " + nomeFile + ".estensione" );
        percorso = Paths.get(nomeFile + ".estensione"); //creo percorso file e file
        contenuto = ""; //contenuto vuoto
    }

    @Override
    public void scriviLinea(String linea) {
        contenuto += linea + "%n";
    }

    @Override
    public void stop() {
        //formatto il contenuto e recupero i Bytes
        try{
            Files.write(percorso , String.format(contenuto).getBytes());
        }catch ( IOException e){
                System.out.println("Impossibile redigere file!!!");
        }

    }
}
