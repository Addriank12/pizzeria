package dataAcces.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataAcces.model.Empleado;
import dataAcces.model.Empleado_Cargo;

public class Empleado_Repository extends SQLController {

    private final String getAll = "SELECT * FROM Empleados";
    private final String insert = "INSERT INTO Empleados (ID, Identificacion, Nombre_Completo, Direccion, Telefono, Mail, Cargo) VALUES(?,?,?,?,?,?,?)";
    private final String update = "UPDATE Empleados SET Identificacion=?, Nombre_Completo=?, Direccion=?, Telefono=?, Mail=?, Cargo=? WHERE ID=?";
    private final String delete = "DELETE FROM Empleados WHERE ID=?";
    private final String getById = "SELECT * FROM Empleados WHERE ID=?";
    private final String getByIdentificacion = "SELECT * FROM Empleados WHERE Identificacion=?";

    public List<Empleado> GetAll() {
        try {
            List<Empleado> result = new ArrayList<>();
            ResultSet reader = ExecuteReader(getAll);
            while (reader.next()) {
                Empleado empleado = new Empleado(
                        reader.getInt("ID"), reader.getString("Identificacion"),
                        reader.getString("Nombre_Completo"), reader.getString("Direccion"),
                        reader.getString("Telefono"), reader.getString("Mail"),
                        Empleado_Cargo.values()[reader.getInt("Cargo")]);
                result.add(empleado);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean Insert(Empleado empleado) {
        try {
            parameters = new ArrayList<>();
            parameters.add(empleado.getID());
            parameters.add(empleado.getIdentificacion());
            parameters.add(empleado.getNombre_Completo());
            parameters.add(empleado.getDireccion());
            parameters.add(empleado.getTelefono());
            parameters.add(empleado.getMail());
            parameters.add(empleado.getCargo().ordinal());
            return ExecuteNonQuery(insert);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean Update(Empleado empleado) {
        try {
            parameters = new ArrayList<>();
            parameters.add(empleado.getIdentificacion());
            parameters.add(empleado.getNombre_Completo());
            parameters.add(empleado.getDireccion());
            parameters.add(empleado.getTelefono());
            parameters.add(empleado.getMail());
            parameters.add(empleado.getCargo().ordinal());
            parameters.add(empleado.getID());
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

    public Empleado GetById(Integer id) {
        try {
            parameters = new ArrayList<>();
            parameters.add(id);
            ResultSet reader = ExecuteReader(getById);
            if (reader.next()) {
                return mapEmpleado(reader);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Empleado GetByIdentificacion(String identificacion) {
        try {
            parameters = new ArrayList<>();
            parameters.add(identificacion);
            ResultSet reader = ExecuteReader(getByIdentificacion);
            if (reader.next()) {
                return mapEmpleado(reader);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Empleado mapEmpleado(ResultSet reader) throws SQLException {
        return new Empleado(
                reader.getInt("ID"),
                reader.getString("Identificacion"),
                reader.getString("Nombre_Completo"),
                reader.getString("Direccion"),
                reader.getString("Telefono"),
                reader.getString("Mail"),
                Empleado_Cargo.values()[reader.getInt("Cargo")]);
    }

}
