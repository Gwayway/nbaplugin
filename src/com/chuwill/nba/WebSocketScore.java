package com.chuwill.nba;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class WebSocketScore extends WebSocketClient {
    public WebSocketScore(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {

    }

    @Override
    public void onMessage(String message) {
        System.out.println(message);
        DataCenter.countimes++;
        String a=message;
        if (DataCenter.countimes%DataCenter.UPDATE_FRAME==0){
            if (DataCenter.JSP!=null){
                DataCenter.testData=message.substring(11,message.length()-1);
                DataCenter.JSP.repaint();
            }
        }

    }

    @Override
    public void onClose(int code, String reason, boolean remote) {

    }

    @Override
    public void onError(Exception ex) {

    }
}
