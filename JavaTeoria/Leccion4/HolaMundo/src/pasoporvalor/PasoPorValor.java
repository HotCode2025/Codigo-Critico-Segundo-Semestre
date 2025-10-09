/*
 El parametro por valor no tiene el poder para introducir una valor
o retornar un valor dentro del espacio de memoria en la variable local
 */
package pasoporvalor;


public class PasoPorValor {
    public static void main(String[] args) {
        var valorX = 20;
        System.out.println("valorX = " + valorX);
        cambioValor(valorX); //Solo le enviamos una copia
        System.out.println("valorX = " + valorX);
    }
    
    public static void cambioValor(int arg1){ //Parametro por valor
        System.out.println("arg1 = " + arg1);
        //arg1 = 15; //esto no sirve para modificar ya que la variable valorX es local
    }
}
