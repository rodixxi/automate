package com.harriague.automate.web.pages.impl;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.BasePage;
import com.harriague.automate.core.structures.FlawedTimeUnit;
import com.harriague.automate.web.control.*;
import com.harriague.automate.web.pages.ControlsGestar;

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
     * @param textBoxName
     * @param input
     * @throws AgentException
     * @throws InterruptedException
     * @authot Rodrigo Crespillo
     * @version 1.0 10/07/2017
     */
    @Override
    public void inputToRequiredTextBox(String textBoxName, String input) throws AgentException, InterruptedException {
        TextBox textBox_object = new TextBox(textBoxName);
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
     * @throws AgentException
     * @throws InterruptedException
     * @authot Rodrigo Crespillo
     * @version 1.0 10/07/2017
     */
    @Override
    public void inputToTextBox(String textBoxName, String input) throws AgentException, InterruptedException {
        TextBox textBox_object = new TextBox(textBoxName);
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
     * @throws AgentException
     * @authot Rodrigo Crespillo
     * @version 1.0 10/07/2017
     */
    @Override
    public void inputToNumericTextBox(String textBoxName, String input) throws AgentException {
        TextBox textBox_object = new TextBox(textBoxName);
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
     * @throws AgentException
     * @authot Rodrigo Crespillo
     * @version 1.0 10/07/2017
     */
    @Override
    public void inputToTextArea(String textAreaName, String input) throws AgentException {
        TextBox textBox_object = new TextBox(textAreaName, TextBox.Modes.multiple_line);
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
     * @throws AgentException
     * @authot Rodrigo Crespillo
     * @version 1.0 10/07/2017
     */
    @Override
    public void inputToPasswordTextBox(String textBoxName, String input) throws AgentException {
        TextBox textBox_object = new TextBox(textBoxName, TextBox.Modes.password);
        if (agent.checkElementIsDisplayed(textBox_object.getCssSelector())) {
            agent.writeInElement(textBox_object.getCssSelector(), input);
        } else {
            System.out.println("No se encontro el campo");
        }
    }

    /**
     * Adjunta un archivo a un control de Attatchemet
     *
     * @param attachmentControlName
     * @param fileURL
     * @throws AgentException
     * @author Rodrigo Crespillo
     * @version 1.0 13/07/2017
     */
    @Override
    public void attachFileToAttatchmentControl(String attachmentControlName, String fileURL) throws AgentException {
        Attachment attatch_object = new Attachment(attachmentControlName);
        if (agent.checkElementIsDisplayed(attatch_object.getCssSelector())) {
            String principalWindows = openAttatchmentControlPopup(attatch_object);
            attatchFileInPopup(fileURL, attatch_object);
            closeAttatchmentPopup(principalWindows, attatch_object);
        } else {
            System.out.println("No se encontro el campo");
        }

    }

    private void closeAttatchmentPopup(String principalWindows, Attachment attatch_object) throws AgentException {
        agent.click(attatch_object.getCloseButton());
        agent.switchToPopup(principalWindows);
    }

    private void attatchFileInPopup(String fileURL, Attachment attch_object) throws AgentException {
        agent.selectFile(attch_object.getInputButton(), fileURL);
        agent.click(attch_object.getAddButton());
    }

    private String openAttatchmentControlPopup(Attachment attch_object) throws AgentException {
        agent.click(attch_object.getCssSelector());
        String principalWindows = agent.switchToPopup();
        agent.checkElementIsDisplayed(attch_object.getAttachButton(), FlawedTimeUnit.seconds(2));
        return principalWindows;
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
    public void loadDateByCalendarUI(String date, String dtpicker) throws AgentException, ParseException {

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
     * @throws AgentException
     * @author Rodrigo Crespillo
     * @version 1.0 12/07/2017
     */
    @Override
    public void selectOption(String selectName, String optionToSelect) throws AgentException {
        SelectControl selectControl = new SelectControl(selectName);
        if (agent.checkElementIsDisplayed(selectControl.getCssSelector())) {
            agent.selectSelectorOption(selectControl.getCssSelector(), optionToSelect);
        } else {
            System.out.println("No se encontro el campo");
        }
    }

    /**
     * Selecciona las opciones dadas(en forma de tabla) de un selector multiple
     *
     * @throws AgentException
     * @author Rodrigo Crespillo
     * @version 1.0 14/07/2017
     */
    @Override
    public void selectMultipleOptions(String multipleSelectorName, ArrayList<String> optionsToSelect) throws AgentException {
        SelectorMultiple selectorMultipleObject = new SelectorMultiple(multipleSelectorName);
        for (String optionToSelect : optionsToSelect) {
            agent.markOptionSelectorMultiple(optionToSelect, selectorMultipleObject.getLeftOptions());
        }
        agent.click(selectorMultipleObject.getToRightButton());
    }

    /**
     * Selecciona todas la opciones de un selector multiple
     *
     * @param multipleSelectorName
     * @throws AgentException
     * @author Rodrigo Crespillo
     * @version 1.0 17/07/2017
     */
    @Override
    public void selectMultipleOptionsAll(String multipleSelectorName) throws AgentException {
        SelectorMultiple selectorMultiple = new SelectorMultiple(multipleSelectorName, "");
        agent.click(selectorMultiple.getToRightAllButton());
    }

    /**
     * Deselecciona las opciones dadas(en forma de tabla) de un selector multiple
     *
     * @throws AgentException
     * @author Rodrigo Crespillo
     * @version 1.0 17/07/2017
     */
    @Override
    public void deselectMultipleOptions(String multipleSelectorName, ArrayList<String> optionsToUnselect) throws AgentException {
        SelectorMultiple selectorMultipleObject = new SelectorMultiple(multipleSelectorName);
        for (String optionToUnselect : optionsToUnselect) {
            agent.markOptionSelectorMultiple(optionToUnselect, selectorMultipleObject.getRightOptions());
        }
        agent.click(selectorMultipleObject.getToLeftButton());
    }

    /**
     * Deselecciona todas la opciones de un selector multiple
     *
     * @param multipleSelectorName
     * @throws AgentException
     * @author Rodrigo Crespillo
     * @version 1.0 17/07/2017
     */
    @Override
    public void deselectMultipleOptionsAll(String multipleSelectorName) throws AgentException {
        SelectorMultiple selectorMultiple = new SelectorMultiple(multipleSelectorName, "");
        agent.click(selectorMultiple.getToLeftAllButton());

    }

    @Override
    public void searchOptionAutoComplete(String autoCompleteName, String optionToSearch) throws AgentException {
        AutoComplete autoCompletObject = new AutoComplete(autoCompleteName);
        if (agent.checkElementIsDisplayed(autoCompletObject.getCssSelector())) {
            agent.searchOption(optionToSearch, autoCompleteName);
        } else {
            System.out.println("No se encontro el campo");
        }
    }

    @Override
    public void searchInLookUpBoxAccount(String lookUpBoxAccountName, String optionToSearch) throws AgentException {
        LookUpBoxAccounts lookUpBoxAccountObject = new LookUpBoxAccounts(lookUpBoxAccountName);
        if (agent.checkElementIsDisplayed(lookUpBoxAccountObject.getCssSelector())){
            agent.click(lookUpBoxAccountObject.getSearchButton());
            String parentWindow = agent.switchToPopup();
            agent.writeInElement(lookUpBoxAccountObject.getSearchBox(), optionToSearch);
            agent.click(lookUpBoxAccountObject.getSearchButtonOptions());
            agent.selectOptionsDoubleClick(optionToSearch, lookUpBoxAccountObject.getOptions());
            agent.click(lookUpBoxAccountObject.getAceptButton());
            agent.switchToPopup(parentWindow);
        }
    }

    @Override
    public void searchInLookUpBoxAccountDobleClick(String lookUpBoxAccountName, String optionToSearch) throws AgentException {
        LookUpBoxAccounts lookUpBoxAccountsObject = new LookUpBoxAccounts(lookUpBoxAccountName);
        if (agent.checkElementIsDisplayed(lookUpBoxAccountsObject.getCssSelector())){
            agent.click(lookUpBoxAccountsObject.getSearchButton());
            String parentWindow = agent.switchToPopup();
            agent.writeInElement(lookUpBoxAccountsObject.getSearchBox(), optionToSearch);
            agent.click(lookUpBoxAccountsObject.getSearchButtonOptions());
            agent.selectOptionsDoubleClick(optionToSearch, lookUpBoxAccountsObject.getOptions());
            agent.switchToPopup(parentWindow);
        }
    }

    @Override
    public void searchOptionAutoCompleteMultiple(String autoComplete, ArrayList<String> optionsToSearch) throws AgentException {
        AutoComplete autoCompleteObject = new AutoComplete(autoComplete);
        if (agent.checkElementIsDisplayed(autoCompleteObject.getCssSelector())) {
            for (String option : optionsToSearch) {
                agent.searchOption(option, autoComplete);
            }
        } else {
            System.out.println("No se encontro el campo");
        }
    }


}
