package ByteScore;

import java.util.Date;

/**
 * Resultado de un equipo de una competicion de liga.
 * @author Denys y Claudia
 * @version 1.1
 */
public class Compite_L implements Compite {
    private Equipo equipo;
    private Liga competicion;
    private Date fFin;
    private int posicion;

    /**
     * Constructor por defecto.
     */
    public Compite_L() {
        posicion = -1;
    }
    /**
     * Constructor usado en casos especificos, no se usa de normal.
     * @param equipo equipo participante
     */
    public Compite_L(Equipo equipo) {
        posicion = -1;
        this.equipo = equipo;
    }
    /**
     * Constructor comun.
     * @param equipo equipo participante
     * @param fFin fecha de finalizacion
     * @param posicion su posicion
     * @param competicion la competicion
     */
    public Compite_L(Equipo equipo, Date fFin, int posicion, Liga competicion) {
        this.equipo = equipo;
        this.posicion = posicion;
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
     * Getter de posicion.
     * @return posicion
     */
    public int getPosicion() {
        return posicion;
    }
    /**
     * Setter de posicion.
     * @param posicion nueva posicion
     */
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
    /**
     * Getter de competicion.
     * @return Liga
     * @see Liga
     */
    public Liga getCompeticion() {
        return competicion;
    }
    /**
     * Setter de competicion.
     * @param competicion nueva competicion liga
     * @see Liga
     */
    public void setCompeticion(Liga competicion) {
        this.competicion = competicion;
    }
    /**
     * Getter de fecha.
     * @return fecha
     */
    public Date getfFin() {
        return fFin;
    }
    /**
     * Setter de fecha.
     * @param fFin nueva fecha
     */
    public void setfFin(Date fFin) {
        this.fFin = fFin;
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
