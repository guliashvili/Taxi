package ge.taxistgela.db;

/**
 * Created by Alex on 5/25/2015.
 */
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionProvider {

    private static BasicDataSource dataSource;

    static {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(DBInfo.MYSQL_DATABASE_SERVER);
        dataSource.setUrl(DBInfo.MYSQL_DATABASE_NAME);
        dataSource.setUsername(DBInfo.MYSQL_USERNAME);
        dataSource.setPassword(DBInfo.MYSQL_PASSWORD);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
