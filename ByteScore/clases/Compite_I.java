package clases;

/**
 * Resultado de un jugador de una competicion individual.
 * 
 * @author Denys y Claudia
 * @version 1.2
 */
public class Compite_I implements Compite {
    private Jugador jugador;
    private Individual competicion;
    private int numRonda;

    /**
     * Constructor por defecto.
     */
    public Compite_I() {
        numRonda = 0;
    }

    /**
     * Constructor para usos especificos, no se usa de normal.
     * 
     * @param jugador jugador participante
     */
    public Compite_I(Jugador jugador) {
        numRonda = 0;
        this.jugador = jugador;
    }

    /**
     * Constructor comun.
     * 
     * @param jugador     jugador participante
     * @param numRonda    ronda en la que ha sido eliminado
     * @param competicion la competicion
     */
    public Compite_I(Jugador jugador, int numRonda, Individual competicion) {
        this.jugador = jugador;
        this.numRonda = numRonda;
        this.competicion = competicion;
    }

    /**
     * Getter de jugador.
     * 
     * @return Jugador
     * @see Jugador
     */
    public Jugador getJugador() {
        return jugador;
    }

    /**
     * Setter de jugador.
     * 
     * @param jugador nuevo jugador
     * @see Jugador
     */
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    /**
     * Getter de numRonda.
     * 
     * @return numRonda
     */
    public int getNumRonda() {
        return numRonda;
    }

    /**
     * Setter de numRonda.
     * 
     * @param numRonda nuevo numRonda
     */
    public void setNumRonda(int numRonda) {
        this.numRonda = numRonda;
    }

    /**
     * Getter de competicion.
     * 
     * @return Individual
     * @see Individual
     */
    public Individual getCompeticion() {
        return competicion;
    }

    /**
     * Setter de competicion.
     * 
     * @param competicion nueva competicion individual
     * @see Individual
     */
    public void setCompeticion(Individual competicion) {
        this.competicion = competicion;
    }

    /**
     * comparar
     * Metodo de la interfaz Compite, compara las cadenas pasadas con el nombre de
     * la competicion y el DNI del jugador.
     * 
     * @param comp Nombre de la competicion.
     * @param dni  DNI del jugador.
     * @return true si coinciden, sino false.
     * @since 1.1
     * @see Compite
     * @see Competicion
     * @see Jugador
     */
    public boolean comparar(String comp, String dni) {
        boolean res = false;
        if (comp.equals(competicion.getNombre()) && dni.equals(jugador.getDni()))
            res = true;
        return res;
    }

    @Override
    public String htmlHeader(String nomCompeticion) {
        return "<!DOCTYPE html><html lang=\"es\"><head><meta charset=\"UTF-8\">" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "<title>Resultados</title><style>table,td,th {border: 1px solid black;}</style></head>" +
                "<body><h1>Competicion Individual: " + nomCompeticion + "</h1>" +
                "<table><tr><th>Jugador</th><th>Ronda</th></tr>";
    }

    @Override
    public String htmlContent() {
        return "<tr><td>" + this.getJugador().getNombre() + "</td><td>" + this.getNumRonda() + "</td></tr>";
    }

    @Override
    public String letra() {
        return "i";
    }

    @Override
    public String escribirCSV() {
        return this.getJugador().getNombre() + "," + this.getNumRonda() + "," + this.getCompeticion().getNombre();
    }
}
