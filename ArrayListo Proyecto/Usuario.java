
public class Usuario {

    //Atributos
    private String nombre;
    private String gmail;
    private String password;
    private String password2;
    private int dni;
    private char letra;
    private int fn; //fecha nacimiento

    public Usuario() {
        // Constructor base
        this.nombre = "";
        this.gmail = "";
        this.password = "";
        this.dni = 0;
        this.letra = ' ';
        this.fn = 0;
    }

    public Usuario(Usuario otro) {
        // Constructor de copia
        this.nombre = otro.nombre;
        this.gmail = otro.gmail;
        this.password = otro.password;
        this.dni = otro.dni;
        this.letra = otro.letra;
        this.fn = otro.fn;
    }

    public Usuario(String nombre, String gmail, String password, int dni, char letra, int fn) {
        // Constructor sobrecargado
        this.nombre = nombre;
        this.gmail = gmail;
        this.password = password;
        this.dni = dni;
        this.letra = letra;
        this.fn = fn;
    }

    //Funciones
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setLetra(char letra) {
        this.letra = letra;
    }

    public void setFn(int fn) {
        this.fn = fn;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGmail() {
        return gmail;
    }

    public String getPassword() {
        return password;
    }

    public int getDni() {
        return dni;
    }

    public char getLetra() {
        return letra;
    }

    public int getFn() {
        return fn;
    }

    public String obtenerDniCompleto() {
        return dni + "" + letra;
    }

    public void mostrar() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Gmail: " + gmail);
        System.out.println("DNI: " + obtenerDniCompleto());
        System.out.println("Fecha de Nacimiento: " + fn);
    }
}
