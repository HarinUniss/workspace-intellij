package it.harin.sistema_scrittura;

public interface Writer {
    /**
     * Fa partire il processo di scrittura
     */
    public void start();

    /**
     * Scrive su una linea
     * @param linea
     */
    public void scriviLinea( String linea);

    /**
     * Termina il processo di scrittura
     */
    public void stop();

}
