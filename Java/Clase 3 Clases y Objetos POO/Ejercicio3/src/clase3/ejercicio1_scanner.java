package clase3;

import java.util.Scanner;

public class ejercicio1_scanner {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int numero;

        do {
            System.out.print("Ingrese un número (0 para salir): ");
            numero = entrada.nextInt();

            if (numero != 0) {
                if (numero % 2 == 0) {
                    System.out.println("El número " + numero + " es PAR.");
                } else {
                    System.out.println("El número " + numero + " es IMPAR.");
                }
            }
        } while (numero != 0);

        System.out.println("Programa finalizado.");
    }
}
