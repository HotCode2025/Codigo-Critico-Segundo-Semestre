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

/**
 * GESTIÓN DE RIEGO Y FERTILIZACIÓN CON INTERFAZ DE SOLAPAS
 * 
 * Sistema completo para planificación y seguimiento de
 * riego y fertilización con interfaz organizada en pestañas.
 * 
 * @author Código Crítico 2025
 * @version 2.0
 */
public class GestionRiegoFertilizacion {

    private ArrayList<PlanDeRiego> planesDeRiego = new ArrayList<>();
    private ArrayList<PlanDeFertilizacion> planesDeFertilizacion = new ArrayList<>();
    
    // Dependencias de otros gestores
    private GestionParcelas gestorParcelas;
    private GestionStock gestorStock;

    // Componentes de la interfaz
    private JComboBox<Parcela> comboParcelasRiego;
    private JTextField txtFrecuenciaRiego;
    private JTextField txtDuracionRiego;
    private JComboBox<Parcela> comboParcelasFert;
    private JComboBox<ProductoAgricola> comboFertilizantes;
    private JTextField txtFechaAplicacion;
    private JTextField txtDosisFertilizacion;
    private JTextArea areaPlanesRiego;
    private JTextArea areaPlanesFertilizacion;

    public GestionRiegoFertilizacion(GestionParcelas gestorParcelas, GestionStock gestorStock) {
        this.gestorParcelas = gestorParcelas;
        this.gestorStock = gestorStock;
    }

    /**
     * MUESTRA LA INTERFAZ COMPLETA CON SOLAPAS
     */
    public void mostrarInterfazCompleta() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Gestión de Riego y Fertilización");
        dialog.setModal(true);
        dialog.setSize(900, 700);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Agregar solapas
        tabbedPane.addTab("💧 Planes de Riego", crearPanelRiego());
        tabbedPane.addTab("🌱 Planes de Fertilización", crearPanelFertilizacion());
        tabbedPane.addTab("📅 Calendario Integrado", crearPanelCalendario());
        tabbedPane.addTab("📊 Métricas de Recursos", crearPanelMetricas());

        dialog.add(tabbedPane, BorderLayout.CENTER);
        
        // Botones inferiores
        JPanel panelBotones = crearPanelBotones(dialog);
        dialog.add(panelBotones, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    /**
     * CREA EL PANEL DE PLANES DE RIEGO
     */
    private JPanel crearPanelRiego() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridLayout(0, 2, 10, 10));
        
        inicializarComponentesRiego();

        // Parcela para riego
        panelFormulario.add(new JLabel("Parcela a regar:"));
        panelFormulario.add(comboParcelasRiego);

        // Frecuencia
        panelFormulario.add(new JLabel("Frecuencia (días):"));
        panelFormulario.add(txtFrecuenciaRiego);

        // Duración
        panelFormulario.add(new JLabel("Duración (horas):"));
        panelFormulario.add(txtDuracionRiego);

