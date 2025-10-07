
package Labels;

/**
 *
 * @author Ariel
 */
public class Labels {
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
        
        //Uso de las palabras break y continue junto con las etiquetas Labels
        
        for(var contando = 0 ; contando <= 7 ; contando++ ){
            if(contando % 2 == 0) { //Para numero pares
                 System.out.println("contando =" + contando);
                 break ; 
            }
              
        }
        inicio: //Se utilizan para ciclos anidados las labels
        for(var contando = 0 ; contando <= 7 ; contando++ ){
            if(contando % 2 != 0) {  
                continue inicio; //Vamos a la siguiente iteracion
            }
            System.out.println("contando =" + contando);

         
        }
    
}
}

