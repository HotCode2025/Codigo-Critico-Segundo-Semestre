package agestion.servicios;

import agestion.modelo.Empleado;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
 * GESTIÓN DE PERSONAL CON INTERFAZ DE SOLAPAS
 * 
 * Sistema completo de Recursos Humanos con interfaz organizada
 * en pestañas para todas las operaciones de empleados.
 * 
 * @author Código Crítico 2025
 * @version 2.0
 */
public class GestionPersonal {

    private ArrayList<Empleado> nomina = new ArrayList<>();

    // Componentes de la interfaz
    private JTextField txtNombre;
    private JTextField txtDNI;
    private JTextField txtCUIT;
    private JTextField txtFechaIngreso;
    private JTextField txtCategoria;
    private JComboBox<Empleado.TipoContrato> comboTipoContrato;
    private JTextField txtSueldo;
    private JTextField txtObraSocial;
    private JTextField txtART;
    private JTextField txtFotoPath;
    private JTextArea areaNomina;
    private JTextField txtLegajoBuscar;
    private JTextArea areaFichaEmpleado;

    /**
     * MUESTRA LA INTERFAZ COMPLETA CON SOLAPAS
     */
    public void mostrarInterfazCompleta() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Gestión de Personal - Recursos Humanos");
        dialog.setModal(true);
        dialog.setSize(600, 600);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Agregar solapas
        tabbedPane.addTab("👥 Contratar Empleado", crearPanelContratar());
        tabbedPane.addTab("📋 Nómina Completa", crearPanelNomina());
        tabbedPane.addTab("👤 Ficha de Empleado", crearPanelFicha());
        tabbedPane.addTab("📊 RRHH - Estadísticas", crearPanelEstadisticasRRHH());

        dialog.add(tabbedPane, BorderLayout.CENTER);
        
