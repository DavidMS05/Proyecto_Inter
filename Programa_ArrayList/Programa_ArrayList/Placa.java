/**
 * Autor: Denys y Claudia
 */

import java.util.ArrayList;

public class Placa {
    private int id;
    private String texto;
    private ArrayList<Premio> premios;

    // Constructores
    public Placa() {
        id = -1;
        texto = "";
        premios = new ArrayList<Premio>();
    }

    public Placa(int id, String texto) {
        this.id = id;
        this.texto = texto;
        premios = new ArrayList<Premio>();
    }

    // Getters && Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    public ArrayList<Premio> getPremios() {
        return premios;
    }
    public Premio getPremio(int index) {
        return premios.get(index);
    }
    public void setPremios(ArrayList<Premio> premios) {
        this.premios = premios;
    }

    // Métodos
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
