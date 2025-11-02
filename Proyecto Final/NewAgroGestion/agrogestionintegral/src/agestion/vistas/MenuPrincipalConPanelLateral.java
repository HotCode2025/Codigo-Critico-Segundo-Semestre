// [file name]: MenuPrincipalConPanelLateral.java
package agestion.vistas;

import agestion.servicios.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * CLASE PRINCIPAL DEL MENÚ - VERSIÓN CON PANEL LATERAL MODERNO
 * 
 * Esta clase implementa una interfaz moderna con panel lateral fijo
 * que proporciona una experiencia de usuario más profesional y accesible.
 * 
 * Características:
 * - Interfaz tipo aplicación de escritorio
 * - Panel lateral fijo con navegación rápida
 * - Panel de contenido dinámico con CardLayout
 * - Diseño responsivo y profesional
 * - Integración completa con todos los módulos
 * 
 * @author Código Crítico 2025
 * @version 2.0
 */
public class MenuPrincipalConPanelLateral extends JFrame {
    
    // --- GESTORES DE SERVICIOS (Mismos que la versión modal) ---
    private final GestionStock gestorDeStock = new GestionStock();
    private final GestionMaquinaria gestorDeMaquinaria = new GestionMaquinaria();
    private final GestionParcelas gestorDeParcelas = new GestionParcelas();
    private final GestionCampo gestorDeCampo;
    private final GestionRiegoFertilizacion gestorRiegoFert;
    private final GestionFinanciera gestorFinanciero;
    private final GestionCosecha gestorCosecha;
    private final GestionPersonal gestorPersonal;

    // --- COMPONENTES DE LA INTERFAZ GRÁFICA ---
    private JPanel contentPanel;          // Panel de contenido principal
    private CardLayout cardLayout;        // Administrador de layouts para contenido
    private JLabel estadoLabel;           // Etiqueta de estado del sistema

    /**
     * CONSTRUCTOR - INICIALIZA LA INTERFAZ Y LOS GESTORES
     */
    public MenuPrincipalConPanelLateral() {
        // Inicializar gestores con inyección de dependencias
        this.gestorDeCampo = new GestionCampo(gestorDeParcelas, gestorDeStock, gestorDeMaquinaria);
        this.gestorRiegoFert = new GestionRiegoFertilizacion(gestorDeParcelas, gestorDeStock);
        this.gestorFinanciero = new GestionFinanciera();
        this.gestorCosecha = new GestionCosecha(gestorDeParcelas);
        this.gestorPersonal = new GestionPersonal();
        
        System.out.println("✅ Menú con panel lateral inicializado");
        
        // Configurar la interfaz de usuario
        inicializarInterfaz();
    }

