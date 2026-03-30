package dataAcces.repository;

import dataAcces.model.Categoria;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Categoria_Repository extends SQLController {

    private final String getAll = "SELECT * FROM Categorias";
    private final String insert = "INSERT INTO Categorias (ID, Categoria) VALUES(?,?)";
    private final String update = "UPDATE Categorias SET Categoria=? WHERE ID=?";
    private final String delete = "DELETE FROM Categorias WHERE ID=?";
    private final String getById = "SELECT * FROM Categorias WHERE ID=?";
    private final String getByNombre = "SELECT * FROM Categorias WHERE Categoria=?";
    private final String getProductosByNombreCategoria = "SELECT p.ID, p.Nombre, p.Descripcion, p.Precio, c.Categoria "
            + "FROM Productos p INNER JOIN Categorias c ON p.Categorias_Id = c.ID "
            + "WHERE LOWER(c.Categoria) LIKE ?";

    public List<Categoria> GetAll() {
        try {
            List<Categoria> result = new ArrayList<>();
            ResultSet reader = ExecuteReader(getAll);
            while (reader.next()) {
                Categoria categoria = new Categoria(
                        reader.getInt("ID"), reader.getString("Categoria"));
                result.add(categoria);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean Insert(Categoria categoria) {
        try {
            parameters = new ArrayList<>();
            parameters.add(categoria.getID());
            parameters.add(categoria.getCategoria());
            return ExecuteNonQuery(insert);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean Update(Categoria categoria) {
        try {
            parameters = new ArrayList<>();
            parameters.add(categoria.getCategoria());
            parameters.add(categoria.getID());

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

    public Categoria GetById(Integer id) {
        try {
            parameters = new ArrayList<>();
            parameters.add(id);
            ResultSet reader = ExecuteReader(getById);
            if (reader.next()) {
                return mapCategoria(reader);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Categoria GetByNombre(String nombre) {
        try {
            parameters = new ArrayList<>();
            parameters.add(nombre);
            ResultSet reader = ExecuteReader(getByNombre);
            if (reader.next()) {
                return mapCategoria(reader);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Object[]> GetProductosByNombreCategoria(String categoria) {
        try {
            parameters = new ArrayList<>();
            String filtro = categoria == null ? "" : categoria.trim().toLowerCase();
            parameters.add("%" + filtro + "%");

            List<Object[]> result = new ArrayList<>();
            ResultSet reader = ExecuteReader(getProductosByNombreCategoria);
            while (reader.next()) {
                Object[] fila = new Object[5];
                fila[0] = reader.getInt("ID");
                fila[1] = reader.getString("Nombre");
                fila[2] = reader.getString("Descripcion");
                fila[3] = reader.getDouble("Precio");
                fila[4] = reader.getString("Categoria");
                result.add(fila);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private Categoria mapCategoria(ResultSet reader) throws SQLException {
        return new Categoria(reader.getInt("ID"), reader.getString("Categoria"));
    }
}
