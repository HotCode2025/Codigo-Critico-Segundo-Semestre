package agestion.servicios;

import agestion.modelo.Maquinaria;
import agestion.modelo.Parcela;
import agestion.modelo.ProductoAgricola;
import agestion.modelo.TareaCampo;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;

/**
 * GESTIÓN DE CAMPO CON INTERFAZ MEJORADA Y ORGANIZADA
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
    private JSpinner spinnerCantidad;
    private JTextArea txtDescripcion;
    private JTextField txtOperador;
    private JTextArea areaHistorial;
    private JButton btnGestionParcelas;
    private JButton btnGestionProductos; // NUEVO: Botón para gestión de productos
    private JButton btnGestionMaquinarias; // NUEVO: Botón para gestión de maquinarias

    public GestionCampo(GestionParcelas gestorParcelas, GestionStock gestorStock, GestionMaquinaria gestorMaquinaria) {
        this.gestorParcelas = gestorParcelas;
        this.gestorStock = gestorStock;
        this.gestorMaquinaria = gestorMaquinaria;
    }

    public void mostrarInterfazCompleta() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Cuaderno de Campo - Gestión de Tareas Agrícolas");
        dialog.setModal(true);
        // MODIFICADO: Cambiar tamaño a 600x600 píxeles
        dialog.setSize(600, 600);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());
        // MODIFICADO: Cambiar color de fondo a GRIS SUAVE
        dialog.getContentPane().setBackground(new Color(240, 240, 240));

        JTabbedPane tabbedPane = new JTabbedPane();
        // MODIFICADO: Cambiar color de fondo a GRIS SUAVE
        tabbedPane.setBackground(new Color(240, 240, 240));
        tabbedPane.setForeground(Color.BLACK);
        
        tabbedPane.addTab("📝 Registrar Tarea", crearPanelRegistroTarea());
        tabbedPane.addTab("📋 Historial Tareas", crearPanelHistorial());
        tabbedPane.addTab("📈 Estadísticas", crearPanelEstadisticas());

        dialog.add(tabbedPane, BorderLayout.CENTER);
        dialog.add(crearPanelBotones(dialog), BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private JPanel crearPanelRegistroTarea() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // MODIFICADO: Cambiar color de fondo a GRIS SUAVE
        panel.setBackground(new Color(240, 240, 240));

        // Panel principal con scroll para formulario largo
        JPanel formPanel = new JPanel(new GridBagLayout());
        // MODIFICADO: Cambiar color de fondo a GRIS SUAVE
        formPanel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        inicializarComponentes();

        // ========== FILA 1: PARCELA ==========
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        JLabel lblParcela = crearLabel("Parcela:");
        formPanel.add(lblParcela, gbc);

        gbc.gridx = 1; gbc.weightx = 0.6;
        formPanel.add(comboParcelas, gbc);

        gbc.gridx = 2; gbc.weightx = 0.1;
        // MODIFICADO: Cambiar botón "Agregar Tarea" a AZUL
        btnGestionParcelas = crearBotonColor("➕", Color.BLUE);
        btnGestionParcelas.setToolTipText("Gestionar Parcelas");
        btnGestionParcelas.addActionListener(e -> abrirGestionParcelas());
        formPanel.add(btnGestionParcelas, gbc);

        // ========== FILA 2: PRODUCTO ==========
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.3;
        JLabel lblProducto = crearLabel("Producto:");
        formPanel.add(lblProducto, gbc);

        gbc.gridx = 1; gbc.weightx = 0.6;
        formPanel.add(comboProductos, gbc);

        // NUEVO: Botón de lupa para gestión de productos
        gbc.gridx = 2; gbc.weightx = 0.1;
        btnGestionProductos = crearBotonLupa();
        btnGestionProductos.setToolTipText("Gestionar Productos");
        btnGestionProductos.addActionListener(e -> abrirGestionStock());
        formPanel.add(btnGestionProductos, gbc);

        // ========== FILA 3: MAQUINARIA ==========
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.3;
        JLabel lblMaquinaria = crearLabel("Maquinaria:");
        formPanel.add(lblMaquinaria, gbc);

        gbc.gridx = 1; gbc.weightx = 0.6;
        formPanel.add(comboMaquinarias, gbc);

        // NUEVO: Botón de lupa para gestión de maquinarias
        gbc.gridx = 2; gbc.weightx = 0.1;
        btnGestionMaquinarias = crearBotonLupa();
        btnGestionMaquinarias.setToolTipText("Gestionar Maquinarias");
        btnGestionMaquinarias.addActionListener(e -> abrirGestionMaquinaria());
        formPanel.add(btnGestionMaquinarias, gbc);

        // ========== FILA 4: CANTIDAD ==========
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0.3;
        JLabel lblCantidad = crearLabel("Cantidad (kg/l):");
        formPanel.add(lblCantidad, gbc);

        gbc.gridx = 1; gbc.gridwidth = 2; gbc.weightx = 0.7;
        formPanel.add(spinnerCantidad, gbc);
        gbc.gridwidth = 1;

        // ========== FILA 5: OPERADOR ==========
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0.3;
        JLabel lblOperador = crearLabel("Operador:");
        formPanel.add(lblOperador, gbc);

        gbc.gridx = 1; gbc.gridwidth = 2; gbc.weightx = 0.7;
        formPanel.add(txtOperador, gbc);
        gbc.gridwidth = 1;

        // ========== FILA 6: DESCRIPCIÓN ==========
        gbc.gridx = 0; gbc.gridy = 5; gbc.weightx = 0.3;
        JLabel lblDescripcion = crearLabel("Descripción:");
        formPanel.add(lblDescripcion, gbc);

        gbc.gridx = 1; gbc.gridy = 5; gbc.gridwidth = 2; 
        gbc.weightx = 0.7; gbc.weighty = 0.4;
        gbc.fill = GridBagConstraints.BOTH;
        JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
        scrollDescripcion.setPreferredSize(new Dimension(300, 80));
        formPanel.add(scrollDescripcion, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;

        // ========== BOTÓN REGISTRAR ==========
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 3;
        gbc.weightx = 1.0; gbc.insets = new Insets(10, 5, 5, 5);
        // MODIFICADO: Cambiar botón "Aceptar" a VERDE
        JButton btnRegistrar = crearBotonColor("✅ Registrar Tarea de Campo", Color.GREEN);
        btnRegistrar.addActionListener(e -> registrarTareaDesdeInterfaz());
        formPanel.add(btnRegistrar, gbc);

        JScrollPane scrollForm = new JScrollPane(formPanel);
        // MODIFICADO: Cambiar color de fondo a GRIS SUAVE
        scrollForm.getViewport().setBackground(new Color(240, 240, 240));
        scrollForm.setBorder(null);
        
        panel.add(scrollForm, BorderLayout.CENTER);
        return panel;
    }

    private void inicializarComponentes() {
        // Combo de parcelas
        comboParcelas = new JComboBox<>();
        actualizarComboParcelas();
        comboParcelas.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Parcela) {
                    Parcela p = (Parcela) value;
                    setText(p.toLineaListaString());
                }
                return c;
            }
        });

        // Combo de productos
        comboProductos = new JComboBox<>();
        actualizarComboProductos();

        // Combo de maquinarias
        comboMaquinarias = new JComboBox<>();
        actualizarComboMaquinarias();

        // Spinner para cantidad con valores decimales
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0.0, 0.0, 10000.0, 0.5);
        spinnerCantidad = new JSpinner(spinnerModel);
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spinnerCantidad, "0.0##");
        spinnerCantidad.setEditor(editor);

        // Campo de operador
        txtOperador = new JTextField();
        
        // Área de texto para descripción
        txtDescripcion = new JTextArea(4, 20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        
        // MODIFICADO: Cambiar estilo de componentes para fondo gris suave
        aplicarEstiloGris(comboParcelas);
        aplicarEstiloGris(comboProductos);
        aplicarEstiloGris(comboMaquinarias);
        aplicarEstiloGris(spinnerCantidad);
        aplicarEstiloGris(txtOperador);
        aplicarEstiloGris(txtDescripcion);
    }

    // NUEVO: Método para crear botón con imagen de lupa
    private JButton crearBotonLupa() {
        try {
            // Cargar la imagen de lupa desde el paquete de imágenes
            ImageIcon iconoLupa = new ImageIcon(getClass().getResource("/agestion/images/lupa.jpg"));
            // Escalar la imagen si es necesario
            Image imagenEscalada = iconoLupa.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
            iconoLupa = new ImageIcon(imagenEscalada);
            
            JButton boton = new JButton(iconoLupa);
            boton.setBackground(new Color(70, 130, 180)); // Color azul acero
            boton.setForeground(Color.WHITE);
            boton.setFocusPainted(false);
            boton.setBorderPainted(false);
            boton.setPreferredSize(new Dimension(30, 25));
            
            boton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    boton.setBackground(new Color(100, 149, 237)); // Azul más claro
                    boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    boton.setBackground(new Color(70, 130, 180)); // Volver al azul acero
                    boton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            });
            
            return boton;
        } catch (Exception e) {
            // Si no se encuentra la imagen, crear un botón con texto
            System.err.println("Error al cargar la imagen lupa.jpg: " + e.getMessage());
            JButton boton = new JButton("🔍");
            boton.setBackground(new Color(70, 130, 180));
            boton.setForeground(Color.WHITE);
            boton.setFocusPainted(false);
            boton.setBorderPainted(false);
            return boton;
        }
    }

    // NUEVO: Método para actualizar combo de productos
    private void actualizarComboProductos() {
        comboProductos.removeAllItems();
        for (ProductoAgricola producto : gestorStock.getInventario()) {
            comboProductos.addItem(producto);
        }
    }

    // NUEVO: Método para actualizar combo de maquinarias
    private void actualizarComboMaquinarias() {
        comboMaquinarias.removeAllItems();
        for (Maquinaria maquinaria : gestorMaquinaria.getFlota()) {
            comboMaquinarias.addItem(maquinaria);
        }
    }

    // NUEVO: Método para abrir gestión de stock
    private void abrirGestionStock() {
        gestorStock.mostrarInterfazCompleta();
        // Actualizar el combo después de cerrar la gestión de stock
        actualizarComboProductos();
    }

    // NUEVO: Método para abrir gestión de maquinaria
    private void abrirGestionMaquinaria() {
        gestorMaquinaria.mostrarInterfazCompleta();
        // Actualizar el combo después de cerrar la gestión de maquinaria
        actualizarComboMaquinarias();
    }

    // MODIFICADO: Nuevo método para aplicar estilo con fondo gris suave
    private void aplicarEstiloGris(JComponent componente) {
        componente.setBackground(Color.WHITE);
        componente.setForeground(Color.BLACK);
        componente.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        // Aplicar setCaretColor solo a componentes de texto específicos
        if (componente instanceof JTextField) {
            ((JTextField) componente).setCaretColor(Color.BLACK);
        } else if (componente instanceof JTextArea) {
            ((JTextArea) componente).setCaretColor(Color.BLACK);
        }
        
        // Manejo especial para JSpinner
        if (componente instanceof JSpinner) {
            JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) ((JSpinner) componente).getEditor();
            editor.getTextField().setBackground(Color.WHITE);
            editor.getTextField().setForeground(Color.BLACK);
            editor.getTextField().setCaretColor(Color.BLACK);
        }
    }

    private void actualizarComboParcelas() {
        comboParcelas.removeAllItems();
        ArrayList<Parcela> parcelas = gestorParcelas.getListaParcelas();
        if (parcelas != null) {
            for (Parcela parcela : parcelas) {
                comboParcelas.addItem(parcela);
            }
        }
        
        // Si no hay parcelas, mostrar mensaje especial
        if (comboParcelas.getItemCount() == 0) {
            // Crear una parcela ficticia para mostrar el mensaje
            Parcela parcelaFicticia = new Parcela(-1, "⚠️ NO HAY PARCELAS - CLICK EN '+' PARA AGREGAR", 0, "");
            comboParcelas.addItem(parcelaFicticia);
        }
    }

    private void abrirGestionParcelas() {
        gestorParcelas.mostrarInterfazCompleta();
        // Actualizar el combo después de cerrar la gestión de parcelas
        actualizarComboParcelas();
    }

    private void registrarTareaDesdeInterfaz() {
        try {
            // Validar que hay parcelas disponibles
            if (comboParcelas.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, 
                    "No hay parcelas disponibles. Por favor, agregue parcelas primero.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                abrirGestionParcelas();
                return;
            }

            Parcela parcelaSeleccionada = (Parcela) comboParcelas.getSelectedItem();
            if (parcelaSeleccionada.getId() == -1) {
                JOptionPane.showMessageDialog(null, 
                    "No hay parcelas disponibles. Por favor, agregue parcelas primero.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                abrirGestionParcelas();
                return;
            }

            // Validaciones básicas
            if (comboProductos.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Seleccione un producto", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double cantidad = (Double) spinnerCantidad.getValue();
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor a cero", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener datos
            Parcela parcela = (Parcela) comboParcelas.getSelectedItem();
            ProductoAgricola producto = (ProductoAgricola) comboProductos.getSelectedItem();
            Maquinaria maquinaria = (Maquinaria) comboMaquinarias.getSelectedItem();
            String descripcion = txtDescripcion.getText().isEmpty() ? "Tarea agrícola realizada" : txtDescripcion.getText();
            String operador = txtOperador.getText().isEmpty() ? "Operador no especificado" : txtOperador.getText();

            // Verificar stock
            if (cantidad > producto.getCantidadEnStock()) {
                JOptionPane.showMessageDialog(null, 
                    String.format("Stock insuficiente. Disponible: %.2f, Solicitado: %.2f", 
                                producto.getCantidadEnStock(), cantidad), 
                    "Error de Stock", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear y registrar tarea
            int nuevaId = historialTareas.size() + 1;
            TareaCampo nuevaTarea = new TareaCampo(nuevaId, LocalDate.now(), descripcion, operador, 
                                                  parcela, producto, maquinaria, cantidad);
            
            historialTareas.add(nuevaTarea);
            gestorStock.actualizarStock(producto, -cantidad);

            // Limpiar y actualizar
            limpiarCampos();
            actualizarHistorial();

            JOptionPane.showMessageDialog(null, 
                "✅ Tarea registrada exitosamente en " + parcela.getNombre(), 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                "Error al registrar tarea: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private JPanel crearPanelHistorial() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // MODIFICADO: Cambiar color de fondo a GRIS SUAVE
        panel.setBackground(new Color(240, 240, 240));

        areaHistorial = new JTextArea(20, 50);
        areaHistorial.setEditable(false);
        areaHistorial.setFont(new Font("Monospaced", Font.PLAIN, 12));
        // MODIFICADO: Cambiar colores para fondo gris suave
        areaHistorial.setBackground(Color.WHITE);
        areaHistorial.setForeground(Color.BLACK);
        areaHistorial.setCaretColor(Color.BLACK);
        
        JScrollPane scrollPane = new JScrollPane(areaHistorial);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton btnActualizar = crearBotonColor("🔄 Actualizar Historial", new Color(50, 50, 50));
        btnActualizar.addActionListener(e -> actualizarHistorial());
        
        JPanel panelBoton = new JPanel();
        // MODIFICADO: Cambiar color de fondo a GRIS SUAVE
        panelBoton.setBackground(new Color(240, 240, 240));
        panelBoton.add(btnActualizar);
        panel.add(panelBoton, BorderLayout.SOUTH);

        actualizarHistorial();
        return panel;
    }

    private JPanel crearPanelEstadisticas() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // MODIFICADO: Cambiar color de fondo a GRIS SUAVE
        panel.setBackground(new Color(240, 240, 240));

        JTextArea areaEstadisticas = new JTextArea();
        areaEstadisticas.setEditable(false);
        areaEstadisticas.setFont(new Font("Monospaced", Font.PLAIN, 12));
        // MODIFICADO: Cambiar colores para fondo gris suave
        areaEstadisticas.setBackground(Color.WHITE);
        areaEstadisticas.setForeground(Color.BLACK);
        areaEstadisticas.setCaretColor(Color.BLACK);
        
        areaEstadisticas.setText(calcularEstadisticas());
        panel.add(new JScrollPane(areaEstadisticas), BorderLayout.CENTER);

        return panel;
    }

    private void actualizarHistorial() {
        if (historialTareas.isEmpty()) {
            areaHistorial.setText("=== CUADERNO DE CAMPO ===\n\nNo hay tareas registradas.");
            return;
        }

        StringBuilder historial = new StringBuilder();
        historial.append("=== CUADERNO DE CAMPO - HISTORIAL COMPLETO ===\n\n");
        
        for (TareaCampo tarea : historialTareas) {
            historial.append("📅 Fecha: ").append(tarea.getFecha()).append("\n");
            historial.append("🌿 Parcela: ").append(tarea.getParcela().getNombre()).append("\n");
            historial.append("👤 Operador: ").append(tarea.getOperador()).append("\n");
            historial.append("🧪 Producto: ").append(tarea.getProductoUtilizado().getNombre())
                     .append(" (").append(tarea.getCantidadProducto()).append(")\n");
            historial.append("🚜 Maquinaria: ").append(tarea.getMaquinariaUtilizada().getNombre()).append("\n");
            historial.append("📝 Descripción: ").append(tarea.getDescripcion()).append("\n");
            historial.append("─".repeat(50)).append("\n");
        }

        areaHistorial.setText(historial.toString());
    }

    private String calcularEstadisticas() {
        StringBuilder stats = new StringBuilder();
        stats.append("=== ESTADÍSTICAS DE TAREAS DE CAMPO ===\n\n");
        
        stats.append("Total de tareas registradas: ").append(historialTareas.size()).append("\n\n");
        
        if (!historialTareas.isEmpty()) {
            // Estadísticas por parcela
            stats.append("📊 TAREAS POR PARCELA:\n");
            java.util.Map<String, Integer> tareasPorParcela = new java.util.HashMap<>();
            for (TareaCampo tarea : historialTareas) {
                String nombreParcela = tarea.getParcela().getNombre();
                tareasPorParcela.put(nombreParcela, tareasPorParcela.getOrDefault(nombreParcela, 0) + 1);
            }
            
            // Ordenar por cantidad de tareas (descendente)
            tareasPorParcela.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .forEach(entry -> stats.append("  🌿 ")
                    .append(entry.getKey()).append(": ")
                    .append(entry.getValue()).append(" tareas\n"));
            
            // Total de productos utilizados
            double totalProductos = 0;
            for (TareaCampo tarea : historialTareas) {
                totalProductos += tarea.getCantidadProducto();
            }
            stats.append("\n📦 TOTAL DE PRODUCTOS UTILIZADOS: ")
                 .append(String.format("%.2f", totalProductos))
                 .append(" unidades\n");
        }

        return stats.toString();
    }

    private void limpiarCampos() {
        spinnerCantidad.setValue(0.0);
        txtDescripcion.setText("");
        txtOperador.setText("");
    }

    private JPanel crearPanelBotones(JDialog dialog) {
        JPanel panel = new JPanel();
        // MODIFICADO: Cambiar color de fondo a GRIS SUAVE
        panel.setBackground(new Color(240, 240, 240));
        
        // MODIFICADO: Cambiar botón "Cancelar" a ROJO
        JButton btnCerrar = crearBotonColor("Cerrar", Color.RED);
        btnCerrar.addActionListener(e -> dialog.dispose());
        
        panel.add(btnCerrar);
        return panel;
    }

    // MODIFICADO: Nuevo método para crear botones con colores específicos
    private JButton crearBotonColor(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setBackground(color);
        
        // Determinar color de texto basado en el color de fondo para mejor contraste
        if (color.equals(Color.GREEN) || color.equals(new Color(240, 240, 240))) {
            boton.setForeground(Color.BLACK);
        } else {
            boton.setForeground(Color.WHITE);
        }
        
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Efecto hover - aclarar el color
                boton.setBackground(color.brighter());
                boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(color);
                boton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        
        return boton;
    }

    private JLabel crearLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setForeground(Color.BLACK); // MODIFICADO: Texto negro para mejor contraste con gris suave
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        return label;
    }

    // Métodos compatibles
    public void registrarNuevaTarea() {
        mostrarInterfazCompleta();
    }

    public void mostrarHistorialTareas() {
        mostrarInterfazCompleta();
    }
}