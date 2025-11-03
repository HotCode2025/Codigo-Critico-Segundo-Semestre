// PlanDeFertilizacion.java
package agestion.modelo;

import java.time.LocalDate;

/**
 * MODELO PARA PLANES DE FERTILIZACIÓN
 * 
 * Representa un plan de fertilización programado para una parcela
 * con producto específico, dosis y fecha de aplicación.
 * 
 * @author Código Crítico 2025
 * @version 2.0
 */
public class PlanDeFertilizacion {
    private int id;
    private Parcela parcela;
    private ProductoAgricola fertilizante;
    private LocalDate fechaAplicacion;
    private double dosisKgPorHectarea;
    private String estado; // "Pendiente", "Completado", "Cancelado"

    /**
     * CONSTRUCTOR COMPLETO PARA PLAN DE FERTILIZACIÓN
     */
    public PlanDeFertilizacion(int id, Parcela parcela, ProductoAgricola fertilizante,
                              LocalDate fechaAplicacion, double dosisKgPorHectarea) {
        this.id = id;
        this.parcela = parcela;
        this.fertilizante = fertilizante;
        this.fechaAplicacion = fechaAplicacion;
        this.dosisKgPorHectarea = dosisKgPorHectarea;
        this.estado = "Pendiente";
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Parcela getParcela() { return parcela; }
    public void setParcela(Parcela parcela) { this.parcela = parcela; }

    public ProductoAgricola getFertilizante() { return fertilizante; }
    public void setFertilizante(ProductoAgricola fertilizante) { this.fertilizante = fertilizante; }

    public LocalDate getFechaAplicacion() { return fechaAplicacion; }
    public void setFechaAplicacion(LocalDate fechaAplicacion) { this.fechaAplicacion = fechaAplicacion; }

    public double getDosisKgPorHectarea() { return dosisKgPorHectarea; }
    public void setDosisKgPorHectarea(double dosisKgPorHectarea) { this.dosisKgPorHectarea = dosisKgPorHectarea; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    /**
     * REPRESENTACIÓN EN STRING DEL PLAN DE FERTILIZACIÓN
     */
    @Override
    public String toString() {
        return String.format(
            "Plan Fertilización #%d | Parcela: %s | Fertilizante: %s\nDosis: %.2f kg/ha | Fecha: %s | Estado: %s",
            id, parcela.getNombre(), fertilizante.getNombre(), dosisKgPorHectarea, fechaAplicacion, estado
        );
    }
}