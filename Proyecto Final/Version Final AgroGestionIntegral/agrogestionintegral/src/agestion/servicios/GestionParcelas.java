package agestion.servicios;

import agestion.modelo.Parcela;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * GESTIÓN DE PARCELAS CON INTERFAZ DE SOLAPAS
 * 
 * Sistema completo para administrar parcelas agrícolas con
 * interfaz organizada en pestañas para diferentes operaciones.
 * 
 * @author Código Crítico 2025
 * @version 2.0
 */
public class GestionParcelas {

    private ArrayList<Parcela> listaParcelas = new ArrayList<>();

    // Componentes de la interfaz
    private JTextField txtNombreParcela;
    private JTextField txtTipoCultivo;
    private JTextField txtSuperficie;
    private JTextArea areaParcelas;
    private JTextArea areaEstadisticas;

    /**
     * MUESTRA LA INTERFAZ COMPLETA CON SOLAPAS
     */
    public void mostrarInterfazCompleta() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Gestión de Parcelas - Administración de Terrenos");
        dialog.setModal(true);
        dialog.setSize(800, 600);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Agregar solapas
        tabbedPane.addTab("🌿 Agregar Parcela", crearPanelAgregarParcela());
        tabbedPane.addTab("📋 Lista de Parcelas", crearPanelListaParcelas());
        tabbedPane.addTab("📊 Estadísticas", crearPanelEstadisticas());
        tabbedPane.addTab("🗺️ Mapa de Cultivos", crearPanelMapaCultivos());

        dialog.add(tabbedPane, BorderLayout.CENTER);
        
