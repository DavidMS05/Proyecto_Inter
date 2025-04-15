/**
 * Autor: Denys y Claudia
 */

import java.util.ArrayList;
import java.util.Scanner;

// Faltan los hijos de Competición (Individual, Liga y Eliminatoria)
public class testCompeticion {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Competicion> competiciones = new ArrayList<Competicion>();
        final String MENU = " MENÚ\n1. Insertar\n2. Mostrar\n3. Borrar\n0. Salir\nOpción: ";
        int op;

        System.out.print(MENU);
        op = scan.nextInt();
        while (op != 0) {
            switch (op) {
                case 1 -> {
                    scan.nextLine();
                    System.out.print("Nombre: ");
                    String nom = scan.nextLine();
                    competiciones.add(new Competicion(nom, new Juego()));
                }
                case 2 -> System.out.println(competiciones);
                case 3 -> {
                    System.out.print("Posición del arraylist: ");
                    int pos = scan.nextInt();
                    try {
                        competiciones.remove(pos);
                        System.out.println("Eliminado.");
                    } catch (Exception e) {
                        System.out.println("Algo ha salido mal.");
                    }
                }
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
            System.out.print(MENU);
            op = scan.nextInt();
        }
        System.out.println("Fin del programa.");
    }
}
