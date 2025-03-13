
import java.util.Scanner;

public class TestUsuario {

    public static void main(String[] args) {
        Scanner tecla = new Scanner(System.in);
        Usuario u1 = new Usuario();
        int opcion;
        do {
            System.out.println(">Selecciona una opcion");
            System.out.println(">Crar cuenta 1");
            System.out.println(">Visualizar cuenta 2");
            System.out.println(">Mas opciones 3");
            System.out.println(">Salir = 0");
            opcion = tecla.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Como te llamas:");
                    tecla.nextLine();
                    u1.setNombre(tecla.nextLine());
                    System.out.print("Ingrese gmail: ");
                    u1.setGmail(tecla.nextLine());
                    System.out.print("Ingrese contraseña: ");
                    u1.setPassword(tecla.nextLine());
                    System.out.print("Ingrese DNI 'número': ");
                    u1.setDni(tecla.nextInt());
                    System.out.print("Ingrese letra del DNI: ");
                    u1.setLetra(tecla.next().charAt(0));
                    System.out.print("Ingrese fecha de nacimiento (YYYYMMDD): ");
                    u1.setFn(tecla.nextInt());
                    break;
                case 2:
                    u1.mostrar();
                    break;
                case 3:
                    System.out.println("(funcionalidad no implementada aún)");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (opcion != 0);
    }
}
