package com.harriague.automate.core.page;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.module.android.osnative.agent.AgentImpl;

import org.apache.log4j.Logger;

public class AndroidBasePage extends BasePage {

    protected final Logger log = Logger.getLogger(AndroidBasePage.class.getName());

    /**
     * Agent to use for connect to application
     */
    protected AgentImpl agent;

    /**
     * Constructor
     * 
     * @param agent Agent
     */
    public AndroidBasePage(Agent agent) {
        super(agent);
        this.agent = (AgentImpl) agent;
    }

}
