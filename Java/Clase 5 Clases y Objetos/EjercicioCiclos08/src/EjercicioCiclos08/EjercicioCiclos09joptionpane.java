package ejercicio2;

import javax.swing.JOptionPane;

public class EjercicioCiclos09joptionpane {
    public static void main(String[] args) {
        // pedir los datos al usuario
        int dia = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el día (1-30):"));
        int mes = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el mes (1-12):"));
        int anio = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el año:"));

        // verificar si la fecha es válida
        if (dia >= 1 && dia <= 30 && mes >= 1 && mes <= 12) {
            JOptionPane.showMessageDialog(null, 
                "La fecha ingresada es correcta: " + dia + "/" + mes + "/" + anio);
        } else {
            JOptionPane.showMessageDialog(null, 
                "La fecha ingresada es INCORRECTA.");
        }
    }
}