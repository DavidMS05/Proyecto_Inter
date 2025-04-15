package ByteScore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase main del subsistema de jugadores.
 * @author David, modificado por Denys (3D)
 * @version 1.2
 */
public class Jugadores {
    /**
     * Metodo main.
     * @param args argumentos
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Jugador> jugadores = new ArrayList<>();
        leerJugadores(jugadores);
        // ArrayList<Equipo> equipos = new ArrayList<>();
        int opcion = -1;
        boolean modJ = false;

        System.out.println("\n".repeat(20));

        while (opcion != 0) {
            System.out.println("\nMENu JUGADORES");
            System.out.println("1. Agregar Jugador");
            System.out.println("2. Mostrar Jugadores");
            // System.out.println("3. Agregar Equipo");
            // System.out.println("4. Mostrar Equipos");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opcion: ");
            
            opcion = scanner.nextInt();
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
                    System.out.print("¿Es capitan? (true/false): ");
                    boolean capitan = scanner.nextBoolean();
                    System.out.print("¿Es titular? (true/false): ");
                    boolean titular = scanner.nextBoolean();
                    scanner.nextLine();
                    
                    jugadores.add(new Jugador(nombre, dni, fechaNacimiento, password, capitan, titular));
                    System.out.println("Jugador agregado correctamente.");
                    modJ = true;
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

                // case 3:
                //     System.out.print("Nombre del equipo: ");
                //     String nombreEquipo = scanner.nextLine();
                //     System.out.print("Puntuacion del equipo: ");
                //     int puntuacion = 0;
                //     // equipos.add(new Equipo(nombreEquipo, puntuacion));
                //     equipos.add(new Equipo());
                //     System.out.println("Equipo agregado correctamente.");
                //     break;

                // case 4:
                //     if (equipos.isEmpty()) {
                //         System.out.println("No hay equipos registrados.");
                //     } else {
                //         System.out.println("\nLista de Equipos:");
                //         for (Equipo e : equipos) {
                //             System.out.println(e);;
                //         }
                //     }
                //     break;

                case 0:
                    //System.out.println("Saliendo del programa...");
                    // scanner.close();
                    if (modJ)
                        escribirJugador(jugadores);
                    break;

                default:
                    System.out.println("Opcion no valida. Intenta de nuevo.");
            }
        }
        System.out.println("\n".repeat(20));
    }

    /**
     * Metodo que lee los datos de jugadores de un archivo <code>csv</code>.
     * @param jugadores ArrayList de Jugador
     * @since 1.2
     * @see Jugador
     */
    public static void leerJugadores(ArrayList<Jugador> jugadores) {
        jugadores.clear();
        File fs = new File("./ByteScore/datos/jugadores.csv");
        if (fs.exists()) {
            try {
                FileReader fr = new FileReader(fs);
                BufferedReader br = new BufferedReader(fr);
                String cadena;

                while ((cadena = br.readLine()) != null) {
                    String strings[] = cadena.split(","), dni = strings[0], nombre = strings[1], f = strings[2], pwd = strings[3], b1 = strings[4], b2 = strings[5];
                    jugadores.add(new Jugador(nombre, dni, f, pwd, b1.equals("true"), b2.equals("true")));
                }
                if (fr != null)
                    fr.close();
            } catch (IOException e) {
                System.err.println("ERROR");
                e.printStackTrace();
            }
        }
    }

    /**
     * Metodo que escribe los datos de jugadores a un archivo <code>csv</code>.
     * @param jugadores ArrayList de Jugador
     * @since 1.2
     * @see Jugador
     */
    public static void escribirJugador(ArrayList<Jugador> jugadores) {
        try {
            File fs = new File("./ByteScore/datos/jugadores.csv");
            FileWriter fw = new FileWriter(fs);
            for (Jugador j : jugadores) {
                String s = j.getDni() + "," + j.getNombre() + "," + j.getFechaNacimiento() + "," + j.getPassword() +
                            "," + j.getForma().getCapitan() + "," + j.getForma().getTitular();
                fw.write(s, 0, s.length());
                fw.write("\r\n");
            }
            if (fw != null)
                fw.close();
        } catch (IOException e) {
            System.err.println("ERROR");
            e.printStackTrace();
        }
    }
}
