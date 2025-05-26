package clases;

/**
 * Clase que representa el estado de un jugador dentro de un equipo.
 * @author David
 * @version 0.9
 * @see Jugador
 */
public class Forma {
    private boolean capitan;
    private boolean titular;
    private Equipo equipo;
    private Jugador jugador;

    public Forma() {}

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

    public void setCapitan(boolean capitan) {
        this.capitan = capitan;
    }

    /**
     * Getter de si es titular.
     * @return booleano
     */
    public boolean getTitular() {
        return titular;
    }

    public void setTitular(boolean titular) {
        this.titular = titular;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
}
