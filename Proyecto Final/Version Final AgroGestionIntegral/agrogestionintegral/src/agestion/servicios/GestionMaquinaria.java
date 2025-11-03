package agestion.servicios;

import agestion.modelo.Maquinaria;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 * GESTIÓN DE MAQUINARIA CON INTERFAZ DE SOLAPAS
 * 
 * Sistema completo para administrar la flota de maquinaria agrícola
 * con interfaz organizada en pestañas.
 * 
 * @author Código Crítico 2025
 * @version 2.0
 */
public class GestionMaquinaria {
    
    private ArrayList<Maquinaria> flota = new ArrayList<>();

    // Componentes de la interfaz
    private JTextField txtNombreMaquina;
    private JComboBox<String> comboEstado;
    private JTextField txtHorasUso;
    private JTextArea areaFlota;
    private JComboBox<Maquinaria> comboMaquinas;
    private JTextField txtNuevoEstado;
    private JTextField txtHorasAgregar;

    /**
     * MUESTRA LA INTERFAZ COMPLETA CON SOLAPAS
     */
    public void mostrarInterfazCompleta() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Gestión de Maquinaria - Control de Flota");
        dialog.setModal(true);
        dialog.setSize(800, 600);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Agregar solapas
        tabbedPane.addTab("➕ Agregar Máquina", crearPanelAgregarMaquina());
        tabbedPane.addTab("📋 Ver Flota", crearPanelVerFlota());
        tabbedPane.addTab("⚙️ Mantenimiento", crearPanelMantenimiento());

        dialog.add(tabbedPane, BorderLayout.CENTER);
        
