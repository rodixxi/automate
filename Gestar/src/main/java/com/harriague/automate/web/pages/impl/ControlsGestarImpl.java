package com.harriague.automate.web.pages.impl;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.BasePage;
import com.harriague.automate.web.control.*;
import com.harriague.automate.web.pages.ControlsGestar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

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
    public void crearNuevoArchivoEnFormulario() throws AgentException, InterruptedException {
        By newFileControl = By.xpath("//a[@onclick='doNew(event); return false;']");
        if (agent.checkElementIsDisplayed(newFileControl)){
            agent.click(newFileControl);
        }
    }

    @Override
    public void seleccionarTabPanel(String tab) throws AgentException, InterruptedException{
        Tab newTab = new Tab(tab);
        if (agent.checkElementIsDisplayed(newTab.getCssSelector())){
            agent.click(newTab.getCssSelector());
        }
        //agent.checkElementIsDisplayed(newTab.tabSelectedByName());
    }

    @Override
    public void ingresoInputACampoRequerido(String input, String campo) throws AgentException, InterruptedException {
        TextBox textBox_object = new TextBox(campo);
        textBox_object.setIsRequired();
        if (agent.checkElementIsDisplayed(textBox_object.getCssSelector())){
            agent.writeInElement(textBox_object.getCssSelector(), input);
        } else {
            System.out.println("No se encontro el campo");
        }
    }

    @Override
    public void ingresoInputACampo(String input, String campo) throws AgentException, InterruptedException {
        TextBox textBox_object = new TextBox(campo);
        if (agent.checkElementIsDisplayed(textBox_object.getCssSelector())){
            agent.writeInElement(textBox_object.getCssSelector(), input);
        } else {
            System.out.println("No se encontro el campo");
        }
    }

    @Override
    public void ingresoInputACampoNumerico(String input, String campo) throws AgentException {
        TextBox textBox_object = new TextBox(campo);
        if (agent.checkElementIsDisplayed(textBox_object.getCssSelector())){
            agent.writeInElement(textBox_object.getCssSelector(), input);
        } else {
            System.out.println("No se encontro el campo");
        }
    }

    @Override
    public void ingresoInputACampoMultiple(String input, String campo) throws AgentException {
        TextBox textBox_object = new TextBox(campo, TextBox.Modes.multiple_line);
        if (agent.checkElementIsDisplayed(textBox_object.getCssSelector())){
            agent.writeInElement(textBox_object.getCssSelector(), input);
        } else {
             System.out.println("No se encontro el campo");
        }
    }

    @Override
    public void ingresoInputACampoPassword(String input, String campo) throws AgentException {
        TextBox textBox_object = new TextBox(campo, TextBox.Modes.password);
        if (agent.checkElementIsDisplayed(textBox_object.getCssSelector())){
            agent.writeInElement(textBox_object.getCssSelector(), input);
        } else {
            System.out.println("No se encontro el campo");
        }
    }

    @Override
    public void openPopup(String url, String adjunto) throws AgentException {
        Attachment attch_object = new Attachment(adjunto);
        if (agent.checkElementIsDisplayed(attch_object.getCssSelector())){
            agent.click(attch_object.getCssSelector());
            String princalwindows = agent.switchToPopup();
            agent.selectFile(attch_object.getInputButton(), url);
            agent.click(attch_object.getAddButton());
            agent.click(attch_object.getCloseButton());
            agent.switchToPopup(princalwindows);
        } else {
            System.out.println("No se encontro el campo");
        }

    }

    @Override
    public void cargarDate(String date, String hh, String mm, String dtpicker) throws AgentException {
        DTPicker dtPicker_object = new DTPicker(dtpicker);
        if (agent.checkElementIsDisplayed(dtPicker_object.getButtonXpath())){
            //agent.click(dtPicker_object.getButtonXpath());
            agent.writeInElement(dtPicker_object.getDateXpath(), date);
            agent.writeInElement(dtPicker_object.getHhXpath(), hh);
            agent.writeInElement(dtPicker_object.getMmXpath(), mm);
        } else {
            System.out.println("No se encontro el campo");
        }
    }

    @Override
    public void selectOption(String opcion, String selector) throws AgentException {
        SelectControl selectControl = new SelectControl(selector);
        if (agent.checkElementIsDisplayed(selectControl.getCssSelector())){
            agent.selectSelectorOption(selectControl.getCssSelector(), opcion);
        }else {
            System.out.println("No se encontro el campo");
        }
    }

    /**
     * Esta a diferencia de los demas usa como parametro el nombre del campo y no la etiqueta
     * @param options
     * @param selector
     */
    @Override
    public void selectMultipleoptions(ArrayList<String> options, String selector) throws AgentException {
        SelectorMultiple selectorMultiple = new SelectorMultiple(selector, "");
        for (String option : options){
            agent.selectOptions(option, selectorMultiple.getLeftOptions());
        }
        agent.click(selectorMultiple.getToRightButton());
    }
}
