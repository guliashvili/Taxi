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


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by GIO on 6/7/2015.
 */
public class PreparedStatementEnhanced implements AutoCloseable {
    private PreparedStatement st;
    public  PreparedStatementEnhanced(PreparedStatement st){
        this.st = st;
    }

    @Override
    public void close() {
        try {
            st.close();
        } catch (SQLException e) {

        }
    }

    @Override
    public String toString() {
        return st.toString();
    }

    public ResultSetEnhanced getGeneratedKeys() throws SQLException {
        return new ResultSetEnhanced(st.getGeneratedKeys());
    }

    public ResultSetEnhanced executeQuery() throws SQLException {
        return new ResultSetEnhanced(st.executeQuery());
    }

    public int executeUpdate() throws SQLException {
        return st.executeUpdate();
    }

    public void setString(int parameterIndex, String x) throws SQLException{
        if(x == null)
            st.setNull(parameterIndex, Types.VARCHAR);
        else
            st.setString(parameterIndex,x);
    }

    /*
        public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException{
            if(x == null)
                st.setNull(parameterIndex, Types.DECIMAL);
            else
                st.setBigDecimal(parameterIndex, x);
        }*/
    public void setBoolean(int parameterIndex, Boolean x)throws SQLException{
        if(x == null)
            st.setNull(parameterIndex, Types.BOOLEAN);
        else
            st.setBoolean(parameterIndex, x);
    }
    public void setDouble(int parameterIndex, Double x)throws SQLException{
        if(x == null)
            st.setNull(parameterIndex, Types.DOUBLE);
        else
            st.setDouble(parameterIndex, x);
    }
    public void setFloat(int parameterIndex, Float x)throws SQLException{
        if(x == null)
            st.setNull(parameterIndex, Types.FLOAT);
        else
            st.setFloat(parameterIndex, x);
    }
    public void setInt(int parameterIndex, Integer x)throws SQLException{
        if(x == null)
            st.setNull(parameterIndex, Types.INTEGER);
        else
            st.setInt(parameterIndex, x);
    }
    public void setLong(int parameterIndex, Long x)throws SQLException{
        if(x == null)
            st.setNull(parameterIndex, Types.BIGINT);
        else
            st.setLong(parameterIndex, x);
    }
    public void setShort(int parameterIndex, Short x)throws SQLException{
        if(x == null)
            st.setNull(parameterIndex, Types.SMALLINT);
        else
            st.setShort(parameterIndex, x);
    }


}
