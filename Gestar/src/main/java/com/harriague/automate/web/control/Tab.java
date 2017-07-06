package com.harriague.automate.web.control;

import org.openqa.selenium.By;

public class Tab extends Control {

    private String height;
    private String width;

    public Tab(String nombre, String etiqueta) {
        super(nombre, etiqueta);
        getControlById();
        getControlByName();
    }

    public Tab(String nombre) {
        super(nombre);
    }

    @Override
    public void getControlById() {
        String id = "//span[@class='tab' and @id='tabSpan_tabPanel1_"+ getNombre() +"']";
        setXpathSelectorById(By.xpath(id));
    }

    @Override
    public void getControlById(String id) {
        id = "//span[@class='tab' and @id='tabSpan_tabPanel1_"+ id +"']";
        super.getControlById(id);
    }

    @Override
    public void getControlByName() {
        String name = "//a[@name and text()=' "+ getEtiqueta() +"']/ancestor::span[1]";
        setXpathSelectorByName(By.xpath(name));
    }

    @Override
    public void getControlByName(String name) {
        name = "//a[@name and text()=' "+ name +"']/ancestor::span[1]";
        super.getControlByName(name);
    }
}
