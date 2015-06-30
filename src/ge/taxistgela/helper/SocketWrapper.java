package ge.taxistgela.helper;

import com.google.gson.Gson;

/**
 * Created by GIO on 6/30/2015.
 */
public class SocketWrapper {
    private String codeName;
    private Object data;

    public SocketWrapper(String codeName, Object data) {
        this.data = data;
        this.codeName = codeName;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
