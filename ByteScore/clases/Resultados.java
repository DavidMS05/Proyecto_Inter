package clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Subsistema de puntuacion, guardado como resultados de los participantes en
 * competiciones.
 * 
 * @author Denys (3D)
 * @version 1.1
 * @see Competicion
 * @see Equipo
 * @see Jugador
 */
public class Resultados {
    /**
     * Metodo main.
     * 
     * @param args argumentos
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        final String MENU = "MENu RESULTADOS\n1. Registrar resultado\n2. Borrar resultado\n3. Exportar a HTML\n0. Salir\nSelecciona una opcion: ";
        ArrayList<Compite_E> ce = new ArrayList<Compite_E>();
        leerCompiteE("./ByteScore/datos/compite_e.csv", ce);
        ArrayList<Compite_L> cl = new ArrayList<Compite_L>();
        leerCompiteL("./ByteScore/datos/compite_l.csv", cl);
        ArrayList<Compite_I> ci = new ArrayList<Compite_I>();
        leerCompiteI("./ByteScore/datos/compite_i.csv", ci);
        ArrayList<Equipo> equipos = new ArrayList<Equipo>();
        leerEquipos("./ByteScore/datos/equipos.csv", equipos);
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        leerJugadores("./ByteScore/datos/jugadores.csv", jugadores);
        ArrayList<Competicion> competiciones = new ArrayList<Competicion>();
        leerCompeticion("./ByteScore/datos/competiciones.csv", competiciones);
        int op = -1;
        boolean modE = false, modL = false, modI = false;

        System.out.println("\n".repeat(20));

        while (op != 0) {
            System.out.print(MENU);
            op = scan.nextInt();
            scan.nextLine();
            switch (op) {
                case 1 -> {
                    System.out.print("¿Para que competicion quiere registrar el resultado? [e/l/i]: ");
                    String com = scan.nextLine();
                    switch (com) {
                        case "e" -> {
                            System.out.println("Nombre del equipo: ");
                            String equipo = scan.nextLine();
                            int posE;
                            if ((posE = buscarPos(equipos, equipo)) != -1) {
                                System.out.println("Nombre de la competicion: ");
                                String comp = scan.nextLine();
                                int posC;
                                if ((posC = buscarPosCompeticion(competiciones, comp)) != -1) {
                                    System.out.println("Posicion en la que le han eliminado: ");
                                    int pos = scan.nextInt();
                                    if (pos > 0) {
                                        ce.add(new Compite_E(equipos.get(posE), pos,
                                                (Eliminatoria) competiciones.get(posC)));
                                        System.out.println("El resultado se ha añadido con exito.");
                                        modE = true;
                                    } else
                                        System.err.println("Posicion invalida.");
                                } else
                                    System.err.println("Competicion no encontrada.");
                            } else
                                System.err.println("Equipo no encontrado.");
                        }

                        case "l" -> {
                            System.out.println("Nombre del equipo: ");
                            String equipo = scan.nextLine();
                            int posE;
                            if ((posE = buscarPos(equipos, equipo)) != -1) {
                                System.out.println("Nombre de la competicion: ");
                                String comp = scan.nextLine();
                                int posC;
                                if ((posC = buscarPosCompeticion(competiciones, comp)) != -1) {
                                    System.out.println("Fecha de finalizacion en formato \"dd mm aaaa\": ");
                                    int dia = scan.nextInt(), mes = scan.nextInt() - 1, anyo = scan.nextInt();
                                    try {
                                        GregorianCalendar fecha = new GregorianCalendar(anyo, mes, dia);
                                        System.out.println("Posicion en la que ha quedado: ");
                                        int pos = scan.nextInt();
                                        if (pos > 0) {
                                            cl.add(new Compite_L(equipos.get(posE), fecha.getTime(), pos,
                                                    (Liga) competiciones.get(posC)));
                                            System.out.println("El resultado se ha añadido con exito.");
                                            modL = true;
                                        } else
                                            System.err.println("Posicion invalida.");
                                    } catch (Exception e) {
                                        System.err.println("Fecha invalida.");
                                        e.printStackTrace();
                                    }
                                } else
                                    System.err.println("Competicion no encontrada.");
                            } else
                                System.err.println("Equipo no encontrado.");
                        }

                        case "i" -> {
                            System.out.println("DNI del jugador: ");
                            String dni = scan.nextLine();
                            int posJ;
                            if ((posJ = buscarPosJugador(jugadores, dni)) != -1) {
                                System.out.println("Nombre de la competicion: ");
                                String comp = scan.nextLine();
                                int posC;
                                if ((posC = buscarPosCompeticion(competiciones, comp)) != -1) {
                                    System.out.println("Ronda a la que ha llegado: ");
                                    int pos = scan.nextInt();
                                    if (pos > 0) {
                                        ci.add(new Compite_I(jugadores.get(posJ), pos,
                                                (Individual) competiciones.get(posC)));
                                        System.out.println("El resultado se ha añadido con exito.");
                                        modI = true;
                                    } else
                                        System.err.println("Posicion invalida.");
                                } else
                                    System.err.println("Competicion no encontrada.");
                            } else
                                System.err.println("Jugador no encontrado.");
                        }

                        default -> System.err.println("Error, tipo incorrecto.");
                    }
                }
                case 2 -> {
                    System.out.print("¿De que tipo de competicion quiere borrar un resultado? [e/l/i]: ");
                    String com = scan.nextLine();
                    switch (com) {
                        case "e" -> {
                            System.out.println("Nombre del equipo: ");
                            String equipo = scan.nextLine();
                            if (buscarPos(equipos, equipo) != -1) {
                                System.out.println("Nombre de la competicion: ");
                                String comp = scan.nextLine();
                                if (buscarPosCompeticion(competiciones, comp) != -1) {
                                    int pos;
                                    if ((pos = buscarPos(ce, comp, equipo)) != -1) {
                                        if (ce.remove(pos) != null) {
                                            System.out.println("El resultado se ha eliminado con exito.");
                                            modE = true;
                                        } else
                                            System.err.println("Se ha producido un error inesperado.");
                                    } else
                                        System.err.println("Posicion invalida.");
                                } else
                                    System.err.println("Competicion no encontrada.");
                            } else
                                System.err.println("Equipo no encontrado.");
                        }

                        case "l" -> {
                            System.out.println("Nombre del equipo: ");
                            String equipo = scan.nextLine();
                            if (buscarPos(equipos, equipo) != -1) {
                                System.out.println("Nombre de la competicion: ");
                                String comp = scan.nextLine();
                                if (buscarPosCompeticion(competiciones, comp) != -1) {
                                    int pos;
                                    if ((pos = buscarPos(cl, comp, equipo)) != -1) {
                                        if (cl.remove(pos) != null) {
                                            System.out.println("El resultado se ha eliminado con exito.");
                                            modL = true;
                                        } else
                                            System.err.println("Se ha producido un error inesperado.");
                                    } else
                                        System.err.println("Posicion invalida.");
                                } else
                                    System.err.println("Competicion no encontrada.");
                            } else
                                System.err.println("Equipo no encontrado.");
                        }

                        case "i" -> {
                            System.out.println("DNI del jugador: ");
                            String jugador = scan.nextLine();
                            if (buscarPosJugador(jugadores, jugador) != -1) {
                                System.out.println("Nombre de la competicion: ");
                                String comp = scan.nextLine();
                                if (buscarPosCompeticion(competiciones, comp) != -1) {
                                    int pos;
                                    if ((pos = buscarPos(ci, comp, jugador)) != -1) {
                                        if (ci.remove(pos) != null) {
                                            System.out.println("El resultado se ha eliminado con exito.");
                                            modI = true;
                                        } else
                                            System.err.println("Se ha producido un error inesperado.");
                                    } else
                                        System.err.println("Posicion invalida.");
                                } else
                                    System.err.println("Competicion no encontrada.");
                            } else
                                System.err.println("Jugador no encontrado.");
                        }

                        default -> System.err.println("Error, tipo incorrecto.");
                    }
                }
                case 3 -> {
                    System.out.print("¿Los resultados de que tipo de competicion quiere exportar? [e/l/i]: ");
                    String com = scan.nextLine();
                    switch (com) {
                        case "e" -> {
                            System.out.println("Nombre de la competicion: ");
                            String comp = scan.nextLine();
                            if (buscarPosCompeticion(competiciones, comp) != -1) {
                                exportarHTML(ce, comp);
                                System.out.println(
                                        "Exportado con exito a ByteScore/salida/compite_e_" + comp + ".html\"");
                            } else
                                System.err.println("Competicion no encontrada.");
                        }

                        case "l" -> {
                            System.out.println("Nombre de la competicion: ");
                            String comp = scan.nextLine();
                            if (buscarPosCompeticion(competiciones, comp) != -1) {
                                exportarHTML(cl, comp);
                                System.out.println(
                                        "Exportado con exito a ByteScore/salida/compite_l_" + comp + ".html\"");
                            } else
                                System.err.println("Competicion no encontrada.");
                        }

                        case "i" -> {
                            System.out.println("Nombre de la competicion: ");
                            String comp = scan.nextLine();
                            if (buscarPosCompeticion(competiciones, comp) != -1) {
                                exportarHTML(ci, comp);
                                System.out.println(
                                        "Exportado con exito a ByteScore/salida/compite_i_" + comp + ".html\"");
                            } else
                                System.err.println("Competicion no encontrada.");
                        }

                        default -> System.err.println("Error, tipo incorrecto.");
                    }
                }
                case 0 -> {
                    if (modE)
                        escribirCompite(ce);
                    if (modL)
                        escribirCompite(cl);
                    if (modI)
                        escribirCompite(ci);
                }
                default -> System.out.println("Opcion invalida.");
            }
        }
        System.out.println("\n".repeat(20));
    }

    /**
     * Metodo parametrizado para buscar resultados de competiciones con cierto
     * nombre y cierto participante.
     * 
     * @param <T>     <b>Compite_E</b>, <b>Compite_L</b> o <b>Compite_I</b>
     * @param a       ArrayList de resultados
     * @param comp    Nombre de la competicion
     * @param nom_dni Nombre del equipo o el <code>DNI</code> del jugador
     * @return su <code>posicion</code> o <code>-1</code> si no encontrado
     * @since 0.8
     * @see Compite_E
     * @see Compite_L
     * @see Compite_I
     */
    public static <T extends Compite> int buscarPos(ArrayList<T> a, String comp, String nom_dni) {
        int res = -1;
        for (int i = 0; i < a.size() && res == -1; i++)
            if (a.get(i).comparar(comp, nom_dni))
                res = i;
        return res;
    }

