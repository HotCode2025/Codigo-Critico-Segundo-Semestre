package agestion.servicios;

import agestion.modelo.Maquinaria;
import agestion.modelo.Parcela;
import agestion.modelo.ProductoAgricola;
import agestion.modelo.TareaCampo;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * GESTIÓN DE CAMPO CON INTERFAZ DE SOLAPAS MODERNA
 * 
 * Esta clase gestiona las tareas de campo mediante una interfaz con pestañas
 * que organiza las funcionalidades de manera intuitiva y accesible.
 * 
 * @author Código Crítico 2025
 * @version 2.0
 */
public class GestionCampo {

    private final ArrayList<TareaCampo> historialTareas = new ArrayList<>();
    private final GestionParcelas gestorParcelas;
    private final GestionStock gestorStock;
    private final GestionMaquinaria gestorMaquinaria;

    // Componentes de la interfaz
    private JComboBox<Parcela> comboParcelas;
    private JComboBox<ProductoAgricola> comboProductos;
    private JComboBox<Maquinaria> comboMaquinarias;
    private JTextField txtCantidad;
    private JTextField txtDescripcion;
    private JTextField txtOperador;
    private JTextArea areaHistorial;

    public GestionCampo(GestionParcelas gestorParcelas, GestionStock gestorStock, GestionMaquinaria gestorMaquinaria) {
        this.gestorParcelas = gestorParcelas;
        this.gestorStock = gestorStock;
        this.gestorMaquinaria = gestorMaquinaria;
    }

    /**
     * MUESTRA LA INTERFAZ PRINCIPAL CON SOLAPAS
     */
    public void mostrarInterfazCompleta() {
        // Crear diálogo principal
        JDialog dialog = new JDialog();
        dialog.setTitle("Gestión de Campo - Cuaderno de Campo");
        dialog.setModal(true);
        dialog.setSize(800, 600);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());

        // Crear panel de solapas
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Agregar las solapas
        tabbedPane.addTab("📝 Registrar Tarea", crearPanelRegistroTarea());
        tabbedPane.addTab("📊 Historial Tareas", crearPanelHistorial());
        tabbedPane.addTab("📈 Estadísticas", crearPanelEstadisticas());

        dialog.add(tabbedPane, BorderLayout.CENTER);
        
