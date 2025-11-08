package agestion.servicios;

import agestion.modelo.Parcela;
import agestion.modelo.PlanDeFertilizacion;
import agestion.modelo.PlanDeRiego;
import agestion.modelo.ProductoAgricola;
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
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JSplitPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * GESTIÓN DE RIEGO Y FERTILIZACIÓN CON INTERFAZ DE SOLAPAS
 * 
 * Sistema completo para planificación y seguimiento de
 * riego y fertilización con interfaz organizada en pestañas.
 * Incluye selección individual de planes para marcarlos como
 * completados o cancelados.
 * 
 * @author Código Crítico 2025
 * @version 2.1 - Con selección de planes
 */
public class GestionRiegoFertilizacion {

    // =========================================================================
    // ATRIBUTOS PRINCIPALES
    // =========================================================================
    
    /** Lista que almacena todos los planes de riego creados */
    private ArrayList<PlanDeRiego> planesDeRiego = new ArrayList<>();
    
    /** Lista que almacena todos los planes de fertilización creados */
    private ArrayList<PlanDeFertilizacion> planesDeFertilizacion = new ArrayList<>();
    
    // Dependencias de otros gestores del sistema
    private GestionParcelas gestorParcelas;
    private GestionStock gestorStock;

    // =========================================================================
    // COMPONENTES DE INTERFAZ - RIEGO
    // =========================================================================
    
    /** ComboBox para seleccionar parcela en planes de riego */
    private JComboBox<Parcela> comboParcelasRiego;
    /** Campo de texto para ingresar frecuencia de riego en días */
    private JTextField txtFrecuenciaRiego;
    /** Campo de texto para ingresar duración de riego en horas */
    private JTextField txtDuracionRiego;
    /** Área de texto para mostrar detalles del plan de riego seleccionado */
    private JTextArea areaPlanesRiego;
    
    // Componentes para lista seleccionable de planes de riego
    private JList<PlanDeRiego> listaPlanesRiego;
    private DefaultListModel<PlanDeRiego> modeloListaRiego;

    // =========================================================================
    // COMPONENTES DE INTERFAZ - FERTILIZACIÓN
    // =========================================================================
    
    /** ComboBox para seleccionar parcela en planes de fertilización */
    private JComboBox<Parcela> comboParcelasFert;
    /** ComboBox para seleccionar producto fertilizante */
    private JComboBox<ProductoAgricola> comboFertilizantes;
    /** Campo de texto para ingresar fecha de aplicación */
    private JTextField txtFechaAplicacion;
    /** Campo de texto para ingresar dosis en kg por hectárea */
    private JTextField txtDosisFertilizacion;
    /** Área de texto para mostrar detalles del plan de fertilización seleccionado */
    private JTextArea areaPlanesFertilizacion;
    
    // Componentes para lista seleccionable de planes de fertilización
    private JList<PlanDeFertilizacion> listaPlanesFertilizacion;
    private DefaultListModel<PlanDeFertilizacion> modeloListaFertilizacion;

    // =========================================================================
    // CONSTRUCTOR
    // =========================================================================
    
    /**
     * Constructor principal que recibe las dependencias necesarias
     * @param gestorParcelas Gestor de parcelas para obtener listado de parcelas
     * @param gestorStock Gestor de stock para obtener listado de fertilizantes
     */
    public GestionRiegoFertilizacion(GestionParcelas gestorParcelas, GestionStock gestorStock) {
        this.gestorParcelas = gestorParcelas;
        this.gestorStock = gestorStock;
    }

    // =========================================================================
    // MÉTODOS PRINCIPALES DE INTERFAZ
    // =========================================================================

