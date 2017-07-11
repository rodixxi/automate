package com.harriague.automate.web.pages.impl;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.BasePage;
import com.harriague.automate.web.control.Tab;
import com.harriague.automate.web.control.TextBox;
import com.harriague.automate.web.pages.ControlsGestar;
import com.harriague.automate.module.web.agent.CommonAgentImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ControlsGestarImpl extends BasePage implements ControlsGestar {


    private static final By LOG_MANUAL = By.xpath("//a[@href='/w/auth/Login?']");
    private static final String USUARIO = "//input[@id='username']";
    private static final String BTNLOGIN = "//button[@class='buttonGradient']";

    private WebDriver driver;

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
        Tab newTab = new Tab(tab);
        if (agent.checkElementIsDisplayed(newTab.getXpathSelectorByName())){
            agent.click(newTab.getXpathSelectorByName());
        }
        agent.checkElementIsDisplayed(newTab.tabSelectedByName());
    }


    @Override
    public void crearNuevoArchivoEnFormulario() throws AgentException, InterruptedException {
        By newFileControl = By.xpath("//a[@onclick='doNew(event); return false;']");
        if (agent.checkElementIsDisplayed(newFileControl)){
            agent.click(newFileControl);
        }
    }

    @Override
    public void ingresoInputACampoRequerido(String input, String campo) throws AgentException, InterruptedException {
        TextBox textBox_object = new TextBox(campo);
        textBox_object.setIsRequired();
        if (agent.checkElementIsDisplayed(textBox_object.getXpathSelectorByName())){
            agent.writeInElement(textBox_object.getXpathSelectorByName(), input);
        } else {
            System.out.println("No se encontro el campo");
        }
    }

    @Override
    public void ingresoInputACampo(String input, String campo) throws AgentException, InterruptedException {
        TextBox textBox_object = new TextBox(campo);
        if (agent.checkElementIsDisplayed(textBox_object.getXpathSelectorByName())){
            agent.writeInElement(textBox_object.getXpathSelectorByName(), input);
        } else {
            System.out.println("No se encontro el campo");
        }
    }

    @Override
    public void ingresoInputACampoNumerico(String input, String campo) throws AgentException {
        TextBox textBox_object = new TextBox(campo);
        if (agent.checkElementIsDisplayed(textBox_object.getXpathSelectorByName())){
            agent.writeInElement(textBox_object.getXpathSelectorByName(), input);
        } else {
            System.out.println("No se encontro el campo");
        }
    }

    @Override
    public void ingresoInputACampoMultiple(String input, String campo) throws AgentException {
        TextBox textBox_object = new TextBox(campo, TextBox.Modes.multiple_line);
        if (agent.checkElementIsDisplayed(textBox_object.getXpathSelectorByName())){
            agent.writeInElement(textBox_object.getXpathSelectorByName(), input);
        } else {
             System.out.println("No se encontro el campo");
        }
    }

    @Override
    public void ingresoInputACampoPassword(String input, String campo) throws AgentException {
        TextBox textBox_object = new TextBox(campo, TextBox.Modes.password);
        if (agent.checkElementIsDisplayed(textBox_object.getXpathSelectorByName())){
            agent.writeInElement(textBox_object.getXpathSelectorByName(), input);
        } else {
            System.out.println("No se encontro el campo");
        }
    }
}
