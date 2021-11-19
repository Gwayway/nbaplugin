package com.chuwill.nba;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.components.JBScrollPane;
import org.jdesktop.swingx.JXTable;

import javax.swing.*;
import java.awt.*;

public class FullDataNba {
    private JPanel mainItemPanel;
    private JPanel itemTop;
    private JPanel itemTable;
    private String jsonStr=DataCenter.testData;
    private ToolWindow toolWindow;
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
    String[] keyname={
            "name",
            "position",
            "mins",
            "fga",
            "fgm",
            "tpa",
            "tpm",
            "fta",
            "ftm",
            "oreb",
            "dreb",
            "reb",
            "asts",
            "pf",
            "stl",
            "to",
            "blk",
            "pts",
            "net_points"
    };
    String[] everyTitle={"主场首发","主场替补","客场首发","客场替补"};
    String[] team= {"home_start","home_reserve","away_start","away_reserve"};

    public FullDataNba(ToolWindow toolWindow) {
        this.toolWindow=toolWindow;
    }

    private  JPanel initOneDataPanel(String[][] dataStr,String tabletile){

        JPanel mainItemPanel=new JPanel(new BorderLayout());
        JLabel jLabel=new JLabel(tabletile,JLabel.LEFT);
        JPanel itemTable=new JPanel(new BorderLayout());
        JTable dataTable=new JXTable(dataStr,title);
        mainItemPanel.add(jLabel,BorderLayout.NORTH);
        mainItemPanel.add(itemTable,BorderLayout.CENTER);
        itemTable.add(dataTable.getTableHeader(),BorderLayout.NORTH);
        itemTable.add(dataTable,BorderLayout.CENTER);

        return mainItemPanel;
    }

    public  JScrollPane init(){
        String[][][] data=dataNbaJsonObjToArr(jsonStr);
        JPanel mainPanel=new JPanel();
        for (int i=0;i<team.length;i++){
            mainPanel.add(initOneDataPanel(data[i],everyTitle[i]));
        }
        BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(boxLayout);
        JScrollPane jScrollPane=new JBScrollPane(mainPanel);


        return jScrollPane;
    }

    private String[][][] dataNbaJsonObjToArr(String jsonStr){

        JSONObject jsonObjectLv1=JSONObject.parseObject(jsonStr).getJSONObject("score");
        JSONArray[] jsonArray=new JSONArray[team.length];
        for (int i=0 ;i<team.length;i++){
            String str=jsonObjectLv1.getString(team[i]);
            jsonArray[i]=JSONArray.parseArray(str);

        }
        String[][][] json2str=new String[jsonArray.length][20][keyname.length];
        for (int i=0;i<jsonArray.length;i++){
            for (int j=0;j<jsonArray[i].size();j++){
                JSONObject jb=jsonArray[i].getJSONObject(j);
                for(int k=0;k<keyname.length;k++){
                    json2str[i][j][k]=jb.getString(keyname[k]);
                }

            }

        }

        return json2str;
    }

}
