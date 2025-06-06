package clases;

import db.Jugador_DB;
import db.Liga_DB;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import db.Competicion_DB;
import db.Compite_E_DB;
import db.Compite_I_DB;
import db.Compite_L_DB;
import db.Conexion_DB;
import db.Eliminatoria_DB;
import db.Equipo_DB;
import db.Forma_DB;
import db.Individual_DB;
import db.Juego_DB;

/**
 * @author Denys (3D)
 * @version 1.5
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Conexion_DB _conexion_DB = new Conexion_DB();
        Connection _con = null;
        try {
            _con = _conexion_DB.AbrirConexion();// Abrimos la conexión
            System.out.println("Conexion abierta");
            _con.setAutoCommit(false);
            _con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            Scanner scan = new Scanner(System.in);
            // programa

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
                        jugadores(_con, scan);
                    }
                    case 2 -> {
                        equipos(_con, scan);
                    }
                    case 3 -> {
                        resultados(_con, scan);
                    }
                    case 4 -> {
                        competiciones(_con, scan);
                    }
                    default -> System.err.println("Opcion incorrecta, inserte un numero del menu.");
                }
                _con.commit();
            }
            System.out.println("Gracias por usar nuestros servicios, todos los cambios han sido guardados con éxito.");

            // fin programa
            // _con.commit(); <- Hay commit en el bucle
        } catch (Exception ex) {
            System.out.println("Exception -> " + ex.getMessage());
            ex.printStackTrace();
            if (_con != null)
                _con.rollback();
        } finally {
            if (_con != null)
                _conexion_DB.CerrarConexion(_con);
            System.out.println("Conexion cerrada");
        }
    }

    public static void jugadores(Connection con, Scanner scan) throws Exception {
        Jugador_DB jDB = new Jugador_DB();
        ArrayList<Jugador> jugadores = new ArrayList<>(jDB.cargarJugadores(con));
        int opcion = -1;

        System.out.println("\n".repeat(20));

        while (opcion != 0) {
            System.out.println("\nMENu JUGADORES");
            System.out.println("1. Agregar Jugador");
            System.out.println("2. Mostrar Jugador");
            System.out.println("3. Eliminar Jugador");
            System.out.println("4. Cargar fichero Jugadores");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opcion: ");

            opcion = scan.nextInt();
            scan.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del jugador: ");
                    String nombre = scan.nextLine();
                    System.out.print("DNI del jugador: ");
                    String dni = scan.nextLine();
                    System.out.print("Fecha de nacimiento (AAAA-MM-DD): ");
                    String fechaNacimiento = scan.nextLine();
                    System.out.print("Email: ");
                    String email = scan.nextLine();
                    System.out.print("Contraseña: ");
                    String password = scan.nextLine();

                    Jugador j = new Jugador(nombre, dni.toLowerCase(), java.sql.Date.valueOf(fechaNacimiento), email,
                            password);
                    jDB.inserta(con, j);
                    jugadores.add(jDB.findByDni(con, j));
                    System.out.println("Jugador agregado correctamente.");
                    break;

                case 2:
                    if (jugadores.isEmpty()) {
                        System.out.println("No hay jugadores registrados.");
                    } else {
                        /*
                         * System.out.println("\nLista de Jugadores:");
                         * for (Jugador ju : jugadores) {
                         * ju.mostrarInfo();
                         * }
                         */

                        System.out.println("Selecciona un jugador (1, 2, 3...). Pulse 0 para volver al menu.");
                        for (int i = 0; i < jugadores.size(); i++) {
                            System.out.println((i + 1) + ". " + jugadores.get(i).getNombre());
                        }
                        int jugadorIndex = scan.nextInt() - 1;
                        // scan.nextLine();
                        if (jugadorIndex > 0 && jugadorIndex <= jugadores.size()) {
                            System.out.println(jugadores.get(jugadorIndex));
                        }
                    }
                    break;

                case 3:
                    if (jugadores.isEmpty()) {
                        System.out.println("No hay jugadores para eliminar.");
                    } else {
                        System.out.println("Selecciona el jugador a eliminar (1, 2, 3...). Pulsa 0 para cancelar.");
                        for (int i = 0; i < jugadores.size(); i++) {
                            System.out.println((i + 1) + ". " + jugadores.get(i).getNombre());
                        }
                        int jugadorIndex = scan.nextInt();
                        scan.nextLine();
                        if (jugadorIndex > 0 && jugadorIndex <= jugadores.size()) {
                            Jugador jug = jugadores.get(jugadorIndex - 1);
                            jugadores.remove(jugadorIndex - 1);
                            jDB.elimina(con, jug);
                            System.out.println("Perfecto. Jugador eliminado exitosamente.");
                        }
                    }
                    break;

                case 4:
                    System.out.println("Por favor, comprueba que el archivo se encuentra en ByteScore/datos/");
                    System.out.println("y tiene el formato 'dni,email,nombre,fecha(aaaa-mm-dd),contraseña'");
                    System.out.println(
                            "A continuación, inserte el nombre del archivo en formato CSV, incluyendo la extensión:");
                    String archivo = scan.nextLine();
                    leerJugadores(con, jugadores, archivo);
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opcion no valida. Intenta de nuevo.");
            }
        }
        System.out.println("\n".repeat(20));
    }

    public static void equipos(Connection con, Scanner scan) throws Exception {
        Equipo_DB eDB = new Equipo_DB();
        ArrayList<Equipo> equipos = new ArrayList<>(eDB.cargarEquipos(con));
        final String MENU = "\nMENu EQUIPOS\n1. Consultar equipo\n2. Crear nuevo equipo\n3. Eliminar equipo\n4. Añadir jugadores a un equipo\n0. Salir\nSelecciona una opcion: ";
        int opcion = -1;

        System.out.println("\n".repeat(20));

        while (opcion != 0) {
            System.out.print(MENU);
            opcion = scan.nextInt();
            scan.nextLine();
            switch (opcion) {
                // Consultar equipo
                case 1 -> {
                    if (equipos.isEmpty()) {
                        System.out.println("No hay equipos registrados.");
                    } else {
                        // System.out.println(equipos);
                        System.out.println("Selecciona un equipo (1, 2, 3...). Pulse 0 para volver al menu.");
                        for (int i = 0; i < equipos.size(); i++) {
                            System.out.println((i + 1) + ". " + equipos.get(i).getNombre());
                        }
                        int equipoIndex = scan.nextInt() - 1;
                        // scan.nextLine();
                        if (equipoIndex > 0 && equipoIndex <= equipos.size()) {
                            System.out.println(equipos.get(equipoIndex));
                        }
                        /*
                         * if (equipoIndex > 0 && equipoIndex <= equipos.size()) {
                         * System.out.println(equipos.get(equipoIndex - 1).getPlayers());
                         * }
                         */
                    }
                }
                // Crear equipo
                case 2 -> {
                    System.out.print("Elige el nombre del nuevo equipo. ");
                    String nombreEquipo = scan.nextLine();
                    /*
                     * ArrayList<String> jugadores = new ArrayList<>();
                     * for (int i = 1; i <= 5; i++) {
                     * System.out.print("Dime el nombre del jugador " + i + ". ");
                     * jugadores.add(scan.nextLine());
                     * }
                     */
                    if (eDB.findByNom(con, nombreEquipo) == null) {
                        Equipo eq = new Equipo(nombreEquipo);
                        eDB.inserta(con, eq);
                        equipos.add(eDB.findByNom(con, nombreEquipo));
                        System.out.println("Perfecto! Equipo creado exitosamente.");
                    } else {
                        System.err.println("Ese nombre ya está ocupado.");
                    }
                }
                // Eliminar equipo
                case 3 -> {
                    if (equipos.isEmpty()) {
                        System.out.println("No hay equipos para eliminar.");
                    } else {
                        System.out.println("Selecciona el equipo a eliminar (1, 2, 3...). Pulsa 0 para cancelar.");
                        for (int i = 0; i < equipos.size(); i++) {
                            System.out.println((i + 1) + ". " + equipos.get(i).getNombre());
                        }
                        int equipoIndex = scan.nextInt();
                        scan.nextLine();
                        if (equipoIndex > 0 && equipoIndex <= equipos.size()) {
                            Equipo eq = equipos.get(equipoIndex - 1);
                            equipos.remove(equipoIndex - 1);
                            eDB.elimina(con, eq);
                            System.out.println("Perfecto. Equipo eliminado exitosamente.");
                        }
                    }
                }
                // Añadir jugadores a equipo
                case 4 -> {
                    if (equipos.isEmpty()) {
                        System.out.println("No hay equipos registrados.");
                    } else {
                        System.out.println("Selecciona un equipo (1, 2, 3...). Pulse 0 para volver al menu.");
                        for (int i = 0; i < equipos.size(); i++) {
                            System.out.println((i + 1) + ". " + equipos.get(i).getNombre());
                        }
                        int equipoIndex = scan.nextInt() - 1;
                        scan.nextLine();
                        if (equipoIndex > 0 && equipoIndex <= equipos.size()) {
                            System.out.println("Inserte los DNI de los jugadores a insertar uno a uno, deje el campo en blanco para terminar.");
                            String dni = scan.nextLine().toLowerCase();
                            Equipo e = equipos.get(equipoIndex);
                            Jugador_DB jDB = new Jugador_DB();
                            Forma_DB fDB = new Forma_DB();
                            while (!dni.equals("")) {
                                Jugador j = jDB.findByDni(con, new Jugador(dni));
                                if (!j.equals(null)) {
                                    if (fDB.findByIdDni(con, new Forma(e, j, false, false)) != null) {
                                        System.out.print("¿Es capitan?[si/no]: ");
                                        boolean capitan = scan.nextLine().equals("si");
                                        System.out.print("¿Es titular?[si/no]: ");
                                        boolean titular = scan.nextLine().equals("si");
                                        fDB.inserta(con, new Forma(e, j, capitan, titular));
                                        System.out.println("Insertado.");
                                    } else
                                        System.err.println("Jugador ya está en el equipo.");
                                } else
                                    System.out.println("Jugador no encontrado.");
                                dni = scan.nextLine();
                            }
                            System.out.print("Insertados con éxito, ¿desea guardar los cambios?[si/no]: ");
                            if (scan.nextLine().toLowerCase().equals("si")) {
                                con.commit();
                                System.out.println("Guardado.");
                            } else
                                System.out.println("Los cambios se guardaran al salir del módulo.");
                        }
                    }
                }
                // Salir
                case 0 -> {
                }
                // Default
                default -> System.out.println("Opción inválida.");
            }
        }

        System.out.println("\n".repeat(20));
    }

    public static void competiciones(Connection con, Scanner scan) throws Exception {
        Competicion_DB cDB = new Competicion_DB();
        Eliminatoria_DB eDB = new Eliminatoria_DB();
        Juego_DB jDB = new Juego_DB();
        Liga_DB lDB = new Liga_DB();
        Individual_DB iDB = new Individual_DB();
        ArrayList<Competicion> competiciones = new ArrayList<Competicion>(cDB.cargarTodos(con));
        final String MENU = "\nMENu COMPETICIONES\n1. Insertar\n2. Mostrar\n3. Borrar\n4. Añadir juego\n0. Salir\nSelecciona una opcion: ";
        int op;

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
                            if (!cDB.findByNom(con, nom)) {
                                System.out.print("Nombre del juego: ");
                                String juego = scan.nextLine();
                                if (jDB.findByNom(con, juego) != null) {
                                    //System.out.print("Nombre del premio: ");
                                    //String nomP = scan.nextLine();
                                    //System.out.print("Premio en metalico: ");
                                    //float p_met = scan.nextFloat();
                                    Competicion c = null;
                                    switch (com) {
                                        case "e" -> {
                                            c = new Eliminatoria(nom, jDB.findByNom(con, juego), new Date());//,
                                                    //new Premio(nomP, p_met), new ArrayList<Compite_E>());
                                            cDB.inserta(con, c);
                                            eDB.inserta(con, (Eliminatoria) c);
                                            c = cDB.findByNom(con, nom, new Eliminatoria());
                                        }
                                        case "l" -> {
                                            c = new Liga(nom, jDB.findByNom(con, juego), new Date());//,
                                                    //new Premio(nomP, p_met), new ArrayList<Compite_L>());
                                            cDB.inserta(con, c);
                                            lDB.inserta(con, (Liga) c);
                                            c = cDB.findByNom(con, nom, new Liga());
                                        }
                                        case "i" -> {
                                            c = new Individual(nom, jDB.findByNom(con, juego), new Date());//,
                                                    //new Premio(nomP, p_met), new ArrayList<Compite_I>());
                                            cDB.inserta(con, c);
                                            iDB.inserta(con, (Individual) c);
                                            c = cDB.findByNom(con, nom, new Individual());
                                        }
                                        default -> {
                                        }
                                    }
                                    competiciones.add(c);
                                    System.out.println("Competición creada.");
                                } else {
                                    System.err.println("Juego no encontrado.");
                                }
                            } else {
                                System.err.println("Esa competición ya está registrada.");
                            }
                        }
                        default -> System.err.println("Tipo invalido.");
                    }
                }
                case 2 -> System.out.println(competiciones);
                case 3 -> {
                    System.out.print("Posicion del arraylist: ");
                    int pos = scan.nextInt();
                    try {
                        Competicion c = competiciones.get(pos);
                        competiciones.remove(pos);
                        cDB.elimina(con, c);
                        System.out.println("Eliminado.");
                    } catch (Exception e) {
                        System.out.println("Algo ha salido mal.");
                    }
                }
                case 4 -> {
                    System.out.print("Nombre del juego: ");
                    String nom = scan.nextLine();
                    if (jDB.findByNom(con, nom) == null) {
                        jDB.inserta(con, new Juego(nom));
                    } else {
                        System.err.println("Ese juego ya está registrado.");
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
        System.out.println("\n".repeat(20));
    }

    public static void resultados(Connection con, Scanner scan) throws Exception {
        Compite_E_DB eDB = new Compite_E_DB();
        Compite_L_DB lDB = new Compite_L_DB();
        Compite_I_DB iDB = new Compite_I_DB();
        final String MENU = "MENu RESULTADOS\n1. Registrar resultado\n2. Borrar resultado\n3. Exportar a HTML\n0. Salir\nSelecciona una opcion: ";
        ArrayList<Compite_E> ce = new ArrayList<Compite_E>(eDB.cargarCompite(con));
        ArrayList<Compite_L> cl = new ArrayList<Compite_L>(lDB.cargarCompite(con));
        ArrayList<Compite_I> ci = new ArrayList<Compite_I>(iDB.cargarCompite(con));
        ArrayList<Equipo> equipos = new ArrayList<Equipo>(new Equipo_DB().cargarEquipos(con));
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>(new Jugador_DB().cargarJugadores(con));
        ArrayList<Competicion> competiciones = new ArrayList<Competicion>(new Competicion_DB().cargarTodos(con));
        int op = -1;

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
                                        Compite_E c = new Compite_E(equipos.get(posE), pos,
                                                (Eliminatoria) competiciones.get(posC));
                                        ce.add(c);
                                        eDB.inserta(con, c);
                                        System.out.println("El resultado se ha añadido con exito.");
                                        // modE = true;
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
                                            Compite_L c = new Compite_L(equipos.get(posE), fecha.getTime(), pos,
                                                    (Liga) competiciones.get(posC));
                                            cl.add(c);
                                            lDB.inserta(con, c);
                                            System.out.println("El resultado se ha añadido con exito.");
                                            // modL = true;
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
                                        Compite_I c = new Compite_I(jugadores.get(posJ), pos,
                                                (Individual) competiciones.get(posC));
                                        ci.add(c);
                                        iDB.inserta(con, c);
                                        System.out.println("El resultado se ha añadido con exito.");
                                        // modI = true;
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
                                        Compite_E c;
                                        if ((c = ce.remove(pos)) != null) {
                                            eDB.elimina(con, c);
                                            System.out.println("El resultado se ha eliminado con exito.");
                                            // modE = true;
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
                                        Compite_L c;
                                        if ((c = cl.remove(pos)) != null) {
                                            lDB.elimina(con, c);
                                            System.out.println("El resultado se ha eliminado con exito.");
                                            // modL = true;
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
                                        Compite_I c;
                                        if ((c = ci.remove(pos)) != null) {
                                            iDB.elimina(con, c);
                                            System.out.println("El resultado se ha eliminado con exito.");
                                            // modI = true;
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
                                ce.clear();
                                ce.addAll(eDB.cargarCompite(con));
                                String ruta = exportarHTML(ce, comp);
                                if (!ruta.equals(""))
                                    System.out.println("Exportado con éxito a " + ruta);
                                else
                                    System.err.println("Ha habido un problema inesperado.");
                            } else
                                System.err.println("Competicion no encontrada.");
                        }

                        case "l" -> {
                            System.out.println("Nombre de la competicion: ");
                            String comp = scan.nextLine();
                            if (buscarPosCompeticion(competiciones, comp) != -1) {
                                cl.clear();
                                cl.addAll(lDB.cargarCompite(con));
                                exportarHTML(cl, comp);
                                System.out.println(
                                        "Exportado con exito a /salida/compite_l_" + comp + ".html\"");
                            } else
                                System.err.println("Competicion no encontrada.");
                        }

                        case "i" -> {
                            System.out.println("Nombre de la competicion: ");
                            String comp = scan.nextLine();
                            if (buscarPosCompeticion(competiciones, comp) != -1) {
                                ci.clear();
                                ci.addAll(iDB.cargarCompite(con));
                                exportarHTML(ci, comp);
                                System.out.println(
                                        "Exportado con exito a /salida/compite_i_" + comp + ".html\"");
                            } else
                                System.err.println("Competicion no encontrada.");
                        }

                        default -> System.err.println("Error, tipo incorrecto.");
                    }
                }
                case 0 -> {
                    // if (modE)
                    // escribirCompite(ce);
                    // if (modL)
                    // escribirCompite(cl);
                    // if (modI)
                    // escribirCompite(ci);
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
    private static <T extends Compite> int buscarPos(ArrayList<T> a, String comp, String nom_dni) {
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
    private static int buscarPos(ArrayList<Equipo> equipos, String nom) {
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
    private static int buscarPosJugador(ArrayList<Jugador> jugadores, String dni) {
        int res = -1;
        for (int i = 0; i < jugadores.size() && res == -1; i++)
            if (jugadores.get(i).getDni().equals(dni))
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
    private static int buscarPosCompeticion(ArrayList<Competicion> competicion, String nom) {
        int res = -1;
        for (int i = 0; i < competicion.size() && res == -1; i++)
            if (competicion.get(i).getNombre().equals(nom))
                res = i;
        return res;
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
    private static <T extends Compite> String exportarHTML(ArrayList<T> competiciones, String nomCompeticion) {
        String ruta = "";
        try {
            ruta = "./salida/compite_" + competiciones.get(0).letra() +
                    "_" + nomCompeticion.replaceAll(" ", "-") + ".html";
            File fs = new File(ruta);
            fs.getParentFile().mkdirs();
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
        return ruta;
    }

    /**
     * Metodo que lee los datos de jugadores de un archivo <code>csv</code>.
     * 
     * @param jugadores ArrayList de Jugador
     * @since 1.6
     * @see Jugador
     */
    public static void leerJugadores(Connection con, ArrayList<Jugador> jugadores, String archivo) {
        // jugadores.clear();
        File fs = new File("./ByteScore/datos/" + archivo);
        if (fs.exists()) {
            int i = 0;
            try {
                FileReader fr = new FileReader(fs);
                BufferedReader br = new BufferedReader(fr);
                String cadena;
                Jugador_DB jDB = new Jugador_DB();

                while ((cadena = br.readLine()) != null) {
                    String strings[] = cadena.split(","), dni = strings[0], email = strings[1], nombre = strings[2],
                            f = strings[3], pwd = strings[4];
                    Jugador j = new Jugador(nombre, dni.toLowerCase(), java.sql.Date.valueOf(f), email, pwd);
                    jDB.inserta(con, j);
                    jugadores.add(jDB.findByDni(con, j));
                    i++;
                }
                if (fr != null)
                    fr.close();
            } catch (IOException e) {
                System.err.println("ERROR");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println(
                        "Ha ocurrido un error y la operación ha sido cancelada. Por favor, comprueba las líneas " + i
                                + " o " + (i + 1));
                System.out.println("Sintaxis necesaria:\ndni,email,nombre,fecha(aaaa-mm-dd),contraseña");
            }
        } else
            System.err.println("Archivo no encontrado.");
    }
}