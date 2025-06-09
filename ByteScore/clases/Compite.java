package clases;

/**
 * Una interfaz pequeña para poder usar parametrizados con Compite_E, Compite_L y Compite_I.
 * @author Denys (3D)
 * @see Compite_E
 * @see Compite_L
 * @see Compite_I
 */
public interface Compite {
    /**
     * comparar
     * Metodo no implementado para comparar los diferentes Compite con 2 cadenas de texto.
     * @param comp Nombre de una competicion
     * @param nom Nombre de un equipo o jugador
     * @return true si coinciden con los atributos del objeto, sino false
     * @since 1.0
     * @see Competicion
     * @see Equipo
     * @see Jugador
     */
    public boolean comparar(String comp, String nom);

    /**
     * Genera parte del archivo HTML al exportar desde Main.
     * @param nomCompeticion nombre de competición
     * @return codigo HTML en texto
     * @see Main#exportarHTML
     */
    public String htmlHeader(String nomCompeticion);

    /**
     * Genera el contenido de la tabla al exportar desde Main.
     * @return contenido de la tabla html en texto
     * @see Main#exportarHTML
     */
    public String htmlContent();

    /**
     * Devuelve la competición. Está aquí para asegurar que existe este getter al usar parametrizados.
     * @return la competición
     * @see Competicion
     */
    public Competicion getCompeticion();

    /**
     * Devuelve la letra del tipo de Compite (e/l/i).
     * @return letra en texto
     */
    public String letra();

    /**
     * Devuelve los datos a escribir a un archivo csv.
     * @return datos en texto y en formato csv
     * @deprecated
     */
    @Deprecated
    public String escribirCSV();
}
