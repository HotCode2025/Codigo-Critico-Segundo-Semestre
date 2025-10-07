/*
Pedir 10 números y escribir la suma total.
Hacerlo con la clase Scanner y JOptionPane
*/
package Ciclos10JOption;


import javax.swing.JOptionPane;
public class Ciclos10JOption {
    public static void main(String[] args) {
      int suma = 0;

        for (int i = 1; i <= 10; i++) {
            String input = JOptionPane.showInputDialog("Ingrese el número " + i + ":");
            int numero = Integer.parseInt(input);
            suma += numero;
        }

        JOptionPane.showMessageDialog(null, "La suma total es: " + suma);
    }
}
    
    