    /**
     * MUESTRA LA INTERFAZ COMPLETA CON LAS CUATRO SOLAPAS PRINCIPALES
     * Crea un diálogo modal con pestañas para organizar las funcionalidades
     */
    public void mostrarInterfazCompleta() {
        // Crear diálogo principal
        JDialog dialog = new JDialog();
        dialog.setTitle("Gestión de Riego y Fertilización v2.1 - Con Selección");
        dialog.setModal(true); // Hacerlo modal para bloquear otras ventanas
        dialog.setSize(700, 650); // Tamaño optimizado para la nueva interfaz
        dialog.setLocationRelativeTo(null); // Centrar en pantalla
        dialog.setLayout(new BorderLayout());

        // Crear panel de pestañas (solapas)
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Agregar las cuatro solapas principales
        tabbedPane.addTab("💧 Planes de Riego", crearPanelRiego());
        tabbedPane.addTab("🌱 Planes de Fertilización", crearPanelFertilizacion());
        tabbedPane.addTab("📅 Calendario Integrado", crearPanelCalendario());
        tabbedPane.addTab("📊 Métricas de Recursos", crearPanelMetricas());

        // Agregar panel de pestañas al diálogo
        dialog.add(tabbedPane, BorderLayout.CENTER);
        
        // Agregar botones inferiores (cerrar)
        JPanel panelBotones = crearPanelBotones(dialog);
        dialog.add(panelBotones, BorderLayout.SOUTH);

        // Mostrar la interfaz
        dialog.setVisible(true);
    }

    // =========================================================================
    // SOLAPA 1: PLANES DE RIEGO
    // =========================================================================

    /**
     * CREA EL PANEL COMPLETO DE PLANES DE RIEGO
     * Incluye formulario de creación, lista seleccionable y controles
     * @return JPanel con todos los componentes de riego
     */
    private JPanel crearPanelRiego() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ==================== PANEL SUPERIOR - FORMULARIO ====================
        JPanel panelFormulario = new JPanel(new GridLayout(0, 2, 5, 5));
        
        // Inicializar componentes de riego
        inicializarComponentesRiego();

        // Agregar componentes al formulario
        panelFormulario.add(new JLabel("Parcela a regar:"));
        panelFormulario.add(comboParcelasRiego);
        panelFormulario.add(new JLabel("Frecuencia (días):"));
        panelFormulario.add(txtFrecuenciaRiego);
        panelFormulario.add(new JLabel("Duración (horas):"));
        panelFormulario.add(txtDuracionRiego);

