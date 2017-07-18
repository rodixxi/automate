package com.harriague.automate.web.control;


import org.openqa.selenium.By;

public class SelectorMultiple extends Control {

    private String leftOptionsPath = "div#select_" + getName() + "_leftselect_container:nth-child(1) > select > option";
    private String rightOptionsPath = "div#select_" + getName() + "_rightselect_container:nth-child(1) > select > option";
    private String toLeftButtonPath = "#" + getName() + "_toleftbutton";
    private String toRightButtonPath = "#" + getName() + "_torightbutton";
    private String toLeftAllButtonPath = "#" + getName() + "_toleftallbutton";
    private String toRightAllButtonPath = "#" + getName() + "_torightallbutton";

    private By leftOptions = By.cssSelector(leftOptionsPath);
    private By rightOptions = By.cssSelector(rightOptionsPath);
    private By toLeftButton = By.cssSelector(toLeftButtonPath);
    private By toRightButton = By.cssSelector(toRightButtonPath);
    private By toLeftAllButton = By.cssSelector(toLeftAllButtonPath);
    private By toRightAllButton = By.cssSelector(toRightAllButtonPath);

    public SelectorMultiple(String name, String title) {
        super(name, title);
    }

    public SelectorMultiple(String name) {
        super(name);
    }

    public By getLeftOptions() {
        return leftOptions;
    }

    public By getRightOptions() {
        return rightOptions;
    }

    public By getToLeftButton() {
        return toLeftButton;
    }

    public By getToRightButton() {
        return toRightButton;
    }

    public By getToLeftAllButton() {
        return toLeftAllButton;
    }

    public By getToRightAllButton() {
        return toRightAllButton;
    }
}
