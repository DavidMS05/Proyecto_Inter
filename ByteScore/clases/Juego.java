package clases;

/**
 * Clase que representa un juego. No tiene gran funcionalidad.
 * @author Denys (3D)
 * @version 1.0
 */
public class Juego {
    private String nombre;
    private int cod;

    /**
     * Constructor por defecto.
     */
    public Juego() {
        nombre = "";
    }
    /**
     * Constructor comun.
     * @param nombre nombre del juego
     */
    public Juego(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.nombre;
    }

    public int getCod() {
        return cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
