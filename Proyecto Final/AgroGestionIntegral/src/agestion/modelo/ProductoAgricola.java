package agestion.modelo;

public class ProductoAgricola {
    
    // --- 1. ATRIBUTOS ---
    // Son las características de cada producto. Los ponemos en 'private'
    // para que solo se puedan modificar a través de los métodos de la clase.
    private int codigo;
    private String nombre;
    private String tipo;
    private double cantidadEnStock;

    // --- 2. CONSTRUCTOR ---
    // Es un método especial que sirve para crear nuevos objetos de esta clase.
    // Recibe los valores iniciales para los atributos.
    public ProductoAgricola(int codigo, String nombre, String tipo, double cantidadEnStock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantidadEnStock = cantidadEnStock;
    }

    // --- 3. MÉTODOS GETTERS Y SETTERS ---
    // Nos permiten obtener (get) y establecer (set) los valores de los atributos de forma controlada.
    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }


    public String getTipo() {
        return tipo;
    }

    public double getCantidadEnStock() {
        return cantidadEnStock;
    }

    public void setCantidadEnStock(double cantidadEnStock) {
        this.cantidadEnStock = cantidadEnStock;
    }
    
    // --- 4. MÉTODO toString ---
    // Este método ya lo tenías y está perfecto. Sirve para representar el objeto como texto.
    @Override
    public String toString() {
        return "Cód: " + codigo + " | Producto: " + nombre + " | Tipo: " + tipo + " | Stock: " + cantidadEnStock;
    }
}