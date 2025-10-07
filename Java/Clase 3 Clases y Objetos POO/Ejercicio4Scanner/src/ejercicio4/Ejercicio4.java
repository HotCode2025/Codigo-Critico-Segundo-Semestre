
package ejercicio4;
import java.util.Scanner;
//CLASE SCANER

public class Ejercicio4 {

    public static void main(String[] args) {
       
        Scanner entrada = new Scanner(System.in);
        int numero; //Definimos el tipo de dato
        int contador = 0;// Inicioamos el contador a 0

        System.out.print("Ingresamos un número: "); //Pedimos un numero al usuario
        numero = entrada.nextInt();

        while (numero >= 0) { 
            contador++;
            System.out.print("Ingresa  otro número: ");
            numero = entrada.nextInt();
        }

        System.out.println("Has ingresado " + contador + " números positivos.");
    }
}

       
