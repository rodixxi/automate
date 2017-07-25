package com.harriague.automate.web.pages;

import com.harriague.automate.core.exceptions.AgentException;

public interface CheckFormValues {
    void checkValueInTextBox(String textBox, String value) throws AgentException;
}