        // Botones inferiores
        JPanel panelBotones = crearPanelBotones(dialog);
        dialog.add(panelBotones, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    /**
     * CREA EL PANEL PARA AGREGAR PARCELAS
     */
    private JPanel crearPanelAgregarParcela() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        inicializarComponentes();

        // Nombre de la parcela
        panel.add(new JLabel("Nombre de la parcela:"));
        panel.add(txtNombreParcela);

        // Tipo de cultivo
        panel.add(new JLabel("Tipo de cultivo:"));
        panel.add(txtTipoCultivo);

        // Superficie
        panel.add(new JLabel("Superficie (hectáreas):"));
        panel.add(txtSuperficie);

        // Botón de agregar
        JButton btnAgregar = new JButton("✅ Agregar Parcela");
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarParcelaDesdeInterfaz();
            }
        });
        panel.add(new JLabel()); // Espacio vacío
        panel.add(btnAgregar);

        return panel;
    }

    /**
     * CREA EL PANEL DE LISTA DE PARCELAS
     */
    private JPanel crearPanelListaParcelas() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        areaParcelas = new JTextArea(20, 50);
        areaParcelas.setEditable(false);
        areaParcelas.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(areaParcelas);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botones de control
        JPanel panelBotones = new JPanel();
        
        JButton btnActualizar = new JButton("🔄 Actualizar Lista");
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarListaParcelas();
            }
        });
        
        JButton btnExportar = new JButton("💾 Exportar Lista");
        btnExportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportarListaParcelas();
            }
        });
        
        JButton btnLimpiar = new JButton("🗑️ Limpiar Todo");
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarParcelas();
            }
        });
        
        panelBotones.add(btnActualizar);
        panelBotones.add(btnExportar);
        panelBotones.add(btnLimpiar);
        panel.add(panelBotones, BorderLayout.SOUTH);

        // Cargar lista inicial
        actualizarListaParcelas();

        return panel;
    }

    /**
     * CREA EL PANEL DE ESTADÍSTICAS
     */
    private JPanel crearPanelEstadisticas() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        areaEstadisticas = new JTextArea(15, 50);
        areaEstadisticas.setEditable(false);
        areaEstadisticas.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        
        panel.add(new JScrollPane(areaEstadisticas), BorderLayout.CENTER);

        // Botón para actualizar estadísticas
        JButton btnActualizar = new JButton("📊 Actualizar Estadísticas");
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarEstadisticas();
            }
        });
        
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnActualizar);
        panel.add(panelBoton, BorderLayout.SOUTH);

        // Cargar estadísticas iniciales
        actualizarEstadisticas();

        return panel;
    }

    /**
     * CREA EL PANEL DE MAPA DE CULTIVOS (SIMULACIÓN)
     */
    private JPanel crearPanelMapaCultivos() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextArea areaMapa = new JTextArea();
        areaMapa.setEditable(false);
        areaMapa.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        
        // Generar mapa simulado
        String mapa = generarMapaCultivos();
        areaMapa.setText(mapa);
        
        panel.add(new JScrollPane(areaMapa), BorderLayout.CENTER);

        JLabel lblInfo = new JLabel("🗺️ Vista previa del mapa de cultivos (simulación)");
        panel.add(lblInfo, BorderLayout.NORTH);

        return panel;
    }

    /**
     * INICIALIZA LOS COMPONENTES DE LA INTERFAZ
     */
    private void inicializarComponentes() {
        txtNombreParcela = new JTextField();
        txtTipoCultivo = new JTextField();
        txtSuperficie = new JTextField();
    }

    /**
     * AGREGA UNA PARCELA DESDE LA INTERFAZ
     */
    private void agregarParcelaDesdeInterfaz() {
        try {
            // Validaciones
            if (txtNombreParcela.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese el nombre de la parcela", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (txtTipoCultivo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese el tipo de cultivo", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double superficie = Double.parseDouble(txtSuperficie.getText());
            if (superficie <= 0) {
                JOptionPane.showMessageDialog(null, "La superficie debe ser mayor a cero", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener datos
            String nombre = txtNombreParcela.getText();
            String tipoCultivo = txtTipoCultivo.getText();

            // Crear parcela
            int id = listaParcelas.size() + 1;
            Parcela nuevaParcela = new Parcela(id, nombre, superficie, tipoCultivo);
            listaParcelas.add(nuevaParcela);

            // Limpiar campos
            limpiarCamposParcela();
            
            // Actualizar listas
            actualizarListaParcelas();
            actualizarEstadisticas();

            JOptionPane.showMessageDialog(null, "✅ Parcela '" + nombre + "' agregada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La superficie debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar parcela: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * ACTUALIZA LA LISTA DE PARCELAS
     */
    private void actualizarListaParcelas() {
        if (listaParcelas.isEmpty()) {
            areaParcelas.setText("No hay parcelas registradas.");
            return;
        }

        StringBuilder lista = new StringBuilder();
        lista.append("=== LISTA DE PARCELAS ===\n\n");
        
        for (Parcela parcela : listaParcelas) {
            lista.append("ID: ").append(parcela.getId()).append("\n");
            lista.append("Nombre: ").append(parcela.getNombre()).append("\n");
            lista.append("Cultivo: ").append(parcela.getTipoCultivo()).append("\n");
            lista.append("Superficie: ").append(parcela.getSuperficie()).append(" ha\n");
            lista.append("--------------------------------\n");
        }

        areaParcelas.setText(lista.toString());
    }

    /**
     * ACTUALIZA LAS ESTADÍSTICAS
     */
    private void actualizarEstadisticas() {
        if (listaParcelas.isEmpty()) {
            areaEstadisticas.setText("No hay parcelas para calcular estadísticas.");
            return;
        }

        double totalSuperficie = 0;
        int totalParcelas = listaParcelas.size();
        
        // Contar tipos de cultivo
        java.util.Map<String, Integer> cultivosCount = new java.util.HashMap<>();
        java.util.Map<String, Double> cultivosSuperficie = new java.util.HashMap<>();
        
        for (Parcela parcela : listaParcelas) {
            totalSuperficie += parcela.getSuperficie();
            
            String cultivo = parcela.getTipoCultivo();
            cultivosCount.put(cultivo, cultivosCount.getOrDefault(cultivo, 0) + 1);
            cultivosSuperficie.put(cultivo, cultivosSuperficie.getOrDefault(cultivo, 0.0) + parcela.getSuperficie());
        }

        // Construir estadísticas
        StringBuilder stats = new StringBuilder();
        stats.append("=== ESTADÍSTICAS DE PARCELAS ===\n\n");
        stats.append("Total de parcelas: ").append(totalParcelas).append("\n");
        stats.append("Superficie total: ").append(String.format("%.2f", totalSuperficie)).append(" ha\n");
        stats.append("Superficie promedio: ").append(String.format("%.2f", totalSuperficie / totalParcelas)).append(" ha\n\n");
        
        stats.append("Distribución por cultivo:\n");
        for (String cultivo : cultivosCount.keySet()) {
            int count = cultivosCount.get(cultivo);
            double sup = cultivosSuperficie.get(cultivo);
            double porcentaje = (sup / totalSuperficie) * 100;
            
            stats.append(String.format("- %s: %d parcelas (%.2f ha - %.1f%%)\n", 
                                     cultivo, count, sup, porcentaje));
        }

        areaEstadisticas.setText(stats.toString());
    }

    /**
     * GENERA UN MAPA SIMULADO DE CULTIVOS
     */
    private String generarMapaCultivos() {
        if (listaParcelas.isEmpty()) {
            return "No hay parcelas para mostrar en el mapa.";
        }

        StringBuilder mapa = new StringBuilder();
        mapa.append("=== MAPA DE CULTIVOS (SIMULACIÓN) ===\n\n");
        
        // Mapa simple basado en texto
        for (Parcela parcela : listaParcelas) {
            String simbolo = obtenerSimboloCultivo(parcela.getTipoCultivo());
            mapa.append(String.format("[%s] %s - %s (%.1f ha)\n", 
                                    simbolo, parcela.getNombre(), 
                                    parcela.getTipoCultivo(), parcela.getSuperficie()));
        }
        
        mapa.append("\nLeyenda:\n");
        mapa.append("🌿 = Viñedo\n");
        mapa.append("🌽 = Cereal\n");
        mapa.append("🌳 = Frutal\n");
        mapa.append("🥬 = Hortaliza\n");
        mapa.append("❓ = Otro\n");

        return mapa.toString();
    }

    /**
     * OBTIENE SÍMBOLO PARA EL TIPO DE CULTIVO
     */
    private String obtenerSimboloCultivo(String tipoCultivo) {
        if (tipoCultivo.toLowerCase().contains("viñedo") || tipoCultivo.toLowerCase().contains("uva")) {
            return "🌿";
        } else if (tipoCultivo.toLowerCase().contains("cereal") || tipoCultivo.toLowerCase().contains("trigo") || tipoCultivo.toLowerCase().contains("maíz")) {
            return "🌽";
        } else if (tipoCultivo.toLowerCase().contains("frutal") || tipoCultivo.toLowerCase().contains("durazno") || tipoCultivo.toLowerCase().contains("manzano")) {
            return "🌳";
        } else if (tipoCultivo.toLowerCase().contains("hortaliza") || tipoCultivo.toLowerCase().contains("lechuga") || tipoCultivo.toLowerCase().contains("tomate")) {
            return "🥬";
        } else {
            return "❓";
        }
    }

    /**
     * EXPORTA LA LISTA DE PARCELAS (SIMULACIÓN)
     */
    private void exportarListaParcelas() {
        JOptionPane.showMessageDialog(null, 
            "Función de exportación en desarrollo.\nLa lista se exportará a archivo CSV.", 
            "Exportar", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * LIMPIA TODAS LAS PARCELAS (CONFIRMACIÓN)
     */
    private void limpiarParcelas() {
        if (listaParcelas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay parcelas para limpiar.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(null, 
            "¿Está seguro de eliminar TODAS las parcelas?\nEsta acción no se puede deshacer.",
            "Confirmar Limpieza Total",
            JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            listaParcelas.clear();
            actualizarListaParcelas();
            actualizarEstadisticas();
            JOptionPane.showMessageDialog(null, "Todas las parcelas han sido eliminadas.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * LIMPIA LOS CAMPOS DEL FORMULARIO
     */
    private void limpiarCamposParcela() {
        txtNombreParcela.setText("");
        txtTipoCultivo.setText("");
        txtSuperficie.setText("");
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
    public void agregarParcela() {
        mostrarInterfazCompleta();
    }

    /**
     * MÉTODO COMPATIBLE CON VERSIÓN ANTERIOR
     */
    public void mostrarParcelas() {
        mostrarInterfazCompleta();
    }

    /**
     * MÉTODO COMPATIBLE CON VERSIÓN ANTERIOR - Para otras clases
     */
    public ArrayList<Parcela> getListaParcelas() {
        return this.listaParcelas;
    }
}