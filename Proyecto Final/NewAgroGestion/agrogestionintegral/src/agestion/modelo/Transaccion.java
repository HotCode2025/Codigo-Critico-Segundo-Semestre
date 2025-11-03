// Transaccion.java
package agestion.modelo;

import java.time.LocalDate;

/**
 * MODELO PARA TRANSACCIONES FINANCIERAS
 * 
 * Representa transacciones contables con desglose de IVA
 * y clasificación por tipo (ingreso/egreso).
 * 
 * @author Código Crítico 2025
 * @version 2.0
 */
public class Transaccion {
    public enum Tipo {
        INGRESO, EGRESO
    }

    private int id;
    private LocalDate fecha;
    private String descripcion;
    private double montoBase;
    private double montoIva;
    private double tasaIva;
    private Tipo tipo;

    /**
     * CONSTRUCTOR COMPLETO PARA TRANSACCIÓN
     */
    public Transaccion(int id, LocalDate fecha, String descripcion, 
                      double montoBase, double montoIva, double tasaIva, Tipo tipo) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.montoBase = montoBase;
        this.montoIva = montoIva;
        this.tasaIva = tasaIva;
        this.tipo = tipo;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getMontoBase() { return montoBase; }
    public void setMontoBase(double montoBase) { this.montoBase = montoBase; }

    public double getMontoIva() { return montoIva; }
    public void setMontoIva(double montoIva) { this.montoIva = montoIva; }

    public double getTasaIva() { return tasaIva; }
    public void setTasaIva(double tasaIva) { this.tasaIva = tasaIva; }

    public Tipo getTipo() { return tipo; }
    public void setTipo(Tipo tipo) { this.tipo = tipo; }

    /**
     * CALCULA EL MONTO TOTAL (BASE + IVA)
     */
    public double getMontoTotal() {
        return montoBase + montoIva;
    }

    /**
     * REPRESENTACIÓN EN STRING DE LA TRANSACCIÓN
     */
    @Override
    public String toString() {
        String tipoStr = (tipo == Tipo.INGRESO) ? "INGRESO" : "EGRESO";
        String signo = (tipo == Tipo.INGRESO) ? "+" : "-";
        
        return String.format(
            "Transacción #%d | %s | %s\n%s$ %.2f (Base: $%.2f + IVA %.0f%%: $%.2f)",
            id, fecha, descripcion, signo, getMontoTotal(), montoBase, tasaIva * 100, montoIva
        );
    }
}