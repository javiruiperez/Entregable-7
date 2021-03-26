import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);
        boolean Salir = false;
        while (!Salir) {
            System.out.println(" ");
            System.out.println("¿Que desea hacer?");
            System.out.println("--------------------------------");
            System.out.println("1-Crear tarea");
            System.out.println("2-Ver tareas pendientes");
            System.out.println("3-Completar tarea pendiente");
            System.out.println("4-Ver tareas completadas");
            System.out.println("5-Salir");
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1 -> CrearTarea.CrearTareas();
                case 2 -> CrearTarea.VerTareas();
                case 3 -> CompletarTarea.CompletarTareas();
                case 4 -> CompletarTarea.VerTareas();
                case 5 -> {
                    Salir = true;
                    System.out.println("Hasta tumorrou");
                }
            }
        }
    }
}