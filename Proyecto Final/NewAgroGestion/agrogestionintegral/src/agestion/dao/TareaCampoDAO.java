// TareaCampoDAO.java
package agestion.dao;

import agestion.modelo.TareaCampo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * DAO PARA GESTIÓN DE TAREAS DE CAMPO
 * 
 * Data Access Object para operaciones CRUD de tareas agrícolas
 * sin interfaz gráfica, solo lógica de acceso a datos.
 * 
 * @author Código Crítico 2025
 * @version 2.0
 */
public class TareaCampoDAO {
    
    private ArrayList<TareaCampo> tareas = new ArrayList<>();
    private int siguienteId = 1;

    /**
     * CREA UNA NUEVA TAREA DE CAMPO
     */
    public TareaCampo crear(TareaCampo tarea) {
        tarea.setId(siguienteId++);
        tareas.add(tarea);
        return tarea;
    }

    /**
     * OBTIENE UNA TAREA POR ID
     */
    public TareaCampo obtenerPorId(int id) {
        for (TareaCampo tarea : tareas) {
            if (tarea.getId() == id) {
                return tarea;
            }
        }
        return null;
    }

    /**
     * ACTUALIZA UNA TAREA EXISTENTE
     */
    public boolean actualizar(TareaCampo tareaActualizada) {
        for (int i = 0; i < tareas.size(); i++) {
            if (tareas.get(i).getId() == tareaActualizada.getId()) {
                tareas.set(i, tareaActualizada);
                return true;
            }
        }
        return false;
    }

    /**
     * ELIMINA UNA TAREA POR ID
     */
    public boolean eliminar(int id) {
        return tareas.removeIf(t -> t.getId() == id);
    }

    /**
     * OBTIENE TODAS LAS TAREAS
     */
    public ArrayList<TareaCampo> obtenerTodos() {
        return new ArrayList<>(tareas);
    }

    /**
     * OBTIENE TAREAS POR FECHA
     */
    public ArrayList<TareaCampo> obtenerPorFecha(LocalDate fecha) {
        ArrayList<TareaCampo> resultado = new ArrayList<>();
        for (TareaCampo tarea : tareas) {
            if (tarea.getFecha().equals(fecha)) {
                resultado.add(tarea);
            }
        }
        return resultado;
    }

    /**
     * OBTIENE TAREAS POR RANGO DE FECHAS
     */
    public ArrayList<TareaCampo> obtenerPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        ArrayList<TareaCampo> resultado = new ArrayList<>();
        for (TareaCampo tarea : tareas) {
            if (!tarea.getFecha().isBefore(fechaInicio) && !tarea.getFecha().isAfter(fechaFin)) {
                resultado.add(tarea);
            }
        }
        return resultado;
    }

    /**
     * OBTIENE TAREAS POR PARCELA
     */
    public ArrayList<TareaCampo> obtenerPorParcela(int idParcela) {
        ArrayList<TareaCampo> resultado = new ArrayList<>();
        for (TareaCampo tarea : tareas) {
            if (tarea.getParcela().getId() == idParcela) {
                resultado.add(tarea);
            }
        }
        return resultado;
    }

    /**
     * OBTIENE TAREAS POR OPERADOR
     */
    public ArrayList<TareaCampo> obtenerPorOperador(String operador) {
        ArrayList<TareaCampo> resultado = new ArrayList<>();
        for (TareaCampo tarea : tareas) {
            if (tarea.getOperador().equalsIgnoreCase(operador)) {
                resultado.add(tarea);
            }
        }
        return resultado;
    }

    /**
     * OBTIENE TAREAS POR PRODUCTO UTILIZADO
     */
    public ArrayList<TareaCampo> obtenerPorProducto(int codigoProducto) {
        ArrayList<TareaCampo> resultado = new ArrayList<>();
        for (TareaCampo tarea : tareas) {
            if (tarea.getProductoUtilizado().getCodigo() == codigoProducto) {
                resultado.add(tarea);
            }
        }
        return resultado;
    }

    /**
     * OBTIENE TAREAS POR MAQUINARIA UTILIZADA
     */
    public ArrayList<TareaCampo> obtenerPorMaquinaria(int idMaquinaria) {
        ArrayList<TareaCampo> resultado = new ArrayList<>();
        for (TareaCampo tarea : tareas) {
            if (tarea.getMaquinariaUtilizada().getId() == idMaquinaria) {
                resultado.add(tarea);
            }
        }
        return resultado;
    }

    /**
     * OBTIENE LA CANTIDAD DE TAREAS POR PARCELA
     */
    public Map<Integer, Integer> obtenerCantidadTareasPorParcela() {
        Map<Integer, Integer> cantidadPorParcela = new HashMap<>();
        for (TareaCampo tarea : tareas) {
            int idParcela = tarea.getParcela().getId();
            cantidadPorParcela.put(idParcela, cantidadPorParcela.getOrDefault(idParcela, 0) + 1);
        }
        return cantidadPorParcela;
    }

    /**
     * OBTIENE LA CANTIDAD DE TAREAS POR OPERADOR
     */
    public Map<String, Integer> obtenerCantidadTareasPorOperador() {
        Map<String, Integer> cantidadPorOperador = new HashMap<>();
        for (TareaCampo tarea : tareas) {
            String operador = tarea.getOperador();
            cantidadPorOperador.put(operador, cantidadPorOperador.getOrDefault(operador, 0) + 1);
        }
        return cantidadPorOperador;
    }

    /**
     * OBTIENE EL CONSUMO TOTAL DE PRODUCTOS
     */
    public double obtenerConsumoTotalProductos() {
        double total = 0;
        for (TareaCampo tarea : tareas) {
            total += tarea.getCantidadProducto();
        }
        return total;
    }

    /**
     * OBTIENE EL CONSUMO DE PRODUCTOS POR CÓDIGO DE PRODUCTO
     */
    public double obtenerConsumoPorProducto(int codigoProducto) {
        double total = 0;
        for (TareaCampo tarea : tareas) {
            if (tarea.getProductoUtilizado().getCodigo() == codigoProducto) {
                total += tarea.getCantidadProducto();
            }
        }
        return total;
    }

    /**
     * VERIFICA SI EXISTE UNA TAREA CON EL ID ESPECIFICADO
     */
    public boolean existeTarea(int id) {
        return obtenerPorId(id) != null;
    }

    /**
     * OBTIENE EL NÚMERO TOTAL DE TAREAS
     */
    public int contarTareas() {
        return tareas.size();
    }

    /**
     * OBTIENE TAREAS RECIENTES (ÚLTIMOS 30 DÍAS)
     */
    public ArrayList<TareaCampo> obtenerTareasRecientes() {
        LocalDate fechaLimite = LocalDate.now().minusDays(30);
        ArrayList<TareaCampo> resultado = new ArrayList<>();
        for (TareaCampo tarea : tareas) {
            if (!tarea.getFecha().isBefore(fechaLimite)) {
                resultado.add(tarea);
            }
        }
        return resultado;
    }
}