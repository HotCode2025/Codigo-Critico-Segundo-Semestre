package agestion.servicios;

import agestion.modelo.Parcela;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 * GESTIÓN DE PARCELAS CON INTERFAZ DE SOLAPAS Y FONDO NEGRO
 */
public class GestionParcelas {

    private ArrayList<Parcela> listaParcelas = new ArrayList<>();

    // Componentes de la interfaz
    private JTextField txtNombreParcela;
    private JTextField txtTipoCultivo;
    private JTextField txtSuperficie;
    private JTextArea areaParcelas;
    private JTextArea areaEstadisticas;

    public void mostrarInterfazCompleta() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Gestión de Parcelas - Administración de Terrenos");
        dialog.setModal(true);
        dialog.setSize(1000, 700); // Tamaño aumentado
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());
        
        // FONDO NEGRO PARA EL DIÁLOGO PRINCIPAL
        dialog.getContentPane().setBackground(Color.BLACK);

        JTabbedPane tabbedPane = new JTabbedPane();
        // FONDO NEGRO PARA EL TABBEDPANE
        tabbedPane.setBackground(Color.BLACK);
        tabbedPane.setForeground(Color.WHITE); // Texto blanco en pestañas
        
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

    private JPanel crearPanelAgregarParcela() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.BLACK); // FONDO NEGRO

        inicializarComponentes();

        // Configurar colores para los componentes
        JLabel lblNombre = new JLabel("Nombre de la parcela:");
        lblNombre.setForeground(Color.WHITE);
        panel.add(lblNombre);
        txtNombreParcela.setBackground(Color.DARK_GRAY);
        txtNombreParcela.setForeground(Color.WHITE);
        panel.add(txtNombreParcela);

        JLabel lblCultivo = new JLabel("Tipo de cultivo:");
        lblCultivo.setForeground(Color.WHITE);
        panel.add(lblCultivo);
        txtTipoCultivo.setBackground(Color.DARK_GRAY);
        txtTipoCultivo.setForeground(Color.WHITE);
        panel.add(txtTipoCultivo);

        JLabel lblSuperficie = new JLabel("Superficie (hectáreas):");
        lblSuperficie.setForeground(Color.WHITE);
        panel.add(lblSuperficie);
        txtSuperficie.setBackground(Color.DARK_GRAY);
        txtSuperficie.setForeground(Color.WHITE);
        panel.add(txtSuperficie);

        // Botón de agregar con estilo oscuro
        JButton btnAgregar = new JButton("✅ Agregar Parcela");
        btnAgregar.setBackground(new Color(0, 100, 0)); // Verde oscuro
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFocusPainted(false);
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

    private JPanel crearPanelListaParcelas() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.BLACK); // FONDO NEGRO

        areaParcelas = new JTextArea(20, 50);
        areaParcelas.setEditable(false);
        areaParcelas.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaParcelas.setBackground(Color.DARK_GRAY); // Fondo oscuro para el área de texto
        areaParcelas.setForeground(Color.WHITE); // Texto blanco
        areaParcelas.setCaretColor(Color.WHITE); // Cursor blanco
        
        JScrollPane scrollPane = new JScrollPane(areaParcelas);
        scrollPane.getViewport().setBackground(Color.DARK_GRAY);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botones de control con estilo oscuro
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Color.BLACK);
        
        JButton btnActualizar = crearBotonOscuro("🔄 Actualizar Lista");
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarListaParcelas();
            }
        });
        
        JButton btnExportar = crearBotonOscuro("💾 Exportar Lista");
        btnExportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportarListaParcelas();
            }
        });
        
        JButton btnLimpiar = crearBotonOscuro("🗑️ Limpiar Todo");
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

    private JPanel crearPanelEstadisticas() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.BLACK); // FONDO NEGRO

        areaEstadisticas = new JTextArea(15, 50);
        areaEstadisticas.setEditable(false);
        areaEstadisticas.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaEstadisticas.setBackground(Color.DARK_GRAY);
        areaEstadisticas.setForeground(Color.WHITE);
        areaEstadisticas.setCaretColor(Color.WHITE);
        
        panel.add(new JScrollPane(areaEstadisticas), BorderLayout.CENTER);

        // Botón para actualizar estadísticas
        JButton btnActualizar = crearBotonOscuro("📊 Actualizar Estadísticas");
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarEstadisticas();
            }
        });
        
        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(Color.BLACK);
        panelBoton.add(btnActualizar);
        panel.add(panelBoton, BorderLayout.SOUTH);

        // Cargar estadísticas iniciales
        actualizarEstadisticas();

        return panel;
    }

    private JPanel crearPanelMapaCultivos() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.BLACK); // FONDO NEGRO

        JTextArea areaMapa = new JTextArea();
        areaMapa.setEditable(false);
        areaMapa.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaMapa.setBackground(Color.DARK_GRAY);
        areaMapa.setForeground(Color.WHITE);
        areaMapa.setCaretColor(Color.WHITE);
        
        // Generar mapa simulado
        String mapa = generarMapaCultivos();
        areaMapa.setText(mapa);
        
        panel.add(new JScrollPane(areaMapa), BorderLayout.CENTER);

        JLabel lblInfo = new JLabel("🗺️ Vista previa del mapa de cultivos (simulación)");
        lblInfo.setForeground(Color.WHITE);
        panel.add(lblInfo, BorderLayout.NORTH);

        return panel;
    }

    // MÉTODO AUXILIAR PARA CREAR BOTONES CON ESTILO OSCURO
    private JButton crearBotonOscuro(String texto) {
        JButton boton = new JButton(texto);
        boton.setBackground(new Color(50, 50, 50)); // Gris oscuro
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        
        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(70, 70, 70));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(50, 50, 50));
            }
        });
        
        return boton;
    }

    // ... (el resto de los métodos se mantienen igual, solo cambia la estética)
    private void inicializarComponentes() {
        txtNombreParcela = new JTextField();
        txtTipoCultivo = new JTextField();
        txtSuperficie = new JTextField();
    }

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

    private void exportarListaParcelas() {
        JOptionPane.showMessageDialog(null, 
            "Función de exportación en desarrollo.\nLa lista se exportará a archivo CSV.", 
            "Exportar", JOptionPane.INFORMATION_MESSAGE);
    }

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

    private void limpiarCamposParcela() {
        txtNombreParcela.setText("");
        txtTipoCultivo.setText("");
        txtSuperficie.setText("");
    }

    private JPanel crearPanelBotones(JDialog dialog) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        
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