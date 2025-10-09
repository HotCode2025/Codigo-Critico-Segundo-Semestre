/*
Los Atributos y métodos comienzan con minusculas, esto para poder
diferenciarlos de una clase

Estructura para crear un método:
modificador de acceso = public o también puede ser privat
tipo de retorno = void (vacio/no retorna nada), int (retorna un entero), etc
identificador = nombre del método (con comelCase)

La palabra reservada "this" es una variable/operador que apunta al espacio de memoria 
de un objeto en ejecución para hacer modificaciones
El operador "this" hace que el programa diferencie los atributos de los argumentos, 
aunqie tengan el mismo nombre, y apunta solo a atributos no a variables locales

El constructor cumple con 3 objetivos:
-Construye un objeto
-Recerva un espacio de memoria 
-Inicializa los atributos de la clase
Cuando se crea un constructor, inevitablemente hay que crear el que viene por defecto
porque sino dejaria de existir siendo reemplazado por el quequeremos crear y daria error

Engineer Java: Memoria stack y heap: es una clasificación de la memoria
-stack: solo almacena variables locales
-heap: para almacenar los atributos y objetos 
*/
package Operaciones;


public class Aritmetica { //Las clases comienzan con Mayuscula, y se usa el PascalCase
    //Atributos de la clase
    int a; //cuando no asignamos nada, en este caso su valor por default es 0
    int b;
    
   
    //El constructor es un método especial
    public Aritmetica(){ //Constructor 1 (vacio), se crea por defecto
        System.out.println("Se está ejecutando el constructor número uno");
    }
    
    //Sobrecarga de constructores
    public Aritmetica(int a, int b){ //Constructor 2
        this.a = a;
        this.b = b;
        System.out.println("Se está ejecutando el constructor número dos");
    }
    
    //Método
    public void sumarNumeros(){ //Creamos al modificador de acceso. Comienza con minuscula y se usa el camelCase
        //Cuerpo de la clase:
        int resultado = a + b;
        System.out.println("resultado = " + resultado); //Mandar información a la consola no es lo mismo que regresar valor
    }
    
    //otro método (ambos métodos hacen lo mismo de distintas maneras, este retorna algo con int y el otro nada con void)
    public int sumarConRetorno(){
        //int resultado = a + b;
        //return resultado; //se puede retornar a traves de una variable o
        //directamente:
        return this.a + this.b;
    }
    
    //Paso de argumentos a un método:
    public int sumarConArgumentos(int a, int b){ //El argumento es la información que va a recibir el método. Jamás dentro dentro de un método se puede usar var
        this.a = a; //El argumento a se asigna al atributo this.a
        this.b = b;
        //return a + b;
        return sumarConRetorno(); //esto se hace solo dentro de la misma clase
    }   
}
