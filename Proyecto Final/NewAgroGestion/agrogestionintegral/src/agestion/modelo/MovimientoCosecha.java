// MovimientoCosecha.java
package agestion.modelo;

import java.time.LocalDate;

/**
 * MODELO PARA MOVIMIENTOS DE COSECHA
 * 
 * Representa un movimiento de cosecha con información completa
 * de logística y transporte.
 * 
 * @author Código Crítico 2025
 * @version 2.0
 */
public class MovimientoCosecha {
    private int id;
    private LocalDate fecha;
    private Parcela parcela;
    private String producto;
    private double kilosNetos;
    private String numeroRemito;
    private String transportista;
    private String patenteVehiculo;
    private String codigoDTVe;
    private String destino;

    /**
     * CONSTRUCTOR COMPLETO PARA MOVIMIENTO DE COSECHA
     */
    public MovimientoCosecha(int id, LocalDate fecha, Parcela parcela, String producto,
                            double kilosNetos, String numeroRemito, String transportista,
                            String patenteVehiculo, String codigoDTVe, String destino) {
        this.id = id;
        this.fecha = fecha;
        this.parcela = parcela;
        this.producto = producto;
        this.kilosNetos = kilosNetos;
        this.numeroRemito = numeroRemito;
        this.transportista = transportista;
        this.patenteVehiculo = patenteVehiculo;
        this.codigoDTVe = codigoDTVe;
        this.destino = destino;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Parcela getParcela() { return parcela; }
    public void setParcela(Parcela parcela) { this.parcela = parcela; }

    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }

    public double getKilosNetos() { return kilosNetos; }
    public void setKilosNetos(double kilosNetos) { this.kilosNetos = kilosNetos; }

    public String getNumeroRemito() { return numeroRemito; }
    public void setNumeroRemito(String numeroRemito) { this.numeroRemito = numeroRemito; }

    public String getTransportista() { return transportista; }
    public void setTransportista(String transportista) { this.transportista = transportista; }

    public String getPatenteVehiculo() { return patenteVehiculo; }
    public void setPatenteVehiculo(String patenteVehiculo) { this.patenteVehiculo = patenteVehiculo; }

    public String getCodigoDTVe() { return codigoDTVe; }
    public void setCodigoDTVe(String codigoDTVe) { this.codigoDTVe = codigoDTVe; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    /**
     * REPRESENTACIÓN EN STRING DEL MOVIMIENTO
     */
    @Override
    public String toString() {
        return String.format(
            "Cosecha #%d | Fecha: %s | Parcela: %s\nProducto: %s | Kilos: %.2f\nRemito: %s | Transportista: %s\nPatente: %s | DTVe: %s | Destino: %s",
            id, fecha, parcela.getNombre(), producto, kilosNetos, numeroRemito,
            transportista, patenteVehiculo, codigoDTVe, destino
        );
    }
}