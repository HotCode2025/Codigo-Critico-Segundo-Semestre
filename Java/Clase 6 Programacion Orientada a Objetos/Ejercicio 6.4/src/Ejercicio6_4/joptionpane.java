package Ejercicio6_4;

import javax.swing.JOptionPane;

public class joptionpane {
    public static void main(String[] args) {
        int ancho = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ancho de la caja:"));
        int alto = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el alto de la caja:"));
        int profundidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la profundidad de la caja:"));

        // Calcular volumen
        int volumen = ancho * alto * profundidad;

        JOptionPane.showMessageDialog(null, "El volumen de la caja es: " + volumen);
    }
}
