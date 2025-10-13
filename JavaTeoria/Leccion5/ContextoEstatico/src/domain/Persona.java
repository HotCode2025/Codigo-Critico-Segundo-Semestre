/*
Contexto estático: cuando estamos trabajando en las clases
Contexto dinámico: cuando ya está cargada la clase y empezamos a crear objetos
de la clase.
El contexto estático no puede acceder al dinámico porque se necesitan que 
las clases ya estén creadas, al contrario el dimámico sí puede acceder.
para las clases estáticas: NombreClase.atributo/métodoEstatico: //La referencia debe ser a través de la clase (no this)
*/

package domain;


public class Persona {

    //Cargamos los atributos
    private int idPersona;
    private static int contadorPersona; //Está asociado a la clase y no al objeto
    private String nombre;
    
    //Constructor
    public Persona(String nombre){
        this.nombre = nombre;
        //Incrementar el contador por cada objeto nuevo
        Persona.contadorPersona++; //No utilizar el operador this para atributos estáticos
        //Vamos a asignar un nuevo valor a la variable idPersona
        this.idPersona = Persona.contadorPersona; //para que el contador no se inicialice 
    }
    
    public static int getContadorPersona() {
        return contadorPersona;
    }

    public static void setContadorPersona(int aContadorPersona) {
        contadorPersona = aContadorPersona;
    }

    public int getIdPersona() {
        return this.idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Persona{" + "idPersona=" + idPersona + ", nombre=" + nombre + '}';
    } 
}
