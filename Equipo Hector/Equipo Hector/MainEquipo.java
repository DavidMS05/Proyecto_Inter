import java.util.*;

public class MainEquipo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Equipo> equipos = new ArrayList<>();

        int opcion = -1;
        System.out.println("Bienvenido al manager de equipos ESports de ByteScore.");

        while (opcion != 0) {
            System.out.println("1. Consultar equipo\n2. Crear nuevo equipo\n3. Eliminar equipo\n0. Salir");
            opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {
                if (equipos.isEmpty()) {
                    System.out.println("No hay equipos registrados.");
                } else {
                    System.out.println("Selecciona un equipo (1, 2, 3...). Pulse 0 para volver al menú.");
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
                    }
                }
            }
        }

        System.out.println("Gracias por usar nuestros servicios. Buena suerte, jugadores.");
        scanner.close();
    }
}