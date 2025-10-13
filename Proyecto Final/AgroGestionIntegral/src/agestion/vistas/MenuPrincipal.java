package agestion.vistas;

import agestion.servicios.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
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
    private final GestionFinanciera gestorFinanciero;
    private final GestionCosecha gestorCosecha;
    private final GestionPersonal gestorPersonal;

    public MenuPrincipal() {
        this.gestorDeCampo = new GestionCampo(gestorDeParcelas, gestorDeStock, gestorDeMaquinaria);
        this.gestorRiegoFert = new GestionRiegoFertilizacion(gestorDeParcelas, gestorDeStock);
        this.gestorFinanciero = new GestionFinanciera();
        this.gestorCosecha = new GestionCosecha(gestorDeParcelas);
        this.gestorPersonal = new GestionPersonal();
    }
    
    public void mostrarMenu() {
        boolean salir = false;
        while (!salir) {
            ImageIcon bannerOriginal = new ImageIcon("src/images/agro_banner.png");
            int nuevoAncho = 500;
            int nuevoAlto = 170;
            Image imagenOriginal = bannerOriginal.getImage();
            Image imagenRedimensionada = imagenOriginal.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
            ImageIcon bannerFinal = new ImageIcon(imagenRedimensionada);
            JLabel imagenLabel = new JLabel(bannerFinal);
            imagenLabel.setBorder(BorderFactory.createEmptyBorder());
            imagenLabel.setHorizontalAlignment(SwingConstants.CENTER);
            
            JLabel mensajeLabel = new JLabel("Bienvenido a Agro Gestión Integral by Código Crítico 2025 - Versión 1.0", SwingConstants.CENTER);
            
            JPanel panelPrincipal = new JPanel(new BorderLayout(0, 15));
            panelPrincipal.setBackground(new Color(60, 63, 65));
            panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            panelPrincipal.add(imagenLabel, BorderLayout.NORTH);
            panelPrincipal.add(mensajeLabel, BorderLayout.CENTER);

            String[] nombresOpciones = {"1. Cuaderno de Campo", "2. Cosecha y Transporte", "3. Personal", 
                                        "4. Riego y Fertilización", "5. Finanzas", "6. Gestión de Stock", 
                                        "7. Control de Maquinaria", "8. Soporte Técnico", "9. Salir"};
            JPanel panelBotones = new JPanel(new GridLayout(0, 3, 10, 10));
            panelBotones.setOpaque(false);
            
            final int[] seleccion = {-1}; 
            
            JDialog dialogo = new JDialog();
            dialogo.setModal(true);
            dialogo.setTitle("Menú Principal - Agro Gestión Integral");

            for (int i = 0; i < nombresOpciones.length; i++) {
                JButton boton = new JButton(nombresOpciones[i]);
                final int indiceBoton = i;
                
                boton.addActionListener(e -> {
                    seleccion[0] = indiceBoton;
                    dialogo.dispose();
                });
                panelBotones.add(boton);
            }
            
            panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
            
            dialogo.setContentPane(panelPrincipal);
            dialogo.pack();
            dialogo.setLocationRelativeTo(null);
            dialogo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialogo.setVisible(true);

            switch (seleccion[0]) {
                case 0: mostrarSubmenuCampo(); break;
                case 1: mostrarSubmenuCosecha(); break;
                case 2: mostrarSubmenuPersonal(); break;
                case 3: mostrarSubmenuRiegoFert(); break;
                case 4: mostrarSubmenuFinanzas(); break;
                case 5: mostrarSubmenuStock(); break;
                case 6: mostrarSubmenuMaquinaria(); break;
                case 7:
                    // --- TEXTO DE SOPORTE ACTUALIZADO ---
                    String infoSoporte = "Equipo de Soporte Técnico - Código Crítico 2025\n\n" +
                                         " - Mazara Ariel - arielmazara@gmail.com\n" +
                                         " - Zuñiga Agustina - agustinavictoriazuniga@gmail.com\n" +
                                         " - Silva Daniel - daniel.dolhartz@gmail.com\n" +
                                         " - Gonzalez Joel - joelious7@gmail.com\n" +
                                         " - Baz Samira - bazsamira79@gmail.com\n" +
                                         " - Mendez Oscar - Oreomendez99@gmail.com\n" +
                                         " - Mamani Santino - santinomamani25@gmail.com \n" +
                                         " - Ponce de Leon Damian - poncedeleondamianadolfo@gmail.com\n\n" +
                                         "Versión del Software: 1.0 Fecha Última Revisión 10-2025";
                    JOptionPane.showMessageDialog(null, infoSoporte, "Información de Soporte", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 8: case -1: 
                    salir = true; 
                    break;
            }
        }
        JOptionPane.showMessageDialog(null, "Gracias por usar Agro Gestión Integral.", "Hasta luego", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // ... (TODOS LOS DEMÁS SUBMENÚS NO CAMBIAN) ...
    
    private void mostrarSubmenuPersonal() {
        String[] opciones = {"Contratar Empleado", "Ver Nómina", "Ver Ficha de Empleado", "Dar de Baja Empleado", "Volver"};
        boolean volver = false;
        while(!volver) {
            int seleccion = JOptionPane.showOptionDialog(null, "Módulo: Gestión de Personal", "Personal (RRHH)", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
            switch (seleccion) {
                case 0: gestorPersonal.contratarEmpleado(); break;
                case 1: gestorPersonal.verNominaPersonal(); break;
                case 2: gestorPersonal.verFichaEmpleado(); break;
                case 3: gestorPersonal.darDeBajaEmpleado(); break;
                case 4: case -1: volver = true; break;
            }
        }
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

    private void mostrarSubmenuCosecha() {
        String[] opciones = {"Registrar Cosecha/Transporte", "Ver Movimientos", "Volver"};
        boolean volver = false;
        while(!volver) {
            int seleccion = JOptionPane.showOptionDialog(null, "Módulo: Cosecha y Transporte", "Cosecha y Transporte", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
            switch (seleccion) {
                case 0: gestorCosecha.registrarMovimiento(); break;
                case 1: gestorCosecha.verMovimientos(); break;
                case 2: case -1: volver = true; break;
            }
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
    
    private void mostrarSubmenuFinanzas() {
        String[] opciones = {"Registrar Transacción", "Ver Libro Contable", "Generar Reporte de IVA", "Volver"};
        boolean volver = false;
        while(!volver) {
            int seleccion = JOptionPane.showOptionDialog(null, "Módulo: Contabilidad y Finanzas", "Finanzas", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
            switch (seleccion) {
                case 0: gestorFinanciero.registrarTransaccion(); break;
                case 1: gestorFinanciero.mostrarLibroContableYBalance(); break;
                case 2: gestorFinanciero.mostrarReporteIva(); break;
                case 3: case -1: volver = true; break;
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