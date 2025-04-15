package ByteScore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Clase main del subsistema de competiciones.
 * @author Denys y Claudia
 * @version 1.0
 */
public class testCompeticion {
    /**
     * Metodo main.
     * @param args argumentos
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Competicion> competiciones = new ArrayList<Competicion>();
        leerCompeticion(competiciones);
        final String MENU = "\nMENu COMPETICIONES\n1. Insertar\n2. Mostrar\n3. Borrar\n0. Salir\nSelecciona una opcion: ";
        int op;
        boolean modC = false;

        System.out.println("\n".repeat(20));

        System.out.print(MENU);
        op = scan.nextInt();
        scan.nextLine();
        while (op != 0) {
            switch (op) {
                case 1 -> {
                    System.out.print("¿Que tipo de competicion? [e/l/i]: ");
                    String com = scan.nextLine();
                    switch (com) {
                        case "e", "l", "i" -> {
                            System.out.print("Nombre: ");
                            String nom = scan.nextLine();
                            System.out.print("Nombre del juego: ");
                            String juego = scan.nextLine();
                            System.out.print("Nombre del premio: ");
                            String nomP = scan.nextLine();
                            System.out.print("Premio en metalico: ");
                            float p_met = scan.nextFloat();
                            switch (com) {
                                case "e" -> {
                                    competiciones.add(new Eliminatoria(nom, new Juego(juego), new Date(),
                                            new Premio(nomP, p_met), new ArrayList<Compite_E>()));
                                }
                                case "l" -> {
                                    competiciones.add(new Liga(nom, new Juego(juego), new Date(),
                                            new Premio(nomP, p_met), new ArrayList<Compite_L>()));
                                }
                                case "i" -> {
                                    competiciones.add(new Individual(nom, new Juego(juego), new Date(),
                                            new Premio(nomP, p_met), new ArrayList<Compite_I>()));
                                }
                                default -> {
                                }
                            }
                            modC = true;
                        }
                        default -> System.err.println("Tipo invalido.");
                    }
                }
                case 2 -> System.out.println(competiciones);
                case 3 -> {
                    System.out.print("Posicion del arraylist: ");
                    int pos = scan.nextInt();
                    try {
                        competiciones.remove(pos);
                        System.out.println("Eliminado.");
                        modC = true;
                    } catch (Exception e) {
                        System.out.println("Algo ha salido mal.");
                    }
                }
                case 0 -> {
                }
                default -> System.out.println("Opcion invalida.");
            }
            System.out.print(MENU);
            op = scan.nextInt();
            scan.nextLine();
        }
        if (modC)
            escribirCompeticion(competiciones);
        System.out.println("\n".repeat(20));
    }

    /**
     * Metodo que lee los datos de competiciones de un archivo <code>csv</code>.
     * @param competiciones ArrayList de <b>Competicion</b>
     * @since 1.2
     * @see Competicion
     */
    public static void leerCompeticion(ArrayList<Competicion> competiciones) {
        competiciones.clear();
        File fs = new File("./ByteScore/datos/competiciones.csv");
        if (fs.exists()) {
            try {
                FileReader fr = new FileReader(fs);
                BufferedReader br = new BufferedReader(fr);
                String cadena;

                while ((cadena = br.readLine()) != null) {
                    String strings[] = cadena.split(","), tipo = strings[0], nombre = strings[1],
                            f[] = strings[2].split("/"), premio = strings[4];
                    switch (tipo) {
                        case "e" -> {
                            String equi[] = new String[strings.length - 5];
                            System.arraycopy(strings, 5, equi, 0, equi.length);
                            ArrayList<Compite_E> equipos = new ArrayList<Compite_E>();
                            for (String string : equi)
                                equipos.add(new Compite_E(new Equipo(string)));
                            competiciones.add(new Eliminatoria(nombre, new Juego(), (new Date(Integer.parseInt(f[2]),
                                    Integer.parseInt(f[1]), Integer.parseInt(f[0]))), new Premio(premio), equipos));
                        }
                        case "l" -> {
                            String equi[] = new String[strings.length - 5];
                            System.arraycopy(strings, 5, equi, 0, equi.length);
                            ArrayList<Compite_L> equipos = new ArrayList<Compite_L>();
                            for (String string : equi)
                                equipos.add(new Compite_L(new Equipo(string)));
                            competiciones.add(new Liga(nombre, new Juego(), (new Date(Integer.parseInt(f[2]),
                                    Integer.parseInt(f[1]), Integer.parseInt(f[0]))), new Premio(premio), equipos));
                        }
                        case "i" -> {
                            String juga[] = new String[strings.length - 5];
                            System.arraycopy(strings, 5, juga, 0, juga.length);
                            ArrayList<Compite_I> jugadores = new ArrayList<Compite_I>();
                            for (String string : juga)
                                jugadores.add(new Compite_I(new Jugador(string)));
                            competiciones.add(new Individual(nombre, new Juego(), (new Date(Integer.parseInt(f[2]),
                                    Integer.parseInt(f[1]), Integer.parseInt(f[0]))), new Premio(premio), jugadores));
                        }
                        default -> System.err.println("Tipo raro de competicion: " + tipo);
                    }
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
     * Metodo que escribe los datos de competiciones a un archivo <code>csv</code>.
     * @param competiciones ArrayList de <b>Competicion</b>
     * @since 1.2
     * @see Competicion
     */
    public static void escribirCompeticion(ArrayList<Competicion> competiciones) {
        try {
            File fs = new File("./ByteScore/datos/competiciones.csv");
            FileWriter fw = new FileWriter(fs);
            for (Competicion c : competiciones) {
                String s = "";
                Date f = c.getfRealizacion();
                if (c instanceof Eliminatoria) {
                    s = "e," + c.getNombre() + "," + (f.getDay() + "/" + f.getMonth() + "/" + f.getYear()) + ",,"
                            + c.getPremio().getNombre_p() + "," + juntarE(((Eliminatoria) c).getEquipos(), ",");
                } else if (c instanceof Liga) {
                    s = "l," + c.getNombre() + "," + (f.getDay() + "/" + f.getMonth() + "/" + f.getYear()) + ",,"
                            + c.getPremio().getNombre_p() + "," + juntarL(((Liga) c).getEquipos(), ",");
                } else {
                    s = "l," + c.getNombre() + "," + (f.getDay() + "/" + f.getMonth() + "/" + f.getYear()) + ",,"
                            + c.getPremio().getNombre_p() + "," + juntarI(((Individual) c).getJugadores(), ",");
                }
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
     * Metodo de utilidad para juntar un <code>ArrayList</code> de <b>Compite_E</b> a una cadena de texto.
     * @param compite ArrayList de <b>Compite_E</b>
     * @param separador separador para insertar entre elementos
     * @return cadena de texto
     * @see Compite_E
     */
    public static String juntarE(ArrayList<Compite_E> compite, String separador) {
        String res = "";
        if (!compite.isEmpty()) {
            for (Compite_E c : compite)
                res += c.getEquipo().getNombre() + ",";
            res = res.substring(0, res.length() - 1);
        }
        return res;
    }

    /**
     * Metodo de utilidad para juntar un <code>ArrayList</code> de <b>Compite_L</b> a una cadena de texto.
     * @param compite ArrayList de <b>Compite_L</b>
     * @param separador separador para insertar entre elementos
     * @return cadena de texto
     * @see Compite_L
     */
    public static String juntarL(ArrayList<Compite_L> compite, String separador) {
        String res = "";
        if (!compite.isEmpty()) {
            for (Compite_L c : compite)
                res += c.getEquipo().getNombre() + ",";
            res = res.substring(0, res.length() - 1);
        }
        return res;
    }

    /**
     * Metodo de utilidad para juntar un <code>ArrayList</code> de <b>Compite_I</b> a una cadena de texto.
     * @param compite ArrayList de <b>Compite_I</b>
     * @param separador separador para insertar entre elementos
     * @return cadena de texto
     * @see Compite_I
     */
    public static String juntarI(ArrayList<Compite_I> compite, String separador) {
        String res = "";
        if (!compite.isEmpty()) {
            for (Compite_I c : compite)
                res += c.getJugador().getNombre() + ",";
            res = res.substring(0, res.length() - 1);
        }
        return res;
    }
}
