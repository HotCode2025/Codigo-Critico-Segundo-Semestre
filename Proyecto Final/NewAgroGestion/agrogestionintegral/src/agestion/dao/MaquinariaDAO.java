// MaquinariaDAO.java
package agestion.dao;

import agestion.modelo.Maquinaria;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * DAO PARA GESTIÓN DE MAQUINARIA
 * 
 * Data Access Object para operaciones CRUD de maquinaria agrícola
 * sin interfaz gráfica, solo lógica de acceso a datos.
 * 
 * @author Código Crítico 2025
 * @version 2.0
 */
public class MaquinariaDAO {
    
    private ArrayList<Maquinaria> maquinarias = new ArrayList<>();
    private int siguienteId = 1;

    /**
     * CREA UNA NUEVA MÁQUINA
     */
    public Maquinaria crear(Maquinaria maquinaria) {
        maquinaria.setId(siguienteId++);
        maquinarias.add(maquinaria);
        return maquinaria;
    }

    /**
     * OBTIENE UNA MÁQUINA POR ID
     */
    public Maquinaria obtenerPorId(int id) {
        for (Maquinaria maquinaria : maquinarias) {
            if (maquinaria.getId() == id) {
                return maquinaria;
            }
        }
        return null;
    }

    /**
     * ACTUALIZA UNA MÁQUINA EXISTENTE
     */
    public boolean actualizar(Maquinaria maquinariaActualizada) {
        for (int i = 0; i < maquinarias.size(); i++) {
            if (maquinarias.get(i).getId() == maquinariaActualizada.getId()) {
                maquinarias.set(i, maquinariaActualizada);
                return true;
            }
        }
        return false;
    }

    /**
     * ELIMINA UNA MÁQUINA POR ID
     */
    public boolean eliminar(int id) {
        return maquinarias.removeIf(m -> m.getId() == id);
    }

    /**
     * OBTIENE TODAS LAS MÁQUINAS
     */
    public ArrayList<Maquinaria> obtenerTodos() {
        return new ArrayList<>(maquinarias);
    }

    /**
     * OBTIENE MÁQUINAS POR ESTADO
     */
    public ArrayList<Maquinaria> obtenerPorEstado(String estado) {
        ArrayList<Maquinaria> resultado = new ArrayList<>();
        for (Maquinaria maquinaria : maquinarias) {
            if (maquinaria.getEstado().equals(estado)) {
                resultado.add(maquinaria);
            }
        }
        return resultado;
    }

    /**
     * OBTIENE MÁQUINAS DISPONIBLES
     */
    public ArrayList<Maquinaria> obtenerDisponibles() {
        return obtenerPorEstado("Disponible");
    }

    /**
     * OBTIENE MÁQUINAS EN MANTENIMIENTO
     */
    public ArrayList<Maquinaria> obtenerEnMantenimiento() {
        return obtenerPorEstado("En Mantenimiento");
    }

    /**
     * OBTIENE MÁQUINAS EN USO
     */
    public ArrayList<Maquinaria> obtenerEnUso() {
        return obtenerPorEstado("En Uso");
    }

    /**
     * OBTIENE MÁQUINAS CON MÁS HORAS DE USO
     */
    public ArrayList<Maquinaria> obtenerConMasHorasUso(int limite) {
        ArrayList<Maquinaria> resultado = new ArrayList<>();
        for (Maquinaria maquinaria : maquinarias) {
            if (maquinaria.getHorasDeUso() > limite) {
                resultado.add(maquinaria);
            }
        }
        return resultado;
    }

    /**
     * OBTIENE MÁQUINAS QUE REQUIEREN MANTENIMIENTO (más de 500 horas)
     */
    public ArrayList<Maquinaria> obtenerQueRequierenMantenimiento() {
        return obtenerConMasHorasUso(500);
    }

    /**
     * ACTUALIZA LAS HORAS DE USO DE UNA MÁQUINA
     */
    public boolean actualizarHorasUso(int id, double horas) {
        Maquinaria maquinaria = obtenerPorId(id);
        if (maquinaria != null) {
            maquinaria.setHorasDeUso(maquinaria.getHorasDeUso() + horas);
            return true;
        }
        return false;
    }

    /**
     * CAMBIA EL ESTADO DE UNA MÁQUINA
     */
    public boolean cambiarEstado(int id, String nuevoEstado) {
        Maquinaria maquinaria = obtenerPorId(id);
        if (maquinaria != null) {
            maquinaria.setEstado(nuevoEstado);
            return true;
        }
        return false;
    }

    /**
     * OBTIENE EL TOTAL DE HORAS DE USO DE TODAS LAS MÁQUINAS
     */
    public double calcularTotalHorasUso() {
        double total = 0;
        for (Maquinaria maquinaria : maquinarias) {
            total += maquinaria.getHorasDeUso();
        }
        return total;
    }

    /**
     * OBTIENE EL PROMEDIO DE HORAS DE USO POR MÁQUINA
     */
    public double calcularPromedioHorasUso() {
        if (maquinarias.isEmpty()) return 0;
        return calcularTotalHorasUso() / maquinarias.size();
    }

    /**
     * OBTIENE LA CANTIDAD DE MÁQUINAS POR ESTADO
     */
    public Map<String, Integer> obtenerCantidadPorEstado() {
        Map<String, Integer> cantidadPorEstado = new HashMap<>();
        for (Maquinaria maquinaria : maquinarias) {
            String estado = maquinaria.getEstado();
            cantidadPorEstado.put(estado, cantidadPorEstado.getOrDefault(estado, 0) + 1);
        }
        return cantidadPorEstado;
    }

    /**
     * VERIFICA SI EXISTE UNA MÁQUINA CON EL ID ESPECIFICADO
     */
    public boolean existeMaquinaria(int id) {
        return obtenerPorId(id) != null;
    }

    /**
     * OBTIENE EL NÚMERO TOTAL DE MÁQUINAS
     */
    public int contarMaquinarias() {
        return maquinarias.size();
    }

    /**
     * OBTIENE EL NÚMERO DE MÁQUINAS DISPONIBLES
     */
    public int contarMaquinariasDisponibles() {
        return obtenerDisponibles().size();
    }

    /**
     * BUSCA MÁQUINAS POR NOMBRE
     */
    public ArrayList<Maquinaria> buscarPorNombre(String texto) {
        ArrayList<Maquinaria> resultado = new ArrayList<>();
        for (Maquinaria maquinaria : maquinarias) {
            if (maquinaria.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                resultado.add(maquinaria);
            }
        }
        return resultado;
    }
}