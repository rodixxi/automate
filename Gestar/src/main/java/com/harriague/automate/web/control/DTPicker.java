package com.harriague.automate.web.control;

import org.openqa.selenium.By;

public class DTPicker extends Control{

    private By buttonXpath;
    private By dateXpath;
    private By hhXpath;
    private By mmXpath;

    public By getButtonXpath() {
        return buttonXpath;
    }

    public By getDateXpath() {
        return dateXpath;
    }

    public By getHhXpath() {
        return hhXpath;
    }

    public By getMmXpath() {
        return mmXpath;
    }

    public DTPicker(String nombre, String etiqueta) {
        super(nombre, etiqueta);
    }

    public DTPicker(String etiqueta) {
        super(etiqueta);

        String buttonPath = "//span[text()='" + etiqueta + "']/ancestor::td[1]/following::td[1]/img[1][@class='ui-datepicker-trigger']";
        String datePath = "//span[text()='" + etiqueta + "']/ancestor::td[1]/following::td[1]/input[1][@class='hasDatepicker']";
        String hhPath = "//span[text()='" + etiqueta + "']/ancestor::td[1]/following::td[1]/input[2]";
        String mmPath = "//span[text()='" + etiqueta + "']/ancestor::td[1]/following::td[1]/input[3]";

        buttonXpath = By.xpath(buttonPath);
        dateXpath = By.xpath(datePath);
        hhXpath = By.xpath(hhPath);
        mmXpath = By.xpath(mmPath);
    }
}
