package com.harriague.automate.web.control;

import org.openqa.selenium.By;

public class AutoComplete extends Control {

    private String searhBoxPath = getNombre() + "_folderSearchAutocomplete";
    private String optionsBoxPath = ".ui-autocomplete a";

    private By searhBox = By.cssSelector(searhBoxPath);
    private By optionsBox = By.cssSelector(optionsBoxPath);

    public String getSearhBoxPath() {
        return searhBoxPath;
    }

    public String getOptionsBoxPath() {
        return optionsBoxPath;
    }

    public By getSearhBox() {
        return searhBox;
    }

    public By getOptionsBox() {
        return optionsBox;
    }

    public AutoComplete(String nombre) {
        super(nombre);
        setCssSelector();
    }

    public AutoComplete(String nombre, String etiqueta) {
        super(nombre, etiqueta);
        setCssSelector();
    }

    @Override
    public void setCssSelector() {
        By cssSelector = By.cssSelector("#" + getNombre() + "_container");
        super.setCssSelector(cssSelector);
    }
}
