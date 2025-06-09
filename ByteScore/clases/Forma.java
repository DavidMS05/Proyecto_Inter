package clases;

/**
 * Clase que representa el estado de un jugador dentro de un equipo.
 * @author David
 * @see Jugador
 * @see Equipo
 */
public class Forma {
    private boolean capitan;
    private boolean titular;
    private Equipo equipo;
    private Jugador jugador;

    /**
     * Constructor por defecto.
     */
    public Forma() {
        equipo = null;
        jugador = null;
    }

    /**
     * Constructor común.
     * @param equipo el equipo
     * @param jugador el jugador
     * @param capitan booleano
     * @param titular booleano
     */
    public Forma(Equipo equipo, Jugador jugador, boolean capitan, boolean titular) {
        this.equipo = equipo;
        this.jugador = jugador;
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
     * Setter de capitan.
     * @param capitan booleano
     */
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

    /**
     * Setter de titular.
     * @param titular booleano
     */
    public void setTitular(boolean titular) {
        this.titular = titular;
    }

    /**
     * Getter de equipo.
     * @return equipo
     */
    public Equipo getEquipo() {
        return equipo;
    }

    /**
     * Setter de equipo.
     * @param equipo nuevo equipo
     */
    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    /**
     * Getter de jugador.
     * @return jugador
     */
    public Jugador getJugador() {
        return jugador;
    }

    /**
     * Setter de jugador.
     * @param jugador nuevo jugador
     */
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
}
