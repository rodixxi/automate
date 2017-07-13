package com.harriague.automate.web.control;


import org.openqa.selenium.By;

public class SelectorMultiple extends Control{

    private By leftOptions = By.cssSelector("div#select_" + getNombre() + "_leftselect_container:nth-child(1) > select > option");
    private By rightOptions = By.cssSelector("div#select_" + getNombre() + "_rightselect_container:nth-child(1) > select > option");
    private By toLeftButton = By.cssSelector("#" + getNombre() + "_toleftbutton");
    private By toRightButton = By.cssSelector("#" + getNombre() + "_torightbutton");
    private By toLeftAllButton =  By.cssSelector("#" + getNombre() + "_toleftallbutton");
    private By toRightAllButton =  By.cssSelector("#" + getNombre() + "_torightallbutton");

    public SelectorMultiple(String nombre, String etiqueta) {
        super(nombre, etiqueta);
    }

    public SelectorMultiple(String etiqueta) {
        super(etiqueta);
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
