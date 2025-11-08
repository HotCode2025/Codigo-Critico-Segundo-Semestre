// Main.java - VERSIÓN CON BOTONES MÁS ANCHOS Y SOPORTE TÉCNICO MEJORADO
package agestion.main;

import agestion.dao.DatabaseConnection;
import agestion.servicios.*;
import java.awt.*;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import javax.swing.*;

/**
 * APLICACIÓN PRINCIPAL - AGRO GESTIÓN INTEGRAL v2.5
 * 
 * Sistema completo de gestión agrícola con interfaz moderna y panel lateral.
 * Versión con botones más anchos y pantalla de soporte técnico optimizada.
 * 
 * @author Código Crítico 2025
 * @version 3.0
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

    /**
     * MÉTODO PRINCIPAL - Punto de entrada de la aplicación
     */
    public static void main(String[] args) {
        System.out.println("🚀 Iniciando Agro Gestión Integral v3.0 ...");

        // Verificar requisitos del sistema
        if (!verificarRequisitosSistema()) {
            return;
        }

        // Configurar apariencia visual moderna
        configurarApariencia();

        // Inicializar base de datos
        if (!inicializarBaseDatos()) {
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
        long maxMemory = heapUsage.getMax() / (1024 * 1024);
        long totalMemory = heapUsage.getCommitted() / (1024 * 1024);

        System.out.println("🧠 Memoria máxima: " + maxMemory + " MB");
        System.out.println("💾 Memoria total: " + totalMemory + " MB");

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
     * CONFIGURA LA APARIENCIA VISUAL MODERNA
     */
    private static void configurarApariencia() {
        System.out.println("🎨 Configurando apariencia visual...");

        try {
            // Usar el Look and Feel del sistema para apariencia nativa
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            // Configurar fuentes optimizadas para 1024x768
            Font fuenteDefault = new Font("Segoe UI", Font.PLAIN, 11);
            UIManager.put("Button.font", fuenteDefault);
            UIManager.put("Label.font", fuenteDefault);
            UIManager.put("TextField.font", fuenteDefault);
            UIManager.put("ComboBox.font", fuenteDefault);

            System.out.println("✅ Apariencia configurada correctamente");

        } catch (Exception e) {
            System.err.println("⚠️  No se pudo configurar la apariencia: " + e.getMessage());
        }
    }

    /**
     * INICIALIZA LA BASE DE DATOS
     */
    private static boolean inicializarBaseDatos() {
        System.out.println("🗄️  Inicializando base de datos...");

        try {
            // Obtener instancia de la conexión
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();

            // Verificar si la conexión está activa
            if (dbConnection.isConnectionActive()) {
                System.out.println("✅ Base de datos inicializada correctamente");
                return true;
            } else {
                System.err.println("❌ La conexión a la base de datos no está activa");
                mostrarInstruccionesSolucion();
                return false;
            }

        } catch (Exception e) {
            System.err.println("❌ Error crítico al inicializar la base de datos: " + e.getMessage());
            mostrarInstruccionesSolucion();
            return false;
        }
    }

    /**
     * MUESTRA INSTRUCCIONES PARA SOLUCIONAR PROBLEMAS DE BASE DE DATOS
     */
    private static void mostrarInstruccionesSolucion() {
        String mensaje = 
            "<html><body style='width: 450px;'>" +
            "<h3>🔧 Solución de Problemas de Base de Datos</h3>" +
            "<p><b>Problema:</b> No se encuentran los drivers JDBC</p>" +
            "<p><b>Solución:</b></p>" +
            "<ol>" +
            "<li><b>Descargar SQLite JDBC:</b><br>" +
            "   Visite: <a href='https://github.com/xerial/sqlite-jdbc/releases'>https://github.com/xerial/sqlite-jdbc/releases</a><br>" +
            "   Descargue: <code>sqlite-jdbc-3.42.0.0.jar</code></li>" +
            "<li><b>Agregar al proyecto:</b><br>" +
            "   - NetBeans: Clic derecho en proyecto → Properties → Libraries → Add JAR/Folder<br>" +
            "   - Agregar el archivo JAR descargado</li>" +
            "<li><b>Reiniciar la aplicación</b></li>" +
            "</ol>" +
            "</body></html>";

        JOptionPane.showMessageDialog(null,
            mensaje,
            "Instrucciones de Instalación",
            JOptionPane.ERROR_MESSAGE);
        
        System.exit(1);
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

        } catch (Exception e) {
            System.err.println("❌ Error al inicializar servicios: " + e.getMessage());
            JOptionPane.showMessageDialog(null,
                "Error al inicializar servicios: " + e.getMessage(),
                "Error de Inicialización",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * CREA LA INTERFAZ GRÁFICA CON PANEL LATERAL MÁS ANCHO
     */
    private static void crearInterfazGrafica() {
        System.out.println("🎨 Creando interfaz gráfica con panel lateral MÁS ANCHO...");

        // Crear ventana principal
        frame = new JFrame("Agro Gestión Integral v3.0");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1100, 730)); // AUMENTADO para panel lateral más ancho
        frame.setPreferredSize(new Dimension(1100, 730));
        frame.setLayout(new BorderLayout());

        // Fondo negro para la ventana principal
        frame.getContentPane().setBackground(Color.BLACK);

        // Crear panel lateral MÁS ANCHO con botones
        panelLateral = crearPanelLateral();
        frame.add(panelLateral, BorderLayout.WEST);

        // Crear panel de contenido con CardLayout y logo central
        cardLayout = new CardLayout();
        panelContenido = new JPanel(cardLayout);
        panelContenido.setBackground(Color.BLACK);
        
        // Agregar panel de bienvenida con logo
        JPanel panelBienvenida = crearPanelBienvenida();
        panelContenido.add(panelBienvenida, "BIENVENIDA");
        
        frame.add(panelContenido, BorderLayout.CENTER);

        // Configurar ventana
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    /**
     * CREA EL PANEL DE BIENVENIDA CON LOGO CENTRAL
     */
    private static JPanel crearPanelBienvenida() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);
        
        // Cargar y mostrar el logo4.jpg en el centro
        ImageIcon logoIcon = cargarLogoCentral();
        if (logoIcon != null) {
            JLabel labelLogo = new JLabel(logoIcon);
            labelLogo.setHorizontalAlignment(JLabel.CENTER);
            panel.add(labelLogo, BorderLayout.CENTER);
        } else {
            // Fallback si no se encuentra la imagen
            JLabel labelTitulo = new JLabel("AGRO GESTIÓN INTEGRAL v3.0 HIBRIDA (ARRAY - BASE DE DATOS)", JLabel.CENTER);
            labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
            labelTitulo.setForeground(Color.WHITE);
            panel.add(labelTitulo, BorderLayout.CENTER);
        }
        
        // Mensaje de bienvenida en la parte inferior
        JLabel labelMensaje = new JLabel("Seleccione una opción del menú lateral para comenzar (MODO HIBRIDO)", JLabel.CENTER);
        labelMensaje.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        labelMensaje.setForeground(Color.WHITE);
        labelMensaje.setBorder(BorderFactory.createEmptyBorder(15, 15, 25, 15));
        panel.add(labelMensaje, BorderLayout.SOUTH);
        
        return panel;
    }

    /**
     * CARGA EL LOGO CENTRAL (logo4.jpg)
     */
    private static ImageIcon cargarLogoCentral() {
        try {
            // Cargar desde la carpeta agestion.images
            java.net.URL imageUrl = Main.class.getResource("/agestion/images/logo4.jpg");
            
            if (imageUrl != null) {
                ImageIcon originalIcon = new ImageIcon(imageUrl);
                // Escalar a 600x600 píxeles
                Image image = originalIcon.getImage();
                Image scaledImage = image.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
                return new ImageIcon(scaledImage);
            } else {
                System.err.println("❌ No se pudo encontrar el archivo logo4.jpg");
                return null;
            }
        } catch (Exception e) {
            System.err.println("❌ Error al cargar el logo central: " + e.getMessage());
            return null;
        }
    }

    /**
     * CREA EL PANEL LATERAL CON BOTONES MÁS ANCHOS
     */
    private static JPanel crearPanelLateral() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        // Color de fondo
        panel.setBackground(new Color(50, 50, 50));
        
        // PANEL LATERAL MÁS ANCHO para botones más grandes
        panel.setPreferredSize(new Dimension(300, 650)); // AUMENTADO de 220 a 300
        panel.setBorder(BorderFactory.createEmptyBorder(12, 15, 15, 15)); // Márgenes ajustados

        // BOTONES DE NAVEGACIÓN - TEXTO COMPLETO
        String[] botones = {
            " Cuaderno de Campo", 
            "️ Gestión de Parcelas",    
            " Cosecha y Transporte", 
            " Personal", 
            " Riego y Fertilización", 
            " Finanzas", 
            " Gestión de Stock", 
            " Control de Maquinaria", 
            " Soporte Técnico", 
            " Salir"
        };

        for (String textoBoton : botones) {
            JButton boton = crearBotonLateral(textoBoton);
            panel.add(boton);
            panel.add(Box.createRigidArea(new Dimension(0, 6)));
        }

        // Espacio flexible para empujar los botones hacia arriba
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    /**
     * CREA UN BOTÓN ESTILIZADO MÁS ANCHO CON IMAGEN Y TEXTO COMPLETO
     */
    private static JButton crearBotonLateral(String texto) {
        JButton boton = new JButton(texto);
        boton.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // BOTONES MÁS ANCHOS para que entren imagen y texto completo
        boton.setMaximumSize(new Dimension(270, 55)); // AUMENTADO de 200 a 270
        boton.setMinimumSize(new Dimension(270, 55));
        boton.setPreferredSize(new Dimension(270, 55));
        
        // Estilo base
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 11));

        // COLOR NEGRO PARA TODOS LOS BOTONES
        boton.setBackground(Color.BLACK);

        // Cargar imagen - MÁS ESPACIO PARA TEXTO
        ImageIcon icono = cargarIconoBoton(texto);
        if (icono != null) {
            boton.setIcon(icono);
            boton.setHorizontalTextPosition(SwingConstants.RIGHT);
            boton.setIconTextGap(15); // AUMENTADO espacio entre icono y texto
            boton.setHorizontalAlignment(SwingConstants.LEFT);
        }

        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(50, 50, 50));
                boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(Color.BLACK);
                boton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        // Asignar acciones a los botones
        boton.addActionListener(e -> manejarClicBoton(texto));

        return boton;
    }

    /**
     * CARGA EL ICONO CORRESPONDIENTE PARA CADA BOTÓN
     */
    private static ImageIcon cargarIconoBoton(String textoBoton) {
        String nombreArchivo = "";
        
        // Mapear botones con sus imágenes correspondientes
        switch (textoBoton) {
            case " Cuaderno de Campo":
                nombreArchivo = "cuaderno.jpg";
                break;
            case "️ Gestión de Parcelas":
                nombreArchivo = "parcelas.jpg";
                break;
            case " Cosecha y Transporte":
                nombreArchivo = "cosecha.jpg";
                break;
            case " Personal":
                nombreArchivo = "personal.jpg";
                break;
            case " Riego y Fertilización":
                nombreArchivo = "riego.jpg";
                break;
            case " Finanzas":
                nombreArchivo = "finanzas.jpg";
                break;
            case " Gestión de Stock":
                nombreArchivo = "stock.jpg";
                break;
            case " Control de Maquinaria":
                nombreArchivo = "control.jpg";
                break;
            case " Soporte Técnico":
                nombreArchivo = "soporte.jpg";
                break;
            case " Salir":
                nombreArchivo = "salir.jpg";
                break;
            default:
                return null;
        }
        
        try {
            // Cargar desde la carpeta agestion.images
            java.net.URL imageUrl = Main.class.getResource("/agestion/images/" + nombreArchivo);
            
            if (imageUrl != null) {
                ImageIcon originalIcon = new ImageIcon(imageUrl);
                // Escalado a 50x50 píxeles
                Image image = originalIcon.getImage();
                Image scaledImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                return new ImageIcon(scaledImage);
            } else {
                System.err.println("❌ No se pudo encontrar la imagen: " + nombreArchivo);
                if (textoBoton.equals("🏞️ Gestión de Parcelas")) {
                    return crearIconoPorDefecto();
                }
                return null;
            }
        } catch (Exception e) {
            System.err.println("❌ Error al cargar el icono para " + textoBoton + ": " + e.getMessage());
            if (textoBoton.equals("🏞️ Gestión de Parcelas")) {
                return crearIconoPorDefecto();
            }
            return null;
        }
    }

    /**
     * CREA UN ICONO POR DEFECTO PARA GESTIÓN DE PARCELAS
     */
    private static ImageIcon crearIconoPorDefecto() {
        java.awt.image.BufferedImage imagen = new java.awt.image.BufferedImage(50, 50, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = imagen.createGraphics();
        
        // Fondo verde
        g2d.setColor(new Color(0, 100, 0));
        g2d.fillRect(0, 0, 50, 50);
        
        // Texto "PARC"
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Segoe UI", Font.BOLD, 11));
        g2d.drawString("PARC", 10, 30);
        
        g2d.dispose();
        return new ImageIcon(imagen);
    }

    /**
     * MANEJA EL CLIC EN LOS BOTONES DEL PANEL LATERAL
     */
    private static void manejarClicBoton(String textoBoton) {
        System.out.println("🔘 Botón clickeado: " + textoBoton);

        switch (textoBoton) {
            case " Cuaderno de Campo":
                if (gestionCampo != null) {
                    gestionCampo.mostrarInterfazCompleta();
                } else {
                    mostrarServicioNoDisponible("Cuaderno de Campo");
                }
                break;
            case "️ Gestión de Parcelas":
                if (gestionParcelas != null) {
                    gestionParcelas.mostrarInterfazCompleta();
                } else {
                    mostrarServicioNoDisponible("Gestión de Parcelas");
                }
                break;
            case " Cosecha y Transporte":
                if (gestionCosecha != null) {
                    gestionCosecha.mostrarInterfazCompleta();
                } else {
                    mostrarServicioNoDisponible("Cosecha y Transporte");
                }
                break;
            case " Personal":
                if (gestionPersonal != null) {
                    gestionPersonal.mostrarInterfazCompleta();
                } else {
                    mostrarServicioNoDisponible("Personal");
                }
                break;
            case " Riego y Fertilización":
                if (gestionRiegoFertilizacion != null) {
                    gestionRiegoFertilizacion.mostrarInterfazCompleta();
                } else {
                    mostrarServicioNoDisponible("Riego y Fertilización");
                }
                break;
            case " Finanzas":
                if (gestionFinanciera != null) {
                    gestionFinanciera.mostrarInterfazCompleta();
                } else {
                    mostrarServicioNoDisponible("Finanzas");
                }
                break;
            case " Gestión de Stock":
                if (gestionStock != null) {
                    gestionStock.mostrarInterfazCompleta();
                } else {
                    mostrarServicioNoDisponible("Gestión de Stock");
                }
                break;
            case " Control de Maquinaria":
                if (gestionMaquinaria != null) {
                    gestionMaquinaria.mostrarInterfazCompleta();
                } else {
                    mostrarServicioNoDisponible("Control de Maquinaria");
                }
                break;
            case " Soporte Técnico":
                mostrarSoporteTecnico();
                break;
            case " Salir":
                confirmarSalida();
                break;
            default:
                System.out.println("⚠️  Botón no implementado: " + textoBoton);
        }
    }

    /**
     * MUESTRA MENSAJE CUANDO UN SERVICIO NO ESTÁ DISPONIBLE
     */
    private static void mostrarServicioNoDisponible(String nombreServicio) {
        JOptionPane.showMessageDialog(frame,
            "<html><body style='width: 300px;'>" +
            "<h3>⚠️ Servicio No Disponible</h3>" +
            "<p>El servicio <b>" + nombreServicio + "</b> no está disponible en este momento.</p>" +
            "</body></html>",
            "Servicio No Disponible",
            JOptionPane.WARNING_MESSAGE);
    }

    /**
     * MUESTRA INFORMACIÓN DE SOPORTE TÉCNICO MEJORADA
     * CON INFORMACIÓN DEL SISTEMA MÁS ARRIBA Y CENTRADA
     */
    private static void mostrarSoporteTecnico() {
        try {
            JDialog dialogoSoporte = new JDialog(frame, "Soporte Técnico - Código Crítico", true);
            dialogoSoporte.setLayout(new BorderLayout());
            dialogoSoporte.setPreferredSize(new Dimension(850, 650));
            
            // FONDO NEGRO
            dialogoSoporte.getContentPane().setBackground(Color.BLACK);
            
            // Panel principal con fondo negro
            JPanel panelSoporte = new JPanel(new BorderLayout(20, 20));
            panelSoporte.setBackground(Color.BLACK);
            panelSoporte.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            // Cargar y mostrar la imagen del logo
            ImageIcon logoIcon = cargarLogoSoporte();
            JLabel labelLogo = null;
            if (logoIcon != null) {
                labelLogo = new JLabel(logoIcon);
            } else {
                labelLogo = new JLabel("CÓDIGO CRÍTICO");
                labelLogo.setForeground(Color.WHITE);
                labelLogo.setFont(new Font("Segoe UI", Font.BOLD, 18));
            }
            labelLogo.setHorizontalAlignment(JLabel.CENTER);
            panelSoporte.add(labelLogo, BorderLayout.NORTH);

            // Panel de contenido principal
            JPanel panelContenidoSoporte = new JPanel();
            panelContenidoSoporte.setLayout(new BoxLayout(panelContenidoSoporte, BoxLayout.Y_AXIS));
            panelContenidoSoporte.setBackground(Color.BLACK);
            
            // Título
            JLabel titulo = new JLabel("EQUIPO DE SOPORTE TÉCNICO - CÓDIGO CRÍTICO 2025");
            titulo.setForeground(Color.WHITE);
            titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
            titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelContenidoSoporte.add(titulo);
            panelContenidoSoporte.add(Box.createRigidArea(new Dimension(0, 20)));
            
            // =============================================
            // INFORMACIÓN DEL SISTEMA - MÁS ARRIBA Y CENTRADA
            // =============================================
            JPanel panelInfoSistemaContainer = new JPanel();
            panelInfoSistemaContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelInfoSistemaContainer.setBackground(Color.BLACK);
            panelInfoSistemaContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            // Panel para información del sistema CENTRADA
            JPanel panelInfoSistema = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
            panelInfoSistema.setBackground(Color.BLACK);
            panelInfoSistema.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80), 1));
            panelInfoSistema.setMaximumSize(new Dimension(400, 40));
            
            // Información del sistema en componentes separados pero CENTRADOS
            JLabel versionLabel = new JLabel(" Versión: 2.5");
            versionLabel.setForeground(Color.WHITE);
            versionLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
            
            JLabel fechaLabel = new JLabel(" Fecha: Noviembre 2025");
            fechaLabel.setForeground(Color.WHITE);
            fechaLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
            
            JLabel javaLabel = new JLabel(" Java: 15 + SQLite + Swing");
            javaLabel.setForeground(Color.WHITE);
            javaLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
            
            // Agregar componentes al panel de información CENTRADA
            panelInfoSistema.add(versionLabel);
            panelInfoSistema.add(Box.createRigidArea(new Dimension(15, 0)));
            panelInfoSistema.add(fechaLabel);
            panelInfoSistema.add(Box.createRigidArea(new Dimension(15, 0)));
            panelInfoSistema.add(javaLabel);
            
            panelInfoSistemaContainer.add(panelInfoSistema);
            panelContenidoSoporte.add(panelInfoSistemaContainer);
            panelContenidoSoporte.add(Box.createRigidArea(new Dimension(0, 20)));
            
            // Subtítulo de contactos (AHORA DESPUÉS de la información del sistema)
            JLabel subtitulo = new JLabel("Contactos del equipo de desarrollo:");
            subtitulo.setForeground(Color.WHITE);
            subtitulo.setFont(new Font("Segoe UI", Font.BOLD, 13));
            subtitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
            panelContenidoSoporte.add(subtitulo);
            panelContenidoSoporte.add(Box.createRigidArea(new Dimension(0, 12)));
            
            // Cargar icono de email
            ImageIcon emailIcon = cargarEmailIcon();
            
            // Panel para contactos en dos columnas
            JPanel panelContactosGrid = new JPanel(new GridLayout(0, 2, 12, 8));
            panelContactosGrid.setBackground(Color.BLACK);
            
            // Lista de TODOS LOS INTEGRANTES
            String[][] integrantes = {
                {"Mazara Ariel", "arielmazara@gmail.com"},
                {"Zuñiga Agustina", "agustinavictoriazuniga@gmail.com"},
                {"Silva Daniel", "daniel.dolhartz@gmail.com"},
                {"Gonzalez Joel", "joelious7@gmail.com"},
                {"Baz Samira", "bazsamira79@gmail.com"},
                {"Mendez Oscar", "Oreomendez99@gmail.com"},
                {"Mamani Santino", "santinomamani25@gmail.com"},
                {"Ponce de León Damián", "poncedeleondamianadolfo@gmail.com"}
            };
            
            for (String[] integrante : integrantes) {
                String nombre = integrante[0];
                String email = integrante[1];
                
                // Panel para cada integrante
                JPanel panelIntegrante = new JPanel(new BorderLayout(8, 5));
                panelIntegrante.setBackground(Color.BLACK);
                panelIntegrante.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                
                // Panel izquierdo con icono de email
                JPanel panelIcono = new JPanel(new FlowLayout(FlowLayout.LEFT));
                panelIcono.setBackground(Color.BLACK);
                if (emailIcon != null) {
                    JLabel iconoLabel = new JLabel(emailIcon);
                    panelIcono.add(iconoLabel);
                }
                
                // Panel derecho con nombre y email
                JPanel panelDatos = new JPanel();
                panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));
                panelDatos.setBackground(Color.BLACK);
                
                JLabel labelNombre = new JLabel(nombre);
                labelNombre.setForeground(Color.WHITE);
                labelNombre.setFont(new Font("Segoe UI", Font.BOLD, 11));
                
                JLabel labelEmail = new JLabel(email);
                labelEmail.setForeground(Color.WHITE);
                labelEmail.setFont(new Font("Segoe UI", Font.PLAIN, 10));
                
                panelDatos.add(labelNombre);
                panelDatos.add(Box.createRigidArea(new Dimension(0, 2)));
                panelDatos.add(labelEmail);
                
                panelIntegrante.add(panelIcono, BorderLayout.WEST);
                panelIntegrante.add(panelDatos, BorderLayout.CENTER);
                
                panelContactosGrid.add(panelIntegrante);
            }
            
            panelContenidoSoporte.add(panelContactosGrid);
            
            // Nota importante
            panelContenidoSoporte.add(Box.createRigidArea(new Dimension(0, 20)));
            JLabel nota = new JLabel("⚠️  Para reportar errores o sugerencias, contactar a cualquier miembro del equipo.");
            nota.setForeground(Color.YELLOW);
            nota.setFont(new Font("Segoe UI", Font.ITALIC, 11));
            nota.setAlignmentX(Component.CENTER_ALIGNMENT); // CENTRADA
            panelContenidoSoporte.add(nota);

            // Panel para centrar el contenido
            JPanel panelCentro = new JPanel();
            panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
            panelCentro.setBackground(Color.BLACK);
            panelCentro.add(Box.createVerticalGlue());
            panelCentro.add(panelContenidoSoporte);
            panelCentro.add(Box.createVerticalGlue());
            
            panelSoporte.add(panelCentro, BorderLayout.CENTER);

            // Botón de cerrar
            JPanel panelBoton = new JPanel();
            panelBoton.setBackground(Color.BLACK);
            JButton btnCerrar = new JButton("Cerrar");
            btnCerrar.setBackground(new Color(200, 0, 0));
            btnCerrar.setForeground(Color.WHITE);
            btnCerrar.setFocusPainted(false);
            btnCerrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
            btnCerrar.setPreferredSize(new Dimension(110, 32));
            
            // Efecto hover para botón rojo
            btnCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btnCerrar.setBackground(new Color(220, 0, 0));
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btnCerrar.setBackground(new Color(200, 0, 0));
                }
            });
            
            btnCerrar.addActionListener(e -> dialogoSoporte.dispose());
            panelBoton.add(btnCerrar);
            
            panelSoporte.add(panelBoton, BorderLayout.SOUTH);

            dialogoSoporte.add(panelSoporte);
            dialogoSoporte.pack();
            dialogoSoporte.setLocationRelativeTo(frame);
            dialogoSoporte.setVisible(true);

        } catch (Exception e) {
            System.err.println("❌ Error al mostrar soporte técnico: " + e.getMessage());
            // Fallback: mostrar información básica
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
                "• Ponce de León Damián - poncedeleondamianadolfo@gmail.com\n\n" +
                "Información del sistema: Versión: 2.5 | Fecha: Noviembre 2025 | Java: 15 + SQLite + Swing";

            JOptionPane.showMessageDialog(frame, infoBasica, 
                "Soporte Técnico - Código Crítico", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * CARGA EL LOGO PARA SOPORTE TÉCNICO
     */
    private static ImageIcon cargarLogoSoporte() {
        try {
            // Cargar desde la carpeta agestion.images
            java.net.URL imageUrl = Main.class.getResource("/agestion/images/codigocritico.jpg");
            
            if (imageUrl != null) {
                ImageIcon originalIcon = new ImageIcon(imageUrl);
                // Escalado a 90x90 píxeles
                Image image = originalIcon.getImage();
                Image scaledImage = image.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
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
     * CARGA EL ICONO DE EMAIL
     */
    private static ImageIcon cargarEmailIcon() {
        try {
            // Cargar desde la carpeta agestion.images
            java.net.URL imageUrl = Main.class.getResource("/agestion/images/email.jpg");
            
            if (imageUrl != null) {
                ImageIcon originalIcon = new ImageIcon(imageUrl);
                // Escalar a 35x35 píxeles
                Image image = originalIcon.getImage();
                Image scaledImage = image.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
                return new ImageIcon(scaledImage);
            } else {
                System.err.println("❌ No se pudo encontrar el archivo email.jpg");
                return null;
            }
        } catch (Exception e) {
            System.err.println("❌ Error al cargar el icono de email: " + e.getMessage());
            return null;
        }
    }

    /**
     * CONFIRMA LA SALIDA DE LA APLICACIÓN
     */
    private static void confirmarSalida() {
        int confirmacion = JOptionPane.showConfirmDialog(frame,
            "<html><body style='width: 280px;'>" +
            "<h3>🚪 Confirmar Salida</h3>" +
            "<p>¿Está seguro de que desea salir de la aplicación?</p>" +
            "</body></html>",
            "Confirmar Salida", 
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
        System.out.println("✅ Aplicación iniciada correctamente");

        // Mostrar mensaje de bienvenida optimizado
        SwingUtilities.invokeLater(() -> {
            JPanel panelBienvenida = new JPanel(new BorderLayout(15, 15));
            panelBienvenida.setBackground(Color.WHITE);
            panelBienvenida.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 20));
            
            // Cargar logo codigocritico
            ImageIcon logoBienvenida = cargarLogoSoporte();
            if (logoBienvenida != null) {
                // Re-escalar a 80x80
                Image image = logoBienvenida.getImage();
                Image scaledImage = image.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                JLabel labelLogo = new JLabel(new ImageIcon(scaledImage));
                panelBienvenida.add(labelLogo, BorderLayout.WEST);
            }
            
            // Panel de texto
            JPanel panelTexto = new JPanel();
            panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.Y_AXIS));
            panelTexto.setBackground(Color.WHITE);
            
            JLabel labelTitulo = new JLabel("¡Bienvenido a Agro Gestión Integral v2.5!");
            labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 15));
            labelTitulo.setForeground(new Color(0, 100, 0));
            
            JLabel labelMensaje1 = new JLabel("Sistema completo de gestión agrícola con interfaz moderna.");
            JLabel labelMensaje2 = new JLabel("Use el panel lateral para navegar por los módulos.");
            
            labelMensaje1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            labelMensaje2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            
            panelTexto.add(labelTitulo);
            panelTexto.add(Box.createRigidArea(new Dimension(0, 8)));
            panelTexto.add(labelMensaje1);
            panelTexto.add(Box.createRigidArea(new Dimension(0, 4)));
            panelTexto.add(labelMensaje2);
            
            panelBienvenida.add(panelTexto, BorderLayout.CENTER);
            
            // Mostrar el diálogo personalizado
            JOptionPane.showMessageDialog(frame, panelBienvenida, "Bienvenida a Agro Gestión", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}