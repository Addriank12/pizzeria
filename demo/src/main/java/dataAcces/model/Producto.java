package dataAcces.model;

public class Producto extends Base_Model {
    private String nombre;
    private String descripcion;
    private double precio;
    private int categoria_ID;

    public int getCategoria_ID() {
        return categoria_ID;
    }

    public void setCategoria_ID(int categoria_ID) {
        this.categoria_ID = categoria_ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Producto(int iD, String nombre, String descripcion, double precio, int categoria_ID) {
        super(iD);
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria_ID = categoria_ID;
    }

    @Override
    public String toString() {
        return nombre + " (ID " + getID() + ")";
    }

}
