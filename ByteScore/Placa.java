package ByteScore;

import java.util.ArrayList;

/**
 * Una placa con texto que se adjunta a un premio.
 * @author Denys (3D)
 * @version 1.1
 */
public class Placa {
    private int id;
    private String texto;
    private ArrayList<Premio> premios;

    // Constructores
    /**
     * Constructor por defecto.
     */
    public Placa() {
        id = -1;
        texto = "";
        premios = new ArrayList<Premio>();
    }
    /**
     * Constructor comun.
     * @param id id de la placa
     * @param texto texto de la placa
     */
    public Placa(int id, String texto) {
        this.id = id;
        this.texto = texto;
        premios = new ArrayList<Premio>();
    }

    // Getters && Setters
    /**
     * Getter de id de placa.
     * @return id
     */
    public int getId() {
        return id;
    }
    /**
     * Setter de id de placa.
     * @param id nuevo id
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Getter de texto de placa.
     * @return texto
     */
    public String getTexto() {
        return texto;
    }
    /**
     * Setter de texto de placa.
     * @param texto nuevo texto
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }
    /**
     * Getter de premios que tienen esta placa.
     * @return ArrayList de Premio
     * @see Premio
     */
    public ArrayList<Premio> getPremios() {
        return premios;
    }
    /**
     * Getter de premio especifico.
     * @param index indice del ArrayList
     * @return Premio
     * @see Premio
     */
    public Premio getPremio(int index) {
        return premios.get(index);
    }
    /**
     * Setter de premios.
     * @param premios nuevo premios
     * @see Premio
     */
    public void setPremios(ArrayList<Premio> premios) {
        this.premios = premios;
    }

    // Metodos
    /**
     * Añade un premio al ArrayList premios
     * @param premio objeto de la clase Premio
     * @return true si exito, false si error
     * @since 1.0
     * @deprecated no tiene uso en un futuro visible
     */
    public boolean addPremio(Premio premio) {
        boolean aux = true;
        try {
            this.premios.add(premio);
        } catch (Exception e) {
            aux = false;
        }
        return aux;
    }
    @Override
    public String toString() {
        return Integer.toString(id);
    }
}
