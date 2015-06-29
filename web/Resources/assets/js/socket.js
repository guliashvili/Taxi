/**
 * Created by Alex on 6/29/2015.
 */

function (sessionType, token) {
    var websocket = new WebSocket("ws://" + window.location.host + "/wsapp/" + sessionType + "/" + token);

    websocket.onopen = function (arg) {
        console.log("success", "connected");
    };

    websocket.onmessage = function (arg) {
        console.log("success", arg.data);
    };

    websocket.onclose = function (arg) {
        console.log("success", "disconnected");
    };

    websocket.onerror = function (arg) {
        console.error(arg.data);
    };

    return websocket;
}