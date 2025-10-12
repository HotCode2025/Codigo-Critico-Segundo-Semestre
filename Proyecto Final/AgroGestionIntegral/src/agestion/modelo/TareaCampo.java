package agestion.modelo;

import java.time.LocalDate;

public class TareaCampo {

    private int id;
    private LocalDate fecha;
    private String descripcion;
    private String nombreOperador; // <-- ATRIBUTO NUEVO
    
    private Parcela parcelaAsociada;
    private ProductoAgricola productoUtilizado;
    private Maquinaria maquinariaUtilizada;
    
    private double cantidadProductoConsumido;

    /**
     * Constructor actualizado para incluir el nombre del operador.
     */
    public TareaCampo(int id, LocalDate fecha, String descripcion, String nombreOperador, // <-- PARÁMETRO NUEVO
                      Parcela parcela, ProductoAgricola producto, Maquinaria maquina, 
                      double cantidadConsumida) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.nombreOperador = nombreOperador; // <-- ASIGNACIÓN NUEVA
        this.parcelaAsociada = parcela;
        this.productoUtilizado = producto;
        this.maquinariaUtilizada = maquina;
        this.cantidadProductoConsumido = cantidadConsumida;
    }

    // --- Getters y Setters (con el nuevo atributo) ---
    public String getNombreOperador() {
        return nombreOperador;
    }

    public void setNombreOperador(String nombreOperador) {
        this.nombreOperador = nombreOperador;
    }
    
    // ... (los otros getters y setters no cambian) ...
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public LocalDate getFecha() { return fecha; }
    // ... etc ...


    /**
     * Método toString actualizado para mostrar el operador.
     */
    @Override
    public String toString() {
        return "Tarea ID: " + id + " | Fecha: " + fecha + " | Operador: " + nombreOperador + "\n" + // <-- CAMBIO
               "  - Descripción: " + descripcion + "\n" +
               "  - Parcela: " + parcelaAsociada.getNombre() + "\n" +
               "  - Producto: " + productoUtilizado.getNombre() + " (" + cantidadProductoConsumido + " unidades)\n" +
               "  - Máquina: " + maquinariaUtilizada.getNombre();
    }
}