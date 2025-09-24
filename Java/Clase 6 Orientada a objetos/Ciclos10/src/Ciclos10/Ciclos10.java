/*
Pedir 10 números y escribir la suma total.
Hacerlo con la clase Scanner y JOptionPane
*/
package Ciclos10;
import java.util.Scanner;

public class Ciclos10 {
    
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int suma = 0;

        System.out.println("Ingrese 10 números:");

        for (int i = 1; i <= 10; i++) {
            System.out.print("Número " + i + ": ");
            int numero = entrada.nextInt();
            suma += numero;
        }

        System.out.println("La suma total es: " + suma);
    }
}
