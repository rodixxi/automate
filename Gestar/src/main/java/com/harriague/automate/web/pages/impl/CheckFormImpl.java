package com.harriague.automate.web.pages.impl;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.page.BasePage;
import com.harriague.automate.web.pages.CheckForm;


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
    public void getFormWhere(String field, String valueEqualTo) {
        agent.selectFormWhere(field, valueEqualTo);
    }
}
