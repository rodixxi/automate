package com.harriague.automate.core.page;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.steps.StepBase;
import org.apache.log4j.Logger;

public class BasePage {

	protected final Logger log = Logger.getLogger(StepBase.class.getName());

	/**
	 * Agent to use for connect to application
	 */
	protected Agent agent;

	/**
	 * Constructor
	 * @param agent Agent
	 */
	public BasePage(Agent agent) {
		this.agent = agent;
	}

}
