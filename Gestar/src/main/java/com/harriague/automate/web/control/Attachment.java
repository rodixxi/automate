package com.harriague.automate.web.control;

import org.openqa.selenium.By;

public class Attachment extends Control{

    public Attachment(String nombre, String etiqueta) {
        super(nombre, etiqueta);
    }


    public Attachment(String etiqueta) {

        super(etiqueta);
        getControlByName();

    }


    @Override
    public void getControlByName() {
        String path = "//span[text()='" + getEtiqueta() + "']/ancestor::td[1]/following::td[1]//img";
        setXpathSelectorByName(By.xpath(path));
    }

}
