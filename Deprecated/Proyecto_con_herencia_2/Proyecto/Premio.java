public class Premio{
    public String nombrePremio, placa;
    public double premioMetalico;

    public Premio(String nombrePremio,int premioMetalico, String placa){
        this.nombrePremio = nombrePremio;
        this.premioMetalico = premioMetalico;
        this.placa = placa;
    }
    @Override
    public String toString(){
        return "El premio " + nombrePremio + " está valuado en " + premioMetalico + " al " + placa;
    }

}