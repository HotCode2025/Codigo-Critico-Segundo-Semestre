package agestion.servicios;

import agestion.modelo.ProductoAgricola;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * GESTIÓN DE STOCK CON INTERFAZ DE SOLAPAS
 * 
 * Sistema completo de inventario y control de stock
 * con interfaz organizada en pestañas para todas las operaciones.
 * 
 * @author Código Crítico 2025
 * @version 2.0
 */
public class GestionStock {
    
    private ArrayList<ProductoAgricola> inventario = new ArrayList<>();

    // Componentes de la interfaz
    private JTextField txtNombreProducto;
    private JTextField txtTipoProducto;
    private JTextField txtCantidadInicial;
    private JTextArea areaStock;
    private JTextArea areaMovimientos;
    private JTextField txtProductoBuscar;
    private JTextField txtCantidadModificar;

    /**
     * MUESTRA LA INTERFAZ COMPLETA CON SOLAPAS
     */
    public void mostrarInterfazCompleta() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Gestión de Stock - Control de Inventario");
        dialog.setModal(true);
        dialog.setSize(600, 600);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Agregar solapas
        tabbedPane.addTab("📦 Agregar Producto", crearPanelAgregarProducto());
        tabbedPane.addTab("📋 Inventario Completo", crearPanelInventario());
        tabbedPane.addTab("🔄 Movimientos de Stock", crearPanelMovimientos());
        tabbedPane.addTab("📊 Análisis de Stock", crearPanelAnalisis());

        dialog.add(tabbedPane, BorderLayout.CENTER);
        
