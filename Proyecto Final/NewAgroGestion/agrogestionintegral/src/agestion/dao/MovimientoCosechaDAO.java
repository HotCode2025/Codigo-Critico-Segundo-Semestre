// MovimientoCosechaDAO.java
package agestion.dao;

import agestion.modelo.MovimientoCosecha;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * DAO PARA GESTIÓN DE MOVIMIENTOS DE COSECHA
 * 
 * Data Access Object para operaciones CRUD de movimientos de cosecha
 * sin interfaz gráfica, solo lógica de acceso a datos.
 * 
 * @author Código Crítico 2025
 * @version 2.0
 */
public class MovimientoCosechaDAO {
    
    private ArrayList<MovimientoCosecha> movimientos = new ArrayList<>();
    private int siguienteId = 1;

    /**
     * CREA UN NUEVO MOVIMIENTO DE COSECHA
     */
    public MovimientoCosecha crear(MovimientoCosecha movimiento) {
        movimiento.setId(siguienteId++);
        movimientos.add(movimiento);
        return movimiento;
    }

    /**
     * OBTIENE UN MOVIMIENTO DE COSECHA POR ID
     */
    public MovimientoCosecha obtenerPorId(int id) {
        for (MovimientoCosecha movimiento : movimientos) {
            if (movimiento.getId() == id) {
                return movimiento;
            }
        }
        return null;
    }

    /**
     * ACTUALIZA UN MOVIMIENTO DE COSECHA EXISTENTE
     */
    public boolean actualizar(MovimientoCosecha movimientoActualizado) {
        for (int i = 0; i < movimientos.size(); i++) {
            if (movimientos.get(i).getId() == movimientoActualizado.getId()) {
                movimientos.set(i, movimientoActualizado);
                return true;
            }
        }
        return false;
    }

    /**
     * ELIMINA UN MOVIMIENTO DE COSECHA POR ID
     */
    public boolean eliminar(int id) {
        return movimientos.removeIf(m -> m.getId() == id);
    }

    /**
     * OBTIENE TODOS LOS MOVIMIENTOS DE COSECHA
     */
    public ArrayList<MovimientoCosecha> obtenerTodos() {
        return new ArrayList<>(movimientos);
    }

    /**
     * OBTIENE MOVIMIENTOS DE COSECHA POR PARCELA
     */
    public ArrayList<MovimientoCosecha> obtenerPorParcela(int idParcela) {
        ArrayList<MovimientoCosecha> resultado = new ArrayList<>();
        for (MovimientoCosecha movimiento : movimientos) {
            if (movimiento.getParcela().getId() == idParcela) {
                resultado.add(movimiento);
            }
        }
        return resultado;
    }

    /**
     * OBTIENE MOVIMIENTOS DE COSECHA POR PRODUCTO
     */
    public ArrayList<MovimientoCosecha> obtenerPorProducto(String producto) {
        ArrayList<MovimientoCosecha> resultado = new ArrayList<>();
        for (MovimientoCosecha movimiento : movimientos) {
            if (movimiento.getProducto().equalsIgnoreCase(producto)) {
                resultado.add(movimiento);
            }
        }
        return resultado;
    }

    /**
     * OBTIENE MOVIMIENTOS DE COSECHA POR FECHA
     */
    public ArrayList<MovimientoCosecha> obtenerPorFecha(LocalDate fecha) {
        ArrayList<MovimientoCosecha> resultado = new ArrayList<>();
        for (MovimientoCosecha movimiento : movimientos) {
            if (movimiento.getFecha().equals(fecha)) {
                resultado.add(movimiento);
            }
        }
        return resultado;
    }

