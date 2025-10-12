package agestion.modelo;

import java.time.LocalDate;

public class PlanDeFertilizacion {
    private int id;
    private Parcela parcela;
    private ProductoAgricola fertilizante;
    private LocalDate fechaAplicacion;
    private double dosis; // ej: kg por hectárea
    private String estado; // "Pendiente", "Completado"

    public PlanDeFertilizacion(int id, Parcela parcela, ProductoAgricola fertilizante, LocalDate fechaAplicacion, double dosis) {
        this.id = id;
        this.parcela = parcela;
        this.fertilizante = fertilizante;
        this.fechaAplicacion = fechaAplicacion;
        this.dosis = dosis;
        this.estado = "Pendiente"; // Por defecto, una nueva aplicación está pendiente
    }

    // --- Getters y Setters ---
    public int getId() { return id; }
    public Parcela getParcela() { return parcela; }
    public ProductoAgricola getFertilizante() { return fertilizante; }
    public LocalDate getFechaAplicacion() { return fechaAplicacion; }
    public double getDosis() { return dosis; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "ID " + id + ": Aplicar '" + fertilizante.getNombre() + "' en '" + parcela.getNombre() + "' el " + fechaAplicacion + " [" + estado + "]";
    }
}