        // Botones inferiores
        JPanel panelBotones = crearPanelBotones(dialog);
        dialog.add(panelBotones, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    /**
     * CREA EL PANEL PARA AGREGAR PRODUCTOS
     */
    private JPanel crearPanelAgregarProducto() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        inicializarComponentes();

        // Nombre del producto
        panel.add(new JLabel("Nombre del producto:"));
        panel.add(txtNombreProducto);

        // Tipo de producto
        panel.add(new JLabel("Tipo (Fertilizante, Semilla, etc.):"));
        panel.add(txtTipoProducto);

        // Cantidad inicial
        panel.add(new JLabel("Cantidad inicial:"));
        panel.add(txtCantidadInicial);

        // Botón de agregar
        JButton btnAgregar = new JButton("✅ Agregar Producto");
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProductoDesdeInterfaz();
            }
        });
        panel.add(new JLabel()); // Espacio vacío
        panel.add(btnAgregar);

        return panel;
    }

    /**
     * CREA EL PANEL DE INVENTARIO COMPLETO
     */
    private JPanel crearPanelInventario() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        areaStock = new JTextArea(20, 60);
        areaStock.setEditable(false);
        areaStock.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(areaStock);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botones de control
        JPanel panelBotones = new JPanel();
        
        JButton btnActualizar = new JButton("🔄 Actualizar Inventario");
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarInventario();
            }
        });
        
        JButton btnExportar = new JButton("💾 Exportar Inventario");
        btnExportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportarInventario();
            }
        });
        
        JButton btnStockBajo = new JButton("⚠️ Stock Crítico");
        btnStockBajo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarStockBajo();
            }
        });
        
        panelBotones.add(btnActualizar);
        panelBotones.add(btnExportar);
        panelBotones.add(btnStockBajo);
        panel.add(panelBotones, BorderLayout.SOUTH);

        // Cargar inventario inicial
        actualizarInventario();

        return panel;
    }

    /**
     * CREA EL PANEL DE MOVIMIENTOS DE STOCK
     */
    private JPanel crearPanelMovimientos() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        areaMovimientos = new JTextArea(15, 60);
        areaMovimientos.setEditable(false);
        areaMovimientos.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        
        panel.add(new JScrollPane(areaMovimientos), BorderLayout.CENTER);

        // Panel de controles para modificar stock
        JPanel panelControles = new JPanel(new GridLayout(2, 3, 10, 10));
        
        panelControles.add(new JLabel("Producto a modificar:"));
        txtProductoBuscar = new JTextField();
        panelControles.add(txtProductoBuscar);
        
        panelControles.add(new JLabel("")); // Espacio vacío
        
        panelControles.add(new JLabel("Cantidad a agregar/restar:"));
        txtCantidadModificar = new JTextField();
        panelControles.add(txtCantidadModificar);
        
        JButton btnModificarStock = new JButton("📝 Modificar Stock");
        btnModificarStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarStockDesdeInterfaz();
            }
        });
        panelControles.add(btnModificarStock);

        panel.add(panelControles, BorderLayout.NORTH);

        // Cargar movimientos iniciales
        actualizarMovimientos();

        return panel;
    }

    /**
     * CREA EL PANEL DE ANÁLISIS DE STOCK
     */
    private JPanel crearPanelAnalisis() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextArea areaAnalisis = new JTextArea();
        areaAnalisis.setEditable(false);
        areaAnalisis.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        
        // Calcular análisis
        String analisis = calcularAnalisisStock();
        areaAnalisis.setText(analisis);
        
        panel.add(new JScrollPane(areaAnalisis), BorderLayout.CENTER);

        // Botón para actualizar análisis
        JButton btnActualizar = new JButton("📊 Actualizar Análisis");
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nuevoAnalisis = calcularAnalisisStock();
                areaAnalisis.setText(nuevoAnalisis);
            }
        });
        
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnActualizar);
        panel.add(panelBoton, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * INICIALIZA LOS COMPONENTES DE LA INTERFAZ
     */
    private void inicializarComponentes() {
        txtNombreProducto = new JTextField();
        txtTipoProducto = new JTextField();
        txtCantidadInicial = new JTextField();
    }

    /**
     * AGREGA UN PRODUCTO DESDE LA INTERFAZ
     */
    private void agregarProductoDesdeInterfaz() {
        try {
            // Validaciones
            if (txtNombreProducto.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese el nombre del producto", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (txtTipoProducto.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese el tipo de producto", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double cantidad = Double.parseDouble(txtCantidadInicial.getText());
            if (cantidad < 0) {
                JOptionPane.showMessageDialog(null, "La cantidad no puede ser negativa", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener datos
            String nombre = txtNombreProducto.getText();
            String tipo = txtTipoProducto.getText();

            // Crear producto
            int codigo = inventario.size() + 1;
            ProductoAgricola nuevoProducto = new ProductoAgricola(codigo, nombre, tipo, cantidad);
            inventario.add(nuevoProducto);

            // Limpiar campos
            limpiarCamposProducto();
            
            // Actualizar vistas
            actualizarInventario();
            actualizarMovimientos();

            JOptionPane.showMessageDialog(null, "✅ Producto '" + nombre + "' agregado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La cantidad debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * ACTUALIZA EL INVENTARIO COMPLETO
     */
    private void actualizarInventario() {
        if (inventario.isEmpty()) {
            areaStock.setText("El inventario está vacío. Agregue productos primero.");
            return;
        }

        StringBuilder lista = new StringBuilder();
        lista.append("=== INVENTARIO ACTUAL - TODOS LOS PRODUCTOS ===\n\n");
        
        for (ProductoAgricola producto : inventario) {
            lista.append(producto.toString()).append("\n");
        }

        areaStock.setText(lista.toString());
    }

    /**
     * ACTUALIZA LOS MOVIMIENTOS DE STOCK
     */
    private void actualizarMovimientos() {
        // En una implementación real, aquí se mostrarían los movimientos históricos
        StringBuilder movimientos = new StringBuilder();
        movimientos.append("=== MOVIMIENTOS DE STOCK ===\n\n");
        movimientos.append("Esta funcionalidad mostrará el historial completo de:\n");
        movimientos.append("- Entradas de stock (compras, recepciones)\n");
        movimientos.append("- Salidas de stock (consumo, ventas)\n");
        movimientos.append("- Ajustes de inventario\n");
        movimientos.append("- Transferencias entre almacenes\n\n");
        movimientos.append("Total de productos en inventario: ").append(inventario.size());

        areaMovimientos.setText(movimientos.toString());
    }

    /**
     * MODIFICA EL STOCK DESDE LA INTERFAZ
     */
    private void modificarStockDesdeInterfaz() {
        try {
            if (txtProductoBuscar.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese el nombre del producto", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (txtCantidadModificar.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese la cantidad a modificar", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String nombreProducto = txtProductoBuscar.getText();
            double cantidad = Double.parseDouble(txtCantidadModificar.getText());

            // Buscar producto
            ProductoAgricola producto = buscarProductoPorNombre(nombreProducto);
            if (producto == null) {
                JOptionPane.showMessageDialog(null, "No se encontró el producto: " + nombreProducto, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verificar que no quede stock negativo
            double nuevoStock = producto.getCantidadEnStock() + cantidad;
            if (nuevoStock < 0) {
                JOptionPane.showMessageDialog(null, 
                    "No se puede tener stock negativo.\nStock actual: " + producto.getCantidadEnStock(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Modificar stock
            producto.setCantidadEnStock(nuevoStock);

            // Limpiar campos
            txtProductoBuscar.setText("");
            txtCantidadModificar.setText("");
            
            // Actualizar vistas
            actualizarInventario();

            String operacion = cantidad >= 0 ? "agregados" : "restados";
            JOptionPane.showMessageDialog(null, 
                String.format("Stock actualizado correctamente.\n%s: %.2f\nStock actual: %.2f", 
                            operacion, Math.abs(cantidad), nuevoStock),
                "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La cantidad debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar stock: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * CALCULA ANÁLISIS DE STOCK
     */
    private String calcularAnalisisStock() {
        if (inventario.isEmpty()) {
            return "No hay productos para analizar.";
        }

        int totalProductos = inventario.size();
        int stockBajo = 0;
        int stockMedio = 0;
        int stockAlto = 0;
        double valorTotalInventario = 0;
        
        // Contar por tipo
        java.util.Map<String, Integer> tiposCount = new java.util.HashMap<>();
        java.util.Map<String, Double> tiposValor = new java.util.HashMap<>();

        for (ProductoAgricola producto : inventario) {
            // Clasificar por nivel de stock (ejemplo simple)
            if (producto.getCantidadEnStock() < 10) {
                stockBajo++;
            } else if (producto.getCantidadEnStock() < 50) {
                stockMedio++;
            } else {
                stockAlto++;
            }

            // Calcular valor estimado (en una implementación real usaríamos precios)
            double valorProducto = producto.getCantidadEnStock() * 10; // Valor estimado
            valorTotalInventario += valorProducto;
            
            // Contar por tipo
            String tipo = producto.getTipo();
            tiposCount.put(tipo, tiposCount.getOrDefault(tipo, 0) + 1);
            tiposValor.put(tipo, tiposValor.getOrDefault(tipo, 0.0) + valorProducto);
        }

        StringBuilder analisis = new StringBuilder();
        analisis.append("=== ANÁLISIS COMPLETO DE STOCK ===\n\n");
        analisis.append("Resumen General:\n");
        analisis.append("  - Total de productos: ").append(totalProductos).append("\n");
        analisis.append("  - Valor estimado del inventario: $").append(String.format("%.2f", valorTotalInventario)).append("\n\n");
        
        analisis.append("Niveles de Stock:\n");
        analisis.append("  - Stock crítico (<10): ").append(stockBajo).append(" productos\n");
        analisis.append("  - Stock medio (10-50): ").append(stockMedio).append(" productos\n");
        analisis.append("  - Stock alto (>50): ").append(stockAlto).append(" productos\n\n");
        
        analisis.append("Distribución por Tipo:\n");
        for (String tipo : tiposCount.keySet()) {
            int count = tiposCount.get(tipo);
            double valor = tiposValor.get(tipo);
            double porcentaje = (count * 100.0) / totalProductos;
            analisis.append(String.format("  - %s: %d productos (%.1f%%) - Valor: $%.2f\n", 
                                        tipo, count, porcentaje, valor));
        }

        // Productos con stock bajo
        analisis.append("\n⚠️  Productos con Stock Crítico:\n");
        boolean hayStockBajo = false;
        for (ProductoAgricola producto : inventario) {
            if (producto.getCantidadEnStock() < 10) {
                analisis.append(String.format("  - %s: %.2f unidades\n", 
                                            producto.getNombre(), producto.getCantidadEnStock()));
                hayStockBajo = true;
            }
        }
        if (!hayStockBajo) {
            analisis.append("  No hay productos con stock crítico.\n");
        }

        return analisis.toString();
    }

    /**
     * MUESTRA PRODUCTOS CON STOCK BAJO
     */
    private void mostrarStockBajo() {
        if (inventario.isEmpty()) {
            areaStock.setText("El inventario está vacío.");
            return;
        }

        StringBuilder stockBajo = new StringBuilder();
        stockBajo.append("=== PRODUCTOS CON STOCK CRÍTICO (<10 unidades) ===\n\n");
        
        int contador = 0;
        for (ProductoAgricola producto : inventario) {
            if (producto.getCantidadEnStock() < 10) {
                stockBajo.append("🔴 ").append(producto.toString()).append("\n");
                contador++;
            }
        }

        if (contador == 0) {
            stockBajo.append("✅ No hay productos con stock crítico.\n");
            stockBajo.append("Todos los productos tienen stock suficiente (>10 unidades).");
        } else {
            stockBajo.append("\nTotal de productos con stock crítico: ").append(contador);
        }

        areaStock.setText(stockBajo.toString());
    }

    /**
     * BUSCA UN PRODUCTO POR NOMBRE
     */
    private ProductoAgricola buscarProductoPorNombre(String nombre) {
        for (ProductoAgricola producto : inventario) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto;
            }
        }
        return null;
    }

    /**
     * EXPORTA EL INVENTARIO (SIMULACIÓN)
     */
    private void exportarInventario() {
        JOptionPane.showMessageDialog(null, 
            "Función de exportación en desarrollo.\nEl inventario se exportará a archivo CSV/Excel.", 
            "Exportar Inventario", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * LIMPIA LOS CAMPOS DEL FORMULARIO
     */
    private void limpiarCamposProducto() {
        txtNombreProducto.setText("");
        txtTipoProducto.setText("");
        txtCantidadInicial.setText("");
    }

    /**
     * CREA EL PANEL DE BOTONES
     */
    private JPanel crearPanelBotones(JDialog dialog) {
        JPanel panel = new JPanel();
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        
        panel.add(btnCerrar);
        return panel;
    }

    /**
     * MÉTODO COMPATIBLE CON VERSIÓN ANTERIOR
     */
    public void agregarProducto() {
        mostrarInterfazCompleta();
    }

    /**
     * MÉTODO COMPATIBLE CON VERSIÓN ANTERIOR
     */
    public void mostrarStock() {
        mostrarInterfazCompleta();
    }

    /**
     * MÉTODO COMPATIBLE CON VERSIÓN ANTERIOR - Para otras clases
     */
    public ArrayList<ProductoAgricola> getInventario() {
        return this.inventario;
    }

    /**
     * MÉTODO COMPATIBLE CON VERSIÓN ANTERIOR - Para otras clases
     */
    public void actualizarStock(ProductoAgricola producto, double cantidad) {
        double nuevoStock = producto.getCantidadEnStock() + cantidad;
        producto.setCantidadEnStock(nuevoStock);
    }
}