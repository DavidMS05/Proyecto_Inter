package clases;

import java.util.*;

/**
 * Clase que representa un equipo formado por diferentes jugadores.
 * @author Hector, modificado por Denys (3D)
 * @version 1.1
 * @see Jugador
 */
public class Equipo {
    private String nombre;
    private ArrayList<String> jugadores;
    private int cod;

    /**
     * Constructor por defecto.
     */
    public Equipo() {
        this.nombre = "";
        this.jugadores = new ArrayList<String>();
    }
    /**
     * Constructor para casos especificos, no se usa de normal.
     * @param nombre nombre del equipo
     */
    public Equipo(String nombre) {
        this.nombre = nombre;
        this.jugadores = new ArrayList<String>();
    }
    /**
     * Constructor comun.
     * @param nombre nombre del equipo
     * @param jugadores jugadores del equipo
     */
    public Equipo(String nombre, ArrayList<String> jugadores) {
        this.nombre = nombre;
        this.jugadores = new ArrayList<>(jugadores);
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    /**
     * Getter de nombre.
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo que devuelve un mensaje con los jugadores del grupo.
     * @return String
     * @see Jugador#toString()
     * @since 1.0
     */
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

    /**
     * Getter de jugadores.
     * @return ArrayList de String
     */
    public ArrayList<String> getJugadores() {
        return jugadores;
    }
}
