package com.harriague.automate.web.pages.impl;

import org.openqa.selenium.By;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.BasePage;
import com.harriague.automate.web.pages.LoginPage;

//import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;


public class LoginPageImpl extends BasePage implements LoginPage {

	// xpath para elementos que van a ser resaltados
	private static final String USUARIO = "//input[@id='username']";
	private static final String PWD = "//input[@id='password']";
	private static final String BTNLOGIN = "//button[@class='buttonGradient']";
	//private static final String LNKLOGIN =  "//a[@href='/w/auth/Login?']";
	private static final String CMBINSTANCE = "//select[@id='instanceName']/option[@value='DESAV2']";
	private static final String PUBLICFOLDER = "//div[@id='1001']";
	private static final String INCIDENTFOLDER = "//div[@id='5488']";
	private static final String ITILROOT = "//div[@id='5487']";
	private static final String MNUDOCUMENTS = "//span[@lang-str='20']";
	private static final String CLICKNUEVO = "//li[@onclick='doNew(event); return false;']/a/div[@title='Nuevo']";
	private static final By INSTANCIA = By.xpath( "//a[@href='/SUPERVIELLE_W/auth/Login?']");
	private static final By USER_NAME = By.xpath("//div[@id='user_name']");
	private static final By LOGOFF = By.xpath("//a[@id='logoffButton']");
	private static final By LOG_MANUAL = By.xpath("//a[@href='/w/auth/Login?']");
	
	public LoginPageImpl(Agent agent) {
		super(agent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void hacerLogin() throws AgentException, InterruptedException {

		//check winlogon
		if(agent.checkElementIsDisplayed(LOG_MANUAL)) {
			agent.click(LOG_MANUAL);
		}
		/*
		if(agent.checkElementIsDisplayed(INSTANCIA)) {
			agent.click(INSTANCIA);
		}
		*/
		
		//agent.click(By.xpath(LNKLOGIN));
		agent.writeInElement(By.xpath(USUARIO), "admin");
		agent.writeInElement(By.xpath(PWD), "");
		agent.click(By.xpath(CMBINSTANCE));
		agent.click(By.xpath(BTNLOGIN));
		//agent.click(By.xpath(PUBLICFOLDER));
		//agent.click(By.xpath(ITILROOT));
		//agent.click(By.xpath(INCIDENTFOLDER));
		//agent.hover(By.xpath(MNUDOCUMENTS));
		//agent.click(By.xpath(CLICKNUEVO));
		//agent.waitUntil(ExpectedConditions.elementToBeClickable(LOGOFF));

		//agent.waitUntil(30);
		//agent.waitForVanish(LOGOFF);

		log.info("paro en login");
		TimeUnit.SECONDS.sleep(2);
	 		
		log.info("arranco en login");

	}

    @Override
    public void hacerLogin(String usuario, String instance) throws AgentException, InterruptedException {
        if(agent.checkElementIsDisplayed(LOG_MANUAL)) {
            agent.click(LOG_MANUAL);
        }

        agent.writeInElement(By.xpath(USUARIO), usuario);
        agent.click(By.xpath("//select[@id='instanceName']/option[@value='" + instance + "']"));
        agent.click(By.xpath(BTNLOGIN));

    }
	
	public void hacerLogin(String usuario, String pwd, String instance) throws AgentException, InterruptedException {
		if(agent.checkElementIsDisplayed(LOG_MANUAL)) {
			agent.click(LOG_MANUAL);
		}
		
		agent.writeInElement(By.xpath(USUARIO), usuario);
		agent.writeInElement(By.xpath(PWD), pwd);
		agent.click(By.xpath("//select[@id='instanceName']/option[@value='" + instance + "']"));
		agent.click(By.xpath(BTNLOGIN));
		  
	}


    @Override
	public void hacerLogout() throws AgentException {
		//only work if i have a connection
		log.info("entrando a logouff");
		
		//agent.waitUntil(ExpectedConditions.presenceOfElementLocated(LOGOFF));
		
		
		if (agent.checkElementIsDisplayed(LOGOFF)) {
			log.info("hago clic");
			agent.click(LOGOFF);
		}
		else {
			log.info("no encontro el elemento");
		}
		
	}
	
	@Override 
	public void pruebo() throws AgentException {
		log.info("entro a prueba");
	}
}