    /**
     * OBTIENE MOVIMIENTOS DE COSECHA POR RANGO DE FECHAS
     */
    public ArrayList<MovimientoCosecha> obtenerPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        ArrayList<MovimientoCosecha> resultado = new ArrayList<>();
        for (MovimientoCosecha movimiento : movimientos) {
            if (!movimiento.getFecha().isBefore(fechaInicio) && !movimiento.getFecha().isAfter(fechaFin)) {
                resultado.add(movimiento);
            }
        }
        return resultado;
    }

    /**
     * OBTIENE MOVIMIENTOS DE COSECHA POR DESTINO
     */
    public ArrayList<MovimientoCosecha> obtenerPorDestino(String destino) {
        ArrayList<MovimientoCosecha> resultado = new ArrayList<>();
        for (MovimientoCosecha movimiento : movimientos) {
            if (movimiento.getDestino().equalsIgnoreCase(destino)) {
                resultado.add(movimiento);
            }
        }
        return resultado;
    }

    /**
     * OBTIENE MOVIMIENTOS DE COSECHA POR TRANSPORTISTA
     */
    public ArrayList<MovimientoCosecha> obtenerPorTransportista(String transportista) {
        ArrayList<MovimientoCosecha> resultado = new ArrayList<>();
        for (MovimientoCosecha movimiento : movimientos) {
            if (movimiento.getTransportista().equalsIgnoreCase(transportista)) {
                resultado.add(movimiento);
            }
        }
        return resultado;
    }

    /**
     * CALCULA EL TOTAL DE KILOS COSECHADOS
     */
    public double calcularTotalKilosCosechados() {
        double total = 0;
        for (MovimientoCosecha movimiento : movimientos) {
            total += movimiento.getKilosNetos();
        }
        return total;
    }

    /**
     * CALCULA EL TOTAL DE KILOS COSECHADOS POR PRODUCTO
     */
    public Map<String, Double> calcularTotalKilosPorProducto() {
        Map<String, Double> kilosPorProducto = new HashMap<>();
        for (MovimientoCosecha movimiento : movimientos) {
            String producto = movimiento.getProducto();
            kilosPorProducto.put(producto, kilosPorProducto.getOrDefault(producto, 0.0) + movimiento.getKilosNetos());
        }
        return kilosPorProducto;
    }

    /**
     * CALCULA EL TOTAL DE KILOS COSECHADOS POR PARCELA
     */
    public Map<Integer, Double> calcularTotalKilosPorParcela() {
        Map<Integer, Double> kilosPorParcela = new HashMap<>();
        for (MovimientoCosecha movimiento : movimientos) {
            int idParcela = movimiento.getParcela().getId();
            kilosPorParcela.put(idParcela, kilosPorParcela.getOrDefault(idParcela, 0.0) + movimiento.getKilosNetos());
        }
        return kilosPorParcela;
    }

    /**
     * OBTIENE EL PROMEDIO DE KILOS POR MOVIMIENTO
     */
    public double calcularPromedioKilosPorMovimiento() {
        if (movimientos.isEmpty()) return 0;
        return calcularTotalKilosCosechados() / movimientos.size();
    }

    /**
     * OBTIENE MOVIMIENTOS RECIENTES (ÚLTIMOS 30 DÍAS)
     */
    public ArrayList<MovimientoCosecha> obtenerMovimientosRecientes() {
        LocalDate fechaLimite = LocalDate.now().minusDays(30);
        ArrayList<MovimientoCosecha> resultado = new ArrayList<>();
        for (MovimientoCosecha movimiento : movimientos) {
            if (!movimiento.getFecha().isBefore(fechaLimite)) {
                resultado.add(movimiento);
            }
        }
        return resultado;
    }

    /**
     * VERIFICA SI EXISTE UN MOVIMIENTO CON EL ID ESPECIFICADO
     */
    public boolean existeMovimiento(int id) {
        return obtenerPorId(id) != null;
    }

    /**
     * OBTIENE EL NÚMERO TOTAL DE MOVIMIENTOS
     */
    public int contarMovimientos() {
        return movimientos.size();
    }

    /**
     * OBTIENE EL NÚMERO DE MOVIMIENTOS POR MES
     */
    public Map<String, Integer> contarMovimientosPorMes() {
        Map<String, Integer> movimientosPorMes = new HashMap<>();
        for (MovimientoCosecha movimiento : movimientos) {
            String mes = movimiento.getFecha().getMonth().toString() + " " + movimiento.getFecha().getYear();
            movimientosPorMes.put(mes, movimientosPorMes.getOrDefault(mes, 0) + 1);
        }
        return movimientosPorMes;
    }
}