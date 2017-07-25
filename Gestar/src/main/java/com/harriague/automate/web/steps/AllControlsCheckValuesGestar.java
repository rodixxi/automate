package com.harriague.automate.web.steps;

import com.harriague.automate.core.steps.StepBase;
import com.harriague.automate.web.pages.CheckForm;
import com.harriague.automate.web.pages.CheckFormValues;
import com.harriague.automate.web.pages.GestarFolder;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;

public class AllControlsCheckValuesGestar extends StepBase {

    @BeforeScenario
    public void beforeScenario() throws Exception {
        getPage(GestarFolder.class).OpenFolder("autoMate");
        getPage(GestarFolder.class).OpenFolder("autoMATE - prueba");
    }

    @Given("seleccionado el formulario donde el campo: $field; es igual a: $valueEqualTo.")
    public void selectFormWhere(String field, String valueEqualTo) throws Exception {
        getPage(CheckForm.class).getFormWhere(field, valueEqualTo);
    }

    @Then("el campo textBox: $textBox; tiene el valor: $value.")
    public void checkValueInTextBox(String textBox, String value) throws Exception {
        getPage(CheckFormValues.class).checkValueInTextBox(textBox, value);
    }
}