    /**
     * Metodo sobrecargado que busca un equipo con cierto nombre
     * 
     * @param equipos ArrayList de <b>Equipo</b>
     * @param nom     Nombre del equipo
     * @return <code>posicion</code> o <code>-1</code>
     * @since 0.8
     * @see Equipo
     */
    public static int buscarPos(ArrayList<Equipo> equipos, String nom) {
        int res = -1;
        for (int i = 0; i < equipos.size() && res == -1; i++)
            if (equipos.get(i).getNombre().equals(nom))
                res = i;
        return res;
    }

    /**
     * Metodo similar a <code>buscarPos</code>, nombre cambiado por tener tambien
     * ArrayList y
     * String como parametros.
     * 
     * @param jugadores ArrayList de <b>Jugador</b>
     * @param dni       <code>DNI</code> del jugador
     * @return <code>posicion</code> o <code>-1</code>
     * @since 0.8
     * @see Jugador
     */
    public static int buscarPosJugador(ArrayList<Jugador> jugadores, String dni) {
        int res = -1;
        for (int i = 0; i < jugadores.size() && res == -1; i++)
            if (jugadores.get(i).getNombre().equals(dni))
                res = i;
        return res;
    }

    /**
     * Metodo similar a <code>buscarPos</code>, nombre cambiado por tener tambien
     * ArrayList y
     * String como parametros.
     * 
     * @param competicion ArrayList de Competicion
     * @param nom         Nombre de la competicion
     * @return <code>posicion</code> o <code>-1</code>
     * @since 0.8
     * @see Competicion
     */
    public static int buscarPosCompeticion(ArrayList<Competicion> competicion, String nom) {
        int res = -1;
        for (int i = 0; i < competicion.size() && res == -1; i++)
            if (competicion.get(i).getNombre().equals(nom))
                res = i;
        return res;
    }

