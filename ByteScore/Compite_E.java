package ByteScore;

/**
 * Resultado de un equipo de una competicion eliminatoria.
 * @author Denys y Claudia
 * @version 1.1
 */
public class Compite_E implements Compite {
    /*
     * Equipo participante.
     * @see Equipo
     */
    private Equipo equipo;
    /*
     * La competicion.
     * @see Eliminatoria
     */
    private Eliminatoria competicion;
    /* Numero de ronda en la que se ha quedado el equipo. */
    private int numRonda;

    /**
     * Constructor por defecto.
     */
    public Compite_E() {
        numRonda = 0;
    }
    /**
     * Constructor usado en casos especificos, no se usa de normal.
     * @param equipo equipo participante
     */
    public Compite_E(Equipo equipo) {
        numRonda = 0;
        this.equipo = equipo;
    }
    /**
     * Constructor comun.
     * @param equipo equipo participante
     * @param numRonda ronda en la que ha sido eliminado
     * @param competicion la competicion
     */
    public Compite_E(Equipo equipo, int numRonda, Eliminatoria competicion) {
        this.equipo = equipo;
        this.numRonda = numRonda;
        this.competicion = competicion;
    }

    /**
     * Getter de equipo.
     * @return Equipo
     * @see Equipo
     */
    public Equipo getEquipo() {
        return equipo;
    }
    /**
     * Setter de equipo.
     * @param equipo nuevo equipo
     * @see Equipo
     */
    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
    /**
     * Getter de numRonda.
     * @return numRonda
     */
    public int getNumRonda() {
        return numRonda;
    }
    /**
     * Setter de numronda.
     * @param numRonda nuevo numRonda
     */
    public void setNumRonda(int numRonda) {
        this.numRonda = numRonda;
    }
    /**
     * Getter de competicion.
     * @return Eliminatoria
     * @see Eliminatoria
     */
    public Eliminatoria getCompeticion() {
        return competicion;
    }
    /**
     * Setter de competicion.
     * @param competicion nueva competicion eliminatoria
     * @see Eliminatoria
     */
    public void setCompeticion(Eliminatoria competicion) {
        this.competicion = competicion;
    }

    /**
     * comparar
     * Metodo de la interfaz Compite, compara las cadenas pasadas con los nombres de competicion y equipo.
     * @param comp Nombre de la competicion.
     * @param nom Nombre del equipo.
     * @return true si coinciden, sino false.
     * @since 1.1
     * @see Compite
     * @see Competicion
     * @see Equipo
     */
    public boolean comparar(String comp, String nom) {
        boolean res = false;
        if (comp.equals(competicion.getNombre()) && nom.equals(equipo.getNombre()))
            res = true;
        return res;
    }
}
