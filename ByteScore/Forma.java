package ByteScore;

/**
 * Clase que representa el estado de un jugador dentro de un equipo.
 * @author David
 * @version 0.9
 * @see Jugador
 */
public class Forma {
    private boolean capitan;
    private boolean titular;

    /**
     * Constructor comun.
     * @param capitan boolean
     * @param titular boolean
     */
    public Forma(boolean capitan, boolean titular) {
        this.capitan = capitan;
        this.titular = titular;
    }

    /**
     * Getter de si es capitan.
     * @return booleano
     */
    public boolean getCapitan() {
        return capitan;
    }
    /**
     * Getter de si es titular.
     * @return booleano
     */
    public boolean getTitular() {
        return titular;
    }
}
