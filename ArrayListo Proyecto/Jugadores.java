// Autor: David Moreno Sanchez 
// Programa para gestionar jugadores y equipos de una página.
// Se mantiene todo en un solo archivo para facilitar su transporte entre dispositivos.

import java.util.ArrayList;
import java.util.Scanner;

public class Jugadores {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Jugador> jugadores = new ArrayList<>();
        ArrayList<Equipo> equipos = new ArrayList<>();

        while (true) {
            System.out.println("\nMENÚ PRINCIPAL");
            System.out.println("1. Agregar Jugador");
            System.out.println("2. Mostrar Jugadores");
            System.out.println("3. Agregar Equipo");
            System.out.println("4. Mostrar Equipos");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del jugador: ");
                    String nombre = scanner.nextLine();
                    System.out.print("DNI del jugador: ");
                    String dni = scanner.nextLine();
                    System.out.print("Fecha de nacimiento (DD/MM/AAAA): ");
                    String fechaNacimiento = scanner.nextLine();
                    System.out.print("Contraseña: ");
                    String password = scanner.nextLine();
                    System.out.print("¿Es capitán? (true/false): ");
                    boolean capitan = scanner.nextBoolean();
                    System.out.print("¿Es titular? (true/false): ");
                    boolean titular = scanner.nextBoolean();
                    scanner.nextLine();
                    
                    jugadores.add(new Jugador(nombre, dni, fechaNacimiento, password, capitan, titular));
                    System.out.println("Jugador agregado correctamente.");
                    break;

                case 2:
                    if (jugadores.isEmpty()) {
                        System.out.println("No hay jugadores registrados.");
                    } else {
                        System.out.println("\nLista de Jugadores:");
                        for (Jugador j : jugadores) {
                            j.mostrarInfo();
                        }
                    }
                    break;

                case 3:
                    System.out.print("Nombre del equipo: ");
                    String nombreEquipo = scanner.nextLine();
                    System.out.print("Puntuación del equipo: ");
                    int puntuacion = 0;
                    equipos.add(new Equipo(nombreEquipo, puntuacion));
                    System.out.println("Equipo agregado correctamente.");
                    break;

                case 4:
                    if (equipos.isEmpty()) {
                        System.out.println("No hay equipos registrados.");
                    } else {
                        System.out.println("\nLista de Equipos:");
                        for (Equipo e : equipos) {
                            e.mostrarInfo();
                        }
                    }
                    break;

                case 5:
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }
}

class Jugador {
    private String nombre;
    private String dni;
    private String fechaNacimiento;
    private String password;
    private Forma forma;
    
    public Jugador(String nombre, String dni, String fechaNacimiento, String password, boolean capitan, boolean titular) {
        this.nombre = nombre;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.password = password;
        this.forma = new Forma(capitan, titular);
    }

    public void mostrarInfo() {
        System.out.println("Jugador: " + nombre + " | DNI: " + dni + " | Nacimiento: " + fechaNacimiento +
                " | Capitán: " + forma.getCapitan() + " | Titular: " + forma.getTitular());
    }
}

class Forma {
    private boolean capitan;
    private boolean titular;

    public Forma(boolean capitan, boolean titular) {
        this.capitan = capitan;
        this.titular = titular;
    }

    public boolean getCapitan() {
        return capitan;
    }

    public boolean getTitular() {
        return titular;
    }
}

class Equipo {
    private String nombre;
    private int puntuacion;

    public Equipo(String nombre, int puntuacion) {
        this.nombre = nombre;
        this.puntuacion = puntuacion;
    }

    public void mostrarInfo() {
        System.out.println("Equipo: " + nombre + " | Puntuación: " + puntuacion);
    }
}
