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

    public Tab(String etiqueta) {
        super(etiqueta);
        getControlByName();
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

    /**
     * Retorna el xpath de el tab como si estubiera seleccioando por id
     * @return xpathAsSelected
     */
    public By tabSelectedById(){
        return By.xpath("//span[@class='tab' and @class='selected' and @id='tabSpan_tabPanel1_"+ getNombre() +"']");
    }

    /**
     * Retorna el xpath de el tab como si estubiera seleccioando por nombre
     * @return xpathAsSelected
     */
    public By tabSelectedByName(){
        System.out.println(By.xpath("//a[@name and text()=' " + getEtiqueta() + "']/ancestor::span[1]/@class='tab selected'"));
        return By.xpath("//a[@name and text()=' " + getEtiqueta() + "']/ancestor::span[1]/@class='tab selected'");
    }
}

