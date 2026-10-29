import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reserva {
    private String id;
    private Sala sala;
    private LocalDateTime fechaHora;
    private String nombreReservante;
    private int numeroAsistentes;

    public Reserva(String id, Sala sala, LocalDateTime fechaHora, String nombreReservante, int numeroAsistentes) {
        
        if (nombreReservante == null || nombreReservante.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del reservante no puede estar vacío.");
        }
        if (numeroAsistentes <= 0) {
            throw new IllegalArgumentException("El número de asistentes debe ser mayor a cero.");
        }
        if (fechaHora.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("No se pueden realizar reservas para una fecha/hora pasada.");
        }
        if (numeroAsistentes > sala.getCapacidadMaxima()) {
            throw new IllegalArgumentException("El número de asistentes excede la capacidad máxima de la sala (" + sala.getCapacidadMaxima() + ").");
        }

        this.id = id;
        this.sala = sala;
        this.fechaHora = fechaHora;
        this.nombreReservante = nombreReservante;
        this.numeroAsistentes = numeroAsistentes;
    }

    public String getId() { return id; }
    public Sala getSala() { return sala; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public int getNumeroAsistentes() { return numeroAsistentes; }
    

    
    public boolean seSolapaCon(LocalDateTime otraFechaHora) {
        LocalDateTime inicioExistente = this.fechaHora;
        LocalDateTime finExistente = this.fechaHora.plusHours(1);
        LocalDateTime inicioNueva = otraFechaHora;
        LocalDateTime finNueva = otraFechaHora.plusHours(1);

        return inicioNueva.isBefore(finExistente) && finNueva.isAfter(inicioExistente);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Reserva ID: " + id + " | Sala: " + sala.getNombre() + 
               " | Horario: " + fechaHora.format(formatter) + " a " + fechaHora.plusHours(1).format(DateTimeFormatter.ofPattern("HH:mm")) +
               " | Organiza: " + nombreReservante + " | Asistentes: " + numeroAsistentes;
    }
}
