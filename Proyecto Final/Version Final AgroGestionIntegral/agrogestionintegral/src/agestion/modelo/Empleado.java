package agestion.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * MODELO DE EMPLEADO - CLASE MEJORADA PARA INTERFAZ CON SOLAPAS
 * 
 * Representa un empleado del sistema con todos sus datos personales y laborales.
 * Incluye métodos mejorados para facilitar la presentación en interfaces gráficas.
 * 
 * @author Código Crítico 2025
 * @version 2.0
 */
public class Empleado {

    public void setLegajo(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // --- ENUM PARA TIPOS DE CONTRATO ---
    public enum TipoContrato { 
        PERMANENTE("Contrato Permanente"), 
        TEMPORAL("Contrato Temporal"), 
        CONTRATADO("Contrato por Servicios");
        
        private final String descripcion;
        
        TipoContrato(String descripcion) {
            this.descripcion = descripcion;
        }
        
        public String getDescripcion() {
            return descripcion;
        }
        
        @Override
        public String toString() {
            return descripcion;
        }
    }

    // --- ATRIBUTOS PRINCIPALES ---
    private int legajo;
    private String nombreCompleto;
    private String dni;
    private LocalDate fechaIngreso;
    private LocalDate fechaBaja;
    private TipoContrato tipoContrato;
    private String estado;
    private String cuit;
    private String categoria;
    private double sueldoBasico;
    private String obraSocial;
    private String art;
    private String fotoPath;

    // --- CONSTRUCTOR PRINCIPAL ---
    public Empleado(int legajo, String nombreCompleto, String dni, LocalDate fechaIngreso, 
                   TipoContrato tipoContrato, String cuit, String categoria, double sueldoBasico, 
                   String obraSocial, String art, String fotoPath) {
        this.legajo = legajo;
        this.nombreCompleto = nombreCompleto;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.fechaBaja = null;
        this.tipoContrato = tipoContrato;
        this.estado = "Activo";
        this.cuit = cuit;
        this.categoria = categoria;
        this.sueldoBasico = sueldoBasico;
        this.obraSocial = obraSocial;
        this.art = art;
        this.fotoPath = fotoPath;
    }

    // --- MÉTODOS DE ACCESO (GETTERS Y SETTERS) ---
    public int getLegajo() { 
        return legajo; 
    }
    
    public String getNombreCompleto() { 
        return nombreCompleto; 
    }
    
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    
    public String getDni() { 
        return dni; 
    }
    
    public void setDni(String dni) {
        this.dni = dni;
    }
    
    public LocalDate getFechaIngreso() { 
        return fechaIngreso; 
    }
    
    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    
    public LocalDate getFechaBaja() { 
        return fechaBaja; 
    }
    
    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }
    
