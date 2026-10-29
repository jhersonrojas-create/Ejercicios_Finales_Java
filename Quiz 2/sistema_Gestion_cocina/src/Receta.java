import java.util.List;

public class Receta {
    private String nombre;
    private List<Ingrediente> ingredientes;
    private String instrucciones;
    private int porciones;

    public Receta(String nombre, List<Ingrediente> ingredientes, String instrucciones, int porciones) {
        
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la receta no puede estar vacío.");
        }
        if (ingredientes == null || ingredientes.isEmpty()) {
            throw new IllegalArgumentException("La receta debe contener al menos un ingrediente.");
        }
        if (instrucciones == null || instrucciones.trim().isEmpty()) {
            throw new IllegalArgumentException("Las instrucciones no pueden estar vacías.");
        }
        if (porciones <= 0) {
            throw new IllegalArgumentException("El número de porciones debe ser mayor que cero.");
        }

        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.instrucciones = instrucciones;
        this.porciones = porciones;
    }

    
    public String getNombre() { return nombre; }
    public String getInstrucciones() { return instrucciones; }
    public void setInstrucciones(String instrucciones) { this.instrucciones = instrucciones; }
    public int getPorciones() { return porciones; }
    
    public void setPorciones(int porciones) { 
        if (porciones <= 0) {
            throw new IllegalArgumentException("El número de porciones debe ser mayor que cero.");
        }
        this.porciones = porciones; 
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Receta: ").append(nombre).append(" ---\n");
        sb.append("Porciones: ").append(porciones).append("\n");
        sb.append("Ingredientes:\n");
        for (Ingrediente ing : ingredientes) {
            sb.append(" - ").append(ing).append("\n");
        }
        sb.append("Instrucciones:\n").append(instrucciones).append("\n");
        return sb.toString();
    }
}

