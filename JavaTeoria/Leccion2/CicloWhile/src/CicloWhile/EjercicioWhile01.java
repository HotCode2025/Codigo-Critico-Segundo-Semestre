package CicloWhile;

public class EjercicioWhile01 {
    public static void main(String[] args) {
        var conteo = 0; //Inferencia de tipos (Java "adivina" el tipo)
        while (conteo < 3){
            System.out.println("conteo = " + conteo);
            conteo++; //Vamos aumentando en uno la variable
        }
        //Ciclo do while: se ejecuta y después evalua la condición
        var contador = 0;
        do {
            System.out.println("contador = " + contador);
            contador++;
        } while (contador <= 7);
        
        
        //El Ciclo for tiene un número determinado de iteraciones
        for (var contando = 0; contando <= 7; contando++) {  //Declaración; condición; incremento/decremento
            if (contando % 2 == 0) { //Encontrar los números pares
                System.out.println("contando = " + contando);
                //La palabra breack nos permite romper el ciclo (nos va a mostrar solo el primer número par)
                break;
            }
        }
        
        //Etiquetas Labels: nos permite indicar a breack y continue ir hacia un lugar especifico del programa,
        //Este es mayormente util en ciclos anidados
        inicio:
        for (var contando = 0; contando <= 7; contando++) {  //Declaración; condición; incremento/decremento
            if (contando % 2 != 0) { //Encontrar los números impares
                continue inicio; //Vamos a la siguiente iteración, ignorando los impares
            }
            System.out.println("contando = " + contando);
        }
       
    }
}
