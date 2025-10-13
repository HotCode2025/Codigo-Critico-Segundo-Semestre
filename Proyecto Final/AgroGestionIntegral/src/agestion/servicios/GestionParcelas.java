package agestion.servicios;

import agestion.modelo.Parcela;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GestionParcelas {

    private ArrayList<Parcela> listaParcelas = new ArrayList<>();

    public void agregarParcela() {
        try {
            String nombre = JOptionPane.showInputDialog(null, "Nombre de la parcela:", "Nueva Parcela", JOptionPane.QUESTION_MESSAGE);
            if (nombre == null || nombre.trim().isEmpty()) { return; }

            String tipoCultivo = JOptionPane.showInputDialog(null, "Tipo de cultivo (ej: Viñedo, Durazno):", "Nueva Parcela", JOptionPane.QUESTION_MESSAGE);
            String superficieStr = JOptionPane.showInputDialog(null, "Superficie en hectáreas (ej: 10.5):", "Nueva Parcela", JOptionPane.QUESTION_MESSAGE);
            double superficie = Double.parseDouble(superficieStr);

            int id = listaParcelas.size() + 1;
            Parcela nuevaParcela = new Parcela(id, nombre, superficie, tipoCultivo);
            listaParcelas.add(nuevaParcela);

            JOptionPane.showMessageDialog(null, "¡Parcela '" + nombre + "' agregada correctamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: La superficie debe ser un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrarParcelas() {
        if (listaParcelas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay parcelas registradas.", "Parcelas Vacías", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder textoParcelas = new StringBuilder("--- LISTA DE PARCELAS ---\n\n");
        for (Parcela parcela : listaParcelas) {
            textoParcelas.append(parcela.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, textoParcelas.toString(), "Control de Parcelas", JOptionPane.PLAIN_MESSAGE);
    }
    
    // Método para que otras clases puedan ver la lista
    public ArrayList<Parcela> getListaParcelas() {
        return this.listaParcelas;
    }
}