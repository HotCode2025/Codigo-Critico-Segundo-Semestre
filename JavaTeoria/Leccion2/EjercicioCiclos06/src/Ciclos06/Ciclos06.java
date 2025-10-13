/*
La inferencia de tipos en Java es una capacidad del compilador 
para deducir automáticamente el tipo de una variable con la palabra clave var.
Java no permite declarar múltiples variables con var en una sola línea
porque la inferencia de tipos requiere que cada variable tenga su propia linea
*/
/*
Ejercicio 6: Pedir números hasta que se teclee un 0, mostrar
la suma de todos los números introducidos
*/
package Ciclos06;

import java.util.Scanner;


public class Ciclos06 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int numero, suma = 0;
        do{
            System.out.println("Digite un número: ");
            numero = Integer.parseInt(entrada.nextLine());
            suma+= numero;
        }while(numero != 0);
        System.out.println("\tLa suma de todos los números ingresados es: "+suma);
    }
}
