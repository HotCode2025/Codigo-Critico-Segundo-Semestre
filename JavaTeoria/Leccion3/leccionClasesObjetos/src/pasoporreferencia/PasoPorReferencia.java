//Paso por referencia
/*
No es requerido un return en un método void porque
el programa lo asigna automaticamente
*/
package pasoporreferencia;

import Clases.Persona;


public class PasoPorReferencia {
    public static void main(String[] args) {
        Persona persona1 = new Persona();
        persona1.nombre = "Ester";
        System.out.println("persona1 nombre = " + persona1.nombre);
        cambiarValor(persona1);
        System.out.println("El cambio que hicimos en el nombre es: "+persona1.nombre);
        persona1 = cambiarElValor(persona1);
        Persona persona2 = new Persona();
        //Persona persona2 = null; para que se ejecute el if 
        persona2 = cambiarElValor(persona2);
        System.out.println("El nuevo valor del objeto es: "+persona2.nombre);
    }
    
    public static void cambiarValor(Persona persona){ //parámetro por referencia
        persona.nombre = "Maria"; 
    }
    
    //return y null
    public static Persona cambiarElValor(Persona persona){ //Método de tipo object
        if(persona == null){
            System.out.println("Valor de persona es invalido: Null");
            return null;
        }
        else{
            persona.nombre = "Monica";
            return persona;
        }
    }
}
