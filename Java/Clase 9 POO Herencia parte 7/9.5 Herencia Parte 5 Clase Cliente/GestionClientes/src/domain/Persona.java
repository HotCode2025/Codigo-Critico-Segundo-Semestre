// Archivo: Persona.java
package domain;

public class Persona {
    protected String nombre;
    protected char genero;
    protected int edad;
    protected String direccion;

    // Constructor vacío
    public Persona() {
    }

    // Constructor para crear un objeto persona con un nombre
    public Persona(String nombre) {
        this.nombre = nombre;
    }

    // Constructor completo
    public Persona(String nombre, char genero, int edad, String direccion) {
        this.nombre = nombre;
        this.genero = genero;
        this.edad = edad;
        this.direccion = direccion;
    }

    // --- Métodos Getters y Setters ---
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getGenero() {
        return this.genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return this.edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    // --- Método toString para mostrar el estado del objeto ---
   @Override
    public String toString() {
        // Usamos \n para saltos de línea y \t para tabular
        return "\n\tNombre: " + this.nombre +
               "\n\tGénero: " + this.genero +
               "\n\tEdad: " + this.edad +
               "\n\tDirección: " + this.direccion;
    }
}