    public TipoContrato getTipoContrato() { 
        return tipoContrato; 
    }
    
    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
    }
    
    public String getEstado() { 
        return estado; 
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getCuit() { 
        return cuit; 
    }
    
    public void setCuit(String cuit) {
        this.cuit = cuit;
    }
    
    public String getCategoria() { 
        return categoria; 
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public double getSueldoBasico() { 
        return sueldoBasico; 
    }
    
    public void setSueldoBasico(double sueldoBasico) {
        this.sueldoBasico = sueldoBasico;
    }
    
    public String getObraSocial() { 
        return obraSocial; 
    }
    
    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }
    
    public String getArt() { 
        return art; 
    }
    
    public void setArt(String art) {
        this.art = art;
    }
    
    public String getFotoPath() { 
        return fotoPath; 
    }
    
    public void setFotoPath(String fotoPath) {
        this.fotoPath = fotoPath;
    }

    // --- MÉTODOS DE NEGOCIO MEJORADOS ---
    
    /**
     * Calcula la antigüedad del empleado en años
     * @return Años de antigüedad
     */
    public int calcularAntiguedad() {
        LocalDate fechaReferencia = (fechaBaja != null) ? fechaBaja : LocalDate.now();
        return fechaReferencia.getYear() - fechaIngreso.getYear();
    }
    
    /**
     * Verifica si el empleado está activo
     * @return true si está activo, false en caso contrario
     */
    public boolean estaActivo() {
        return "Activo".equals(estado);
    }
    
    /**
     * Obtiene el estado con emoji para mejor visualización
     * @return String con estado y emoji
     */
    public String getEstadoConEmoji() {
        switch (estado) {
            case "Activo": return "✅ Activo";
            case "Inactivo": return "❌ Inactivo";
            case "Vacaciones": return "🏖️ Vacaciones";
            case "Licencia": return "🏥 Licencia";
            default: return "❓ " + estado;
        }
    }
    
    /**
     * Obtiene el tipo de contrato formateado para display
     * @return String formateado del contrato
     */
    public String getTipoContratoFormateado() {
        return tipoContrato.getDescripcion();
    }

    // --- MÉTODOS DE PRESENTACIÓN MEJORADOS ---
    
    /**
     * MÉTODO TOSTRING MEJORADO - Para listas generales
     * Formato compacto para tablas y listas
     */
    @Override
    public String toString() {
        return String.format("👤 %s | Legajo: %d | %s | %s", 
                           nombreCompleto, legajo, categoria, getEstadoConEmoji());
    }
    
    /**
     * MÉTODO PARA TARJETA DE EMPLEADO - Formato detallado
     * @return String formateado para mostrar en tarjetas
     */
    public String toTarjetaString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        StringBuilder sb = new StringBuilder();
        
        sb.append("╔══════════════════════════════════════╗\n");
        sb.append(String.format("║ 👤  %-32s ║\n", nombreCompleto.toUpperCase()));
        sb.append("╠══════════════════════════════════════╣\n");
        sb.append(String.format("║ 📋 Legajo: %-26d ║\n", legajo));
        sb.append(String.format("║ 🏷️  Categoría: %-23s ║\n", categoria));
        sb.append(String.format("║ 📄 Estado: %-26s ║\n", getEstadoConEmoji()));
        sb.append(String.format("║ 📅 Ingreso: %-25s ║\n", fechaIngreso.format(formatter)));
        
        if (fechaBaja != null) {
            sb.append(String.format("║ 🚪 Baja: %-27s ║\n", fechaBaja.format(formatter)));
        }
        
        sb.append(String.format("║ 💰 Sueldo: $%-24.2f ║\n", sueldoBasico));
        sb.append("╚══════════════════════════════════════╝");
        
        return sb.toString();
    }
    
    /**
     * MÉTODO PARA FICHA DETALLADA - Información completa
     * @return String con todos los datos del empleado
     */
    public String toFichaCompletaString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        StringBuilder sb = new StringBuilder();
        
        sb.append("═".repeat(50)).append("\n");
        sb.append("         FICHA COMPLETA DE EMPLEADO\n");
        sb.append("═".repeat(50)).append("\n");
        
        // Información Personal
        sb.append("\n📊 INFORMACIÓN PERSONAL:\n");
        sb.append("─".repeat(30)).append("\n");
        sb.append(String.format("  👤 Nombre: %s\n", nombreCompleto));
        sb.append(String.format("  🆔 DNI: %s\n", dni));
        sb.append(String.format("  📋 CUIT: %s\n", cuit));
        
        // Información Laboral
        sb.append("\n💼 INFORMACIÓN LABORAL:\n");
        sb.append("─".repeat(30)).append("\n");
        sb.append(String.format("  🔢 Legajo: %d\n", legajo));
        sb.append(String.format("  🏷️  Categoría: %s\n", categoria));
        sb.append(String.format("  📄 Contrato: %s\n", getTipoContratoFormateado()));
        sb.append(String.format("  📊 Estado: %s\n", getEstadoConEmoji()));
        sb.append(String.format("  📅 Fecha Ingreso: %s\n", fechaIngreso.format(formatter)));
        sb.append(String.format("  🎯 Antigüedad: %d años\n", calcularAntiguedad()));
        
        if (fechaBaja != null) {
            sb.append(String.format("  🚪 Fecha Baja: %s\n", fechaBaja.format(formatter)));
        }
        
        // Información Económica
        sb.append("\n💰 INFORMACIÓN ECONÓMICA:\n");
        sb.append("─".repeat(30)).append("\n");
        sb.append(String.format("  💵 Sueldo Básico: $%.2f\n", sueldoBasico));
        sb.append(String.format("  🏥 Obra Social: %s\n", obraSocial));
        sb.append(String.format("  🛡️  ART: %s\n", art));
        
        // Información Adicional
        sb.append("\n📎 INFORMACIÓN ADICIONAL:\n");
        sb.append("─".repeat(30)).append("\n");
        sb.append(String.format("  📷 Foto: %s\n", 
                (fotoPath != null && !fotoPath.isEmpty()) ? fotoPath : "No especificada"));
        
        sb.append("═".repeat(50)).append("\n");
        
        return sb.toString();
    }
    
    /**
     * MÉTODO PARA LINEA DE LISTA - Formato compacto para JList
     * @return String compacto para listas
     */
    public String toLineaListaString() {
        return String.format("[%d] %s - %s - %s", 
                           legajo, nombreCompleto, categoria, getEstadoConEmoji());
    }
    
    /**
     * MÉTODO PARA CSV - Exportación de datos
     * @return String en formato CSV
     */
    public String toCSVString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return String.format("%d,%s,%s,%s,%s,%s,%s,%.2f,%s,%s,%s",
                           legajo, nombreCompleto, dni, fechaIngreso.format(formatter),
                           tipoContrato.name(), estado, categoria, sueldoBasico,
                           obraSocial, art, fotoPath);
    }
    
    /**
     * MÉTODO PARA JSON - Formato para APIs
     * @return String en formato JSON simple
     */
    public String toJSONString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        StringBuilder json = new StringBuilder();
        
        json.append("{\n");
        json.append("  \"legajo\": ").append(legajo).append(",\n");
        json.append("  \"nombreCompleto\": \"").append(nombreCompleto).append("\",\n");
        json.append("  \"dni\": \"").append(dni).append("\",\n");
        json.append("  \"fechaIngreso\": \"").append(fechaIngreso.format(formatter)).append("\",\n");
        json.append("  \"tipoContrato\": \"").append(tipoContrato.name()).append("\",\n");
        json.append("  \"estado\": \"").append(estado).append("\",\n");
        json.append("  \"categoria\": \"").append(categoria).append("\",\n");
        json.append("  \"sueldoBasico\": ").append(sueldoBasico).append(",\n");
        json.append("  \"obraSocial\": \"").append(obraSocial).append("\",\n");
        json.append("  \"art\": \"").append(art).append("\"\n");
        json.append("}");
        
        return json.toString();
    }
}