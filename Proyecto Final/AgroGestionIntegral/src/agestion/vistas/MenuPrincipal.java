package agestion.vistas;

import agestion.servicios.GestionStock;
import java.awt.BorderLayout;
import java.awt.Image; // --> ¡IMPORTANTE! Se necesita para redimensionar.
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MenuPrincipal {
    
    private GestionStock gestorDeStock = new GestionStock();
    
    public void mostrarMenu() {
        // --- CREACIÓN DEL PANEL PERSONALIZADO PARA EL MENÚ ---

        // 1. Carga tu imagen original desde el archivo.
        ImageIcon bannerOriginal = new ImageIcon("src/images/agro_banner.png");

        // --> INICIO: Bloque de código para redimensionar la imagen
        // Puedes jugar con estos valores para encontrar el tamaño perfecto.
        int nuevoAncho = 450; 
        int nuevoAlto = 160;
        Image imagenOriginal = bannerOriginal.getImage();
        Image imagenRedimensionada = imagenOriginal.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
        ImageIcon bannerFinal = new ImageIcon(imagenRedimensionada);
        // --> FIN: Bloque de código para redimensionar la imagen

        // 2. Ahora, el JLabel usa la imagen ya redimensionada.
        JLabel imagenLabel = new JLabel(bannerFinal); // --> CAMBIO: Se usa el nuevo ImageIcon

        // 3. Crea la etiqueta para el texto (esto no cambia).
        JLabel mensajeLabel = new JLabel("Bienvenido a Agro Gestión Integral", SwingConstants.CENTER);

        // 4. Crea el panel principal que contendrá todo (esto no cambia).
        JPanel panelMensaje = new JPanel(new BorderLayout(0, 10)); 
        panelMensaje.add(imagenLabel, BorderLayout.NORTH); 
        panelMensaje.add(mensajeLabel, BorderLayout.CENTER); 

        // --- FIN DE LA CREACIÓN DEL PANEL ---

        boolean salir = false;
        while (!salir) {
            String[] opciones = {"1. Cuaderno de Campo", "2. Gestión de Stock", "3. Control de Maquinaria", "4. Salir"};
            
            // La llamada a JOptionPane no cambia, pero ahora el panel tiene el tamaño correcto.
            int seleccion = JOptionPane.showOptionDialog(
                null, 
                panelMensaje,
                "Menú Principal - Agro Gestión Integral", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones, 
                opciones[0]
            );

            // El resto de la lógica no cambia...
            switch (seleccion) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Módulo aún no implementado.");
                    break;
                case 1:
                    mostrarSubmenuStock();
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Módulo aún no implementado.");
                    break;
                case 3: case -1:
                    salir = true;
                    break;
            }
        }
        JOptionPane.showMessageDialog(null, "Gracias por usar Agro Gestión Integral.", "Hasta luego", JOptionPane.INFORMATION_MESSAGE);
    }

    // El método del submenú no necesita cambios.
    private void mostrarSubmenuStock() {
        String[] opcionesStock = {"1. Agregar Producto", "2. Ver Stock", "Volver al menú principal"};
        
        boolean volver = false;
        while (!volver) {
            int seleccion = JOptionPane.showOptionDialog(null, "Módulo: Gestión de Stock", "Gestión de Stock", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcionesStock, opcionesStock[0]);

            switch (seleccion) {
                case 0:
                    gestorDeStock.agregarProducto();
                    break;
                case 1:
                    gestorDeStock.mostrarStock();
                    break;
                case 2: case -1:
                    volver = true;
                    break;
            }
        }
    }
}