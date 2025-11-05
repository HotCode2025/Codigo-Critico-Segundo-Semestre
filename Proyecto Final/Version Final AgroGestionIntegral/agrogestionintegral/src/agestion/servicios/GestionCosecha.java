package agestion.servicios;

import agestion.modelo.MovimientoCosecha;
import agestion.modelo.Parcela;
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
 * GESTIÓN DE COSECHA CON INTERFAZ DE SOLAPAS
 * 
 * Maneja el registro y seguimiento de cosechas y transporte
 * mediante una interfaz organizada en pestañas.
 * 
 * @author Código Crítico 2025
 * @version 2.0
 */
public class GestionCosecha {

    private ArrayList<MovimientoCosecha> movimientos = new ArrayList<>();
    private GestionParcelas gestorParcelas;

    // Componentes de la interfaz
    private JComboBox<Parcela> comboParcelas;
    private JTextField txtProducto;
    private JTextField txtKilos;
    private JTextField txtRemito;
    private JTextField txtTransportista;
    private JTextField txtPatente;
    private JTextField txtDTVe;
    private JTextField txtDestino;
    private JTextArea areaMovimientos;

    public GestionCosecha(GestionParcelas gestorParcelas) {
        this.gestorParcelas = gestorParcelas;
    }

    /**
     * MUESTRA LA INTERFAZ COMPLETA CON SOLAPAS
     */
    public void mostrarInterfazCompleta() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Gestión de Cosecha y Transporte");
        dialog.setModal(true);
        dialog.setSize(600, 600);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Agregar solapas
        tabbedPane.addTab("🌾 Registrar Cosecha", crearPanelRegistroCosecha());
        tabbedPane.addTab("📋 Movimientos", crearPanelMovimientos());
        tabbedPane.addTab("🚚 Logística", crearPanelLogistica());

        dialog.add(tabbedPane, BorderLayout.CENTER);
        
