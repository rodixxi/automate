package com.harriague.automate.web.pages;

import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.page.Page;

import java.io.IOException;
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

    void selectOption(String selectName, String optionToSelect) throws AgentException;

    void selectMultipleOptions(String multipleSelectorName, ArrayList<String> optionsToSelect) throws AgentException;

    void loadDateByCalendarUI(String date, String dtpicker) throws AgentException, ParseException;

    void selectMultipleOptionsAll(String multipleSelectorName) throws AgentException;

    void deselectMultipleOptions(String multipleSelectorName, ArrayList<String> optionsToUnselect) throws AgentException;

    void deselectMultipleOptionsAll(String multipleSelectorName) throws AgentException;

    void searchOptionAutoComplete(String autoCompleteName, String optionToSearch) throws AgentException;

    void searchInLookUpBoxAccount(String lookUpBoxAccountName, String optionToSearch) throws AgentException;

    void searchInLookUpBoxAccountDobleClick(String lookUpBoxAccountName, String optionToSearch) throws AgentException;

    void searchOptionAutoCompleteMultiple(String autoCompleteName, ArrayList<String> optionsToSearch) throws AgentException;

    void attachFileToAttatchmentControlNew(String attachmentControlName, String fileURL) throws AgentException, IOException;

    void loadDateByCalendarUIBoostrap(String date, String dtpicker, String hh, String mm) throws ParseException, AgentException;

    void loadDateByCalendarUIBoostrapOnlyDate(String date, String dtpicker) throws AgentException, ParseException;

    void loadDateByCalendarUINewOnlyTime(String dtpicker, String hh, String mm) throws AgentException;
}
