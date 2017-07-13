package com.harriague.automate.web.control;

import org.openqa.selenium.By;

public class Tab extends Control {

    private String height;
    private String width;

    public Tab(String nombre, String etiqueta) {
        super(nombre, etiqueta);
        setCssSelectorById();

    }

    public Tab(String nombre) {
        super(nombre);
        setCssSelectorById();
    }

    @Override
    public void setCssSelectorById() {
        String id = "#tabSpan_tabPane1_" + getNombre();
        setCssSelector(By.cssSelector(id));
    }

    @Override
    public void setCssSelectorById(String id) {
        id = "#tabSpan_tabPane1_" + id;
        super.setCssSelectorById(id);
    }

    /**
     * Retorna el xpath de el tab como si estubiera seleccioando por id
     *
     * @return xpathAsSelected
     */
    public By tabSelectedById() {
        return By.xpath("#tabSpan_tabPane1_" + getNombre() + ".selected");
    }
}
