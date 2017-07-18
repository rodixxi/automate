package com.harriague.automate.web.control;

import org.openqa.selenium.By;


public class Control {

    private String nombre;
    private String etiqueta;
    private By cssSelector;

    public Control(String nombre, String etiqueta) {
        this.nombre = nombre;
        this.etiqueta = etiqueta;
    }

    public Control(String nombre) {
        this.nombre = nombre;
        this.etiqueta = "";
        setCssSelector();

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public By getCssSelector() {
        return cssSelector;
    }

    public void setCssSelector(By cssSelector) {
        this.cssSelector = cssSelector;
    }

    public void setCssSelector() {
        this.cssSelector = By.cssSelector("#" + this.nombre);
    }

    /**
     * Toma el control por id
     *
     * @param id
     */
    public void setCssSelectorById(String id) {
        this.cssSelector = By.cssSelector("" + id);
    }

    /**
     * Toma el control por id
     */
    public void setCssSelectorById() {
        this.cssSelector = By.cssSelector("" + this.nombre);
    }
}
