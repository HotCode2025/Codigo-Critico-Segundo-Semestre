package agestion.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * MODELO DE PARCELA - CLASE MEJORADA PARA INTERFAZ CON SOLAPAS
 * 
 * Representa una parcela agrícola con su información de cultivo y superficie.
 * Incluye métodos mejorados para presentación en interfaces gráficas.
 * 
 * @author Código Crítico 2025
 * @version 2.0
 */
public class Parcela {

    // --- ATRIBUTOS PRINCIPALES ---
    private int id;
    private String nombre;
    private double superficie;
    private String tipoCultivo;
    private String variedad;
    private LocalDate fechaSiembra;
    private LocalDate fechaCosechaEstimada;
    private String estado;
    private String sueloTipo;
    private double phSuelo;
    private String coordenadas;
    private String observaciones;

    // --- CONSTRUCTORES ---
    
    /**
     * Constructor básico (compatible con versión anterior)
     */
    public Parcela(int id, String nombre, double superficie, String tipoCultivo) {
        this.id = id;
        this.nombre = nombre;
        this.superficie = superficie;
        this.tipoCultivo = tipoCultivo;
        this.variedad = "No especificada";
        this.fechaSiembra = LocalDate.now();
        this.fechaCosechaEstimada = LocalDate.now().plusMonths(6);
        this.estado = "Activa";
        this.sueloTipo = "No especificado";
        this.phSuelo = 6.5;
        this.coordenadas = "No especificadas";
        this.observaciones = "";
    }
    
    /**
     * Constructor completo con todos los atributos
     */
    public Parcela(int id, String nombre, double superficie, String tipoCultivo,
                  String variedad, LocalDate fechaSiembra, LocalDate fechaCosechaEstimada,
                  String estado, String sueloTipo, double phSuelo, String coordenadas,
                  String observaciones) {
        this.id = id;
        this.nombre = nombre;
        this.superficie = superficie;
        this.tipoCultivo = tipoCultivo;
        this.variedad = variedad;
        this.fechaSiembra = fechaSiembra;
        this.fechaCosechaEstimada = fechaCosechaEstimada;
        this.estado = estado;
        this.sueloTipo = sueloTipo;
        this.phSuelo = phSuelo;
        this.coordenadas = coordenadas;
        this.observaciones = observaciones;
    }

    // --- MÉTODOS DE ACCESO (GETTERS Y SETTERS) ---
    public int getId() { 
        return id; 
    }
    
    public String getNombre() { 
        return nombre; 
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public double getSuperficie() { 
        return superficie; 
    }
    
    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }
    
    public String getTipoCultivo() { 
        return tipoCultivo; 
    }
    
    public void setTipoCultivo(String tipoCultivo) {
        this.tipoCultivo = tipoCultivo;
    }
    
    public String getVariedad() {
        return variedad;
    }
    
    public void setVariedad(String variedad) {
        this.variedad = variedad;
    }
    
    public LocalDate getFechaSiembra() {
        return fechaSiembra;
    }
    
    public void setFechaSiembra(LocalDate fechaSiembra) {
        this.fechaSiembra = fechaSiembra;
    }
    
    public LocalDate getFechaCosechaEstimada() {
        return fechaCosechaEstimada;
    }
    
    public void setFechaCosechaEstimada(LocalDate fechaCosechaEstimada) {
        this.fechaCosechaEstimada = fechaCosechaEstimada;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getSueloTipo() {
        return sueloTipo;
    }
    
    public void setSueloTipo(String sueloTipo) {
        this.sueloTipo = sueloTipo;
    }
    
    public double getPhSuelo() {
        return phSuelo;
    }
    
    public void setPhSuelo(double phSuelo) {
        this.phSuelo = phSuelo;
    }
    
    public String getCoordenadas() {
        return coordenadas;
    }
    
    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }
    
