package clases;

/**
 * Clase que representa un juego.
 * @author Denys (3D)
 */
public class Juego {
    private String nombre;
    private int cod;

    /**
     * Constructor por defecto.
     */
    public Juego() {
        nombre = "";
        cod = 0;
    }

    /**
     * Constructor común.
     * @param nombre nombre del juego
     */
    public Juego(String nombre) {
        this.nombre = nombre;
        this.cod = 0;
    }

    @Override
    public String toString() {
        return this.nombre;
    }

    /**
     * Getter de código.
     * @return código
     */
    public int getCod() {
        return cod;
    }

    /**
     * Getter de nombre.
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter de código.
     * @param cod nuevo código
     */
    public void setCod(int cod) {
        this.cod = cod;
    }

    /**
     * Setter de nombre.
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
