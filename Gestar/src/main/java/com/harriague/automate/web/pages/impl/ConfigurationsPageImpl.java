package com.harriague.automate.web.pages.impl;


import org.openqa.selenium.By;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.BasePage;
import com.harriague.automate.web.pages.ConfigurationsPage; 

public class ConfigurationsPageImpl extends BasePage implements ConfigurationsPage {

	private static final By DIV_DOORS_INI = By.xpath("//div[@id='doorsini']");
	private static final By DIV_SETTINGS = By.xpath("//div[@id='setting']");
	private static final By DIV_INSTANCES = By.xpath("//div[@id='settings' and contains(., 'Instances')]");
	private static final By DIV_LDAP = By.xpath("//div[@id='settings' and contains(., 'LDAP')]");
	private static final By BTN_VOLVER = By.xpath("//*[@id='Volver']");
	
	private static final By ATTACHMENTS_PATH = By.xpath("//*[@id='ATTACHMENTS_PATH']/td[2]");
	
	public ConfigurationsPageImpl(Agent agent) {
		super(agent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void OpenElement(String element) throws AgentException {
		// TODO Auto-generated method stub
		By by = null;
		
		switch (element){
		case "doors.ini":
			by = DIV_DOORS_INI;
			break;
		case "Settings.Administrator":
			by = DIV_SETTINGS;
			break;
		case "Instances.Administrator":
			by = DIV_INSTANCES;
			break;
		case "Servidores.ldap":
			by = DIV_LDAP;
			break;
		}
		if( by != null)
			agent.click(by);
		
	}

	@Override
	public void ChangeElement(String element, String value) throws AgentException {
		// TODO Auto-generated method stub
		By by = null;
		
		switch (element){
		case "ATTACHMENTS_PATH":
			by = ATTACHMENTS_PATH;
			break;
		
		}
		if( by != null) {
			agent.doubleClick(by);
			//agent.doubleClickWriteAndSend(by, value);
			/*
			By by2 = By.xpath("//input[@id='" + element + "']");
			agent.writeInElement(by2, value);
			*/
		}
			
		
	}

	@Override
	public void GoBack() throws AgentException {
		// TODO Auto-generated method stub
		agent.click(BTN_VOLVER);
	}

	
		
}
