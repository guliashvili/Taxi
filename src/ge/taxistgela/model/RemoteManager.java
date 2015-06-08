package ge.taxistgela.model;

import javax.websocket.RemoteEndpoint;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex on 6/5/2015.
 */
public class RemoteManager implements RemoteManagerAPI {

    public static final int USER_REMOTE = 1;
    public static final int DRIVER_REMOTE = 2;

    private Map<String, RemoteEndpoint.Async> userRemotes;
    private Map<String, RemoteEndpoint.Async> driverRemotes;

    public RemoteManager() {
        this.userRemotes = Collections.synchronizedMap(new HashMap<>());
        this.driverRemotes = Collections.synchronizedMap(new HashMap<>());
    }

    @Override
    public void addRemote(int remoteType, String token, RemoteEndpoint.Async remote) {
        Map<String, RemoteEndpoint.Async> remotes = getMap(remoteType);

        if (remotes != null) {
            remotes.put(token, remote);
        }
    }

    @Override
    public void removeRemote(int remoteType, String token) {
        Map<String, RemoteEndpoint.Async> remotes = getMap(remoteType);

        if (remotes != null) {
            remotes.remove(token);
        }
    }

    @Override
    public void sendMessage(int remoteType, String token, String message) {
        Map<String, RemoteEndpoint.Async> remotes = getMap(remoteType);

        if (remotes != null && remotes.containsKey(token)) {
            remotes.get(token).sendText(message);
        }
    }

    private Map<String, RemoteEndpoint.Async> getMap(int sessionType) {
        switch (sessionType) {
            case USER_REMOTE:
                return userRemotes;
            case DRIVER_REMOTE:
                return driverRemotes;

            default:
                return null;
        }
    }
}
