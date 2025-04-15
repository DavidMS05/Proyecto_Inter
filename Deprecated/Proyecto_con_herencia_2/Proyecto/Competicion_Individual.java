/*Aquí tenemos la primera herencia */ 
public class Competicion_Individual extends Competicion{
    /*Voy a poner a 3 participamtes para poner el ejemplo */
    
    public String participante1,participante2, participante3;
    public int ronda1, ronda2, ronda3;

    public Competicion_Individual(String nombreCompeticion, int par, String fecha, Premio recompensa, String juego, String tipo, String modalidad, String participante1, String participante2, String participante3){
        /*Vamos a llamar a las superclases de competicion para rellenar los datos de la modalidad, el juego que están jugando, la fecha y el premio*/
        super(nombreCompeticion, recompensa, fecha, juego, "Individual");
        this.participante1 = participante1;
        this.ronda1 = 0;
        this.participante2 = participante2;
        this.ronda2 = 0;
        this.participante3 = participante3;
        this.ronda3 = 0;
    } 
    public int calculoRonda() {
        return (int) Math.ceil(Math.log(3) / Math.log(2)) + 1;
    }

    public void registrarRonda(String participante, int ronda) {
    if (participante.equals(participante1)) {
        ronda1 = ronda;
    } else if (participante.equals(participante2)){
        ronda2 = ronda;
    } 
    
    else if (participante.equals(participante3)){
        ronda3 = ronda;
    } 
    
    else {
        System.out.println("Participante no encontrado: " + participante);
    }
}

    /*El override lo que hace es básicamente dejarnos modificar la superclase que hemos queado antes */
    @Override
    public void mostrarCompeticion() {
        System.out.println("Competición Individual: " + getNombre());
        System.out.println("Fecha: " + getFecha());
        System.out.println("Juego: " + getJuego());
        System.out.println(getrecompensa());
        System.out.println("Participantes:");
        System.out.println("- " + participante1 + ", Ronda alcanzada: " + ronda1);
        System.out.println("- " + participante2 + ", Ronda alcanzada: " + ronda2);
        System.out.println("- " + participante3 + ", Ronda alcanzada: " + ronda3);
        System.out.println("Número de rondas: " + calculoRonda());
    }
}