        // Botón de crear plan
        JButton btnCrearPlan = new JButton("💧 Crear Plan de Riego");
        btnCrearPlan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearPlanRiegoDesdeInterfaz();
            }
        });
        panelFormulario.add(new JLabel()); // Espacio vacío
        panelFormulario.add(btnCrearPlan);

        panel.add(panelFormulario, BorderLayout.NORTH);

        // Área de planes de riego
        areaPlanesRiego = new JTextArea(15, 60);
        areaPlanesRiego.setEditable(false);
        areaPlanesRiego.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(areaPlanesRiego);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botones de control
        JPanel panelControl = new JPanel();
        
        JButton btnActualizar = new JButton("🔄 Actualizar Lista");
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPlanesRiego();
            }
        });
        
        JButton btnCompletar = new JButton("✅ Marcar como Completado");
        btnCompletar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                completarPlanRiego();
            }
        });
        
        panelControl.add(btnActualizar);
        panelControl.add(btnCompletar);
        panel.add(panelControl, BorderLayout.SOUTH);

        // Cargar planes iniciales
        actualizarPlanesRiego();

        return panel;
    }

    /**
     * CREA EL PANEL DE PLANES DE FERTILIZACIÓN
     */
    private JPanel crearPanelFertilizacion() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridLayout(0, 2, 10, 10));
        
        inicializarComponentesFertilizacion();

        // Parcela para fertilización
        panelFormulario.add(new JLabel("Parcela a fertilizar:"));
        panelFormulario.add(comboParcelasFert);

        // Fertilizante
        panelFormulario.add(new JLabel("Fertilizante:"));
        panelFormulario.add(comboFertilizantes);

        // Fecha de aplicación
        panelFormulario.add(new JLabel("Fecha aplicación (AAAA-MM-DD):"));
        panelFormulario.add(txtFechaAplicacion);

        // Dosis
        panelFormulario.add(new JLabel("Dosis (kg/ha):"));
        panelFormulario.add(txtDosisFertilizacion);

        // Botón de crear plan
        JButton btnCrearPlan = new JButton("🌱 Crear Plan de Fertilización");
        btnCrearPlan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearPlanFertilizacionDesdeInterfaz();
            }
        });
        panelFormulario.add(new JLabel()); // Espacio vacío
        panelFormulario.add(btnCrearPlan);

        panel.add(panelFormulario, BorderLayout.NORTH);

        // Área de planes de fertilización
        areaPlanesFertilizacion = new JTextArea(15, 60);
        areaPlanesFertilizacion.setEditable(false);
        areaPlanesFertilizacion.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(areaPlanesFertilizacion);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botones de control
        JPanel panelControl = new JPanel();
        
        JButton btnActualizar = new JButton("🔄 Actualizar Lista");
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPlanesFertilizacion();
            }
        });
        
        JButton btnCompletar = new JButton("✅ Marcar como Completado");
        btnCompletar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                completarPlanFertilizacion();
            }
        });
        
        panelControl.add(btnActualizar);
        panelControl.add(btnCompletar);
        panel.add(panelControl, BorderLayout.SOUTH);

        // Cargar planes iniciales
        actualizarPlanesFertilizacion();

        return panel;
    }

    /**
     * CREA EL PANEL DE CALENDARIO INTEGRADO
     */
    private JPanel crearPanelCalendario() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextArea areaCalendario = new JTextArea();
        areaCalendario.setEditable(false);
        areaCalendario.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        
        // Generar calendario
        String calendario = generarCalendarioIntegrado();
        areaCalendario.setText(calendario);
        
        panel.add(new JScrollPane(areaCalendario), BorderLayout.CENTER);

        JLabel lblInfo = new JLabel("📅 Calendario Integrado de Riego y Fertilización");
        panel.add(lblInfo, BorderLayout.NORTH);

        return panel;
    }

    /**
     * CREA EL PANEL DE MÉTRICAS DE RECURSOS
     */
    private JPanel crearPanelMetricas() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextArea areaMetricas = new JTextArea();
        areaMetricas.setEditable(false);
        areaMetricas.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        
        // Calcular métricas
        String metricas = calcularMetricasRecursos();
        areaMetricas.setText(metricas);
        
        panel.add(new JScrollPane(areaMetricas), BorderLayout.CENTER);

        // Botón para actualizar
        JButton btnActualizar = new JButton("📊 Actualizar Métricas");
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nuevasMetricas = calcularMetricasRecursos();
                areaMetricas.setText(nuevasMetricas);
            }
        });
        
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnActualizar);
        panel.add(panelBoton, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * INICIALIZA COMPONENTES DE RIEGO
     */
    private void inicializarComponentesRiego() {
        comboParcelasRiego = new JComboBox<>();
        for (Parcela parcela : gestorParcelas.getListaParcelas()) {
            comboParcelasRiego.addItem(parcela);
        }
        
        txtFrecuenciaRiego = new JTextField();
        txtDuracionRiego = new JTextField();
    }

    /**
     * INICIALIZA COMPONENTES DE FERTILIZACIÓN
     */
    private void inicializarComponentesFertilizacion() {
        comboParcelasFert = new JComboBox<>();
        for (Parcela parcela : gestorParcelas.getListaParcelas()) {
            comboParcelasFert.addItem(parcela);
        }
        
        comboFertilizantes = new JComboBox<>();
        for (ProductoAgricola producto : gestorStock.getInventario()) {
            comboFertilizantes.addItem(producto);
        }
        
        txtFechaAplicacion = new JTextField();
        txtDosisFertilizacion = new JTextField();
    }

    /**
     * CREA UN PLAN DE RIEGO DESDE LA INTERFAZ
     */
    private void crearPlanRiegoDesdeInterfaz() {
        try {
            // Validaciones
            if (comboParcelasRiego.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Seleccione una parcela", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int frecuencia = Integer.parseInt(txtFrecuenciaRiego.getText());
            double duracion = Double.parseDouble(txtDuracionRiego.getText());
            
            if (frecuencia <= 0 || duracion <= 0) {
                JOptionPane.showMessageDialog(null, "La frecuencia y duración deben ser mayores a cero", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener datos
            Parcela parcela = (Parcela) comboParcelasRiego.getSelectedItem();

            // Crear plan de riego
            int id = planesDeRiego.size() + 1;
            PlanDeRiego nuevoPlan = new PlanDeRiego(id, parcela, LocalDate.now(), frecuencia, duracion);
            planesDeRiego.add(nuevoPlan);

            // Limpiar campos
            limpiarCamposRiego();
            
            // Actualizar lista
            actualizarPlanesRiego();

            JOptionPane.showMessageDialog(null, "✅ Plan de riego creado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La frecuencia y duración deben ser números válidos", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear plan de riego: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * CREA UN PLAN DE FERTILIZACIÓN DESDE LA INTERFAZ
     */
    private void crearPlanFertilizacionDesdeInterfaz() {
        try {
            // Validaciones
            if (comboParcelasFert.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Seleccione una parcela", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (comboFertilizantes.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Seleccione un fertilizante", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            LocalDate fechaAplicacion = LocalDate.parse(txtFechaAplicacion.getText());
            double dosis = Double.parseDouble(txtDosisFertilizacion.getText());
            
            if (dosis <= 0) {
                JOptionPane.showMessageDialog(null, "La dosis debe ser mayor a cero", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener datos
            Parcela parcela = (Parcela) comboParcelasFert.getSelectedItem();
            ProductoAgricola fertilizante = (ProductoAgricola) comboFertilizantes.getSelectedItem();

            // Crear plan de fertilización
            int id = planesDeFertilizacion.size() + 1;
            PlanDeFertilizacion nuevoPlan = new PlanDeFertilizacion(id, parcela, fertilizante, fechaAplicacion, dosis);
            planesDeFertilizacion.add(nuevoPlan);

            // Limpiar campos
            limpiarCamposFertilizacion();
            
            // Actualizar lista
            actualizarPlanesFertilizacion();

            JOptionPane.showMessageDialog(null, "✅ Plan de fertilización creado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (java.time.format.DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use AAAA-MM-DD", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La dosis debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear plan de fertilización: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * ACTUALIZA LA LISTA DE PLANES DE RIEGO
     */
    private void actualizarPlanesRiego() {
        if (planesDeRiego.isEmpty()) {
            areaPlanesRiego.setText("No hay planes de riego activos.");
            return;
        }

        StringBuilder lista = new StringBuilder();
        lista.append("=== PLANES DE RIEGO ACTIVOS ===\n\n");
        
        for (PlanDeRiego plan : planesDeRiego) {
            lista.append(plan.toString()).append("\n");
        }

        areaPlanesRiego.setText(lista.toString());
    }

    /**
     * ACTUALIZA LA LISTA DE PLANES DE FERTILIZACIÓN
     */
    private void actualizarPlanesFertilizacion() {
        if (planesDeFertilizacion.isEmpty()) {
            areaPlanesFertilizacion.setText("No hay planes de fertilización.");
            return;
        }

        StringBuilder lista = new StringBuilder();
        lista.append("=== PLANES DE FERTILIZACIÓN ===\n\n");
        
        for (PlanDeFertilizacion plan : planesDeFertilizacion) {
            lista.append(plan.toString()).append("\n");
        }

        areaPlanesFertilizacion.setText(lista.toString());
    }

    /**
     * GENERA CALENDARIO INTEGRADO
     */
    private String generarCalendarioIntegrado() {
        StringBuilder calendario = new StringBuilder();
        calendario.append("=== CALENDARIO INTEGRADO ===\n\n");
        
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
     * CALCULA MÉTRICAS DE RECURSOS
     */
    private String calcularMetricasRecursos() {
        StringBuilder metricas = new StringBuilder();
        metricas.append("=== MÉTRICAS DE RECURSOS HÍDRICOS Y NUTRICIONALES ===\n\n");
        
        metricas.append("Planes de Riego:\n");
        metricas.append("  - Total: ").append(planesDeRiego.size()).append("\n");
        long activosRiego = planesDeRiego.stream().filter(p -> "Activo".equals(p.getEstado())).count();
        metricas.append("  - Activos: ").append(activosRiego).append("\n");
        metricas.append("  - Finalizados: ").append(planesDeRiego.size() - activosRiego).append("\n\n");
        
        metricas.append("Planes de Fertilización:\n");
        metricas.append("  - Total: ").append(planesDeFertilizacion.size()).append("\n");
        long pendientesFert = planesDeFertilizacion.stream().filter(p -> "Pendiente".equals(p.getEstado())).count();
        metricas.append("  - Pendientes: ").append(pendientesFert).append("\n");
        metricas.append("  - Completados: ").append(planesDeFertilizacion.size() - pendientesFert).append("\n");

        return metricas.toString();
    }

    /**
     * COMPLETA UN PLAN DE RIEGO (SIMULACIÓN)
     */
    private void completarPlanRiego() {
        JOptionPane.showMessageDialog(null, 
            "Seleccione un plan de riego de la lista para marcarlo como completado.", 
            "Completar Riego", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * COMPLETA UN PLAN DE FERTILIZACIÓN (SIMULACIÓN)
     */
    private void completarPlanFertilizacion() {
        JOptionPane.showMessageDialog(null, 
            "Seleccione un plan de fertilización de la lista para marcarlo como completado.", 
            "Completar Fertilización", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * LIMPIA CAMPOS DE RIEGO
     */
    private void limpiarCamposRiego() {
        txtFrecuenciaRiego.setText("");
        txtDuracionRiego.setText("");
    }

    /**
     * LIMPIA CAMPOS DE FERTILIZACIÓN
     */
    private void limpiarCamposFertilizacion() {
        txtFechaAplicacion.setText("");
        txtDosisFertilizacion.setText("");
    }

    /**
     * CREA EL PANEL DE BOTONES
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
     * MÉTODO COMPATIBLE CON VERSIÓN ANTERIOR
     */
    public void crearPlanDeRiego() {
        mostrarInterfazCompleta();
    }

    /**
     * MÉTODO COMPATIBLE CON VERSIÓN ANTERIOR
     */
    public void verPlanesDeRiego() {
        mostrarInterfazCompleta();
    }

    /**
     * MÉTODO COMPATIBLE CON VERSIÓN ANTERIOR
     */
    public void crearPlanDeFertilizacion() {
        mostrarInterfazCompleta();
    }

    /**
     * MÉTODO COMPATIBLE CON VERSIÓN ANTERIOR
     */
    public void verPlanesDeFertilizacion() {
        mostrarInterfazCompleta();
    }
}