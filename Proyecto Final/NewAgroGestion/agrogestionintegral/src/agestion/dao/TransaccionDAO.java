// TransaccionDAO.java
package agestion.dao;

import agestion.modelo.Transaccion;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO PARA GESTIÓN DE TRANSACCIONES
 * 
 * Data Access Object para operaciones CRUD de transacciones financieras
 * sin interfaz gráfica, solo lógica de acceso a datos.
 * 
 * @author Código Crítico 2025
 * @version 2.0
 */
public class TransaccionDAO {
    
    private ArrayList<Transaccion> transacciones = new ArrayList<>();
    private int siguienteId = 1;

    /**
     * CREA UNA NUEVA TRANSACCIÓN
     */
    public Transaccion crear(Transaccion transaccion) {
        transaccion.setId(siguienteId++);
        transacciones.add(transaccion);
        return transaccion;
    }

    /**
     * OBTIENE UNA TRANSACCIÓN POR ID
     */
    public Transaccion obtenerPorId(int id) {
        for (Transaccion transaccion : transacciones) {
            if (transaccion.getId() == id) {
                return transaccion;
            }
        }
        return null;
    }

    /**
     * ACTUALIZA UNA TRANSACCIÓN EXISTENTE
     */
    public boolean actualizar(Transaccion transaccionActualizada) {
        for (int i = 0; i < transacciones.size(); i++) {
            if (transacciones.get(i).getId() == transaccionActualizada.getId()) {
                transacciones.set(i, transaccionActualizada);
                return true;
            }
        }
        return false;
    }

    /**
     * ELIMINA UNA TRANSACCIÓN POR ID
     */
    public boolean eliminar(int id) {
        return transacciones.removeIf(t -> t.getId() == id);
    }

    /**
     * OBTIENE TODAS LAS TRANSACCIONES
     */
    public ArrayList<Transaccion> obtenerTodos() {
        return new ArrayList<>(transacciones);
    }

    /**
     * OBTIENE TRANSACCIONES POR TIPO
     */
    public ArrayList<Transaccion> obtenerPorTipo(Transaccion.Tipo tipo) {
        ArrayList<Transaccion> resultado = new ArrayList<>();
        for (Transaccion transaccion : transacciones) {
            if (transaccion.getTipo() == tipo) {
                resultado.add(transaccion);
            }
        }
        return resultado;
    }

    /**
     * OBTIENE TRANSACCIONES POR FECHA
     */
    public ArrayList<Transaccion> obtenerPorFecha(LocalDate fecha) {
        ArrayList<Transaccion> resultado = new ArrayList<>();
        for (Transaccion transaccion : transacciones) {
            if (transaccion.getFecha().equals(fecha)) {
                resultado.add(transaccion);
            }
        }
        return resultado;
    }

    /**
     * OBTIENE TRANSACCIONES POR RANGO DE FECHAS
     */
    public ArrayList<Transaccion> obtenerPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        ArrayList<Transaccion> resultado = new ArrayList<>();
        for (Transaccion transaccion : transacciones) {
            if (!transaccion.getFecha().isBefore(fechaInicio) && !transaccion.getFecha().isAfter(fechaFin)) {
                resultado.add(transaccion);
            }
        }
        return resultado;
    }

    /**
     * CALCULA EL BALANCE TOTAL
     */
    public double calcularBalanceTotal() {
        double balance = 0;
        for (Transaccion transaccion : transacciones) {
            if (transaccion.getTipo() == Transaccion.Tipo.INGRESO) {
                balance += transaccion.getMontoTotal();
            } else {
                balance -= transaccion.getMontoTotal();
            }
        }
        return balance;
    }

    /**
     * OBTIENE EL TOTAL DE INGRESOS
     */
    public double calcularTotalIngresos() {
        double total = 0;
        for (Transaccion transaccion : transacciones) {
            if (transaccion.getTipo() == Transaccion.Tipo.INGRESO) {
                total += transaccion.getMontoTotal();
            }
        }
        return total;
    }

    /**
     * OBTIENE EL TOTAL DE EGRESOS
     */
    public double calcularTotalEgresos() {
        double total = 0;
        for (Transaccion transaccion : transacciones) {
            if (transaccion.getTipo() == Transaccion.Tipo.EGRESO) {
                total += transaccion.getMontoTotal();
            }
        }
        return total;
    }

    /**
     * OBTIENE EL TOTAL DE IVA POR TIPO
     */
    public double calcularIvaPorTipo(Transaccion.Tipo tipo) {
        double totalIva = 0;
        for (Transaccion transaccion : transacciones) {
            if (transaccion.getTipo() == tipo) {
                totalIva += transaccion.getMontoIva();
            }
        }
        return totalIva;
    }

    /**
     * OBTIENE EL NÚMERO TOTAL DE TRANSACCIONES
     */
    public int contarTransacciones() {
        return transacciones.size();
    }

    /**
     * VERIFICA SI EXISTE UNA TRANSACCIÓN CON EL ID ESPECIFICADO
     */
    public boolean existeTransaccion(int id) {
        return obtenerPorId(id) != null;
    }

    /**
     * OBTIENE TRANSACCIONES POR DESCRIPCIÓN (BÚSQUEDA PARCIAL)
     */
    public ArrayList<Transaccion> buscarPorDescripcion(String texto) {
        ArrayList<Transaccion> resultado = new ArrayList<>();
        for (Transaccion transaccion : transacciones) {
            if (transaccion.getDescripcion().toLowerCase().contains(texto.toLowerCase())) {
                resultado.add(transaccion);
            }
        }
        return resultado;
    }
}