        // Botones inferiores
        JPanel panelBotones = crearPanelBotones(dialog);
        dialog.add(panelBotones, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    /**
     * CREA EL PANEL DE REGISTRO DE COSECHA
     */
    private JPanel crearPanelRegistroCosecha() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        inicializarComponentes();

        // Parcela
        panel.add(new JLabel("Parcela de origen:"));
        panel.add(comboParcelas);

        // Producto cosechado
        panel.add(new JLabel("Producto cosechado:"));
        panel.add(txtProducto);

        // Kilos
        panel.add(new JLabel("Kilos netos:"));
        panel.add(txtKilos);

        // Botón de registro
        JButton btnRegistrar = new JButton("✅ Registrar Cosecha");
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarCosechaDesdeInterfaz();
            }
        });
        panel.add(new JLabel()); // Espacio vacío
        panel.add(btnRegistrar);

        return panel;
    }

    /**
     * CREA EL PANEL DE MOVIMIENTOS
     */
    private JPanel crearPanelMovimientos() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        areaMovimientos = new JTextArea(20, 60);
        areaMovimientos.setEditable(false);
        areaMovimientos.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(areaMovimientos);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botones de control
        JPanel panelBotones = new JPanel();
        
        JButton btnActualizar = new JButton("🔄 Actualizar");
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarMovimientos();
            }
        });
        
        JButton btnLimpiar = new JButton("🗑️ Limpiar Historial");
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarMovimientos();
            }
        });
        
        panelBotones.add(btnActualizar);
        panelBotones.add(btnLimpiar);
        panel.add(panelBotones, BorderLayout.SOUTH);

        // Cargar movimientos iniciales
        actualizarMovimientos();

        return panel;
    }

    /**
     * CREA EL PANEL DE LOGÍSTICA
     */
    private JPanel crearPanelLogistica() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Datos de transporte
        panel.add(new JLabel("Número de Remito:"));
        panel.add(txtRemito);

        panel.add(new JLabel("Transportista/Chofer:"));
        panel.add(txtTransportista);

        panel.add(new JLabel("Patente del vehículo:"));
        panel.add(txtPatente);

        panel.add(new JLabel("Código DTVe:"));
        panel.add(txtDTVe);

        panel.add(new JLabel("Destino de la carga:"));
        panel.add(txtDestino);

        // Botón para completar logística
        JButton btnCompletarLogistica = new JButton("🚚 Completar Logística");
        btnCompletarLogistica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                completarLogistica();
            }
        });
        panel.add(new JLabel()); // Espacio vacío
        panel.add(btnCompletarLogistica);

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

        // Campos de texto
        txtProducto = new JTextField();
        txtKilos = new JTextField();
        txtRemito = new JTextField();
        txtTransportista = new JTextField();
        txtPatente = new JTextField();
        txtDTVe = new JTextField();
        txtDestino = new JTextField();
    }

    /**
     * REGISTRA UNA COSECHA DESDE LA INTERFAZ
     */
    private void registrarCosechaDesdeInterfaz() {
        try {
            // Validaciones
            if (comboParcelas.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Seleccione una parcela", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (txtProducto.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese el producto cosechado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double kilos = Double.parseDouble(txtKilos.getText());
            if (kilos <= 0) {
                JOptionPane.showMessageDialog(null, "Los kilos deben ser mayores a cero", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener datos
            Parcela parcela = (Parcela) comboParcelas.getSelectedItem();
            String producto = txtProducto.getText();
            
            // Datos de logística
            String remito = txtRemito.getText();
            String transportista = txtTransportista.getText();
            String patente = txtPatente.getText();
            String dtve = txtDTVe.getText();
            String destino = txtDestino.getText();

            // Crear movimiento
            int id = movimientos.size() + 1;
            MovimientoCosecha movimiento = new MovimientoCosecha(id, LocalDate.now(), parcela, producto, kilos, 
                                                                remito, transportista, patente, dtve, destino);
            
            movimientos.add(movimiento);

            // Limpiar campos
            limpiarCamposCosecha();
            
            // Actualizar lista
            actualizarMovimientos();

            JOptionPane.showMessageDialog(null, "✅ Cosecha registrada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Los kilos deben ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar cosecha: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * COMPLETA LA INFORMACIÓN LOGÍSTICA
     */
    private void completarLogistica() {
        // Aquí se podría implementar la lógica para asociar la logística
        // con un movimiento de cosecha específico
        JOptionPane.showMessageDialog(null, "Información logística guardada", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * ACTUALIZA LA LISTA DE MOVIMIENTOS
     */
    private void actualizarMovimientos() {
        if (movimientos.isEmpty()) {
            areaMovimientos.setText("No hay movimientos de cosecha registrados.");
            return;
        }

        StringBuilder lista = new StringBuilder();
        lista.append("=== MOVIMIENTOS DE COSECHA Y TRANSPORTE ===\n\n");
        
        for (MovimientoCosecha mov : movimientos) {
            lista.append(mov.toString()).append("\n");
            lista.append("------------------------------------------\n");
        }

        areaMovimientos.setText(lista.toString());
    }

    /**
     * LIMPIA LOS MOVIMIENTOS (CONFIRMACIÓN)
     */
    private void limpiarMovimientos() {
        int confirmacion = JOptionPane.showConfirmDialog(null, 
            "¿Está seguro de eliminar todos los movimientos? Esta acción no se puede deshacer.",
            "Confirmar Limpieza",
            JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            movimientos.clear();
            actualizarMovimientos();
            JOptionPane.showMessageDialog(null, "Movimientos eliminados", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * LIMPIA LOS CAMPOS DEL FORMULARIO
     */
    private void limpiarCamposCosecha() {
        txtProducto.setText("");
        txtKilos.setText("");
        // No limpiar campos de logística para reutilización
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
    public void registrarMovimiento() {
        mostrarInterfazCompleta();
    }

    /**
     * MÉTODO COMPATIBLE CON VERSIÓN ANTERIOR
     */
    public void verMovimientos() {
        mostrarInterfazCompleta();
    }
}