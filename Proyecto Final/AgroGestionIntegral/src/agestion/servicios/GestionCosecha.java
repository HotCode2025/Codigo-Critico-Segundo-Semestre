package agestion.servicios;

import agestion.modelo.MovimientoCosecha;
import agestion.modelo.Parcela;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GestionCosecha {

    private ArrayList<MovimientoCosecha> movimientos = new ArrayList<>();
    private GestionParcelas gestorParcelas;

    public GestionCosecha(GestionParcelas gestorParcelas) {
        this.gestorParcelas = gestorParcelas;
    }

    public void registrarMovimiento() {
        if (gestorParcelas.getListaParcelas().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe registrar una Parcela antes de registrar una cosecha.", "Faltan Datos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Datos de la Cosecha
            Parcela parcela = (Parcela) JOptionPane.showInputDialog(null, "Seleccione la parcela de origen:", "Registro de Cosecha", JOptionPane.QUESTION_MESSAGE, null, gestorParcelas.getListaParcelas().toArray(), null);
            if (parcela == null) return;
            
            String producto = JOptionPane.showInputDialog(null, "Producto cosechado (ej: Uva Malbec):");
            String kilosStr = JOptionPane.showInputDialog(null, "Kilos netos cosechados:");
            double kilos = Double.parseDouble(kilosStr);

            // Datos del Remito y Transporte
            String remito = JOptionPane.showInputDialog(null, "Número de Remito:");
            String transportista = JOptionPane.showInputDialog(null, "Nombre del transportista/chofer:");
            String patente = JOptionPane.showInputDialog(null, "Patente del vehículo (dominio):");
            String dtve = JOptionPane.showInputDialog(null, "Código DTVe (si aplica):");
            String destino = JOptionPane.showInputDialog(null, "Destino de la carga (ej: Bodega):");
            
            int id = movimientos.size() + 1;
            MovimientoCosecha nuevoMovimiento = new MovimientoCosecha(id, LocalDate.now(), parcela, producto, kilos, remito, transportista, patente, dtve, destino);
            movimientos.add(nuevoMovimiento);
            
            JOptionPane.showMessageDialog(null, "Movimiento de cosecha registrado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Los kilos deben ser un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void verMovimientos() {
        if (movimientos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay movimientos de cosecha registrados.", "Vacío", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder("--- REGISTRO DE COSECHA Y TRANSPORTE ---\n\n");
        for (MovimientoCosecha mov : movimientos) {
            sb.append(mov.toString()).append("\n------------------------------------------\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Historial de Movimientos", JOptionPane.PLAIN_MESSAGE);
    }
}