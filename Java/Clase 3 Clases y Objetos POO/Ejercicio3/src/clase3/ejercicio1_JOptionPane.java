package clase3;

import javax.swing.JOptionPane;

public class ejercicio1_JOptionPane {
    public static void main(String[] args) {
        int numero;

        do {
            numero = Integer.parseInt(JOptionPane.showInputDialog("Ingrese un número (0 para salir):"));

            if (numero != 0) {
                if (numero % 2 == 0) {
                    JOptionPane.showMessageDialog(null, "El número " + numero + " es PAR.");
                } else {
                    JOptionPane.showMessageDialog(null, "El número " + numero + " es IMPAR.");
                }
            }
        } while (numero != 0);

        JOptionPane.showMessageDialog(null, "Programa finalizado.");
    }
}
