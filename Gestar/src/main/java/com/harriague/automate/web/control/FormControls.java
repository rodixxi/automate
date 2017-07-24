package com.harriague.automate.web.control;

import org.openqa.selenium.By;

public class FormControls {
    private String formCotrolDivPath = "#menu_bar_orange";
    private String formPrintButtonPath = formCotrolDivPath + " #imprimir";
    private String formSaveButtonPath = formCotrolDivPath + " #save";
    private String formSaveExitButtonPath = formCotrolDivPath + " #saveexit";
    private String formCancelButtonPath = formPrintButtonPath + " #cancel";

    private By formPritnButton = By.cssSelector(formPrintButtonPath);
    private By formSaverButton = By.cssSelector(formSaveButtonPath);
    private By formSaveExiyButton = By.cssSelector(formSaveExitButtonPath);
    private By formCancelButton = By.cssSelector(formCancelButtonPath);

    public FormControls(){}

    public By getFormPritnButton() {
        return formPritnButton;
    }

    public By getFormSaverButton() {
        return formSaverButton;
    }

    public By getFormSaveExiyButton() {
        return formSaveExiyButton;
    }

    public By getFormCancelButton() {
        return formCancelButton;
    }
}
