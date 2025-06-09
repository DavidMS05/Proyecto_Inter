package clases;

import java.sql.Date;

/**
 * Clase que representa un jugador o usuario del sistema.
 * @author David
 */
public class Jugador {
    private String dni;
    private String nombre;
    private Date fechaNacimiento;
    private String email;
    private String password;
    
    /**
     * Constructor por defecto.
     */
    public Jugador() {}

    /**
     * Constructor común.
     * @param nombre nombre del jugador
     * @param dni dni del jugador
     * @param fechaNacimiento fecha de nacimiento
     * @param email email del jugador
     * @param password contrasenya
     */
    public Jugador(String nombre, String dni, Date fechaNacimiento, String email, String password) {
        this.nombre = nombre;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.password = password;
    }

    /**
     * Muestra la informacion relevante del jugador.
     * @deprecated
     */
    @Deprecated
    public void mostrarInfo() {
        System.out.println("Jugador: " + nombre + " | DNI: " + dni + " | Nacimiento: " + fechaNacimiento);
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
     * @param dni nuevo DNI
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Setter de email
     * @param email nuevo email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Setter de fecha de nacimiento
     * @param fechaNacimiento nueva fecha
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Setter de nombre
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Setter de contraseña
     * @param password nueva contraseña
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return this.dni + "\n-Nombre: " + this.nombre + "\n-Email: " + this.email + "\n-Fecha de nacimiento: " + this.fechaNacimiento;
    }
}
