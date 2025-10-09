/*
Clases y objetos:
-Una clase es una plantilla (un modelo de cómo deben ser los objetos de esa clase, un "molde")
-Los objetos provienen de las clases, una instancia de nuestra clase
-Se trabaja sobre los obejetos a partir de una clase, asignandole caracateristicas,Ej
nombre, apellido, edad, etc
-En los objetos se asignan valores, en las clases se definir atributos y metodos de manera
general
-Atributos = caracteristicas del objeto
-Métodos = acciones que hace el objeto
-Un método es reutilizable pudiendo ser llamado las veces que se quiera y puede recibir valores que se conocen como argumentos 
-Los métodos pueden regresar un valor a esto se le llama valor de retorno
-Las clases son un conjunto de objetos que tienen cosas en común

*/
package Clases;

public class Persona {
    //Atributos de la clase
    public String nombre;
    public String apellido;
    
    //Métodos de la clase
    public void obtenerInformación(){
        System.out.println("Nombre: "+nombre);
        System.out.println("Apellido: "+apellido);
    }
    
}
