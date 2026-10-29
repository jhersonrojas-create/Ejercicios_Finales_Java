import java.util.ArrayList;
import java.util.List;

public class GestorRecetas {
    private Receta[] almacenamiento;
    private int contador;

    public GestorRecetas(int capacidadMaxima) {
        this.almacenamiento = new Receta[capacidadMaxima];
        this.contador = 0;
    }

    // Registro de Eventos (Auditoría)
    private void registrarEvento(String tipo, String mensaje) {
        System.out.println("[AUDITORÍA - " + tipo + "]: " + mensaje);
    }

    // 1. Registro de Recetas
    public void registrarReceta(Receta nuevaReceta) {
        if (contador >= almacenamiento.length) {
            registrarEvento("FALLO", "Intento de añadir receta fallido: Almacenamiento lleno.");
            System.out.println("Error: No se pueden añadir más recetas, se ha alcanzado el límite máximo.");
            return;
        }
        
        almacenamiento[contador] = nuevaReceta;
        contador++;
        registrarEvento("REGISTRO", "Receta '" + nuevaReceta.getNombre() + "' registrada con éxito.");
    }

    // 2. Consulta de Recetas
    public Receta buscarReceta(String nombre) {
        registrarEvento("BÚSQUEDA", "Buscando receta: " + nombre);
        for (int i = 0; i < contador; i++) {
            if (almacenamiento[i].getNombre().equalsIgnoreCase(nombre)) {
                return almacenamiento[i];
            }
        }
        registrarEvento("FALLO", "Receta no encontrada: " + nombre);
        System.out.println("Notificación: La receta '" + nombre + "' no existe en el sistema.");
        return null;
    }

    // 3. Actualización de Recetas
    public void actualizarReceta(String nombre, String nuevasInstrucciones, int nuevasPorciones) {
        registrarEvento("ACTUALIZACIÓN", "Intentando actualizar receta: " + nombre);
        Receta receta = buscarReceta(nombre);
        if (receta != null) {
            try {
                receta.setInstrucciones(nuevasInstrucciones);
                receta.setPorciones(nuevasPorciones);
                registrarEvento("ACTUALIZACIÓN", "Receta '" + nombre + "' actualizada correctamente.");
            } catch (IllegalArgumentException e) {
                registrarEvento("FALLO", "Error al actualizar datos de '" + nombre + "': " + e.getMessage());
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // 4. Listado de Recetas
    public void listarRecetas() {
        registrarEvento("LISTADO", "Mostrando todas las recetas.");
        if (contador == 0) {
            System.out.println("No hay recetas registradas.");
            return;
        }
        for (int i = 0; i < contador; i++) {
            System.out.println((i + 1) + ". " + almacenamiento[i].getNombre() + " (" + almacenamiento[i].getPorciones() + " porciones)");
        }
    }
}

