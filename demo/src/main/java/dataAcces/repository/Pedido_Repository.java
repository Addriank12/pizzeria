/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAcces.repository;

import dataAcces.model.Cliente;
import dataAcces.model.Estado_Pedido;
import dataAcces.model.Pedido;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Pedido_Repository extends SQLController{
    private final String getAll = "SELECT * FROM Pedidos";
    private final String insert = "INSERT INTO Pedidos (ID, Fecha, Estado, Total, Clientes_ID, Empleado_ID) VALUES (?,?,?,?,?,?)";
    private final String update = "UPDATE Pedidos SET ID=?, Fecha=?, Estado=?, Total=?, Clientes_ID=?, Empleado_ID=? WHERE ID=?";
    private final String delete = "DELETE FROM Pedidos WHERE ID=?";
    
        public List<Pedido> GetAll() {
        try {
            List<Pedido> result = new ArrayList<>();
            ResultSet reader = ExecuteReader(getAll);
            while (reader.next()) {
                Pedido pedido = new Pedido(
                        reader.getInt("ID"), reader.getDate("Fecha"),
                        Estado_Pedido.valueOf("Estado"), reader.getDouble("Total"),
                        reader.getInt("Clientes_ID"), reader.getInt("Empleado_ID"));
                result.add(pedido);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean Insert(Pedido pedido) {
        try {
            parameters = new ArrayList<>();
            parameters.add(pedido.getID());
            parameters.add(pedido.getFecha());
            parameters.add(pedido.getEstado());
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
            parameters.add(pedido.getID());
            parameters.add(pedido.getFecha());
            parameters.add(pedido.getEstado());
            parameters.add(pedido.getTotal());
            parameters.add(pedido.getCliente_ID());
            parameters.add(pedido.getEmpleado_ID());
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

