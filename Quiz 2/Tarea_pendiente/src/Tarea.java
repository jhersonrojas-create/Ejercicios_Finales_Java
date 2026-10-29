public class Tarea {
    
    private String descripcion; 
    private int prioridad;      
    private boolean completada; 

  
    public Tarea(String descripcion) {
        this.descripcion = descripcion;
        this.prioridad = 2; 
        this.completada = false; 
    }

    
    
    public Tarea(String descripcion, int prioridad) {
        this.descripcion = descripcion;
        
        
        if (prioridad < 1 || prioridad > 3) {
            System.out.println("[ALERTA]: Prioridad inválida (" + prioridad + "). Se asignará 2 (Media) por defecto.");
            this.prioridad = 2;
        } else {
            this.prioridad = prioridad;
        }
        
        this.completada = false; 
    }

    

    
    public String getDescripcion() {
        return descripcion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public boolean isCompletada() {
        return completada;
    }

   
    public void setPrioridad(int nuevaPrioridad) {
        if (nuevaPrioridad >= 1 && nuevaPrioridad <= 3) {
            this.prioridad = nuevaPrioridad;
        } else {
            System.out.println("Error: La prioridad debe estar en el rango de 1 a 3 (1=Alta, 2=Media, 3=Baja). No se aplicaron cambios.");
        }
    }

    
    public void marcarComoCompletada() {
        this.completada = true;
    }

    
    public void mostrarEstado() {
        String textoPrioridad;
        switch (this.prioridad) {
            case 1: textoPrioridad = "Alta"; break;
            case 2: textoPrioridad = "Media"; break;
            case 3: textoPrioridad = "Baja"; break;
            default: textoPrioridad = "Desconocida";
        }

        String textoEstado = this.completada ? "Completada" : "Pendiente";

        System.out.println("Tarea: " + descripcion + " | Prioridad: " + textoPrioridad + " | Estado: " + textoEstado);
    }
}
