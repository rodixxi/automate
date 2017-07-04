package com.harriague.automate.core.page;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.steps.StepBase;
import com.harriague.automate.module.web.agent.CommonAgentImpl;

import org.apache.log4j.Logger;

public class BaseWebPage extends BasePage {

	protected final Logger log = Logger.getLogger(StepBase.class.getName());

	/**
	 * Agent to use for connect to application
	 */
	protected CommonAgentImpl agent;

	/**
	 * Constructor
	 * @param agent Agent
	 */
	public BaseWebPage(Agent agent) {
	    super(agent);
		this.agent = (CommonAgentImpl) agent;
	}

}
