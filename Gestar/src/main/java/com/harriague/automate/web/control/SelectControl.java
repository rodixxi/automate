package com.harriague.automate.web.control;

import org.openqa.selenium.By;

public class SelectControl extends Control{


    public SelectControl(String nombre, String etiqueta) {
        super(nombre, etiqueta);
    }

    public SelectControl(String etiqueta) {
        super(etiqueta);
        String path = "//span[text()='" + etiqueta + "']/ancestor::td[1]/following::td[1]//select";
        setXpathSelectorByName(By.xpath(path));
    }
}