        // Botones inferiores
        JPanel panelBotones = crearPanelBotones(dialog);
        dialog.add(panelBotones, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    /**
     * CREA EL PANEL DE CONTRATACIÓN
     */
    private JPanel crearPanelContratar() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        inicializarComponentes();

        // Información personal
        panel.add(new JLabel("Nombre completo:"));
        panel.add(txtNombre);

        panel.add(new JLabel("DNI:"));
        panel.add(txtDNI);

        panel.add(new JLabel("CUIT:"));
        panel.add(txtCUIT);

        // Información laboral
        panel.add(new JLabel("Fecha de ingreso (AAAA-MM-DD):"));
        panel.add(txtFechaIngreso);

        panel.add(new JLabel("Categoría/Puesto:"));
        panel.add(txtCategoria);

        panel.add(new JLabel("Tipo de contrato:"));
        panel.add(comboTipoContrato);

        // Información económica
        panel.add(new JLabel("Sueldo básico ($):"));
        panel.add(txtSueldo);

        panel.add(new JLabel("Obra Social:"));
        panel.add(txtObraSocial);

        panel.add(new JLabel("ART:"));
        panel.add(txtART);

        panel.add(new JLabel("Ruta de foto (opcional):"));
        panel.add(txtFotoPath);

        // Botón de contratación
        JButton btnContratar = new JButton("✅ Contratar Empleado");
        btnContratar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contratarEmpleadoDesdeInterfaz();
            }
        });
        panel.add(new JLabel()); // Espacio vacío
        panel.add(btnContratar);

        return panel;
    }

    /**
     * CREA EL PANEL DE NÓMINA COMPLETA
     */
    private JPanel crearPanelNomina() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        areaNomina = new JTextArea(20, 60);
        areaNomina.setEditable(false);
        areaNomina.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 11));
        
        JScrollPane scrollPane = new JScrollPane(areaNomina);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botones de control
        JPanel panelBotones = new JPanel();
        
        JButton btnActualizar = new JButton("🔄 Actualizar Nómina");
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarNomina();
            }
        });
        
        JButton btnExportar = new JButton("💾 Exportar Nómina");
        btnExportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportarNomina();
            }
        });
        
        JButton btnFiltrarActivos = new JButton("👥 Solo Activos");
        btnFiltrarActivos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarEmpleadosActivos();
            }
        });
        
        panelBotones.add(btnActualizar);
        panelBotones.add(btnExportar);
        panelBotones.add(btnFiltrarActivos);
        panel.add(panelBotones, BorderLayout.SOUTH);

        // Cargar nómina inicial
        actualizarNomina();

        return panel;
    }

    /**
     * CREA EL PANEL DE FICHA DE EMPLEADO
     */
    private JPanel crearPanelFicha() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel de búsqueda
        JPanel panelBusqueda = new JPanel(new GridLayout(1, 3, 10, 10));
        panelBusqueda.add(new JLabel("Legajo del empleado:"));
        txtLegajoBuscar = new JTextField();
        panelBusqueda.add(txtLegajoBuscar);
        
        JButton btnBuscar = new JButton("🔍 Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarEmpleadoPorLegajo();
            }
        });
        panelBusqueda.add(btnBuscar);

        panel.add(panelBusqueda, BorderLayout.NORTH);

        // Área de ficha
        areaFichaEmpleado = new JTextArea(15, 60);
        areaFichaEmpleado.setEditable(false);
        areaFichaEmpleado.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        
        panel.add(new JScrollPane(areaFichaEmpleado), BorderLayout.CENTER);

        // Botones de acciones
        JPanel panelAcciones = new JPanel();
        
        JButton btnDarBaja = new JButton("📝 Dar de Baja");
        btnDarBaja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                darDeBajaEmpleadoDesdeInterfaz();
            }
        });
        
        JButton btnEditar = new JButton("✏️ Editar Datos");
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarEmpleadoDesdeInterfaz();
            }
        });
        
        panelAcciones.add(btnDarBaja);
        panelAcciones.add(btnEditar);
        panel.add(panelAcciones, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * CREA EL PANEL DE ESTADÍSTICAS DE RRHH
     */
    private JPanel crearPanelEstadisticasRRHH() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextArea areaEstadisticas = new JTextArea();
        areaEstadisticas.setEditable(false);
        areaEstadisticas.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        
        // Calcular estadísticas
        String estadisticas = calcularEstadisticasRRHH();
        areaEstadisticas.setText(estadisticas);
        
        panel.add(new JScrollPane(areaEstadisticas), BorderLayout.CENTER);

        // Botón para actualizar
        JButton btnActualizar = new JButton("📊 Actualizar Estadísticas");
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nuevasStats = calcularEstadisticasRRHH();
                areaEstadisticas.setText(nuevasStats);
            }
        });
        
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnActualizar);
        panel.add(panelBoton, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * INICIALIZA LOS COMPONENTES DE LA INTERFAZ
     */
    private void inicializarComponentes() {
        txtNombre = new JTextField();
        txtDNI = new JTextField();
        txtCUIT = new JTextField();
        txtFechaIngreso = new JTextField();
        txtCategoria = new JTextField();
        txtSueldo = new JTextField();
        txtObraSocial = new JTextField();
        txtART = new JTextField();
        txtFotoPath = new JTextField();
        
        // Combo tipo contrato
        comboTipoContrato = new JComboBox<>(Empleado.TipoContrato.values());
    }

    /**
     * CONTRATA UN EMPLEADO DESDE LA INTERFAZ
     */
    private void contratarEmpleadoDesdeInterfaz() {
        try {
            // Validaciones básicas
            if (txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese el nombre del empleado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (txtDNI.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese el DNI", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (txtFechaIngreso.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese la fecha de ingreso", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Parsear datos
            LocalDate fechaIngreso = LocalDate.parse(txtFechaIngreso.getText().trim());
            double sueldo = Double.parseDouble(txtSueldo.getText());
            
            // Generar legajo único
            int legajo = nomina.size() + 1001;

            // Crear empleado
            Empleado nuevoEmpleado = new Empleado(
                legajo,
                txtNombre.getText(),
                txtDNI.getText(),
                fechaIngreso,
                (Empleado.TipoContrato) comboTipoContrato.getSelectedItem(),
                txtCUIT.getText(),
                txtCategoria.getText(),
                sueldo,
                txtObraSocial.getText(),
                txtART.getText(),
                txtFotoPath.getText()
            );

            nomina.add(nuevoEmpleado);

            // Limpiar campos
            limpiarCamposContratacion();
            
            // Actualizar vistas
            actualizarNomina();

            JOptionPane.showMessageDialog(null, 
                "✅ Empleado contratado con éxito\nLegajo asignado: " + legajo, 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use AAAA-MM-DD", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El sueldo debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al contratar empleado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * ACTUALIZA LA NÓMINA COMPLETA
     */
    private void actualizarNomina() {
        if (nomina.isEmpty()) {
            areaNomina.setText("No hay empleados registrados en la nómina.");
            return;
        }

        StringBuilder lista = new StringBuilder();
        lista.append("=== NÓMINA DE PERSONAL - TODOS LOS EMPLEADOS ===\n\n");
        
        for (Empleado empleado : nomina) {
            lista.append(empleado.toString()).append("\n");
            lista.append("----------------------------------------\n");
        }

        areaNomina.setText(lista.toString());
    }

    /**
     * BUSCA UN EMPLEADO POR LEGAJO
     */
    private void buscarEmpleadoPorLegajo() {
        try {
            if (txtLegajoBuscar.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese un número de legajo", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int legajo = Integer.parseInt(txtLegajoBuscar.getText());
            Empleado empleado = buscarEmpleado(legajo);

            if (empleado == null) {
                areaFichaEmpleado.setText("No se encontró ningún empleado con el legajo " + legajo);
                return;
            }

            // Mostrar ficha detallada
            mostrarFichaEmpleado(empleado);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El legajo debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * MUESTRA LA FICHA DE UN EMPLEADO
     */
    private void mostrarFichaEmpleado(Empleado empleado) {
        StringBuilder ficha = new StringBuilder();
        ficha.append("=== FICHA DE EMPLEADO ===\n\n");
        ficha.append("Legajo: ").append(empleado.getLegajo()).append("\n");
        ficha.append("Nombre: ").append(empleado.getNombreCompleto()).append("\n");
        ficha.append("DNI: ").append(empleado.getDni()).append("\n");
        ficha.append("CUIT: ").append(empleado.getCuit()).append("\n");
        ficha.append("Categoría: ").append(empleado.getCategoria()).append("\n");
        ficha.append("Tipo Contrato: ").append(empleado.getTipoContrato()).append("\n");
        ficha.append("Estado: ").append(empleado.getEstado()).append("\n");
        ficha.append("Sueldo: $").append(empleado.getSueldoBasico()).append("\n");
        ficha.append("Obra Social: ").append(empleado.getObraSocial()).append("\n");
        ficha.append("ART: ").append(empleado.getArt()).append("\n");
        
        areaFichaEmpleado.setText(ficha.toString());
    }

    /**
     * DA DE BAJA A UN EMPLEADO DESDE LA INTERFAZ
     */
    private void darDeBajaEmpleadoDesdeInterfaz() {
        try {
            if (txtLegajoBuscar.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese un legajo para dar de baja", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int legajo = Integer.parseInt(txtLegajoBuscar.getText());
            Empleado empleado = buscarEmpleado(legajo);

            if (empleado == null) {
                JOptionPane.showMessageDialog(null, "No se encontró el empleado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if ("Inactivo".equals(empleado.getEstado())) {
                JOptionPane.showMessageDialog(null, "El empleado ya está inactivo", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            int confirmacion = JOptionPane.showConfirmDialog(null, 
                "¿Confirma la baja del empleado " + empleado.getNombreCompleto() + "?",
                "Confirmar Baja",
                JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                String fechaBajaStr = JOptionPane.showInputDialog("Ingrese la fecha de baja (AAAA-MM-DD):");
                if (fechaBajaStr != null) {
                    LocalDate fechaBaja = LocalDate.parse(fechaBajaStr);
                    empleado.setEstado("Inactivo");
                    empleado.setFechaBaja(fechaBaja);
                    
                    actualizarNomina();
                    mostrarFichaEmpleado(empleado);
                    
                    JOptionPane.showMessageDialog(null, "Empleado dado de baja correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Formato de fecha inválido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Legajo inválido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al dar de baja: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * EDITA UN EMPLEADO DESDE LA INTERFAZ (SIMULACIÓN)
     */
    private void editarEmpleadoDesdeInterfaz() {
        JOptionPane.showMessageDialog(null, 
            "Función de edición en desarrollo.\nPróximamente podrá modificar todos los datos del empleado.", 
            "Editar Empleado", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * CALCULA ESTADÍSTICAS DE RRHH
     */
    private String calcularEstadisticasRRHH() {
        if (nomina.isEmpty()) {
            return "No hay empleados para calcular estadísticas.";
        }

        int totalEmpleados = nomina.size();
        int activos = 0;
        int inactivos = 0;
        double totalSueldos = 0;
        
        // Contar por tipo de contrato
        java.util.Map<Empleado.TipoContrato, Integer> contratosCount = new java.util.HashMap<>();
        java.util.Map<String, Integer> categoriasCount = new java.util.HashMap<>();
        
        for (Empleado empleado : nomina) {
            if ("Activo".equals(empleado.getEstado())) {
                activos++;
            } else {
                inactivos++;
            }
            
            totalSueldos += empleado.getSueldoBasico();
            contratosCount.put(empleado.getTipoContrato(), contratosCount.getOrDefault(empleado.getTipoContrato(), 0) + 1);
            categoriasCount.put(empleado.getCategoria(), categoriasCount.getOrDefault(empleado.getCategoria(), 0) + 1);
        }

        StringBuilder stats = new StringBuilder();
        stats.append("=== ESTADÍSTICAS DE RECURSOS HUMANOS ===\n\n");
        stats.append("Total de empleados: ").append(totalEmpleados).append("\n");
        stats.append("Empleados activos: ").append(activos).append("\n");
        stats.append("Empleados inactivos: ").append(inactivos).append("\n");
        stats.append(String.format("Masa salarial total: $ %.2f\n", totalSueldos));
        stats.append(String.format("Sueldo promedio: $ %.2f\n\n", totalSueldos / totalEmpleados));
        
        stats.append("Distribución por tipo de contrato:\n");
        for (Empleado.TipoContrato tipo : contratosCount.keySet()) {
            int count = contratosCount.get(tipo);
            double porcentaje = (count * 100.0) / totalEmpleados;
            stats.append(String.format("- %s: %d (%.1f%%)\n", tipo, count, porcentaje));
        }
        
        stats.append("\nDistribución por categoría:\n");
        for (String categoria : categoriasCount.keySet()) {
            int count = categoriasCount.get(categoria);
            double porcentaje = (count * 100.0) / totalEmpleados;
            stats.append(String.format("- %s: %d (%.1f%%)\n", categoria, count, porcentaje));
        }

        return stats.toString();
    }

    /**
     * FILTRA EMPLEADOS ACTIVOS
     */
    private void filtrarEmpleadosActivos() {
        if (nomina.isEmpty()) {
            areaNomina.setText("No hay empleados registrados.");
            return;
        }

        StringBuilder lista = new StringBuilder();
        lista.append("=== NÓMINA - EMPLEADOS ACTIVOS ===\n\n");
        
        int contador = 0;
        for (Empleado empleado : nomina) {
            if ("Activo".equals(empleado.getEstado())) {
                lista.append(empleado.toString()).append("\n");
                lista.append("----------------------------------------\n");
                contador++;
            }
        }

        if (contador == 0) {
            lista.append("No hay empleados activos.");
        } else {
            lista.append("\nTotal de empleados activos: ").append(contador);
        }

        areaNomina.setText(lista.toString());
    }

    /**
     * EXPORTA LA NÓMINA (SIMULACIÓN)
     */
    private void exportarNomina() {
        JOptionPane.showMessageDialog(null, 
            "Función de exportación en desarrollo.\nLa nómina se exportará a archivo PDF/CSV.", 
            "Exportar Nómina", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * BUSCA UN EMPLEADO POR LEGAJO
     */
    private Empleado buscarEmpleado(int legajo) {
        for (Empleado empleado : nomina) {
            if (empleado.getLegajo() == legajo) {
                return empleado;
            }
        }
        return null;
    }

    /**
     * LIMPIA LOS CAMPOS DE CONTRATACIÓN
     */
    private void limpiarCamposContratacion() {
        txtNombre.setText("");
        txtDNI.setText("");
        txtCUIT.setText("");
        txtFechaIngreso.setText("");
        txtCategoria.setText("");
        txtSueldo.setText("");
        txtObraSocial.setText("");
        txtART.setText("");
        txtFotoPath.setText("");
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
    public void contratarEmpleado() {
        mostrarInterfazCompleta();
    }

    /**
     * MÉTODO COMPATIBLE CON VERSIÓN ANTERIOR
     */
    public void verNominaPersonal() {
        mostrarInterfazCompleta();
    }

    /**
     * MÉTODO COMPATIBLE CON VERSIÓN ANTERIOR
     */
    public void verFichaEmpleado() {
        mostrarInterfazCompleta();
    }

    /**
     * MÉTODO COMPATIBLE CON VERSIÓN ANTERIOR
     */
    public void darDeBajaEmpleado() {
        mostrarInterfazCompleta();
    }
}