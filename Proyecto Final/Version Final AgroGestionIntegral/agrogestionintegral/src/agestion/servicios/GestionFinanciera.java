package agestion.servicios;

import agestion.modelo.Transaccion;
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
 * GESTIÓN FINANCIERA CON INTERFAZ DE SOLAPAS
 * 
 * Sistema completo de contabilidad con pestañas para diferentes
 * funcionalidades financieras.
 * 
 * @author Código Crítico 2025
 * @version 2.0
 */
public class GestionFinanciera {
    
    private final ArrayList<Transaccion> libroContable = new ArrayList<>();

    // Componentes de la interfaz
    private JComboBox<String> comboTipoTransaccion;
    private JComboBox<String> comboTasaIVA;
    private JTextField txtDescripcion;
    private JTextField txtMontoTotal;
    private JTextField txtFechaFiltro;
    private JTextArea areaLibroContable;
    private JTextArea areaReporteIVA;

    /**
     * MUESTRA LA INTERFAZ COMPLETA CON SOLAPAS
     */
    public void mostrarInterfazCompleta() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Gestión Financiera - Contabilidad Integral");
        dialog.setModal(true);
        dialog.setSize(600, 600);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Agregar solapas
        tabbedPane.addTab("💰 Registrar Transacción", crearPanelRegistroTransaccion());
        tabbedPane.addTab("📒 Libro Contable", crearPanelLibroContable());
        tabbedPane.addTab("📊 Reporte IVA", crearPanelReporteIVA());
        tabbedPane.addTab("📈 Balance General", crearPanelBalance());

        dialog.add(tabbedPane, BorderLayout.CENTER);
        
