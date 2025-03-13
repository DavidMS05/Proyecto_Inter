/**
 * Autor: Denys y Claudia
 */

public class Premio {
    private String nombre_p;
    private float p_metalico; // No he encontrado clase Euros
    private Placa placa;

    // Constructores
    public Premio() {
        nombre_p = "";
        p_metalico = 0;
    }

    public Premio(String nombre_p) {
        this.nombre_p = nombre_p;
        this.p_metalico = 0;
    }

    public Premio(String nombre_p, float p_metalico) {
        this.nombre_p = nombre_p;
        this.p_metalico = p_metalico;
    }

    public Premio(String nombre_p, float p_metalico, Placa placa) {
        this.nombre_p = nombre_p;
        this.p_metalico = p_metalico;
        this.placa = placa;
    }

    // Getters && Setters
    public String getNombre_p() {
        return nombre_p;
    }
    public void setNombre_p(String nombre_p) {
        this.nombre_p = nombre_p;
    }
    public float getP_metalico() {
        return p_metalico;
    }
    public void setP_metalico(float p_metalico) {
        this.p_metalico = p_metalico;
    }
    public Placa getPlaca() {
        return placa;
    }
    public void setPlaca(Placa placa) {
        this.placa = placa;
    }

    // Métodos
    @Override
    public String toString() {
        return nombre_p + " | Placa: " + placa;
    }
}
