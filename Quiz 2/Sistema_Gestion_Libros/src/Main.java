import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Inicializamos la biblioteca con espacio solo para 2 libros (para forzar el error de almacenamiento lleno)
        Biblioteca biblioteca = new Biblioteca(2);
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== SIMULACIÓN EXPLICITA DE ERRORES Y REGISTRO DE LOGS ===");

        
        try {
            Libro libroErroneo = new Libro("978-X", "", "Autor Anónimo", -5);
        } catch (IllegalArgumentException e) {
            System.out.println("[MANEJO INTERNO] Capturado error de inicialización: " + e.getMessage());
        }

       
        Libro libro1 = new Libro("111", "Java para Principiantes", "Deitel", 1); // Solo 1 copia
        Libro libro2 = new Libro("222", "Estructuras de Datos", "Weiss", 5);
        biblioteca.registrarLibro(libro1);
        biblioteca.registrarLibro(libro2);

        
        Libro libroDuplicado = new Libro("111", "Otro Libro de Java", "Desconocido", 2);
        biblioteca.registrarLibro(libroDuplicado);

        
        Libro libroExcedente = new Libro("333", "Don Quijote", "Cervantes", 3);
        biblioteca.registrarLibro(libroExcedente);

      
        System.out.println("\n-> Primer préstamo (Debería ser exitoso):");
        biblioteca.prestarLibro("111"); // Copias disponibles bajan a 0
        
        System.out.println("\n-> Segundo préstamo del mismo libro (Debería alertar que no hay copias):");
        biblioteca.prestarLibro("111");

        
        System.out.println("\n-> Intentando devolver un ISBN que no está registrado:");
        biblioteca.devolverLibro("999");

      
        System.out.println("\n--- PRUEBA DE ENTRADA NO NUMÉRICA ---");
        System.out.print("Simula ingresar el código de un menú o cantidad (escribe letras, ej: 'hola'): ");
        String entradaInvalida = scanner.nextLine();
        
        try {
            int numeroProcesado = Integer.parseInt(entradaInvalida);
            System.out.println("Número ingresado correctamente: " + numeroProcesado);
        } catch (NumberFormatException e) {
            
            System.out.println("[MANEJO DE ERROR 6.5] Entrada inválida controlada. Se evitó que el programa falle.");
        }

       
        System.out.println();
        biblioteca.listarLibros();
    }
}

