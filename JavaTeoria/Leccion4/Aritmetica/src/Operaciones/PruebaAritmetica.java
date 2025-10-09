/*
-Siempre es recomendado hacer el main en un archivo diferente de la clase
-el orden para llamar a una clase es: Nombre de la clase -> el nombre que le vamos
a poner al objeto -> palabra recerbada "new" -> llamar al constructor (el nombre de la clase + parentesis) 

El alcance de variables es solo dentro de su método, las variables son locales
*/

package Operaciones;


public class PruebaAritmetica {
    public static void main(String[] args) { //Método main
        int a = 10; //Variables locales
        int b = 7; //Memoria stack
        miMetodo(); //Llamamos el método nuevo
        Aritmetica aritmetica1 = new Aritmetica();
        aritmetica1.a = 3;
        aritmetica1.b = 7;
        aritmetica1.sumarNumeros();
        
        int resultado = aritmetica1.sumarConRetorno();
        System.out.println("resultado = " + resultado);
        
        resultado = aritmetica1.sumarConArgumentos(12, 26);
        System.out.println("Resultado usando argumentos = "+resultado);
        
        System.out.println("aritmetica1 a: "+aritmetica1.a);
        System.out.println("aritmetica1 b: "+aritmetica1.b);
        
        //Creación de otro objeto
        Aritmetica aritmetica2 = new Aritmetica(5, 8);
        System.out.println("aritmetica2 = " + aritmetica2.a);
        System.out.println("aritmetica2 = " + aritmetica2.b);
        //aritmetica1 = null; //nunca se debe hacer, se suele hacer con la intención de limpiar (dejar vacio)el objeto pero el propio programa ya lo hace
        //System.gc(); //parecido al anterior es un método para limpiar residuos, es pesado, no utilizar
        Persona persona = new Persona("Samira", "Baz" ); //Creamos un objeto Persona a patir de la clase Persona
        System.out.println("persona = " + persona);
        System.out.println("Persona nombre: "+persona.nombre);
        System.out.println("Persona apellido: "+persona.apellido);
    }
    
    //Modularidad creamos un nuevo método
    public static void miMetodo(){
        // a = 10; //una variable está límitada
        System.out.println("Aquí hay otro método");    
    }
}

//Creamos una nueva clase
class Persona{ //Solo puede haber una clase de tipo public, al crear una segunda clase ya se le asigna un modificador de acceso llamado package/defoult
    String nombre;
    String apellido;
    
    Persona(String nombre, String apellido){ //Constructor
        super(); //Constructor objets (clase padre)
        //Imprimir imprimir = new imprimir(); Así es como veniamos creando objetos
        new Imprimir().Imprimir(this);
        this.nombre = nombre;
        this.apellido = apellido;
        System.out.println("Objeto persona usando this: "+this); //gracias al constructor objets es que se puede aaceder al espacio de memoria con this en esta línea
    }
}

class Imprimir{
    public Imprimir(){
        super(); //el constructor de la clase padre, para reservar memoria
    }
    
    public void Imprimir(Persona persona){
        System.out.println("Persona desde la clase imprimir: "+persona);
        System.out.println("Impresión del objeto actual (this): "+this);
    }
}