    public String getObservaciones() {
        return observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    // --- MÉTODOS DE NEGOCIO MEJORADOS ---
    
    /**
     * Obtiene el estado con emoji para mejor visualización
     * @return String con estado y emoji
     */
    public String getEstadoConEmoji() {
        switch (estado.toLowerCase()) {
            case "activa": return "✅ Activa";
            case "inactiva": return "❌ Inactiva";
            case "en descanso": return "💤 En Descanso";
            case "preparación": return "🔧 En Preparación";
            case "cosechada": return "🌾 Cosechada";
            default: return "❓ " + estado;
        }
    }
    
    /**
     * Obtiene el tipo de cultivo con emoji
     * @return String con cultivo y emoji
     */
    public String getTipoCultivoConEmoji() {
        switch (tipoCultivo.toLowerCase()) {
            case "viñedo": return "🍇 Viñedo";
            case "trigo": return "🌾 Trigo";
            case "maíz": return "🌽 Maíz";
            case "soja": return "🥜 Soja";
            case "girasol": return "🌻 Girasol";
            case "hortalizas": return "🥬 Hortalizas";
            case "frutales": return "🍎 Frutales";
            default: return "🌱 " + tipoCultivo;
        }
    }
    
    /**
     * Calcula los días hasta la cosecha estimada
     * @return Días hasta cosecha (negativo si ya pasó)
     */
    public long calcularDiasHastaCosecha() {
        return java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), fechaCosechaEstimada);
    }
    
    /**
     * Obtiene el estado de crecimiento basado en fechas
     * @return String con estado de crecimiento
     */
    public String getEstadoCrecimiento() {
        long diasDesdeSiembra = java.time.temporal.ChronoUnit.DAYS.between(fechaSiembra, LocalDate.now());
        long diasTotales = java.time.temporal.ChronoUnit.DAYS.between(fechaSiembra, fechaCosechaEstimada);
        
        if (diasDesdeSiembra < 0) return "⏳ No sembrada";
        if (diasDesdeSiembra < diasTotales * 0.25) return "🌱 Germinación";
        if (diasDesdeSiembra < diasTotales * 0.5) return "📈 Crecimiento";
        if (diasDesdeSiembra < diasTotales * 0.75) return "💪 Desarrollo";
        if (diasDesdeSiembra < diasTotales) return "🌾 Maduración";
        return "✅ Lista para cosecha";
    }
    
    /**
     * Verifica si la parcela está activa
     * @return true si está activa
     */
    public boolean estaActiva() {
        return "Activa".equalsIgnoreCase(estado);
    }

    // --- MÉTODOS DE PRESENTACIÓN MEJORADOS ---
    
    /**
     * MÉTODO TOSTRING MEJORADO - Para listas generales
     */
    @Override
    public String toString() {
        return String.format("🌿 %s | %.1f ha | %s | %s", 
                           nombre, superficie, getTipoCultivoConEmoji(), getEstadoConEmoji());
    }
    
    /**
     * MÉTODO PARA TARJETA DE PARCELA - Formato detallado
     * @return String formateado para mostrar en tarjetas
     */
    public String toTarjetaString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        StringBuilder sb = new StringBuilder();
        
        sb.append("╔══════════════════════════════════════╗\n");
        sb.append(String.format("║ 🌿  %-32s ║\n", nombre.toUpperCase()));
        sb.append("╠══════════════════════════════════════╣\n");
        sb.append(String.format("║ 📋 ID: %-29d ║\n", id));
        sb.append(String.format("║ 🏷️  Cultivo: %-24s ║\n", getTipoCultivoConEmoji()));
        sb.append(String.format("║ 📊 Estado: %-26s ║\n", getEstadoConEmoji()));
        sb.append(String.format("║ 📐 Superficie: %-21.1f ║\n", superficie));
        sb.append(String.format("║ 🌱 Variedad: %-23s ║\n", variedad));
        sb.append(String.format("║ 📅 Siembra: %-24s ║\n", fechaSiembra.format(formatter)));
        sb.append(String.format("║ ⏳ Cosecha: %-24s ║\n", fechaCosechaEstimada.format(formatter)));
        sb.append("╚══════════════════════════════════════╝");
        
        return sb.toString();
    }
    
    /**
     * MÉTODO PARA FICHA TÉCNICA - Información completa
     * @return String con todos los datos técnicos
     */
    public String toFichaTecnicaString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        StringBuilder sb = new StringBuilder();
        
        sb.append("═".repeat(50)).append("\n");
        sb.append("         FICHA TÉCNICA DE PARCELA\n");
        sb.append("═".repeat(50)).append("\n");
        
        // Información Básica
        sb.append("\n📊 INFORMACIÓN BÁSICA:\n");
        sb.append("─".repeat(30)).append("\n");
        sb.append(String.format("  🌿 Nombre: %s\n", nombre));
        sb.append(String.format("  🔢 ID: %d\n", id));
        sb.append(String.format("  📐 Superficie: %.1f hectáreas\n", superficie));
        sb.append(String.format("  📊 Estado: %s\n", getEstadoConEmoji()));
        
        // Información del Cultivo
        sb.append("\n🌱 INFORMACIÓN DEL CULTIVO:\n");
        sb.append("─".repeat(30)).append("\n");
        sb.append(String.format("  🏷️  Tipo: %s\n", getTipoCultivoConEmoji()));
        sb.append(String.format("  🌟 Variedad: %s\n", variedad));
        sb.append(String.format("  📅 Fecha Siembra: %s\n", fechaSiembra.format(formatter)));
        sb.append(String.format("  ⏳ Fecha Cosecha Estimada: %s\n", fechaCosechaEstimada.format(formatter)));
        sb.append(String.format("  📈 Estado Crecimiento: %s\n", getEstadoCrecimiento()));
        sb.append(String.format("  🎯 Días hasta Cosecha: %d\n", calcularDiasHastaCosecha()));
        
        // Información del Suelo
        sb.append("\n🌍 INFORMACIÓN DEL SUELO:\n");
        sb.append("─".repeat(30)).append("\n");
        sb.append(String.format("  🏞️  Tipo de Suelo: %s\n", sueloTipo));
        sb.append(String.format("  🧪 pH del Suelo: %.1f\n", phSuelo));
        
        // Información de Ubicación
        sb.append("\n📍 INFORMACIÓN DE UBICACIÓN:\n");
        sb.append("─".repeat(30)).append("\n");
        sb.append(String.format("  🗺️  Coordenadas: %s\n", coordenadas));
        
        // Información Adicional
        if (observaciones != null && !observaciones.isEmpty()) {
            sb.append("\n📝 OBSERVACIONES:\n");
            sb.append("─".repeat(30)).append("\n");
            sb.append(String.format("  %s\n", observaciones));
        }
        
        sb.append("═".repeat(50)).append("\n");
        
        return sb.toString();
    }
    
    /**
     * MÉTODO PARA LINEA DE LISTA - Formato compacto para JList
     * @return String compacto para listas
     */
    public String toLineaListaString() {
        return String.format("[%d] %s - %.1f ha - %s", 
                           id, nombre, superficie, getTipoCultivoConEmoji());
    }
    
    /**
     * MÉTODO PARA CSV - Exportación de datos
     * @return String en formato CSV
     */
    public String toCSVString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return String.format("%d,%s,%.1f,%s,%s,%s,%s,%s,%s,%.1f,%s",
                           id, nombre, superficie, tipoCultivo, variedad,
                           fechaSiembra.format(formatter), fechaCosechaEstimada.format(formatter),
                           estado, sueloTipo, phSuelo, coordenadas);
    }
    
    /**
     * MÉTODO PARA MAPA DE CULTIVOS - Formato para visualización en mapa
     * @return String formateado para mapas
     */
    public String toMapaString() {
        return String.format("📍 %s\n🌱 %s - %.1f ha\n📊 %s", 
                           nombre, getTipoCultivoConEmoji(), superficie, getEstadoCrecimiento());
    }

    public void setId(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}