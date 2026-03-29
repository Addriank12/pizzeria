package dataAcces.model;

import java.util.Date;

public class Pedido extends Base_Model {
    private Date Fecha;
    private Estado_Pedido estado;
    private double total;
    private int cliente_ID;
    private int empleado_ID;

    public int getCliente_ID() {
        return cliente_ID;
    }

    public void setCliente_ID(int cliente_ID) {
        this.cliente_ID = cliente_ID;
    }

    public int getEmpleado_ID() {
        return empleado_ID;
    }

    public void setEmpleado_ID(int empleado_ID) {
        this.empleado_ID = empleado_ID;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public Estado_Pedido getEstado() {
        return estado;
    }

    public void setEstado(Estado_Pedido estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Pedido(int iD, Date fecha, Estado_Pedido estado, double total, int cliente_ID, int empleado_ID) {
        super(iD);
        Fecha = fecha;
        this.estado = estado;
        this.total = total;
        this.cliente_ID = cliente_ID;
        this.empleado_ID = empleado_ID;
    }

}
