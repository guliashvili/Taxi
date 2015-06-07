package ge.taxistgela.helper;

import com.mysql.jdbc.PreparedStatement;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by GIO on 6/7/2015.
 */
public class PreparedStatementEnhanced{
    private PreparedStatement st;
    public  PreparedStatementEnhanced(PreparedStatement st){
        this.st = st;
    }

    public void setString(int parameterIndex, String x) throws SQLException{
        if(x == null)
            st.setNull(parameterIndex, Types.VARCHAR);
        else
            st.setString(parameterIndex,x);
    }

    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException{
        if(x == null)
            st.setNull(parameterIndex, Types.DECIMAL);
        else
            st.setBigDecimal(parameterIndex,x);
    }
    public void setBoolean(int parameterIndex, Boolean x)throws SQLException{
        if(x == null)
            st.setNull(parameterIndex, Types.BOOLEAN);
        else
            st.setBoolean(parameterIndex,x);
    }
    public void setDouble(int parameterIndex, Double x)throws SQLException{
        if(x == null)
            st.setNull(parameterIndex, Types.DOUBLE);
        else
            st.setDouble(parameterIndex,x);
    }
    public void setFloat(int parameterIndex, Float x)throws SQLException{
        if(x == null)
            st.setNull(parameterIndex, Types.FLOAT);
        else
            st.setFloat(parameterIndex,x);
    }
    public void setInt(int parameterIndex, Integer x)throws SQLException{
        if(x == null)
            st.setNull(parameterIndex, Types.INTEGER);
        else
            st.setInt(parameterIndex,x);
    }
    public void setLong(int parameterIndex, Long x)throws SQLException{
        if(x == null)
            st.setNull(parameterIndex, Types.BIGINT);
        else
            st.setLong(parameterIndex,x);
    }
    public void setShort(int parameterIndex, Short x)throws SQLException{
        if(x == null)
            st.setNull(parameterIndex, Types.SMALLINT);
        else
            st.setShort(parameterIndex,x);
    }


}
