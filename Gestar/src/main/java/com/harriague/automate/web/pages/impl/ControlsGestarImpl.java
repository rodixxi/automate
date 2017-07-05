package com.harriague.automate.web.pages.impl;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.BasePage;
import com.harriague.automate.web.pages.ControlsGestar;
import org.openqa.selenium.By;

public class ControlsGestarImpl extends BasePage implements ControlsGestar {


    private static final By LOG_MANUAL = By.xpath("//a[@href='/w/auth/Login?']");
    private static final String USUARIO = "//input[@id='username']";
    private static final String BTNLOGIN = "//button[@class='buttonGradient']";

    /**
     * Constructor
     *
     * @param agent Agent
     */
    public ControlsGestarImpl(Agent agent) {
        super(agent);
    }


    @Override
    public void seleccionarTabPanel(String tab) throws AgentException, InterruptedException{
        By newFileControl = By.xpath("//a[@onclick='doNew(event); return false;']");
        if (agent.checkElementIsDisplayed(newFileControl)){
            agent.click(newFileControl);
        }
    }


    @Override
    public void crearNuevoArchivoEnFormulario() throws AgentException, InterruptedException {
        By newFileControl = By.xpath("//a[@onclick='doNew(event); return false;']");
        if (agent.checkElementIsDisplayed(newFileControl)){
            agent.click(newFileControl);
        }
    }
}
