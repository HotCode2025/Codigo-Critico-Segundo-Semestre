package agestion.servicios;

import agestion.modelo.Maquinaria;
import agestion.modelo.Parcela;
import agestion.modelo.ProductoAgricola;
import agestion.modelo.TareaCampo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;

/**
 * GESTIÓN DE CAMPO CON INTERFAZ DE SOLAPAS MODERNA Y FONDO NEGRO
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

    /**
     * CONSTRUCTOR PRINCIPAL
     * Inicializa los gestores necesarios para la gestión de campo
     */
    public GestionCampo(GestionParcelas gestorParcelas, GestionStock gestorStock, GestionMaquinaria gestorMaquinaria) {
        this.gestorParcelas = gestorParcelas;
        this.gestorStock = gestorStock;
        this.gestorMaquinaria = gestorMaquinaria;
    }

    /**
     * MUESTRA LA INTERFAZ PRINCIPAL CON SOLAPAS Y FONDO NEGRO
     */
    public void mostrarInterfazCompleta() {
        // Crear diálogo principal
        JDialog dialog = new JDialog();
        dialog.setTitle("Gestión de Campo - Cuaderno de Campo");
        dialog.setModal(true);
        dialog.setSize(1000, 700); // Tamaño aumentado para mejor visualización
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());
        
        // FONDO NEGRO PARA EL DIÁLOGO PRINCIPAL
        dialog.getContentPane().setBackground(Color.BLACK);

        // Crear panel de solapas
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(Color.BLACK); // Fondo negro para las pestañas
        tabbedPane.setForeground(Color.WHITE); // Texto blanco en las pestañas
        
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
     * CREA EL PANEL DE REGISTRO DE TAREAS CON FONDO NEGRO
     * @return JPanel configurado para registro de tareas
     */
    private JPanel crearPanelRegistroTarea() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.BLACK); // FONDO NEGRO

        // Inicializar componentes
        inicializarComponentes();

        // ========== CONFIGURACIÓN DE COMPONENTES CON ESTILO OSCURO ==========

        // Parcela
        JLabel lblParcela = new JLabel("Parcela:");
        lblParcela.setForeground(Color.WHITE); // Texto blanco
        panel.add(lblParcela);
        comboParcelas.setBackground(Color.DARK_GRAY); // Fondo gris oscuro
        comboParcelas.setForeground(Color.WHITE); // Texto blanco
        panel.add(comboParcelas);

        // Producto
        JLabel lblProducto = new JLabel("Producto a utilizar:");
        lblProducto.setForeground(Color.WHITE);
        panel.add(lblProducto);
        comboProductos.setBackground(Color.DARK_GRAY);
        comboProductos.setForeground(Color.WHITE);
        panel.add(comboProductos);

        // Maquinaria
        JLabel lblMaquinaria = new JLabel("Maquinaria:");
        lblMaquinaria.setForeground(Color.WHITE);
        panel.add(lblMaquinaria);
        comboMaquinarias.setBackground(Color.DARK_GRAY);
        comboMaquinarias.setForeground(Color.WHITE);
        panel.add(comboMaquinarias);

        // Cantidad
        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setForeground(Color.WHITE);
        panel.add(lblCantidad);
        txtCantidad.setBackground(Color.DARK_GRAY);
        txtCantidad.setForeground(Color.WHITE);
        txtCantidad.setCaretColor(Color.WHITE); // Cursor blanco
        panel.add(txtCantidad);

        // Descripción
        JLabel lblDescripcion = new JLabel("Descripción de la tarea:");
        lblDescripcion.setForeground(Color.WHITE);
        panel.add(lblDescripcion);
        txtDescripcion.setBackground(Color.DARK_GRAY);
        txtDescripcion.setForeground(Color.WHITE);
        txtDescripcion.setCaretColor(Color.WHITE);
        panel.add(txtDescripcion);

        // Operador
        JLabel lblOperador = new JLabel("Nombre del operador:");
        lblOperador.setForeground(Color.WHITE);
        panel.add(lblOperador);
        txtOperador.setBackground(Color.DARK_GRAY);
        txtOperador.setForeground(Color.WHITE);
        txtOperador.setCaretColor(Color.WHITE);
        panel.add(txtOperador);

        // Botón de registro con estilo oscuro
        JButton btnRegistrar = crearBotonOscuro("✅ Registrar Tarea");
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
     * CREA EL PANEL DE HISTORIAL DE TAREAS CON FONDO NEGRO
     * @return JPanel configurado para mostrar historial
     */
    private JPanel crearPanelHistorial() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.BLACK); // FONDO NEGRO

        // Área de texto para el historial
        areaHistorial = new JTextArea(20, 50);
        areaHistorial.setEditable(false);
        areaHistorial.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaHistorial.setBackground(Color.DARK_GRAY); // Fondo gris oscuro
        areaHistorial.setForeground(Color.WHITE); // Texto blanco
        areaHistorial.setCaretColor(Color.WHITE); // Cursor blanco
        
        // Scroll pane para el área de texto
        JScrollPane scrollPane = new JScrollPane(areaHistorial);
        scrollPane.getViewport().setBackground(Color.DARK_GRAY); // Fondo del viewport
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botón para actualizar historial
        JButton btnActualizar = crearBotonOscuro("🔄 Actualizar Historial");
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarHistorial();
            }
        });
        
        // Panel para el botón
        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(Color.BLACK);
        panelBoton.add(btnActualizar);
        panel.add(panelBoton, BorderLayout.SOUTH);

        // Cargar historial inicial
        actualizarHistorial();

        return panel;
    }

    /**
     * CREA EL PANEL DE ESTADÍSTICAS CON FONDO NEGRO
     * @return JPanel configurado para mostrar estadísticas
     */
    private JPanel crearPanelEstadisticas() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.BLACK); // FONDO NEGRO

        // Área de texto para estadísticas
        JTextArea areaEstadisticas = new JTextArea();
        areaEstadisticas.setEditable(false);
        areaEstadisticas.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaEstadisticas.setBackground(Color.DARK_GRAY);
        areaEstadisticas.setForeground(Color.WHITE);
        areaEstadisticas.setCaretColor(Color.WHITE);
        
        // Calcular y mostrar estadísticas
        String estadisticas = calcularEstadisticas();
        areaEstadisticas.setText(estadisticas);
        
        panel.add(new JScrollPane(areaEstadisticas), BorderLayout.CENTER);

        return panel;
    }

    /**
     * INICIALIZA LOS COMPONENTES DE LA INTERFAZ
     * Configura los combobox y campos de texto
     */
    private void inicializarComponentes() {
        // Combo de parcelas - carga todas las parcelas disponibles
        comboParcelas = new JComboBox<>();
        for (Parcela parcela : gestorParcelas.getListaParcelas()) {
            comboParcelas.addItem(parcela);
        }

        // Combo de productos - carga todos los productos del inventario
        comboProductos = new JComboBox<>();
        for (ProductoAgricola producto : gestorStock.getInventario()) {
            comboProductos.addItem(producto);
        }

        // Combo de maquinarias - carga toda la flota disponible
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
     * Valida los datos y crea una nueva tarea de campo
     */
    private void registrarTareaDesdeInterfaz() {
        try {
            // ========== VALIDACIONES DE DATOS ==========

            // Validar parcela seleccionada
            if (comboParcelas.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Seleccione una parcela", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar producto seleccionado
            if (comboProductos.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Seleccione un producto", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar cantidad
            double cantidad = Double.parseDouble(txtCantidad.getText());
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor a cero", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // ========== OBTENER DATOS DE LA INTERFAZ ==========

            Parcela parcela = (Parcela) comboParcelas.getSelectedItem();
            ProductoAgricola producto = (ProductoAgricola) comboProductos.getSelectedItem();
            Maquinaria maquinaria = (Maquinaria) comboMaquinarias.getSelectedItem();
            String descripcion = txtDescripcion.getText().isEmpty() ? "Sin descripción" : txtDescripcion.getText();
            String operador = txtOperador.getText().isEmpty() ? "No especificado" : txtOperador.getText();

            // ========== VERIFICAR STOCK DISPONIBLE ==========

            if (cantidad > producto.getCantidadEnStock()) {
                JOptionPane.showMessageDialog(null, 
                    "Stock insuficiente. Stock disponible: " + producto.getCantidadEnStock(), 
                    "Error de Stock", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // ========== CREAR Y REGISTRAR TAREA ==========

            int nuevaId = historialTareas.size() + 1;
            TareaCampo nuevaTarea = new TareaCampo(nuevaId, LocalDate.now(), descripcion, operador, 
                                                  parcela, producto, maquinaria, cantidad);
            
            historialTareas.add(nuevaTarea);
            gestorStock.actualizarStock(producto, -cantidad); // Actualizar stock

            // ========== LIMPIAR CAMPOS Y ACTUALIZAR INTERFAZ ==========

            limpiarCampos();
            actualizarHistorial();

            JOptionPane.showMessageDialog(null, "✅ Tarea registrada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La cantidad debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar tarea: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * ACTUALIZA EL CONTENIDO DEL HISTORIAL DE TAREAS
     * Muestra todas las tareas registradas en formato legible
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
     * CALCULA ESTADÍSTICAS DETALLADAS DE LAS TAREAS
     * @return String con estadísticas formateadas
     */
    private String calcularEstadisticas() {
        StringBuilder stats = new StringBuilder();
        stats.append("=== ESTADÍSTICAS DE TAREAS DE CAMPO ===\n\n");
        
        stats.append("Total de tareas registradas: ").append(historialTareas.size()).append("\n\n");
        
        if (!historialTareas.isEmpty()) {
            // ========== TAREAS POR PARCELA ==========
            stats.append("📊 TAREAS POR PARCELA:\n");
            java.util.Map<String, Long> tareasPorParcela = historialTareas.stream()
                .collect(java.util.stream.Collectors.groupingBy(
                    t -> t.getParcela().getNombre(), 
                    java.util.stream.Collectors.counting()
                ));
            
            tareasPorParcela.entrySet().stream()
                .sorted(java.util.Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(entry -> stats.append("  🌿 ")
                    .append(entry.getKey()).append(": ")
                    .append(entry.getValue()).append(" tareas\n"));
            
            // ========== PRODUCTOS MÁS UTILIZADOS ==========
            stats.append("\n🧪 PRODUCTOS MÁS UTILIZADOS:\n");
            java.util.Map<String, Double> productoUso = new java.util.HashMap<>();
            java.util.Map<String, Long> productoCount = new java.util.HashMap<>();
            
            for (TareaCampo tarea : historialTareas) {
                String producto = tarea.getProductoUtilizado().getNombre();
                double cantidad = tarea.getCantidadProducto();
                
                productoUso.put(producto, productoUso.getOrDefault(producto, 0.0) + cantidad);
                productoCount.put(producto, productoCount.getOrDefault(producto, 0L) + 1);
            }
            
            productoUso.entrySet().stream()
                .sorted(java.util.Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(entry -> stats.append("  💊 ")
                    .append(entry.getKey()).append(": ")
                    .append(String.format("%.2f", entry.getValue()))
                    .append(" unidades en ").append(productoCount.get(entry.getKey()))
                    .append(" tareas\n"));
            
            // ========== OPERADORES MÁS ACTIVOS ==========
            stats.append("\n👨‍🌾 OPERADORES MÁS ACTIVOS:\n");
            java.util.Map<String, Long> operadoresActivos = historialTareas.stream()
                .collect(java.util.stream.Collectors.groupingBy(
                    TareaCampo::getOperador, 
                    java.util.stream.Collectors.counting()
                ));
            
            operadoresActivos.entrySet().stream()
                .sorted(java.util.Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(entry -> stats.append("  👤 ")
                    .append(entry.getKey()).append(": ")
                    .append(entry.getValue()).append(" tareas\n"));
            
            // ========== TOTAL DE PRODUCTOS UTILIZADOS ==========
            double totalProductos = historialTareas.stream()
                .mapToDouble(TareaCampo::getCantidadProducto)
                .sum();
            stats.append("\n📦 TOTAL DE PRODUCTOS UTILIZADOS: ")
                 .append(String.format("%.2f", totalProductos))
                 .append(" unidades\n");
        }

        return stats.toString();
    }

    /**
     * LIMPIA LOS CAMPOS DEL FORMULARIO DE REGISTRO
     */
    private void limpiarCampos() {
        txtCantidad.setText("");
        txtDescripcion.setText("");
        txtOperador.setText("");
    }

    /**
     * CREA EL PANEL DE BOTONES INFERIORES CON ESTILO OSCURO
     * @param dialog Diálogo padre para cerrar
     * @return JPanel con botones configurados
     */
    private JPanel crearPanelBotones(JDialog dialog) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK); // Fondo negro
        
        // Botón cerrar con estilo oscuro
        JButton btnCerrar = crearBotonOscuro("Cerrar");
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
     * CREA UN BOTÓN CON ESTILO OSCURO Y EFECTO HOVER
     * @param texto Texto del botón
     * @return JButton configurado con estilo oscuro
     */
    private JButton crearBotonOscuro(String texto) {
        JButton boton = new JButton(texto);
        boton.setBackground(new Color(50, 50, 50)); // Gris oscuro
        boton.setForeground(Color.WHITE); // Texto blanco
        boton.setFocusPainted(false); // Sin borde de foco
        boton.setBorderPainted(false); // Sin borde
        boton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        // EFECTO HOVER - Cambia el color al pasar el mouse
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(70, 70, 70)); // Gris más claro al hover
                boton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor de mano
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(50, 50, 50)); // Volver al gris oscuro original
                boton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Cursor normal
            }
        });
        
        return boton;
    }

    // ========== MÉTODOS COMPATIBLES CON LA VERSIÓN ANTERIOR ==========

    /**
     * MÉTODO COMPATIBLE CON LA VERSIÓN ANTERIOR
     * Abre la interfaz completa de gestión de campo
     */
    public void registrarNuevaTarea() {
        mostrarInterfazCompleta();
    }

    /**
     * MÉTODO COMPATIBLE CON LA VERSIÓN ANTERIOR
     * Abre la interfaz completa de gestión de campo
     */
    public void mostrarHistorialTareas() {
        mostrarInterfazCompleta();
    }
}