    /**
     * Metodo que lee el archivo <code>csv</code> con los resultados de las
     * competiciones de tipo
     * eliminatorias.
     * 
     * @param fichero ruta del fichero en String
     * @param ae      ArrayList de <b>Compite_E</b>
     * @since 0.9
     * @see Compite_E
     */
    public static void leerCompiteE(String fichero, ArrayList<Compite_E> ae) {
        ae.clear();
        File fs = new File(fichero);
        if (fs.exists()) {
            try {
                FileReader fr = new FileReader(fs);
                BufferedReader br = new BufferedReader(fr);
                String cadena;

                while ((cadena = br.readLine()) != null) {
                    String strings[] = cadena.split(",");
                    ae.add(new Compite_E(new Equipo(strings[0]), Integer.parseInt(strings[1]),
                            new Eliminatoria(strings[2])));
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
     * Metodo que lee el archivo <code>csv</code> con los resultados de las
     * competiciones de tipo
     * liga.
     * 
     * @param fichero ruta del fichero en String
     * @param al      ArrayList de <b>Compite_L</b>
     * @since 0.9
     * @see Compite_L
     */
    public static void leerCompiteL(String fichero, ArrayList<Compite_L> al) {
        al.clear();
        File fs = new File(fichero);
        if (fs.exists()) {
            try {
                FileReader fr = new FileReader(fs);
                BufferedReader br = new BufferedReader(fr);
                String cadena;

                while ((cadena = br.readLine()) != null) {
                    String strings[] = cadena.split(","), fecha[] = strings[1].split("/");
                    al.add(new Compite_L(new Equipo(strings[0]),
                            (new GregorianCalendar(Integer.parseInt(fecha[2]), Integer.parseInt(fecha[1]),
                                    Integer.parseInt(fecha[0]))).getTime(),
                            Integer.parseInt(strings[2]), new Liga(strings[3])));
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
     * Metodo que lee el archivo <code>csv</code> con los resultados de las
     * competiciones de tipo
     * individual.
     * 
     * @param fichero ruta del fichero en String
     * @param ai      ArrayList de <b>Compite_I</b>
     * @since 0.9
     * @see Compite_I
     */
    public static void leerCompiteI(String fichero, ArrayList<Compite_I> ai) {
        ai.clear();
        File fs = new File(fichero);
        if (fs.exists()) {
            try {
                FileReader fr = new FileReader(fs);
                BufferedReader br = new BufferedReader(fr);
                String cadena;

                while ((cadena = br.readLine()) != null) {
                    String strings[] = cadena.split(",");
                    ai.add(new Compite_I(new Jugador(strings[0]), Integer.parseInt(strings[1]),
                            new Individual(strings[2])));
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
     * Metodo que lee el archivo <code>csv</code> con los equipos existentes.
     * 
     * @param fichero ruta del fichero en String
     * @param equipos ArrayList de <b>Equipo</b>
     * @since 0.9
     * @see Equipo
     */
    public static void leerEquipos(String fichero, ArrayList<Equipo> equipos) {
        equipos.clear();
        File fs = new File(fichero);
        if (fs.exists()) {
            try {
                FileReader fr = new FileReader(fs);
                BufferedReader br = new BufferedReader(fr);
                String cadena;

                while ((cadena = br.readLine()) != null) {
                    String strings[] = cadena.split(","), nombre = strings[0];
                    equipos.add(new Equipo(nombre));
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
     * Metodo que lee el archivo <code>csv</code> con los jugadores existentes.
     * 
     * @param fichero   ruta del fichero en String
     * @param jugadores ArrayList de <b>Jugador</b>
     * @since 0.9
     * @see Jugador
     */
    public static void leerJugadores(String fichero, ArrayList<Jugador> jugadores) {
        jugadores.clear();
        File fs = new File(fichero);
        if (fs.exists()) {
            try {
                FileReader fr = new FileReader(fs);
                BufferedReader br = new BufferedReader(fr);
                String cadena;

                while ((cadena = br.readLine()) != null) {
                    String strings[] = cadena.split(","), dni = strings[0];
                    jugadores.add(new Jugador(dni));
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
     * Metodo que lee el archivo <code>csv</code> con las competiciones registradas.
     * 
     * @param fichero       ruta del fichero en String
     * @param competiciones ArrayList de <b>Competicion</b>
     * @since 0.9
     * @see Competicion
     */
    public static void leerCompeticion(String fichero, ArrayList<Competicion> competiciones) {
        competiciones.clear();
        File fs = new File(fichero);
        if (fs.exists()) {
            try {
                FileReader fr = new FileReader(fs);
                BufferedReader br = new BufferedReader(fr);
                String cadena;

                while ((cadena = br.readLine()) != null) {
                    String strings[] = cadena.split(","), tipo = strings[0], nombre = strings[1];
                    competiciones.add(switch (tipo) {
                        case "e" -> new Eliminatoria(nombre);
                        case "l" -> new Liga(nombre);
                        case "i" -> new Individual(nombre);
                        default -> null;
                    });
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
     * Metodo universal que exporta los resultados de una competicion cualquiera a
     * una
     * tabla <code>html</code>.
     * 
     * @param competiciones  ArrayList de <b>clase que implementa Compite</b>
     * @param nomCompeticion nombre de la competicion
     * @since 1.1
     * @see Compite
     */
    public static <T extends Compite> void exportarHTML(ArrayList<T> competiciones, String nomCompeticion) {
        try {
            File fs = new File("./ByteScore/salida/compite_" + competiciones.get(0).letra() +
                    "_" + nomCompeticion.replaceAll(" ", "-") + ".html");
            FileWriter fw = new FileWriter(fs);
            String s = competiciones.get(0).htmlHeader(nomCompeticion);
            for (T c : competiciones)
                if (nomCompeticion.equals(c.getCompeticion().getNombre()))
                    s += c.htmlContent();
            s += "</table></body></html>";
            fw.write(s, 0, s.length());
            fw.write("\r\n");
            if (fw != null)
                fw.close();
        } catch (IOException e) {
            System.err.println("ERROR");
            e.printStackTrace();
        }
    }

    /**
     * Escribe los datos de un ArrayList de cualquier subtipo de <b>Compite</b> a un archivo <code>csv</code>.
     * 
     * @param al ArrayList de <b>subtipo de Compite</b>
     * @since 1.1
     * @see Compite
     */
    public static <T extends Compite> void escribirCompite(ArrayList<T> al) {
        try {
            File fs = new File("./ByteScore/datos/compite_" + al.get(0).letra() + ".csv");
            FileWriter fw = new FileWriter(fs);
            for (T c : al) {
                fw.write(c.escribirCSV());
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
