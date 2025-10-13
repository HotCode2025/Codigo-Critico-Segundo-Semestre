package agestion.modelo;

import java.time.LocalDate;

public class MovimientoCosecha {

    private int id;
    private LocalDate fechaCosecha;
    private Parcela parcela;
    private String productoCosechado; // ej: "Uva Malbec", "Durazno para industria"
    private double kilos;
    
    // Datos de Logística
    private String numeroRemito;
    private String nombreTransportista;
    private String patenteVehiculo;
    private String codigoDTVe;
    private String destino;
    private String estado; // "En Finca", "En Tránsito", "Entregado"

    public MovimientoCosecha(int id, LocalDate fechaCosecha, Parcela parcela, String productoCosechado, double kilos, 
                             String numeroRemito, String nombreTransportista, String patenteVehiculo, 
                             String codigoDTVe, String destino) {
        this.id = id;
        this.fechaCosecha = fechaCosecha;
        this.parcela = parcela;
        this.productoCosechado = productoCosechado;
        this.kilos = kilos;
        this.numeroRemito = numeroRemito;
        this.nombreTransportista = nombreTransportista;
        this.patenteVehiculo = patenteVehiculo;
        this.codigoDTVe = codigoDTVe;
        this.destino = destino;
        this.estado = "En Finca"; // El estado inicial por defecto
    }

    // --- Getters y Setters (solo los necesarios por ahora) ---
    public int getId() { return id; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "ID: " + id + " | Fecha: " + fechaCosecha + " | " + productoCosechado + " (" + kilos + " kg)\n" +
               "  - Origen: " + parcela.getNombre() + " -> Destino: " + destino + "\n" +
               "  - Remito: " + numeroRemito + " | DTVe: " + codigoDTVe + "\n" +
               "  - Transporte: " + nombreTransportista + " (" + patenteVehiculo + ") | Estado: [" + estado + "]";
    }
}