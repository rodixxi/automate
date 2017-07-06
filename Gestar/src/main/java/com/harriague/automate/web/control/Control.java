package com.harriague.automate.web.control;

import org.openqa.selenium.By;
import com.harriague.automate.core.agent.Agent;

public class Control {

    private String nombre;
    private String etiqueta;
    private By xpathSelectorById;
    private By xpathSelectorByName;

    public Control(String nombre, String etiqueta) {
        this.nombre = nombre;
        this.etiqueta = etiqueta;
    }

    public Control(String nombre) {
        this.nombre = nombre;
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

    public By getXpathSelectorById() {
        return xpathSelectorById;
    }

    public void setXpathSelectorById(By xpathSelectorById) {
        this.xpathSelectorById = xpathSelectorById;
    }

    public By getXpathSelectorByName() {
        return xpathSelectorByName;
    }

    public void setXpathSelectorByName(By xpathSelectorByName) {
        this.xpathSelectorByName = xpathSelectorByName;
    }

    /**
     * Toma el control por id
     * @param id
     */
    public void getControlById(String id){
        this.xpathSelectorById = By.xpath("" + id);
    }

    /**
     * Toma el control por id
     *
     */
    public void getControlById(){
        this.xpathSelectorById = By.xpath("" + this.nombre);
    }

    /**
     * Toma el control por nombre
     * @param name
     */
    public void getControlByName(String name){
        this.xpathSelectorByName = By.xpath("" + name);
    }

    /**
     * Toma el control por nombre
     *
     */
    public void getControlByName(){
        this.xpathSelectorByName = By.xpath("" + this.etiqueta);
    }
}
