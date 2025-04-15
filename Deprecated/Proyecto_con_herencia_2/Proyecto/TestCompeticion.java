public class TestCompeticion {
    public static void main(String[] args) {
        // Crear un trofeo
        Premio recompensa;
        recompensa = new Premio("Campeón ", 1000, "El mejor jugador");

        // Crear competición individual
        Competicion_Individual competicion_Individual = new Competicion_Individual("Torneo 1v1", 2024/11/2006, "Overwatch", recompensa,"Linavy", "Nava", "Frim", "Rine", null, null);

        competicion_Individual.registrarRonda("Lina", 5);
        competicion_Individual.registrarRonda("Nava", 7);

        // Mostrar detalles de la competición individual
        competicion_Individual.mostrarCompeticion();

        // Crear competición de liga
        Competicion_Liga competicionLiga = new Competicion_Liga("Liga OWN", 2024/07/07, "FIFA24", recompensa, "Equipo 1", 1, "Equipo 2", 2, 2024/07/15);

        // Mostrar detalles de la competición de liga
        competicionLiga.mostrarCompeticion();
    }
}