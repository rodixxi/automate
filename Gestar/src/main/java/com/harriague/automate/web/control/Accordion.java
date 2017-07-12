package com.harriague.automate.web.control;

import org.openqa.selenium.By;

public class Accordion extends Control{
    public Accordion(String nombre, String etiqueta) {
        super(nombre, etiqueta);
    }

    public Accordion(String etiqueta) {
        super(etiqueta);
        String path = "//p['" + etiqueta + "']/ancestor::h3[1]";
        setXpathSelectorByName(By.xpath(path));
    }
}
