package ByteScore;

/**
 * Clase que representa un juego. No tiene gran funcionalidad.
 * @author Denys (3D)
 * @version 1.0
 */
public class Juego {
    private String nombre;

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
}
