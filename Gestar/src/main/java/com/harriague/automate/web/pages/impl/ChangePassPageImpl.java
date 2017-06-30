package com.harriague.automate.web.pages.impl;

import org.openqa.selenium.By;
import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.BasePage;
import com.harriague.automate.web.pages.ChangePassPage;

public class ChangePassPageImpl extends BasePage implements ChangePassPage {

	
	private static final By OLD_PWD = By.xpath("//input[@id='oldpassword']");

	private static final By NEW_PWD = By.xpath("//input[@id='newpassword']");
	private static final By CONF_NEW_PWD = By.xpath("//input[@id='confirm_password']");
	private static final By ACEPTAR_BTN = By.xpath("//input[@id='submit' and @value='Aceptar']");
	
	
	public ChangePassPageImpl(Agent agent) {
		super(agent);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void ChangeInfo() throws AgentException {
		// TODO Auto-generated method stub
		
		agent.writeInElement(OLD_PWD, "");
		agent.writeInElement(NEW_PWD, "");
		agent.writeInElement(CONF_NEW_PWD, "");
		agent.click(ACEPTAR_BTN);
		
	}

}
