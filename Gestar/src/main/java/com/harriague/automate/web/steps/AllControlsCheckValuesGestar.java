package com.harriague.automate.web.steps;

import com.harriague.automate.core.steps.StepBase;
import com.harriague.automate.web.pages.CheckForm;
import com.harriague.automate.web.pages.CheckFormValues;
import com.harriague.automate.web.pages.GestarFolder;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.model.ExamplesTable;

import java.util.ArrayList;
import java.util.Map;

public class AllControlsCheckValuesGestar extends StepBase {

    @Given("abrir la carpeta de automate.")
    public void openAutomateFolder() throws Exception {
        getPage(GestarFolder.class).OpenFolder("autoMate");
        getPage(GestarFolder.class).OpenFolder("autoMATE - prueba");
    }

    @Given("seleccionado el formulario donde el campo: $field; es igual a: $valueEqualTo.")
    public void selectFormWhere(String field, String valueEqualTo) throws Exception {
        getPage(CheckForm.class).getFormWhere(field, valueEqualTo);
    }

    @Given("seleccionado los formularios donde el campo: $field; es igual a: $valueEqualTo.")
    public void selectFormWhere(String field, ExamplesTable valueEqualTo) throws Exception {
        ArrayList<String> options = new ArrayList();
        for (Map<String, String> row : valueEqualTo.getRows()) {
            String option = row.get("options");
            options.add(option);
        }
        getPage(CheckForm.class).selectFormWhere(field, options);
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

    @Then("el DTPicker: $dtpicker; tiene la fecha y hora: $date, con $hh:$mm.")
    public void checkDtpickerDateAndHour(String dtpicker, String date, String hh, String mm) throws Exception {
        getPage(CheckFormValues.class).checkDtpickerDateAndHour(dtpicker, date, hh, mm);
    }

    @Then("el DTPicker: $dtpicker; tiene la fecha: $date.")
    public void checkDtpickerDate(String dtpicker, String date) throws Exception {
        getPage(CheckFormValues.class).checkDtpickerDate(dtpicker, date);
    }

    @Then("el LookUpBoxAccount: $lookUpBoxAccount; tiene seleccionado: $account.")
    public void checkLookUpBoxAccount(String lookUpBoxAccount, String account) throws Exception {
        getPage(CheckFormValues.class).checkLookUpBoxAccount(lookUpBoxAccount, account);
    }

    @Then("el Select: $selectControl; tiene seleccionado: $optionExpected.")
    public void checkSelectedOption(String selectControl, String optionExpected) throws Exception {
        getPage(CheckFormValues.class).checkSelectedOption(selectControl, optionExpected);
    }

    @Then("el Selector multiple: $multipleSelector; selecciono las opciones: $tableOptions.")
    public void checkMultipleOptionsRightList(String  multipleSelector, ExamplesTable tableOptions) throws Exception {
        ArrayList<String> options = new ArrayList();
        for (Map<String, String> row : tableOptions.getRows()) {
            String option = row.get("options");
            options.add(option);
        }
        getPage(CheckFormValues.class).checkMultipleOptionsRightList(options, multipleSelector);
    }

    @Then("el Selector multiple: $multipleSelector; no selecciono las opciones: $tableOptions.")
    public void checkMultipleOptionsLeftList(String  multipleSelector, ExamplesTable tableOptions) throws Exception {
        ArrayList<String> options = new ArrayList();
        for (Map<String, String> row : tableOptions.getRows()) {
            String option = row.get("options");
            options.add(option);
        }
        getPage(CheckFormValues.class).checkMultipleOptionsLeftList(options, multipleSelector);
    }

    @Then("el Autocomplete: $autoCompleteControl; selecciono el valor: $valueExpected.")
    public void checkAutoComplete(String autoCompleteControl, String valueExpected) throws Exception {
        getPage(CheckFormValues.class).checkAutoComplete(autoCompleteControl, valueExpected);
    }

    @Then("el Autocomplete: $autoCompleteControl; selecciono los valores: $valuesExpected.")
    public void checkAutoCompleteMultiple(String autoCompleteControl, ExamplesTable valuesExpected) throws Exception {
        ArrayList<String> optionsExpected = new ArrayList();
        for (Map<String, String> row : valuesExpected.getRows()) {
            String option = row.get("options");
            optionsExpected.add(option);
        }
        getPage(CheckFormValues.class).checkAutoCompleteMultiple(autoCompleteControl, optionsExpected);
    }

}
