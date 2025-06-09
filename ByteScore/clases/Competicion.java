package clases;

import java.util.Date;

/**
 * Clase abstracta de competiciones, se subdivide en eliminatorias, ligas e individuales.
 * @author Denys y Claudia
 * @see Eliminatoria
 * @see Liga
 * @see Individual
 */
public abstract class Competicion {
    /** Nombre de la competicion, debe ser unico. */
    protected String nombre;
    /** Fecha de realizacion de la competicion. */
    protected Date fRealizacion;
    /** Juego de la competicion.
     * @see Juego
     */
    protected Juego juego;

    // Constructores
    /**
     * Constructor por defecto.
     */
    public Competicion() {
        nombre = "";
        fRealizacion = new Date();
    }

    /**
     * Constructor común.
     * @param nombre nombre de la competicion
     * @param juego juego de la competicion
     * @param fRealizacion fecha de la competicion
     */
    public Competicion(String nombre, Juego juego, Date fRealizacion) {
        this.nombre = nombre;
        this.juego = juego;
        this.fRealizacion = fRealizacion;
        // premio = new Premio();
    }

    // Getters && Setters
    /**
     * Getter de nombre.
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Setter de nombre.
     * @param nombre Nuevo nombre de competicion
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Getter de fecha.
     * @return fecha
     */
    public Date getfRealizacion() {
        return fRealizacion;
    }
    /**
     * Setter de fecha.
     * @param fRealizacion nueva fecha
     */
    public void setfRealizacion(Date fRealizacion) {
        this.fRealizacion = fRealizacion;
    }
    /**
     * Getter de juego.
     * @return Juego
     * @see Juego
     */
    public Juego getJuego() {
        return juego;
    }
    /**
     * Setter de juego.
     * @param juego nuevo juego
     * @see Juego
     */
    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    /**
     * Getter de tipo de competición en texto.
     * @return tipo en texto
     */
    public abstract String getTipo();

    // Metodos
    @Override
    public String toString() {
        return "\nCompeticion: " + nombre + 
        "\n - Tipo: " + getTipo() +
        "\n - Fecha: " + fRealizacion + 
        "\n - Juego: " + juego;
    }
}