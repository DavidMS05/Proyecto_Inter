/*Aquí tenemos la segunda herencia */ 
public class Competicion_Liga extends Competicion{
    /*Voy a poner a 2 equipoa para poner el ejemplo */
    
    public String equipo1, equipo2, fechaFin;
    public int posicion1, posicion2;

    public Competicion_Liga (String nombreCompeticion, int posicion1, String fecha, Premio recompensa, String modalidad, int posicion2, String juego, int par2, int par3){
        /*Vamos a llamar a las superclases de competicion para rellenar los datos de la modalidad, el juego que están jugando, la fecha y el premio*/
        super(nombreCompeticion, fecha, juego, recompensa, "Liga");
        this.equipo1 = equipo1;
        this.posicion1 = posicion1;
        this.equipo2 = equipo2;
        this.posicion2 = posicion2;
        this.fechaFin = fechaFin;
    }

    /*El override lo que hace es básicamente dejarnos modificar la superclase que hemos queado antes */
    
    @Override
    public void mostrarCompeticion() {
            System.out.println("Competición de Liga: " + getNombre());
            System.out.println("Fecha de inicio: " + getFecha());
            System.out.println("Fecha de finalización: " + fechaFin);
            System.out.println("Juego: " + getJuego());
            System.out.println(getrecompensa());
            System.out.println("Posiciones:");
            System.out.println("- " + equipo1 + ": posición " + posicion1);
            System.out.println("- " + equipo2 + ": posición " + posicion2);
    }
}