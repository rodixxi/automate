package com.harriague.automate.web.pages.impl;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.BasePage;
import com.harriague.automate.web.control.FormOptionsBar;
import com.harriague.automate.web.pages.FormControlBar;

public class FormControlBarImpl extends BasePage implements FormControlBar{

    private FormOptionsBar formOptionsBar = new FormOptionsBar();

    /**
     * Constructor
     *
     * @param agent Agent
     */
    public FormControlBarImpl(Agent agent) {
        super(agent);
    }

    @Override
    public void saveForm() throws AgentException {
        agent.click(formOptionsBar.getSaveExit());
    }
}
