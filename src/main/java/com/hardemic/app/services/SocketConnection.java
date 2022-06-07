/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hardemic.app.services;

import com.hardemic.app.utils.Logs;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import java.net.URISyntaxException;

public class SocketConnection {
    private static Socket socket = null;
    private static final Logs logs = new Logs();
    
    private static SocketConnection uniqueInstance;

    private SocketConnection() {
        try {
            socket = IO.socket("20.127.58.43:3333");
        } catch (URISyntaxException ex) {
            logs.warning("Erro ao se conectar no servidor socket");
        }
    }
    
    public static synchronized SocketConnection getInstance() {
        if (uniqueInstance == null){
            uniqueInstance = new SocketConnection();
        }
        return uniqueInstance;
    }
    
    public Socket getSocket() {
        return socket.connect();
    };
}
