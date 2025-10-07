package ejercicio4joption;


import javax.swing.JOptionPane;

public class Ejercicio4JOption {
    public static void main(String[] args) {
        int numero;
        int contador = 0;

        // Primer número antes del ciclo
        numero = Integer.parseInt(JOptionPane.showInputDialog("Ingresa un número:"));

        while (numero >= 0) {
            contador++;
            numero = Integer.parseInt(JOptionPane.showInputDialog("Ingresa otro número:"));
        }

        JOptionPane.showMessageDialog(null, "Has ingresado " + contador + " números positivos.");
    }
}
