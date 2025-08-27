
package Ciclos05Scanner;
import java.util.Scanner;
/**
 *
 * @author ArielMazara
 */
public class Ciclos05Scanner {
    public static void main(String[] args) {
        // Creamos un objeto Scanner para leer lo que el usuario escriba
        Scanner entrada = new Scanner(System.in);

        // 2. Generamos un número aleatorio entre 0 y 100
        int numeroSecreto = (int) (Math.random() * 101);

        int numeroUsuario; // Variable para guardar el número del usuario
        int intentos = 0;  // Contador de intentos, inicia en 0

        System.out.println("--- Juego: Adivina el Número ---");
        System.out.println("He generado un número entre 0 y 100. ¡Intenta adivinar cuál es!");

        // 3. Creamos un bucle que se repita hasta que el usuario adivine
        do {
            System.out.print("Introduce tu número: ");
            numeroUsuario = entrada.nextInt(); // Leemos el número entero que escribe el usuario
            intentos++; // Aumentamos el contador de intentos

            // 4. Comparamos y damos pistas
            if (numeroUsuario < numeroSecreto) {
                System.out.println("El número secreto es MAYOR.");
            } else if (numeroUsuario > numeroSecreto) {
                System.out.println("El número secreto es MENOR.");
            }

        } while (numeroUsuario != numeroSecreto); // La condición para que el bucle continúe

        // 5. Cuando el bucle termina, mostramos el mensaje de felicitación
        System.out.println("\n**************************************************");
        System.out.println("¡FELICIDADES! ¡Has adivinado el número!");
        System.out.println("El número secreto era: " + numeroSecreto);
        System.out.println("Lo lograste en " + intentos + " intentos.");
        System.out.println("**************************************************");

        // 6. Cerramos el scanner para liberar recursos
        entrada.close();
    }
    
}
