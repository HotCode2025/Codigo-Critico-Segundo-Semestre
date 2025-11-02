// PlanDeRiegoDAO.java
package agestion.dao;

import agestion.modelo.PlanDeRiego;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * DAO PARA GESTIÓN DE PLANES DE RIEGO
 * 
 * Data Access Object para operaciones CRUD de planes de riego
 * sin interfaz gráfica, solo lógica de acceso a datos.
 * 
 * @author Código Crítico 2025
 * @version 2.0
 */
public class PlanDeRiegoDAO {
    
    private ArrayList<PlanDeRiego> planes = new ArrayList<>();
    private int siguienteId = 1;

    /**
     * CREA UN NUEVO PLAN DE RIEGO
     */
    public PlanDeRiego crear(PlanDeRiego plan) {
        plan.setId(siguienteId++);
        planes.add(plan);
        return plan;
    }

    /**
     * OBTIENE UN PLAN DE RIEGO POR ID
     */
    public PlanDeRiego obtenerPorId(int id) {
        for (PlanDeRiego plan : planes) {
            if (plan.getId() == id) {
                return plan;
            }
        }
        return null;
    }

    /**
     * ACTUALIZA UN PLAN DE RIEGO EXISTENTE
     */
    public boolean actualizar(PlanDeRiego planActualizado) {
        for (int i = 0; i < planes.size(); i++) {
            if (planes.get(i).getId() == planActualizado.getId()) {
                planes.set(i, planActualizado);
                return true;
            }
        }
        return false;
    }

    /**
     * ELIMINA UN PLAN DE RIEGO POR ID
     */
    public boolean eliminar(int id) {
        return planes.removeIf(p -> p.getId() == id);
    }

    /**
     * OBTIENE TODOS LOS PLANES DE RIEGO
     */
    public ArrayList<PlanDeRiego> obtenerTodos() {
        return new ArrayList<>(planes);
    }

    /**
     * OBTIENE PLANES DE RIEGO POR PARCELA
     */
    public ArrayList<PlanDeRiego> obtenerPorParcela(int idParcela) {
        ArrayList<PlanDeRiego> resultado = new ArrayList<>();
        for (PlanDeRiego plan : planes) {
            if (plan.getParcela().getId() == idParcela) {
                resultado.add(plan);
            }
        }
        return resultado;
    }

    /**
     * OBTIENE PLANES DE RIEGO POR ESTADO
     */
    public ArrayList<PlanDeRiego> obtenerPorEstado(String estado) {
        ArrayList<PlanDeRiego> resultado = new ArrayList<>();
        for (PlanDeRiego plan : planes) {
            if (plan.getEstado().equals(estado)) {
                resultado.add(plan);
            }
        }
        return resultado;
    }

    /**
     * OBTIENE PLANES DE RIEGO ACTIVOS
     */
    public ArrayList<PlanDeRiego> obtenerActivos() {
        return obtenerPorEstado("Activo");
    }

    /**
     * OBTIENE PLANES DE RIEGO COMPLETADOS
     */
    public ArrayList<PlanDeRiego> obtenerCompletados() {
        return obtenerPorEstado("Completado");
    }

    /**
     * OBTIENE PLANES DE RIEGO CANCELADOS
     */
    public ArrayList<PlanDeRiego> obtenerCancelados() {
        return obtenerPorEstado("Cancelado");
    }

    /**
     * MARCA UN PLAN DE RIEGO COMO COMPLETADO
     */
    public boolean marcarComoCompletado(int id) {
        PlanDeRiego plan = obtenerPorId(id);
        if (plan != null) {
            plan.setEstado("Completado");
            return true;
        }
        return false;
    }

    /**
     * MARCA UN PLAN DE RIEGO COMO CANCELADO
     */
    public boolean marcarComoCancelado(int id) {
        PlanDeRiego plan = obtenerPorId(id);
        if (plan != null) {
            plan.setEstado("Cancelado");
            return true;
        }
        return false;
    }

    /**
     * REACTIVA UN PLAN DE RIEGO
     */
    public boolean reactivar(int id) {
        PlanDeRiego plan = obtenerPorId(id);
        if (plan != null) {
            plan.setEstado("Activo");
            return true;
        }
        return false;
    }

    /**
     * OBTIENE LA CANTIDAD DE PLANES POR ESTADO
     */
    public Map<String, Integer> obtenerCantidadPorEstado() {
        Map<String, Integer> cantidadPorEstado = new HashMap<>();
        for (PlanDeRiego plan : planes) {
            String estado = plan.getEstado();
            cantidadPorEstado.put(estado, cantidadPorEstado.getOrDefault(estado, 0) + 1);
        }
        return cantidadPorEstado;
    }

    /**
     * OBTIENE LA CANTIDAD DE PLANES POR PARCELA
     */
    public Map<Integer, Integer> obtenerCantidadPorParcela() {
        Map<Integer, Integer> cantidadPorParcela = new HashMap<>();
        for (PlanDeRiego plan : planes) {
            int idParcela = plan.getParcela().getId();
            cantidadPorParcela.put(idParcela, cantidadPorParcela.getOrDefault(idParcela, 0) + 1);
        }
        return cantidadPorParcela;
    }

    /**
     * CALCULA EL TOTAL DE HORAS DE RIEGO PROGRAMADAS
     */
    public double calcularTotalHorasRiego() {
        double total = 0;
        for (PlanDeRiego plan : planes) {
            if ("Activo".equals(plan.getEstado())) {
                total += plan.getDuracionHoras();
            }
        }
        return total;
    }

    /**
     * CALCULA EL PROMEDIO DE FRECUENCIA DE RIEGO
     */
    public double calcularFrecuenciaPromedio() {
        if (planes.isEmpty()) return 0;
        
        double total = 0;
        for (PlanDeRiego plan : planes) {
            total += plan.getFrecuenciaDias();
        }
        return total / planes.size();
    }

    /**
     * VERIFICA SI EXISTE UN PLAN DE RIEGO CON EL ID ESPECIFICADO
     */
    public boolean existePlan(int id) {
        return obtenerPorId(id) != null;
    }

    /**
     * OBTIENE EL NÚMERO TOTAL DE PLANES DE RIEGO
     */
    public int contarPlanes() {
        return planes.size();
    }

    /**
     * OBTIENE EL NÚMERO DE PLANES ACTIVOS
     */
    public int contarPlanesActivos() {
        return obtenerActivos().size();
    }

    /**
     * OBTIENE PLANES QUE REQUIEREN RIEGO INMEDIATO (frecuencia <= 2 días)
     */
    public ArrayList<PlanDeRiego> obtenerPlanesRiegoInmediato() {
        ArrayList<PlanDeRiego> resultado = new ArrayList<>();
        for (PlanDeRiego plan : planes) {
            if ("Activo".equals(plan.getEstado()) && plan.getFrecuenciaDias() <= 2) {
                resultado.add(plan);
            }
        }
        return resultado;
    }
}