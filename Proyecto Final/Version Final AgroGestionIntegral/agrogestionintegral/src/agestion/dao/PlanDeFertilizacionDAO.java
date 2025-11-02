// PlanDeFertilizacionDAO.java
package agestion.dao;

import agestion.modelo.PlanDeFertilizacion;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * DAO PARA GESTIÓN DE PLANES DE FERTILIZACIÓN
 * 
 * Data Access Object para operaciones CRUD de planes de fertilización
 * sin interfaz gráfica, solo lógica de acceso a datos.
 * 
 * @author Código Crítico 2025
 * @version 2.0
 */
public class PlanDeFertilizacionDAO {
    
    private ArrayList<PlanDeFertilizacion> planes = new ArrayList<>();
    private int siguienteId = 1;

    /**
     * CREA UN NUEVO PLAN DE FERTILIZACIÓN
     */
    public PlanDeFertilizacion crear(PlanDeFertilizacion plan) {
        plan.setId(siguienteId++);
        planes.add(plan);
        return plan;
    }

    /**
     * OBTIENE UN PLAN DE FERTILIZACIÓN POR ID
     */
    public PlanDeFertilizacion obtenerPorId(int id) {
        for (PlanDeFertilizacion plan : planes) {
            if (plan.getId() == id) {
                return plan;
            }
        }
        return null;
    }

    /**
     * ACTUALIZA UN PLAN DE FERTILIZACIÓN EXISTENTE
     */
    public boolean actualizar(PlanDeFertilizacion planActualizado) {
        for (int i = 0; i < planes.size(); i++) {
            if (planes.get(i).getId() == planActualizado.getId()) {
                planes.set(i, planActualizado);
                return true;
            }
        }
        return false;
    }

    /**
     * ELIMINA UN PLAN DE FERTILIZACIÓN POR ID
     */
    public boolean eliminar(int id) {
        return planes.removeIf(p -> p.getId() == id);
    }

    /**
     * OBTIENE TODOS LOS PLANES DE FERTILIZACIÓN
     */
    public ArrayList<PlanDeFertilizacion> obtenerTodos() {
        return new ArrayList<>(planes);
    }

    /**
     * OBTIENE PLANES DE FERTILIZACIÓN POR PARCELA
     */
    public ArrayList<PlanDeFertilizacion> obtenerPorParcela(int idParcela) {
        ArrayList<PlanDeFertilizacion> resultado = new ArrayList<>();
        for (PlanDeFertilizacion plan : planes) {
            if (plan.getParcela().getId() == idParcela) {
                resultado.add(plan);
            }
        }
        return resultado;
    }

    /**
     * OBTIENE PLANES DE FERTILIZACIÓN POR FERTILIZANTE
     */
    public ArrayList<PlanDeFertilizacion> obtenerPorFertilizante(int codigoFertilizante) {
        ArrayList<PlanDeFertilizacion> resultado = new ArrayList<>();
        for (PlanDeFertilizacion plan : planes) {
            if (plan.getFertilizante().getCodigo() == codigoFertilizante) {
                resultado.add(plan);
            }
        }
        return resultado;
    }

    /**
     * OBTIENE PLANES DE FERTILIZACIÓN POR ESTADO
     */
    public ArrayList<PlanDeFertilizacion> obtenerPorEstado(String estado) {
        ArrayList<PlanDeFertilizacion> resultado = new ArrayList<>();
        for (PlanDeFertilizacion plan : planes) {
            if (plan.getEstado().equals(estado)) {
                resultado.add(plan);
            }
        }
        return resultado;
    }

    /**
     * OBTIENE PLANES DE FERTILIZACIÓN PENDIENTES
     */
    public ArrayList<PlanDeFertilizacion> obtenerPendientes() {
        return obtenerPorEstado("Pendiente");
    }

    /**
     * OBTIENE PLANES DE FERTILIZACIÓN COMPLETADOS
     */
    public ArrayList<PlanDeFertilizacion> obtenerCompletados() {
        return obtenerPorEstado("Completado");
    }

    /**
     * OBTIENE PLANES DE FERTILIZACIÓN POR FECHA DE APLICACIÓN
     */
    public ArrayList<PlanDeFertilizacion> obtenerPorFechaAplicacion(LocalDate fecha) {
        ArrayList<PlanDeFertilizacion> resultado = new ArrayList<>();
        for (PlanDeFertilizacion plan : planes) {
            if (plan.getFechaAplicacion().equals(fecha)) {
                resultado.add(plan);
            }
        }
        return resultado;
    }

    /**
     * OBTIENE PLANES DE FERTILIZACIÓN POR RANGO DE FECHAS
     */
    public ArrayList<PlanDeFertilizacion> obtenerPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        ArrayList<PlanDeFertilizacion> resultado = new ArrayList<>();
        for (PlanDeFertilizacion plan : planes) {
            if (!plan.getFechaAplicacion().isBefore(fechaInicio) && !plan.getFechaAplicacion().isAfter(fechaFin)) {
                resultado.add(plan);
            }
        }
        return resultado;
    }

    /**
     * MARCA UN PLAN DE FERTILIZACIÓN COMO COMPLETADO
     */
    public boolean marcarComoCompletado(int id) {
        PlanDeFertilizacion plan = obtenerPorId(id);
        if (plan != null) {
            plan.setEstado("Completado");
            return true;
        }
        return false;
    }

    /**
     * CALCULA LA CANTIDAD TOTAL DE FERTILIZANTE PROGRAMADA
     */
    public double calcularTotalFertilizanteProgramado() {
        double total = 0;
        for (PlanDeFertilizacion plan : planes) {
            if ("Pendiente".equals(plan.getEstado())) {
                total += plan.getDosisKgPorHectarea() * plan.getParcela().getSuperficie();
            }
        }
        return total;
    }

    /**
     * OBTIENE LA CANTIDAD DE PLANES POR ESTADO
     */
    public Map<String, Integer> obtenerCantidadPorEstado() {
        Map<String, Integer> cantidadPorEstado = new HashMap<>();
        for (PlanDeFertilizacion plan : planes) {
            String estado = plan.getEstado();
            cantidadPorEstado.put(estado, cantidadPorEstado.getOrDefault(estado, 0) + 1);
        }
        return cantidadPorEstado;
    }

    /**
     * OBTIENE PLANES PRÓXIMOS A APLICAR (próximos 7 días)
     */
    public ArrayList<PlanDeFertilizacion> obtenerPlanesProximos() {
        LocalDate hoy = LocalDate.now();
        LocalDate enUnaSemana = hoy.plusDays(7);
        return obtenerPorRangoFechas(hoy, enUnaSemana);
    }

    /**
     * VERIFICA SI EXISTE UN PLAN DE FERTILIZACIÓN CON EL ID ESPECIFICADO
     */
    public boolean existePlan(int id) {
        return obtenerPorId(id) != null;
    }

    /**
     * OBTIENE EL NÚMERO TOTAL DE PLANES DE FERTILIZACIÓN
     */
    public int contarPlanes() {
        return planes.size();
    }

    /**
     * OBTIENE EL NÚMERO DE PLANES PENDIENTES
     */
    public int contarPlanesPendientes() {
        return obtenerPendientes().size();
    }
}