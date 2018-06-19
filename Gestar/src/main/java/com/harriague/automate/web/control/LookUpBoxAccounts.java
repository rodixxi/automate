package com.harriague.automate.web.control;

import org.openqa.selenium.By;

public class LookUpBoxAccounts extends Control {

    private String controlTablePath = "#tbCtrl_" + getName();
    private String searchButtonPath = controlTablePath + " img";
    private String searchBoxPath = "#txtSearch";
    private String searchButtonOptionsPath = "#cmdSearch";
    private String optionsPath = "#lstSearch option";
    private String cancelButtonPath = "#cmdCancel";
    private String aceptButtonPath = "#cmdOk";
    private String accountSelectedPath = "#" + getName() + "_div span";

    private By controlTable = By.cssSelector(controlTablePath);
    private By searchButton = By.cssSelector(searchButtonPath);
    private By searchBox = By.cssSelector(searchBoxPath);
    private By searchButtonOptions = By.cssSelector(searchButtonOptionsPath);
    private By options = By.cssSelector(optionsPath);
    private By cancelButton = By.cssSelector(cancelButtonPath);
    private By aceptButton = By.cssSelector(aceptButtonPath);
    private By accountSelected = By.cssSelector(accountSelectedPath);


    public LookUpBoxAccounts(String name, String title) {
        super(name, title);
        setCssSelector();
    }

    public LookUpBoxAccounts(String name) {
        super(name);
        setCssSelector();
    }

    public String getControlTablePath() {
        return controlTablePath;
    }

    public void setControlTablePath(String controlTablePath) {
        this.controlTablePath = controlTablePath;
    }

    public String getSearchButtonPath() {
        return searchButtonPath;
    }

    public void setSearchButtonPath(String searchButtonPath) {
        this.searchButtonPath = searchButtonPath;
        searchButton = By.cssSelector(searchButtonPath);
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

    public By getAccountSelected() {
        return accountSelected;
    }

    @Override
    public void setCssSelector() {
        super.setCssSelector(this.controlTable);
    }
}
