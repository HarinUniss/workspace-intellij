package it.oc.gestionefatture.sistema_scrittura;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileWriter  implements Writer{
    private String filename;
    private Path path;
    private String content;

    public FileWriter(String filename) {
        this.filename = filename;
    }

    @Override
    public void start() {
        path = Paths.get(filename);
        content = "";
    }

    @Override
    public void writeLine(String line) {
        content += line + "%n";
    }

    @Override
    public void stop() {
        //formatto content e recupero i Bytes
        try{
            Files.write(path , String.format(content).getBytes());
        }catch ( IOException e){
                System.out.println("Impossibile redigere la fattura");
        }

    }
}
