package ge.taxistgela.helper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by GIO on 6/7/2015.
 */
public class ResultSetEnhanced implements AutoCloseable {
    ResultSet resultSet;

    public ResultSetEnhanced(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    @Override
    public void close() {
        try {
            resultSet.close();
        } catch (SQLException e) {

        }
    }

    public boolean next() throws SQLException {
        return resultSet.next();
    }

    public Double getDouble(int parameterIndex) throws SQLException {
        Double ret = resultSet.getDouble(parameterIndex);
        if (resultSet.wasNull()) ret = null;

        return ret;
    }

    public Double getDouble(String parameterIndex) throws SQLException {
        Double ret = resultSet.getDouble(parameterIndex);
        if (resultSet.wasNull()) ret = null;

        return ret;
    }

    public Integer getInt(int parameterIndex) throws SQLException {
        Integer ret = resultSet.getInt(parameterIndex);
        if (resultSet.wasNull()) ret = null;

        return ret;
    }

    public Integer getInt(String parameterIndex) throws SQLException {
        Integer ret = resultSet.getInt(parameterIndex);
        if (resultSet.wasNull()) ret = null;

        return ret;
    }


    public String getString(int parameterIndex) throws SQLException {
        String ret = resultSet.getString(parameterIndex);
        if (resultSet.wasNull()) ret = null;

        return ret;
    }

    public String getString(String parameterIndex) throws SQLException {
        String ret = resultSet.getString(parameterIndex);
        if (resultSet.wasNull()) ret = null;

        return ret;
    }


    public Boolean getBoolean(int parameterIndex) throws SQLException {
        Boolean ret = resultSet.getBoolean(parameterIndex);
        if (resultSet.wasNull()) ret = null;

        return ret;
    }

    public Boolean getBoolean(String parameterIndex) throws SQLException {
        Boolean ret = resultSet.getBoolean(parameterIndex);
        if (resultSet.wasNull()) ret = null;

        return ret;
    }


    public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
        BigDecimal ret = resultSet.getBigDecimal(parameterIndex);
        if (resultSet.wasNull()) ret = null;

        return ret;
    }

    public BigDecimal getBigDecimal(String parameterIndex) throws SQLException {
        BigDecimal ret = resultSet.getBigDecimal(parameterIndex);
        if (resultSet.wasNull()) ret = null;

        return ret;
    }


}
