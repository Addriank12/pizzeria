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
                Object value = parameters.get(i);
                if (value instanceof String) {
                    pst.setString(i + 1, (String) value);
                } else if (value instanceof Integer) {
                    pst.setInt(i + 1, (Integer) value);
                } else if (value instanceof Double) {
                    pst.setDouble(i + 1, (Double) value);
                } else if (value instanceof Float) {
                    pst.setFloat(i + 1, (Float) value);
                } else if (value instanceof java.sql.Date) {
                    pst.setDate(i + 1, (java.sql.Date) value);
                } else if (value instanceof java.util.Date) {
                    pst.setTimestamp(i + 1, new java.sql.Timestamp(((java.util.Date) value).getTime()));
                } else if (value instanceof Enum<?>) {
                    pst.setString(i + 1, ((Enum<?>) value).name());
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
                Object value = parameters.get(i);
                if (value instanceof String) {
                    pst.setString(i + 1, (String) value);
                } else if (value instanceof Integer) {
                    pst.setInt(i + 1, (Integer) value);
                } else if (value instanceof Double) {
                    pst.setDouble(i + 1, (Double) value);
                } else if (value instanceof Float) {
                    pst.setFloat(i + 1, (Float) value);
                } else if (value instanceof java.sql.Date) {
                    pst.setDate(i + 1, (java.sql.Date) value);
                } else if (value instanceof java.util.Date) {
                    pst.setTimestamp(i + 1, new java.sql.Timestamp(((java.util.Date) value).getTime()));
                } else if (value instanceof Enum<?>) {
                    pst.setString(i + 1, ((Enum<?>) value).name());
                }
            }
            parameters = null;
        }
        return pst.executeQuery();
    }

    protected Boolean ExecuteCall(String transactsql) throws SQLException {
        PreparedStatement pst = GetConnection().prepareCall(transactsql);
        if (parameters != null) {
            for (int i = 0; i < parameters.size(); i++) {
                Object value = parameters.get(i);
                if (value instanceof String) {
                    pst.setString(i + 1, (String) value);
                } else if (value instanceof Integer) {
                    pst.setInt(i + 1, (Integer) value);
                } else if (value instanceof Double) {
                    pst.setDouble(i + 1, (Double) value);
                } else if (value instanceof Float) {
                    pst.setFloat(i + 1, (Float) value);
                } else if (value instanceof java.sql.Date) {
                    pst.setDate(i + 1, (java.sql.Date) value);
                } else if (value instanceof java.util.Date) {
                    pst.setTimestamp(i + 1, new java.sql.Timestamp(((java.util.Date) value).getTime()));
                } else if (value instanceof Enum<?>) {
                    pst.setString(i + 1, ((Enum<?>) value).name());
                }
            }
            parameters = null;
        }
        return pst.execute(transactsql);
    }

}
