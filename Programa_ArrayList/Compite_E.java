/**
 * Autor: Denys y Claudia
 */

public class Compite_E {
    private Equipo equipo;
    private Eliminatoria competicion;
    private int numRonda;

    public Compite_E() {
        numRonda = 0;
    }
    public Compite_E(Equipo equipo, int numRonda, Eliminatoria competicion) {
        this.equipo = equipo;
        this.numRonda = numRonda;
        this.competicion = competicion;
    }

    public Equipo getEquipo() {
        return equipo;
    }
    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
    public int getNumRonda() {
        return numRonda;
    }
    public void setNumRonda(int numRonda) {
        this.numRonda = numRonda;
    }
    public Eliminatoria getCompeticion() {
        return competicion;
    }
    public void setCompeticion(Eliminatoria competicion) {
        this.competicion = competicion;
    }
}
