package clases;

import java.util.Date;

/**
 * Clase abstracta de competiciones, se subdivide en eliminatorias, ligas e individuales.
 * @author Denys y Claudia
 * @version 1.2
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
    /** Premio de la competicion.
     * @see Premio
     */
    protected Premio premio;

    // Constructores
    /**
     * Constructor por defecto.
     */
    public Competicion() {
        nombre = "";
        fRealizacion = new Date();
        // juego = new Juego();
        // premio = new Premio();
    }
    /**
     * Constructor para usos especificos, no se usa normalmente.
     * @param nombre nombre de la competicion
     */
    public Competicion(String nombre) {
        this.nombre = nombre;
        // fRealizacion = new Date();
        // juego = new Juego();
        // premio = new Premio();
    }
    /**
     * Constructor 2
     * @param nombre nombre de la competicion
     * @param juego juego de la competicion
     */
    public Competicion(String nombre, Juego juego) {
        this.nombre = nombre;
        this.juego = juego;
        fRealizacion = new Date();
        // premio = new Premio();
    }
    /**
     * Constructor 3
     * @param nombre nombre de la competicion
     * @param juego juego de la competicion
     * @param premio premio de la competicion
     */
    public Competicion(String nombre, Juego juego, Premio premio) {
        this.nombre = nombre;
        this.juego = juego;
        fRealizacion = new Date();
        this.premio = premio;
    }
    /**
     * Constructor 4
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
    /**
     * Constructor usado para crear y guardar competiciones.
     * @param nombre nombre de la competicion
     * @param juego juego de la competicion
     * @param fRealizacion fecha de la competicion
     * @param premio premio de la competicion
     */
    public Competicion(String nombre, Juego juego, Date fRealizacion, Premio premio) {
        this.nombre = nombre;
        this.juego = juego;
        this.fRealizacion = fRealizacion;
        this.premio = premio;
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
     * Getter de premio.
     * @return Premio
     * @see Premio
     */
    public Premio getPremio() {
        return premio;
    }
    /**
     * Setter de premio.
     * @param premio nuevo premio
     * @see Premio
     */
    public void setPremio(Premio premio) {
        this.premio = premio;
    }

    // Metodos
    @Override
    public String toString() {
        return "\nCompeticion: " + nombre + 
        "\n - Fecha: " + fRealizacion + 
        "\n - Juego: " + juego + 
        "\n - Premio: " + premio;
    }
}