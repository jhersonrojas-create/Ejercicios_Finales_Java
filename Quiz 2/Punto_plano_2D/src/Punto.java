public class Punto {
    private double x;
    private double y;

    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double nuevoX) {
        this.x = nuevoX;
    }

    public double getY() {
        return y;
    }

    public void setY(double nuevoY) {
        this.y = nuevoY;
    }

    public void mover (double deltaX, double deltaY){
        this.x += deltaX;
        this.y += deltaY;
    }

    public double calcularDistanciaDesdeOrigen(){
         return Math.sqrt((this.x * this.x) + (this.y * this.y));
    }

    public void mostrarCoordenadas(){
        System.out.println("("+ this.x +"),("+ this.y +")");
    }  
    
}
