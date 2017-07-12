package com.harriague.automate.web.control;

import org.openqa.selenium.By;

public class Attachment extends Control{

    private String inputButtom = "//input[@name='file']";
    private String addButtom = "//input[@value='Agregar']";
    private String deleteButtom = "//input[@value='Eliminar']";
    private String closeButtom = "//input[@value='Cerrar']";

    private By inputButtomXpath = By.xpath(inputButtom);
    private By addButtomXpath = By.xpath(addButtom);
    private By deleteButtomXpath = By.xpath(deleteButtom);
    private By closeButtomXpath = By.xpath(closeButtom);





    public Attachment(String nombre, String etiqueta) {
        super(nombre, etiqueta);
    }


    public Attachment(String etiqueta) {

        super(etiqueta);
        getControlByName();

    }

    public By getInputButtomXpath() {
        return inputButtomXpath;
    }

    public By getAddButtomXpath() {
        return addButtomXpath;
    }

    public By getDeleteButtomXpath() {
        return deleteButtomXpath;
    }

    public By getCloseButtomXpath() {
        return closeButtomXpath;
    }

    public String getInputButtom() {
        return inputButtom;
    }

    public void setInputButtom(String inputButtom) {
        this.inputButtom = inputButtom;
    }

    public String getAddButtom() {
        return addButtom;
    }

    public void setAddButtom(String addButtom) {
        this.addButtom = addButtom;
    }

    public String getDeleteButtom() {
        return deleteButtom;
    }

    public void setDeleteButtom(String deleteButtom) {
        this.deleteButtom = deleteButtom;
    }

    public String getCloseButtom() {
        return closeButtom;
    }

    public void setCloseButtom(String closeButtom) {
        this.closeButtom = closeButtom;
    }

    @Override
    public void getControlByName() {
        String path = "//span[text()='" + getEtiqueta() + "']/ancestor::td[1]/following::td[1]//img";
        setXpathSelectorByName(By.xpath(path));
    }

}
