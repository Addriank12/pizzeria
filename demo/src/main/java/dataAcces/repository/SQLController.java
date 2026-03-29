package dataAcces.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SQLController extends Repository {

    protected List<Object> parameters;

    protected Boolean ExecuteNonQuery(String transactsql) throws SQLException {
        PreparedStatement pst = GetConnection().prepareStatement(transactsql);
        if (parameters != null) {
            for (int i = 0; i < parameters.size(); i++) {
                if (parameters.get(i) instanceof String) {
                    pst.setString(i + 1, (String) parameters.get(i));
                } else if (parameters.get(i) instanceof Integer) {
                    pst.setInt(i + 1, (Integer) parameters.get(i));
                } else if (parameters.get(i) instanceof Double) {
                    pst.setDouble(i + 1, (Double) parameters.get(i));
                } else if (parameters.get(i) instanceof Float) {
                    pst.setFloat(i + 1, (Float) parameters.get(i));
                }
            }
            parameters = null;
        }
        return pst.execute();
    }

    protected ResultSet ExecuteReader(String transactsql) throws SQLException {
        PreparedStatement pst = GetConnection().prepareStatement(transactsql);
        if (parameters != null) {
            for (int i = 0; i < parameters.size(); i++) {
                if (parameters.get(i) instanceof String) {
                    pst.setString(i + 1, (String) parameters.get(i));
                } else if (parameters.get(i) instanceof Integer) {
                    pst.setInt(i + 1, (Integer) parameters.get(i));
                } else if (parameters.get(i) instanceof Double) {
                    pst.setDouble(i + 1, (Double) parameters.get(i));
                } else if (parameters.get(i) instanceof Float) {
                    pst.setFloat(i + 1, (Float) parameters.get(i));
                }
            }
            parameters = null;
        }
        return pst.executeQuery(transactsql);
    }

    protected Boolean ExecuteCall(String transactsql) throws SQLException {
        PreparedStatement pst = GetConnection().prepareCall(transactsql);
        if (parameters != null) {
            for (int i = 0; i < parameters.size(); i++) {
                if (parameters.get(i) instanceof String) {
                    pst.setString(i + 1, (String) parameters.get(i));
                } else if (parameters.get(i) instanceof Integer) {
                    pst.setInt(i + 1, (Integer) parameters.get(i));
                } else if (parameters.get(i) instanceof Double) {
                    pst.setDouble(i + 1, (Double) parameters.get(i));
                } else if (parameters.get(i) instanceof Float) {
                    pst.setFloat(i + 1, (Float) parameters.get(i));
                }
            }
            parameters = null;
        }
        return pst.execute(transactsql);
    }

}
