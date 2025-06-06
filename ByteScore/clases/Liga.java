package clases;

//import java.util.ArrayList;
import java.util.Date;

/**
 * Clase hija de Competicion, representa las competiciones de tipo liga.
 * @author Denys y Claudia
 * @version 1.1
 * @see Liga
 */
public class Liga extends Competicion {
    //private ArrayList<Compite_L> equipos;

    /**
     * Constructor por defecto.
     */
    public Liga() {
        super();
        //equipos = new ArrayList<Compite_L>();
    }
    
    /**
     * Constructor comun.
     * @param nombre nombre de la competicion
     * @param juego juego de la competicion
     * @param fRealizacion fecha de la competicion
     * @param premio premio de la competicion
     * @param equipos equipos participantes
     */
    public Liga(String nombre, Juego juego, Date fRealizacion) {//, Premio premio, ArrayList<Compite_L> equipos) {
        super(nombre, juego, fRealizacion);//, premio);
        //this.equipos = equipos;
    }

    /**
     * Getter de equipos participantes.
     * @return ArrayList de Compite_L
     * @see Compite_L
     *//*
    public ArrayList<Compite_L> getEquipos() {
        return equipos;
    }*/
    /**
     * Setter de equipos participantes.
     * @param equipos nuevo equipos
     * @see Compite_L
     *//*
    public void setEquipos(ArrayList<Compite_L> equipos) {
        this.equipos = equipos;
    }*/

    /**
     * addEquipo
     * Añade un equipo dado al ArrayList equipos mediante una objeto nuevo de Compite_L
     * @param equipo un objeto de la clase Equipo
     * @param fFin una fecha Date que representa el final de la competicion
     * @param posicion un entero que representa la posicion en la que ha quedado el equipo
     * @return booleano (true si se ha insertado con exito, false si se ha producido un error)
     * @since 1.0
     * @see Equipo
     * @see Compite_L
     *//*
    public boolean addEquipo(Equipo equipo, Date fFin, int posicion) {
        boolean aux = true;
        try {
            this.equipos.add(new Compite_L(equipo, fFin, posicion, this));
        } catch (Exception e) {
            aux = false;
        }
        return aux;
    }*/

    @Override
    public String getTipo() {
        return "Liga";
    }
}
