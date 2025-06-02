package clases;

import java.util.ArrayList;
import java.util.Date;

/**
 * Clase hija de Competicion, representa las competiciones de tipo individual.
 * @author Denys y Claudia
 * @version 1.1
 * @see Competicion
 */
public class Individual extends Competicion {
    private ArrayList<Compite_I> jugadores;

    /**
     * Constructor por defecto.
     */
    public Individual() {
        super();
        jugadores = new ArrayList<Compite_I>();
    }
    
    /**
     * Constructor comun.
     * @param nombre nombre de la competicion
     * @param juego juego de la competicion
     * @param fRealizacion fecha de la competicion
     * @param premio premio de la competicion
     * @param jugadores jugadores participantes
     */
    public Individual(String nombre, Juego juego, Date fRealizacion, Premio premio, ArrayList<Compite_I> jugadores) {
        super(nombre, juego, fRealizacion, premio);
        this.jugadores = jugadores;
    }

    /**
     * Getter de jugadores participantes.
     * @return ArrayList de Compite_I
     * @see Compite_I
     */
    public ArrayList<Compite_I> getJugadores() {
        return jugadores;
    }
    /**
     * Setter de jugadores participantes
     * @param jugadores nuevos jugadores
     * @see Compite_I
     */
    public void setJugadores(ArrayList<Compite_I> jugadores) {
        this.jugadores = jugadores;
    }

    /**
     * addEquipo
     * Añade un jugador dado al ArrayList jugadores mediante una objeto nuevo de Compite_I
     * @param jugador un objeto de la clase Jugador
     * @param numRonda un entero que representa la ronda en la que se ha eliminado el jugador
     * @return booleano (true si se ha insertado con exito, false si se ha producido un error)
     * @since 1.0
     * @see Jugador
     * @see Compite_I
     */
    public boolean addJugador(Jugador jugador, int numRonda) {
        boolean aux = true;
        try {
            this.jugadores.add(new Compite_I(jugador, numRonda, this));
        } catch (Exception e) {
            aux = false;
        }
        return aux;
    }

    @Override
    public String getTipo() {
        return "Individual";
    }
}
