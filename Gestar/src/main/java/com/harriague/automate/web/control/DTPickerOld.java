package com.harriague.automate.web.control;

import org.openqa.selenium.By;

public class DTPickerOld extends Control{

    String buttonPath = "#" + getName() + " + img.ui-datepicker-trigger";
    String datePath = "#" + getName();
    String hhPath = "#" + getName() + "_HH";
    String mmPath = "#" + getName() + "_MI";

    private By button = By.cssSelector(buttonPath);
    private By date = By.cssSelector(datePath);
    private By hh = By.cssSelector(hhPath);
    private By mm = By.cssSelector(mmPath);

    public DTPickerOld(String name, String title) {
        super(name, title);
    }

    public DTPickerOld(String name) {
        super(name);

    }

    public By getButton() {
        return button;
    }

    public By getDate() {
        return date;
    }

    public By getHH() {
        return hh;
    }

    public By getMM() {
        return mm;
    }
}
