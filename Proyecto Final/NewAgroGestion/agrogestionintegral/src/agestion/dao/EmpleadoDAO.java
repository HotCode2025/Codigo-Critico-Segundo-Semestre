// EmpleadoDAO.java
package agestion.dao;

import agestion.modelo.Empleado;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * DAO PARA GESTIÓN DE EMPLEADOS
 * 
 * Data Access Object para operaciones CRUD de empleados
 * sin interfaz gráfica, solo lógica de acceso a datos.
 * 
 * @author Código Crítico 2025
 * @version 2.0
 */
public class EmpleadoDAO {
    
    private ArrayList<Empleado> empleados = new ArrayList<>();
    private int siguienteLegajo = 1001;

    /**
     * CREA UN NUEVO EMPLEADO
     */
    public Empleado crear(Empleado empleado) {
        empleado.setLegajo(siguienteLegajo++);
        empleados.add(empleado);
        return empleado;
    }

    /**
     * OBTIENE UN EMPLEADO POR LEGAJO
     */
    public Empleado obtenerPorLegajo(int legajo) {
        for (Empleado empleado : empleados) {
            if (empleado.getLegajo() == legajo) {
                return empleado;
            }
        }
        return null;
    }

    /**
     * OBTIENE UN EMPLEADO POR DNI
     */
    public Empleado obtenerPorDni(String dni) {
        for (Empleado empleado : empleados) {
            if (empleado.getDni().equals(dni)) {
                return empleado;
            }
        }
        return null;
    }

