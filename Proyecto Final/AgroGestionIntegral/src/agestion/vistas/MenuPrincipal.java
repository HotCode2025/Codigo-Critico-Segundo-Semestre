package agestion.vistas;

import agestion.servicios.GestionCampo;
import agestion.servicios.GestionMaquinaria;
import agestion.servicios.GestionParcelas;
import agestion.servicios.GestionRiegoFertilizacion;
import agestion.servicios.GestionStock;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MenuPrincipal {

    // --- Atributos (Todos los Gestores) ---
    private final GestionStock gestorDeStock = new GestionStock();
    private final GestionMaquinaria gestorDeMaquinaria = new GestionMaquinaria();
    private final GestionParcelas gestorDeParcelas = new GestionParcelas();
    private final GestionCampo gestorDeCampo;
    private final GestionRiegoFertilizacion gestorRiegoFert;

    public MenuPrincipal() {
        this.gestorDeCampo = new GestionCampo(gestorDeParcelas, gestorDeStock, gestorDeMaquinaria);
        this.gestorRiegoFert = new GestionRiegoFertilizacion(gestorDeParcelas, gestorDeStock);
    }
    
    public void mostrarMenu() {
        // --- CREACIÓN DEL PANEL PERSONALIZADO PARA EL MENÚ ---
        ImageIcon bannerOriginal = new ImageIcon("src/images/agro_banner.png");
        int nuevoAncho = 500;
        int nuevoAlto = 170;
        Image imagenOriginal = bannerOriginal.getImage();
        Image imagenRedimensionada = imagenOriginal.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
        ImageIcon bannerFinal = new ImageIcon(imagenRedimensionada);
        JLabel imagenLabel = new JLabel(bannerFinal);
        imagenLabel.setBorder(BorderFactory.createEmptyBorder());
        imagenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel mensajeLabel = new JLabel("Bienvenido a Agro Gestión Integral", SwingConstants.CENTER);
        JPanel panelMensaje = new JPanel(new BorderLayout(0, 15));
        panelMensaje.setBackground(new Color(60, 63, 65));
        panelMensaje.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelMensaje.add(imagenLabel, BorderLayout.NORTH);
        panelMensaje.add(mensajeLabel, BorderLayout.CENTER);

        boolean salir = false;
        while (!salir) {
            // --- CAMBIO EN LAS OPCIONES DEL MENÚ ---
            String[] opciones = {"1. Cuaderno de Campo", "2. Riego y Fertilización", "3. Gestión de Stock", "4. Control de Maquinaria", "5. Salir"};
            
            int seleccion = JOptionPane.showOptionDialog(null, panelMensaje, "Menú Principal - Agro Gestión Integral", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            switch (seleccion) {
                case 0: mostrarSubmenuCampo(); break;
                case 1: mostrarSubmenuRiegoFert(); break;
                case 2: mostrarSubmenuStock(); break;
                case 3: mostrarSubmenuMaquinaria(); break;
                case 4: case -1: salir = true; break;
            }
        }
        JOptionPane.showMessageDialog(null, "Gracias por usar Agro Gestión Integral.", "Hasta luego", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void mostrarSubmenuCampo() {
        String[] opciones = {"1. Registrar Tarea de Campo", "2. Ver Historial de Tareas", "3. Gestionar Parcelas", "Volver"};
        boolean volver = false;
        while (!volver) {
            int seleccion = JOptionPane.showOptionDialog(null, "Módulo: Cuaderno de Campo", "Cuaderno de Campo", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
            switch (seleccion) {
                case 0: gestorDeCampo.registrarNuevaTarea(); break;
                case 1: gestorDeCampo.mostrarHistorialTareas(); break;
                case 2: mostrarSubmenuParcelas(); break;
                case 3: case -1: volver = true; break;
            }
        }
    }
    
    private void mostrarSubmenuParcelas() {
        String[] opciones = {"1. Agregar Parcela", "2. Ver Parcelas", "Volver"};
        int seleccion = JOptionPane.showOptionDialog(null, "Gestión de Parcelas", "Parcelas", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
        switch (seleccion) {
            case 0: gestorDeParcelas.agregarParcela(); break;
            case 1: gestorDeParcelas.mostrarParcelas(); break;
        }
    }

    private void mostrarSubmenuRiegoFert() {
        String[] opciones = {"Crear Plan de Riego", "Ver Planes de Riego", "Crear Plan de Fertilización", "Ver Planes de Fertilización", "Volver"};
        boolean volver = false;
        while(!volver) {
            int seleccion = JOptionPane.showOptionDialog(null, "Módulo: Riego y Fertilización", "Riego y Fertilización", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
            switch (seleccion) {
                case 0: gestorRiegoFert.crearPlanDeRiego(); break;
                case 1: gestorRiegoFert.verPlanesDeRiego(); break;
                case 2: gestorRiegoFert.crearPlanDeFertilizacion(); break;
                case 3: gestorRiegoFert.verPlanesDeFertilizacion(); break;
                case 4: case -1: volver = true; break;
            }
        }
    }
    
    private void mostrarSubmenuStock() {
        String[] opcionesStock = {"1. Agregar Producto", "2. Ver Stock", "Volver"};
        boolean volver = false;
        while (!volver) {
            int seleccion = JOptionPane.showOptionDialog(null, "Módulo: Gestión de Stock", "Gestión de Stock", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcionesStock, opcionesStock[0]);
            switch (seleccion) {
                case 0: gestorDeStock.agregarProducto(); break;
                case 1: gestorDeStock.mostrarStock(); break;
                case 2: case -1: volver = true; break;
            }
        }
    }
    
    private void mostrarSubmenuMaquinaria() {
        String[] opciones = {"1. Agregar Máquina", "2. Ver Flota", "Volver"};
        boolean volver = false;
        while (!volver) {
            int seleccion = JOptionPane.showOptionDialog(null, "Módulo: Control de Maquinaria", "Control de Maquinaria", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
            switch (seleccion) {
                case 0: gestorDeMaquinaria.agregarMaquina(); break;
                case 1: gestorDeMaquinaria.mostrarMaquinaria(); break;
                case 2: case -1: volver = true; break;
            }
        }
    }
}