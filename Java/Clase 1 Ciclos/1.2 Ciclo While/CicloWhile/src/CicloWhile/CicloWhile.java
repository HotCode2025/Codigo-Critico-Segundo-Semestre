
package CicloWhile;

/**
 *
 * @author Ariel
 */
public class CicloWhile {
    
    public static void main(String[] args) {
        var conteo = 0; // Interferencia de tipos
        while(conteo < 3){
            System.out.println("conteo =" + conteo);
            conteo++; //Vamos aumentando en uno la variable
        }
}
}
    
