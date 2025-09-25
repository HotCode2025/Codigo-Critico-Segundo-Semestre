package EjercicioCiclos11;

import javax.swing.JOptionPane;

public class EjercicioCiclos11 {

    
    public static void main(String[] args) {
        
        long producto = 1L; // Usamos 'long' para un número grande

        // Ciclo para calcular el producto de los primeros 10 impares (1 al 19)
        // 1∗3∗5∗7∗9∗11∗13∗15∗17∗19 = 654,729,075
        for (int i = 1; i <= 19; i += 2) {
            producto = producto * i;
        }

        // Mostramos el resultado en consola
        System.out.println("El resultado es: " + producto);
        
        // Mostramos el resultado en una ventana
        JOptionPane.showMessageDialog(null, "El producto de los 10 primeros números impares es: " + producto);
    }
}