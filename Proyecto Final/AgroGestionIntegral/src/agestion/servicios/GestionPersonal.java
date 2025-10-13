package agestion.servicios;

import agestion.modelo.Empleado;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GestionPersonal {

    private ArrayList<Empleado> nomina = new ArrayList<>();

    /**
     * Pide al usuario los datos para un nuevo empleado, verificando cancelaciones en cada paso.
     */
    public void contratarEmpleado() {
        try {
            String nombre = JOptionPane.showInputDialog(null, "Nombre completo del empleado:", "Nuevo Empleado (1/4)", JOptionPane.QUESTION_MESSAGE);
            if (nombre == null || nombre.trim().isEmpty()) { return; }

            String dni = JOptionPane.showInputDialog(null, "DNI del empleado:", "Nuevo Empleado (1/4)", JOptionPane.QUESTION_MESSAGE);
            if (dni == null) { return; }

            String cuit = JOptionPane.showInputDialog(null, "CUIT del empleado:", "Nuevo Empleado (1/4)", JOptionPane.QUESTION_MESSAGE);
            if (cuit == null) { return; }

            String fechaIngresoStr = JOptionPane.showInputDialog(null, "Fecha de Ingreso (AAAA-MM-DD):", "Nuevo Empleado (2/4)", JOptionPane.QUESTION_MESSAGE);
            if (fechaIngresoStr == null) { return; }
            LocalDate fechaIngreso = LocalDate.parse(fechaIngresoStr.trim());

            String categoria = JOptionPane.showInputDialog(null, "Categoría / Puesto:", "Nuevo Empleado (2/4)", JOptionPane.QUESTION_MESSAGE);
            if (categoria == null) { return; }

            Empleado.TipoContrato tipo = (Empleado.TipoContrato) JOptionPane.showInputDialog(
                null, "Seleccione el tipo de contrato:", "Nuevo Empleado (2/4)",
                JOptionPane.QUESTION_MESSAGE, null, Empleado.TipoContrato.values(), Empleado.TipoContrato.PERMANENTE);
            if (tipo == null) { return; }

            String sueldoStr = JOptionPane.showInputDialog(null, "Sueldo Básico ($):", "Nuevo Empleado (3/4)", JOptionPane.QUESTION_MESSAGE);
            if (sueldoStr == null) { return; }
            double sueldo = Double.parseDouble(sueldoStr.trim());
            
            String obraSocial = JOptionPane.showInputDialog(null, "Obra Social:", "Nuevo Empleado (3/4)", JOptionPane.QUESTION_MESSAGE);
            if (obraSocial == null) { return; }

            String art = JOptionPane.showInputDialog(null, "Aseguradora de Riesgos del Trabajo (ART):", "Nuevo Empleado (3/4)", JOptionPane.QUESTION_MESSAGE);
            if (art == null) { return; }
            
            String foto = JOptionPane.showInputDialog(null, "Ruta al archivo de la foto (opcional):", "Nuevo Empleado (4/4)", JOptionPane.QUESTION_MESSAGE);
            if (foto == null) { foto = ""; }

            int legajo = nomina.size() + 1001;
            Empleado nuevoEmpleado = new Empleado(legajo, nombre, dni, fechaIngreso, tipo, cuit, categoria, sueldo, obraSocial, art, foto);
            nomina.add(nuevoEmpleado);

            JOptionPane.showMessageDialog(null, "Empleado '" + nombre + "' contratado con éxito.\nLegajo asignado: " + legajo, "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "El formato de la fecha no es válido. Use AAAA-MM-DD.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El sueldo debe ser un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al registrar al empleado.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Muestra una lista con todos los empleados registrados.
     */
    public void verNominaPersonal() {
        if (nomina.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay personal registrado. Por favor, contrate un empleado primero.", "Nómina Vacía", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder("--- NÓMINA DE PERSONAL ---\n\n");
        for (Empleado empleado : nomina) {
            sb.append(empleado.toString()).append("\n-------------------------------------\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Personal de la Finca", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Muestra la ficha de un empleado, manejando errores si la foto no se encuentra.
     */
    public void verFichaEmpleado() {
        if (nomina.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay personal registrado.", "Nómina Vacía", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        try {
            String legajoStr = JOptionPane.showInputDialog(null, "Ingrese el número de legajo del empleado a consultar:");
            if (legajoStr == null) return;
            
            int legajo = Integer.parseInt(legajoStr);
            Empleado empleado = buscarEmpleadoPorLegajo(legajo);

            if (empleado == null) {
                JOptionPane.showMessageDialog(null, "No se encontró ningún empleado con el legajo " + legajo, "No Encontrado", JOptionPane.WARNING_MESSAGE);
                return;
            }

            JPanel panelFicha = new JPanel(new BorderLayout(15, 15));
            panelFicha.setBackground(new Color(60, 63, 65));

            String fotoPath = empleado.getFotoPath();
            if (fotoPath != null && !fotoPath.trim().isEmpty()) {
                ImageIcon fotoIcon = new ImageIcon(fotoPath);
                if (fotoIcon.getIconHeight() > 0) {
                    Image foto = fotoIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                    JLabel labelFoto = new JLabel(new ImageIcon(foto));
                    panelFicha.add(labelFoto, BorderLayout.WEST);
                }
            }

            JTextArea datosArea = new JTextArea(empleado.toString());
            datosArea.setEditable(false);
            datosArea.setOpaque(false);
            datosArea.setForeground(Color.WHITE);
            datosArea.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
            panelFicha.add(datosArea, BorderLayout.CENTER);

            JOptionPane.showMessageDialog(null, panelFicha, "Ficha de Empleado - " + empleado.getNombreCompleto(), JOptionPane.PLAIN_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El legajo debe ser un número.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al mostrar la ficha.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Permite dar de baja a un empleado, cambiando su estado a "Inactivo" y registrando la fecha.
     */
    public void darDeBajaEmpleado() {
        if (nomina.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay personal registrado.", "Nómina Vacía", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            String legajoStr = JOptionPane.showInputDialog(null, "Ingrese el legajo del empleado a dar de baja:");
            if (legajoStr == null) return;
            
            int legajo = Integer.parseInt(legajoStr);
            Empleado empleado = buscarEmpleadoPorLegajo(legajo);

            if (empleado == null) {
                JOptionPane.showMessageDialog(null, "No se encontró ningún empleado con el legajo " + legajo, "No Encontrado", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (empleado.getEstado().equals("Inactivo")) {
                 JOptionPane.showMessageDialog(null, "El empleado " + empleado.getNombreCompleto() + " ya se encuentra inactivo.", "Operación Inválida", JOptionPane.INFORMATION_MESSAGE);
                 return;
            }

            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Confirma la baja del empleado " + empleado.getNombreCompleto() + "?", "Confirmar Baja", JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                String fechaBajaStr = JOptionPane.showInputDialog(null, "Ingrese la fecha de baja (AAAA-MM-DD):");
                if (fechaBajaStr == null) return;

                LocalDate fechaBaja = LocalDate.parse(fechaBajaStr);
                
                empleado.setEstado("Inactivo");
                empleado.setFechaBaja(fechaBaja);

                JOptionPane.showMessageDialog(null, "El empleado ha sido dado de baja correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El legajo debe ser un número.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "El formato de la fecha no es válido. Use AAAA-MM-DD.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Busca un empleado en la lista por su número de legajo.
     * @param legajo El ID del empleado a buscar.
     * @return El objeto Empleado si se encuentra, o null si no existe.
     */
    private Empleado buscarEmpleadoPorLegajo(int legajo) {
        for (Empleado empleado : nomina) {
            if (empleado.getLegajo() == legajo) {
                return empleado;
            }
        }
        return null; // No se encontró
    }
}