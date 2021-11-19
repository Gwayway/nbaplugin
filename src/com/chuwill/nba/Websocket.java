package com.chuwill.nba;

import java.net.URI;

import com.intellij.openapi.wm.ToolWindow;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import javax.swing.*;


public class Websocket extends WebSocketClient {

    private ToolWindow toolWindow;
    public Websocket(URI serverUri, ToolWindow toolWindow) {

        super(serverUri);
        this.toolWindow=toolWindow;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
    }

    @Override
    public void onMessage(String message) {
        Untils.messageIn(message);
        DataCenter.textArea.insert(Untils.messageOut(),0);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        DataCenter.WSS_CONNECT=null;
        Untils.notificationWssMessage("连接已经关闭!");
    }

    @Override
    public void onError(Exception ex) {

    }
}
