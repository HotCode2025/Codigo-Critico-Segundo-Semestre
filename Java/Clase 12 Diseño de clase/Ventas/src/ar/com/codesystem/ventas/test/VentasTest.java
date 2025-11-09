
package ar.com.codesystem.ventas.test;

import ar.com.codesystem.ventas.*;

/**
 *
 * @author ariel
 */
public class VentasTest {
    public static void main(String[] args) {
        Producto producto1 = new Producto("Pantalon", 9500.00);
        Producto producto2 = new Producto("Campera", 29900.00);
        
        Orden orden1 = new Orden();
        //Agregamos productos a la orden
        orden1.agregarProducto(producto1);
        orden1.agregarProducto(producto2);
        orden1.mostrarOrden();
        
        //Tarea
        //Crear mas objetos de tipo Producto
        //Crear msa objetos de tipo Orden
        
        // --- INICIO DE LA TAREA ---
        
        // 1. Crear más productos (hasta tener 10 en total)
        Producto producto3 = new Producto("Remera", 7500.00);
        Producto producto4 = new Producto("Zapatillas", 35000.00);
        Producto producto5 = new Producto("Buzo", 15000.00);
        Producto producto6 = new Producto("Gorra", 4500.00);
        Producto producto7 = new Producto("Medias", 2000.00);
        Producto producto8 = new Producto("Cinturon", 6000.00);
        Producto producto9 = new Producto("Short", 8000.00);
        Producto producto10 = new Producto("Lentes", 12000.00);

        // 2. Crear más ordenes (hasta tener 2 en total)
        Orden orden2 = new Orden();

        // 3. Agregar productos a las ordenes
        
        // Agregamos más productos a la orden 1
        orden1.agregarProducto(producto3);
        orden1.agregarProducto(producto4);
        
        // Agregamos productos a la orden 2
        orden2.agregarProducto(producto5);
        orden2.agregarProducto(producto6);
        orden2.agregarProducto(producto7);
        orden2.agregarProducto(producto8);
        orden2.agregarProducto(producto9);
        orden2.agregarProducto(producto10);

        // 4. Mostrar todas las ordenes
        System.out.println("--- MOSTRANDO ORDEN 1 ---");
        orden1.mostrarOrden();
        
        System.out.println("\n--- MOSTRANDO ORDEN 2 ---");
        orden2.mostrarOrden();
        
        // --- FIN DE LA TAREA ---
        
        // --- PRUEBA ADICIONAL (Opcional) ---
        // Probamos agregar 11 productos a una orden nueva
        
        System.out.println("\n--- PROBANDO EL LÍMITE DE PRODUCTOS ---");
        Orden orden3 = new Orden();
        orden3.agregarProducto(producto1);
        orden3.agregarProducto(producto2);
        orden3.agregarProducto(producto3);
        orden3.agregarProducto(producto4);
        orden3.agregarProducto(producto5);
        orden3.agregarProducto(producto6);
        orden3.agregarProducto(producto7);
        orden3.agregarProducto(producto8);
        orden3.agregarProducto(producto9);
        orden3.agregarProducto(producto10);
        
        // Este es el producto 11
        System.out.println("Intentando agregar el producto 11...");
        Producto producto11 = new Producto("Mochila", 18000.00);
        orden3.agregarProducto(producto11); // Aquí debe saltar el mensaje de error

        // Mostramos la orden 3, que solo debe tener los primeros 10 productos
        orden3.mostrarOrden();
    }
}
    
    

