/*
Ejercicio 3: Leer números hasta que se introduzca un cero 
Para cada uno indicar si es par o impar.
Prinero lo haremos con la clase Scanner y luego con la 
clase JOptionPane
*/
package Ciclos03;

import javax.swing.JOptionPane;

public class Ejercicio03 {
    public static void main(String[] args) {
        int numero;
        
        numero = Integer.parseInt(JOptionPane.showInputDialog("Digite un número: ")); // se usa cuando necesitás que el usuario te dé información.
        while(numero!= 0){
            if (numero % 2 == 0){
                JOptionPane.showMessageDialog(null, "El número ingresado " +numero+ " es PAR"); //se usa cuando querés mostrar información al usuario.
            }
            else{
                JOptionPane.showMessageDialog(null, "El número ingresado " +numero+ " es IMPAR");//null → significa que no hay una ventana principal asociada (se usa por defecto).
            }
            numero = Integer.parseInt(JOptionPane.showInputDialog("Digite otro número: "));
        }
        JOptionPane.showMessageDialog(null, "El número ingresado es "+numero+" . Finaliza el programa");
    }
}
