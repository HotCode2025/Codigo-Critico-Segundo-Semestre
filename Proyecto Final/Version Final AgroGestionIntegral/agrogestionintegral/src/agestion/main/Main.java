// Main.java - VERSIÓN CORREGIDA
package agestion.main;

import agestion.dao.DatabaseConnection;
import agestion.servicios.*;
import java.awt.*;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import javax.swing.*;

/**
 * APLICACIÓN PRINCIPAL - AGRO GESTIÓN INTEGRAL v2.0
 * 
 * Sistema completo de gestión agrícola con interfaz moderna y panel lateral.
 * Versión corregida para problemas de compilación.
 * 
 * @author Código Crítico 2025
 * @version 2.2 - Corregido problemas de compilación
 */
public class Main {

    // Servicios de la aplicación
    private static GestionFinanciera gestionFinanciera;
    private static GestionMaquinaria gestionMaquinaria;
    private static GestionParcelas gestionParcelas;
    private static GestionStock gestionStock;
    private static GestionCampo gestionCampo;
    private static GestionRiegoFertilizacion gestionRiegoFertilizacion;
    private static GestionCosecha gestionCosecha;
    private static GestionPersonal gestionPersonal;

    // Componentes de la interfaz
    private static JFrame frame;
    private static JPanel panelLateral;
    private static JPanel panelContenido;
    private static CardLayout cardLayout;

    // Estado de la aplicación
    private static boolean modoSimulacion = false;

    /**
     * MÉTODO PRINCIPAL
     */
    public static void main(String[] args) {
        System.out.println("🚀 Iniciando Agro Gestión Integral v2.0 (Panel Lateral)...");

        // Verificar requisitos del sistema
        if (!verificarRequisitosSistema()) {
            return;
        }

        // Configurar apariencia visual moderna
        configurarApariencia();

        // Inicializar base de datos (o modo simulación)
        if (!inicializarBaseDatos()) {
            // Si no se pudo inicializar la base de datos, mostrar opciones
            mostrarOpcionesBaseDatosFallida();
            return;
        }

        // Inicializar servicios
        inicializarServicios();

        // Crear y mostrar interfaz gráfica
        SwingUtilities.invokeLater(() -> {
            crearInterfazGrafica();
            mostrarInterfaz();
        });
    }

