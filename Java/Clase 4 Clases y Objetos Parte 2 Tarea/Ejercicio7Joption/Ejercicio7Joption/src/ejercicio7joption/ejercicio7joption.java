package ejercicio7joption;

import javax.swing.JOptionPane;

public class ejercicio7joption {
    public static void main(String[] args) {
        int numero, suma = 0, contador = 0;

        do {
            numero = Integer.parseInt(JOptionPane.showInputDialog("Introduce un número (negativo para terminar):"));

            if (numero >= 0) {
                suma += numero;
                contador++;
            }

        } while (numero >= 0);

        if (contador > 0) {
            double media = (double) suma / contador;
            JOptionPane.showMessageDialog(null, "La media es: " + media);
        } else {
            JOptionPane.showMessageDialog(null, "No se ingresaron números válidos.");
        }
    }
}
