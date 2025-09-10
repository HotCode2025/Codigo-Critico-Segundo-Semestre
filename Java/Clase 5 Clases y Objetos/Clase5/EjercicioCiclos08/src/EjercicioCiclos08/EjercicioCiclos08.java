package EjercicioCiclos08;

import java.util.Scanner;
import javax.swing.JOptionPane;

public class EjercicioCiclos08{
    public static void main(String[] args) {
        int N = 0;
        boolean valido = false;

        // Validamos entrada con JOptionPane
        while (!valido) {
            try {
                String input = JOptionPane.showInputDialog("Ingresa un número N:");
                N = Integer.parseInt(input);
                valido = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingresa un número válido.");
            }
        }

        // Mostramos los números del 1 al N en consola
        System.out.println("Números del 1 al " + N + ":");
        for (int i = 1; i <= N; i++) {
            System.out.println(i);
        }

        // Usamos Scanner para pedir otro número por consola
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ahora ingresa otro número por consola: ");
        int M = scanner.nextInt();

        // Mostramos los números del 1 al M en consola
        System.out.println("Números del 1 al " + M + ":");
        for (int i = 1; i <= M; i++) {
            System.out.println(i);
        }

        scanner.close();
    }
}
