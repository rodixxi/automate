package com.harriague.automate.web.control;

import org.openqa.selenium.By;

public class Button extends Control {

    private String text = "";
    private String buttonXpath= "//button[text()='"+ text +"']";


    public Button(String nombre, String etiqueta) {
        super(nombre, etiqueta);
    }

    public Button(String text) {
        super("");
        this.text = text;
        getButtonXpath();
    }


    public void getButtonXpath(){
        buttonXpath = "//button[text()='"+ text +"']";
        //setXpathSelectorByName(By.xpath(buttonXpath));
    }
}
