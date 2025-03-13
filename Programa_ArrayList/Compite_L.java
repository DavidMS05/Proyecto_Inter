/**
 * Autor: Denys y Claudia
 */

import java.util.Date;

public class Compite_L {
    private Equipo equipo;
    private Liga competicion;
    private Date fFin;
    private int posicion;

    public Compite_L() {
        posicion = -1;
    }
    public Compite_L(Equipo equipo, Date fFin, int posicion, Liga competicion) {
        this.equipo = equipo;
        this.posicion = posicion;
        this.competicion = competicion;
    }

    public Equipo getEquipo() {
        return equipo;
    }
    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
    public int getPosicion() {
        return posicion;
    }
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
    public Liga getCompeticion() {
        return competicion;
    }
    public void setCompeticion(Liga competicion) {
        this.competicion = competicion;
    }
    public Date getfFin() {
        return fFin;
    }
    public void setfFin(Date fFin) {
        this.fFin = fFin;
    }
}
