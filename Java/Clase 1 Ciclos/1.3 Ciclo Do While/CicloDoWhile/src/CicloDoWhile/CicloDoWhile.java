
package CicloDoWhile;

/**
 *
 * @author Ariel
 */
public class CicloDoWhile {
    public static void main(String[] args) {
        var conteo = 0; // Interferencia de tipos
        while(conteo < 3){
            System.out.println("conteo =" + conteo);
            conteo++; //Vamos aumentando en uno la variable
        }
        
        var contador = 0;
        do{
            System.out.println("contador =" + contador);
            contador++;
        }while(contador <= 7); //En Do While en necesario el ;
        
    }
    
}
