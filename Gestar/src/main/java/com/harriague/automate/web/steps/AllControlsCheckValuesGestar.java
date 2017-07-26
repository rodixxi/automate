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

    @Then("el campo textArea: $textArea; tiene el valor: $value.")
    public void checkValueInTextArea(String textArea, String value) throws Exception {
        getPage(CheckFormValues.class).checkValueInTextArea(textArea, value);
    }

    @Then("el campo textBox requerido: $textBox; tiene el valor: $value.")
    public void checkValueInRequiredTextBox(String textBox, String value) throws Exception {
        getPage(CheckFormValues.class).checkValueInRequiredTextBox(textBox, value);
    }

    @Then("el campo textBox numerico: $textBox; tiene el valor: $value.")
    public void checkValueInNumericTextBox(String textBox, String value) throws Exception {
        getPage(CheckFormValues.class).checkValueInNumericTextBox(textBox, value);
    }

    @Then("el campo textBox password: $textBox; tiene el valor: $value.")
    public void checkValueInPasswordTextBox(String textBox, String value) throws Exception {
        getPage(CheckFormValues.class).checkValueInPasswordTextBox(textBox, value);
    }

    @Then("el campo andjunto: $attatchField; tiene el archivo: $attachName")
    public void checkAttatchment(String attatchField, String attachName) throws Exception {
        getPage(CheckFormValues.class).checkAttatchment(attatchField, attachName);
    }

    @Then("el checkbox: $checkboxId; esta seleccionado")
    public void checkCheckBoxChecked(String checkboxId) throws Exception {
        getPage(CheckFormValues.class).checkCheckBoxChecked(checkboxId, true);
    }
}
