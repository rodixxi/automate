package com.harriague.automate.web.control;

import org.openqa.selenium.By;

public class Accordion extends Control {

    private String accordion_path = "ui-accordion-accordion_" + getNombre() + "-header-0";
    private By accordion = By.cssSelector(accordion_path);

    public Accordion(String nombre, String etiqueta) {
        super(nombre, etiqueta);
    }

    public Accordion(String nombre) {
        super(nombre);
    }

    public By getAccordion() {
        return accordion;
    }
}
