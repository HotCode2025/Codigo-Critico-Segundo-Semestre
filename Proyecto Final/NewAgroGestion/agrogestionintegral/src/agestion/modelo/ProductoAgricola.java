// ProductoAgricola.java
package agestion.modelo;

/**
 * MODELO PARA PRODUCTOS AGRÍCOLAS
 * 
 * Representa productos del inventario como fertilizantes, semillas,
 * fitosanitarios y otros insumos agrícolas.
 * 
 * @author Código Crítico 2025
 * @version 2.0
 */
public class ProductoAgricola {
    private int codigo;
    private String nombre;
    private String tipo; // "Fertilizante", "Semilla", "Fitosanitario", "Otro"
    private double cantidadEnStock;

    /**
     * CONSTRUCTOR COMPLETO PARA PRODUCTO AGRÍCOLA
     */
    public ProductoAgricola(int codigo, String nombre, String tipo, double cantidadEnStock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantidadEnStock = cantidadEnStock;
    }

    // Getters y Setters
    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public double getCantidadEnStock() { return cantidadEnStock; }
    public void setCantidadEnStock(double cantidadEnStock) { this.cantidadEnStock = cantidadEnStock; }

    /**
     * REPRESENTACIÓN EN STRING DEL PRODUCTO
     */
    @Override
    public String toString() {
        return String.format(
            "Producto #%d | %s | Tipo: %s | Stock: %.2f",
            codigo, nombre, tipo, cantidadEnStock
        );
    }
}