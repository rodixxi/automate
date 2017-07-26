package com.harriague.automate.web.pages;

import com.harriague.automate.core.exceptions.AgentException;

public interface CheckFormValues {
    void checkValueInTextBox(String textBox, String value) throws AgentException;

    void checkValueInTextArea(String textArea, String value) throws AgentException;

    void checkValueInRequiredTextBox(String textBox, String value) throws AgentException;

    void checkValueInNumericTextBox(String textBox, String value) throws AgentException;

    void checkValueInPasswordTextBox(String textBox, String value) throws AgentException;

    void checkAttatchment(String attatchField, String attachNameExpected) throws AgentException;

    void checkCheckBoxChecked(String checkboxId, boolean isChecked);
}
