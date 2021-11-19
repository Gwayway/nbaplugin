package com.chuwill.nba;

import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;
import java.awt.*;

public class NbaToolDataWin {

    private JPanel jPanel;
    private JTable jTable;

    public NbaToolDataWin(ToolWindow toolWindow) {

        init();
    }

    private void init(){

        jPanel=new JPanel(new BorderLayout());
        nbaData(jPanel);
    }

    private void nbaData(JPanel jPanel){
        String[] title={
                "名字",
                "位置",
                "出场时间",
                "投射(次数)",
                "命中(次数)",
                "三分出手",
                "三分命中",
                "罚篮出手次数",
                "罚篮命中次数",
                "前场篮板球",
                "防守篮板球",
                "篮板球",
                "助攻",
                "个人犯规",
                "抢断",
                "失误",
                "盖帽",
                "总得分",
                "上场得分变化"};

        String[][] data={{"","","","","","","","","","","","","","","","","","",""}};
        jTable=new JTable(data,title);
        jPanel.add(jTable.getTableHeader(),BorderLayout.NORTH);
        jPanel.add(jTable,BorderLayout.CENTER);
    }

    public JPanel getInstance(){return jPanel;}
}
