

public abstract class Competicion{
public int diaRealizacion,mesRealizacion, anoRealizacion;
public String nombreCompeticion, juego, modalidad, fecha;
public Premio recompensa;
    
    public Competicion(String nombreCompeticion, String fecha,  String juego, String tipo, String modalidad, Premio recompensa){
        this.nombreCompeticion = nombreCompeticion;
        this.juego = juego;
        this.fecha = fecha;
        this.modalidad = modalidad;
        this.recompensa = recompensa;
    }

    public Competicion(String nombreCompeticion2, Premio recompensa2, String fecha2, String juego2, String string) {
        //TODO Auto-generated constructor stub
    }

    public Competicion(String nombreCompeticion2, String fecha2, String juego2, Premio recompensa2, String string) {
        //TODO Auto-generated constructor stub
    }

    public String getNombre() {
        return nombreCompeticion;
    }

    public String getJuego() {
        return juego;
    }

    public String getModalidad() {
        return modalidad;
    }

    public Premio getrecompensa() {
        return recompensa;
    }

    public String getFecha(){
        return fecha;
    }



    //*Usaré abstract porque voy a usar 2 herencias, una para la modalidad individual y otra para la de equipos *//

    public abstract void mostrarCompeticion();

}