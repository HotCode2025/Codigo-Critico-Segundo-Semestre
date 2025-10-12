package agestion.servicios;

import agestion.modelo.Parcela;
import agestion.modelo.PlanDeFertilizacion;
import agestion.modelo.PlanDeRiego;
import agestion.modelo.ProductoAgricola;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GestionRiegoFertilizacion {

    private ArrayList<PlanDeRiego> planesDeRiego = new ArrayList<>();
    private ArrayList<PlanDeFertilizacion> planesDeFertilizacion = new ArrayList<>();
    
    // Dependencias de otros gestores
    private GestionParcelas gestorParcelas;
    private GestionStock gestorStock;

    public GestionRiegoFertilizacion(GestionParcelas gestorParcelas, GestionStock gestorStock) {
        this.gestorParcelas = gestorParcelas;
        this.gestorStock = gestorStock;
    }

    // --- Métodos de Riego ---
    public void crearPlanDeRiego() {
        if (gestorParcelas.getListaParcelas().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe registrar una Parcela primero.", "Faltan Datos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            Parcela parcela = (Parcela) JOptionPane.showInputDialog(null, "Seleccione la parcela a regar:", "Nuevo Plan de Riego", JOptionPane.QUESTION_MESSAGE, null, gestorParcelas.getListaParcelas().toArray(), null);
            if (parcela == null) return;
            
            String frecStr = JOptionPane.showInputDialog(null, "Frecuencia de riego (en días):");
            int frecuencia = Integer.parseInt(frecStr);
            
            String durStr = JOptionPane.showInputDialog(null, "Duración del riego (en horas):");
            double duracion = Double.parseDouble(durStr);
            
            int id = planesDeRiego.size() + 1;
            PlanDeRiego nuevoPlan = new PlanDeRiego(id, parcela, LocalDate.now(), frecuencia, duracion);
            planesDeRiego.add(nuevoPlan);
            JOptionPane.showMessageDialog(null, "Plan de riego creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La frecuencia y duración deben ser números.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void verPlanesDeRiego() {
        if (planesDeRiego.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay planes de riego activos.", "Vacío", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder sb = new StringBuilder("--- PLANES DE RIEGO ---\n\n");
        for (PlanDeRiego plan : planesDeRiego) {
            sb.append(plan.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Planes de Riego", JOptionPane.PLAIN_MESSAGE);
    }

    // --- Métodos de Fertilización ---
    public void crearPlanDeFertilizacion() {
        if (gestorParcelas.getListaParcelas().isEmpty() || gestorStock.getInventario().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe registrar Parcelas y Productos primero.", "Faltan Datos", JOptionPane.WARNING_MESSAGE);
            return;
        }
         try {
            Parcela parcela = (Parcela) JOptionPane.showInputDialog(null, "Seleccione la parcela a fertilizar:", "Nuevo Plan de Fertilización", JOptionPane.QUESTION_MESSAGE, null, gestorParcelas.getListaParcelas().toArray(), null);
            if (parcela == null) return;

            ProductoAgricola producto = (ProductoAgricola) JOptionPane.showInputDialog(null, "Seleccione el fertilizante:", "Nuevo Plan de Fertilización", JOptionPane.QUESTION_MESSAGE, null, gestorStock.getInventario().toArray(), null);
            if (producto == null) return;

            String fechaStr = JOptionPane.showInputDialog(null, "Fecha de aplicación (AAAA-MM-DD):");
            LocalDate fecha = LocalDate.parse(fechaStr);

            String dosisStr = JOptionPane.showInputDialog(null, "Dosis (ej: kg/ha):");
            double dosis = Double.parseDouble(dosisStr);

            int id = planesDeFertilizacion.size() + 1;
            PlanDeFertilizacion nuevoPlan = new PlanDeFertilizacion(id, parcela, producto, fecha, dosis);
            planesDeFertilizacion.add(nuevoPlan);
            JOptionPane.showMessageDialog(null, "Plan de fertilización creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en el formato de los datos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void verPlanesDeFertilizacion() {
         if (planesDeFertilizacion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay planes de fertilización.", "Vacío", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        StringBuilder sb = new StringBuilder("--- PLANES DE FERTILIZACIÓN ---\n\n");
        for (PlanDeFertilizacion plan : planesDeFertilizacion) {
            sb.append(plan.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Planes de Fertilización", JOptionPane.PLAIN_MESSAGE);
    }
}