package ge.taxistgela.helper;

import ge.taxistgela.db.DBConnectionProvider;

import java.sql.Connection;

/**
 * Created by Ratmach on 30/5/15.
 */
public class AdminDatabase {
    private final static String database = "src\\ge\\taxistgela\\db\\clearDatabase.sql";

    /**
     * drops and recreates database from database.sql file
     * @throws Exception
     */
    public synchronized void recreateDatabase() throws Exception{

        try(Connection con = DBConnectionProvider.getConnection()){

            con.createStatement().execute("DELETE  FROM Companies");
            con.createStatement().execute("DELETE  FROM Reviews");
            con.createStatement().execute("DELETE  FROM Orders");
            con.createStatement().execute("DELETE  FROM Drivers");
            con.createStatement().execute("DELETE  FROM Cars");
            con.createStatement().execute("DELETE  FROM DriverPreferences");
            con.createStatement().execute("DELETE  FROM Users");
            con.createStatement().execute("DELETE  FROM UserPreferences");
        }
    }
}
