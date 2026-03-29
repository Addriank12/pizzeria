package dataAcces.repository;

import dataAcces.model.Detalle_Pedido;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataAcces.model.Empleado;
import dataAcces.model.Empleado_Cargo;

public class Detalle_pedido_Repository extends SQLController {

    private final String getAll = "SELECT * FROM DetallePedido";
    private final String insert = "INSERT INTO DetallePedido (ID, Cantidad, Subtotal, Pedidos_ID, Productos_ID) VALUES(?,?,?,?,?)";
    private final String update = "UPDATE DetallePedido SET ID=?, Cantidad=?, Subtotal=?, Pedidos_ID=?, Productos_ID=? WHERE ID=?";
    private final String delete = "DELETE FROM DetallePedido WHERE ID=?";

    public List<Detalle_Pedido> GetAll() {
        try {
            List<Detalle_Pedido> result = new ArrayList<>();
            ResultSet reader = ExecuteReader(getAll);
            while (reader.next()) {
                Detalle_Pedido detalle_pedido = new Detalle_Pedido(
                        reader.getInt("ID"), reader.getDouble("Cantidad"),
                        reader.getDouble("Subtotal"), reader.getInt("Pedidos_ID"),
                        reader.getInt("Productos_ID"));
                result.add(detalle_pedido);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean Insert(Detalle_Pedido detalle_pedido) {
        try {
            parameters = new ArrayList<>();
            parameters.add(detalle_pedido.getID());
            parameters.add(detalle_pedido.getCantidad());
            parameters.add(detalle_pedido.getSubtotal());
            parameters.add(detalle_pedido.getPedidos_ID());
            parameters.add(detalle_pedido.getProductos_ID());
            return ExecuteNonQuery(insert);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean Update(Detalle_Pedido detalle_Pedido) {
        try {
            parameters = new ArrayList<>();
            parameters.add(detalle_Pedido.getID());
            parameters.add(detalle_Pedido.getCantidad());
            parameters.add(detalle_Pedido.getSubtotal());
            parameters.add(detalle_Pedido.getPedidos_ID());
            parameters.add(detalle_Pedido.getProductos_ID());
            return ExecuteNonQuery(update);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean Delete(Integer id) {
        try {
            parameters = new ArrayList<>();
            parameters.add(id);
            return ExecuteNonQuery(delete);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
