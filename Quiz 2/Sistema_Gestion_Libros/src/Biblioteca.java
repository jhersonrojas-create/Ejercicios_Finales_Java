public class Biblioteca {
    private Libro[] catalogo;
    private int contadorLibros;

    
    public Biblioteca(int capacidadMaxima) {
        this.catalogo = new Libro[capacidadMaxima];
        this.contadorLibros = 0;
    }

   
    private void registrarLog(String operacion, String mensaje) {
        System.out.println("[LOG - " + operacion.toUpperCase() + "] " + mensaje);
    }

   
    public void registrarLibro(Libro nuevoLibro) {
        // Validación 6.6: Si se intenta añadir más libros de los permitidos por el almacenamiento
        if (contadorLibros >= catalogo.length) {
            registrarLog("ERROR", "Intento fallido de registro. Catálogo lleno (Límite alcanzado).");
            System.out.println("Notificación: No se pueden almacenar más libros. Capacidad de almacenamiento al límite.");
            return;
        }

        
        for (int i = 0; i < contadorLibros; i++) {
            if (catalogo[i].getIsbn().equalsIgnoreCase(nuevoLibro.getIsbn())) {
                registrarLog("ERROR", "Intento de registrar ISBN duplicado: " + nuevoLibro.getIsbn());
                System.out.println("Notificación: No se pudo registrar. El libro con ISBN " + nuevoLibro.getIsbn() + " ya existe.");
                return;
            }
        }

        catalogo[contadorLibros] = nuevoLibro;
        contadorLibros++;
        registrarLog("REGISTRO", "Libro añadido con éxito: " + nuevoLibro.getTitulo() + " (ISBN: " + nuevoLibro.getIsbn() + ")");
    }

    
    public void prestarLibro(String isbn) {
        registrarLog("PRESTAMO_INTENTO", "Solicitud de préstamo para ISBN: " + isbn);
        
        for (int i = 0; i < contadorLibros; i++) {
            if (catalogo[i].getIsbn().equalsIgnoreCase(isbn)) {
                if (catalogo[i].prestarCopia()) {
                    registrarLog("PRESTAMO_EXITO", "Copia prestada de: " + catalogo[i].getTitulo());
                    System.out.println("Éxito: Préstamo realizado.");
                } else {
                   
                    registrarLog("ERROR", "No hay copias disponibles para el ISBN: " + isbn);
                    System.out.println("Notificación: El sistema informa que no quedan copias disponibles para este libro.");
                }
                return;
            }
        }
       
        registrarLog("ERROR", "Intento de préstamo de libro inexistente con ISBN: " + isbn);
        System.out.println("Notificación: El libro solicitado con ISBN " + isbn + " no existe en el sistema.");
    }

    // Requisito 6.4: Devolución de libros
    public void devolverLibro(String isbn) {
        registrarLog("DEVOLUCION_INTENTO", "Solicitud de devolución para ISBN: " + isbn);
        
        for (int i = 0; i < contadorLibros; i++) {
            if (catalogo[i].getIsbn().equalsIgnoreCase(isbn)) {
                if (catalogo[i].devolverCopia()) {
                    registrarLog("DEVOLUCION_EXITO", "Copia devuelta de: " + catalogo[i].getTitulo());
                    System.out.println("Éxito: Devolución procesada correctamente.");
                } else {
                    registrarLog("ERROR", "Intento de devolver copias de más para: " + catalogo[i].getTitulo());
                    System.out.println("Error: Todas las copias de este libro ya se encuentran en inventario.");
                }
                return;
            }
        }
       
        registrarLog("ERROR", "Intento de devolver un libro no registrado con ISBN: " + isbn);
        System.out.println("Notificación: El libro con ISBN " + isbn + " no pertenece a esta biblioteca.");
    }

    
    public void buscarLibro(String criterio) {
        registrarLog("CONSULTA", "Búsqueda iniciada con término: " + criterio);
        boolean encontrado = false;
        
        for (int i = 0; i < contadorLibros; i++) {
            if (catalogo[i].getIsbn().equalsIgnoreCase(criterio) || catalogo[i].getTitulo().equalsIgnoreCase(criterio)) {
                System.out.println("[RESULTADO] " + catalogo[i]);
                encontrado = true;
            }
        }
        
        if (!encontrado) {
            registrarLog("ERROR", "Consulta sin resultados para: " + criterio);
            System.out.println("Notificación: No se encontró ningún libro con ese ISBN o Título.");
        }
    }

   
    public void listarLibros() {
        registrarLog("LISTADO", "Imprimiendo catálogo completo.");
        if (contadorLibros == 0) {
            System.out.println("El catálogo está vacío.");
            return;
        }
        System.out.println("\n--- CATÁLOGO DE LA BIBLIOTECA ---");
        for (int i = 0; i < contadorLibros; i++) {
            System.out.println((i + 1) + ". " + catalogo[i]);
        }
        System.out.println("---------------------------------\n");
    }
}
