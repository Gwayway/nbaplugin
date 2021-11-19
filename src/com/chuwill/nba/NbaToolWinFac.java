package com.chuwill.nba;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class NbaToolWinFac implements ToolWindowFactory {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        NbaToolWin nbaToolWin=new NbaToolWin(toolWindow);
        FullDataNba nbaToolDataWin=new FullDataNba(toolWindow);
        JScrollPane jScrollPane=nbaToolDataWin.init();
        DataCenter.JSP=jScrollPane;
        ContentFactory contentFactory=ContentFactory.SERVICE.getInstance();
        Content content=contentFactory.createContent(nbaToolWin.getPanel(),"球赛",false);
        Content content2=contentFactory.createContent(jScrollPane,"数据",false);
        toolWindow.getContentManager().addContent(content);
        toolWindow.getContentManager().addContent(content2);
    }
}