        // Panel de botones inferiores
        JPanel panelBotones = crearPanelBotones(dialog);
        dialog.add(panelBotones, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    /**
     * CREA EL PANEL DE REGISTRO DE TAREAS
     */
    private JPanel crearPanelRegistroTarea() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Inicializar componentes
        inicializarComponentes();

        // Parcela
        panel.add(new JLabel("Parcela:"));
        panel.add(comboParcelas);

        // Producto
        panel.add(new JLabel("Producto a utilizar:"));
        panel.add(comboProductos);

        // Maquinaria
        panel.add(new JLabel("Maquinaria:"));
        panel.add(comboMaquinarias);

        // Cantidad
        panel.add(new JLabel("Cantidad:"));
        panel.add(txtCantidad);

        // Descripción
        panel.add(new JLabel("Descripción de la tarea:"));
        panel.add(txtDescripcion);

        // Operador
        panel.add(new JLabel("Nombre del operador:"));
        panel.add(txtOperador);

        // Botón de registro
        JButton btnRegistrar = new JButton("✅ Registrar Tarea");
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarTareaDesdeInterfaz();
            }
        });
        panel.add(btnRegistrar);

        return panel;
    }

    /**
     * CREA EL PANEL DE HISTORIAL DE TAREAS
     */
    private JPanel crearPanelHistorial() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        areaHistorial = new JTextArea(20, 50);
        areaHistorial.setEditable(false);
        areaHistorial.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(areaHistorial);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botón para actualizar historial
        JButton btnActualizar = new JButton("🔄 Actualizar Historial");
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarHistorial();
            }
        });
        
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnActualizar);
        panel.add(panelBoton, BorderLayout.SOUTH);

        // Cargar historial inicial
        actualizarHistorial();

        return panel;
    }

    /**
     * CREA EL PANEL DE ESTADÍSTICAS
     */
    private JPanel crearPanelEstadisticas() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextArea areaEstadisticas = new JTextArea();
        areaEstadisticas.setEditable(false);
        areaEstadisticas.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        
        // Calcular estadísticas
        String estadisticas = calcularEstadisticas();
        areaEstadisticas.setText(estadisticas);
        
        panel.add(new JScrollPane(areaEstadisticas), BorderLayout.CENTER);

        return panel;
    }

    /**
     * INICIALIZA LOS COMPONENTES DE LA INTERFAZ
     */
    private void inicializarComponentes() {
        // Combo de parcelas
        comboParcelas = new JComboBox<>();
        for (Parcela parcela : gestorParcelas.getListaParcelas()) {
            comboParcelas.addItem(parcela);
        }

        // Combo de productos
        comboProductos = new JComboBox<>();
        for (ProductoAgricola producto : gestorStock.getInventario()) {
            comboProductos.addItem(producto);
        }

        // Combo de maquinarias
        comboMaquinarias = new JComboBox<>();
        for (Maquinaria maquinaria : gestorMaquinaria.getFlota()) {
            comboMaquinarias.addItem(maquinaria);
        }

        // Campos de texto
        txtCantidad = new JTextField();
        txtDescripcion = new JTextField();
        txtOperador = new JTextField();
    }

    /**
     * REGISTRA UNA TAREA DESDE LA INTERFAZ GRÁFICA
     */
    private void registrarTareaDesdeInterfaz() {
        try {
            // Validaciones
            if (comboParcelas.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Seleccione una parcela", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (comboProductos.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Seleccione un producto", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double cantidad = Double.parseDouble(txtCantidad.getText());
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor a cero", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener datos de la interfaz
            Parcela parcela = (Parcela) comboParcelas.getSelectedItem();
            ProductoAgricola producto = (ProductoAgricola) comboProductos.getSelectedItem();
            Maquinaria maquinaria = (Maquinaria) comboMaquinarias.getSelectedItem();
            String descripcion = txtDescripcion.getText().isEmpty() ? "Sin descripción" : txtDescripcion.getText();
            String operador = txtOperador.getText().isEmpty() ? "No especificado" : txtOperador.getText();

            // Verificar stock
            if (cantidad > producto.getCantidadEnStock()) {
                JOptionPane.showMessageDialog(null, 
                    "Stock insuficiente. Stock disponible: " + producto.getCantidadEnStock(), 
                    "Error de Stock", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear y registrar tarea
            int nuevaId = historialTareas.size() + 1;
            TareaCampo nuevaTarea = new TareaCampo(nuevaId, LocalDate.now(), descripcion, operador, 
                                                  parcela, producto, maquinaria, cantidad);
            
            historialTareas.add(nuevaTarea);
            gestorStock.actualizarStock(producto, -cantidad);

            // Limpiar campos
            limpiarCampos();
            
            // Actualizar historial
            actualizarHistorial();

            JOptionPane.showMessageDialog(null, "✅ Tarea registrada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La cantidad debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar tarea: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * ACTUALIZA EL CONTENIDO DEL HISTORIAL
     */
    private void actualizarHistorial() {
        if (historialTareas.isEmpty()) {
            areaHistorial.setText("No hay tareas registradas en el cuaderno de campo.");
            return;
        }

        StringBuilder historial = new StringBuilder();
        historial.append("=== CUADERNO DE CAMPO - HISTORIAL COMPLETO ===\n\n");
        
        for (TareaCampo tarea : historialTareas) {
            historial.append(tarea.toString()).append("\n");
            historial.append("----------------------------------------\n");
        }

        areaHistorial.setText(historial.toString());
    }

    /**
     * CALCULA ESTADÍSTICAS DE LAS TAREAS
     */
    private String calcularEstadisticas() {
        StringBuilder stats = new StringBuilder();
        stats.append("=== ESTADÍSTICAS DE TAREAS DE CAMPO ===\n\n");
        
        stats.append("Total de tareas registradas: ").append(historialTareas.size()).append("\n\n");
        
        if (!historialTareas.isEmpty()) {
            // Tareas por parcela
            stats.append("Tareas por parcela:\n");
            // Implementar lógica de agrupación por parcela
            
            // Productos más utilizados
            stats.append("\nProductos más utilizados:\n");
            // Implementar lógica de agrupación por producto
            
            // Operadores más activos
            stats.append("\nOperadores más activos:\n");
            // Implementar lógica de agrupación por operador
        }

        return stats.toString();
    }

    /**
     * LIMPIA LOS CAMPOS DEL FORMULARIO
     */
    private void limpiarCampos() {
        txtCantidad.setText("");
        txtDescripcion.setText("");
        txtOperador.setText("");
    }

    /**
     * CREA EL PANEL DE BOTONES INFERIORES
     */
    private JPanel crearPanelBotones(JDialog dialog) {
        JPanel panel = new JPanel();
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        
        panel.add(btnCerrar);
        return panel;
    }

    /**
     * MÉTODO COMPATIBLE CON LA VERSIÓN ANTERIOR
     */
    public void registrarNuevaTarea() {
        mostrarInterfazCompleta();
    }

    /**
     * MÉTODO COMPATIBLE CON LA VERSIÓN ANTERIOR
     */
    public void mostrarHistorialTareas() {
        mostrarInterfazCompleta();
    }
}