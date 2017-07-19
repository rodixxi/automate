package com.harriague.automate.web.pages.impl;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.BasePage;
import com.harriague.automate.core.structures.FlawedTimeUnit;
import com.harriague.automate.web.control.*;
import com.harriague.automate.web.pages.ControlsGestar;
import org.openqa.selenium.By;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ControlsGestarImpl extends BasePage implements ControlsGestar {

    /**
     * Constructor
     *
     * @param agent Agent
     */
    public ControlsGestarImpl(Agent agent) {
        super(agent);
    }


    @Override
    public void creanteNewFileInFolder() throws AgentException, InterruptedException {
        By newFileControl = By.xpath("//a[@onclick='doNew(event); return false;']");
        if (agent.checkElementIsDisplayed(newFileControl)) {
            agent.click(newFileControl);
        }
    }

    /**
     * Selecciona un tab
     *
     * @param tab
     * @throws AgentException
     * @throws InterruptedException
     * @authot Rodrigo Crespillo
     * @version 1.0 10/07/2017
     */
    @Override
    public void selectTabPanel(String tab) throws AgentException, InterruptedException {
        Tab newTab = new Tab(tab);
        if (agent.checkElementIsDisplayed(newTab.getCssSelector())) {
            agent.click(newTab.getCssSelector());
        }
    }


    /**
     * Ingresa texto a un campo requerido
     *
     * @param input
     * @param campo
     * @throws AgentException
     * @throws InterruptedException
     * @authot Rodrigo Crespillo
     * @version 1.0 10/07/2017
     */
    @Override
    public void inputToRequiredTextBox(String input, String campo) throws AgentException, InterruptedException {
        TextBox textBox_object = new TextBox(campo);
        textBox_object.setIsRequired();
        if (agent.checkElementIsDisplayed(textBox_object.getCssSelector())) {
            agent.writeInElement(textBox_object.getCssSelector(), input);
        } else {
            System.out.println("No se encontro el campo");
        }
    }

    /**
     * Ingresa un input a un campo común
     *
     * @param input
     * @param campo
     * @throws AgentException
     * @throws InterruptedException
     * @authot Rodrigo Crespillo
     * @version 1.0 10/07/2017
     */
    @Override
    public void inputToTextBox(String input, String campo) throws AgentException, InterruptedException {
        TextBox textBox_object = new TextBox(campo);
        if (agent.checkElementIsDisplayed(textBox_object.getCssSelector())) {
            agent.writeInElement(textBox_object.getCssSelector(), input);
        } else {
            System.out.println("No se encontro el campo");
        }
    }

    /**
     * Ingresa valores a un control de tipo numerico
     *
     * @param input
     * @param campo
     * @throws AgentException
     * @authot Rodrigo Crespillo
     * @version 1.0 10/07/2017
     */
    @Override
    public void inputToNumericTextBox(String input, String campo) throws AgentException {
        TextBox textBox_object = new TextBox(campo);
        if (agent.checkElementIsDisplayed(textBox_object.getCssSelector())) {
            agent.writeInElement(textBox_object.getCssSelector(), input);
        } else {
            System.out.println("No se encontro el campo");
        }
    }

    /**
     * Ingresa texto a un input tipo textbox
     *
     * @param input
     * @param campo
     * @throws AgentException
     * @authot Rodrigo Crespillo
     * @version 1.0 10/07/2017
     */
    @Override
    public void inputToTextArea(String input, String campo) throws AgentException {
        TextBox textBox_object = new TextBox(campo, TextBox.Modes.multiple_line);
        if (agent.checkElementIsDisplayed(textBox_object.getCssSelector())) {
            agent.writeInElement(textBox_object.getCssSelector(), input);
        } else {
            System.out.println("No se encontro el campo");
        }
    }

    /**
     * Ingresa valores a input tipo password
     *
     * @param input
     * @param campo
     * @throws AgentException
     * @authot Rodrigo Crespillo
     * @version 1.0 10/07/2017
     */
    @Override
    public void inputToPasswordTextBox(String input, String campo) throws AgentException {
        TextBox textBox_object = new TextBox(campo, TextBox.Modes.password);
        if (agent.checkElementIsDisplayed(textBox_object.getCssSelector())) {
            agent.writeInElement(textBox_object.getCssSelector(), input);
        } else {
            System.out.println("No se encontro el campo");
        }
    }

    /**
     * Adjunta un archivo a un control de Attatchemet
     *
     * @param url
     * @param adjunto
     * @throws AgentException
     * @author Rodrigo Crespillo
     * @version 1.0 13/07/2017
     */
    @Override
    public void attachFile(String url, String adjunto) throws AgentException {
        Attachment attch_object = new Attachment(adjunto);
        if (agent.checkElementIsDisplayed(attch_object.getCssSelector())) {
            agent.click(attch_object.getCssSelector());
            String principalWindows = agent.switchToPopup();
            agent.checkElementIsDisplayed(attch_object.getAttachButton(), FlawedTimeUnit.seconds(2));
            agent.selectFile(attch_object.getInputButton(), url);
            agent.click(attch_object.getAddButton());
            agent.click(attch_object.getCloseButton());
            agent.switchToPopup(principalWindows);
        } else {
            System.out.println("No se encontro el campo");
        }

    }

    /**
     * Ingresa la fecha de un datepicker directamente sobre el campo
     *
     * @param date
     * @param hh
     * @param mm
     * @param dtpicker
     * @throws AgentException
     * @author Rodrigo Crespillo
     * @version 1.0 14/07/2017
     */
    @Override
    public void loadDateByTextBox(String date, String hh, String mm, String dtpicker) throws AgentException {
        DTPicker dtPicker_object = new DTPicker(dtpicker);
        if (agent.checkElementIsDisplayed(dtPicker_object.getButton())) {

            agent.writeInElement(dtPicker_object.getDate(), date);
            agent.writeInElement(dtPicker_object.getHh(), hh);
            agent.writeInElement(dtPicker_object.getMm(), mm);

            //agent.click(dtPicker_object.getButton());
            System.out.printf("lala");
        } else {
            System.out.println("No se encontro el campo");
        }
    }


    /**
     * Selecciona la fecha de un datepicker usando la ui, este puede ser usado en el caso de q el campo este bloqueado
     *
     * @param date
     * @param dtpicker
     * @throws AgentException
     * @throws ParseException
     * @author Rodrigo Crespillo
     * @version 1.0 14/07/2017
     */
    @Override
    public void loadDateByCalendarU(String date, String dtpicker) throws AgentException, ParseException {

        DTPicker dtPicker_object = new DTPicker(dtpicker);

        SimpleDateFormat dmy = new SimpleDateFormat("dd/MM/yyyy");
        Date aDate = dmy.parse(date);

        if (agent.checkElementIsDisplayed(dtPicker_object.getButton())) {
            agent.click(dtPicker_object.getButton());
            agent.selectDateFromUi(aDate);

        } else {
            System.out.println("No se encontro el campo");
        }
    }


    /**
     * Selecciona una opcion de un selector
     *
     * @param opcion
     * @param selector
     * @throws AgentException
     * @author Rodrigo Crespillo
     * @version 1.0 12/07/2017
     */
    @Override
    public void selectOption(String opcion, String selector) throws AgentException {
        SelectControl selectControl = new SelectControl(selector);
        if (agent.checkElementIsDisplayed(selectControl.getCssSelector())) {
            agent.selectSelectorOption(selectControl.getCssSelector(), opcion);
        } else {
            System.out.println("No se encontro el campo");
        }
    }

    /**
     * Selecciona las opciones dadas(en forma de tabla) de un selector multiple
     *
     * @param options
     * @param selector
     * @throws AgentException
     * @author Rodrigo Crespillo
     * @version 1.0 14/07/2017
     */
    @Override
    public void selectMultipleOptions(ArrayList<String> options, String selector) throws AgentException {
        SelectorMultiple selectorMultiple = new SelectorMultiple(selector, "");
        for (String option : options) {
            agent.selectOptionsCrtlClick(option, selectorMultiple.getLeftOptions());
        }
        agent.click(selectorMultiple.getToRightButton());
    }

    /**
     * Selecciona todas la opciones de un selector multiple
     *
     * @param selector
     * @throws AgentException
     * @author Rodrigo Crespillo
     * @version 1.0 17/07/2017
     */
    @Override
    public void selectMultipleOptionsAll(String selector) throws AgentException {
        SelectorMultiple selectorMultiple = new SelectorMultiple(selector, "");
        agent.click(selectorMultiple.getToRightAllButton());
    }

    /**
     * Deselecciona las opciones dadas(en forma de tabla) de un selector multiple
     *
     * @param options
     * @param selector
     * @throws AgentException
     * @author Rodrigo Crespillo
     * @version 1.0 17/07/2017
     */
    @Override
    public void deselectMultipleOptions(ArrayList<String> options, String selector) throws AgentException {
        SelectorMultiple selectorMultiple = new SelectorMultiple(selector, "");
        for (String option : options) {
            agent.selectOptionsCrtlClick(option, selectorMultiple.getRightOptions());
        }
        agent.click(selectorMultiple.getToLeftButton());
    }

    /**
     * Deselecciona todas la opciones de un selector multiple
     *
     * @param selector
     * @throws AgentException
     * @author Rodrigo Crespillo
     * @version 1.0 17/07/2017
     */
    @Override
    public void deselectMultipleOptionsAll(String selector) throws AgentException {
        SelectorMultiple selectorMultiple = new SelectorMultiple(selector, "");
        agent.click(selectorMultiple.getToLeftAllButton());

    }

    @Override
    public void searchOptionAutoComplete(String search, String autoComplete) throws AgentException {
        AutoComplete autoComplete_control = new AutoComplete(autoComplete);
        if (agent.checkElementIsDisplayed(autoComplete_control.getCssSelector())) {
            agent.searchOption(search, autoComplete);
        } else {
            System.out.println("No se encontro el campo");
        }

    }

    @Override
    public void searchLookUpBoxAccount(String search, String control) throws AgentException {
        LookupBoxAccounts lookupBoxAccounts_control = new LookupBoxAccounts(control);
        if (agent.checkElementIsDisplayed(lookupBoxAccounts_control.getCssSelector())){
            agent.click(lookupBoxAccounts_control.getSearchButton());
            String parentWindow = agent.switchToPopup();
            agent.writeInElement(lookupBoxAccounts_control.getSearchBox(), search);
            agent.click(lookupBoxAccounts_control.getSearchButtonOptions());
            agent.selectOptionsDoubleClick(search, lookupBoxAccounts_control.getOptions());
            agent.click(lookupBoxAccounts_control.getAceptButton());
            agent.switchToPopup(parentWindow);
        }
    }

    @Override
    public void searchLookUpBoxAccountDobleClick(String search, String control) throws AgentException {
        LookupBoxAccounts lookupBoxAccounts_control = new LookupBoxAccounts(control);
        if (agent.checkElementIsDisplayed(lookupBoxAccounts_control.getCssSelector())){
            agent.click(lookupBoxAccounts_control.getSearchButton());
            String parentWindow = agent.switchToPopup();
            agent.writeInElement(lookupBoxAccounts_control.getSearchBox(), search);
            agent.click(lookupBoxAccounts_control.getSearchButtonOptions());
            agent.selectOptionsDoubleClick(search, lookupBoxAccounts_control.getOptions());
            agent.switchToPopup(parentWindow);
        }
    }


}
