package EjercicioCiclos12;

import javax.swing.JOptionPane;
import java.util.Scanner;

public class EjercicioCiclos12 {

    public static void main(String[] args) {
 // Opcion si uso Scanner o JOptionPane
        String opcion = JOptionPane.showInputDialog(
            "¿Cómo desea ingresar el número?\n" +
            "1. Por consola (Scanner)\n" +
            "2. Por ventana (JOptionPane)"
        );

        int eleccion = Integer.parseInt(opcion);

        if (eleccion == 1) {
            usarScanner();
        } else if (eleccion == 2) {
            usarJOptionPane();
        } else {
            JOptionPane.showMessageDialog(null, "Opción no válida.");
        }
    }

    // El método Scanner 
    public static void usarScanner() {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Digite un número para calcular su factorial: ");
        int numero = entrada.nextInt();
        long factorial = 1;

        if (numero < 0) {
            System.out.println("No se puede calcular el factorial de un número negativo.");
        } else {
            for (int i = 1; i <= numero; i++) {
                factorial *= i;
            }
            System.out.println("El factorial de " + numero + " es: " + factorial);
        }
        entrada.close();
    }

    // El método usarJOptionPane 
    public static void usarJOptionPane() {
        String input = JOptionPane.showInputDialog("Digite un número para calcular su factorial:");
        int numero = Integer.parseInt(input);
        long factorial = 1;

        if (numero < 0) {
            JOptionPane.showMessageDialog(null, "No se puede calcular el factorial de un número negativo.");
        } else {
            for (int i = 1; i <= numero; i++) {
                factorial *= i;
            }
            JOptionPane.showMessageDialog(null, "El factorial de " + numero + " es: " + factorial);
        }
    }

} 

// Ejemplo 7!=7∗6∗5∗4∗3∗2∗1=5,040