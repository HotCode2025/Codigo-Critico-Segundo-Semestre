package Operaciones;

public class Aritmetica {
    //Atributos de la clase
    int a;
    int b;
    
    // El constructor es un metodo especial
    public Aritmetica(){ //Constructor 1
        System.out.println("Se esta ejecutando este constructor numero uno");
    }
    
    
    //Estamos viendo lo que se llama sobrecarga de constructores
    public Aritmetica(int a, int b){ //Constructor 2
        this.a = a;
        this. b = b;
        System.out.println("Se esta ejecutando este constructor numero dos");
        
    }
    
    //Metodo
    
    public void sumarNumeros() {
    int resultado = a + b;
    System.out.println("La suma es: " + resultado);
    }
}
    
