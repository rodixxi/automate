package com.harriague.automate.web.control;


import org.openqa.selenium.By;

public class SelectorMultiple extends Control{

    private By leftOptions = By.xpath("//table[@id='tbCtrl_" + getNombre() + "']//select[@id='" + getNombre() + "_leftselect']/options");
    private By rightOptions = By.xpath("//table[@id='tbCtrl_" + getNombre() + "']//select[@id='" + getNombre() + "_rightselect']/option");
    private By toLeftButton = By.xpath("//table[@id='tbCtrl_" + getNombre() + "']//button[@id='" + getNombre() + "_toleftbutton']");
    private By toRightButton = By.xpath("//table[@id='tbCtrl_" + getNombre() + "']//button[@id='" + getNombre() + "_torightbutton']");
    private By toLeftAllButton =  By.xpath("//table[@id='tbCtrl_" + getNombre() + "']//button[@id='" + getNombre() + "_toleftallbutton']");
    private By toRightAllButton =  By.xpath("//table[@id='tbCtrl_" + getNombre() + "']//button[@id='" + getNombre() + "_torightallbutton']");

    public SelectorMultiple(String nombre, String etiqueta) {
        super(nombre, etiqueta);
    }

    public SelectorMultiple(String etiqueta) {
        super(etiqueta);
    }
}
