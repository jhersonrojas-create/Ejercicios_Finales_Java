import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        GestorRecetas gestor = new GestorRecetas(2); 
        try (Scanner scanner = new Scanner(System.in)) {
            
            System.out.println("--- Prueba de entrada para porciones ---");
            System.out.print("Introduce el número de porciones (ej. prueba a meter una letra): ");
            String entrada = scanner.nextLine();
            
            int porciones = 0;
            try {
                porciones = Integer.parseInt(entrada);
                System.out.println("Porciones procesadas: " + porciones);
            } catch (NumberFormatException e) {
                System.out.println("Error: La entrada introducida no es un número válido.");
            }
        }

        
        try {
            ArrayList<Ingrediente> ingredientes = new ArrayList<>();
            ingredientes.add(new Ingrediente("Harina", 500, "gramos"));
            Receta receta1 = new Receta("Pan Casero", ingredientes, "Mezclar y hornear por 30 minutos.", 4);
            Receta receta2 = new Receta("Pan Dulce",ingredientes,"Mezclar y hornear por 40 minutos", 6  );
            gestor.registrarReceta(receta1);
            gestor.registrarReceta(receta2);
            gestor.listarRecetas();
        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear receta: " + e.getMessage());
        }
    }
}
