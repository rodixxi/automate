package com.harriague.automate.web.control;

import org.openqa.selenium.By;

public class AutoComplete extends Control {

    private String searhBoxPath = getName() + "_folderSearchAutocomplete";
    private String optionsBoxPath = ".ui-autocomplete a";
    private String selectOptionPath = "#" + getName() + "_selectedElements b.autocomplete-item-text";

    private By searhBox = By.cssSelector(searhBoxPath);
    private By optionsBox = By.cssSelector(optionsBoxPath);
    private By selectOption = By.cssSelector(selectOptionPath);

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

    public By getSelectOption() {
        return selectOption;
    }

    public AutoComplete(String name) {
        super(name);
        setCssSelector();
    }

    public AutoComplete(String name, String title) {
        super(name, title);
        setCssSelector();
    }

    @Override
    public void setCssSelector() {
        By cssSelector = By.cssSelector("#" + getName() + "_container");
        super.setCssSelector(cssSelector);
    }
}
