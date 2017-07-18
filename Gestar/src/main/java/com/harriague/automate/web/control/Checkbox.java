package com.harriague.automate.web.control;

import org.openqa.selenium.By;

public class Checkbox extends Control {


    public Checkbox(String nombre, String etiqueta) {
        super(nombre, etiqueta);
        setCssSelector();
    }

    public Checkbox(String nombre) {
        super(nombre);
        setCssSelector();
    }

    @Override
    public void setCssSelector() {
        By aux = By.cssSelector(getNombre() + "_chk");
        super.setCssSelector();
    }
}
