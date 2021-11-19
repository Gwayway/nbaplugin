package com.chuwill.nba;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.EditorTextField;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class InputPanel  extends DialogWrapper {

    private EditorTextField textField;
    public  InputPanel(){
        super(true);
        init();
        setTitle("自己找入参");
    }


    @Nullable
    @Override
    protected  JComponent createCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        textField=new EditorTextField();
        panel.add(textField,BorderLayout.NORTH);
        return panel;
    }

    @Override
    protected JComponent createSouthPanel() {
        JPanel panel=new JPanel(new FlowLayout());
        JButton sendbutton=new JButton("发送参数");
        JButton closebutton=new JButton("结束");

        sendbutton.addActionListener(e->{
            if(DataCenter.WSS_CONNECT!=null) DataCenter.WSS_CONNECT.send(textField.getText());

        });
        closebutton.addActionListener(e->{
            if(DataCenter.WSS_CONNECT!=null) DataCenter.WSS_CONNECT.close();
        });
        panel.add(sendbutton);
        panel.add(closebutton);
        return panel;
    }
}
