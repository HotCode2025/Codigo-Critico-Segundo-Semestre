package agestion.servicios;

import agestion.modelo.Parcela;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * GESTIÓN DE PARCELAS CON INTERFAZ MEJORADA - TEMA GRIS SUAVE
 * - Fondo gris suave con letras negras
 * - Botones de distintos colores con texto negro
 * - Navegación por tecla Enter entre campos
 * - Funcionalidad completa de CRUD
 */
public class GestionParcelas {

    private ArrayList<Parcela> listaParcelas = new ArrayList<>();
    private DefaultListModel<Parcela> listModel;
    private JList<Parcela> listaParcelasJList;

    // Componentes de la interfaz principal
    private JTextField txtNombreParcela;
    private JTextField txtTipoCultivo;
    private JTextField txtSuperficie;
    private JTextField txtVariedad;
    private JTextArea areaObservaciones;
    private JComboBox<String> comboSueloTipo;
    private JTextField txtPhSuelo;
    private JTextField txtCoordenadas;

    // Referencias a botones de edición
    private JButton btnVerDetalles;
    private JButton btnEditar;
    private JButton btnEliminar;

    // Colores del tema gris suave
    private final Color COLOR_FONDO = new Color(240, 240, 240); // Gris suave
    private final Color COLOR_TEXTO = Color.BLACK;
    private final Color COLOR_BORDE = new Color(180, 180, 180);
    
    // Colores para botones
    private final Color COLOR_BOTON_ACEPTAR = new Color(144, 238, 144); // Verde claro
    private final Color COLOR_BOTON_CANCELAR = new Color(255, 182, 193); // Rosa claro
    private final Color COLOR_BOTON_LIMPIAR = new Color(255, 255, 153); // Amarillo claro
    private final Color COLOR_BOTON_DETALLES = new Color(175, 238, 238); // Cian claro
    private final Color COLOR_BOTON_EDITAR = new Color(255, 218, 185); // Naranja claro
    private final Color COLOR_BOTON_ELIMINAR = new Color(255, 153, 153); // Rojo claro
    private final Color COLOR_BOTON_NORMAL = new Color(200, 200, 200); // Gris claro