        // Botón para crear nuevo plan de riego
        JButton btnCrearPlan = new JButton("💧 Crear Plan de Riego");
        btnCrearPlan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearPlanRiegoDesdeInterfaz();
            }
        });
        panelFormulario.add(new JLabel()); // Espacio vacío para alineación
        panelFormulario.add(btnCrearPlan);

        panel.add(panelFormulario, BorderLayout.NORTH);

        // ============ PANEL CENTRAL - LISTA Y DETALLES DIVIDIDOS =============
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(300); // Dividir a 300px de la izquierda
        
        // ------ PANEL IZQUIERDO: Lista seleccionable de planes ------
        JPanel panelLista = new JPanel(new BorderLayout());
        modeloListaRiego = new DefaultListModel<>();
        listaPlanesRiego = new JList<>(modeloListaRiego);
        listaPlanesRiego.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Solo una selección
        listaPlanesRiego.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        
        JScrollPane scrollLista = new JScrollPane(listaPlanesRiego);
        panelLista.add(new JLabel("Planes de Riego (seleccione uno):"), BorderLayout.NORTH);
        panelLista.add(scrollLista, BorderLayout.CENTER);
        
        // ------ PANEL DERECHO: Detalles del plan seleccionado ------
        areaPlanesRiego = new JTextArea();
        areaPlanesRiego.setEditable(false);
        areaPlanesRiego.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        areaPlanesRiego.setText("Seleccione un plan de la lista para ver detalles...");
        
        JScrollPane scrollDetalles = new JScrollPane(areaPlanesRiego);
        
        // Listener para mostrar detalles cuando se selecciona un plan
        listaPlanesRiego.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    mostrarDetallesPlanRiegoSeleccionado();
                }
            }
        });
        
        // Configurar los paneles divididos
        splitPane.setLeftComponent(panelLista);
        splitPane.setRightComponent(scrollDetalles);
        panel.add(splitPane, BorderLayout.CENTER);

        // ============== PANEL INFERIOR - BOTONES DE CONTROL ==============
        JPanel panelControl = new JPanel();
        
        // Botón para actualizar la lista
        JButton btnActualizar = new JButton("🔄 Actualizar Lista");
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarListaPlanesRiego();
            }
        });
        
        // Botón para marcar plan como completado
        JButton btnCompletar = new JButton("✅ Marcar como Completado");
        btnCompletar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                completarPlanRiegoSeleccionado();
            }
        });
        
        // Botón para cancelar plan
        JButton btnCancelar = new JButton("❌ Cancelar Plan");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarPlanRiegoSeleccionado();
            }
        });

        // Agregar botones al panel de control
        panelControl.add(btnActualizar);
        panelControl.add(btnCompletar);
        panelControl.add(btnCancelar);
        panel.add(panelControl, BorderLayout.SOUTH);

        // Cargar planes iniciales en la lista
        actualizarListaPlanesRiego();

        return panel;
    }

    // =========================================================================
    // SOLAPA 2: PLANES DE FERTILIZACIÓN
    // =========================================================================

    /**
     * CREA EL PANEL COMPLETO DE PLANES DE FERTILIZACIÓN
     * Incluye formulario de creación, lista seleccionable y controles
     * @return JPanel con todos los componentes de fertilización
     */
    private JPanel crearPanelFertilizacion() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ==================== PANEL SUPERIOR - FORMULARIO ====================
        JPanel panelFormulario = new JPanel(new GridLayout(0, 2, 5, 5));
        
        // Inicializar componentes de fertilización
        inicializarComponentesFertilizacion();

        // Agregar componentes al formulario
        panelFormulario.add(new JLabel("Parcela a fertilizar:"));
        panelFormulario.add(comboParcelasFert);
        panelFormulario.add(new JLabel("Fertilizante:"));
        panelFormulario.add(comboFertilizantes);
        panelFormulario.add(new JLabel("Fecha aplicación (AAAA-MM-DD):"));
        panelFormulario.add(txtFechaAplicacion);
        panelFormulario.add(new JLabel("Dosis (kg/ha):"));
        panelFormulario.add(txtDosisFertilizacion);

        // Botón para crear nuevo plan de fertilización
        JButton btnCrearPlan = new JButton("🌱 Crear Plan de Fertilización");
        btnCrearPlan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearPlanFertilizacionDesdeInterfaz();
            }
        });
        
        panelFormulario.add(new JLabel()); // Espacio vacío para alineación
        panelFormulario.add(btnCrearPlan);
        panel.add(panelFormulario, BorderLayout.NORTH);

        // ============ PANEL CENTRAL - LISTA Y DETALLES DIVIDIDOS =============
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(300); // Dividir a 300px de la izquierda
        
        // ------ PANEL IZQUIERDO: Lista seleccionable de planes ------
        JPanel panelLista = new JPanel(new BorderLayout());
        modeloListaFertilizacion = new DefaultListModel<>();
        listaPlanesFertilizacion = new JList<>(modeloListaFertilizacion);
        listaPlanesFertilizacion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaPlanesFertilizacion.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        
        JScrollPane scrollLista = new JScrollPane(listaPlanesFertilizacion);
        panelLista.add(new JLabel("Planes de Fertilización (seleccione uno):"), BorderLayout.NORTH);
        panelLista.add(scrollLista, BorderLayout.CENTER);
        
        // ------ PANEL DERECHO: Detalles del plan seleccionado ------
        areaPlanesFertilizacion = new JTextArea();
        areaPlanesFertilizacion.setEditable(false);
        areaPlanesFertilizacion.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        areaPlanesFertilizacion.setText("Seleccione un plan de la lista para ver detalles...");
        
        JScrollPane scrollDetalles = new JScrollPane(areaPlanesFertilizacion);
        
        // Listener para mostrar detalles cuando se selecciona un plan
        listaPlanesFertilizacion.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    mostrarDetallesPlanFertilizacionSeleccionado();
                }
            }
        });
        
        // Configurar los paneles divididos
        splitPane.setLeftComponent(panelLista);
        splitPane.setRightComponent(scrollDetalles);
        panel.add(splitPane, BorderLayout.CENTER);

        // ============== PANEL INFERIOR - BOTONES DE CONTROL ==============
        JPanel panelControl = new JPanel();
        
        // Botón para actualizar la lista
        JButton btnActualizar = new JButton("🔄 Actualizar Lista");
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarListaPlanesFertilizacion();
            }
        });
        
        // Botón para marcar plan como completado
        JButton btnCompletar = new JButton("✅ Marcar como Completado");
        btnCompletar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                completarPlanFertilizacionSeleccionado();
            }
        });
        
        // Botón para cancelar plan
        JButton btnCancelar = new JButton("❌ Cancelar Plan");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarPlanFertilizacionSeleccionado();
            }
        });

        // Agregar botones al panel de control
        panelControl.add(btnActualizar);
        panelControl.add(btnCompletar);
        panelControl.add(btnCancelar);
        panel.add(panelControl, BorderLayout.SOUTH);

        // Cargar planes iniciales en la lista
        actualizarListaPlanesFertilizacion();

        return panel;
    }

    // =========================================================================
    // SOLAPA 3: CALENDARIO INTEGRADO
    // =========================================================================

    /**
     * CREA EL PANEL DE CALENDARIO INTEGRADO
     * Muestra una vista consolidada de todos los planes activos
     * @return JPanel con el calendario integrado
     */
    private JPanel crearPanelCalendario() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Área de texto para mostrar el calendario
        JTextArea areaCalendario = new JTextArea();
        areaCalendario.setEditable(false);
        areaCalendario.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        
        // Generar y mostrar el calendario
        String calendario = generarCalendarioIntegrado();
        areaCalendario.setText(calendario);
        
        // Agregar área de calendario con scroll
        panel.add(new JScrollPane(areaCalendario), BorderLayout.CENTER);

        // Etiqueta informativa
        JLabel lblInfo = new JLabel("📅 Calendario Integrado de Riego y Fertilización");
        panel.add(lblInfo, BorderLayout.NORTH);

        return panel;
    }

    // =========================================================================
    // SOLAPA 4: MÉTRICAS DE RECURSOS
    // =========================================================================

    /**
     * CREA EL PANEL DE MÉTRICAS DE RECURSOS
     * Muestra estadísticas y métricas de los planes
     * @return JPanel con las métricas calculadas
     */
    private JPanel crearPanelMetricas() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Área de texto para mostrar las métricas
        JTextArea areaMetricas = new JTextArea();
        areaMetricas.setEditable(false);
        areaMetricas.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        
        // Calcular y mostrar métricas iniciales
        String metricas = calcularMetricasRecursos();
        areaMetricas.setText(metricas);
        
        // Agregar área de métricas con scroll
        panel.add(new JScrollPane(areaMetricas), BorderLayout.CENTER);

        // Botón para actualizar métricas
        JButton btnActualizar = new JButton("📊 Actualizar Métricas");
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nuevasMetricas = calcularMetricasRecursos();
                areaMetricas.setText(nuevasMetricas);
            }
        });
        
        // Panel para el botón de actualización
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnActualizar);
        panel.add(panelBoton, BorderLayout.SOUTH);

        return panel;
    }

    // =========================================================================
    // MÉTODOS DE INICIALIZACIÓN DE COMPONENTES
    // =========================================================================

    /**
     * INICIALIZA LOS COMPONENTES DE LA SOLAPA DE RIEGO
     * Carga las parcelas disponibles en el ComboBox
     */
    private void inicializarComponentesRiego() {
        comboParcelasRiego = new JComboBox<>();
        // Cargar todas las parcelas del gestor de parcelas
        for (Parcela parcela : gestorParcelas.getListaParcelas()) {
            comboParcelasRiego.addItem(parcela);
        }
        
        // Inicializar campos de texto
        txtFrecuenciaRiego = new JTextField();
        txtDuracionRiego = new JTextField();
    }

    /**
     * INICIALIZA LOS COMPONENTES DE LA SOLAPA DE FERTILIZACIÓN
     * Carga parcelas y fertilizantes disponibles en los ComboBox
     */
    private void inicializarComponentesFertilizacion() {
        // ComboBox para parcelas
        comboParcelasFert = new JComboBox<>();
        for (Parcela parcela : gestorParcelas.getListaParcelas()) {
            comboParcelasFert.addItem(parcela);
        }
        
        // ComboBox para fertilizantes
        comboFertilizantes = new JComboBox<>();
        for (ProductoAgricola producto : gestorStock.getInventario()) {
            comboFertilizantes.addItem(producto);
        }
        
        // Inicializar campos de texto
        txtFechaAplicacion = new JTextField();
        txtDosisFertilizacion = new JTextField();
    }

    // =========================================================================
    // MÉTODOS PARA CREAR PLANES DESDE LA INTERFAZ
    // =========================================================================

    /**
     * CREA UN NUEVO PLAN DE RIEGO DESDE LOS DATOS DEL FORMULARIO
     * Valida los datos, crea el plan y lo agrega a la lista
     */
    private void crearPlanRiegoDesdeInterfaz() {
        try {
            // Validar que se haya seleccionado una parcela
            if (comboParcelasRiego.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Seleccione una parcela", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar y obtener datos numéricos
            int frecuencia = Integer.parseInt(txtFrecuenciaRiego.getText());
            double duracion = Double.parseDouble(txtDuracionRiego.getText());
            
            // Validar valores positivos
            if (frecuencia <= 0 || duracion <= 0) {
                JOptionPane.showMessageDialog(null, "La frecuencia y duración deben ser mayores a cero", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener parcela seleccionada
            Parcela parcela = (Parcela) comboParcelasRiego.getSelectedItem();

            // Crear nuevo plan de riego (ID autoincremental)
            int id = planesDeRiego.size() + 1;
            PlanDeRiego nuevoPlan = new PlanDeRiego(id, parcela, LocalDate.now(), frecuencia, duracion);
            planesDeRiego.add(nuevoPlan);

            // Limpiar campos y actualizar interfaz
            limpiarCamposRiego();
            actualizarListaPlanesRiego();

            JOptionPane.showMessageDialog(null, "✅ Plan de riego creado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La frecuencia y duración deben ser números válidos", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear plan de riego: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * CREA UN NUEVO PLAN DE FERTILIZACIÓN DESDE LOS DATOS DEL FORMULARIO
     * Valida los datos, crea el plan y lo agrega a la lista
     */
    private void crearPlanFertilizacionDesdeInterfaz() {
        try {
            // Validar selección de parcela
            if (comboParcelasFert.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Seleccione una parcela", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar selección de fertilizante
            if (comboFertilizantes.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Seleccione un fertilizante", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar y obtener datos
            LocalDate fechaAplicacion = LocalDate.parse(txtFechaAplicacion.getText());
            double dosis = Double.parseDouble(txtDosisFertilizacion.getText());
            
            // Validar dosis positiva
            if (dosis <= 0) {
                JOptionPane.showMessageDialog(null, "La dosis debe ser mayor a cero", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener datos seleccionados
            Parcela parcela = (Parcela) comboParcelasFert.getSelectedItem();
            ProductoAgricola fertilizante = (ProductoAgricola) comboFertilizantes.getSelectedItem();

            // Crear nuevo plan de fertilización (ID autoincremental)
            int id = planesDeFertilizacion.size() + 1;
            PlanDeFertilizacion nuevoPlan = new PlanDeFertilizacion(id, parcela, fertilizante, fechaAplicacion, dosis);
            planesDeFertilizacion.add(nuevoPlan);

            // Limpiar campos y actualizar interfaz
            limpiarCamposFertilizacion();
            actualizarListaPlanesFertilizacion();

            JOptionPane.showMessageDialog(null, "✅ Plan de fertilización creado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (java.time.format.DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use AAAA-MM-DD", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La dosis debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear plan de fertilización: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // =========================================================================
    // MÉTODOS PARA ACTUALIZAR LISTAS Y DETALLES
    // =========================================================================

    /**
     * ACTUALIZA LA LISTA DE PLANES DE RIEGO EN LA INTERFAZ
     * Sincroniza el modelo de la lista con la lista interna de planes
     */
    private void actualizarListaPlanesRiego() {
        modeloListaRiego.clear();
        // Agregar todos los planes al modelo de la lista
        for (PlanDeRiego plan : planesDeRiego) {
            modeloListaRiego.addElement(plan);
        }
        
        // Mensaje por defecto si no hay planes
        if (planesDeRiego.isEmpty()) {
            areaPlanesRiego.setText("No hay planes de riego activos.");
        } else {
            areaPlanesRiego.setText("Seleccione un plan de la lista para ver detalles...");
        }
    }

    /**
     * ACTUALIZA LA LISTA DE PLANES DE FERTILIZACIÓN EN LA INTERFAZ
     * Sincroniza el modelo de la lista con la lista interna de planes
     */
    private void actualizarListaPlanesFertilizacion() {
        modeloListaFertilizacion.clear();
        // Agregar todos los planes al modelo de la lista
        for (PlanDeFertilizacion plan : planesDeFertilizacion) {
            modeloListaFertilizacion.addElement(plan);
        }
        
        // Mensaje por defecto si no hay planes
        if (planesDeFertilizacion.isEmpty()) {
            areaPlanesFertilizacion.setText("No hay planes de fertilización.");
        } else {
            areaPlanesFertilizacion.setText("Seleccione un plan de la lista para ver detalles...");
        }
    }

    /**
     * MUESTRA LOS DETALLES DEL PLAN DE RIEGO SELECCIONADO
     * Se ejecuta automáticamente cuando el usuario selecciona un plan
     */
    private void mostrarDetallesPlanRiegoSeleccionado() {
        PlanDeRiego planSeleccionado = listaPlanesRiego.getSelectedValue();
        if (planSeleccionado != null) {
            // Mostrar representación en texto del plan seleccionado
            areaPlanesRiego.setText(planSeleccionado.toString());
        } else {
            areaPlanesRiego.setText("Seleccione un plan de la lista para ver detalles...");
        }
    }

    /**
     * MUESTRA LOS DETALLES DEL PLAN DE FERTILIZACIÓN SELECCIONADO
     * Se ejecuta automáticamente cuando el usuario selecciona un plan
     */
    private void mostrarDetallesPlanFertilizacionSeleccionado() {
        PlanDeFertilizacion planSeleccionado = listaPlanesFertilizacion.getSelectedValue();
        if (planSeleccionado != null) {
            // Mostrar representación en texto del plan seleccionado
            areaPlanesFertilizacion.setText(planSeleccionado.toString());
        } else {
            areaPlanesFertilizacion.setText("Seleccione un plan de la lista para ver detalles...");
        }
    }

    // =========================================================================
    // MÉTODOS PARA GESTIÓN DE ESTADOS DE PLANES
    // =========================================================================

    /**
     * MARCA EL PLAN DE RIEGO SELECCIONADO COMO COMPLETADO
     * Incluye confirmación del usuario antes de realizar el cambio
     */
    private void completarPlanRiegoSeleccionado() {
        PlanDeRiego planSeleccionado = listaPlanesRiego.getSelectedValue();
        if (planSeleccionado == null) {
            JOptionPane.showMessageDialog(null, 
                "Seleccione un plan de riego de la lista para completarlo.", 
                "Selección Requerida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Confirmar con el usuario antes de completar
        int confirmacion = JOptionPane.showConfirmDialog(null,
            "¿Está seguro de que desea marcar como COMPLETADO el siguiente plan?\n\n" +
            planSeleccionado.toString() + "\n\nEsta acción no se puede deshacer.",
            "Confirmar Completado", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            // Cambiar estado y actualizar interfaz
            planSeleccionado.setEstado("COMPLETADO");
            actualizarListaPlanesRiego();
            areaPlanesRiego.setText(planSeleccionado.toString());
            JOptionPane.showMessageDialog(null, 
                "✅ Plan de riego marcado como COMPLETADO exitosamente.", 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * CANCELA EL PLAN DE RIEGO SELECCIONADO
     * Incluye confirmación del usuario antes de realizar el cambio
     */
    private void cancelarPlanRiegoSeleccionado() {
        PlanDeRiego planSeleccionado = listaPlanesRiego.getSelectedValue();
        if (planSeleccionado == null) {
            JOptionPane.showMessageDialog(null, 
                "Seleccione un plan de riego de la lista para cancelarlo.", 
                "Selección Requerida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Confirmar con el usuario antes de cancelar
        int confirmacion = JOptionPane.showConfirmDialog(null,
            "¿Está seguro de que desea CANCELAR el siguiente plan?\n\n" +
            planSeleccionado.toString() + "\n\nEsta acción no se puede deshacer.",
            "Confirmar Cancelación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            // Cambiar estado y actualizar interfaz
            planSeleccionado.setEstado("CANCELADO");
            actualizarListaPlanesRiego();
            areaPlanesRiego.setText(planSeleccionado.toString());
            JOptionPane.showMessageDialog(null, 
                "✅ Plan de riego CANCELADO exitosamente.", 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * MARCA EL PLAN DE FERTILIZACIÓN SELECCIONADO COMO COMPLETADO
     * Incluye confirmación del usuario antes de realizar el cambio
     */
    private void completarPlanFertilizacionSeleccionado() {
        PlanDeFertilizacion planSeleccionado = listaPlanesFertilizacion.getSelectedValue();
        if (planSeleccionado == null) {
            JOptionPane.showMessageDialog(null, 
                "Seleccione un plan de fertilización de la lista para completarlo.", 
                "Selección Requerida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Confirmar con el usuario antes de completar
        int confirmacion = JOptionPane.showConfirmDialog(null,
            "¿Está seguro de que desea marcar como COMPLETADO el siguiente plan?\n\n" +
            planSeleccionado.toString() + "\n\nEsta acción no se puede deshacer.",
            "Confirmar Completado", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            // Cambiar estado y actualizar interfaz
            planSeleccionado.setEstado("COMPLETADO");
            actualizarListaPlanesFertilizacion();
            areaPlanesFertilizacion.setText(planSeleccionado.toString());
            JOptionPane.showMessageDialog(null, 
                "✅ Plan de fertilización marcado como COMPLETADO exitosamente.", 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * CANCELA EL PLAN DE FERTILIZACIÓN SELECCIONADO
     * Incluye confirmación del usuario antes de realizar el cambio
     */
    private void cancelarPlanFertilizacionSeleccionado() {
        PlanDeFertilizacion planSeleccionado = listaPlanesFertilizacion.getSelectedValue();
        if (planSeleccionado == null) {
            JOptionPane.showMessageDialog(null, 
                "Seleccione un plan de fertilización de la lista para cancelarlo.", 
                "Selección Requerida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Confirmar con el usuario antes de cancelar
        int confirmacion = JOptionPane.showConfirmDialog(null,
            "¿Está seguro de que desea CANCELAR el siguiente plan?\n\n" +
            planSeleccionado.toString() + "\n\nEsta acción no se puede deshacer.",
            "Confirmar Cancelación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            // Cambiar estado y actualizar interfaz
            planSeleccionado.setEstado("CANCELADO");
            actualizarListaPlanesFertilizacion();
            areaPlanesFertilizacion.setText(planSeleccionado.toString());
            JOptionPane.showMessageDialog(null, 
                "✅ Plan de fertilización CANCELADO exitosamente.", 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // =========================================================================
    // MÉTODOS PARA GENERACIÓN DE INFORMES
    // =========================================================================

    /**
     * GENERA UN CALENDARIO INTEGRADO CON TODOS LOS PLANES ACTIVOS
     * @return String con el calendario formateado
     */
    private String generarCalendarioIntegrado() {
        StringBuilder calendario = new StringBuilder();
        calendario.append("=== CALENDARIO INTEGRADO ===\n\n");
        
        // Sección de planes de riego activos
        calendario.append("Próximos Riegos:\n");
        if (planesDeRiego.isEmpty()) {
            calendario.append("  No hay planes de riego programados\n");
        } else {
            for (PlanDeRiego plan : planesDeRiego) {
                if ("Activo".equals(plan.getEstado())) {
                    calendario.append("  - ").append(plan.getParcela().getNombre())
                             .append(" cada ").append(plan.getFrecuenciaDias()).append(" días\n");
                }
            }
        }
        
        // Sección de planes de fertilización pendientes
        calendario.append("\nPróximas Fertilizaciones:\n");
        if (planesDeFertilizacion.isEmpty()) {
            calendario.append("  No hay planes de fertilización programados\n");
        } else {
            for (PlanDeFertilizacion plan : planesDeFertilizacion) {
                if ("Pendiente".equals(plan.getEstado())) {
                    calendario.append("  - ").append(plan.getParcela().getNombre())
                             .append(" el ").append(plan.getFechaAplicacion()).append("\n");
                }
            }
        }

        return calendario.toString();
    }

    /**
     * CALCULA MÉTRICAS Y ESTADÍSTICAS DE LOS RECURSOS
     * @return String con las métricas formateadas
     */
    private String calcularMetricasRecursos() {
        StringBuilder metricas = new StringBuilder();
        metricas.append("=== MÉTRICAS DE RECURSOS HÍDRICOS Y NUTRICIONALES ===\n\n");
        
        // Métricas de riego
        metricas.append("Planes de Riego:\n");
        metricas.append("  - Total: ").append(planesDeRiego.size()).append("\n");
        long activosRiego = planesDeRiego.stream().filter(p -> "Activo".equals(p.getEstado())).count();
        metricas.append("  - Activos: ").append(activosRiego).append("\n");
        metricas.append("  - Finalizados: ").append(planesDeRiego.size() - activosRiego).append("\n\n");
        
        // Métricas de fertilización
        metricas.append("Planes de Fertilización:\n");
        metricas.append("  - Total: ").append(planesDeFertilizacion.size()).append("\n");
        long pendientesFert = planesDeFertilizacion.stream().filter(p -> "Pendiente".equals(p.getEstado())).count();
        metricas.append("  - Pendientes: ").append(pendientesFert).append("\n");
        metricas.append("  - Completados: ").append(planesDeFertilizacion.size() - pendientesFert).append("\n");

        return metricas.toString();
    }

    // =========================================================================
    // MÉTODOS AUXILIARES
    // =========================================================================

    /**
     * LIMPIA LOS CAMPOS DEL FORMULARIO DE RIEGO
     */
    private void limpiarCamposRiego() {
        txtFrecuenciaRiego.setText("");
        txtDuracionRiego.setText("");
    }

    /**
     * LIMPIA LOS CAMPOS DEL FORMULARIO DE FERTILIZACIÓN
     */
    private void limpiarCamposFertilizacion() {
        txtFechaAplicacion.setText("");
        txtDosisFertilizacion.setText("");
    }

    /**
     * CREA EL PANEL DE BOTONES INFERIORES
     * @param dialog Diálogo padre para poder cerrarlo
     * @return JPanel con los botones
     */
    private JPanel crearPanelBotones(JDialog dialog) {
        JPanel panel = new JPanel();
        
        // Botón para cerrar la ventana
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); // Cerrar el diálogo
            }
        });
        
        panel.add(btnCerrar);
        return panel;
    }

    // =========================================================================
    // MÉTODOS DE COMPATIBILIDAD CON VERSIONES ANTERIORES
    // =========================================================================

    /**
     * MÉTODO COMPATIBLE CON VERSIÓN ANTERIOR
     * Redirige a la interfaz completa
     */
    public void crearPlanDeRiego() {
        mostrarInterfazCompleta();
    }

    /**
     * MÉTODO COMPATIBLE CON VERSIÓN ANTERIOR
     * Redirige a la interfaz completa
     */
    public void verPlanesDeRiego() {
        mostrarInterfazCompleta();
    }

    /**
     * MÉTODO COMPATIBLE CON VERSIÓN ANTERIOR
     * Redirige a la interfaz completa
     */
    public void crearPlanDeFertilizacion() {
        mostrarInterfazCompleta();
    }

    /**
     * MÉTODO COMPATIBLE CON VERSIÓN ANTERIOR
     * Redirige a la interfaz completa
     */
    public void verPlanesDeFertilizacion() {
        mostrarInterfazCompleta();
    }
}