package agestion.modelo;

import java.time.LocalDate;

public class Transaccion {

    public enum Tipo { INGRESO, EGRESO }

    private int id;
    private LocalDate fecha;
    private String descripcion;
    private double montoBase;
    private double montoIva;
    private double tasaIvaAplicada; // <-- NUEVO: Guardamos la tasa (ej: 0.21)
    private Tipo tipo;

    public Transaccion(int id, LocalDate fecha, String descripcion, double montoBase, double montoIva, double tasaIvaAplicada, Tipo tipo) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.montoBase = montoBase;
        this.montoIva = montoIva;
        this.tasaIvaAplicada = tasaIvaAplicada; // <-- NUEVO
        this.tipo = tipo;
    }

    // --- Getters ---
    public int getId() { return id; }
    public LocalDate getFecha() { return fecha; }
    public String getDescripcion() { return descripcion; }
    public Tipo getTipo() { return tipo; }
    public double getMontoIva() { return montoIva; }
    public double getMontoBase() { return montoBase; }
    
    public double getMontoTotal() {
        double total = montoBase + montoIva;
        return (tipo == Tipo.EGRESO) ? -total : total;
    }

    @Override
    public String toString() {
        String simbolo = (tipo == Tipo.INGRESO) ? "+ $" : "- $";
        String totalStr = String.format("%.2f", montoBase + montoIva);
        String tasaStr = String.format("%.1f%%", tasaIvaAplicada * 100); // Muestra el %
        return fecha + " | " + descripcion + " | " + simbolo + totalStr + " (IVA " + tasaStr + ")";
    }
}