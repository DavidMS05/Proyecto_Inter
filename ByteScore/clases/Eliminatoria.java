package clases;

import java.util.ArrayList;
import java.util.Date;

/**
 * Clase hija de Competicion, representa las competiciones de tipo eliminatoria.
 * @author Denys y Claudia
 * @version 1.1
 * @see Competicion
 */
public class Eliminatoria extends Competicion {
    private ArrayList<Compite_E> equipos;

    /**
     * Constructor por defecto.
     */
    public Eliminatoria() {
        super();
        equipos = new ArrayList<Compite_E>();
    }
    
    /**
     * Constructor comun.
     * @param nombre nombre de la competicion
     * @param juego juego de la competicion
     * @param fRealizacion fecha de la competicion
     * @param premio premio de la competicion
     * @param equipos equipos participantes
     */
    public Eliminatoria(String nombre, Juego juego, Date fRealizacion, Premio premio, ArrayList<Compite_E> equipos) {
        super(nombre, juego, fRealizacion, premio);
        this.equipos = equipos;
    }

    /**
     * Getter de equipos participantes.
     * @return ArrayList de Compite_E
     * @see Compite_E
     */
    public ArrayList<Compite_E> getEquipos() {
        return equipos;
    }
    /**
     * Setter de equipos participantes.
     * @param equipos nuevo equipos
     * @see Compite_E
     */
    public void setEquipos(ArrayList<Compite_E> equipos) {
        this.equipos = equipos;
    }

    /**
     * addEquipo
     * Añade un equipo dado al ArrayList equipos mediante una objeto nuevo de Compite_E
     * @param equipo un objeto de la clase Equipo
     * @param numRonda un entero que representa la ronda en la que se ha eliminado el equipo
     * @return booleano (true si se ha insertado con exito, false si se ha producido un error)
     * @since 1.0
     * @see Equipo
     * @see Compite_E
     */
    public boolean addEquipo(Equipo equipo, int numRonda) {
        boolean aux = true;
        try {
            this.equipos.add(new Compite_E(equipo, numRonda, this));
        } catch (Exception e) {
            aux = false;
        }
        return aux;
    }
    @Override
    public String getTipo() {
        return "Eliminatoria";
    }
}
