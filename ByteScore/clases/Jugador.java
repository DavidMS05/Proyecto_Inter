package clases;

import java.sql.Date;

/**
 * Clase que representa un jugador o usuario del sistema.
 * @author David
 * @version 1.1
 */
public class Jugador {
    private String dni;
    private String nombre;
    private Date fechaNacimiento;
    private String email;
    private String password;
    
    public Jugador() {}

    /**
     * Constructor por defecto.
     * @param dni dni del jugador
     */
    public Jugador(String dni) {
        this.dni = dni;
        //this.nombre = this.fechaNacimiento = this.password = "";
        // this.forma = new Forma(capitan, titular);
    }
    /**
     * Constructor comun.
     * @param nombre nombre del jugador
     * @param dni dni del jugador
     * @param fechaNacimiento fecha de nacimiento
     * @param password contrasenya
     * @param capitan si es capitan
     * @param titular si juega
     */
    public Jugador(String nombre, String dni, Date fechaNacimiento, String email, String password) {//, boolean capitan, boolean titular) {
        this.nombre = nombre;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.password = password;
    }

    /**
     * Muestra la informacion relevante del jugador.
     * @since 1.0
     */
    public void mostrarInfo() {
        System.out.println("Jugador: " + nombre + " | DNI: " + dni + " | Nacimiento: " + fechaNacimiento);// +
                //" | Capitan: " + forma.getCapitan() + " | Titular: " + forma.getTitular());
    }

    /**
     * Getter de nombre de jugador.
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Getter de dni del jugador.
     * @return dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * Getter de fecha de nacimiento.
     * @return fecha
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Getter de contrasenya.
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter de email.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter de DNI
     * @param dni
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Setter de email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Setter de fecha de nacimiento
     * @param fechaNacimiento
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Setter de nombre
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Setter de contraseña
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return this.dni + "\nNombre: " + this.nombre + "\nEmail: " + this.email + "\nFecha de nacimiento: " + this.fechaNacimiento;
    }
}
