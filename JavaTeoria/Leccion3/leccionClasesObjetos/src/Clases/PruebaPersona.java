/*
Instancia = objeto
Instanciar = crear objeto
*/
package Clases;


public class PruebaPersona {
    public static void main(String[] args) {
        Persona persona1 = new Persona(); //Llamamos al constructor de la clase persona
        persona1.nombre = "Samira"; //El valor hexadecimal inicia normalmente con 0x, este valor es una referencia en memoria
        persona1.apellido = "Baz";
        //Llamar al método
        persona1.obtenerInformación(); 
        
        //Creamos un nuevo objeto
        Persona persona2 = new Persona();
        System.out.println("persona2 = " + persona2); //Muestra la dirección de memoria
        System.out.println("persona1 = " + persona1);
        persona2.obtenerInformación();
        persona2.nombre = "Osvaldo";
        persona2.apellido = "Giordanini";
        persona2.obtenerInformación();
    }
}
