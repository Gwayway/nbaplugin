package com.chuwill.nba;

import com.alibaba.fastjson.JSONObject;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.Notifications;
import com.intellij.openapi.ui.MessageType;
import org.java_websocket.client.WebSocketClient;

import javax.swing.*;
import java.net.URISyntaxException;
import java.util.function.Function;


public class Untils {

    public static  void notificationWssMessage(String str){
        NotificationGroup notificationGroup=new NotificationGroup("nba_wss_id", NotificationDisplayType.BALLOON,true);
        Notification notification=notificationGroup.createNotification(str, MessageType.WARNING);
        Notifications.Bus.notify(notification);
    }

    public static void  messageIn(String str){
        if(str.length()<3) return;
        String m1="{"+str.split("\\{")[1];
        m1=m1.split("}")[0]+"}";
        DataCenter.messageCache.add(m1);
    }

    public static String messageOut(){
        if (DataCenter.messageCache.size()==0) return null;
        JSONObject jsonObject=JSONObject.parseObject(DataCenter.messageCache.get(0));
        DataCenter.messageCache.remove(0);
        String message="时间:"+jsonObject.getString("time")+"   "+
                        "主客队:"+jsonObject.getString("team")+"   "+
                        "事件:"+jsonObject.getString("event")+"   "+
                        "比分:"+jsonObject.getString("vs")+"   "+
                        "进程:"+jsonObject.getString("process")+"\n";

        return message;
    }

    public  static void wssConnect(WebSocketClient webSocketClient, Function function) throws URISyntaxException {
        if (webSocketClient.isClosed()){
            webSocketClient.connect();
        }
    }

    public static  void appedTextArea(){
        SwingUtilities.invokeLater(() -> DataCenter.textArea.append(messageOut()));
    }

}
