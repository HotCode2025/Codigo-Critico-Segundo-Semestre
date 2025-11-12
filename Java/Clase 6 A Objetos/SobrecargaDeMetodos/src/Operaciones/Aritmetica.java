package Operaciones;

public class Aritmetica {

    // Atributos de la clase
    int a;
    int b;

    // Constructor 1 (vacío)
    public Aritmetica() {
        System.out.println("Se está ejecutando este constructor número uno");
    }

    // Constructor 2 (con argumentos)
    public Aritmetica(int a, int b) {
        this.a = a;
        this.b = b;
        System.out.println("Se está ejecutando este constructor número dos");
    }

    // Método sin retorno (solo imprime resultado)
    public void sumarNumeros() {
        int resultado = a + b;
        System.out.println("resultado = " + resultado);
    }

    // Método con retorno (devuelve resultado)
    public int sumarConRetorno() {
        return a + b;
    }

    // Método con argumentos (recibe parámetros y devuelve resultado)
    public int sumarConArgumentos(int a, int b) {
        this.a = a;
        this.b = b;
        return a + b;
    }
}