    public void mostrarInterfazCompleta() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Gestión de Parcelas - Administración de Terrenos");
        dialog.setModal(true);
        dialog.setSize(1200, 700);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout(10, 10));
        
        // FONDO GRIS SUAVE PARA EL DIÁLOGO PRINCIPAL
        dialog.getContentPane().setBackground(COLOR_FONDO);

        // Panel principal con bordes
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panelPrincipal.setBackground(COLOR_FONDO);

        // Crear y agregar los paneles
        panelPrincipal.add(crearPanelFormulario(), BorderLayout.NORTH);
        panelPrincipal.add(crearPanelListaParcelas(), BorderLayout.CENTER);
        panelPrincipal.add(crearPanelBotones(dialog), BorderLayout.SOUTH);

        dialog.add(panelPrincipal, BorderLayout.CENTER);
        dialog.setVisible(true);
    }

    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_BORDE), "📝 Datos de la Parcela"));
        panel.setBackground(COLOR_FONDO);
        ((javax.swing.border.TitledBorder) panel.getBorder()).setTitleColor(COLOR_TEXTO);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Fila 1: Nombre y Tipo de Cultivo
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(crearEtiqueta("Nombre de la parcela:"), gbc);
        
        gbc.gridx = 1;
        txtNombreParcela = crearCampoTexto(20);
        panel.add(txtNombreParcela, gbc);
        
        gbc.gridx = 2;
        panel.add(crearEtiqueta("Tipo de cultivo:"), gbc);
        
        gbc.gridx = 3;
        txtTipoCultivo = crearCampoTexto(15);
        panel.add(txtTipoCultivo, gbc);

        // Fila 2: Superficie y Variedad
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(crearEtiqueta("Superficie (hectáreas):"), gbc);
        
        gbc.gridx = 1;
        txtSuperficie = crearCampoTexto(10);
        panel.add(txtSuperficie, gbc);
        
        gbc.gridx = 2;
        panel.add(crearEtiqueta("Variedad:"), gbc);
        
        gbc.gridx = 3;
        txtVariedad = crearCampoTexto(15);
        panel.add(txtVariedad, gbc);

        // Fila 3: Tipo de Suelo y pH
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(crearEtiqueta("Tipo de suelo:"), gbc);
        
        gbc.gridx = 1;
        String[] tiposSuelo = {"Arcilloso", "Arenoso", "Franco", "Limoso", "No especificado"};
        comboSueloTipo = new JComboBox<>(tiposSuelo);
        comboSueloTipo.setBackground(Color.WHITE);
        comboSueloTipo.setForeground(COLOR_TEXTO);
        comboSueloTipo.setBorder(BorderFactory.createLineBorder(COLOR_BORDE));
        panel.add(comboSueloTipo, gbc);
        
        gbc.gridx = 2;
        panel.add(crearEtiqueta("pH del suelo:"), gbc);
        
        gbc.gridx = 3;
        txtPhSuelo = crearCampoTexto(8);
        txtPhSuelo.setText("6.5");
        panel.add(txtPhSuelo, gbc);

        // Fila 4: Coordenadas
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(crearEtiqueta("Coordenadas:"), gbc);
        
        gbc.gridx = 1; gbc.gridwidth = 3;
        txtCoordenadas = crearCampoTexto(40);
        panel.add(txtCoordenadas, gbc);
        
        gbc.gridwidth = 1; // Reset gridwidth

        // Fila 5: Observaciones
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(crearEtiqueta("Observaciones:"), gbc);
        
        gbc.gridx = 1; gbc.gridwidth = 3; gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        areaObservaciones = new JTextArea(3, 40);
        areaObservaciones.setLineWrap(true);
        areaObservaciones.setWrapStyleWord(true);
        areaObservaciones.setBackground(Color.WHITE);
        areaObservaciones.setForeground(COLOR_TEXTO);
        areaObservaciones.setCaretColor(COLOR_TEXTO);
        areaObservaciones.setBorder(BorderFactory.createLineBorder(COLOR_BORDE));
        JScrollPane scrollObservaciones = new JScrollPane(areaObservaciones);
        scrollObservaciones.getViewport().setBackground(Color.WHITE);
        scrollObservaciones.setBorder(BorderFactory.createLineBorder(COLOR_BORDE));
        panel.add(scrollObservaciones, gbc);

        // Configurar navegación con Enter
        configurarNavegacionConEnter();

        return panel;
    }

    private JPanel crearPanelListaParcelas() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_BORDE), "📋 Parcelas Registradas"));
        panel.setBackground(COLOR_FONDO);
        ((javax.swing.border.TitledBorder) panel.getBorder()).setTitleColor(COLOR_TEXTO);

        // Crear el modelo y la lista
        listModel = new DefaultListModel<>();
        listaParcelasJList = new JList<>(listModel);
        listaParcelasJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // FONDO BLANCO Y TEXTO NEGRO para la lista
        listaParcelasJList.setBackground(Color.WHITE);
        listaParcelasJList.setForeground(Color.BLACK);
        listaParcelasJList.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        // Usar un renderer personalizado para mostrar mejor la información
        listaParcelasJList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, 
                                                         boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Parcela) {
                    Parcela parcela = (Parcela) value;
                    setText(parcela.toLineaListaString());
                }
                
                // Mantener colores para la lista (blanco y negro)
                if (isSelected) {
                    setBackground(new Color(200, 200, 255)); // Azul claro para selección
                    setForeground(Color.BLACK);
                } else {
                    setBackground(Color.WHITE);
                    setForeground(Color.BLACK);
                }
                
                return c;
            }
        });

        // Listener para cuando se selecciona una parcela
        listaParcelasJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    habilitarBotonesEdicion(true);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(listaParcelasJList);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        scrollPane.getViewport().setBackground(Color.WHITE); // Fondo blanco para el viewport
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel de botones para las operaciones con la lista
        JPanel panelBotonesLista = new JPanel(new FlowLayout());
        panelBotonesLista.setBackground(COLOR_FONDO);
        
        btnVerDetalles = crearBotonConColor("👁️ Ver Detalles", COLOR_BOTON_DETALLES);
        btnVerDetalles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verDetallesParcela();
            }
        });
        
        btnEditar = crearBotonConColor("✏️ Editar", COLOR_BOTON_EDITAR);
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarParcela();
            }
        });
        
        btnEliminar = crearBotonConColor("🗑️ Eliminar", COLOR_BOTON_ELIMINAR);
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarParcela();
            }
        });
        
        // Inicialmente deshabilitados hasta que se seleccione una parcela
        habilitarBotonesEdicion(false);
        
        panelBotonesLista.add(btnVerDetalles);
        panelBotonesLista.add(btnEditar);
        panelBotonesLista.add(btnEliminar);
        
        panel.add(panelBotonesLista, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelBotones(JDialog dialog) {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(COLOR_FONDO);
        
        JButton btnAceptar = crearBotonConColor("✅ Aceptar", COLOR_BOTON_ACEPTAR);
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarParcelaDesdeInterfaz();
            }
        });
        
        JButton btnCancelar = crearBotonConColor("❌ Cancelar", COLOR_BOTON_CANCELAR);
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        
        JButton btnLimpiar = crearBotonConColor("🧹 Limpiar Campos", COLOR_BOTON_LIMPIAR);
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCamposParcela();
                txtNombreParcela.requestFocus(); // Volver al primer campo
            }
        });
        
        panel.add(btnAceptar);
        panel.add(btnCancelar);
        panel.add(btnLimpiar);
        
        return panel;
    }

    // MÉTODOS AUXILIARES PARA CREACIÓN DE COMPONENTES
    private JLabel crearEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setForeground(COLOR_TEXTO);
        etiqueta.setFont(etiqueta.getFont().deriveFont(Font.BOLD));
        return etiqueta;
    }
    
    private JTextField crearCampoTexto(int columnas) {
        JTextField campo = new JTextField(columnas);
        campo.setBackground(Color.WHITE);
        campo.setForeground(COLOR_TEXTO);
        campo.setCaretColor(COLOR_TEXTO);
        campo.setBorder(BorderFactory.createLineBorder(COLOR_BORDE));
        return campo;
    }
    
    private JButton crearBotonConColor(String texto, Color colorFondo) {
        JButton boton = new JButton(texto);
        boton.setBackground(colorFondo);
        boton.setForeground(COLOR_TEXTO);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(COLOR_BORDE));
        boton.setFont(boton.getFont().deriveFont(Font.BOLD));
        
        // Efecto hover - oscurecer el color
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(oscurecerColor(colorFondo, 20));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorFondo);
            }
        });
        
        return boton;
    }
    
    private Color oscurecerColor(Color color, int factor) {
        int rojo = Math.max(0, color.getRed() - factor);
        int verde = Math.max(0, color.getGreen() - factor);
        int azul = Math.max(0, color.getBlue() - factor);
        return new Color(rojo, verde, azul);
    }

    private void configurarNavegacionConEnter() {
        // Array de componentes en el orden de navegación
        Component[] componentes = {
            txtNombreParcela, txtTipoCultivo, txtSuperficie, txtVariedad,
            comboSueloTipo, txtPhSuelo, txtCoordenadas, areaObservaciones
        };

        for (int i = 0; i < componentes.length - 1; i++) {
            final int currentIndex = i;
            final int nextIndex = i + 1;
            
            componentes[currentIndex].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        componentes[nextIndex].requestFocus();
                        
                        // Si es un JComboBox, abrirlo automáticamente
                        if (componentes[nextIndex] instanceof JComboBox) {
                            SwingUtilities.invokeLater(() -> {
                                ((JComboBox<?>) componentes[nextIndex]).showPopup();
                            });
                        }
                        
                        // Si es un JTextArea, seleccionar todo el texto
                        if (componentes[nextIndex] instanceof JTextArea) {
                            SwingUtilities.invokeLater(() -> {
                                ((JTextArea) componentes[nextIndex]).selectAll();
                            });
                        }
                        
                        e.consume();
                    }
                }
            });
        }

        // Comportamiento especial para el último campo (área de observaciones)
        areaObservaciones.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && e.isControlDown()) {
                    // Ctrl+Enter para agregar la parcela
                    agregarParcelaDesdeInterfaz();
                    e.consume();
                }
            }
        });
    }

    // MÉTODOS DE FUNCIONALIDAD
    private void agregarParcelaDesdeInterfaz() {
        try {
            // Validaciones
            if (txtNombreParcela.getText().trim().isEmpty()) {
                mostrarError("Ingrese el nombre de la parcela");
                txtNombreParcela.requestFocus();
                return;
            }

            if (txtTipoCultivo.getText().trim().isEmpty()) {
                mostrarError("Ingrese el tipo de cultivo");
                txtTipoCultivo.requestFocus();
                return;
            }

            double superficie = Double.parseDouble(txtSuperficie.getText());
            if (superficie <= 0) {
                mostrarError("La superficie debe ser mayor a cero");
                txtSuperficie.requestFocus();
                txtSuperficie.selectAll();
                return;
            }

            // Obtener datos
            String nombre = txtNombreParcela.getText().trim();
            String tipoCultivo = txtTipoCultivo.getText().trim();
            String variedad = txtVariedad.getText().trim();
            String sueloTipo = comboSueloTipo.getSelectedItem().toString();
            double phSuelo = Double.parseDouble(txtPhSuelo.getText());
            String coordenadas = txtCoordenadas.getText().trim();
            String observaciones = areaObservaciones.getText().trim();

            // Crear parcela
            int id = listaParcelas.size() + 1;
            Parcela nuevaParcela = new Parcela(id, nombre, superficie, tipoCultivo, variedad, 
                    java.time.LocalDate.now(), java.time.LocalDate.now().plusMonths(6), 
                    "Activa", sueloTipo, phSuelo, coordenadas, observaciones);
            
            listaParcelas.add(nuevaParcela);
            listModel.addElement(nuevaParcela);

            // Limpiar campos y volver al inicio
            limpiarCamposParcela();
            txtNombreParcela.requestFocus();
            
            mostrarExito("✅ Parcela '" + nombre + "' agregada correctamente");

        } catch (NumberFormatException e) {
            mostrarError("La superficie y el pH deben ser números válidos");
            txtSuperficie.requestFocus();
            txtSuperficie.selectAll();
        } catch (Exception e) {
            mostrarError("Error al agregar parcela: " + e.getMessage());
        }
    }

    private void verDetallesParcela() {
        Parcela parcelaSeleccionada = listaParcelasJList.getSelectedValue();
        if (parcelaSeleccionada != null) {
            JDialog dialogDetalles = new JDialog();
            dialogDetalles.setTitle("Detalles de Parcela: " + parcelaSeleccionada.getNombre());
            dialogDetalles.setModal(true);
            dialogDetalles.setSize(600, 500);
            dialogDetalles.setLocationRelativeTo(null);
            dialogDetalles.getContentPane().setBackground(COLOR_FONDO);
            
            JTextArea areaDetalles = new JTextArea();
            areaDetalles.setText(parcelaSeleccionada.toFichaTecnicaString());
            areaDetalles.setEditable(false);
            areaDetalles.setBackground(Color.WHITE);
            areaDetalles.setForeground(COLOR_TEXTO);
            areaDetalles.setCaretColor(COLOR_TEXTO);
            areaDetalles.setFont(new Font("Monospaced", Font.PLAIN, 12));
            areaDetalles.setCaretPosition(0);
            
            JScrollPane scrollPane = new JScrollPane(areaDetalles);
            scrollPane.getViewport().setBackground(Color.WHITE);
            scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_BORDE));
            
            JButton btnCerrar = crearBotonConColor("Cerrar", COLOR_BOTON_NORMAL);
            btnCerrar.addActionListener(e -> dialogDetalles.dispose());
            
            JPanel panelBoton = new JPanel();
            panelBoton.setBackground(COLOR_FONDO);
            panelBoton.add(btnCerrar);
            
            dialogDetalles.add(scrollPane, BorderLayout.CENTER);
            dialogDetalles.add(panelBoton, BorderLayout.SOUTH);
            dialogDetalles.setVisible(true);
        }
    }

    private void editarParcela() {
        Parcela parcelaSeleccionada = listaParcelasJList.getSelectedValue();
        if (parcelaSeleccionada != null) {
            // Crear diálogo de edición con tema gris suave
            JDialog dialogEditar = new JDialog();
            dialogEditar.setTitle("Editar Parcela: " + parcelaSeleccionada.getNombre());
            dialogEditar.setModal(true);
            dialogEditar.setSize(500, 400);
            dialogEditar.setLocationRelativeTo(null);
            dialogEditar.setLayout(new BorderLayout(10, 10));
            dialogEditar.getContentPane().setBackground(COLOR_FONDO);

            JPanel panelFormulario = new JPanel(new GridLayout(0, 2, 10, 10));
            panelFormulario.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            panelFormulario.setBackground(COLOR_FONDO);

            // Campos de edición con los valores actuales
            JTextField txtEditNombre = crearCampoTexto(20);
            txtEditNombre.setText(parcelaSeleccionada.getNombre());
            
            JTextField txtEditCultivo = crearCampoTexto(20);
            txtEditCultivo.setText(parcelaSeleccionada.getTipoCultivo());
            
            JTextField txtEditSuperficie = crearCampoTexto(10);
            txtEditSuperficie.setText(String.valueOf(parcelaSeleccionada.getSuperficie()));
            
            JTextField txtEditVariedad = crearCampoTexto(20);
            txtEditVariedad.setText(parcelaSeleccionada.getVariedad());
            
            JComboBox<String> comboEditSuelo = new JComboBox<>(new String[]{"Arcilloso", "Arenoso", "Franco", "Limoso", "No especificado"});
            comboEditSuelo.setSelectedItem(parcelaSeleccionada.getSueloTipo());
            comboEditSuelo.setBackground(Color.WHITE);
            comboEditSuelo.setForeground(COLOR_TEXTO);
            comboEditSuelo.setBorder(BorderFactory.createLineBorder(COLOR_BORDE));
            
            JTextField txtEditPh = crearCampoTexto(10);
            txtEditPh.setText(String.valueOf(parcelaSeleccionada.getPhSuelo()));
            
            JTextField txtEditCoordenadas = crearCampoTexto(30);
            txtEditCoordenadas.setText(parcelaSeleccionada.getCoordenadas());
            
            JTextArea areaEditObservaciones = new JTextArea(3, 30);
            areaEditObservaciones.setText(parcelaSeleccionada.getObservaciones());
            areaEditObservaciones.setLineWrap(true);
            areaEditObservaciones.setWrapStyleWord(true);
            areaEditObservaciones.setBackground(Color.WHITE);
            areaEditObservaciones.setForeground(COLOR_TEXTO);
            areaEditObservaciones.setCaretColor(COLOR_TEXTO);
            areaEditObservaciones.setBorder(BorderFactory.createLineBorder(COLOR_BORDE));

            // Agregar componentes al formulario
            panelFormulario.add(crearEtiqueta("Nombre:"));
            panelFormulario.add(txtEditNombre);
            panelFormulario.add(crearEtiqueta("Tipo Cultivo:"));
            panelFormulario.add(txtEditCultivo);
            panelFormulario.add(crearEtiqueta("Superficie (ha):"));
            panelFormulario.add(txtEditSuperficie);
            panelFormulario.add(crearEtiqueta("Variedad:"));
            panelFormulario.add(txtEditVariedad);
            panelFormulario.add(crearEtiqueta("Tipo Suelo:"));
            panelFormulario.add(comboEditSuelo);
            panelFormulario.add(crearEtiqueta("pH Suelo:"));
            panelFormulario.add(txtEditPh);
            panelFormulario.add(crearEtiqueta("Coordenadas:"));
            panelFormulario.add(txtEditCoordenadas);
            panelFormulario.add(crearEtiqueta("Observaciones:"));
            panelFormulario.add(new JScrollPane(areaEditObservaciones));

            // Panel de botones
            JPanel panelBotonesEditar = new JPanel(new FlowLayout());
            panelBotonesEditar.setBackground(COLOR_FONDO);
            
            JButton btnGuardar = crearBotonConColor("💾 Guardar Cambios", COLOR_BOTON_ACEPTAR);
            btnGuardar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        // Validar y actualizar
                        if (txtEditNombre.getText().trim().isEmpty()) {
                            mostrarError("El nombre no puede estar vacío");
                            return;
                        }
                        
                        double nuevaSuperficie = Double.parseDouble(txtEditSuperficie.getText());
                        if (nuevaSuperficie <= 0) {
                            mostrarError("La superficie debe ser mayor a cero");
                            return;
                        }
                        
                        double nuevoPh = Double.parseDouble(txtEditPh.getText());
                        
                        // Actualizar la parcela
                        parcelaSeleccionada.setNombre(txtEditNombre.getText().trim());
                        parcelaSeleccionada.setTipoCultivo(txtEditCultivo.getText().trim());
                        parcelaSeleccionada.setSuperficie(nuevaSuperficie);
                        parcelaSeleccionada.setVariedad(txtEditVariedad.getText().trim());
                        parcelaSeleccionada.setSueloTipo(comboEditSuelo.getSelectedItem().toString());
                        parcelaSeleccionada.setPhSuelo(nuevoPh);
                        parcelaSeleccionada.setCoordenadas(txtEditCoordenadas.getText().trim());
                        parcelaSeleccionada.setObservaciones(areaEditObservaciones.getText().trim());
                        
                        // Actualizar la lista visual
                        listModel.setElementAt(parcelaSeleccionada, listaParcelasJList.getSelectedIndex());
                        
                        mostrarExito("Parcela actualizada correctamente");
                        dialogEditar.dispose();
                        
                    } catch (NumberFormatException ex) {
                        mostrarError("La superficie y el pH deben ser números válidos");
                    }
                }
            });
            
            JButton btnCancelar = crearBotonConColor("❌ Cancelar", COLOR_BOTON_CANCELAR);
            btnCancelar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialogEditar.dispose();
                }
            });
            
            panelBotonesEditar.add(btnGuardar);
            panelBotonesEditar.add(btnCancelar);

            dialogEditar.add(panelFormulario, BorderLayout.CENTER);
            dialogEditar.add(panelBotonesEditar, BorderLayout.SOUTH);
            dialogEditar.setVisible(true);
        }
    }

    private void eliminarParcela() {
        Parcela parcelaSeleccionada = listaParcelasJList.getSelectedValue();
        if (parcelaSeleccionada != null) {
            int confirmacion = JOptionPane.showConfirmDialog(null, 
                "¿Está seguro de eliminar la parcela '" + parcelaSeleccionada.getNombre() + "'?\nEsta acción no se puede deshacer.",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
            
            if (confirmacion == JOptionPane.YES_OPTION) {
                int indice = listaParcelasJList.getSelectedIndex();
                listaParcelas.remove(parcelaSeleccionada);
                listModel.remove(indice);
                habilitarBotonesEdicion(false);
                
                mostrarExito("Parcela eliminada correctamente");
            }
        }
    }

    private void habilitarBotonesEdicion(boolean habilitar) {
        btnVerDetalles.setEnabled(habilitar);
        btnEditar.setEnabled(habilitar);
        btnEliminar.setEnabled(habilitar);
    }

    private void limpiarCamposParcela() {
        txtNombreParcela.setText("");
        txtTipoCultivo.setText("");
        txtSuperficie.setText("");
        txtVariedad.setText("");
        comboSueloTipo.setSelectedIndex(4); // "No especificado"
        txtPhSuelo.setText("6.5");
        txtCoordenadas.setText("");
        areaObservaciones.setText("");
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void mostrarExito(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    // MÉTODOS COMPATIBLES CON VERSIÓN ANTERIOR
    public void agregarParcela() {
        mostrarInterfazCompleta();
    }

    public void mostrarParcelas() {
        mostrarInterfazCompleta();
    }

    public ArrayList<Parcela> getListaParcelas() {
        return this.listaParcelas;
    }
}