
public class Main {
    public static void main(String[] args) {
        System.out.println("=== PROBANDO LA CLASE TAREA ===\n");

      
        System.out.println("-> Creando Tarea 1 (Solo descripción):");
        Tarea t1 = new Tarea("Comprar materiales de laboratorio");
        t1.mostrarEstado();

        
        System.out.println("\n-> Creando Tarea 2 (Prioridad Alta):");
        Tarea t2 = new Tarea("Estudiar para el examen de Java", 1);
        t2.mostrarEstado();

        
        System.out.println("\n-> Creando Tarea 3 (Con prioridad fuera de rango):");
        Tarea t3 = new Tarea("Sacar la basura", 5);
        t3.mostrarEstado();

        
        System.out.println("\n-> Marcando la Tarea 2 como completada...");
        t2.marcarComoCompletada();
        t2.mostrarEstado();

        
        System.out.println("\n-> Cambiando prioridad de Tarea 1 a Baja (3):");
        t1.setPrioridad(3);
        t1.mostrarEstado();

        System.out.println("\n-> Intentando cambiar prioridad de Tarea 1 a un valor incorrecto (0):");
        t1.setPrioridad(0); 
        t1.mostrarEstado();
    }
}
