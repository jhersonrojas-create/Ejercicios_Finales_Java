public class Main {
    public static void main(String[] args) {
        System.out.println("=== PROBANDO LA CLASE PUNTO 2D ===\n");

        
        System.out.print("Punto 1 (Origen): ");
        Punto p1 = new Punto(0, 0);
        p1.mostrarCoordenadas();

        
        System.out.print("Punto 2 (Personalizado): ");
        Punto p2 = new Punto(3.0, 4.0);
        p2.mostrarCoordenadas();

        
       
        double distancia = p2.calcularDistanciaDesdeOrigen();
        System.out.println("Distancia de Punto 2 al origen: " + distancia);

        
        System.out.println("\nMoviendo el Punto 2 (deltaX = 2.5, deltaY = -1.0)...");
        p2.mover(2.5, -1.0); // Nuevas coordenadas deberían ser (3 + 2.5, 4 - 1.0) = (5.5, 3.0)
        System.out.print("Nuevas coordenadas de Punto 2: ");
        p2.mostrarCoordenadas();

        
        System.out.println("\nModificando la coordenada X del Punto 1 usando setX...");
        p1.setX(10.25);
        System.out.println("El nuevo valor de X en Punto 1 es: " + p1.getX());
        System.out.print("Estado final de Punto 1: ");
        p1.mostrarCoordenadas();
    }
}