    /**
     * INICIALIZA TODOS LOS COMPONENTES DE LA INTERFAZ GRÁFICA
     */
    private void inicializarInterfaz() {
        // CONFIGURACIÓN BÁSICA DE LA VENTANA
        setTitle("Agro Gestión Integral - Panel de Control v2.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1200, 700));
        setMinimumSize(new Dimension(1000, 600));
        
        // Usar BorderLayout para organización general
        setLayout(new BorderLayout());
        
        // CONFIGURAR COMPONENTES PRINCIPALES
        configurarPanelLateral();
        configurarPanelContenido();
        configurarBarraEstado();
        
        // EMPAQUETAR Y MOSTRAR
        pack();
        setLocationRelativeTo(null); // Centrar en pantalla
        
        System.out.println("🖥️  Interfaz gráfica inicializada correctamente");
    }

    /**
     * CONFIGURA EL PANEL LATERAL CON MENÚ DE NAVEGACIÓN
     */
    private void configurarPanelLateral() {
        // PANEL LATERAL PRINCIPAL
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBackground(new Color(50, 54, 57)); // Gris oscuro moderno
        sidePanel.setPreferredSize(new Dimension(280, getHeight()));
        sidePanel.setBorder(new EmptyBorder(20, 15, 20, 15));

        // LOGO O TÍTULO DE LA APLICACIÓN
        JLabel titulo = new JLabel("AGRO GESTIÓN");
        titulo.setForeground(new Color(255, 215, 0)); // Dorado
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        // SUBTÍTULO
        JLabel subtitulo = new JLabel("Panel de Control");
        subtitulo.setForeground(Color.LIGHT_GRAY);
        subtitulo.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        // AGREGAR TÍTULOS AL PANEL
        sidePanel.add(titulo);
        sidePanel.add(subtitulo);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // BOTONES DEL MENÚ PRINCIPAL
        String[] opcionesMenu = {
            "🏠 Inicio", 
            "🌱 Cuaderno de Campo", 
            "🚜 Cosecha y Transporte", 
            "👥 Personal", 
            "💧 Riego y Fertilización", 
            "💰 Finanzas", 
            "📦 Gestión de Stock", 
            "🔧 Control de Maquinaria", 
            "📞 Soporte Técnico", 
            "🚪 Salir"
        };

        // CREAR BOTONES PARA CADA OPCIÓN
        for (String opcion : opcionesMenu) {
            JButton boton = crearBotonMenu(opcion);
            sidePanel.add(boton);
            sidePanel.add(Box.createRigidArea(new Dimension(0, 8))); // Espaciado
        }

        // ESPACIO FLEXIBLE PARA EMPUJAR EL CONTENIDO HACIA ARRIBA
        sidePanel.add(Box.createVerticalGlue());
        
        // INFORMACIÓN DE VERSIÓN EN LA PARTE INFERIOR
        JLabel versionLabel = new JLabel("v2.0 - Código Crítico 2025");
        versionLabel.setForeground(Color.GRAY);
        versionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        versionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidePanel.add(versionLabel);

        // AGREGAR PANEL LATERAL A LA VENTANA
        add(sidePanel, BorderLayout.WEST);
    }

    /**
     * CREA UN BOTÓN DE MENÚ CON ESTILO MODERNO
     */
    private JButton crearBotonMenu(String texto) {
        JButton boton = new JButton(texto);
        
        // CONFIGURACIÓN DE ESTILO
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setMaximumSize(new Dimension(240, 45));
        boton.setPreferredSize(new Dimension(240, 45));
        
        // ESTILOS VISUALES MODERNOS
        boton.setBackground(new Color(70, 130, 180)); // Azul acero
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 149, 237), 1),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        
        // FUENTE MODERNA
        boton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        // EFECTO HOVER (cambio de color al pasar el mouse)
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(100, 149, 237)); // Azul más claro
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(70, 130, 180)); // Volver al original
            }
        });
        
        // ASIGNAR ACCIÓN AL BOTÓN
        boton.addActionListener(new MenuButtonListener(texto));
        
        return boton;
    }

    /**
     * CONFIGURA EL PANEL DE CONTENIDO PRINCIPAL CON CARDLAYOUT
     */
    private void configurarPanelContenido() {
        // CONFIGURAR CARDLAYOUT PARA CONTENIDO DINÁMICO
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(new Color(240, 240, 240)); // Fondo gris claro
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // AGREGAR DIFERENTES "PÁGINAS" AL PANEL DE CONTENIDO
        contentPanel.add(crearPanelInicio(), "INICIO");
        contentPanel.add(crearPanelPlaceholder("Cuaderno de Campo"), "CAMPO");
        contentPanel.add(crearPanelPlaceholder("Cosecha y Transporte"), "COSECHA");
        contentPanel.add(crearPanelPlaceholder("Gestión de Personal"), "PERSONAL");
        contentPanel.add(crearPanelPlaceholder("Riego y Fertilización"), "RIEGO");
        contentPanel.add(crearPanelPlaceholder("Finanzas"), "FINANZAS");
        contentPanel.add(crearPanelPlaceholder("Gestión de Stock"), "STOCK");
        contentPanel.add(crearPanelPlaceholder("Control de Maquinaria"), "MAQUINARIA");

        // AGREGAR PANEL DE CONTENIDO A LA VENTANA
        add(contentPanel, BorderLayout.CENTER);
    }

    /**
     * CREA EL PANEL DE INICIO/BIENVENIDA
     */
    private JPanel crearPanelInicio() {
        JPanel panelInicio = new JPanel(new BorderLayout());
        panelInicio.setBackground(new Color(240, 240, 240));
        panelInicio.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        try {
            // CARGAR Y MOSTRAR BANNER
            ImageIcon bannerOriginal = new ImageIcon("src/images/agro_banner.png");
            if (bannerOriginal.getIconWidth() > 0) {
                Image imagenRedimensionada = bannerOriginal.getImage()
                    .getScaledInstance(600, 200, Image.SCALE_SMOOTH);
                JLabel bannerLabel = new JLabel(new ImageIcon(imagenRedimensionada));
                bannerLabel.setHorizontalAlignment(SwingConstants.CENTER);
                bannerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
                panelInicio.add(bannerLabel, BorderLayout.NORTH);
            }
        } catch (Exception e) {
            System.err.println("⚠️  No se pudo cargar el banner: " + e.getMessage());
        }

        // MENSAJE DE BIENVENIDA
        JLabel mensajeBienvenida = new JLabel(
            "<html><center>"
            + "<h1 style='color: #2E8B57; font-size: 28px;'>Bienvenido a Agro Gestión Integral</h1>"
            + "<h3 style='color: #555; font-size: 18px;'>by Código Crítico 2025 - Versión 2.0</h3>"
            + "<hr style='margin: 30px 0;'>"
            + "<p style='color: #666; font-size: 16px; line-height: 1.6;'>"
            + "Sistema integral de gestión agrícola diseñado para optimizar<br>"
            + "todas las operaciones de su empresa agropecuaria."
            + "</p>"
            + "<p style='color: #777; font-size: 14px; margin-top: 20px;'>"
            + "Seleccione una opción del menú lateral para comenzar"
            + "</p>"
            + "</center></html>",
            SwingConstants.CENTER
        );
        
        panelInicio.add(mensajeBienvenida, BorderLayout.CENTER);

        // PANEL DE ESTADÍSTICAS RÁPIDAS (opcional)
        JPanel panelStats = crearPanelEstadisticas();
        panelInicio.add(panelStats, BorderLayout.SOUTH);

        return panelInicio;
    }

    /**
     * CREA UN PANEL DE ESTADÍSTICAS RÁPIDAS PARA EL INICIO
     */
    private JPanel crearPanelEstadisticas() {
        JPanel panelStats = new JPanel(new GridLayout(1, 4, 15, 0));
        panelStats.setBackground(new Color(220, 220, 220));
        panelStats.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        panelStats.setPreferredSize(new Dimension(0, 100));

        // TARJETAS DE ESTADÍSTICAS
        String[][] estadisticas = {
            {"Parcelas", "12", "🌿"},
            {"Empleados", "8", "👥"},
            {"Máquinas", "5", "🚜"},
            {"Stock", "24", "📦"}
        };

        for (String[] stat : estadisticas) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBackground(Color.WHITE);
            card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
            ));

            JLabel icono = new JLabel(stat[2], SwingConstants.CENTER);
            icono.setFont(new Font("Segoe UI", Font.PLAIN, 24));
            
            JLabel numero = new JLabel(stat[1], SwingConstants.CENTER);
            numero.setFont(new Font("Segoe UI", Font.BOLD, 28));
            numero.setForeground(new Color(70, 130, 180));
            
            JLabel titulo = new JLabel(stat[0], SwingConstants.CENTER);
            titulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            titulo.setForeground(Color.DARK_GRAY);

            card.add(icono, BorderLayout.NORTH);
            card.add(numero, BorderLayout.CENTER);
            card.add(titulo, BorderLayout.SOUTH);

            panelStats.add(card);
        }

        return panelStats;
    }

    /**
     * CREA UN PANEL PLACEHOLDER PARA MÓDULOS NO IMPLEMENTADOS EN LA INTERFAZ
     */
    private JPanel crearPanelPlaceholder(String modulo) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JLabel mensaje = new JLabel(
            "<html><center>"
            + "<h1 style='color: #555; font-size: 24px;'>Módulo: " + modulo + "</h1>"
            + "<p style='color: #777; font-size: 16px; margin-top: 20px;'>"
            + "Este módulo se abrirá en una ventana de diálogo separada.<br>"
            + "La interfaz unificada está en desarrollo para la versión 3.0."
            + "</p>"
            + "</center></html>",
            SwingConstants.CENTER
        );

        panel.add(mensaje, BorderLayout.CENTER);
        return panel;
    }

    /**
     * CONFIGURA LA BARRA DE ESTADO INFERIOR
     */
    private void configurarBarraEstado() {
        JPanel barraEstado = new JPanel(new BorderLayout());
        barraEstado.setBackground(new Color(60, 63, 65));
        barraEstado.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        barraEstado.setPreferredSize(new Dimension(0, 30));

        // ETIQUETA DE ESTADO
        estadoLabel = new JLabel("Sistema listo - Conectado a base de datos H2");
        estadoLabel.setForeground(Color.LIGHT_GRAY);
        estadoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        // INFORMACIÓN DE USUARIO/SESIÓN (placeholder)
        JLabel usuarioLabel = new JLabel("Usuario: Administrador");
        usuarioLabel.setForeground(Color.LIGHT_GRAY);
        usuarioLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        barraEstado.add(estadoLabel, BorderLayout.WEST);
        barraEstado.add(usuarioLabel, BorderLayout.EAST);

        add(barraEstado, BorderLayout.SOUTH);
    }

    /**
     * CLASE INTERNA PARA MANEJAR EVENTOS DE LOS BOTONES DEL MENÚ
     */
    private class MenuButtonListener implements ActionListener {
        private String opcion;
        
        public MenuButtonListener(String opcion) {
            this.opcion = opcion;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("🔘 Botón presionado: " + opcion);
            
            // Actualizar barra de estado
            estadoLabel.setText("Navegando a: " + opcion.replace("🏠", "").replace("🚪", "").trim());
            
            // Procesar la opción seleccionada
            switch (opcion) {
                case "🏠 Inicio":
                    cardLayout.show(contentPanel, "INICIO");
                    break;
                case "🌱 Cuaderno de Campo":
                    mostrarSubmenuCampo();
                    break;
                case "🚜 Cosecha y Transporte":
                    mostrarSubmenuCosecha();
                    break;
                case "👥 Personal":
                    mostrarSubmenuPersonal();
                    break;
                case "💧 Riego y Fertilización":
                    mostrarSubmenuRiegoFert();
                    break;
                case "💰 Finanzas":
                    mostrarSubmenuFinanzas();
                    break;
                case "📦 Gestión de Stock":
                    mostrarSubmenuStock();
                    break;
                case "🔧 Control de Maquinaria":
                    mostrarSubmenuMaquinaria();
                    break;
                case "📞 Soporte Técnico":
                    mostrarSoporteTecnico();
                    break;
                case "🚪 Salir":
                    salirAplicacion();
                    break;
                default:
                    System.out.println("⚠️  Opción no reconocida: " + opcion);
            }
        }
    }

    // =========================================================================
    // MÉTODOS DE SUBMENÚS (SIMILARES A LA VERSIÓN MODAL)
    // =========================================================================

    /**
     * MUESTRA EL SUBMENÚ DE GESTIÓN DE PERSONAL
     */
    private void mostrarSubmenuPersonal() {
        String[] opciones = {
            "Contratar Empleado", 
            "Ver Nómina", 
            "Ver Ficha de Empleado", 
            "Dar de Baja Empleado", 
            "Volver al Panel Principal"
        };
        
        boolean volver = false;
        while(!volver) {
            int seleccion = JOptionPane.showOptionDialog(
                this, 
                "MÓDULO: GESTIÓN DE PERSONAL\n\nGestión completa de recursos humanos:", 
                "Personal (RRHH)", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                null, 
                opciones, 
                opciones[0]
            );
            
            switch (seleccion) {
                case 0: gestorPersonal.contratarEmpleado(); break;
                case 1: gestorPersonal.verNominaPersonal(); break;
                case 2: gestorPersonal.verFichaEmpleado(); break;
                case 3: gestorPersonal.darDeBajaEmpleado(); break;
                case 4: case -1: volver = true; break;
            }
        }
        // Volver al panel de inicio después de cerrar el submenú
        cardLayout.show(contentPanel, "INICIO");
    }

    /**
     * MUESTRA EL SUBMENÚ DE CUADERNO DE CAMPO
     */
    private void mostrarSubmenuCampo() {
        String[] opciones = {
            "1. Registrar Tarea de Campo", 
            "2. Ver Historial de Tareas", 
            "3. Gestionar Parcelas", 
            "Volver al Panel Principal"
        };
        
        boolean volver = false;
        while (!volver) {
            int seleccion = JOptionPane.showOptionDialog(
                this, 
                "MÓDULO: CUADERNO DE CAMPO\n\nRegistro y seguimiento de actividades agrícolas:", 
                "Cuaderno de Campo", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                null, 
                opciones, 
                opciones[0]
            );
            
            switch (seleccion) {
                case 0: gestorDeCampo.registrarNuevaTarea(); break;
                case 1: gestorDeCampo.mostrarHistorialTareas(); break;
                case 2: mostrarSubmenuParcelas(); break;
                case 3: case -1: volver = true; break;
            }
        }
        cardLayout.show(contentPanel, "INICIO");
    }

    /**
     * MUESTRA EL SUBMENÚ DE GESTIÓN DE PARCELAS
     */
    private void mostrarSubmenuParcelas() {
        String[] opciones = {
            "1. Agregar Parcela", 
            "2. Ver Parcelas", 
            "Volver al Menú Anterior"
        };
        
        int seleccion = JOptionPane.showOptionDialog(
            this, 
            "GESTIÓN DE PARCELAS\n\nAdministración de terrenos y cultivos:", 
            "Parcelas", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.INFORMATION_MESSAGE, 
            null, 
            opciones, 
            opciones[0]
        );
        
        switch (seleccion) {
            case 0: gestorDeParcelas.agregarParcela(); break;
            case 1: gestorDeParcelas.mostrarParcelas(); break;
        }
    }

    /**
     * MUESTRA EL SUBMENÚ DE COSECHA Y TRANSPORTE
     */
    private void mostrarSubmenuCosecha() {
        String[] opciones = {
            "Registrar Cosecha/Transporte", 
            "Ver Movimientos", 
            "Volver al Panel Principal"
        };
        
        boolean volver = false;
        while(!volver) {
            int seleccion = JOptionPane.showOptionDialog(
                this, 
                "MÓDULO: COSECHA Y TRANSPORTE\n\nGestión de cosechas y logística:", 
                "Cosecha y Transporte", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                null, 
                opciones, 
                opciones[0]
            );
            
            switch (seleccion) {
                case 0: gestorCosecha.registrarMovimiento(); break;
                case 1: gestorCosecha.verMovimientos(); break;
                case 2: case -1: volver = true; break;
            }
        }
        cardLayout.show(contentPanel, "INICIO");
    }

    /**
     * MUESTRA EL SUBMENÚ DE RIEGO Y FERTILIZACIÓN
     */
    private void mostrarSubmenuRiegoFert() {
        String[] opciones = {
            "Crear Plan de Riego", 
            "Ver Planes de Riego", 
            "Crear Plan de Fertilización", 
            "Ver Planes de Fertilización", 
            "Volver al Panel Principal"
        };
        
        boolean volver = false;
        while(!volver) {
            int seleccion = JOptionPane.showOptionDialog(
                this, 
                "MÓDULO: RIEGO Y FERTILIZACIÓN\n\nPlanificación de recursos hídricos y nutrientes:", 
                "Riego y Fertilización", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                null, 
                opciones, 
                opciones[0]
            );
            
            switch (seleccion) {
                case 0: gestorRiegoFert.crearPlanDeRiego(); break;
                case 1: gestorRiegoFert.verPlanesDeRiego(); break;
                case 2: gestorRiegoFert.crearPlanDeFertilizacion(); break;
                case 3: gestorRiegoFert.verPlanesDeFertilizacion(); break;
                case 4: case -1: volver = true; break;
            }
        }
        cardLayout.show(contentPanel, "INICIO");
    }

    /**
     * MUESTRA EL SUBMENÚ DE FINANZAS
     */
    private void mostrarSubmenuFinanzas() {
        String[] opciones = {
            "Registrar Transacción", 
            "Ver Libro Contable", 
            "Generar Reporte de IVA", 
            "Volver al Panel Principal"
        };
        
        boolean volver = false;
        while(!volver) {
            int seleccion = JOptionPane.showOptionDialog(
                this, 
                "MÓDULO: CONTABILIDAD Y FINANZAS\n\nGestión financiera y contable:", 
                "Finanzas", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                null, 
                opciones, 
                opciones[0]
            );
            
            switch (seleccion) {
                case 0: gestorFinanciero.registrarTransaccion(); break;
                case 1: gestorFinanciero.mostrarLibroContableYBalance(); break;
                case 2: gestorFinanciero.mostrarReporteIva(); break;
                case 3: case -1: volver = true; break;
            }
        }
        cardLayout.show(contentPanel, "INICIO");
    }

    /**
     * MUESTRA EL SUBMENÚ DE GESTIÓN DE STOCK
     */
    private void mostrarSubmenuStock() {
        String[] opcionesStock = {
            "1. Agregar Producto", 
            "2. Ver Stock", 
            "Volver al Panel Principal"
        };
        
        boolean volver = false;
        while (!volver) {
            int seleccion = JOptionPane.showOptionDialog(
                this, 
                "MÓDULO: GESTIÓN DE STOCK\n\nControl de inventario y productos:", 
                "Gestión de Stock", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                null, 
                opcionesStock, 
                opcionesStock[0]
            );
            
            switch (seleccion) {
                case 0: gestorDeStock.agregarProducto(); break;
                case 1: gestorDeStock.mostrarStock(); break;
                case 2: case -1: volver = true; break;
            }
        }
        cardLayout.show(contentPanel, "INICIO");
    }

    /**
     * MUESTRA EL SUBMENÚ DE CONTROL DE MAQUINARIA
     */
    private void mostrarSubmenuMaquinaria() {
        String[] opciones = {
            "1. Agregar Máquina", 
            "2. Ver Flota", 
            "Volver al Panel Principal"
        };
        
        boolean volver = false;
        while (!volver) {
            int seleccion = JOptionPane.showOptionDialog(
                this, 
                "MÓDULO: CONTROL DE MAQUINARIA\n\nGestión de equipos y maquinaria:", 
                "Control de Maquinaria", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                null, 
                opciones, 
                opciones[0]
            );
            
            switch (seleccion) {
                case 0: gestorDeMaquinaria.agregarMaquina(); break;
                case 1: gestorDeMaquinaria.mostrarMaquinaria(); break;
                case 2: case -1: volver = true; break;
            }
        }
        cardLayout.show(contentPanel, "INICIO");
    }

    /**
     * MUESTRA INFORMACIÓN DE SOPORTE TÉCNICO
     */
    private void mostrarSoporteTecnico() {
        String infoSoporte = 
            "EQUIPO DE SOPORTE TÉCNICO - CÓDIGO CRÍTICO 2025\n\n" +
            "🔧 Contactos del equipo de desarrollo:\n\n" +
            "• Mazara Ariel - arielmazara@gmail.com\n" +
            "• Zuñiga Agustina - agustinavictoriazuniga@gmail.com\n" +
            "• Silva Daniel - daniel.dolhartz@gmail.com\n" +
            "• Gonzalez Joel - joelious7@gmail.com\n" +
            "• Baz Samira - bazsamira79@gmail.com\n" +
            "• Mendez Oscar - Oreomendez99@gmail.com\n" +
            "• Mamani Santino - santinomamani25@gmail.com\n" +
            "• Ponce de Leon Damian - poncedeleondamianadolfo@gmail.com\n\n" +
            "📋 Información del sistema:\n" +
            "Versión: 2.0 (Interfaz Panel Lateral)\n" +
            "Fecha: Octubre 2025\n" +
            "Java: 15 + H2 Database + Swing UI\n\n" +
            "⚠️  Para reportar errores o sugerencias, contactar a cualquier miembro del equipo.";
        
        JOptionPane.showMessageDialog(
            this, 
            infoSoporte, 
            "Información de Soporte Técnico", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * CONFIRMA Y CIERRA LA APLICACIÓN
     */
    private void salirAplicacion() {
        int confirmacion = JOptionPane.showConfirmDialog(
            this, 
            "¿Está seguro que desea salir de Agro Gestión Integral?\n\n" +
            "Se cerrará la conexión con la base de datos y todas las ventanas.", 
            "Confirmar Salida", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(
                this, 
                "Gracias por usar Agro Gestión Integral v2.0\n\n" +
                "Desarrollado por Código Crítico 2025", 
                "Hasta luego", 
                JOptionPane.INFORMATION_MESSAGE
            );
            System.out.println("👋 Aplicación cerrada por el usuario");
            System.exit(0);
        }
    }

    /**
     * MÉTODO PRINCIPAL PARA MOSTRAR LA INTERFAZ
     */
    public void mostrarMenu() {
        System.out.println("🖥️  Mostrando interfaz con panel lateral...");
        setVisible(true);
        estadoLabel.setText("Sistema iniciado - Interfaz cargada correctamente");
    }
}