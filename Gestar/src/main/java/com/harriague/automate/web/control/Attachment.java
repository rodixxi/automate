package com.harriague.automate.web.control;

import org.openqa.selenium.By;

public class Attachment extends Control {

    private String inputButtomPath = "input[name='file']";
    private String addButtomPath = "input[value='Agregar']";
    private String deleteButtomPath = "input[value='Eliminar']";
    private String closeButtomPath = "input[value='Cerrar']";
    private String attachButtonPath = "#tbCtrl_" + getName() + " img";
    private String attatchNamePath = "#tbCtrl_ " + getName() + " a";

    private By inputButton = By.cssSelector(inputButtomPath);
    private By addButton = By.cssSelector(addButtomPath);
    private By deleteButton = By.cssSelector(deleteButtomPath);
    private By closeButton = By.cssSelector(closeButtomPath);
    private By attachButton = By.cssSelector(attachButtonPath);
    private By attatchName = By.cssSelector(attatchNamePath);


    public Attachment(String name, String title) {
        super(name, title);
    }


    public Attachment(String name) {
        super(name);
        setCssSelector();
    }

    public By getInputButton() {
        return inputButton;
    }

    public By getAddButton() {
        return addButton;
    }

    public By getDeleteButton() {
        return deleteButton;
    }

    public By getCloseButton() {
        return closeButton;
    }

    public By getAttachButton() {
        return attachButton;
    }

    public By getAttatchName() {
        return attatchName;
    }

    @Override
    public void setCssSelector() {
        super.setCssSelectorById("#tbCtrl_" + getName() + " img");
    }
}
