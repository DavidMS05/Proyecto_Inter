package clases;

/**
 * Clase que representa un equipo formado por diferentes jugadores.
 * @author Hector, modificado por Denys (3D)
 */
public class Equipo {
    private String nombre;
    private int cod;

    /**
     * Constructor por defecto.
     */
    public Equipo() {
        this.nombre = "";
    }
    
    /**
     * Constructor común.
     * @param nombre nombre del equipo
     */
    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter de id de equipo.
     * @return codigo
     */
    public int getCod() {
        return cod;
    }

    /**
     * Setter de id de equipo (por si acaso).
     * @param cod el nuevo id
     */
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

    /**
     * Setter de nombre de equipo.
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return String.format("%3d", this.cod) + ": " + this.nombre;
    }
}
