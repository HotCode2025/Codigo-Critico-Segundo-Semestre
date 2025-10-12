package agestion.servicios;

// Importamos la clase ProductoAgricola porque la vamos a usar aquí.
import agestion.modelo.ProductoAgricola;
import java.util.ArrayList;
import javax.swing.JOptionPane; // ¡Importante para las ventanas!

public class GestionStock {
    // La lista se mantiene igual. 'private' para protegerla.
    private ArrayList<ProductoAgricola> inventario = new ArrayList<>();

    // --- MÉTODOS AHORA COMPLETOS ---
    
    /**
     * Pide al usuario los datos de un nuevo producto usando ventanas
     * y lo añade al inventario.
     */
    public void agregarProducto() {
        try {
            // Pedimos los datos al usuario con ventanas de diálogo
            String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del producto:", "Nuevo Producto", JOptionPane.QUESTION_MESSAGE);
            
            // Si el usuario presiona "Cancelar" o deja el nombre vacío, no hacemos nada.
            if (nombre == null || nombre.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Operación cancelada.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return; // Salimos del método
            }

            String tipo = JOptionPane.showInputDialog(null, "Ingrese el tipo (ej: Fertilizante, Semilla):", "Nuevo Producto", JOptionPane.QUESTION_MESSAGE);
            String cantidadStr = JOptionPane.showInputDialog(null, "Ingrese la cantidad inicial (ej: 50.5):", "Nuevo Producto", JOptionPane.QUESTION_MESSAGE);
            
            // Convertimos la cantidad de texto a número. Puede fallar si el usuario escribe letras.
            double cantidad = Double.parseDouble(cantidadStr);
            int codigo = inventario.size() + 1; // Un código simple basado en el tamaño actual

            // Creamos el nuevo objeto ProductoAgricola
            ProductoAgricola nuevoProducto = new ProductoAgricola(codigo, nombre, tipo, cantidad);
            
            // Lo añadimos a nuestra lista
            inventario.add(nuevoProducto);

            // Informamos al usuario que todo salió bien
            JOptionPane.showMessageDialog(null, "¡Producto '" + nombre + "' agregado correctamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            // Este bloque se ejecuta si el usuario ingresa texto en lugar de un número para la cantidad.
            JOptionPane.showMessageDialog(null, "Error: La cantidad debe ser un número válido.", "Error de formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            // Captura cualquier otro error inesperado
            JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado al agregar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Muestra una lista de todos los productos en el inventario
     * usando una ventana de diálogo.
     */
    public void mostrarStock() {
        // Primero, verificamos si hay productos para mostrar.
        if (inventario.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El inventario está vacío. Agregue productos primero.", "Stock Vacío", JOptionPane.INFORMATION_MESSAGE);
            return; // Salimos del método si no hay nada que mostrar
        }

        // Usamos un StringBuilder para construir el texto que mostraremos. Es más eficiente.
        StringBuilder listaProductos = new StringBuilder("--- INVENTARIO ACTUAL ---\n\n");
        
        // Recorremos la lista de productos (¡Bucle!)
        for (ProductoAgricola producto : inventario) {
            // Usamos el método toString() que definimos en la clase ProductoAgricola
            listaProductos.append(producto.toString()).append("\n");
        }

        // Mostramos la lista completa en una sola ventana.
        JOptionPane.showMessageDialog(null, listaProductos.toString(), "Gestión de Stock", JOptionPane.PLAIN_MESSAGE);
    }
}