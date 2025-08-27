/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ciclos05;

import javax.swing.JOptionPane;
/**
 *
 * @author ArielMazara
 */
public class Ciclos05 {
    public static void main(String[] args) {
        // 1. Generar el número aleatorio (igual que en el ejemplo anterior)
        int numeroSecreto = (int) (Math.random() * 101);

        int numeroUsuario = -1; // Inicializamos con un valor que no pueda ser el correcto
        int intentos = 0;       // Contador de intentos

        // Mensaje de bienvenida inicial
        JOptionPane.showMessageDialog(null, "¡Juego de Adivinar el Número!\nHe pensado en un número entre 0 y 100. ¡Intenta adivinarlo!");

        // 2. Iniciar el ciclo para pedir números
        do {
            // Variable para guardar la entrada como texto
            String entrada;
            
            // Pedimos el número usando una ventana de diálogo
            entrada = JOptionPane.showInputDialog(null, "Introduce tu número:");

            // Si el usuario presiona "Cancelar" o cierra la ventana, terminamos el juego.
            if (entrada == null) {
                JOptionPane.showMessageDialog(null, "Juego cancelado.");
                System.exit(0); // Cierra la aplicación
            }

            try {
                // 3. Convertir el texto a número entero
                numeroUsuario = Integer.parseInt(entrada);
                intentos++; // Solo contamos como intento si ingresa un número válido

                // 4. Dar pistas al usuario con ventanas de mensaje
                if (numeroUsuario < numeroSecreto) {
                    JOptionPane.showMessageDialog(null, "El número secreto es MAYOR.");
                } else if (numeroUsuario > numeroSecreto) {
                    JOptionPane.showMessageDialog(null, "El número secreto es MENOR.");
                }

            } catch (NumberFormatException e) {
                // Manejo de error si el usuario no introduce un número
                JOptionPane.showMessageDialog(null, "Error: Por favor, introduce solo números válidos.");
            }

        } while (numeroUsuario != numeroSecreto); // El ciclo se repite mientras no adivine

        // 5. Mostrar mensaje final cuando acierta
        String mensajeFinal = String.format(
            "¡Felicidades! ¡Has adivinado el número secreto!\n\nEl número era: %d\nLo lograste en %d intentos.",
            numeroSecreto,
            intentos
        );
        JOptionPane.showMessageDialog(null, mensajeFinal);
    }
} 

