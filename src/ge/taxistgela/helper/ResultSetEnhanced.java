/*
 *
 *         Copyright (C) 2015  Giorgi Guliashvili
 *
 *         This program is free software: you can redistribute it and/or modify
 *         it under the terms of the GNU General Public License as published by
 *         the Free Software Foundation, either version 3 of the License, or
 *         (at your option) any later version.
 *
 *         This program is distributed in the hope that it will be useful,
 *         but WITHOUT ANY WARRANTY; without even the implied warranty of
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *         GNU General Public License for more details.
 *
 *         You should have received a copy of the GNU General Public License
 *         along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 */

package ge.taxistgela.helper;

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

/*
    public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
        BigDecimal ret = resultSet.getBigDecimal(parameterIndex);
        if (resultSet.wasNull()) ret = null;

        return ret;
    }

    public BigDecimal getBigDecimal(String parameterIndex) throws SQLException {
        BigDecimal ret = resultSet.getBigDecimal(parameterIndex);
        if (resultSet.wasNull()) ret = null;

        return ret;
    }*/


}
