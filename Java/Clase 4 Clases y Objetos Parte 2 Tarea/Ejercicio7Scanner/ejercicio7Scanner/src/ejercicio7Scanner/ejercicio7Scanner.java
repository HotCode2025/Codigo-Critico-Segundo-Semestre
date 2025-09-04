package ejercicio7Scanner;

import java.util.Scanner;

public class ejercicio7Scanner {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int numero, suma = 0, contador = 0;

        System.out.println("Introduce números (un negativo para terminar):");

        while (true) {
            numero = entrada.nextInt();

            if (numero < 0) {
                break; // Se detiene el ciclo al ingresar un número negativo
            }

            suma += numero;
            contador++;
        }

        if (contador > 0) {
            double media = (double) suma / contador;
            System.out.println("La media es: " + media);
        } else {
            System.out.println("No se ingresaron números válidos.");
        }

        entrada.close();
    }
}