        // Botones inferiores
        JPanel panelBotones = crearPanelBotones(dialog);
        dialog.add(panelBotones, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    /**
     * CREA EL PANEL DE REGISTRO DE TRANSACCIONES
     */
    private JPanel crearPanelRegistroTransaccion() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        inicializarComponentes();

        // Tipo de transacción
        panel.add(new JLabel("Tipo de transacción:"));
        panel.add(comboTipoTransaccion);

        // Tasa de IVA
        panel.add(new JLabel("Tasa de IVA:"));
        panel.add(comboTasaIVA);

        // Descripción
        panel.add(new JLabel("Descripción:"));
        panel.add(txtDescripcion);

        // Monto total
        panel.add(new JLabel("Monto TOTAL ($) (IVA Incluido):"));
        panel.add(txtMontoTotal);

        // Botón de registro
        JButton btnRegistrar = new JButton("✅ Registrar Transacción");
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarTransaccionDesdeInterfaz();
            }
        });
        panel.add(new JLabel()); // Espacio vacío
        panel.add(btnRegistrar);

        return panel;
    }

    /**
     * CREA EL PANEL DEL LIBRO CONTABLE
     */
    private JPanel crearPanelLibroContable() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        areaLibroContable = new JTextArea(20, 60);
        areaLibroContable.setEditable(false);
        areaLibroContable.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(areaLibroContable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel de controles
        JPanel panelControles = new JPanel(new GridLayout(1, 3, 10, 10));
        
        // Filtro por fecha
        panelControles.add(new JLabel("Filtrar por fecha (AAAA-MM-DD):"));
        txtFechaFiltro = new JTextField();
        panelControles.add(txtFechaFiltro);
        
        JButton btnFiltrar = new JButton("🔍 Filtrar");
        btnFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarPorFecha();
            }
        });
        panelControles.add(btnFiltrar);
        
        JButton btnMostrarTodo = new JButton("📋 Mostrar Todo");
        btnMostrarTodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTodoLibroContable();
            }
        });
        panelControles.add(btnMostrarTodo);

        panel.add(panelControles, BorderLayout.NORTH);

        // Cargar libro contable inicial
        mostrarTodoLibroContable();

        return panel;
    }

    /**
     * CREA EL PANEL DE REPORTE DE IVA
     */
    private JPanel crearPanelReporteIVA() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        areaReporteIVA = new JTextArea(15, 60);
        areaReporteIVA.setEditable(false);
        areaReporteIVA.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        
        panel.add(new JScrollPane(areaReporteIVA), BorderLayout.CENTER);

        // Botón para generar reporte
        JButton btnGenerarReporte = new JButton("📊 Generar Reporte de IVA");
        btnGenerarReporte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarReporteIVA();
            }
        });
        
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnGenerarReporte);
        panel.add(panelBoton, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * CREA EL PANEL DE BALANCE GENERAL
     */
    private JPanel crearPanelBalance() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextArea areaBalance = new JTextArea();
        areaBalance.setEditable(false);
        areaBalance.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        
        // Calcular balance
        String balance = calcularBalanceGeneral();
        areaBalance.setText(balance);
        
        panel.add(new JScrollPane(areaBalance), BorderLayout.CENTER);

        // Botón para actualizar balance
        JButton btnActualizar = new JButton("🔄 Actualizar Balance");
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nuevoBalance = calcularBalanceGeneral();
                areaBalance.setText(nuevoBalance);
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
        // Combo tipo transacción
        comboTipoTransaccion = new JComboBox<>(new String[]{"Ingreso (Venta)", "Egreso (Compra/Gasto)"});
        
        // Combo tasa IVA
        comboTasaIVA = new JComboBox<>(new String[]{"21%", "10.5%", "27%"});
        
        // Campos de texto
        txtDescripcion = new JTextField();
        txtMontoTotal = new JTextField();
    }

    /**
     * REGISTRA UNA TRANSACCIÓN DESDE LA INTERFAZ
     */
    private void registrarTransaccionDesdeInterfaz() {
        try {
            // Validaciones
            if (txtDescripcion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese una descripción", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double montoTotal = Double.parseDouble(txtMontoTotal.getText());
            if (montoTotal <= 0) {
                JOptionPane.showMessageDialog(null, "El monto debe ser mayor a cero", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener datos
            int tipoSeleccionado = comboTipoTransaccion.getSelectedIndex();
            Transaccion.Tipo tipo = (tipoSeleccionado == 0) ? Transaccion.Tipo.INGRESO : Transaccion.Tipo.EGRESO;
            
            String tasaSeleccionadaStr = (String) comboTasaIVA.getSelectedItem();
            double tasaIva = Double.parseDouble(tasaSeleccionadaStr.replace("%", "")) / 100.0;
            
            String descripcion = txtDescripcion.getText();

            // Calcular montos
            double montoBase = montoTotal / (1 + tasaIva);
            double montoIva = montoTotal - montoBase;

            // Crear transacción
            int id = libroContable.size() + 1;
            Transaccion nuevaTransaccion = new Transaccion(id, LocalDate.now(), descripcion, montoBase, montoIva, tasaIva, tipo);
            libroContable.add(nuevaTransaccion);

            // Limpiar campos
            limpiarCamposTransaccion();
            
            // Actualizar vistas
            mostrarTodoLibroContable();

            // Mostrar detalle
            String detalleIva = String.format("Transacción registrada con éxito.\n\nMonto Base: $%.2f\nIVA (%s): $%.2f", 
                                            montoBase, tasaSeleccionadaStr, montoIva);
            JOptionPane.showMessageDialog(null, detalleIva, "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El monto debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar transacción: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * FILTRA EL LIBRO CONTABLE POR FECHA
     */
    private void filtrarPorFecha() {
        try {
            String fechaStr = txtFechaFiltro.getText().trim();
            if (fechaStr.isEmpty()) {
                mostrarTodoLibroContable();
                return;
            }

            LocalDate fechaFiltro = LocalDate.parse(fechaStr);
            ArrayList<Transaccion> transaccionesFiltradas = new ArrayList<>();
            
            for (Transaccion t : libroContable) {
                if (t.getFecha().equals(fechaFiltro)) {
                    transaccionesFiltradas.add(t);
                }
            }

            if (transaccionesFiltradas.isEmpty()) {
                areaLibroContable.setText("No se encontraron transacciones para la fecha " + fechaFiltro);
                return;
            }

            mostrarTransacciones(transaccionesFiltradas, "Transacciones del " + fechaFiltro);

        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use AAAA-MM-DD", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * MUESTRA TODO EL LIBRO CONTABLE
     */
    private void mostrarTodoLibroContable() {
        mostrarTransacciones(libroContable, "LIBRO CONTABLE COMPLETO");
    }

    /**
     * MUESTRA TRANSACCIONES EN EL ÁREA DE TEXTO
     */
    private void mostrarTransacciones(ArrayList<Transaccion> transacciones, String titulo) {
        if (transacciones.isEmpty()) {
            areaLibroContable.setText("No hay transacciones registradas.");
            return;
        }

        StringBuilder sb = new StringBuilder("=== " + titulo + " ===\n\n");
        double balance = 0;
        
        for (Transaccion t : transacciones) {
            sb.append(t.toString()).append("\n");
            balance += t.getMontoTotal();
        }
        
        sb.append("\n--------------------------------\n");
        sb.append(String.format("BALANCE: $ %.2f", balance));
        
        areaLibroContable.setText(sb.toString());
    }

    /**
     * GENERA EL REPORTE DE IVA
     */
    private void generarReporteIVA() {
        if (libroContable.isEmpty()) {
            areaReporteIVA.setText("No hay transacciones para calcular el IVA.");
            return;
        }

        double ivaDebito = 0;
        double ivaCredito = 0;
        
        for (Transaccion t : libroContable) {
            if (t.getTipo() == Transaccion.Tipo.INGRESO) {
                ivaDebito += t.getMontoIva();
            } else {
                ivaCredito += t.getMontoIva();
            }
        }
        
        double saldoIva = ivaDebito - ivaCredito;
        String resultado = (saldoIva > 0) ? 
            String.format("Saldo a PAGAR a AFIP: $ %.2f", saldoIva) : 
            String.format("Saldo a FAVOR (crédito fiscal): $ %.2f", -saldoIva);
        
        String reporte = "=== REPORTE DE IVA ===\n\n" +
                         String.format("IVA Débito (por ventas):   $ %.2f\n", ivaDebito) +
                         String.format("IVA Crédito (por compras): $ %.2f\n", ivaCredito) +
                         "--------------------------------\n" +
                         resultado;
        
        areaReporteIVA.setText(reporte);
    }

    /**
     * CALCULA EL BALANCE GENERAL
     */
    private String calcularBalanceGeneral() {
        double totalIngresos = 0;
        double totalEgresos = 0;
        
        for (Transaccion t : libroContable) {
            if (t.getTipo() == Transaccion.Tipo.INGRESO) {
                totalIngresos += t.getMontoTotal();
            } else {
                totalEgresos += Math.abs(t.getMontoTotal());
            }
        }
        
        double balanceNeto = totalIngresos - totalEgresos;
        
        return String.format(
            "=== BALANCE GENERAL ===\n\n" +
            "Total Ingresos:  $ %10.2f\n" +
            "Total Egresos:   $ %10.2f\n" +
            "----------------------------\n" +
            "Balance Neto:    $ %10.2f\n\n" +
            "Total Transacciones: %d",
            totalIngresos, totalEgresos, balanceNeto, libroContable.size()
        );
    }

    /**
     * LIMPIA LOS CAMPOS DEL FORMULARIO
     */
    private void limpiarCamposTransaccion() {
        txtDescripcion.setText("");
        txtMontoTotal.setText("");
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
    public void registrarTransaccion() {
        mostrarInterfazCompleta();
    }

    /**
     * MÉTODO COMPATIBLE CON VERSIÓN ANTERIOR
     */
    public void mostrarLibroContableYBalance() {
        mostrarInterfazCompleta();
    }

    /**
     * MÉTODO COMPATIBLE CON VERSIÓN ANTERIOR
     */
    public void mostrarReporteIva() {
        mostrarInterfazCompleta();
    }
}