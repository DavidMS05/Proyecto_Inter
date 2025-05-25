package clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Clase main del subsistema de Equipos.
 * @author Hector, modificado por Denys (3D)
 * @version 1.2
 */
public class MainEquipo {
    /**
     * Metodo main.
     * @param args argumentos
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Equipo> equipos = new ArrayList<>();
        leerEquipos(equipos);
        boolean modE = false;
        int opcion = -1;

        System.out.println("\n".repeat(20));

        while (opcion != 0) {
            System.out.print(
                    "\nMENu EQUIPOS\n1. Consultar equipo\n2. Crear nuevo equipo\n3. Eliminar equipo\n0. Salir\nSelecciona una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {
                if (equipos.isEmpty()) {
                    System.out.println("No hay equipos registrados.");
                } else {
                    System.out.println("Selecciona un equipo (1, 2, 3...). Pulse 0 para volver al menu.");
                    for (int i = 0; i < equipos.size(); i++) {
                        System.out.println((i + 1) + ". " + equipos.get(i).getNombre());
                    }
                    int equipoIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (equipoIndex > 0 && equipoIndex <= equipos.size()) {
                        System.out.println(equipos.get(equipoIndex - 1).getPlayers());
                    }
                }
            } else if (opcion == 2) {
                System.out.print("Elige el nombre del nuevo equipo. ");
                String nombreEquipo = scanner.nextLine();
                ArrayList<String> jugadores = new ArrayList<>();
                for (int i = 1; i <= 5; i++) {
                    System.out.print("Dime el nombre del jugador " + i + ". ");
                    jugadores.add(scanner.nextLine());
                }
                equipos.add(new Equipo(nombreEquipo, jugadores));
                System.out.println("Perfecto! Equipo creado exitosamente.");
                modE = true;
            } else if (opcion == 3) {
                if (equipos.isEmpty()) {
                    System.out.println("No hay equipos para eliminar.");
                } else {
                    System.out.println("Selecciona el equipo a eliminar (1, 2, 3...). Pulsa 0 para cancelar.");
                    for (int i = 0; i < equipos.size(); i++) {
                        System.out.println((i + 1) + ". " + equipos.get(i).getNombre());
                    }
                    int equipoIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (equipoIndex > 0 && equipoIndex <= equipos.size()) {
                        equipos.remove(equipoIndex - 1);
                        System.out.println("Perfecto. Equipo eliminado exitosamente.");
                        modE = true;
                    }
                }
            }
        }
        if (modE)
            escribirEquipos(equipos);

        System.out.println("\n".repeat(20));
        // scanner.close();
    }

    /**
     * Metodo que lee los datos de equipos de un archivo <code>csv</code>.
     * @param equipos ArrayList de Equipo
     * @since 1.2
     * @see Equipo
     */
    public static void leerEquipos(ArrayList<Equipo> equipos) {
        equipos.clear();
        File fs = new File("./ByteScore/datos/equipos.csv");
        if (fs.exists()) {
            try {
                FileReader fr = new FileReader(fs);
                BufferedReader br = new BufferedReader(fr);
                String cadena;

                while ((cadena = br.readLine()) != null) {
                    String strings[] = cadena.split(","), nombre = strings[0];
                    String[] jugadores = new String[strings.length - 1];
                    System.arraycopy(strings, 1, jugadores, 0, jugadores.length);
                    ArrayList<String> jugadores2 = new ArrayList<String>();
                    jugadores2.addAll(Arrays.asList(jugadores));
                    equipos.add(new Equipo(nombre, jugadores2));
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
     * Metodo que escribe los datos de equipos a un archivo <code>csv</code>.
     * @param equipos ArrayList de Equipo
     * @since 1.2
     * @see Equipo
     */
    public static void escribirEquipos(ArrayList<Equipo> equipos) {
        try {
            File fs = new File("./ByteScore/datos/equipos.csv");
            FileWriter fw = new FileWriter(fs);
            for (Equipo e : equipos) {
                String s = e.getNombre() + "," + juntar(e.getJugadores(), ",");
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

    /**
     * Metodo de utilidad para juntar un <code>ArrayList</code> de String a una cadena de texto.
     * @param strs ArrayList de String
     * @param separador separador para insertar entre elementos
     * @return cadena de texto
     */
    public static String juntar(ArrayList<String> strs, String separador) {
        String res = "";
        if (!strs.isEmpty()) {
            for (String s : strs)
                res += s + ",";
            res = res.substring(0, res.length() - 1);
        }
        return res;
    }
}