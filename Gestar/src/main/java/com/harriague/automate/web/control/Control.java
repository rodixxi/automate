package com.harriague.automate.web.control;

import org.openqa.selenium.By;


public abstract class Control {

    private String name;
    private String title;
    private By cssSelector;

    public Control() {
        this.name = "";
        this.title = "";
    }

    public Control(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public Control(String name) {
        this.name = name;
        this.title = "";
        setCssSelector();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public By getCssSelector() {
        return cssSelector;
    }

    public void setCssSelector(String cssSelector) {
        this.cssSelector = By.cssSelector(cssSelector);
    }

    public void setCssSelector(By cssSelector) {
        this.cssSelector = cssSelector;
    }

    public void setCssSelector() {
        this.cssSelector = By.cssSelector("#" + this.name);
    }

    /**
     * Toma el control por id
     *
     * @param id
     */
    public void setCssSelectorById(String id) {
        this.cssSelector = By.cssSelector("" + id);
    }

    /**
     * Toma el control por id
     */
    public void setCssSelectorById() {
        this.cssSelector = By.cssSelector("" + this.name);
    }
}
