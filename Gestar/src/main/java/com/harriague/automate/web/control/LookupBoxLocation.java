package com.harriague.automate.web.control;

import org.openqa.selenium.By;

public class LookupBoxLocation extends Control {

    private String controlTablePath = "#tbCtrl_" + getName();
    private String searchButtonPath = controlTablePath + " #ubications_search";
    private String idTextBoxPath = controlTablePath + " #ubications_id";
    private String nameTextBoxPath = controlTablePath + " #ubications_name";
    private String pathTextBoxPath = controlTablePath + " #ubications_path";
    private String elementsPath = "#id0 > div > div > a:nth-child(2)";

    private By controlTable = By.cssSelector(controlTablePath);
    private By searchButton = By.cssSelector(searchButtonPath);
    private By idTextBox = By.cssSelector(idTextBoxPath);
    private By nameTextBox = By.cssSelector(nameTextBoxPath);
    private By pathTextBox = By.cssSelector(pathTextBoxPath);
    private By elements = By.cssSelector(elementsPath);


    public LookupBoxLocation(String name, String title) {
        super(name, title);
    }

    public LookupBoxLocation(String name) {
        super(name);
    }

    public By getControlTable() {
        return controlTable;
    }

    public By getSearchButton() {
        return searchButton;
    }

    public By getIdTextBox() {
        return idTextBox;
    }

    public By getNameTextBox() {
        return nameTextBox;
    }

    public By getPathTextBox() {
        return pathTextBox;
    }

    public By getElements() {
        return elements;
    }

    public By getElement(String ubicacion) {
        return By.cssSelector(elementsPath + "[title='" + ubicacion + "']");
    }
}
