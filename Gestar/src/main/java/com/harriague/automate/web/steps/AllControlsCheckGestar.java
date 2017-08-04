package com.harriague.automate.web.steps;

import com.harriague.automate.core.steps.StepBase;
import com.harriague.automate.web.pages.ControlsGestar;
import com.harriague.automate.web.pages.FolderControlBar;
import com.harriague.automate.web.pages.FormControlBar;
import com.harriague.automate.web.pages.LoginPage;
import org.jbehave.core.annotations.*;
import org.jbehave.core.model.ExamplesTable;

import java.util.ArrayList;
import java.util.Map;

public class AllControlsCheckGestar extends StepBase {


    //@AfterStory
    public void afterStory() {
        log.info("cerrando broser ...");
        try {
            closeApplication();
            log.info("... browser cerrado!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Given("Navigate to the url: $url; in the browser: chrome.")
    public void navigateUrlBrowser(String url, String browser) throws Exception {
        getPage(StepBase.class).navigateUrlBrowser(url, browser);
    }

    @Given("me conecto a gestar con el usuario: $usuario sin pass a la instancia $instance.")
    public void doLogin(String user, String instance) throws Exception {
        getPage(LoginPage.class).doLogin(user, instance);
    }

    @Given("abrir la carpeta de automate y creo un nuevo formulario.")
    public void openAutomateForm() throws Exception {
        //getPage(GestarFolder.class).OpenFolder("autoMate");
        //getPage(GestarFolder.class).OpenFolder("autoMATE - prueba");
        creanteNewFileInFolder();
    }

    @When("seleccionar tabPanel: $tab.")
    public void selectTabPanel(String tab) throws Exception {
        getPage(ControlsGestar.class).selectTabPanel(tab);
    }

    @When("ingreso $input; para el campo requerido: $textBox.")
    public void inputToRequiredTextBox(String input, String textBox) throws Exception {
        getPage(ControlsGestar.class).inputToRequiredTextBox(input, textBox);
    }

    @When("ingreso $input; para el textBox: $textBox.")
    public void inputToTextBox(String input, String textBox) throws Exception {
        getPage(ControlsGestar.class).inputToTextBox(input, textBox);
    }

    @When("ingreso $input; para el textBox numerico: $textBox.")
    public void inputToNumericTextBox(String input, String textBox) throws Exception {
        getPage(ControlsGestar.class).inputToNumericTextBox(input, textBox);
    }

    @When("ingreso $input; para textArea: $textArea.")
    public void inputToTextArea(String input, String textArea) throws Exception {
        getPage(ControlsGestar.class).inputToTextArea(input, textArea);
    }

    @When("ingreso $input; para el textBox password: $textBox.")
    public void inputToPasswordTextBox(String input, String textBox) throws Exception {
        getPage(ControlsGestar.class).inputToPasswordTextBox(input, textBox);
    }

    @When("adjuntar el archivo: $url; en andjunto: $attatchment.")
    public void attachFile(String url, String attatchment) throws Exception {
        getPage(ControlsGestar.class).attachFile(url, attatchment);
    }

    @When("cargo manualmente la fecha: $date, con $hh:$mm  en DTPicker: $dtpicker.")
    public void loadDateByTextBox(String date, String hh, String mm, String dtpicker) throws Exception {
        getPage(ControlsGestar.class).loadDateByTextBox(date, hh, mm, dtpicker);
    }

    @When("cargo por calendario la fecha: $date, en DTPicker: $dtpicker.")
    public void loadDateByCalendarUI(String date, String dtpicker) throws Exception {
        getPage(ControlsGestar.class).loadDateByCalendarU(date, dtpicker);
    }

    @When("selecciono option: $option, de el select: $select.")
    public void selectOption(String option, String select) throws Exception {
        getPage(ControlsGestar.class).selectOption(option, select);
    }

    @When("selecciono las opciones: $tableOptions del Selector multiple: $multipleSelector.")
    public void selectMultipleOptions(ExamplesTable $tableOptions, String multipleSelector) throws Exception {
        ArrayList<String> options = new ArrayList();
        for (Map<String, String> row : $tableOptions.getRows()) {
            String option = row.get("options");
            options.add(option);
        }
        getPage(ControlsGestar.class).selectMultipleOptions(options, multipleSelector);
    }

    @When("selecciono todas las opciones del selector multiple: $multipleSelector.")
    public void selectMultipleOptionsAll(String multipleSelector) throws Exception {
        ArrayList<String> options = new ArrayList();
        getPage(ControlsGestar.class).selectMultipleOptionsAll(multipleSelector);
    }

    @When("deselecciono las opciones: $tableOptions del selector multiple: $multipleSelector.")
    public void deselectMultipleOptions(ExamplesTable $tableOptions, String multipleSelector) throws Exception {
        ArrayList<String> options = new ArrayList();
        for (Map<String, String> row : $tableOptions.getRows()) {
            String option = row.get("options");
            options.add(option);
        }
        getPage(ControlsGestar.class).deselectMultipleOptions(options, multipleSelector);
    }

    @When("deselecciono todas las opciones del selector multiple: $multipleSelector.")
    public void deselectMultipleOptionsAll(String multipleSelector) throws Exception {
        ArrayList<String> options = new ArrayList();
        getPage(ControlsGestar.class).deselectMultipleOptionsAll(multipleSelector);
    }

    @When("busco : $search; en el control de autocompletado: $autoComplete.")
    public void searchOptionAutoComplete(String search, String autoComplete) throws Exception {
        getPage(ControlsGestar.class).searchOptionAutoComplete(search, autoComplete);
    }

    @When("busco: $search; en el control LookUpBoxAccount: $control.")
    public void searchLookUpBoxAccount(String search, String control) throws Exception {
        getPage(ControlsGestar.class).searchLookUpBoxAccount(search, control);
    }

    @When("busco: $search; en el control con DobleClick LookUpBoxAccount: $control.")
    public void searchLookUpBoxAccountDobleClick(String search, String control) throws Exception {
        getPage(ControlsGestar.class).searchLookUpBoxAccountDobleClick(search, control);
    }

    @When("guardar y salir del formulario.")
    public void saveForm() throws Exception {
        getPage(FormControlBar.class).saveForm();
    }

    @Then("estas a nivel de folder.")
    public void atFolderLevel() throws Exception {
        getPage(FolderControlBar.class).atFolderLevel();
    }

    @Then("seleccionar la vista: $viewName.")
    public void selectView(String viewName) throws Exception {
        getPage(FolderControlBar.class).selectView(viewName);
    }

    @When("seleccionar la opcion: $optionExpected; del menu Documentos.")
    public void selectOptionFromDomuentsMenu(String $optionExpected) throws Exception {
        getPage(FolderControlBar.class).selectOptionFromDocumentsMenu($optionExpected);
    }

    @When("crear arhivo nuevo.")
    public void creanteNewFileInFolder() throws Exception {
        getPage(FolderControlBar.class).creanteNewFileInFolder();
    }

}


