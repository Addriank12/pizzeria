/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAcces.repository;

import dataAcces.model.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cliente_Repository extends SQLController {

    private final String getAll = "CALL sp_Clientes_GetAll()";
    private final String insert = "CALL sp_Clientes_Insert(?,?,?,?,?,?)";
    private final String update = "CALL sp_Clientes_Update(?,?,?,?,?,?)";
    private final String delete = "CALL sp_Clientes_Delete(?)";

    private final String getById = "SELECT * FROM Clientes WHERE ID=?";
    private final String getByIdentificacion = "SELECT * FROM Clientes WHERE Identificacion=?";
    private final String getPedidosByNombreCliente = "SELECT p.ID, c.Nombre_Completo, p.Fecha, p.Estado, p.Total "
            + "FROM Pedidos p INNER JOIN Clientes c ON p.Clientes_ID = c.ID "
            + "WHERE LOWER(c.Nombre_Completo) LIKE ?";

    public List<Cliente> GetAll() {
        try {
            List<Cliente> result = new ArrayList<>();
            ResultSet reader = ExecuteCallReader(getAll);
            while (reader.next()) {
                Cliente cliente = new Cliente(
                        reader.getInt("ID"), reader.getString("Identificacion"),
                        reader.getString("Nombre_Completo"), reader.getString("Direccion"),
                        reader.getString("Telefono"), reader.getString("Mail"));
                result.add(cliente);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean Insert(Cliente cliente) {
        try {
            parameters = new ArrayList<>();
            parameters.add(cliente.getID());
            parameters.add(cliente.getIdentificacion());
            parameters.add(cliente.getNombre_Completo());
            parameters.add(cliente.getDireccion());
            parameters.add(cliente.getTelefono());
            parameters.add(cliente.getMail());
            return ExecuteCallNonQuery(insert);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean Update(Cliente cliente) {
        try {
            parameters = new ArrayList<>();
            parameters.add(cliente.getIdentificacion());
            parameters.add(cliente.getNombre_Completo());
            parameters.add(cliente.getDireccion());
            parameters.add(cliente.getTelefono());
            parameters.add(cliente.getMail());
            parameters.add(cliente.getID());
            return ExecuteCallNonQuery(update);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean Delete(Integer id) {
        try {
            parameters = new ArrayList<>();
            parameters.add(id);
            return ExecuteCallNonQuery(delete);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Cliente GetById(Integer id) {
        try {
            parameters = new ArrayList<>();
            parameters.add(id);
            ResultSet reader = ExecuteReader(getById);
            if (reader.next()) {
                return mapCliente(reader);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Cliente GetByIdentificacion(String identificacion) {
        try {
            parameters = new ArrayList<>();
            parameters.add(identificacion);
            ResultSet reader = ExecuteReader(getByIdentificacion);
            if (reader.next()) {
                return mapCliente(reader);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Object[]> GetPedidosByNombreCliente(String nombreCliente) {
        try {
            parameters = new ArrayList<>();
            String filtro = nombreCliente == null ? "" : nombreCliente.trim().toLowerCase();
            parameters.add("%" + filtro + "%");

            List<Object[]> result = new ArrayList<>();
            ResultSet reader = ExecuteReader(getPedidosByNombreCliente);
            while (reader.next()) {
                Object[] fila = new Object[5];
                fila[0] = reader.getInt("ID");
                fila[1] = reader.getString("Nombre_Completo");
                fila[2] = reader.getDate("Fecha");
                fila[3] = reader.getInt("Estado");
                fila[4] = reader.getDouble("Total");
                result.add(fila);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private Cliente mapCliente(ResultSet reader) throws SQLException {
        return new Cliente(
                reader.getInt("ID"),
                reader.getString("Identificacion"),
                reader.getString("Nombre_Completo"),
                reader.getString("Direccion"),
                reader.getString("Telefono"),
                reader.getString("Mail"));
    }
}
