package com.harriague.automate.web.pages;

import com.harriague.automate.core.exceptions.AgentException;

import java.util.ArrayList;

public interface CheckFormValues {
    void checkValueInTextBox(String textBox, String value) throws AgentException;

    void checkValueInTextArea(String textArea, String value) throws AgentException;

    void checkValueInRequiredTextBox(String textBox, String value) throws AgentException;

    void checkValueInNumericTextBox(String textBox, String value) throws AgentException;

    void checkValueInPasswordTextBox(String textBox, String value) throws AgentException;

    void checkAttatchment(String attatchField, String attachNameExpected) throws AgentException;

    void checkCheckBoxChecked(String checkboxId, boolean isChecked);

    void checkDtpickerDateAndHour(String dtpicker, String date, String hh, String mm) throws AgentException;

    void checkDtpickerDate(String dtpicker, String date) throws AgentException;

    void checkLookUpBoxAccount(String lookUpBoxAccount, String account) throws AgentException;

    void checkSelectedOption(String selectControl, String optionExpected);

    void checkMultipleOptionsRightList(ArrayList<String> options, String multipleSelector);

    void checkMultipleOptionsLeftList(ArrayList<String> options, String multipleSelector);

    void checkAutoComplete(String autoCompleteControl, String valueExpected) throws AgentException;

    void checkAutoCompleteMultiple(String autoCompleteControl, ArrayList<String> optionsExpected);
}
