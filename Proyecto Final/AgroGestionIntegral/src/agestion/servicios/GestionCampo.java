package agestion.servicios;

import agestion.modelo.Maquinaria;
import agestion.modelo.Parcela;
import agestion.modelo.ProductoAgricola;
import agestion.modelo.TareaCampo;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Gestiona toda la lógica del Cuaderno de Campo, conectando los demás módulos.
 */
public class GestionCampo {

    // Atributos
    private final ArrayList<TareaCampo> historialTareas = new ArrayList<>();
    // Referencias a los otros gestores para poder colaborar con ellos
    private final GestionParcelas gestorParcelas;
    private final GestionStock gestorStock;
    private final GestionMaquinaria gestorMaquinaria;

    /**
     * Constructor que recibe los otros gestores (Inyección de Dependencias).
     * Esto asegura que todos los gestores trabajen con las mismas listas de datos.
     */
    public GestionCampo(GestionParcelas gestorParcelas, GestionStock gestorStock, GestionMaquinaria gestorMaquinaria) {
        this.gestorParcelas = gestorParcelas;
        this.gestorStock = gestorStock;
        this.gestorMaquinaria = gestorMaquinaria;
    }

    /**
     * Orquesta el proceso completo para registrar una nueva tarea en el campo.
     */
    public void registrarNuevaTarea() {
        // --- Validaciones Previas ---
        // Se asegura de que existan datos en los otros módulos antes de continuar.
        if (gestorParcelas.getListaParcelas().isEmpty() || gestorStock.getInventario().isEmpty() || gestorMaquinaria.getFlota().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe registrar al menos una Parcela, un Producto y una Máquina antes de crear una tarea.", "Faltan Datos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // --- 1. Selección de Parcela ---
            Parcela parcelaSeleccionada = (Parcela) JOptionPane.showInputDialog(null, "Seleccione la parcela:",
                "Registrar Tarea (Paso 1/5)", JOptionPane.QUESTION_MESSAGE, null, 
                gestorParcelas.getListaParcelas().toArray(), gestorParcelas.getListaParcelas().get(0));
            if (parcelaSeleccionada == null) { return; } // El usuario canceló

            // --- 2. Selección de Producto ---
            ProductoAgricola productoSeleccionado = (ProductoAgricola) JOptionPane.showInputDialog(null, "Seleccione el producto a utilizar:",
                "Registrar Tarea (Paso 2/5)", JOptionPane.QUESTION_MESSAGE, null,
                gestorStock.getInventario().toArray(), gestorStock.getInventario().get(0));
            if (productoSeleccionado == null) { return; }

            // --- 3. Selección de Maquinaria ---
            Maquinaria maquinaSeleccionada = (Maquinaria) JOptionPane.showInputDialog(null, "Seleccione la máquina:",
                "Registrar Tarea (Paso 3/5)", JOptionPane.QUESTION_MESSAGE, null,
                gestorMaquinaria.getFlota().toArray(), gestorMaquinaria.getFlota().get(0));
            if (maquinaSeleccionada == null) { return; }

            // --- 4. Ingreso de Datos Adicionales ---
            String cantidadStr = JOptionPane.showInputDialog(null, "Cantidad de producto a utilizar (Stock actual: " + productoSeleccionado.getCantidadEnStock() + "):", "Registrar Tarea (Paso 4/5)", JOptionPane.QUESTION_MESSAGE);
            double cantidad = Double.parseDouble(cantidadStr);

            if (cantidad <= 0 || cantidad > productoSeleccionado.getCantidadEnStock()) {
                JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor a cero y no puede exceder el stock disponible.", "Error de Stock", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String descripcion = JOptionPane.showInputDialog(null, "Breve descripción de la tarea (ej: 'Aplicación foliar'):", "Registrar Tarea (Paso 5/5)", JOptionPane.QUESTION_MESSAGE);
            if (descripcion == null || descripcion.trim().isEmpty()) { descripcion = "Sin descripción"; }
            
            String operador = JOptionPane.showInputDialog(null, "Nombre del operador que realiza la tarea:", "Registrar Tarea (Paso 5/5)", JOptionPane.QUESTION_MESSAGE);
            if (operador == null || operador.trim().isEmpty()) { operador = "No especificado"; }

            // --- 5. Creación del Objeto Tarea y Registro ---
            int nuevaId = historialTareas.size() + 1;
            TareaCampo nuevaTarea = new TareaCampo(nuevaId, LocalDate.now(), descripcion, operador, parcelaSeleccionada, productoSeleccionado, maquinaSeleccionada, cantidad);
            
            historialTareas.add(nuevaTarea);
            gestorStock.actualizarStock(productoSeleccionado, -cantidad); // Restamos la cantidad del stock

            JOptionPane.showMessageDialog(null, "Tarea registrada exitosamente en el Cuaderno de Campo.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La cantidad debe ser un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado al registrar la tarea.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Imprime el error en la consola para depuración
        }
    }

    /**
     * Muestra el historial completo de todas las tareas registradas.
     */
    public void mostrarHistorialTareas() {
        if (historialTareas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay tareas registradas en el cuaderno de campo.", "Historial Vacío", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder historial = new StringBuilder("--- CUADERNO DE CAMPO ---\n\n");
        for (TareaCampo tarea : historialTareas) {
            historial.append(tarea.toString()).append("\n--------------------------------\n");
        }

        JOptionPane.showMessageDialog(null, historial.toString(), "Historial de Tareas", JOptionPane.PLAIN_MESSAGE);
    }
}