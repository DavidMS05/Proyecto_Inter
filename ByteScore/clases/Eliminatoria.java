package clases;

import java.util.Date;

/**
 * Clase hija de Competicion, representa las competiciones de tipo eliminatoria.
 * @author Denys y Claudia
 * @see Competicion
 */
public class Eliminatoria extends Competicion {
    /**
     * Constructor por defecto.
     */
    public Eliminatoria() {
        super();
    }
    
    /**
     * Constructor común.
     * @param nombre nombre de la competicion
     * @param juego juego de la competicion
     * @param fRealizacion fecha de la competicion
     */
    public Eliminatoria(String nombre, Juego juego, Date fRealizacion) {
        super(nombre, juego, fRealizacion);
    }

    @Override
    public String getTipo() {
        return "Eliminatoria";
    }
}
