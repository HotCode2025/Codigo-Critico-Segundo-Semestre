
package ejercicio6joption;

import javax.swing.JOptionPane;

/**
 *
 * @author ArielMazara
 */
public class Ejercicio6Joption {
public static void main(String[] args) {
        int numero;
        int suma = 0;

        do {
            // Pedir el número usando una ventana de diálogo
            String entrada = JOptionPane.showInputDialog("Digite un número (0 para terminar):");
            
            // Convertimos el texto (String) ingresado a un número entero (int)
            numero = Integer.parseInt(entrada);
            
            // Acumur el valor en la variable suma
            suma += numero;

        } while (numero != 0); // El ciclo continúara mientras el número sea diferente de 0

        // Al salir del bucle, muestra la suma total en una ventana de mensaje
        JOptionPane.showMessageDialog(null, "La suma de todos los números introducidos es: " + suma);
    }
}