package clases;

/**
 * Un premio para una competion.
 * @author Denys
 * @version 1.1
 */
public class Premio {
    private String nombre_p;
    private float p_metalico; // No he encontrado clase Euros
    private Placa placa;
    private Competicion competicion;

    // Constructores
    /**
     * Constructor po defecto.
     */
    public Premio() {
        nombre_p = "";
        p_metalico = 0;
    }
    /**
     * Constructor mini.
     * @param nombre_p nombre de la placa
     */
    public Premio(String nombre_p) {
        this.nombre_p = nombre_p;
        this.p_metalico = 0;
    }
    /**
     * Constructor sin placa.
     * @param nombre_p nombre de la placa
     * @param p_metalico premio en dinero
     */
    public Premio(String nombre_p, float p_metalico) {
        this.nombre_p = nombre_p;
        this.p_metalico = p_metalico;
    }
    /**
     * Constructor con placa.
     * @param nombre_p nombre de la placa
     * @param p_metalico premio en dinero
     * @param placa la placa
     */
    public Premio(String nombre_p, float p_metalico, Placa placa) {
        this.nombre_p = nombre_p;
        this.p_metalico = p_metalico;
        this.placa = placa;
    }

    // Getters && Setters
    /**
     * Getter de nombre de premio.
     * @return nombre
     */
    public String getNombre_p() {
        return nombre_p;
    }
    /**
     * Setter de nombre de premio.
     * @param nombre_p nuevo nombre
     */
    public void setNombre_p(String nombre_p) {
        this.nombre_p = nombre_p;
    }
    /**
     * Getter de premio en dinero.
     * @return premio dinero
     */
    public float getP_metalico() {
        return p_metalico;
    }
    /**
     * Setter de premio en dinero.
     * @param p_metalico nuevo premio
     */
    public void setP_metalico(float p_metalico) {
        this.p_metalico = p_metalico;
    }
    /**
     * Getter de placa.
     * @return Placa
     * @see Placa
     */
    public Placa getPlaca() {
        return placa;
    }
    /**
     * Setter de placa.
     * @param placa nueva placa
     * @see Placa
     */
    public void setPlaca(Placa placa) {
        this.placa = placa;
    }

    public Competicion getCompeticion() {
        return competicion;
    }

    public void setCompeticion(Competicion competicion) {
        this.competicion = competicion;
    }

    // Metodos
    @Override
    public String toString() {
        return this.nombre_p + " | Dinero: " + this.p_metalico + " | Placa: " + this.placa;
    }
}
