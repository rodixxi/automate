package com.harriague.automate.web.control;

import org.openqa.selenium.By;

public class TextBox extends Control{

    private String height;
    private String width;

    private Boolean isNumeric;
    private Boolean isRequired;

    public enum Modes{
        one_line,
        multiple_line,
        password
    }

    private Modes mode;

    public TextBox(String etiqueta) {
        super(etiqueta);
        getControlByName();
    }

    public TextBox(String nombre, String etiqueta) {
        super(nombre, etiqueta);
        getControlById();
        getControlByName();
    }

    public TextBox(String etiqueta, Modes mode) {
        super(etiqueta);
        getControlByName();
        this.mode = mode;
    }

    public TextBox(String nombre, String etiqueta, Modes mode) {
        super(nombre, etiqueta);
        getControlById();
        getControlByName();
        this.mode = mode;
    }

    public TextBox(String nombre, String etiqueta, Boolean isNumeric, Boolean isRequired){
        super(nombre, etiqueta);
        getControlById();
        getControlByName();
        if(isNumeric) this.isNumeric = isNumeric;
        if(isRequired) this.isRequired = isRequired;
    }


    public Boolean getNumeric() {
        return isNumeric;
    }

    public void setNumeric(Boolean numeric) {
        isNumeric = numeric;
    }

    public Boolean getRequired() {
        return isRequired;
    }

    public void setRequired(Boolean required) {
        isRequired = required;
    }

    public Modes getMode() {
        return mode;
    }

    public void setMode(Modes mode) {
        this.mode = mode;
    }


    @Override
    public void getControlById() {
        String id = "//input[@id='" + getNombre() + "' and @name='" + getNombre() + isRequired() + "']";
        setXpathSelectorById(By.xpath(id));
    }

    @Override
    public void getControlById(String id) {
        id = "//input[@id='" + id + "' and @name='" + id + isRequired() + "']";
        super.getControlById(id);
    }

    @Override
    public void getControlByName() {
        String name = "//span[text()='" + getEtiqueta() + "']/ancestor::td[1]/following::td[1]/input";
        setXpathSelectorByName(By.xpath(name));
    }

    @Override
    public void getControlByName(String name) {
        name = "//span[text()='" + name + "']/ancestor::td[1]/following::td[1]/input";
        super.getControlByName(name);
    }

    public String isRequired(){
        String aux = "";
        if(isRequired) aux = " and isrequired='1'";
        return aux;
    }

}
