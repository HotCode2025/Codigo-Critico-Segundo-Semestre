// ProductoAgricolaDAO.java
package agestion.dao;

import agestion.modelo.ProductoAgricola;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * DAO PARA GESTIÓN DE PRODUCTOS AGRÍCOLAS
 * 
 * Data Access Object para operaciones CRUD de productos del inventario
 * sin interfaz gráfica, solo lógica de acceso a datos.
 * 
 * @author Código Crítico 2025
 * @version 2.0
 */
public class ProductoAgricolaDAO {
    
    private ArrayList<ProductoAgricola> productos = new ArrayList<>();
    private int siguienteCodigo = 1;

    /**
     * CREA UN NUEVO PRODUCTO
     */
    public ProductoAgricola crear(ProductoAgricola producto) {
        producto.setCodigo(siguienteCodigo++);
        productos.add(producto);
        return producto;
    }

    /**
     * OBTIENE UN PRODUCTO POR CÓDIGO
     */
    public ProductoAgricola obtenerPorCodigo(int codigo) {
        for (ProductoAgricola producto : productos) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        return null;
    }

    /**
     * OBTIENE UN PRODUCTO POR NOMBRE
     */
    public ProductoAgricola obtenerPorNombre(String nombre) {
        for (ProductoAgricola producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto;
            }
        }
        return null;
    }

    /**
     * ACTUALIZA UN PRODUCTO EXISTENTE
     */
    public boolean actualizar(ProductoAgricola productoActualizado) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo() == productoActualizado.getCodigo()) {
                productos.set(i, productoActualizado);
                return true;
            }
        }
        return false;
    }

    /**
     * ELIMINA UN PRODUCTO POR CÓDIGO
     */
    public boolean eliminar(int codigo) {
        return productos.removeIf(p -> p.getCodigo() == codigo);
    }

    /**
     * OBTIENE TODOS LOS PRODUCTOS
     */
    public ArrayList<ProductoAgricola> obtenerTodos() {
        return new ArrayList<>(productos);
    }

    /**
     * OBTIENE PRODUCTOS POR TIPO
     */
    public ArrayList<ProductoAgricola> obtenerPorTipo(String tipo) {
        ArrayList<ProductoAgricola> resultado = new ArrayList<>();
        for (ProductoAgricola producto : productos) {
            if (producto.getTipo().equalsIgnoreCase(tipo)) {
                resultado.add(producto);
            }
        }
        return resultado;
    }

    /**
     * OBTIENE PRODUCTOS CON STOCK BAJO
     */
    public ArrayList<ProductoAgricola> obtenerStockBajo(double limite) {
        ArrayList<ProductoAgricola> resultado = new ArrayList<>();
        for (ProductoAgricola producto : productos) {
            if (producto.getCantidadEnStock() < limite) {
                resultado.add(producto);
            }
        }
        return resultado;
    }

    /**
     * OBTIENE PRODUCTOS CON STOCK CRÍTICO (menos de 10 unidades)
     */
    public ArrayList<ProductoAgricola> obtenerStockCritico() {
        return obtenerStockBajo(10);
    }

    /**
     * ACTUALIZA EL STOCK DE UN PRODUCTO
     */
    public boolean actualizarStock(int codigo, double nuevaCantidad) {
        ProductoAgricola producto = obtenerPorCodigo(codigo);
        if (producto != null) {
            producto.setCantidadEnStock(nuevaCantidad);
            return true;
        }
        return false;
    }

    /**
     * INCREMENTA EL STOCK DE UN PRODUCTO
     */
    public boolean incrementarStock(int codigo, double cantidad) {
        ProductoAgricola producto = obtenerPorCodigo(codigo);
        if (producto != null) {
            producto.setCantidadEnStock(producto.getCantidadEnStock() + cantidad);
            return true;
        }
        return false;
    }

    /**
     * DECREMENTA EL STOCK DE UN PRODUCTO
     */
    public boolean decrementarStock(int codigo, double cantidad) {
        ProductoAgricola producto = obtenerPorCodigo(codigo);
        if (producto != null && producto.getCantidadEnStock() >= cantidad) {
            producto.setCantidadEnStock(producto.getCantidadEnStock() - cantidad);
            return true;
        }
        return false;
    }

    /**
     * OBTIENE EL STOCK TOTAL DE TODOS LOS PRODUCTOS
     */
    public double obtenerStockTotal() {
        double total = 0;
        for (ProductoAgricola producto : productos) {
            total += producto.getCantidadEnStock();
        }
        return total;
    }

    /**
     * OBTIENE LA CANTIDAD DE PRODUCTOS POR TIPO
     */
    public Map<String, Integer> obtenerCantidadPorTipo() {
        Map<String, Integer> cantidadPorTipo = new HashMap<>();
        for (ProductoAgricola producto : productos) {
            cantidadPorTipo.put(producto.getTipo(), 
                cantidadPorTipo.getOrDefault(producto.getTipo(), 0) + 1);
        }
        return cantidadPorTipo;
    }

    /**
     * OBTIENE EL STOCK TOTAL POR TIPO
     */
    public Map<String, Double> obtenerStockPorTipo() {
        Map<String, Double> stockPorTipo = new HashMap<>();
        for (ProductoAgricola producto : productos) {
            stockPorTipo.put(producto.getTipo(), 
                stockPorTipo.getOrDefault(producto.getTipo(), 0.0) + producto.getCantidadEnStock());
        }
        return stockPorTipo;
    }

    /**
     * VERIFICA SI EXISTE UN PRODUCTO CON EL CÓDIGO ESPECIFICADO
     */
    public boolean existeProducto(int codigo) {
        return obtenerPorCodigo(codigo) != null;
    }

    /**
     * VERIFICA SI EXISTE UN PRODUCTO CON EL NOMBRE ESPECIFICADO
     */
    public boolean existeProducto(String nombre) {
        return obtenerPorNombre(nombre) != null;
    }

    /**
     * OBTIENE EL NÚMERO TOTAL DE PRODUCTOS
     */
    public int contarProductos() {
        return productos.size();
    }

    /**
     * BUSCA PRODUCTOS POR TEXTO EN EL NOMBRE
     */
    public ArrayList<ProductoAgricola> buscarPorNombre(String texto) {
        ArrayList<ProductoAgricola> resultado = new ArrayList<>();
        for (ProductoAgricola producto : productos) {
            if (producto.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                resultado.add(producto);
            }
        }
        return resultado;
    }
}