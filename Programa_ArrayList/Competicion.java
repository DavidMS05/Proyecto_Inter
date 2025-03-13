/**
 * Autor: Denys y Claudia
 */

import java.util.Date;

public class Competicion {
    protected String nombre;
    protected Date fRealizacion;
    protected Juego juego;
    protected Premio premio;

    // Constructores
    public Competicion() {
        nombre = "";
        fRealizacion = new Date();
        // juego = new Juego();
        // premio = new Premio();
    }
    public Competicion(String nombre, Juego juego) {
        this.nombre = nombre;
        fRealizacion = new Date();
        // juego = new Juego();
        // premio = new Premio();
    }
    public Competicion(String nombre, Juego juego, Premio premio) {
        this.nombre = nombre;
        fRealizacion = new Date();
        // juego = new Juego();
        this.premio = premio;
    }
    public Competicion(String nombre, Juego juego, Date fRealizacion) {
        this.nombre = nombre;
        this.juego = juego;
        this.fRealizacion = fRealizacion;
        // premio = new Premio();
    }
    public Competicion(String nombre, Juego juego, Date fRealizacion, Premio premio) {
        this.nombre = nombre;
        this.juego = juego;
        this.fRealizacion = fRealizacion;
        this.premio = premio;
    }

    // Getters && Setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Date getfRealizacion() {
        return fRealizacion;
    }
    public void setfRealizacion(Date fRealizacion) {
        this.fRealizacion = fRealizacion;
    }
    public Juego getJuego() {
        return juego;
    }
    public void setJuego(Juego juego) {
        this.juego = juego;
    }
    public Premio getPremio() {
        return premio;
    }
    public void setPremio(Premio premio) {
        this.premio = premio;
    }

    // Métodos
    @Override
    public String toString() {
        return "\nCompetición: " + nombre + 
        "\n - Fecha: " + fRealizacion + 
        "\n - Juego: " + juego + 
        "\n - Premio: " + premio;
    }
}