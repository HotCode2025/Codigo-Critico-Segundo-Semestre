package agestion.modelo;

import java.time.LocalDate;

public class Empleado {

    // Usamos un enum para los tipos de contrato. Es más seguro que usar texto libre.
    public enum TipoContrato { PERMANENTE, TEMPORAL, CONTRATADO }

    private int legajo; // Número de empleado
    private String nombreCompleto;
    private String dni;
    private LocalDate fechaIngreso;
    private LocalDate fechaBaja; // Será null si el empleado está activo
    private TipoContrato tipoContrato;
    private String estado; // "Activo" o "Inactivo"

    private String cuit;
    private String categoria; // Puesto o rol, ej: "Tractorista", "Cosechador"
    private double sueldoBasico;
    private String obraSocial;
    private String art; // Aseguradora de Riesgos del Trabajo
    private String fotoPath; // Ruta al archivo de la foto

    public Empleado(int legajo, String nombreCompleto, String dni, LocalDate fechaIngreso, TipoContrato tipoContrato,
                    String cuit, String categoria, double sueldoBasico, String obraSocial, String art, String fotoPath) {
        this.legajo = legajo;
        this.nombreCompleto = nombreCompleto;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.fechaBaja = null; // Un empleado nuevo no tiene fecha de baja
        this.tipoContrato = tipoContrato;
        this.estado = "Activo";
        this.cuit = cuit;
        this.categoria = categoria;
        this.sueldoBasico = sueldoBasico;
        this.obraSocial = obraSocial;
        this.art = art;
        this.fotoPath = fotoPath;
    }

    // --- Getters y Setters ---
    public int getLegajo() { return legajo; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getFotoPath() { return fotoPath; }
    public void setFechaBaja(LocalDate fechaBaja) { this.fechaBaja = fechaBaja; }
    
    @Override
    public String toString() {
        // Construcción del texto a mostrar
        String infoPrincipal = "Legajo: " + legajo + " | " + nombreCompleto + " | " + categoria + "\n" +
                             "  - DNI: " + dni + " | CUIT: " + cuit + "\n" +
                             "  - Contrato: " + tipoContrato + " | Desde: " + fechaIngreso;
        
        // Si el empleado está inactivo, añadimos la fecha de baja
        if (estado.equals("Inactivo") && fechaBaja != null) {
            infoPrincipal += " | Hasta: " + fechaBaja;
        }

        infoPrincipal += " | Estado: [" + estado + "]\n";
        
        String infoSueldo = String.format("  - Sueldo: $%.2f | Obra Social: %s | ART: %s", sueldoBasico, obraSocial, art);
        
        // Línea añadida para depuración de la ruta de la foto
        String infoFoto = "\n  - Ruta Foto: " + (fotoPath.isEmpty() ? "No especificada" : fotoPath);
        
        return infoPrincipal + infoSueldo + infoFoto;
    }
}