package Operaciones;

public class PruebaAritmetica {

    public static void main(String[] args) {

        // Crear objeto con constructor vacío
        Aritmetica aritmetica1 = new Aritmetica();
        aritmetica1.a = 3;
        aritmetica1.b = 7;
        aritmetica1.sumarNumeros();

        // Llamar método con retorno
        int resultado = aritmetica1.sumarConRetorno();
        System.out.println("resultado = " + resultado);

        // Llamar método con argumentos
        resultado = aritmetica1.sumarConArgumentos(12, 26);
        System.out.println("Resultado usando argumentos = " + resultado);

        // Mostrar valores actuales
        System.out.println("aritmetica1 a = " + aritmetica1.a);
        System.out.println("aritmetica1 b = " + aritmetica1.b);

        // Crear objeto con constructor con parámetros
        Aritmetica aritmetica2 = new Aritmetica(5, 8);
        System.out.println("aritmetica2 a = " + aritmetica2.a);
        System.out.println("aritmetica2 b = " + aritmetica2.b);
    }
}
