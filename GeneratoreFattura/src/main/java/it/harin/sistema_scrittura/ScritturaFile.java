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

    @Override
    public void start() {
        percorso = Paths.get(nomeFile);
        contenuto = "";
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
