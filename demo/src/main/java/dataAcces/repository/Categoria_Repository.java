package dataAcces.repository;

import dataAcces.model.Categoria;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Categoria_Repository extends SQLController{

    private final String getAll = "SELECT * FROM Categorias";
    private final String insert = "INSERT INTO Categorias (ID, Categoria) VALUES(?,?)";
    private final String update = "UPDATE Categorias SET ID=?, Categoria=? WHERE ID=?";
    private final String delete = "DELETE FROM Categorias WHERE ID=?";

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
            parameters.add(categoria.getID());
            parameters.add(categoria.getCategoria());

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
