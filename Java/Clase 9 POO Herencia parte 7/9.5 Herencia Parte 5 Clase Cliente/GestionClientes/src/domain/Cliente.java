// Archivo: Cliente.java
package domain;

import java.util.Date;

public class Cliente extends Persona {
    
    // Atributos propios de la clase Cliente
    private int idCliente;
    private Date fechaRegistro;
    private boolean vip;
    
    // Atributo estático para contar los clientes
    private static int contadorCliente;

    // Constructor que inicializa los atributos de Persona y los propios de Cliente
    public Cliente(String nombre, char genero, int edad, String direccion, boolean vip) {
        // 1. Llamar al constructor de la clase padre para inicializar sus atributos
        super(nombre, genero, edad, direccion);
        
        // 2. Asignar el ID único incrementando el contador estático
        this.idCliente = ++Cliente.contadorCliente;
        
        // 3. Asignar la fecha de registro actual
        this.fechaRegistro = new Date();
        
        // 4. Inicializar el atributo vip
        this.vip = vip;
    }

    // --- Métodos Getters y Setters ---
    public int getIdCliente() {
        return this.idCliente;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public boolean isVip() {
        return this.vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    // --- Método toString para mostrar el estado completo del objeto ---
     @Override
    public String toString() {
        // Construimos una salida más elaborada y legible
        StringBuilder sb = new StringBuilder();
        sb.append("--- Datos del Cliente ---");
        sb.append("\nID Cliente: ").append(this.idCliente);
        
        // Usamos un operador ternario para que 'vip' se muestre como "Sí" o "No"
        sb.append("\nCliente VIP: ").append(this.vip ? "Sí" : "No");
        
        sb.append("\nFecha de Registro: ").append(this.fechaRegistro);
        sb.append("\n--- Datos Personales ---");
        
        // Agregamos los datos de la persona directamente
        sb.append("\nNombre: ").append(this.nombre);
        sb.append("\nGénero: ").append(this.genero);
        sb.append("\nEdad: ").append(this.edad);
        sb.append("\nDirección: ").append(this.direccion);
        
        return sb.toString();
    }
}