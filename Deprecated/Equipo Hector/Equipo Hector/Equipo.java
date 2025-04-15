import java.util.*;
class Equipo {
    private String nombre;
    private ArrayList<String> jugadores;

    public Equipo(String nombre, ArrayList<String> jugadores) {
        this.nombre = nombre;
        this.jugadores = new ArrayList<>(jugadores);
    }

    public String getNombre() {
        return nombre;
    }

    public String getPlayers() {
        String resultado = "Los jugadores del equipo " + nombre + " son ";
        for (int i = 0; i < jugadores.size(); i++) {
            resultado += jugadores.get(i);
            if (i < jugadores.size() - 1) {
                resultado += ", ";
            }
        }
        resultado += ".";
        return resultado;
    }
}
