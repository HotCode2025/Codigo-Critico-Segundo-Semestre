
package test;

/**
 *
 * @author arielmazara
 * 
 */
import dominio.Persona;

public class PersonaPrueba {
    public static void main(String[] args) {
        Persona persona1 = new Persona("Osvaldo", 57.000, false);
        System.out.println("persona1 = " + persona1);
        System.out.println("persona1 su nombre modificado: "+persona1.getNombre());
        
        // Modificar a traves de los metodos
        persona1.setNombre("Juan Ignacio");
        //persona1.nombre ="Juan Ignacio"; //Ya no se puede utilizar
        // System.out.println("Nombre es: "+persona1.nombre); //error
        System.out.println("persona1 con su nombre modificado: "+persona1.getNombre());
        System.out.println("persona1 el resultado para el sueldo: "+persona1.getSueldo());
        System.out.println("persona1 para obtener el booleano: "+persona1.isEliminado());
    
        
        //Tarea: Crear otro objeto de tipo Persona, asignar valores de manera inicial
        //y imprimir, luego modificar sus valores y volver a imprimir
    // --- INICIO DE LA TAREA ---
        System.out.println("\n--- Tarea ---");

        // 1. Crear otro objeto de tipo Persona
        Persona persona2 = new Persona("Ana", 65000.0, false);

        // 2. Imprimir sus valores iniciales
        System.out.println("Valores Iniciales de persona2:");
        System.out.println("Nombre: " + persona2.getNombre());
        System.out.println("Sueldo: " + persona2.getSueldo());
        System.out.println("Eliminado: " + persona2.isEliminado());

        // 3. Modificar sus valores
        persona2.setNombre("Ana Laura");
        persona2.setSueldo(70000.0);
        persona2.setEliminado(true);

        // 4. Volver a imprimir los valores (ya modificados)
        System.out.println("\nValores Modificados de persona2:");
        System.out.println("Nombre: " + persona2.getNombre());
        System.out.println("Sueldo: " + persona2.getSueldo());
        System.out.println("Eliminado: " + persona2.isEliminado());
        // --- FIN DE LA TAREA ---
        
        System.out.println("persona1 = " + persona1);
    }
    
        
}