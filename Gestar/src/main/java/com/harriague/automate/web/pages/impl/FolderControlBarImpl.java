package com.harriague.automate.web.pages.impl;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.BasePage;
import com.harriague.automate.web.control.FolderOptionsBar;
import com.harriague.automate.web.pages.FolderControlBar;

public class FolderControlBarImpl extends BasePage implements FolderControlBar{

    private FolderOptionsBar folderOptionsBar = new FolderOptionsBar();

    /**
     * Constructor
     *
     * @param agent Agent
     */
    public FolderControlBarImpl(Agent agent) {
        super(agent);
    }

    @Override
    public void atFolderLevel() throws AgentException {
        agent.checkElementIsDisplayed(folderOptionsBar.getIdSelector());
    }

    @Override
    public void selectOptionFromDocumentsMenu(String option) {
        agent.selectOptionMenu(folderOptionsBar.getDocumentsOptionsSelector(), option);
    }

    @Override
    public void creanteNewFileInFolder() throws AgentException {
        agent.click(folderOptionsBar.getNewFormSelector());
    }
}
