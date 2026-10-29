public class Sala {
    private String id;
    private String nombre;
    private int capacidadMaxima;

    public Sala(String id, String nombre, int capacidadMaxima) {
        this.id = id;
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }
    
    @Override 
    public String toString(){
        return "[" + id + "]" + nombre + " (Capacidad Max: " + capacidadMaxima + " personas)";
    }
    
}
