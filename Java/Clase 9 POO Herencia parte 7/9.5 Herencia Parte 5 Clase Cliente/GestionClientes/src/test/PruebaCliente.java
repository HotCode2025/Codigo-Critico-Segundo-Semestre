// Archivo: PruebaCliente.java
package test;

import domain.Cliente;

public class PruebaCliente {
    public static void main(String[] args) {
        
        // Creamos nuestro primer objeto cliente
        Cliente cliente1 = new Cliente("Ariel Mazara", 'M', 35, "Calle Yaretas 9", true);
        System.out.println("Datos del Cliente 1:");
        System.out.println(cliente1); // Se llama automáticamente a cliente1.toString()
        
        System.out.println("\n----------------------------------\n");

        // Creamos un segundo objeto cliente para verificar el contador de ID
        Cliente cliente2 = new Cliente("Agustina Zuniga", 'F', 29, "Calle Yaretas 9", false);
        System.out.println("Datos del Cliente 2:");
        System.out.println(cliente2);
        
    
    }
}