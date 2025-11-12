

package dominio;

/**
 *
 * @author arielmazara
 */
public class Persona {
// 1. Atributos (privados para el encapsulamiento)
    private String nombre;
    private double sueldo;
    private boolean eliminado;

    // 2. Constructor
    // Este método es el que se llama cuando usas "new Persona(...)"
    public Persona(String nombre, double sueldo, boolean eliminado) {
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.eliminado = eliminado;
    }

    // 3. Métodos Getters y Setters (para acceder y modificar los atributos)
    
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSueldo() {
        return this.sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    // Para booleanos, el "get" suele ser "is"
    public boolean isEliminado() {
        return this.eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }
    
    public String toString(){ // Convierte en una cada cada atributo
        return "Persona [ nombre: "+this.nombre+
                ", sueldo: "+this.sueldo+
                ", eliminado: "+this.eliminado+" ]";
    }
}
