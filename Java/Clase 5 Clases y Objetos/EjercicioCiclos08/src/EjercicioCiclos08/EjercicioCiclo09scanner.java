package ejercicio2;

import java.util.Scanner;

public class EjercicioCiclos09scanner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese el día:");
        int dia = sc.nextInt();

        System.out.println("Ingrese el mes:");
        int mes = sc.nextInt();

        System.out.println("Ingrese el año:");
        int anio = sc.nextInt();

        boolean fechaValida = true;

        // Validamos el año
        if (anio <= 0) {
            fechaValida = false;
        }

        // Validamos el mes
        if (mes < 1 || mes > 12) {
            fechaValida = false;
        }

        // Validamos el día 
        if (dia < 1 || dia > 30) {
            fechaValida = false;
        }

        if (fechaValida) {
            System.out.println("La fecha " + dia + "/" + mes + "/" + anio + " es correcta.");
        } else {
            System.out.println("La fecha ingresada no es correcta.");
        }

        sc.close();
    }
}
