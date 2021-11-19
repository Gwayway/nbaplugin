package com.chuwill.nba;

import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.EditorTextField;
import com.intellij.ui.components.JBScrollPane;
import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;

public class NbaToolWin {

    private JButton startConnect;
    private JButton closeConnect;
    private JButton sendMessage;
    private JTextArea nbaData;
    private JPanel mainPanel=new JPanel(new BorderLayout());
    public static EditorTextField textField;
    private JScrollPane dataScroll;
    private ToolWindow toolWindow;

    public NbaToolWin(ToolWindow toolWindow) {
        init();
        this.toolWindow=toolWindow;

    }

    private void init(){
        buttonInit();
        bottomInin();
    }

    private void buttonInit(){
        Box topBox=Box.createHorizontalBox();
        startConnect=new JButton("连接");
        closeConnect=new JButton("断开连接");
        sendMessage=new JButton("发送信息");
        DataCenter.editorTextField=textField=new EditorTextField();
        startConnect.addActionListener(e->{
            try {
                if(DataCenter.WSS_CONNECT==null){
                    DataCenter.WSS_CONNECT=new Websocket(new URI(DataCenter.URL),this.toolWindow);
                    DataCenter.WSS_CONNECT.connect();
                }
                if(DataCenter.WSS_CONNECT_GETDATA==null){
                    DataCenter.WSS_CONNECT_GETDATA=new WebSocketScore(new URI(DataCenter.URL));
                    DataCenter.WSS_CONNECT_GETDATA.connect();
                }
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }

        });
        closeConnect.addActionListener(e->{
            if(DataCenter.WSS_CONNECT!=null) DataCenter.WSS_CONNECT.close();
            if(DataCenter.WSS_CONNECT_GETDATA!=null) DataCenter.WSS_CONNECT_GETDATA.close();
        });

        sendMessage.addActionListener(e->{
            if(DataCenter.WSS_CONNECT!=null&&textField.getText()!="") DataCenter.WSS_CONNECT.send("42[\"join\",{\"id\":\""+textField.getText()+"\",\"type\":\"live\"}]");
            DataCenter.WSS_CONNECT_GETDATA.send("42[\"join\",{\"id\":\""+textField.getText()+"\",\"type\":\"score\"}]");
        });

        topBox.add(startConnect);
        topBox.add(closeConnect);
        topBox.add(sendMessage);
        topBox.add(textField);
        mainPanel.add(topBox,BorderLayout.NORTH);

    }


    private  void bottomInin(){
        nbaData=new JTextArea();
        nbaData.setLineWrap(true);
        nbaData.paintImmediately(nbaData.getBounds());
        DataCenter.textArea=nbaData;
        dataScroll=new JBScrollPane(nbaData);
        Box bottomBox=Box.createVerticalBox();
        bottomBox.add(dataScroll);
        mainPanel.add(bottomBox,BorderLayout.CENTER);
    }


    public  JPanel getPanel(){
        return mainPanel;
    }
}
