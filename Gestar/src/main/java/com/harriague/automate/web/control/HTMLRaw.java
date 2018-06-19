package com.harriague.automate.web.control;

import org.openqa.selenium.By;

public class HTMLRaw extends Control {

    private String controlTablePath = "#tbCtrl_" + getName();

    private By controlTable = By.cssSelector(controlTablePath);

    public HTMLRaw(String name, String title) {
        super(name, title);
    }

    public HTMLRaw(String name) {
        super(name);
    }

    public By getControlTable() {
        return controlTable;
    }

    public By getHTMLRawElement(String elementCss) {
        return By.cssSelector(controlTablePath + " " + elementCss);
    }
}
