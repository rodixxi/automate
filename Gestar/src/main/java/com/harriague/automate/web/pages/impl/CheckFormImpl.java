package com.harriague.automate.web.pages.impl;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.BasePage;
import com.harriague.automate.web.pages.CheckForm;

import java.util.ArrayList;


public class CheckFormImpl extends BasePage implements CheckForm {

    /**
     * Constructor
     *
     * @param agent Agent
     */
    public CheckFormImpl(Agent agent) {
        super(agent);
    }

    @Override
    public void getFormWhere(String field, String valueEqualTo) throws AgentException {
        agent.selectFormWhere(field, valueEqualTo);
    }

    @Override
    public void selectFormWhere(String field, ArrayList<String> options) {
        agent.selectFormWhere(field, options);
    }
}
