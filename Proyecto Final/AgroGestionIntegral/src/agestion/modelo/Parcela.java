// El IDE pone esto automáticamente para indicar que la clase pertenece a este paquete.
package agestion.modelo;

public class Parcela {
    private int id;
    private String nombre;
    private double superficie;
    private String tipoCultivo;

    // Constructor
    public Parcela(int id, String nombre, double superficie, String tipoCultivo) {
        this.id = id;
        this.nombre = nombre;
        this.superficie = superficie;
        this.tipoCultivo = tipoCultivo;
    }

    // Métodos Getters y Setters (para acceder a los atributos desde fuera)
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getSuperficie() { return superficie; }
    public String getTipoCultivo() { return tipoCultivo; }
    
    // Podemos agregar un método para mostrar la info fácilmente
    @Override
    public String toString() {
        return nombre + " (" + tipoCultivo + ") - " + superficie + " ha.";
    }
}