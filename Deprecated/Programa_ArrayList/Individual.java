/**
 * Autor: Denys y Claudia
 */

import java.util.ArrayList;
import java.util.Date;

public class Individual extends Competicion {
    private ArrayList<Compite_I> jugadores;

    public Individual() {
        super();
        jugadores = new ArrayList<Compite_I>();
    }
    public Individual(String nombre, Juego juego, Date fRealizacion, Premio premio, ArrayList<Compite_I> jugadores) {
        super(nombre, juego, fRealizacion, premio);
        this.jugadores = jugadores;
    }

    public ArrayList<Compite_I> getJugadores() {
        return jugadores;
    }
    public void setJugadores(ArrayList<Compite_I> jugadores) {
        this.jugadores = jugadores;
    }

    public boolean addJugador(Jugador jugador, int numRonda) {
        boolean aux = true;
        try {
            this.jugadores.add(new Compite_I(jugador, numRonda, this));
        } catch (Exception e) {
            aux = false;
        }
        return aux;
    }
}
