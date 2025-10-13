package agestion.main;

import agestion.vistas.MenuPrincipal;
import com.formdev.flatlaf.FlatDarkLaf;   // <-- 1. IMPORTANTE: Importamos el tema oscuro
import com.formdev.flatlaf.FlatLightLaf;  // <-- 1. IMPORTANTE: Importamos el tema claro
import java.awt.Font;
import javax.swing.UIManager;

public class Main {
    
    public static void main(String[] args) {
        
        // --- CONFIGURACIÓN DE ESTILO VISUAL MODERNO CON FLATLAF ---
        try {
            // Elige el tema que prefieras (descomenta una de las dos líneas):
            FlatDarkLaf.setup();  // Para un tema oscuro elegante.
            // FlatLightLaf.setup(); // Para un tema claro y limpio.

        } catch (Exception e) {
            System.err.println("No se pudo inicializar el tema FlatLaf.");
        }
        // --- FIN DE LA CONFIGURACIÓN DE ESTILO ---
        
        // Mantenemos los ajustes para agrandar la fuente.
        // Puedes hacerla incluso un poco más grande si quieres.
        UIManager.put("OptionPane.messageFont", new Font("Segoe UI", Font.PLAIN, 16));
        UIManager.put("OptionPane.buttonFont", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("Label.font", new Font("Segoe UI", Font.BOLD, 18)); // Hacemos más grande el "Bienvenido..."


        // El resto del código no cambia.
        MenuPrincipal menu = new MenuPrincipal();
        menu.mostrarMenu();
    }
}