    /**
     * VERIFICA LOS REQUISITOS DEL SISTEMA
     */
    private static boolean verificarRequisitosSistema() {
        System.out.println("🔍 Verificando requisitos del sistema...");

        // Verificar versión de Java
        String javaVersion = System.getProperty("java.version");
        System.out.println("☕ Versión de Java: " + javaVersion);

        // Verificar sistema operativo
        String osName = System.getProperty("os.name");
        String osArch = System.getProperty("os.arch");
        System.out.println("💻 Sistema operativo: " + osName + " (" + osArch + ")");

        // Verificar memoria
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage();
        MemoryUsage nonHeapUsage = memoryBean.getNonHeapMemoryUsage();

        long maxMemory = heapUsage.getMax() / (1024 * 1024);
        long totalMemory = heapUsage.getCommitted() / (1024 * 1024);
        long freeMemory = (heapUsage.getCommitted() - heapUsage.getUsed()) / (1024 * 1024);

        System.out.println("🧠 Memoria máxima: " + maxMemory + " MB");
        System.out.println("💾 Memoria total: " + totalMemory + " MB");
        System.out.println("🆓 Memoria libre: " + freeMemory + " MB");

        // Verificación mínima de memoria
        if (maxMemory < 512) {
            System.err.println("❌ Memoria insuficiente. Se requieren al menos 512 MB.");
            JOptionPane.showMessageDialog(null,
                "Memoria insuficiente.\nSe requieren al menos 512 MB de memoria.\n\nMemoria disponible: " + maxMemory + " MB",
                "Error de Memoria",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        System.out.println("✅ Requisitos del sistema verificados correctamente");
        return true;
    }

    /**
     * CONFIGURA LA APARIENCIA VISUAL MODERNA - VERSIÓN CORREGIDA
     */
    private static void configurarApariencia() {
        System.out.println("🎨 Configurando apariencia visual moderna...");

        try {
            // CORRECCIÓN: Usar getSystemLookAndFeelClassName() en lugar de getSystemLookAndFeel()
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            // Configurar fuentes modernas
            Font fuenteDefault = new Font("Segoe UI", Font.PLAIN, 12);
            UIManager.put("Button.font", fuenteDefault);
            UIManager.put("Label.font", fuenteDefault);
            UIManager.put("TextField.font", fuenteDefault);
            UIManager.put("ComboBox.font", fuenteDefault);
            UIManager.put("TextArea.font", new Font("Consolas", Font.PLAIN, 12));
            UIManager.put("TabbedPane.font", fuenteDefault);

            // Configurar colores modernos
            UIManager.put("Panel.background", new Color(240, 240, 240));
            UIManager.put("Button.background", new Color(70, 130, 180));
            UIManager.put("Button.foreground", Color.WHITE);
            UIManager.put("Button.focus", new Color(30, 144, 255));

            System.out.println("✅ Fuentes modernas configuradas para panel lateral");
            System.out.println("✅ Apariencia moderna configurada correctamente");

        } catch (Exception e) {
            System.err.println("⚠️  No se pudo configurar la apariencia moderna: " + e.getMessage());
            // Continuar incluso si falla la apariencia
        }
    }

    /**
     * INICIALIZA LA BASE DE DATOS O MODO SIMULACIÓN - VERSIÓN CORREGIDA
     */
    private static boolean inicializarBaseDatos() {
        System.out.println("🗄️  Inicializando base de datos...");

        try {
            // Obtener instancia de la conexión (esto inicializa la base de datos)
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();

            // CORRECCIÓN: Verificar modo simulación de manera segura
            try {
                // Intentar usar el método isSimulationMode si existe
                java.lang.reflect.Method method = dbConnection.getClass().getMethod("isSimulationMode");
                modoSimulacion = (Boolean) method.invoke(dbConnection);
                if (modoSimulacion) {
                    System.out.println("🔧 Modo simulación activado - Sin base de datos real");
                    System.out.println("💡 Los datos se guardarán en memoria y se perderán al cerrar la aplicación");
                    return true;
                }
            } catch (NoSuchMethodException e) {
                // El método no existe, asumir modo normal
                modoSimulacion = false;
                System.out.println("ℹ️  Método isSimulationMode no disponible, asumiendo modo normal");
            }

            // Verificar si la conexión está activa
            if (dbConnection.isConnectionActive()) {
                System.out.println("✅ Base de datos inicializada correctamente");
                return true;
            } else {
                System.err.println("❌ La conexión a la base de datos no está activa");
                return false;
            }

        } catch (Exception e) {
            System.err.println("❌ Error crítico al inicializar la base de datos: " + e.getMessage());
            
            // Mostrar diagnóstico detallado
            System.err.println("\n--- DIAGNÓSTICO DE BASE DE DATOS ---");
            System.err.println("Posibles causas:");
            System.err.println("1. El archivo sqlite-jdbc-3.42.0.0.jar no está en el classpath");
            System.err.println("2. No hay permisos de escritura en la carpeta del proyecto");
            System.err.println("3. La base de datos está corrupta");
            System.err.println("4. Otra aplicación está usando la base de datos");
            System.err.println("5. El driver JDBC no se puede cargar");
            System.err.println("");
            System.err.println("Solución:");
            System.err.println("- Descargar sqlite-jdbc-3.42.0.0.jar desde:");
            System.err.println("  https://github.com/xerial/sqlite-jdbc/releases");
            System.err.println("- Agregar el JAR al classpath del proyecto");
            System.err.println("- Verificar permisos de la carpeta del proyecto");
            System.err.println("--------------------------------------");
            
            return false;
        }
    }

    /**
     * MUESTRA OPCIONES CUANDO FALLA LA INICIALIZACIÓN DE LA BASE DE DATOS
     */
    private static void mostrarOpcionesBaseDatosFallida() {
        System.err.println("❌ No se pudo inicializar la base de datos. La aplicación no puede continuar.");

        // Mostrar mensaje con opciones al usuario
        Object[] opciones = {"Usar Modo Simulación", "Solucionar Problema", "Salir"};
        int eleccion = JOptionPane.showOptionDialog(null,
            "<html><body style='width: 400px;'>" +
            "<h3>❌ Error de Base de Datos</h3>" +
            "<p>No se pudo conectar con la base de datos.</p>" +
            "<p><b>Opciones:</b></p>" +
            "<ul>" +
            "<li><b>Modo Simulación:</b> Usar datos en memoria (se pierden al cerrar)</li>" +
            "<li><b>Solucionar:</b> Instalar drivers y reiniciar</li>" +
            "<li><b>Salir:</b> Cerrar la aplicación</li>" +
            "</ul>" +
            "</body></html>",
            "Error de Base de Datos",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.ERROR_MESSAGE,
            null,
            opciones,
            opciones[0]);

        switch (eleccion) {
            case 0: // Modo Simulación
                usarModoSimulacion();
                break;
            case 1: // Solucionar Problema
                mostrarInstruccionesSolucion();
                break;
            default: // Salir
                System.exit(1);
        }
    }

    /**
     * ACTIVA EL MODO SIMULACIÓN MANUALMENTE
     */
    private static void usarModoSimulacion() {
        modoSimulacion = true;
        System.out.println("🔧 Activando modo simulación manualmente...");
        
        // Inicializar servicios en modo simulación
        inicializarServicios();
        
        // Crear y mostrar interfaz
        SwingUtilities.invokeLater(() -> {
            crearInterfazGrafica();
            personalizarInterfazModoSimulacion();
            mostrarInterfaz();
        });
    }

    /**
     * PERSONALIZA LA INTERFAZ PARA MODO SIMULACIÓN
     */
    private static void personalizarInterfazModoSimulacion() {
        if (frame != null) {
            frame.setTitle("Agro Gestión Integral v2.0 - [MODO SIMULACIÓN]");
            
            // Cambiar color de fondo para indicar modo simulación
            if (panelLateral != null) {
                panelLateral.setBackground(new Color(70, 70, 70)); // Gris más oscuro
            }
            
            // Mostrar advertencia
            JOptionPane.showMessageDialog(frame,
                "<html><body style='width: 400px;'>" +
                "<h3>🔧 Modo Simulación Activado</h3>" +
                "<p>La aplicación está funcionando en <b>modo simulación</b>.</p>" +
                "<p><b>Características:</b></p>" +
                "<ul>" +
                "<li>✅ Todas las funciones disponibles</li>" +
                "<li>✅ Datos de ejemplo precargados</li>" +
                "<li>❌ Los datos se pierden al cerrar</li>" +
                "<li>❌ No se requiere base de datos</li>" +
                "</ul>" +
                "<p><i>Para usar base de datos real, instale los drivers JDBC.</i></p>" +
                "</body></html>",
                "Modo Simulación",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * MUESTRA INSTRUCCIONES PARA SOLUCIONAR EL PROBLEMA
     */
    private static void mostrarInstruccionesSolucion() {
        String mensaje = 
            "<html><body style='width: 500px;'>" +
            "<h3>🔧 Solución de Problemas de Base de Datos</h3>" +
            "<p><b>Problema:</b> No se encuentran los drivers JDBC</p>" +
            "<p><b>Solución:</b></p>" +
            "<ol>" +
            "<li><b>Descargar SQLite JDBC:</b><br>" +
            "   Visite: <a href='https://github.com/xerial/sqlite-jdbc/releases'>https://github.com/xerial/sqlite-jdbc/releases</a><br>" +
            "   Descargue: <code>sqlite-jdbc-3.42.0.0.jar</code></li>" +
            "<li><b>Agregar al proyecto:</b><br>" +
            "   - NetBeans: Clic derecho en proyecto → Properties → Libraries → Add JAR/Folder<br>" +
            "   - Eclipse: Clic derecho en proyecto → Build Path → Configure Build Path → Add External JARs<br>" +
            "   - IntelliJ: File → Project Structure → Libraries → + → Java</li>" +
            "<li><b>Reiniciar la aplicación</b></li>" +
            "</ol>" +
            "<p><b>Alternativa rápida:</b> Use el modo simulación para probar la aplicación inmediatamente.</p>" +
            "</body></html>";

        Object[] opciones = {"Usar Modo Simulación", "Salir"};
        int eleccion = JOptionPane.showOptionDialog(null,
            mensaje,
            "Instrucciones de Instalación",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            opciones,
            opciones[0]);

        if (eleccion == 0) {
            usarModoSimulacion();
        } else {
            System.exit(1);
        }
    }

    /**
     * INICIALIZA LOS SERVICIOS DE LA APLICACIÓN
     */
    private static void inicializarServicios() {
        System.out.println("🔧 Inicializando servicios...");

        try {
            // Inicializar servicios básicos
            gestionParcelas = new GestionParcelas();
            gestionStock = new GestionStock();
            gestionMaquinaria = new GestionMaquinaria();

            // Inicializar servicios dependientes
            gestionFinanciera = new GestionFinanciera();
            gestionCampo = new GestionCampo(gestionParcelas, gestionStock, gestionMaquinaria);
            gestionRiegoFertilizacion = new GestionRiegoFertilizacion(gestionParcelas, gestionStock);
            gestionCosecha = new GestionCosecha(gestionParcelas);
            gestionPersonal = new GestionPersonal();

            System.out.println("✅ Todos los servicios inicializados correctamente");

            if (modoSimulacion) {
                System.out.println("💡 Servicios operando en modo simulación");
            }

        } catch (Exception e) {
            System.err.println("❌ Error al inicializar servicios: " + e.getMessage());
            JOptionPane.showMessageDialog(null,
                "Error al inicializar servicios: " + e.getMessage(),
                "Error de Inicialización",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * CREA LA INTERFAZ GRÁFICA CON PANEL LATERAL
     */
    private static void crearInterfazGrafica() {
        System.out.println("🎨 Creando interfaz gráfica con panel lateral...");

        // Crear ventana principal
        String titulo = modoSimulacion ? 
            "Agro Gestión Integral v2.0 - [MODO SIMULACIÓN]" : 
            "Agro Gestión Integral v2.0";
            
        frame = new JFrame(titulo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1200, 700));
        frame.setLayout(new BorderLayout());

        // Crear panel lateral con botones
        panelLateral = crearPanelLateral();
        frame.add(panelLateral, BorderLayout.WEST);

        // Crear panel de contenido con CardLayout
        cardLayout = new CardLayout();
        panelContenido = new JPanel(cardLayout);
        frame.add(panelContenido, BorderLayout.CENTER);

        // Configurar ventana
        frame.pack();
        frame.setLocationRelativeTo(null); // Centrar en pantalla
    }

    /**
     * CREA EL PANEL LATERAL CON BOTONES DE NAVEGACIÓN
     */
    private static JPanel crearPanelLateral() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        // Color diferente para modo simulación
        Color colorFondo = modoSimulacion ? new Color(70, 70, 70) : new Color(50, 50, 50);
        panel.setBackground(colorFondo);
        
        panel.setPreferredSize(new Dimension(200, 600));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Título del panel lateral
        JLabel titulo = new JLabel("AGRO GESTIÓN");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titulo);

        // Indicador de modo
        if (modoSimulacion) {
            JLabel lblModo = new JLabel("[MODO SIMULACIÓN]");
            lblModo.setForeground(Color.YELLOW);
            lblModo.setFont(new Font("Segoe UI", Font.ITALIC, 10));
            lblModo.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(lblModo);
        }

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // NUEVOS BOTONES DE NAVEGACIÓN
        String[] botones = {
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

        for (String textoBoton : botones) {
            JButton boton = crearBotonLateral(textoBoton);
            panel.add(boton);
            panel.add(Box.createRigidArea(new Dimension(0, 8)));
        }

        return panel;
    }

    /**
     * CREA UN BOTÓN ESTILIZADO PARA EL PANEL LATERAL
     */
    private static JButton crearBotonLateral(String texto) {
        JButton boton = new JButton(texto);
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setMaximumSize(new Dimension(180, 40));
        boton.setMinimumSize(new Dimension(180, 40));
        boton.setPreferredSize(new Dimension(180, 40));
        
        // Color diferente para modo simulación
        Color colorBoton = modoSimulacion ? new Color(100, 100, 100) : new Color(70, 130, 180);
        boton.setBackground(colorBoton);
        
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(30, 144, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorBoton);
            }
        });

        // Asignar acciones a los botones
        boton.addActionListener(e -> manejarClicBoton(texto));

        return boton;
    }

    /**
     * MANEJA EL CLIC EN LOS BOTONES DEL PANEL LATERAL
     */
    private static void manejarClicBoton(String textoBoton) {
        System.out.println("🔘 Botón clickeado: " + textoBoton);

        switch (textoBoton) {
            case "🏠 Inicio":
                mostrarLogo();
                break;
            case "🌱 Cuaderno de Campo":
                if (gestionCampo != null) {
                    gestionCampo.mostrarInterfazCompleta();
                }
                break;
            case "🚜 Cosecha y Transporte":
                if (gestionCosecha != null) {
                    gestionCosecha.mostrarInterfazCompleta();
                }
                break;
            case "👥 Personal":
                if (gestionPersonal != null) {
                    gestionPersonal.mostrarInterfazCompleta();
                }
                break;
            case "💧 Riego y Fertilización":
                if (gestionRiegoFertilizacion != null) {
                    gestionRiegoFertilizacion.mostrarInterfazCompleta();
                }
                break;
            case "💰 Finanzas":
                if (gestionFinanciera != null) {
                    gestionFinanciera.mostrarInterfazCompleta();
                }
                break;
            case "📦 Gestión de Stock":
                if (gestionStock != null) {
                    gestionStock.mostrarInterfazCompleta();
                }
                break;
            case "🔧 Control de Maquinaria":
                if (gestionMaquinaria != null) {
                    gestionMaquinaria.mostrarInterfazCompleta();
                }
                break;
            case "📞 Soporte Técnico":
                mostrarSoporteTecnico();
                break;
            case "🚪 Salir":
                confirmarSalida();
                break;
            default:
                System.out.println("⚠️  Botón no implementado: " + textoBoton);
        }
    }

    /**
     * MUESTRA EL LOGO DE CÓDIGO CRÍTICO
     */
    private static void mostrarLogo() {
        String logo = 
            "╔════════════════════════════════════════╗\n" +
            "║          ╔═╗╔═╗╔╦╗╔═╗╦╔╗╔╔═╗          ║\n" +
            "║          ║ ╦╠═╝ ║ ╠═╣║║║║║╣           ║\n" +
            "║          ╚═╝╩  ╚╩╝╩ ╩╩╝╚╝╚═╝          ║\n" +
            "║                                        ║\n" +
            "║        ██████╗██╗██████╗ ██╗ ██████╗  ║\n" +
            "║       ██╔════╝██║██╔══██╗██║██╔═══██╗ ║\n" +
            "║       ██║     ██║██║  ██║██║██║   ██║ ║\n" +
            "║       ██║     ██║██║  ██║██║██║   ██║ ║\n" +
            "║       ╚██████╗██║██████╔╝██║╚██████╔╝ ║\n" +
            "║        ╚═════╝╚═╝╚═════╝ ╚═╝ ╚═════╝  ║\n" +
            "║                                        ║\n" +
            "║           AGRO GESTIÓN INTEGRAL        ║\n" +
            "║               v2.0 - 2025              ║\n" +
            "╚════════════════════════════════════════╝";

        JTextArea textArea = new JTextArea(logo);
        textArea.setFont(new Font("Consolas", Font.BOLD, 14));
        textArea.setBackground(new Color(30, 30, 30));
        textArea.setForeground(Color.GREEN);
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));

        JOptionPane.showMessageDialog(frame, scrollPane, "Código Crítico - Agro Gestión", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * MUESTRA INFORMACIÓN DE SOPORTE TÉCNICO CON LOGO E INFORMACIÓN DEL EQUIPO
     */
    private static void mostrarSoporteTecnico() {
        try {
            // Crear panel principal
            JPanel panelSoporte = new JPanel(new BorderLayout(10, 10));
            panelSoporte.setBackground(Color.WHITE);
            
            // Cargar y mostrar la imagen del logo
            ImageIcon logoIcon = cargarLogo();
            if (logoIcon != null) {
                JLabel labelLogo = new JLabel(logoIcon);
                labelLogo.setHorizontalAlignment(JLabel.CENTER);
                panelSoporte.add(labelLogo, BorderLayout.NORTH);
            }
            
            // Texto de información del equipo
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
                "Fecha: Noviembre 2025\n" +
                "Java: 15 + SQLITE-JDBC + Swing UI\n\n" +
                "⚠️  Para reportar errores o sugerencias, contactar a cualquier miembro del equipo.";

            JTextArea textArea = new JTextArea(infoSoporte);
            textArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            textArea.setBackground(new Color(240, 240, 240));
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(600, 400));
            panelSoporte.add(scrollPane, BorderLayout.CENTER);

            // Mostrar el diálogo
            JOptionPane.showMessageDialog(frame, panelSoporte, 
                "Soporte Técnico - Código Crítico", 
                JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            System.err.println("❌ Error al mostrar soporte técnico: " + e.getMessage());
            // Fallback: mostrar información básica sin imagen
            String infoBasica = 
                "EQUIPO DE SOPORTE TÉCNICO - CÓDIGO CRÍTICO 2025\n\n" +
                "Contactos del equipo de desarrollo:\n\n" +
                "• Mazara Ariel - arielmazara@gmail.com\n" +
                "• Zuñiga Agustina - agustinavictoriazuniga@gmail.com\n" +
                "• Silva Daniel - daniel.dolhartz@gmail.com\n" +
                "• Gonzalez Joel - joelious7@gmail.com\n" +
                "• Baz Samira - bazsamira79@gmail.com\n" +
                "• Mendez Oscar - Oreomendez99@gmail.com\n" +
                "• Mamani Santino - santinomamani25@gmail.com\n" +
                "• Ponce de Leon Damian - poncedeleondamianadolfo@gmail.com";

            JOptionPane.showMessageDialog(frame, infoBasica, 
                "Soporte Técnico - Código Crítico", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * CARGA EL LOGO DESDE LA CARPETA DE IMÁGENES
     */
    private static ImageIcon cargarLogo() {
        try {
            // Intentar cargar la imagen desde diferentes ubicaciones posibles
            java.net.URL imageUrl = Main.class.getResource("/agestion/images/codigocritico.jpg");
            
            if (imageUrl == null) {
                // Intentar con otra ruta relativa
                imageUrl = Main.class.getResource("images/codigocritico.jpg");
            }
            
            if (imageUrl == null) {
                // Intentar con ruta del filesystem
                imageUrl = new java.io.File("agestion/images/codigocritico.jpg").toURI().toURL();
            }

            if (imageUrl != null) {
                ImageIcon originalIcon = new ImageIcon(imageUrl);
                // Escalar la imagen si es muy grande
                Image image = originalIcon.getImage();
                Image scaledImage = image.getScaledInstance(300, 150, Image.SCALE_SMOOTH);
                return new ImageIcon(scaledImage);
            } else {
                System.err.println("❌ No se pudo encontrar el archivo codigocritico.jpg");
                return null;
            }
        } catch (Exception e) {
            System.err.println("❌ Error al cargar el logo: " + e.getMessage());
            return null;
        }
    }

    /**
     * CONFIRMA LA SALIDA DE LA APLICACIÓN
     */
    private static void confirmarSalida() {
        String mensaje = modoSimulacion ?
            "<html><body style='width: 400px;'>" +
            "<h3>🚪 Confirmar Salida - Modo Simulación</h3>" +
            "<p><b>¡Advertencia!</b> Todos los datos se perderán.</p>" +
            "<p>Está utilizando el modo simulación, por lo que los datos no se guardan permanentemente.</p>" +
            "<p>¿Está seguro de que desea salir?</p>" +
            "</body></html>" :
            "<html><body style='width: 300px;'>" +
            "<h3>🚪 Confirmar Salida</h3>" +
            "<p>¿Está seguro de que desea salir de la aplicación?</p>" +
            "</body></html>";

        int confirmacion = JOptionPane.showConfirmDialog(frame, mensaje, "Confirmar Salida", 
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    /**
     * MUESTRA LA INTERFAZ GRÁFICA
     */
    private static void mostrarInterfaz() {
        System.out.println("🎯 Mostrando interfaz gráfica...");
        frame.setVisible(true);
        
        if (modoSimulacion) {
            System.out.println("✅ Aplicación iniciada correctamente en MODO SIMULACIÓN");
        } else {
            System.out.println("✅ Aplicación iniciada correctamente con BASE DE DATOS REAL");
        }

        // Mostrar mensaje de bienvenida
        SwingUtilities.invokeLater(() -> {
            String mensajeBienvenida = modoSimulacion ?
                "<html><body style='width: 450px;'>" +
                "<h3>¡Bienvenido a Agro Gestión Integral v2.0!</h3>" +
                "<p><b>Modo:</b> Simulación (Datos en Memoria)</p>" +
                "<p>El sistema está funcionando en <b>modo simulación</b>. Use el panel lateral para navegar.</p>" +
                "<p><b>Nota:</b> Los datos se perderán al cerrar la aplicación.</p>" +
                "</body></html>" :
                "<html><body style='width: 400px;'>" +
                "<h3>¡Bienvenido a Agro Gestión Integral v2.0!</h3>" +
                "<p>Sistema completo de gestión agrícola.</p>" +
                "<p>Use el panel lateral para navegar por los módulos.</p>" +
                "</body></html>";

            JOptionPane.showMessageDialog(frame, mensajeBienvenida, "Bienvenida", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}