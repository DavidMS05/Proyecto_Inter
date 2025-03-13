/**
 * Autor: Denys y Claudia
 */

public class Compite_I {
    private Jugador jugador;
    private Individual competicion;
    private int numRonda;

    public Compite_I() {
        numRonda = 0;
    }
    public Compite_I(Jugador jugador, int numRonda, Individual competicion) {
        this.jugador = jugador;
        this.numRonda = numRonda;
        this.competicion = competicion;
    }

    public Jugador getJugador() {
        return jugador;
    }
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
    public int getNumRonda() {
        return numRonda;
    }
    public void setNumRonda(int numRonda) {
        this.numRonda = numRonda;
    }
    public Individual getCompeticion() {
        return competicion;
    }
    public void setCompeticion(Individual competicion) {
        this.competicion = competicion;
    }
}
