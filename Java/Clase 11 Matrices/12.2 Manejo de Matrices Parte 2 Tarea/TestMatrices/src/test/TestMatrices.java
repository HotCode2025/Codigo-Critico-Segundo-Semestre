package test;

public class TestMatrices {
    public static void main(String[] args) {
        int edades[][] = new int[3][2];
        System.out.println("edades = " + edades);

        // Llenado manual
        edades[0][0] = 5; // llenado manual
        edades[0][1] = 7; // es una diferente columna
        edades[1][0] = 8; // 
        edades[1][1] = 4; // 
        edades[2][0] = 2; // terminamos la tarea
        edades[2][1] = 9; // 

        // Imprimir valores de la matriz
        System.out.println("edades 0-0 = " + edades[0][0]);
        System.out.println("edades 0-1 = " + edades[0][1]);
        System.out.println("edades 1-0 = " + edades[1][0]);
        System.out.println("edades 1-1 = " + edades[1][1]);
        System.out.println("edades 2-0 = " + edades[2][0]);
        System.out.println("edades 2-1 = " + edades[2][1]);
    }
}
