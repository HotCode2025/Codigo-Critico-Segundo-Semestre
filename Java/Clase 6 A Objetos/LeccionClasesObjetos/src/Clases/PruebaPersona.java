package Clases;

public class PruebaPersona {
    public static void main(String[] args) {
        // Primer objeto Persona
        Persona persona1 = new Persona();  // Llamamos al constructor
        persona1.nombre = "Ariel";         // Asignamos valores
        persona1.apellido = "Betancud";
        persona1.obtenerInformacion();     // Llamamos al método

        // Segundo objeto Persona
        Persona persona2 = new Persona();  
        System.out.println("persona2 = " + persona2);
        System.out.println("persona1 = " + persona1);

        persona2.obtenerInformacion();     // Muestra sin datos (aún vacíos)
        
        persona2.nombre = "Osvaldo";
        persona2.apellido = "Giordanini";
        persona2.obtenerInformacion();     // Muestra los datos asignados
    }
}
