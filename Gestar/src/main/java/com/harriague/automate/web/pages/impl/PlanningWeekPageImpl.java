package com.harriague.automate.web.pages.impl;

 

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.BasePage;
import com.harriague.automate.web.pages.PlanningWeekPage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import java.util.Calendar;


public class PlanningWeekPageImpl extends BasePage implements PlanningWeekPage {

	private static final By DATE = By.xpath("//input[@class='week-picker hasDatepicker']");
	private static final String CHAIN_TEXT = "****";
	private  String selectorTeam = "team";
	private static final By PLANIFICAR = By.xpath("//button[@class='button_action']");
	
	public PlanningWeekPageImpl(Agent agent) {
		super(agent);
		// TODO Auto-generated constructor stub
	}

	public static Calendar nextDayOfWeek(int dow){
		Calendar date = Calendar.getInstance();
		int diff = dow - date.get(Calendar.DAY_OF_WEEK);
		if(!(diff > 0)){
			diff += 7;
		}
		date.add(Calendar.DAY_OF_MONTH, diff);
		return date;
	}

	@Override
	public void SelectWeek() throws AgentException {
		// TODO Auto-generated method stub
		Date date = nextDayOfWeek(Calendar.MONDAY).getTime();
		//agent.writeInElement(DATE, date);
        agent.selectDateFromDatePicker(DATE, date);
		
	}

	@Override
	public void SelectTeam(String team) throws AgentException {
		// TODO Auto-generated method stub
		agent.selectSelectorOption(selectorTeam, team);
		
	}

	@Override
	public void BotonPlanificar() throws AgentException {
		// TODO Auto-generated method stub
		agent.click(PLANIFICAR);
	}
	

}
