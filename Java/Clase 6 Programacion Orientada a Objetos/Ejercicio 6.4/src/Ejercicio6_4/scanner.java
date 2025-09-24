package Ejercicio6_4;

import java.util.Scanner;

public class scanner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el ancho de la caja: ");
        int ancho = sc.nextInt();

        System.out.print("Ingrese el alto de la caja: ");
        int alto = sc.nextInt();

        System.out.print("Ingrese la profundidad de la caja: ");
        int profundidad = sc.nextInt();

        // Calcular volumen
        int volumen = ancho * alto * profundidad;

        System.out.println("El volumen de la caja es: " + volumen);

        sc.close();
    }
}
