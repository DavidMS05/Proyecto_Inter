package clases;

import db.Jugador_DB;
import db.Liga_DB;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import db.Competicion_DB;
import db.Conexion_DB;
import db.Eliminatoria_DB;
import db.Equipo_DB;
import db.Individual_DB;

public class TestConnector {
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
            // jugadores(_con, scan);
            // equipos(_con, scan);
            Competicion_DB cDB = new Competicion_DB();
            Eliminatoria_DB eDB = new Eliminatoria_DB();
            Liga_DB lDB = new Liga_DB();
            Individual_DB iDB = new Individual_DB();
            ArrayList<Competicion> competiciones = new ArrayList<Competicion>(cDB.cargarEliminatorias(_con));
            competiciones.addAll(cDB.cargarLigas(_con));
            competiciones.addAll(cDB.cargarIndividuales(_con));
            final String MENU = "\nMENu COMPETICIONES\n1. Insertar\n2. Mostrar\n3. Borrar\n0. Salir\nSelecciona una opcion: ";
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
                                System.out.print("Nombre del juego: ");
                                String juego = scan.nextLine();
                                System.out.print("Nombre del premio: ");
                                String nomP = scan.nextLine();
                                System.out.print("Premio en metalico: ");
                                float p_met = scan.nextFloat();
                                Competicion c = null;
                                switch (com) {
                                    case "e" -> {
                                        c = new Eliminatoria(nom, new Juego(juego), new Date(),
                                                new Premio(nomP, p_met), new ArrayList<Compite_E>());
                                        competiciones.add(c);
                                        eDB.inserta(_con, (Eliminatoria) c);
                                    }
                                    case "l" -> {
                                        c = new Liga(nom, new Juego(juego), new Date(),
                                                new Premio(nomP, p_met), new ArrayList<Compite_L>());
                                        competiciones.add(c);
                                        lDB.inserta(_con, (Liga) c);
                                    }
                                    case "i" -> {
                                        c = new Individual(nom, new Juego(juego), new Date(),
                                                new Premio(nomP, p_met), new ArrayList<Compite_I>());
                                        competiciones.add(c);
                                        iDB.inserta(_con, (Individual) c);
                                    }
                                    default -> {
                                    }
                                }
                                cDB.inserta(_con, c);
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
                            cDB.elimina(_con, c);
                            System.out.println("Eliminado.");
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
            System.out.println("\n".repeat(20));
            // fin programa
            _con.commit();
        } catch (Exception ex) {
            System.out.println("Exception -> " + ex.getMessage());
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
            System.out.println("2. Mostrar Jugadores");
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
                    System.out.print("Fecha de nacimiento (DD/MM/AAAA): ");
                    String fechaNacimiento = scan.nextLine();
                    System.out.print("Email: ");
                    String email = scan.nextLine();
                    System.out.print("Contraseña: ");
                    String password = scan.nextLine();

                    Jugador j = new Jugador(nombre, dni, fechaNacimiento, email, password);
                    jugadores.add(j);
                    jDB.inserta(con, j);
                    System.out.println("Jugador agregado correctamente.");
                    break;

                case 2:
                    if (jugadores.isEmpty()) {
                        System.out.println("No hay jugadores registrados.");
                    } else {
                        System.out.println("\nLista de Jugadores:");
                        for (Jugador ju : jugadores) {
                            ju.mostrarInfo();
                        }
                    }
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
        int opcion = -1;

        System.out.println("\n".repeat(20));

        while (opcion != 0) {
            System.out.print(
                    "\nMENu EQUIPOS\n1. Consultar equipo\n2. Crear nuevo equipo\n3. Eliminar equipo\n0. Salir\nSelecciona una opcion: ");
            opcion = scan.nextInt();
            scan.nextLine();

            if (opcion == 1) {
                if (equipos.isEmpty()) {
                    System.out.println("No hay equipos registrados.");
                } else {
                    System.out.println("Selecciona un equipo (1, 2, 3...). Pulse 0 para volver al menu.");
                    for (int i = 0; i < equipos.size(); i++) {
                        System.out.println((i + 1) + ". " + equipos.get(i).getNombre());
                    }
                    int equipoIndex = scan.nextInt();
                    scan.nextLine();
                    if (equipoIndex > 0 && equipoIndex <= equipos.size()) {
                        System.out.println(equipos.get(equipoIndex - 1).getPlayers());
                    }
                }
            } else if (opcion == 2) {
                System.out.print("Elige el nombre del nuevo equipo. ");
                String nombreEquipo = scan.nextLine();
                ArrayList<String> jugadores = new ArrayList<>();
                for (int i = 1; i <= 5; i++) {
                    System.out.print("Dime el nombre del jugador " + i + ". ");
                    jugadores.add(scan.nextLine());
                }
                Equipo eq = new Equipo(nombreEquipo, jugadores);
                eq.setCod(equipos.size() + 1);
                equipos.add(eq);
                eDB.inserta(con, eq);
                System.out.println("Perfecto! Equipo creado exitosamente.");
            } else if (opcion == 3) {
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
        }

        System.out.println("\n".repeat(20));
    }
}
