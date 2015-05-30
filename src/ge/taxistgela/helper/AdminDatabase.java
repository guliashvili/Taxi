package ge.taxistgela.helper;

import ge.taxistgela.db.DBConnectionProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;

/**
 * Created by Ratmach on 30/5/15.
 */
public class AdminDatabase {
    private final static String database = "src\\ge\\taxistgela\\db\\database.sql";

    /**
     * drops and recreates database from database.sql file
     * @throws Exception
     */
    public synchronized void recreateDatabase() throws Exception{
        BufferedReader rdr = new BufferedReader(new FileReader(getClass().getResource(database).getPath()));
        StringBuilder b = new StringBuilder();
        String str;
        while((str=rdr.readLine())!=null){
            b.append(str);
        }
        try(Connection con = DBConnectionProvider.getConnection()){
            con.createStatement().executeUpdate(b.toString());
        }
    }
}
