package ge.taxistgela.admin;

/**
 * Created by Alex on 6/30/2015.
 */
public class Admin {
    private static final String USERNAME = "taxistgela";
    private static final String PASSWORD = "zoro";

    public Admin() {
    }

    public boolean checkLogin(String username, String password) {
        return !(username == null || password == null) && USERNAME.equals(username) && PASSWORD.equals(password);
    }
}
