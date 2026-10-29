import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GestorReserva {
    private List<Sala> salasPredefinidas;
    private Reserva[] almacenamientoReservas;
    private int contadorReservas;
    private int autoincrementalId;

    public GestorReserva(int capacidadAlmacenamientoMax) {
        this.salasPredefinidas = new ArrayList<>();
        this.almacenamientoReservas = new Reserva[capacidadAlmacenamientoMax];
        this.contadorReservas = 0;
        this.autoincrementalId = 1;
        cargarSalasPredefinidas();
    }

    private void cargarSalasPredefinidas() {
        salasPredefinidas.add(new Sala("S1", "Sala de Juntas Ejecutiva", 10));
        salasPredefinidas.add(new Sala("S2", "Sala Creativa / Brainstorming", 6));
        salasPredefinidas.add(new Sala("S3", "Auditorio Principal", 50));
    }

    private void registrarEvento(String tipo, String mensaje) {
        System.out.println("[AUDITORÍA - " + tipo + "]: " + mensaje);
    }

    // Buscar sala por ID
    public Sala buscarSala(String idSala) {
        for (Sala sala : salasPredefinidas) {
            if (sala.getId().equalsIgnoreCase(idSala)) {
                return sala;
            }
        }
        registrarEvento("FALLO", "Búsqueda de sala fallida: ID " + idSala + " no existe.");
        System.out.println("Notificación: La sala con ID '" + idSala + "' no existe.");
        return null;
    }

    
    public boolean verificarDisponibilidad(Sala sala, LocalDateTime fechaHora) {
        registrarEvento("VERIFICACIÓN", "Verificando disponibilidad para sala " + sala.getId() + " en el horario solicitado.");
        for (int i = 0; i < contadorReservas; i++) {
            Reserva r = almacenamientoReservas[i];
            if (r.getSala().getId().equals(sala.getId()) && r.seSolapaCon(fechaHora)) {
                return false; // Está ocupada
            }
        }
        return true; // Disponible
    }

    
    public void crearReserva(String idSala, LocalDateTime fechaHora, String nombreReservante, int asistentes) {
        registrarEvento("CREACIÓN", "Intento de reserva en sala " + idSala);
        
        // Error: Límite de almacenamiento excedido (Requisito 6)
        if (contadorReservas >= almacenamientoReservas.length) {
            registrarEvento("FALLO", "Límite de almacenamiento de reservas alcanzado.");
            System.out.println("Notificación: El sistema no puede almacenar más reservas. Límite excedido.");
            return;
        }

        Sala sala = buscarSala(idSala);
        if (sala == null) return; // Mensaje de no existencia manejado en buscarSala()

        // Error: Sala no disponible por solapamiento (Requisito 6)
        if (!verificarDisponibilidad(sala, fechaHora)) {
            registrarEvento("FALLO", "Sala " + idSala + " ya se encuentra reservada en ese horario.");
            System.out.println("Notificación: La sala no está disponible (ya cuenta con una reserva en ese horario).");
            return;
        }

        try {
            String idReserva = "RES-" + autoincrementalId++;
            Reserva nuevaReserva = new Reserva(idReserva, sala, fechaHora, nombreReservante, asistentes);
            
            almacenamientoReservas[contadorReservas] = nuevaReserva;
            contadorReservas++;
            registrarEvento("ÉXITO", "Reserva " + idReserva + " creada correctamente.");
            System.out.println("¡Reserva creada con éxito! ID asignado: " + idReserva);
        } catch (IllegalArgumentException e) {
            registrarEvento("FALLO", "Datos inválidos en la creación: " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }
    }

    
    public void consultarPorSala(String idSala) {
        registrarEvento("CONSULTA", "Consultando reservas de la sala " + idSala);
        boolean encontro = false;
        for (int i = 0; i < contadorReservas; i++) {
            if (almacenamientoReservas[i].getSala().getId().equalsIgnoreCase(idSala)) {
                System.out.println(almacenamientoReservas[i]);
                encontro = true;
            }
        }
        if (!encontro) {
            System.out.println("No hay reservas registradas para la sala " + idSala);
        }
    }

    
    public void consultarPorFecha(LocalDate fecha) {
        registrarEvento("CONSULTA", "Consultando reservas para la fecha " + fecha);
        boolean encontro = false;
        for (int i = 0; i < contadorReservas; i++) {
            if (almacenamientoReservas[i].getFechaHora().toLocalDate().equals(fecha)) {
                System.out.println(almacenamientoReservas[i]);
                encontro = true;
            }
        }
        if (!encontro) {
            System.out.println("No hay reservas registradas para la fecha: " + fecha);
        }
    }

    // 5. Cancelación de Reservas
    public void cancelarReserva(String idReserva) {
        registrarEvento("CANCELACIÓN", "Intento de cancelación de reserva ID: " + idReserva);
        int indiceEncontrado = -1;

        for (int i = 0; i < contadorReservas; i++) {
            if (almacenamientoReservas[i].getId().equalsIgnoreCase(idReserva)) {
                indiceEncontrado = i;
                break;
            }
        }

      
        if (indiceEncontrado == -1) {
            registrarEvento("FALLO", "Cancelación fallida: El ID de reserva " + idReserva + " no existe.");
            System.out.println("Notificación: El código de reserva '" + idReserva + "' no existe en el sistema.");
            return;
        }

        // Reordenamiento del arreglo para llenar el hueco eliminado
        for (int i = indiceEncontrado; i < contadorReservas - 1; i++) {
            almacenamientoReservas[i] = almacenamientoReservas[i + 1];
        }
        almacenamientoReservas[contadorReservas - 1] = null;
        contadorReservas--;

        registrarEvento("ÉXITO", "Reserva " + idReserva + " cancelada exitosamente.");
        System.out.println("La reserva con ID " + idReserva + " ha sido cancelada correctamente.");
    }

    public void mostrarSalas() {
        System.out.println("\n--- SALAS DISPONIBLES EN LA OFICINA ---");
        for (Sala s : salasPredefinidas) {
            System.out.println(s);
        }
    }
}

