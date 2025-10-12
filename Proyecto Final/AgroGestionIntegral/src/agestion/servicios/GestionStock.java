package agestion.servicios;

import agestion.modelo.ProductoAgricola;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GestionStock {
    
    private ArrayList<ProductoAgricola> inventario = new ArrayList<>();

    public void agregarProducto() {
        try {
            String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del producto:", "Nuevo Producto", JOptionPane.QUESTION_MESSAGE);
            if (nombre == null || nombre.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Operación cancelada.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String tipo = JOptionPane.showInputDialog(null, "Ingrese el tipo (ej: Fertilizante, Semilla):", "Nuevo Producto", JOptionPane.QUESTION_MESSAGE);
            String cantidadStr = JOptionPane.showInputDialog(null, "Ingrese la cantidad inicial (ej: 50.5):", "Nuevo Producto", JOptionPane.QUESTION_MESSAGE);
            
            double cantidad = Double.parseDouble(cantidadStr);
            int codigo = inventario.size() + 1;

            ProductoAgricola nuevoProducto = new ProductoAgricola(codigo, nombre, tipo, cantidad);
            inventario.add(nuevoProducto);

            JOptionPane.showMessageDialog(null, "¡Producto '" + nombre + "' agregado correctamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: La cantidad debe ser un número válido.", "Error de formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado al agregar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrarStock() {
        if (inventario.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El inventario está vacío. Agregue productos primero.", "Stock Vacío", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder listaProductos = new StringBuilder("--- INVENTARIO ACTUAL ---\n\n");
        for (ProductoAgricola producto : inventario) {
            listaProductos.append(producto.toString()).append("\n");
        }

        JOptionPane.showMessageDialog(null, listaProductos.toString(), "Gestión de Stock", JOptionPane.PLAIN_MESSAGE);
    }

    // --- MÉTODOS NUEVOS AÑADIDOS AQUÍ ---

    /**
     * Permite que otras clases (como GestionCampo) obtengan la lista completa de productos.
     * @return El ArrayList del inventario.
     */
    public ArrayList<ProductoAgricola> getInventario() {
        return this.inventario;
    }

    /**
     * Modifica la cantidad en stock de un producto específico.
     * @param producto El producto a modificar.
     * @param cantidad La cantidad a sumar (si es positivo) o restar (si es negativo).
     */
    public void actualizarStock(ProductoAgricola producto, double cantidad) {
        // Calcula el nuevo stock y lo establece en el objeto producto.
        double nuevoStock = producto.getCantidadEnStock() + cantidad;
        producto.setCantidadEnStock(nuevoStock);
    }
    
} // <-- Fin de la clase GestionStock