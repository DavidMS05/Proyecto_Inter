/**
 * Autor: Denys y Claudia
 */

import java.util.ArrayList;
import java.util.Date;

public class Liga extends Competicion {
    private ArrayList<Compite_L> equipos;

    public Liga() {
        super();
        equipos = new ArrayList<Compite_L>();
    }
    public Liga(String nombre, Juego juego, Date fRealizacion, Premio premio, ArrayList<Compite_L> equipos) {
        super(nombre, juego, fRealizacion, premio);
        this.equipos = equipos;
    }

    public ArrayList<Compite_L> getEquipos() {
        return equipos;
    }
    public void setEquipos(ArrayList<Compite_L> equipos) {
        this.equipos = equipos;
    }

    public boolean addEquipo(Equipo equipo, Date fFin, int posicion) {
        boolean aux = true;
        try {
            this.equipos.add(new Compite_L(equipo, fFin, posicion, this));
        } catch (Exception e) {
            aux = false;
        }
        return aux;
    }
}
