package com.harriague.automate.web.pages.impl;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.BasePage;
import com.harriague.automate.web.control.*;
import com.harriague.automate.web.pages.CheckFormValues;

import java.util.ArrayList;

public class CheckFormValuesImpl extends BasePage implements CheckFormValues {

    public CheckFormValuesImpl(Agent agent) {
        super(agent);
    }


    @Override
    public void checkValueInTextBox(String textBox, String value) throws AgentException {
        TextBox textBox_control = new TextBox(textBox);
        String textBox_value= agent.getValue(textBox_control.getCssSelector(), "value");
        agent.aceptStringIfEqualTo(value, textBox_value);
    }

    @Override
    public void checkValueInTextArea(String textArea, String value) throws AgentException {
        TextBox textArea_control = new TextBox(textArea, TextBox.Modes.multiple_line);
        String textBox_value= agent.getValue(textArea_control.getCssSelector(), "value");
        agent.aceptStringIfEqualTo(value, textBox_value);

    }

    @Override
    public void checkValueInRequiredTextBox(String textBox, String value) throws AgentException {
        TextBox RequiredTextBox_control = new TextBox(textBox);
        RequiredTextBox_control.setIsRequired();
        String textBox_value= agent.getValue(RequiredTextBox_control.getCssSelector(), "value");
        agent.aceptStringIfEqualTo(value, textBox_value);

    }

    @Override
    public void checkValueInNumericTextBox(String textBox, String value) throws AgentException {
        TextBox NumericTextBox_control = new TextBox(textBox);
        NumericTextBox_control.setIsNumeric();
        String textBox_value= agent.getValue(NumericTextBox_control.getCssSelector(), "value");
        agent.aceptStringIfEqualTo(value, textBox_value);

    }

    @Override
    public void checkValueInPasswordTextBox(String textBox, String value) throws AgentException {
        TextBox passwordTextBox_control = new TextBox(textBox, TextBox.Modes.password);
        String textBox_value= agent.getValue(passwordTextBox_control.getCssSelector(), "value");
        agent.aceptStringIfEqualTo(value, textBox_value);

    }

    @Override
    public void checkAttatchment(String attatchField, String attachNameExpected) throws AgentException {
        Attachment attachField_control = new Attachment(attatchField);
        String attatchName_text = agent.getTextValue(attachField_control.getAttatchName());
        agent.aceptStringIfEqualTo(attachNameExpected, attatchName_text);
    }

    @Override
    public void checkCheckBoxChecked(String checkboxId, boolean isChecked) {
        Checkbox checkBox_control = new Checkbox(checkboxId);
        boolean isChecked_control = agent.getIsChecked(checkBox_control.getCssSelector());
        agent.aceptIfBoolean(isChecked_control, isChecked);
    }

    @Override
    public void checkDtpickerDateAndHour(String dtpicker, String date, String hh, String mm) throws AgentException {
        DTPicker dtPicker_control = new DTPicker(dtpicker);
        String dtPicker_control_fulldate = agent.getValue(dtPicker_control.getDate(), "value");
        dtPicker_control_fulldate += agent.getValue(dtPicker_control.getHh(), "value");
        dtPicker_control_fulldate += agent.getValue(dtPicker_control.getMm(), "value");
        agent.aceptStringIfEqualTo(dtPicker_control_fulldate, date+hh+mm);
    }

    @Override
    public void checkDtpickerDate(String dtpicker, String date) throws AgentException {
        DTPicker dtPicker_control = new DTPicker(dtpicker);
        String dtPicker_control_date = agent.getValue(dtPicker_control.getDate(), "value");
        agent.aceptStringIfEqualTo(dtPicker_control_date, date);

    }

    @Override
    public void checkLookUpBoxAccount(String lookUpBoxAccount, String accountExpected) throws AgentException {
        LookUpBoxAccounts lookUpBoxAccounts_control = new LookUpBoxAccounts((lookUpBoxAccount));
        String accontSelected = agent.getTextValue(lookUpBoxAccounts_control.getAccountSelected());
        agent.aceptStringIfEqualTo(accontSelected, accountExpected);
    }

    @Override
    public void checkSelectedOption(String selectControl, String optionExpected) {
        SelectControl select_control = new SelectControl(selectControl);
        agent.aceptSelectOption(select_control.getCssSelector(), optionExpected);

    }

    @Override
    public void checkMultipleOptionsRightList(ArrayList<String> options, String multipleSelector) {
        SelectorMultiple selectorMultiple_control = new SelectorMultiple(multipleSelector);
        agent.aceptSelectOptions(selectorMultiple_control.getRightList(), options);
    }

    @Override
    public void checkMultipleOptionsLeftList(ArrayList<String> options, String multipleSelector) {
        SelectorMultiple selectorMultiple_control = new SelectorMultiple(multipleSelector);
        agent.aceptSelectOptions(selectorMultiple_control.getLeftList(), options);
    }

    @Override
    public void checkAutoComplete(String autoCompleteControl, String valueExpected) throws AgentException {
        AutoComplete autoComplete_control = new AutoComplete(autoCompleteControl);
        String selected_text = agent.getTextValue(autoComplete_control.getSelectOption());
        agent.aceptStringIfEqualTo(selected_text, valueExpected);
    }

    @Override
    public void checkAutoCompleteMultiple(String autoCompleteControl, ArrayList<String> optionsExpected) {
        AutoComplete autoComplete_control = new AutoComplete(autoCompleteControl);
        agent.aceptSearchMultiple(autoComplete_control.getSelectOption(), optionsExpected);

    }

}
