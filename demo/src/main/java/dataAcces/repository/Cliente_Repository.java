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

    private final String getAll = "SELECT * FROM Clientes";
    private final String insert = "INSERT INTO Clientes (ID, Identificacion, Nombre_Completo, Direccion, Telefono, Mail) VALUES (?,?,?,?,?,?)";
    private final String update = "UPDATE Clientes SET Identificacion=?, Nombre_Completo=?, Direccion=?, Telefono=?, Mail=? WHERE ID=?";
    private final String delete = "DELETE FROM Clientes WHERE ID=?";
    private final String getById = "SELECT * FROM Clientes WHERE ID=?";
    private final String getByIdentificacion = "SELECT * FROM Clientes WHERE Identificacion=?";

    public List<Cliente> GetAll() {
        try {
            List<Cliente> result = new ArrayList<>();
            ResultSet reader = ExecuteReader(getAll);
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
            return ExecuteNonQuery(insert);
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
