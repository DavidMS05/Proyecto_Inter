package ByteScore;

/**
 * Una interfaz pequeña para poder usar parametrizados con Compite_E, Compite_L y Compite_I.
 * @author Denys Diachenko
 * @version 1.0
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
}
