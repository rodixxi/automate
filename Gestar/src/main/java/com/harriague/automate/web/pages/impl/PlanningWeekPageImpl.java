package com.harriague.automate.web.pages.impl;

 

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.BasePage;
import com.harriague.automate.web.pages.PlanningWeekPage; 

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class PlanningWeekPageImpl extends BasePage implements PlanningWeekPage {

	private static final By DATE = By.xpath("//input[@class='week-picker hasDatepicker']");
	private static final String CHAIN_TEXT = "****";
	private  String TEAM = "//select[@id='team']/option[@value='" + CHAIN_TEXT + "']";
	private static final By PLANIFICAR = By.xpath("//button[@class='button_action']");
	
	public PlanningWeekPageImpl(Agent agent) {
		super(agent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void SelectWeek() throws AgentException {
		// TODO Auto-generated method stub
		agent.writeInElement(DATE, "06/19/2017");
		
	}

	@Override
	public void SelectTeam(String team) throws AgentException {
		// TODO Auto-generated method stub
		agent.click(By.xpath( TEAM.replace(CHAIN_TEXT, team)));
		
	}

	@Override
	public void BotonPlanificar() throws AgentException {
		// TODO Auto-generated method stub
		agent.click(PLANIFICAR);
	}
	

}
