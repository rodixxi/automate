package com.harriague.automate.web.control;

import org.openqa.selenium.By;

public class Checkbox extends Control{


    public Checkbox(String nombre, String etiqueta) {
        super(nombre, etiqueta);
    }

    public Checkbox(String etiqueta) {
        super(etiqueta);
        String xpath = "//span[text()='" + etiqueta + "']/ancestor::td[1]/following::td[1]/span/input[@type='checkbox']";
        //setXpathSelectorByName(By.xpath(xpath));
    }

}
