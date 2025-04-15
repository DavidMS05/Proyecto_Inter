package ByteScore;

import java.util.Scanner;

/**
 * Clase principal del sistema, sirve como puente para moverse entre otros subsistemas.
 * @author Denys (3D)
 * @version 1.1
 * @see Jugadores
 * @see MainEquipo
 * @see Resultados
 * @see testCompeticion
 */
public class Main {
    /**
     * Metodo main.
     * @param args argumentos
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        final String MENU_INICIO = "\nMENu PRINCIPAL\n1. Jugadores\n2. Equipos\n3. Resultados\n4. Competiciones\n0. Salir\nElija un subsistema: ";
        int op = -1;

        System.out.println("\n".repeat(20));

        while (op != 0) {
            System.out.print(MENU_INICIO);
            op = scan.nextInt();
            scan.nextLine();

            switch (op) {
                case 0 -> scan.close();
                case 1 -> {
                    Jugadores.main(new String[0]);
                }
                case 2 -> {
                    MainEquipo.main(new String[0]);
                }
                case 3 -> {
                    Resultados.main(new String[0]);
                }
                case 4 -> {
                    testCompeticion.main(new String[0]);
                }
                default -> System.err.println("Opcion incorrecta, inserte un numero del menu.");
            }
        }
        System.out.println("Gracias por usar nuestros servicios. Buena suerte, jugadores.");
    }
}
