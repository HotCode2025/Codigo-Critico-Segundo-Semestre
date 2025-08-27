/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Break;

/**
 *
 * @author Ariel
 */
public class Continue {
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
        
        for( var contando = 0 ; contando <= 7 ; contando++ ){
            if(contando % 2 == 0) { //Para numero pares
                 System.out.println("contando =" + contando);
                 break; 
            }
              
        }
        for( var contando = 0 ; contando <= 7 ; contando++ ){
            if(contando % 2 != 0) {  
                continue; //Vamos a la siguiente iteracion
            }
            System.out.println("contando =" + contando);

              
        }
    
}
}
