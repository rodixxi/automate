package com.harriague.automate.web.pages.impl;

import org.openqa.selenium.By;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.BasePage;
import com.harriague.automate.web.pages.LoginPage;

//import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.concurrent.TimeUnit;


public class LoginPageImpl extends BasePage implements LoginPage {

	// xpath para elementos que van a ser resaltados
	private static final String USUARIO = "#username";
	private static final String PWD = "#password";
	private static final String BTNLOGIN = "button.buttonGradient";
	//private static final String LNKLOGIN =  "//a[@href='/w/auth/Login?']";
	private static  final String TEXT_INPUT_INSTANCE = "#instanceName";
	private static final String CMBINSTANCE = "//select[@id='instanceName']/option[@value='DESAV2']";
	private static final String PUBLICFOLDER = "//div[@id='1001']";
	private static final String INCIDENTFOLDER = "//div[@id='5488']";
	private static final String ITILROOT = "//div[@id='5487']";
	private static final String MNUDOCUMENTS = "//span[@lang-str='20']";
	private static final String CLICKNUEVO = "//li[@onclick='doNew(event); return false;']/a/div[@title='Nuevo']";
	private static final By INSTANCIA = By.cssSelector( "a[href='/SUPERVIELLE_W/auth/Login?']");
	private static final By USER_NAME = By.cssSelector("div#user_name'");
	private static final By LOGOFF = By.cssSelector("a#logoffButton");
	private static final By LOG_MANUAL = By.cssSelector("a[href='/w/auth/Login?']");
	
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
		agent.writeInElement(By.cssSelector(USUARIO), "admin");
		agent.writeInElement(By.cssSelector(PWD), "");
		agent.click(By.xpath(CMBINSTANCE));
		agent.click(By.cssSelector(BTNLOGIN));
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
    public void doLogin(String usuario, String instance) throws AgentException, InterruptedException {
        if(agent.checkElementIsDisplayed(LOG_MANUAL)) {
            agent.click(LOG_MANUAL);
        }

        agent.writeInElement(By.cssSelector(USUARIO), usuario);
        agent.selectSelectorOption(By.cssSelector("#instanceName"), instance);
        agent.click(By.cssSelector(BTNLOGIN));

    }
	
	public void hacerLogin(String usuario, String pwd, String instance) throws AgentException, InterruptedException {
		if(agent.checkElementIsDisplayed(LOG_MANUAL)) {
			agent.click(LOG_MANUAL);
		}
		
		agent.writeInElement(By.cssSelector(USUARIO), usuario);
		agent.writeInElement(By.cssSelector(PWD), pwd);
        agent.selectSelectorOption(By.cssSelector("#instanceName"), instance);
		agent.click(By.cssSelector(BTNLOGIN));
		  
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

	@Override
	public void doLoginWithPassAndTextInstance(String user, String pass, String instance) throws AgentException {
		if(agent.checkElementIsDisplayed(LOG_MANUAL)) {
			agent.click(LOG_MANUAL);
		}

		agent.writeInElement(By.cssSelector(USUARIO), user);
		agent.writeInElement(By.cssSelector(PWD), pass);
		agent.writeInElement(By.cssSelector(TEXT_INPUT_INSTANCE), instance);
		agent.click(By.cssSelector(BTNLOGIN));
	}

	@Override
	public void switchURL(String url) throws AgentException {
		agent.navigateTo(url);
	}
}
