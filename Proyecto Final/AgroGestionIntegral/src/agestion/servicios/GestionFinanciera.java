package agestion.servicios;

import agestion.modelo.Transaccion;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GestionFinanciera {
    
    private final ArrayList<Transaccion> libroContable = new ArrayList<>();

    public void registrarTransaccion() {
        try {
            String[] tipos = {"Ingreso (Venta)", "Egreso (Compra/Gasto)"};
            int tipoSeleccionado = JOptionPane.showOptionDialog(null, "Seleccione el tipo de transacción:", "Nueva Transacción", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, tipos, tipos[0]);
            if (tipoSeleccionado == -1) return;
            Transaccion.Tipo tipo = (tipoSeleccionado == 0) ? Transaccion.Tipo.INGRESO : Transaccion.Tipo.EGRESO;

            String[] tasasIvaOpciones = {"21%", "10.5%", "27%"};
            String tasaSeleccionadaStr = (String) JOptionPane.showInputDialog(null, "Seleccione la tasa de IVA:", "Tasa de IVA", JOptionPane.QUESTION_MESSAGE, null, tasasIvaOpciones, tasasIvaOpciones[0]);
            if (tasaSeleccionadaStr == null) return;
            
            double tasaIva = Double.parseDouble(tasaSeleccionadaStr.replace("%", "")) / 100.0;

            String descripcion = JOptionPane.showInputDialog(null, "Descripción (ej: 'Venta de cosecha'):");
            if (descripcion == null || descripcion.trim().isEmpty()) return;

            String montoTotalStr = JOptionPane.showInputDialog(null, "Monto TOTAL ($) (IVA Incluido):");
            double montoTotal = Double.parseDouble(montoTotalStr);

            double montoBase = montoTotal / (1 + tasaIva);
            double montoIva = montoTotal - montoBase;

            int id = libroContable.size() + 1;
            Transaccion nuevaTransaccion = new Transaccion(id, LocalDate.now(), descripcion, montoBase, montoIva, tasaIva, tipo);
            libroContable.add(nuevaTransaccion);
            
            String detalleIva = String.format("Monto Base: $%.2f\nIVA (%s): $%.2f", montoBase, tasaSeleccionadaStr, montoIva);
            JOptionPane.showMessageDialog(null, "Transacción registrada con éxito.\n\n" + detalleIva, "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El monto debe ser un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrarLibroContableYBalance() {
        if (libroContable.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay transacciones registradas.", "Libro Contable Vacío", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea filtrar las transacciones por fecha?", "Aplicar Filtro", JOptionPane.YES_NO_OPTION);
        ArrayList<Transaccion> listaParaMostrar = libroContable;
        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                String fechaStr = JOptionPane.showInputDialog(null, "Ingrese la fecha a filtrar (AAAA-MM-DD):");
                if (fechaStr != null && !fechaStr.trim().isEmpty()) {
                    LocalDate fechaFiltro = LocalDate.parse(fechaStr);
                    ArrayList<Transaccion> transaccionesFiltradas = new ArrayList<>();
                    for (Transaccion t : libroContable) {
                        if (t.getFecha().equals(fechaFiltro)) {
                            transaccionesFiltradas.add(t);
                        }
                    }
                    if (transaccionesFiltradas.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No se encontraron transacciones para la fecha " + fechaFiltro, "Sin Resultados", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    listaParaMostrar = transaccionesFiltradas;
                }
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "El formato de la fecha no es válido. Use AAAA-MM-DD.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        double balance = 0;
        StringBuilder sb = new StringBuilder("--- LIBRO CONTABLE ---\n\n");
        for (Transaccion t : listaParaMostrar) {
            sb.append(t.toString()).append("\n");
            balance += t.getMontoTotal();
        }
        sb.append("\n--------------------------------\n");
        sb.append(String.format("BALANCE DEL PERÍODO SELECCIONADO: $ %.2f", balance));
        JOptionPane.showMessageDialog(null, sb.toString(), "Finanzas", JOptionPane.PLAIN_MESSAGE);
    }
    
    public void mostrarReporteIva() {
        if (libroContable.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay transacciones para calcular el IVA.", "Reporte Vacío", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        double ivaDebito = 0;
        double ivaCredito = 0;
        for (Transaccion t : libroContable) {
            if (t.getTipo() == Transaccion.Tipo.INGRESO) {
                ivaDebito += t.getMontoIva();
            } else {
                ivaCredito += t.getMontoIva();
            }
        }
        double saldoIva = ivaDebito - ivaCredito;
        String resultado = (saldoIva > 0) ? String.format("Saldo a PAGAR a AFIP: $ %.2f", saldoIva) : String.format("Saldo a FAVOR (crédito fiscal): $ %.2f", -saldoIva);
        String reporte = "--- REPORTE DE IVA ---\n\n" +
                         String.format("IVA Débito (por ventas):   $ %.2f\n", ivaDebito) +
                         String.format("IVA Crédito (por compras): $ %.2f\n", ivaCredito) +
                         "--------------------------------\n" +
                         resultado;
        JOptionPane.showMessageDialog(null, reporte, "Posición Mensual de IVA", JOptionPane.INFORMATION_MESSAGE);
    }
}