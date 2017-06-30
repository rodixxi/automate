package com.harriague.automate.web.pages.impl;

//import java.util.ArrayList;

import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.BasePage;
import com.harriague.automate.web.pages.UserPage;
//import com.harriague.automate.web.pages.GestarPage;


//import org.openqa.selenium.support.ui.ExpectedCondition;
//import org.openqa.selenium.support.ui.ExpectedConditions;

//import java.util.concurrent.TimeUnit;


public class UserPageImpl extends BasePage implements UserPage {
	
	//private static final By NUEVO_CONTACTO = By.xpath("//td[contains(@id, 'st') and contains(@id, 'cnt') and contains(., 'Usuario')]");
	private static final By NUEVO_CONTACTO = By.xpath("//span[@customid='p1i0']");
	
	
	
	public UserPageImpl(Agent agent) {
		super(agent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void OpenMenu(String menu) throws AgentException {
		// TODO Auto-generated method stub
		if (menu == "cuentas") {
			log.info("abro la ventana para crear el nuevo usuario");
			
			return;
			
			// ACTIVO EL RESTO CUANDO SE PUEDA RECORRER FRAME DENTRO DE UN IFRAME

			/*
			WebElement nc = (WebElement) agent.findElement(NUEVO_CONTACTO);
			nc.click();
			*/
		}
		
	}

	
	
}

