package it.oc.gestionefatture.sistema_scrittura;

public interface Writer {
    /**
     * Fa partire il processo di scrittura
     */
    public void start();

    /**
     * Scrive su una linea
     * @param line
     */
    public void writeLine( String line);

    /**
     * Termina il processo di scrittura
     */
    public void stop();

}