        // Botones inferiores
        JPanel panelBotones = crearPanelBotones(dialog);
        dialog.add(panelBotones, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    /**
     * CREA EL PANEL PARA AGREGAR MÁQUINAS
     */
    private JPanel crearPanelAgregarMaquina() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        inicializarComponentes();

        // Nombre de la máquina
        panel.add(new JLabel("Nombre de la máquina:"));
        panel.add(txtNombreMaquina);

        // Estado
        panel.add(new JLabel("Estado inicial:"));
        panel.add(comboEstado);

        // Horas de uso
        panel.add(new JLabel("Horas de uso iniciales:"));
        panel.add(txtHorasUso);

        // Botón de agregar
        JButton btnAgregar = new JButton("✅ Agregar Máquina");
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarMaquinaDesdeInterfaz();
            }
        });
        panel.add(new JLabel()); // Espacio vacío
        panel.add(btnAgregar);

        return panel;
    }

    /**
     * CREA EL PANEL PARA VER LA FLOTA
     */
    private JPanel crearPanelVerFlota() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        areaFlota = new JTextArea(20, 50);
        areaFlota.setEditable(false);
        areaFlota.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(areaFlota);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botones de control
        JPanel panelBotones = new JPanel();
        
        JButton btnActualizar = new JButton("🔄 Actualizar Lista");
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarListaFlota();
            }
        });
        
        JButton btnExportar = new JButton("💾 Exportar Lista");
        btnExportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportarListaFlota();
            }
        });
        
        panelBotones.add(btnActualizar);
        panelBotones.add(btnExportar);
        panel.add(panelBotones, BorderLayout.SOUTH);

        // Cargar flota inicial
        actualizarListaFlota();

        return panel;
    }

    /**
     * CREA EL PANEL DE MANTENIMIENTO
     */
    private JPanel crearPanelMantenimiento() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Selección de máquina
        panel.add(new JLabel("Seleccionar máquina:"));
        comboMaquinas = new JComboBox<>();
        actualizarComboMaquinas();
        panel.add(comboMaquinas);

        // Cambio de estado
        panel.add(new JLabel("Nuevo estado:"));
        txtNuevoEstado = new JTextField();
        panel.add(txtNuevoEstado);

        // Agregar horas
        panel.add(new JLabel("Horas a agregar:"));
        txtHorasAgregar = new JTextField();
        panel.add(txtHorasAgregar);

        // Botones de acción
        JButton btnCambiarEstado = new JButton("🔄 Cambiar Estado");
        btnCambiarEstado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarEstadoMaquina();
            }
        });
        panel.add(btnCambiarEstado);

        JButton btnAgregarHoras = new JButton("⏱️ Agregar Horas");
        btnAgregarHoras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarHorasMaquina();
            }
        });
        panel.add(btnAgregarHoras);

        return panel;
    }

    /**
     * INICIALIZA LOS COMPONENTES DE LA INTERFAZ
     */
    private void inicializarComponentes() {
        txtNombreMaquina = new JTextField();
        
        comboEstado = new JComboBox<>(new String[]{"Disponible", "En Mantenimiento", "En Uso"});
        
        txtHorasUso = new JTextField();
    }

    /**
     * AGREGA UNA MÁQUINA DESDE LA INTERFAZ
     */
    private void agregarMaquinaDesdeInterfaz() {
        try {
            // Validaciones
            if (txtNombreMaquina.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese el nombre de la máquina", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double horas = Double.parseDouble(txtHorasUso.getText());
            if (horas < 0) {
                JOptionPane.showMessageDialog(null, "Las horas no pueden ser negativas", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener datos
            String nombre = txtNombreMaquina.getText();
            String estado = (String) comboEstado.getSelectedItem();

            // Crear máquina
            int id = flota.size() + 1;
            Maquinaria nuevaMaquina = new Maquinaria(id, nombre, estado, horas);
            flota.add(nuevaMaquina);

            // Limpiar campos
            limpiarCamposMaquina();
            
            // Actualizar listas
            actualizarListaFlota();
            actualizarComboMaquinas();

            JOptionPane.showMessageDialog(null, "✅ Máquina '" + nombre + "' agregada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Las horas deben ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar máquina: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * ACTUALIZA LA LISTA DE LA FLOTA
     */
    private void actualizarListaFlota() {
        if (flota.isEmpty()) {
            areaFlota.setText("No hay maquinaria registrada en la flota.");
            return;
        }

        StringBuilder lista = new StringBuilder();
        lista.append("=== FLOTA DE MAQUINARIA ===\n\n");
        
        for (Maquinaria maquina : flota) {
            lista.append(maquina.toString()).append("\n");
        }

        areaFlota.setText(lista.toString());
    }

    /**
     * ACTUALIZA EL COMBOBOX DE MÁQUINAS
     */
    private void actualizarComboMaquinas() {
        comboMaquinas.removeAllItems();
        for (Maquinaria maquina : flota) {
            comboMaquinas.addItem(maquina);
        }
    }

    /**
     * CAMBIA EL ESTADO DE UNA MÁQUINA
     */
    private void cambiarEstadoMaquina() {
        try {
            if (comboMaquinas.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Seleccione una máquina", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String nuevoEstado = txtNuevoEstado.getText().trim();
            if (nuevoEstado.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese el nuevo estado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Maquinaria maquina = (Maquinaria) comboMaquinas.getSelectedItem();
            maquina.setEstado(nuevoEstado);

            // Actualizar listas
            actualizarListaFlota();
            
            JOptionPane.showMessageDialog(null, "Estado actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cambiar estado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * AGREGA HORAS DE USO A UNA MÁQUINA
     */
    private void agregarHorasMaquina() {
        try {
            if (comboMaquinas.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Seleccione una máquina", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double horas = Double.parseDouble(txtHorasAgregar.getText());
            if (horas <= 0) {
                JOptionPane.showMessageDialog(null, "Las horas deben ser mayores a cero", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Maquinaria maquina = (Maquinaria) comboMaquinas.getSelectedItem();
            double horasActuales = maquina.getHorasDeUso();
            maquina.setHorasDeUso(horasActuales + horas);

            // Limpiar campo
            txtHorasAgregar.setText("");
            
            // Actualizar listas
            actualizarListaFlota();
            
            JOptionPane.showMessageDialog(null, 
                String.format("Horas agregadas. Total actual: %.2f hs", maquina.getHorasDeUso()), 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Las horas deben ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar horas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * EXPORTA LA LISTA DE FLOTA (SIMULACIÓN)
     */
    private void exportarListaFlota() {
        JOptionPane.showMessageDialog(null, 
            "Función de exportación en desarrollo.\nLa lista se exportará a archivo CSV.", 
            "Exportar", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * LIMPIA LOS CAMPOS DEL FORMULARIO
     */
    private void limpiarCamposMaquina() {
        txtNombreMaquina.setText("");
        txtHorasUso.setText("");
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
    public void agregarMaquina() {
        mostrarInterfazCompleta();
    }

    /**
     * MÉTODO COMPATIBLE CON VERSIÓN ANTERIOR
     */
    public void mostrarMaquinaria() {
        mostrarInterfazCompleta();
    }

    /**
     * MÉTODO COMPATIBLE CON VERSIÓN ANTERIOR - Para otras clases
     */
    public ArrayList<Maquinaria> getFlota() {
        return this.flota;
    }
}