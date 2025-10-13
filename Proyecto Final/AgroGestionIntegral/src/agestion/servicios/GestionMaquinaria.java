package agestion.servicios;

import agestion.modelo.Maquinaria;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GestionMaquinaria {
    private ArrayList<Maquinaria> flota = new ArrayList<>();

    public void agregarMaquina() {
        try {
            String nombre = JOptionPane.showInputDialog(null, "Nombre de la máquina:", "Nueva Máquina", JOptionPane.QUESTION_MESSAGE);
            if (nombre == null || nombre.trim().isEmpty()) { return; }

            String[] estadosPosibles = {"Disponible", "En Mantenimiento", "En Uso"};
            String estadoSeleccionado = (String) JOptionPane.showInputDialog(null, "Seleccione el estado inicial:",
                "Estado de la Máquina", JOptionPane.QUESTION_MESSAGE, null, estadosPosibles, estadosPosibles[0]);
            if (estadoSeleccionado == null) { return; }

            String horasStr = JOptionPane.showInputDialog(null, "Ingrese las horas de uso iniciales:", "Nueva Máquina", JOptionPane.QUESTION_MESSAGE);
            double horas = Double.parseDouble(horasStr);
            
            int id = flota.size() + 1;
            Maquinaria nuevaMaquina = new Maquinaria(id, nombre, estadoSeleccionado, horas);
            flota.add(nuevaMaquina);

            JOptionPane.showMessageDialog(null, "¡Máquina '" + nombre + "' agregada correctamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Las horas de uso deben ser un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrarMaquinaria() {
        if (flota.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay maquinaria registrada.", "Flota Vacía", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder listaMaquinas = new StringBuilder("--- FLOTA DE MAQUINARIA ---\n\n");
        for (Maquinaria maquina : flota) {
            listaMaquinas.append(maquina.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, listaMaquinas.toString(), "Control de Maquinaria", JOptionPane.PLAIN_MESSAGE);
    }

    // --- MÉTODO NUEVO AÑADIDO AQUÍ ---

    /**
     * Permite que otras clases (como GestionCampo) obtengan la lista completa de máquinas.
     * @return El ArrayList de la flota.
     */
    public ArrayList<Maquinaria> getFlota() {
        return this.flota;
    }

} // <-- Fin de la clase GestionMaquinaria