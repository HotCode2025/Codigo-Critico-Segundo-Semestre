package agestion.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * MODELO DE MAQUINARIA - CLASE MEJORADA PARA INTERFAZ CON SOLAPAS
 * 
 * Representa una máquina o equipo agrícola con su estado y horas de uso.
 * Incluye métodos mejorados para presentación en interfaces gráficas.
 * 
 * @author Código Crítico 2025
 * @version 2.0
 */
public class Maquinaria {

    // --- ATRIBUTOS PRINCIPALES ---
    private int id;
    private String nombre;
    private String estado;
    private double horasDeUso;
    private String tipo;
    private String modelo;
    private String patente;
    private LocalDate fechaAdquisicion;
    private double costoCompra;
    private String proveedor;

    // --- CONSTRUCTORES ---
    
    /**
     * Constructor básico (compatible con versión anterior)
     * @param id
     */
    public Maquinaria(int id, String nombre, String estado, double horasDeUso) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.horasDeUso = horasDeUso;
        this.tipo = "No especificado";
        this.modelo = "No especificado";
        this.patente = "Sin patente";
        this.fechaAdquisicion = LocalDate.now();
        this.costoCompra = 0.0;
        this.proveedor = "No especificado";
    }
    
    /**
     * Constructor completo con todos los atributos
     */
    public Maquinaria(int id, String nombre, String estado, double horasDeUso, 
                     String tipo, String modelo, String patente, LocalDate fechaAdquisicion,
                     double costoCompra, String proveedor) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.horasDeUso = horasDeUso;
        this.tipo = tipo;
        this.modelo = modelo;
        this.patente = patente;
        this.fechaAdquisicion = fechaAdquisicion;
        this.costoCompra = costoCompra;
        this.proveedor = proveedor;
    }

    // --- MÉTODOS DE ACCESO (GETTERS Y SETTERS) ---
    public int getId() { 
        return id; 
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre() { 
        return nombre; 
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getEstado() { 
        return estado; 
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public double getHorasDeUso() { 
        return horasDeUso; 
    }
    
    public void setHorasDeUso(double horasDeUso) {
        this.horasDeUso = horasDeUso;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getModelo() {
        return modelo;
    }
    
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    public String getPatente() {
        return patente;
    }
    
    public void setPatente(String patente) {
        this.patente = patente;
    }
    
    public LocalDate getFechaAdquisicion() {
        return fechaAdquisicion;
    }
    
    public void setFechaAdquisicion(LocalDate fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }
    
    public double getCostoCompra() {
        return costoCompra;
    }
    
    public void setCostoCompra(double costoCompra) {
        this.costoCompra = costoCompra;
    }
    
    public String getProveedor() {
        return proveedor;
    }
    
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    // --- MÉTODOS DE NEGOCIO MEJORADOS ---
    
    /**
     * Obtiene el estado con emoji para mejor visualización
     * @return String con estado y emoji
     */
    public String getEstadoConEmoji() {
        switch (estado.toLowerCase()) {
            case "disponible": return "✅ Disponible";
            case "en mantenimiento": return "🔧 En Mantenimiento";
            case "en uso": return "🚜 En Uso";
            case "averiada": return "❌ Averiada";
            case "baja": return "💀 Dada de Baja";
            default: return "❓ " + estado;
        }
    }
    
    /**
     * Verifica si la máquina está disponible para uso
     * @return true si está disponible
     */
    public boolean estaDisponible() {
        return "Disponible".equalsIgnoreCase(estado);
    }
    
    /**
     * Calcula la antigüedad de la máquina en años
     * @return Años de antigüedad
     */
    public int calcularAntiguedad() {
        return LocalDate.now().getYear() - fechaAdquisicion.getYear();
    }
    
    /**
     * Agrega horas de uso a la máquina
     * @param horas Horas a agregar
     */
    public void agregarHorasUso(double horas) {
        if (horas > 0) {
            this.horasDeUso += horas;
        }
    }
    
    /**
     * Obtiene el tipo de máquina con emoji
     * @return String con tipo y emoji
     */
    public String getTipoConEmoji() {
        switch (tipo.toLowerCase()) {
            case "tractor": return "🚜 Tractor";
            case "cosechadora": return "🌾 Cosechadora";
            case "pulverizadora": return "💨 Pulverizadora";
            case "sembradora": return "🌱 Sembradora";
            case "camion": return "🚚 Camión";
            case "utv": return "🛻 UTV";
            default: return "🔧 " + tipo;
        }
    }

    // --- MÉTODOS DE PRESENTACIÓN MEJORADOS ---
    
    /**
     * MÉTODO TOSTRING MEJORADO - Para listas generales
     */
    @Override
    public String toString() {
        return String.format("🚜 %s | %s | %.1f hs | %s", 
                           nombre, getTipoConEmoji(), horasDeUso, getEstadoConEmoji());
    }
    
    /**
     * MÉTODO PARA TARJETA DE MAQUINARIA - Formato detallado
     * @return String formateado para mostrar en tarjetas
     */
    public String toTarjetaString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        StringBuilder sb = new StringBuilder();
        
        sb.append("╔══════════════════════════════════════╗\n");
        sb.append(String.format("║ 🚜  %-32s ║\n", nombre.toUpperCase()));
        sb.append("╠══════════════════════════════════════╣\n");
        sb.append(String.format("║ 📋 ID: %-29d ║\n", id));
        sb.append(String.format("║ 🏷️  Tipo: %-27s ║\n", getTipoConEmoji()));
        sb.append(String.format("║ 📊 Estado: %-26s ║\n", getEstadoConEmoji()));
        sb.append(String.format("║ ⏱️  Horas Uso: %-22.1f ║\n", horasDeUso));
        sb.append(String.format("║ 📅 Adquisición: %-21s ║\n", fechaAdquisicion.format(formatter)));
        sb.append(String.format("║ 🎯 Antigüedad: %-22d ║\n", calcularAntiguedad()));
        sb.append(String.format("║ 💰 Costo: $%-25.2f ║\n", costoCompra));
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
        sb.append("         FICHA TÉCNICA DE MAQUINARIA\n");
        sb.append("═".repeat(50)).append("\n");
        
        // Información Básica
        sb.append("\n📊 INFORMACIÓN BÁSICA:\n");
        sb.append("─".repeat(30)).append("\n");
        sb.append(String.format("  🚜 Nombre: %s\n", nombre));
        sb.append(String.format("  🔢 ID: %d\n", id));
        sb.append(String.format("  🏷️  Tipo: %s\n", getTipoConEmoji()));
        sb.append(String.format("  📄 Modelo: %s\n", modelo));
        sb.append(String.format("  🆔 Patente: %s\n", patente));
        
        // Estado y Uso
        sb.append("\n⚙️ ESTADO Y USO:\n");
        sb.append("─".repeat(30)).append("\n");
        sb.append(String.format("  📊 Estado: %s\n", getEstadoConEmoji()));
        sb.append(String.format("  ⏱️  Horas de Uso: %.1f hs\n", horasDeUso));
        sb.append(String.format("  📈 Disponible: %s\n", estaDisponible() ? "✅ Sí" : "❌ No"));
        
        // Información de Adquisición
        sb.append("\n💰 INFORMACIÓN DE ADQUISICIÓN:\n");
        sb.append("─".repeat(30)).append("\n");
        sb.append(String.format("  📅 Fecha Adquisición: %s\n", fechaAdquisicion.format(formatter)));
        sb.append(String.format("  🎯 Antigüedad: %d años\n", calcularAntiguedad()));
        sb.append(String.format("  💵 Costo de Compra: $%.2f\n", costoCompra));
        sb.append(String.format("  🏢 Proveedor: %s\n", proveedor));
        
        // Métricas de Rendimiento (simuladas)
        sb.append("\n📈 MÉTRICAS DE RENDIMIENTO:\n");
        sb.append("─".repeat(30)).append("\n");
        sb.append(String.format("  📊 Horas Promedio Mensual: %.1f hs\n", horasDeUso / Math.max(calcularAntiguedad() * 12, 1)));
        sb.append(String.format("  💰 Costo por Hora: $%.2f/hs\n", costoCompra / Math.max(horasDeUso, 1)));
        
        sb.append("═".repeat(50)).append("\n");
        
        return sb.toString();
    }
    
    /**
     * MÉTODO PARA LINEA DE LISTA - Formato compacto para JList
     * @return String compacto para listas
     */
    public String toLineaListaString() {
        return String.format("[%d] %s - %s - %.1f hs", 
                           id, nombre, getEstadoConEmoji(), horasDeUso);
    }
    
    /**
     * MÉTODO PARA CSV - Exportación de datos
     * @return String en formato CSV
     */
    public String toCSVString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return String.format("%d,%s,%s,%.1f,%s,%s,%s,%s,%.2f,%s",
                           id, nombre, estado, horasDeUso, tipo, modelo, patente,
                           fechaAdquisicion.format(formatter), costoCompra, proveedor);
    }
    
    /**
     * MÉTODO PARA INVENTARIO - Formato para reportes de inventario
     * @return String formateado para inventario
     */
    public String toInventarioString() {
        return String.format("│ %-4d │ %-20s │ %-15s │ %-8.1f │ %-18s │ %-12.2f │",
                           id, nombre, tipo, horasDeUso, estado, costoCompra);
    }
    
    /**
     * MÉTODO ESTÁTICO - Encabezado para inventario
     * @return Encabezado formateado
     */
    public static String getEncabezadoInventario() {
        return "┌──────┬──────────────────────┬─────────────────┬──────────┬────────────────────┬──────────────┐\n" +
               "│  ID  │       NOMBRE         │      TIPO       │  HORAS   │      ESTADO        │    COSTO     │\n" +
               "├──────┼──────────────────────┼─────────────────┼──────────┼────────────────────┼──────────────┤";
    }
    
    /**
     * MÉTODO ESTÁTICO - Pie para inventario
     * @return Pie formateado
     */
    public static String getPieInventario() {
        return "└──────┴──────────────────────┴─────────────────┴──────────┴────────────────────┴──────────────┘";
    }
}