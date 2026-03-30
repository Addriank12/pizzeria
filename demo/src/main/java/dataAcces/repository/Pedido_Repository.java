/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAcces.repository;

import dataAcces.model.Estado_Pedido;
import dataAcces.model.Pedido;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Pedido_Repository extends SQLController {
    private final String getAll = "SELECT * FROM Pedidos";
    private final String getById = "SELECT * FROM Pedidos WHERE ID=?";
    private final String getByEstado = "SELECT * FROM Pedidos WHERE Estado=?";
    private final String insert = "INSERT INTO Pedidos (ID, Fecha, Estado, Total, Clientes_ID, Empleado_ID) VALUES (?,?,?,?,?,?)";
    private final String update = "UPDATE Pedidos SET Fecha=?, Estado=?, Total=?, Clientes_ID=?, Empleado_ID=? WHERE ID=?";
    private final String delete = "DELETE FROM Pedidos WHERE ID=?";

    public List<Pedido> GetAll() {
        try {
            List<Pedido> result = new ArrayList<>();
            ResultSet reader = ExecuteReader(getAll);
            while (reader.next()) {
                result.add(mapPedido(reader));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Pedido> GetByEstado(Estado_Pedido estado) {
        try {
            parameters = new ArrayList<>();
            parameters.add(estado.ordinal());
            List<Pedido> result = new ArrayList<>();
            ResultSet reader = ExecuteReader(getByEstado);
            while (reader.next()) {
                result.add(mapPedido(reader));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Pedido GetById(Integer id) {
        try {
            parameters = new ArrayList<>();
            parameters.add(id);
            ResultSet reader = ExecuteReader(getById);
            if (reader.next()) {
                return mapPedido(reader);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean Insert(Pedido pedido) {
        try {
            parameters = new ArrayList<>();
            parameters.add(pedido.getID());
            parameters.add(new java.sql.Date(pedido.getFecha().getTime()));
            parameters.add(pedido.getEstado().ordinal());
            parameters.add(pedido.getTotal());
            parameters.add(pedido.getCliente_ID());
            parameters.add(pedido.getEmpleado_ID());
            return ExecuteNonQuery(insert);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean Update(Pedido pedido) {
        try {
            parameters = new ArrayList<>();
            parameters.add(new java.sql.Date(pedido.getFecha().getTime()));
            parameters.add(pedido.getEstado().ordinal());
            parameters.add(pedido.getTotal());
            parameters.add(pedido.getCliente_ID());
            parameters.add(pedido.getEmpleado_ID());
            parameters.add(pedido.getID());
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

    private Pedido mapPedido(ResultSet reader) throws SQLException {
        return new Pedido(
                reader.getInt("ID"),
                reader.getDate("Fecha"),
                Estado_Pedido.values()[reader.getInt("Estado")],
                reader.getDouble("Total"),
                reader.getInt("Clientes_ID"),
                reader.getInt("Empleado_ID"));
    }
}