    /**
     * ACTUALIZA UN EMPLEADO EXISTENTE
     */
    public boolean actualizar(Empleado empleadoActualizado) {
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getLegajo() == empleadoActualizado.getLegajo()) {
                empleados.set(i, empleadoActualizado);
                return true;
            }
        }
        return false;
    }

    /**
     * ELIMINA UN EMPLEADO POR LEGAJO
     */
    public boolean eliminar(int legajo) {
        return empleados.removeIf(e -> e.getLegajo() == legajo);
    }

    /**
     * OBTIENE TODOS LOS EMPLEADOS
     */
    public ArrayList<Empleado> obtenerTodos() {
        return new ArrayList<>(empleados);
    }

    /**
     * OBTIENE EMPLEADOS POR ESTADO
     */
    public ArrayList<Empleado> obtenerPorEstado(String estado) {
        ArrayList<Empleado> resultado = new ArrayList<>();
        for (Empleado empleado : empleados) {
            if (empleado.getEstado().equals(estado)) {
                resultado.add(empleado);
            }
        }
        return resultado;
    }

    /**
     * OBTIENE EMPLEADOS ACTIVOS
     */
    public ArrayList<Empleado> obtenerActivos() {
        return obtenerPorEstado("Activo");
    }

    /**
     * OBTIENE EMPLEADOS INACTIVOS
     */
    public ArrayList<Empleado> obtenerInactivos() {
        return obtenerPorEstado("Inactivo");
    }

    /**
     * OBTIENE EMPLEADOS POR TIPO DE CONTRATO
     */
    public ArrayList<Empleado> obtenerPorTipoContrato(Empleado.TipoContrato tipoContrato) {
        ArrayList<Empleado> resultado = new ArrayList<>();
        for (Empleado empleado : empleados) {
            if (empleado.getTipoContrato() == tipoContrato) {
                resultado.add(empleado);
            }
        }
        return resultado;
    }

    /**
     * OBTIENE EMPLEADOS POR CATEGORÍA
     */
    public ArrayList<Empleado> obtenerPorCategoria(String categoria) {
        ArrayList<Empleado> resultado = new ArrayList<>();
        for (Empleado empleado : empleados) {
            if (empleado.getCategoria().equalsIgnoreCase(categoria)) {
                resultado.add(empleado);
            }
        }
        return resultado;
    }

    /**
     * OBTIENE EMPLEADOS POR FECHA DE INGRESO
     */
    public ArrayList<Empleado> obtenerPorFechaIngreso(LocalDate fecha) {
        ArrayList<Empleado> resultado = new ArrayList<>();
        for (Empleado empleado : empleados) {
            if (empleado.getFechaIngreso().equals(fecha)) {
                resultado.add(empleado);
            }
        }
        return resultado;
    }

    /**
     * OBTIENE EMPLEADOS POR RANGO DE FECHAS DE INGRESO
     */
    public ArrayList<Empleado> obtenerPorRangoFechaIngreso(LocalDate fechaInicio, LocalDate fechaFin) {
        ArrayList<Empleado> resultado = new ArrayList<>();
        for (Empleado empleado : empleados) {
            if (!empleado.getFechaIngreso().isBefore(fechaInicio) && !empleado.getFechaIngreso().isAfter(fechaFin)) {
                resultado.add(empleado);
            }
        }
        return resultado;
    }

    /**
     * DA DE BAJA A UN EMPLEADO
     */
    public boolean darDeBaja(int legajo, LocalDate fechaBaja) {
        Empleado empleado = obtenerPorLegajo(legajo);
        if (empleado != null && "Activo".equals(empleado.getEstado())) {
            empleado.setEstado("Inactivo");
            empleado.setFechaBaja(fechaBaja);
            return true;
        }
        return false;
    }

    /**
     * REINCORPORA A UN EMPLEADO
     */
    public boolean reincorporar(int legajo) {
        Empleado empleado = obtenerPorLegajo(legajo);
        if (empleado != null && "Inactivo".equals(empleado.getEstado())) {
            empleado.setEstado("Activo");
            empleado.setFechaBaja(null);
            return true;
        }
        return false;
    }

    /**
     * CALCULA LA MASA SALARIAL TOTAL
     */
    public double calcularMasaSalarialTotal() {
        double total = 0;
        for (Empleado empleado : empleados) {
            if ("Activo".equals(empleado.getEstado())) {
                total += empleado.getSueldoBasico();
            }
        }
        return total;
    }

    /**
     * CALCULA LA MASA SALARIAL POR CATEGORÍA
     */
    public Map<String, Double> calcularMasaSalarialPorCategoria() {
        Map<String, Double> salarioPorCategoria = new HashMap<>();
        for (Empleado empleado : empleados) {
            if ("Activo".equals(empleado.getEstado())) {
                String categoria = empleado.getCategoria();
                salarioPorCategoria.put(categoria, salarioPorCategoria.getOrDefault(categoria, 0.0) + empleado.getSueldoBasico());
            }
        }
        return salarioPorCategoria;
    }

    /**
     * CALCULA LA MASA SALARIAL POR TIPO DE CONTRATO
     */
    public Map<Empleado.TipoContrato, Double> calcularMasaSalarialPorTipoContrato() {
        Map<Empleado.TipoContrato, Double> salarioPorContrato = new HashMap<>();
        for (Empleado empleado : empleados) {
            if ("Activo".equals(empleado.getEstado())) {
                Empleado.TipoContrato tipo = empleado.getTipoContrato();
                salarioPorContrato.put(tipo, salarioPorContrato.getOrDefault(tipo, 0.0) + empleado.getSueldoBasico());
            }
        }
        return salarioPorContrato;
    }

    /**
     * OBTIENE LA CANTIDAD DE EMPLEADOS POR ESTADO
     */
    public Map<String, Integer> obtenerCantidadPorEstado() {
        Map<String, Integer> cantidadPorEstado = new HashMap<>();
        for (Empleado empleado : empleados) {
            String estado = empleado.getEstado();
            cantidadPorEstado.put(estado, cantidadPorEstado.getOrDefault(estado, 0) + 1);
        }
        return cantidadPorEstado;
    }

    /**
     * OBTIENE LA CANTIDAD DE EMPLEADOS POR TIPO DE CONTRATO
     */
    public Map<Empleado.TipoContrato, Integer> obtenerCantidadPorTipoContrato() {
        Map<Empleado.TipoContrato, Integer> cantidadPorContrato = new HashMap<>();
        for (Empleado empleado : empleados) {
            Empleado.TipoContrato tipo = empleado.getTipoContrato();
            cantidadPorContrato.put(tipo, cantidadPorContrato.getOrDefault(tipo, 0) + 1);
        }
        return cantidadPorContrato;
    }

    /**
     * OBTIENE LA CANTIDAD DE EMPLEADOS POR CATEGORÍA
     */
    public Map<String, Integer> obtenerCantidadPorCategoria() {
        Map<String, Integer> cantidadPorCategoria = new HashMap<>();
        for (Empleado empleado : empleados) {
            String categoria = empleado.getCategoria();
            cantidadPorCategoria.put(categoria, cantidadPorCategoria.getOrDefault(categoria, 0) + 1);
        }
        return cantidadPorCategoria;
    }

    /**
     * CALCULA EL SUELDO PROMEDIO
     */
    public double calcularSueldoPromedio() {
        if (empleados.isEmpty()) return 0;
        return calcularMasaSalarialTotal() / obtenerActivos().size();
    }

    /**
     * VERIFICA SI EXISTE UN EMPLEADO CON EL LEGAJO ESPECIFICADO
     */
    public boolean existeEmpleado(int legajo) {
        return obtenerPorLegajo(legajo) != null;
    }

    /**
     * VERIFICA SI EXISTE UN EMPLEADO CON EL DNI ESPECIFICADO
     */
    public boolean existeEmpleado(String dni) {
        return obtenerPorDni(dni) != null;
    }

    /**
     * OBTIENE EL NÚMERO TOTAL DE EMPLEADOS
     */
    public int contarEmpleados() {
        return empleados.size();
    }

    /**
     * OBTIENE EL NÚMERO DE EMPLEADOS ACTIVOS
     */
    public int contarEmpleadosActivos() {
        return obtenerActivos().size();
    }

    /**
     * BUSCA EMPLEADOS POR NOMBRE
     */
    public ArrayList<Empleado> buscarPorNombre(String texto) {
        ArrayList<Empleado> resultado = new ArrayList<>();
        for (Empleado empleado : empleados) {
            if (empleado.getNombreCompleto().toLowerCase().contains(texto.toLowerCase())) {
                resultado.add(empleado);
            }
        }
        return resultado;
    }

    /**
     * OBTIENE EMPLEADOS CON MAYOR ANTIGÜEDAD
     */
    public ArrayList<Empleado> obtenerConMayorAntiguedad(int cantidad) {
        ArrayList<Empleado> copia = new ArrayList<>(empleados);
        copia.sort((e1, e2) -> e1.getFechaIngreso().compareTo(e2.getFechaIngreso()));
        
        ArrayList<Empleado> resultado = new ArrayList<>();
        for (int i = 0; i < Math.min(cantidad, copia.size()); i++) {
            resultado.add(copia.get(i));
        }
        return resultado;
    }
}