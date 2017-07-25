package com.harriague.automate.web.pages.impl;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.BasePage;
import com.harriague.automate.web.control.TextBox;
import com.harriague.automate.web.pages.CheckFormValues;

public class CheckFormValuesImpl extends BasePage implements CheckFormValues {

    public CheckFormValuesImpl(Agent agent) {
        super(agent);
    }


    @Override
    public void checkValueInTextBox(String textBox, String value) throws AgentException {
        TextBox textBox_control = new TextBox(textBox);
        String textBox_value= agent.getTextValue(textBox_control.getCssSelector());
        agent.acceptStringIfEqualTo(textBox_value,value);
    }
}
