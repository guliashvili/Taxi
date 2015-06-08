package ge.taxistgela.model;

import javax.websocket.RemoteEndpoint;

/**
 * Created by Alex on 6/5/2015.
 */
public interface RemoteManagerAPI {

    /**
     * Saves the remote depending on the remote type and token.
     *
     * @param remoteType
     * @param token
     * @param remote
     */
    void addRemote(int remoteType, String token, RemoteEndpoint.Async remote);

    /**
     * Removes the remote depending on the remote type and token.
     *
     * @param remoteType
     * @param token
     */
    void removeRemote(int remoteType, String token);

    /**
     * Sends the message to remote endpoint depending on the remote type and token.
     *
     * @param remoteType
     * @param message
     */
    void sendMessage(int remoteType, String token, String message);
}
