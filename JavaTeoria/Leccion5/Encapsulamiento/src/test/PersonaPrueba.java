/*
sí se quiere importar todas las clases de un paquete, quedá sí:
import dominio.*; //el * reserva un espacio de memoria para todas las clases
*/
package test;

import dominio.Persona; //se importa porque la clase persona está en otro paquete

public class PersonaPrueba {
    public static void main(String[] args) {
        Persona persona1 = new Persona("Osvaldo", 57.000, false);
        System.out.println("persona1 = " + persona1); //llamamos al toString 
        System.out.println("persona1 su nombre es: "+persona1.getNombre());
        
        //Modificar a través de los métodos
        persona1.setNombre("Juan Ignacio");
        //persona1.nombre = "Juan Ignacio"; //Ya no se puede utilizar porque el atributo es privado
        //System.out.println("Nombre es: "+persona1.nombre); //tampoco ya no se puede utilizar
        System.out.println("persona1 con su nombre modificado: "+persona1.getNombre());
        System.out.println("persona1 el resultado para su sueldo es: "+persona1.getSueldo());
        System.out.println("persona1 para obtener el booleano: "+persona1.isEliminado());
        
        //TAREA: Crear otro objeto de tipo Persona, asignar valores de manera inicial 
        //e imprimir, luego modificar sus valores y volver a imprimir
        
        //Crear un nuevo ojeto y asignar valores
        Persona persona2 = new Persona("Samira", 700.000, false);
        //Imprimir valores
        System.out.println("persona2 su nombre es: "+persona2.getNombre());
        System.out.println("persona2 su sueldo es: "+persona2.getSueldo());
        System.out.println("persona2 para obtener el booleano: "+persona2.isEliminado());
        
        //Modificar valores de persona2
        persona2.setNombre("Sofia");
        persona2.setSueldo(900.000);
        persona2.setEliminado(true);
        
        //Mostrar modificaciones
        System.out.println("persona2 su nombre moficado es: "+persona2.getNombre());
        System.out.println("persona2 su sueldo modificado es: "+persona2.getSueldo());
        System.out.println("persona2 para obtener el booleano modifado es: "+persona2.isEliminado());
        
        //Probando el Metodo toString 
        System.out.println("persona1: "+persona1.toString()); //No hace falta poner toString 

    }
    
}
