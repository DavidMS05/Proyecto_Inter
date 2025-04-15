/**
 * Autor: Denys y Claudia
 */

import java.util.ArrayList;
import java.util.Date;

public class Eliminatoria extends Competicion {
    private ArrayList<Compite_E> equipos;

    public Eliminatoria() {
        super();
        equipos = new ArrayList<Compite_E>();
    }
    public Eliminatoria(String nombre, Juego juego, Date fRealizacion, Premio premio, ArrayList<Compite_E> equipos) {
        super(nombre, juego, fRealizacion, premio);
        this.equipos = equipos;
    }

    public ArrayList<Compite_E> getEquipos() {
        return equipos;
    }
    public void setEquipos(ArrayList<Compite_E> equipos) {
        this.equipos = equipos;
    }

    public boolean addEquipo(Equipo equipo, int numRonda) {
        boolean aux = true;
        try {
            this.equipos.add(new Compite_E(equipo, numRonda, this));
        } catch (Exception e) {
            aux = false;
        }
        return aux;
    }
}
