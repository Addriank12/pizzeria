/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAcces.repository;
import dataAcces.model.Cliente;
import dataAcces.model.Producto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Producto_Repository extends SQLController{
    private final String getAll = "SELECT * FROM Productos";
    private final String insert = "INSERT INTO Productos (ID, Nombre, Descripcion, Precio, Categorias_Id) VALUES (?,?,?,?,?)";
    private final String update = "UPDATE Productos SET ID=?, Nombre=?, Descripcion=?, Precio=?, Categorias_Id=? WHERE ID=?";
    private final String delete = "DELETE FROM Productos WHERE ID=?";
    
        public List<Producto> GetAll() {
        try {
            List<Producto> result = new ArrayList<>();
            ResultSet reader = ExecuteReader(getAll);
            while (reader.next()) {
                Producto producto = new Producto(
                        reader.getInt("ID"), reader.getString("Nombre"),
                        reader.getString("Descripcion"), reader.getDouble("Precio"),
                        reader.getInt("Categorias_Id"));
                result.add(producto);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean Insert(Producto producto) {
        try {
            parameters = new ArrayList<>();
            parameters.add(producto.getID());
            parameters.add(producto.getNombre());
            parameters.add(producto.getDescripcion());
            parameters.add(producto.getPrecio());
            parameters.add(producto.getCategoria_ID());
            return ExecuteNonQuery(insert);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean Update(Producto producto) {
        try {
            parameters = new ArrayList<>();
            parameters.add(producto.getID());
            parameters.add(producto.getNombre());
            parameters.add(producto.getDescripcion());
            parameters.add(producto.getPrecio());
            parameters.add(producto.getCategoria_ID());
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
