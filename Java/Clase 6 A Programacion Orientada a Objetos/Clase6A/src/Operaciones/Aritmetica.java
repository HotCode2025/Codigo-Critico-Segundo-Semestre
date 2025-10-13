package Operaciones;

public class Aritmetica {
    // Atributos de la Clase
    int a;
    int b;
    
    //EL CONSTRUCTOR ES UN METODO ESPECIAL
   public Aritmetica(){ //Constructor 1 Vacio
       System.out.println("Se esta ejecutando este constructor numero uno");
   }
   
   //Estamos viendo lo que se llama sobrecarga de constructores
   public Aritmetica(int a, int b){ // Constructor 2
       this.a = a;
       this.b = b;
       System.out.println("Se esta ejecutando este constructor numero dos");
   }
    
    // Metodo
    public void sumarNumeros(){
        int resultado = a + b;
        System.out.println("resultado =" + resultado);
    }
    
    public int sumarConRetorno(){
        // int resultado = a + b;
        return this.a + this.b;
    }
    
    public int sumarConArgumentos(int a, int b){ //Jamas se puede utilizar Var por int porque son parametros
        this.a = a; // |El argumento "a" se asigna al atributo this.a
        this.b = b;
       //  return a + b;
       return this.sumarConRetorno();
    }
}
