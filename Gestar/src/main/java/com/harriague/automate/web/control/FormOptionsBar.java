package com.harriague.automate.web.control;

import org.openqa.selenium.By;

public class FormOptionsBar {

    private By idSelector = By.cssSelector("#menu_bar_orange");
    private By print = new Button("imprimir").getCssSelector();
    private By save = new Button("save").getCssSelector();
    private By saveExit = new Button("saveexit").getCssSelector();
    private By cancel = new Button("cancel").getCssSelector();

    public FormOptionsBar() {
    }

    public By getPrint() {
        return print;
    }

    public By getSave() {
        return save;
    }

    public By getSaveExit() {
        return saveExit;
    }

    public By getCancel() {
        return cancel;
    }

    public By getIdSelector() {
        return idSelector;
    }
}
