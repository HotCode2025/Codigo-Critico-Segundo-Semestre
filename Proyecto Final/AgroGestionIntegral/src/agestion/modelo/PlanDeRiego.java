package agestion.modelo;

import java.time.LocalDate;

public class PlanDeRiego {
    private int id;
    private Parcela parcela;
    private LocalDate fechaInicio;
    private int frecuenciaDias; // Cada cuántos días se repite
    private double duracionHoras; // Cuántas horas dura cada riego
    private String estado; // "Activo", "Finalizado"

    public PlanDeRiego(int id, Parcela parcela, LocalDate fechaInicio, int frecuenciaDias, double duracionHoras) {
        this.id = id;
        this.parcela = parcela;
        this.fechaInicio = fechaInicio;
        this.frecuenciaDias = frecuenciaDias;
        this.duracionHoras = duracionHoras;
        this.estado = "Activo"; // Por defecto, un nuevo plan está activo
    }

    // --- Getters y Setters ---
    public int getId() { return id; }
    public Parcela getParcela() { return parcela; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public int getFrecuenciaDias() { return frecuenciaDias; }
    public double getDuracionHoras() { return duracionHoras; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "ID " + id + ": Riego en '" + parcela.getNombre() + "' cada " + frecuenciaDias + " días (" + duracionHoras + "hs) - [" + estado + "]";
    }
}