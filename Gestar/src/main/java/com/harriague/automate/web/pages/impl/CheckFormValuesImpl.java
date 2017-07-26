package com.harriague.automate.web.pages.impl;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.BasePage;
import com.harriague.automate.web.control.*;
import com.harriague.automate.web.pages.CheckFormValues;

public class CheckFormValuesImpl extends BasePage implements CheckFormValues {

    public CheckFormValuesImpl(Agent agent) {
        super(agent);
    }


    @Override
    public void checkValueInTextBox(String textBox, String value) throws AgentException {
        TextBox textBox_control = new TextBox(textBox);
        String textBox_value= agent.getValue(textBox_control.getCssSelector(), "value");
        agent.aceptStringIfEqualTo(textBox_value,value);
    }

    @Override
    public void checkValueInTextArea(String textArea, String value) throws AgentException {
        TextBox textArea_control = new TextBox(textArea, TextBox.Modes.multiple_line);
        String textBox_value= agent.getValue(textArea_control.getCssSelector(), "value");
        agent.aceptStringIfEqualTo(textBox_value,value);

    }

    @Override
    public void checkValueInRequiredTextBox(String textBox, String value) throws AgentException {
        TextBox RequiredTextBox_control = new TextBox(textBox);
        RequiredTextBox_control.setIsRequired();
        String textBox_value= agent.getValue(RequiredTextBox_control.getCssSelector(), "value");
        agent.aceptStringIfEqualTo(textBox_value,value);

    }

    @Override
    public void checkValueInNumericTextBox(String textBox, String value) throws AgentException {
        TextBox NumericTextBox_control = new TextBox(textBox);
        NumericTextBox_control.setIsNumeric();
        String textBox_value= agent.getValue(NumericTextBox_control.getCssSelector(), "value");
        agent.aceptStringIfEqualTo(textBox_value,value);

    }

    @Override
    public void checkValueInPasswordTextBox(String textBox, String value) throws AgentException {
        TextBox passwordTextBox_control = new TextBox(textBox, TextBox.Modes.password);
        String textBox_value= agent.getValue(passwordTextBox_control.getCssSelector(), "value");
        agent.aceptStringIfEqualTo(textBox_value,value);

    }

    @Override
    public void checkAttatchment(String attatchField, String attachNameExpected) throws AgentException {
        Attachment attachField_control = new Attachment(attatchField);
        String attatchName_text = agent.getTextValue(attachField_control.getAttatchName());
        agent.aceptStringIfEqualTo(attatchName_text , attachNameExpected);
    }

    @Override
    public void checkCheckBoxChecked(String checkboxId, boolean isChecked) {
        Checkbox checkBox_control = new Checkbox(checkboxId);
        boolean isChecked_control = agent.getIsChecked(checkBox_control.getCssSelector());
        agent.aceptIfBoolean(isChecked_control, isChecked);
    }

}
