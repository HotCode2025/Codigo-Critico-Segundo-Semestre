// TareaCampo.java
package agestion.modelo;

import java.time.LocalDate;

/**
 * MODELO PARA TAREAS DE CAMPO
 * 
 * Representa una tarea agrícola realizada en una parcela específica,
 * utilizando productos y maquinaria determinados.
 * 
 * @author Código Crítico 2025
 * @version 2.0
 */
public class TareaCampo {
    private int id;
    private LocalDate fecha;
    private String descripcion;
    private String operador;
    private Parcela parcela;
    private ProductoAgricola productoUtilizado;
    private Maquinaria maquinariaUtilizada;
    private double cantidadProducto;

    /**
     * CONSTRUCTOR COMPLETO PARA TAREA DE CAMPO
     */
    public TareaCampo(int id, LocalDate fecha, String descripcion, String operador,
                     Parcela parcela, ProductoAgricola productoUtilizado,
                     Maquinaria maquinariaUtilizada, double cantidadProducto) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.operador = operador;
        this.parcela = parcela;
        this.productoUtilizado = productoUtilizado;
        this.maquinariaUtilizada = maquinariaUtilizada;
        this.cantidadProducto = cantidadProducto;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getOperador() { return operador; }
    public void setOperador(String operador) { this.operador = operador; }

    public Parcela getParcela() { return parcela; }
    public void setParcela(Parcela parcela) { this.parcela = parcela; }

    public ProductoAgricola getProductoUtilizado() { return productoUtilizado; }
    public void setProductoUtilizado(ProductoAgricola productoUtilizado) { this.productoUtilizado = productoUtilizado; }

    public Maquinaria getMaquinariaUtilizada() { return maquinariaUtilizada; }
    public void setMaquinariaUtilizada(Maquinaria maquinariaUtilizada) { this.maquinariaUtilizada = maquinariaUtilizada; }

    public double getCantidadProducto() { return cantidadProducto; }
    public void setCantidadProducto(double cantidadProducto) { this.cantidadProducto = cantidadProducto; }

    /**
     * REPRESENTACIÓN EN STRING DE LA TAREA
     */
    @Override
    public String toString() {
        return String.format(
            "Tarea #%d | Fecha: %s | Parcela: %s\nOperador: %s | Producto: %s (%.2f)\nMaquinaria: %s\nDescripción: %s",
            id, fecha, parcela.getNombre(), operador, productoUtilizado.getNombre(), 
            cantidadProducto, maquinariaUtilizada.getNombre(), descripcion
        );
    }
}