import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        GestorReserva gestor = new GestorReserva(3);
        Scanner scanner = new Scanner(System.in);

        gestor.mostrarSalas();

        System.out.println("\n--- PRUEBA DE MANEJO DE ENTRADAS NO NUMÉRICAS ---");
        System.out.print("Introduce la cantidad de asistentes (escribe letras a propósito para probar el control de errores): ");
        String entradaAsistentes = scanner.nextLine();

        int asistentes = 0;
        try {
            
            asistentes = Integer.parseInt(entradaAsistentes);
            System.out.println("Número capturado con éxito: " + asistentes);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR MANEJADO]: La entrada proporcionada no es un número válido. Se asignará 1 asistente por defecto.");
            asistentes = 1;
        }

        System.out.println("\n--- EJECUTANDO PRUEBAS DEL SISTEMA ---");
        
        
        LocalDateTime horaReserva1 = LocalDateTime.now().plusHours(2);
        gestor.crearReserva("S1", horaReserva1, "Carlos Mendoza", asistentes);

       
        System.out.println("\n-> Intentando solapar horarios:");
        gestor.crearReserva("S1", horaReserva1, "Marta Gómez", 5);

      
        System.out.println("\n-> Consultando estado actual de Sala 1:");
        gestor.consultarPorSala("S1");

       
        System.out.println("\n-> Intentando cancelar ID inválido:");
        gestor.cancelarReserva("RES-999");

        
        System.out.println("\n-> Cancelando reserva válida:");
        gestor.cancelarReserva("RES-1");
    }
}
