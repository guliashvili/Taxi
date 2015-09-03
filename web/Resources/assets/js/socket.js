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