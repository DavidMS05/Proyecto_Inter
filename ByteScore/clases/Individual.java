package clases;

import java.util.Date;

/**
 * Clase hija de Competicion, representa las competiciones de tipo individual.
 * @author Denys y Claudia
 * @see Competicion
 */
public class Individual extends Competicion {
    /**
     * Constructor por defecto.
     */
    public Individual() {
        super();
    }
    
    /**
     * Constructor comun.
     * @param nombre nombre de la competicion
     * @param juego juego de la competicion
     * @param fRealizacion fecha de la competicion
     */
    public Individual(String nombre, Juego juego, Date fRealizacion) {
        super(nombre, juego, fRealizacion);
    }

    @Override
    public String getTipo() {
        return "Individual";
    }
}
