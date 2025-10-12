package agestion.modelo;

public class Maquinaria {
    private int id;
    private String nombre;
    private String estado;
    private double horasDeUso;

    public Maquinaria(int id, String nombre, String estado, double horasDeUso) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.horasDeUso = horasDeUso;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public double getHorasDeUso() { return horasDeUso; }
    public void setHorasDeUso(double horasDeUso) { this.horasDeUso = horasDeUso; }

    @Override
    public String toString() {
        return "ID: " + id + " | Máquina: " + nombre + " | Estado: " + estado + " | Horas: " + horasDeUso + "hs";
    }
}