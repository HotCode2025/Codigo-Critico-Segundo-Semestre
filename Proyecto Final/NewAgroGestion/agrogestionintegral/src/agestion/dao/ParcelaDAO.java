// ParcelaDAO.java
package agestion.dao;

import agestion.modelo.Parcela;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * DAO PARA GESTIÓN DE PARCELAS
 * 
 * Data Access Object para operaciones CRUD de parcelas agrícolas
 * sin interfaz gráfica, solo lógica de acceso a datos.
 * 
 * @author Código Crítico 2025
 * @version 2.0
 */
public class ParcelaDAO {
    
    private ArrayList<Parcela> parcelas = new ArrayList<>();
    private int siguienteId = 1;

    /**
     * CREA UNA NUEVA PARCELA
     */
    public Parcela crear(Parcela parcela) {
        parcela.setId(siguienteId++);
        parcelas.add(parcela);
        return parcela;
    }

    /**
     * OBTIENE UNA PARCELA POR ID
     */
    public Parcela obtenerPorId(int id) {
        for (Parcela parcela : parcelas) {
            if (parcela.getId() == id) {
                return parcela;
            }
        }
        return null;
    }

    /**
     * OBTIENE UNA PARCELA POR NOMBRE
     */
    public Parcela obtenerPorNombre(String nombre) {
        for (Parcela parcela : parcelas) {
            if (parcela.getNombre().equalsIgnoreCase(nombre)) {
                return parcela;
            }
        }
        return null;
    }

    /**
     * ACTUALIZA UNA PARCELA EXISTENTE
     */
    public boolean actualizar(Parcela parcelaActualizada) {
        for (int i = 0; i < parcelas.size(); i++) {
            if (parcelas.get(i).getId() == parcelaActualizada.getId()) {
                parcelas.set(i, parcelaActualizada);
                return true;
            }
        }
        return false;
    }

    /**
     * ELIMINA UNA PARCELA POR ID
     */
    public boolean eliminar(int id) {
        return parcelas.removeIf(p -> p.getId() == id);
    }

    /**
     * OBTIENE TODAS LAS PARCELAS
     */
    public ArrayList<Parcela> obtenerTodos() {
        return new ArrayList<>(parcelas);
    }

    /**
     * OBTIENE PARCELAS POR TIPO DE CULTIVO
     */
    public ArrayList<Parcela> obtenerPorTipoCultivo(String tipoCultivo) {
        ArrayList<Parcela> resultado = new ArrayList<>();
        for (Parcela parcela : parcelas) {
            if (parcela.getTipoCultivo().equalsIgnoreCase(tipoCultivo)) {
                resultado.add(parcela);
            }
        }
        return resultado;
    }

    /**
     * OBTIENE PARCELAS POR SUPERFICIE MÍNIMA
     */
    public ArrayList<Parcela> obtenerPorSuperficieMinima(double superficieMinima) {
        ArrayList<Parcela> resultado = new ArrayList<>();
        for (Parcela parcela : parcelas) {
            if (parcela.getSuperficie() >= superficieMinima) {
                resultado.add(parcela);
            }
        }
        return resultado;
    }

    /**
     * OBTIENE PARCELAS POR SUPERFICIE MÁXIMA
     */
    public ArrayList<Parcela> obtenerPorSuperficieMaxima(double superficieMaxima) {
        ArrayList<Parcela> resultado = new ArrayList<>();
        for (Parcela parcela : parcelas) {
            if (parcela.getSuperficie() <= superficieMaxima) {
                resultado.add(parcela);
            }
        }
        return resultado;
    }

    /**
     * CALCULA LA SUPERFICIE TOTAL DE TODAS LAS PARCELAS
     */
    public double calcularSuperficieTotal() {
        double total = 0;
        for (Parcela parcela : parcelas) {
            total += parcela.getSuperficie();
        }
        return total;
    }

    /**
     * CALCULA LA SUPERFICIE PROMEDIO POR PARCELA
     */
    public double calcularSuperficiePromedio() {
        if (parcelas.isEmpty()) return 0;
        return calcularSuperficieTotal() / parcelas.size();
    }

    /**
     * OBTIENE LA CANTIDAD DE PARCELAS POR TIPO DE CULTIVO
     */
    public Map<String, Integer> obtenerCantidadPorTipoCultivo() {
        Map<String, Integer> cantidadPorCultivo = new HashMap<>();
        for (Parcela parcela : parcelas) {
            String cultivo = parcela.getTipoCultivo();
            cantidadPorCultivo.put(cultivo, cantidadPorCultivo.getOrDefault(cultivo, 0) + 1);
        }
        return cantidadPorCultivo;
    }

    /**
     * OBTIENE LA SUPERFICIE TOTAL POR TIPO DE CULTIVO
     */
    public Map<String, Double> obtenerSuperficiePorTipoCultivo() {
        Map<String, Double> superficiePorCultivo = new HashMap<>();
        for (Parcela parcela : parcelas) {
            String cultivo = parcela.getTipoCultivo();
            superficiePorCultivo.put(cultivo, superficiePorCultivo.getOrDefault(cultivo, 0.0) + parcela.getSuperficie());
        }
        return superficiePorCultivo;
    }

    /**
     * OBTIENE LA PARCELA CON MAYOR SUPERFICIE
     */
    public Parcela obtenerParcelaMayorSuperficie() {
        if (parcelas.isEmpty()) return null;
        
        Parcela mayor = parcelas.get(0);
        for (Parcela parcela : parcelas) {
            if (parcela.getSuperficie() > mayor.getSuperficie()) {
                mayor = parcela;
            }
        }
        return mayor;
    }

    /**
     * OBTIENE LA PARCELA CON MENOR SUPERFICIE
     */
    public Parcela obtenerParcelaMenorSuperficie() {
        if (parcelas.isEmpty()) return null;
        
        Parcela menor = parcelas.get(0);
        for (Parcela parcela : parcelas) {
            if (parcela.getSuperficie() < menor.getSuperficie()) {
                menor = parcela;
            }
        }
        return menor;
    }

    /**
     * VERIFICA SI EXISTE UNA PARCELA CON EL ID ESPECIFICADO
     */
    public boolean existeParcela(int id) {
        return obtenerPorId(id) != null;
    }

    /**
     * VERIFICA SI EXISTE UNA PARCELA CON EL NOMBRE ESPECIFICADO
     */
    public boolean existeParcela(String nombre) {
        return obtenerPorNombre(nombre) != null;
    }

    /**
     * OBTIENE EL NÚMERO TOTAL DE PARCELAS
     */
    public int contarParcelas() {
        return parcelas.size();
    }

    /**
     * BUSCA PARCELAS POR NOMBRE
     */
    public ArrayList<Parcela> buscarPorNombre(String texto) {
        ArrayList<Parcela> resultado = new ArrayList<>();
        for (Parcela parcela : parcelas) {
            if (parcela.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                resultado.add(parcela);
            }
        }
        return resultado;
    }

    /**
     * OBTIENE PARCELAS CON CULTIVOS ESPECÍFICOS
     */
    public ArrayList<Parcela> obtenerParcelasConCultivos(String... cultivos) {
        ArrayList<Parcela> resultado = new ArrayList<>();
        for (Parcela parcela : parcelas) {
            for (String cultivo : cultivos) {
                if (parcela.getTipoCultivo().equalsIgnoreCase(cultivo)) {
                    resultado.add(parcela);
                    break;
                }
            }
        }
        return resultado;
    }
}