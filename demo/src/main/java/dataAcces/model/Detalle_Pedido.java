package dataAcces.model;

public class Detalle_Pedido extends Base_Model {

    private float cantidad;
    private float subtotal;
    private int pedidos_ID;
    private int productos_ID;

    public int getPedidos_ID() {
        return pedidos_ID;
    }

    public void setPedidos_ID(int pedidos_ID) {
        this.pedidos_ID = pedidos_ID;
    }

    public int getProductos_ID() {
        return productos_ID;
    }

    public void setProductos_ID(int productos_ID) {
        this.productos_ID = productos_ID;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public Detalle_Pedido(int iD, float cantidad, float subtotal, int pedidos_ID, int productos_ID) {
        super(iD);
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.pedidos_ID = pedidos_ID;
        this.productos_ID = productos_ID;
    }

}
