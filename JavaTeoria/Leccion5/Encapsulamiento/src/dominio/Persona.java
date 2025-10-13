/*
Encapsulamiento: La idea del encapsulamiento en la POO es evitar el acceso y manipulación
directa(con private en los atributos)de otras clases a los datos(atributos de la clase en cuestión) de un objeto. Para acceder a los 
datos solo será por medio de los metodos getter(obtiene información del atributo) y setter (modifica el valor del atributo ) 
y estos sí deben ser publicos, creando uno por cada atributo.

En los tipos boolean en vez de utilizar el get se utiliza is porque expresa una condición o estado que puede ser verdadero o falso,
y semánticamente resulta más claro y natural al leerlo como una pregunta.
 */
package dominio;

public class Persona {
    //Atributos encapsulados
    private String nombre;
    private double sueldo;
    private boolean eliminado;
    
    //Constructor
    public Persona(String nombre, double sueldo,boolean eliminado){ //El constructor siempre debe llevar el mismo nombre que la clase
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.eliminado = eliminado;
    }

    public Persona() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    //Métodos
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public boolean isEliminado() { 
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }
    
    //Metodo toString para acceder y ver toda la información de los atributos
    public String toString(){ //Convierte en una cadena cada atributo
        return "Persona [ nombre: "+this.nombre+
                ", sueldo: "+this.sueldo+
                ", eliminado: "+this.eliminado+" ]";
    }
    
}
