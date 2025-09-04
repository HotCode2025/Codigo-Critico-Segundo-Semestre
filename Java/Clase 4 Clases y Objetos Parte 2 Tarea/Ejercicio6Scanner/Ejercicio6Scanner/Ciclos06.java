/*
Ejercicio 6: Pedir números hasta que se teclee un 0, mostrar la suma
de todos los números introducidos.
*/
package Ciclos02;

import java.util.Scanner;

public class Ciclos06 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int numero; // Variable para guardar el número ingresado
        int suma = 0; // Acumula la suma total

        System.out.println("Ingresá números para sumarlos. Con el 0 se finaliza");

        // Ciclo que se repite mientras el número no sea 0
        do {
            System.out.print("Digite un número: ");
            numero = entrada.nextInt(); 
            suma += numero; 
        } while (numero != 0);

        // Mostramos el resultado final
        System.out.println("La suma total de los números introducidos es: " + suma);

    }
}
