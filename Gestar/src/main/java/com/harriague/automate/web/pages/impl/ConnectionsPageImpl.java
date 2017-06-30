package com.harriague.automate.web.pages.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.BasePage;
import com.harriague.automate.web.pages.ConnectionsPage;


public class ConnectionsPageImpl extends BasePage implements ConnectionsPage {

	private static final By TABLE = By.xpath("//div[@id='TABPANE_TabConnection']");
	
	public ConnectionsPageImpl(Agent agent) {
		super(agent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void CheckConnections() throws AgentException {
		 Object e = agent.findElement(TABLE);
		 
		 return;
		 
	}

}
