// PlanDeRiego.java
package agestion.modelo;

import java.time.LocalDate;

/**
 * MODELO PARA PLANES DE RIEGO
 * 
 * Representa un plan de riego programado para una parcela específica
 * con control de frecuencia, duración y estado.
 * 
 * @author Código Crítico 2025
 * @version 2.0
 */
public class PlanDeRiego {
    private int id;
    private Parcela parcela;
    private LocalDate fechaCreacion;
    private int frecuenciaDias;
    private double duracionHoras;
    private String estado; // "Activo", "Completado", "Cancelado"

    /**
     * CONSTRUCTOR COMPLETO PARA PLAN DE RIEGO
     */
    public PlanDeRiego(int id, Parcela parcela, LocalDate fechaCreacion, 
                      int frecuenciaDias, double duracionHoras) {
        this.id = id;
        this.parcela = parcela;
        this.fechaCreacion = fechaCreacion;
        this.frecuenciaDias = frecuenciaDias;
        this.duracionHoras = duracionHoras;
        this.estado = "Activo";
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Parcela getParcela() { return parcela; }
    public void setParcela(Parcela parcela) { this.parcela = parcela; }

    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public int getFrecuenciaDias() { return frecuenciaDias; }
    public void setFrecuenciaDias(int frecuenciaDias) { this.frecuenciaDias = frecuenciaDias; }

    public double getDuracionHoras() { return duracionHoras; }
    public void setDuracionHoras(double duracionHoras) { this.duracionHoras = duracionHoras; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    /**
     * REPRESENTACIÓN EN STRING DEL PLAN DE RIEGO
     */
    @Override
    public String toString() {
        return String.format(
            "Plan Riego #%d | Parcela: %s | Frecuencia: %d días | Duración: %.1f hs | Estado: %s | Creado: %s",
            id, parcela.getNombre(), frecuenciaDias, duracionHoras, estado, fechaCreacion
        );
    }
}
