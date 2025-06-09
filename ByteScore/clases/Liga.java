package clases;

import java.util.Date;

/**
 * Clase hija de Competicion, representa las competiciones de tipo liga.
 * @author Denys y Claudia
 * @see Competicion
 */
public class Liga extends Competicion {
    /**
     * Constructor por defecto.
     */
    public Liga() {
        super();
    }
    
    /**
     * Constructor comun.
     * @param nombre nombre de la competicion
     * @param juego juego de la competicion
     * @param fRealizacion fecha de la competicion
     */
    public Liga(String nombre, Juego juego, Date fRealizacion) {
        super(nombre, juego, fRealizacion);
    }

    @Override
    public String getTipo() {
        return "Liga";
    }
}
