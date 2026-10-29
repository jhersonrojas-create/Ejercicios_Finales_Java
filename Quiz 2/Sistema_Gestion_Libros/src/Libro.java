public class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private int copiasTotales;
    private int copiasDisponibles;

    public Libro(String isbn, String titulo, String autor, int copiasTotales) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("El ISBN no puede estar vacío.");
        }
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título del libro no puede estar vacío.");
        }
        if (autor == null || autor.trim().isEmpty()) {
            throw new IllegalArgumentException("El autor no puede estar vacío.");
        }
        if (copiasTotales < 0) {
            throw new IllegalArgumentException("La cantidad total de copias no puede ser negativa.");
        }

        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.copiasTotales = copiasTotales;
        this.copiasDisponibles = copiasTotales; 
    }

    // Getters y Setters necesarios
    public String getIsbn() { return isbn; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getCopiasDisponibles() { return copiasDisponibles; }

    
    public boolean prestarCopia() {
        if (copiasDisponibles > 0) {
            copiasDisponibles--;
            return true;
        }
        return false;
    }

    public boolean devolverCopia() {
        if (copiasDisponibles < copiasTotales) {
            copiasDisponibles++;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "ISBN: " + isbn + " | Título: " + titulo + " | Autor: " + autor + 
               " (Disponibles: " + copiasDisponibles + "/" + copiasTotales + ")";
    }
}
