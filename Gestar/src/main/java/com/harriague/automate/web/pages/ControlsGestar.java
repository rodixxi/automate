package com.harriague.automate.web.pages;

import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.Page;

import java.text.ParseException;
import java.util.ArrayList;

public interface ControlsGestar extends Page{

    void selectTabPanel(String tabName) throws AgentException, InterruptedException;

    void inputToRequiredTextBox(String textBoxName, String input) throws AgentException, InterruptedException;

    void inputToTextBox(String textBoxName, String input) throws AgentException, InterruptedException;

    void inputToNumericTextBox(String textBoxName, String input) throws AgentException;

    void inputToTextArea(String textAreaName, String input) throws AgentException;

    void inputToPasswordTextBox(String textBoxName, String input) throws AgentException;

    void attachFileToAttatchmentControl(String attachmentControlName, String fileURL) throws AgentException;

    void loadDateByTextBox(String date, String hh, String mm, String dtpicker) throws AgentException;

    void selectOption(String opcion, String selector) throws AgentException;

    void selectMultipleOptions(ArrayList<String> options, String selector) throws AgentException;

    void loadDateByCalendarUI(String date, String dtpicker) throws AgentException, ParseException;

    void selectMultipleOptionsAll(String selector) throws AgentException;

    void deselectMultipleOptions(ArrayList<String> options, String selector) throws AgentException;

    void deselectMultipleOptionsAll(String selector) throws AgentException;

    void searchOptionAutoComplete(String search, String autoComplete) throws AgentException;

    void searchLookUpBoxAccount(String search, String control) throws AgentException;

    void searchLookUpBoxAccountDobleClick(String search, String control) throws AgentException;

    void searchOptionAutoCompleteMultiple(String search, ArrayList<String> options) throws AgentException;
}
