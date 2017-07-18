package com.harriague.automate.web.control;

import org.openqa.selenium.By;

public class LookupBoxAccounts extends Control {

    private String controlTablePath = "#tbCtrl_" + getName();
    private String searchButtonPath = controlTablePath + " img";
    private String searchBoxPath = "#txtSearch";
    private String searchButtonOptionsPath = "#cmdSearch";
    private String optionsPath = "#lstSearch option";
    private String cancelButtonPath = "#cmdCancel";
    private String aceptButtonPath = "#cmdOk";

    private By controlTable = By.cssSelector(controlTablePath);
    private By searchButton = By.cssSelector(searchButtonPath);
    private By searchBox = By.cssSelector(searchBoxPath);
    private By searchButtonOptions = By.cssSelector(searchButtonOptionsPath);
    private By options = By.cssSelector(optionsPath);
    private By cancelButton = By.cssSelector(cancelButtonPath);
    private By aceptButton = By.cssSelector(aceptButtonPath);


    public LookupBoxAccounts(String name, String title) {
        super(name, title);
        setCssSelector();
    }

    public LookupBoxAccounts(String name) {
        super(name);
        setCssSelector();
    }

    public By getControlTable() {
        return controlTable;
    }

    public By getSearchButton() {
        return searchButton;
    }

    public By getSearchBox() {
        return searchBox;
    }

    public By getSearchButtonOptions() {
        return searchButtonOptions;
    }

    public By getOptions() {
        return options;
    }

    public By getCancelButton() {
        return cancelButton;
    }

    public By getAceptButton() {
        return aceptButton;
    }

    @Override
    public void setCssSelector() {
        super.setCssSelector(this.controlTable);
    }
}
