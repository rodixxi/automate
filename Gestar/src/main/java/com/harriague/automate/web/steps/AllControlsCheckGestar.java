package com.harriague.automate.web.steps;

import com.harriague.automate.core.steps.StepBase;
import com.harriague.automate.web.pages.*;
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


    @Given("me conecto a gestar con el usuario: $user sin pass a la instancia $instance.")
    public void doLogin(String user, String instance) throws Exception {
        getPage(LoginPage.class).doLogin(user, instance);
    }

    @Given("abrir la carpeta de automate y creo un nuevo formulario.")
    public void openAutomateForm() throws Exception {
        getPage(GestarFolder.class).OpenFolder("autoMate");
        getPage(GestarFolder.class).OpenFolder("autoMATE - prueba");
        creanteNewFileInFolder();
    }

    @When("seleccionar tabPanel: $tabName.")
    public void selectTabPanel(String tabName) throws Exception {
        getPage(ControlsGestar.class).selectTabPanel(tabName);
    }

    @When("en el campo requerido: $textBox; ingreso $input.")
    public void inputToRequiredTextBox(String textBoxName, String input) throws Exception {
        getPage(ControlsGestar.class).inputToRequiredTextBox(textBoxName, input);
    }

    @When("en el textBox: $textBoxName; ingreso $input.")
    public void inputToTextBox(String textBoxName, String input) throws Exception {
        getPage(ControlsGestar.class).inputToTextBox(textBoxName, input);
    }

    @When("en el textBox numerico: $textBoxName; ingreso $input.")
    public void inputToNumericTextBox(String textBoxName, String input) throws Exception {
        getPage(ControlsGestar.class).inputToNumericTextBox(textBoxName, input);
    }

    @When("en el textArea: $textArea; ingreso $input.")
    public void inputToTextArea(String textAreaName, String input) throws Exception {
        getPage(ControlsGestar.class).inputToTextArea(textAreaName, input);
    }

    @When("en el textBox password: $textBoxName; ingreso $input.")
    public void inputToPasswordTextBox(String textBoxName, String input) throws Exception {
        getPage(ControlsGestar.class).inputToPasswordTextBox(textBoxName, input);
    }

    @When("en el campo andjunto: $attachmentControlName; adjuntar el archivo: $fileURL -")
    public void attachFileToAttatchmentControl(String attachmentControlName, String fileURL) throws Exception {
        getPage(ControlsGestar.class).attachFileToAttatchmentControl(attachmentControlName, fileURL);
    }

    @When("en el DTPicker: $dtpickerName; cargo manualmente la fecha: $date, con $hh:$mm.")
    public void loadDateByTextBox(String dtpickerName, String date, String hh, String mm) throws Exception {
        getPage(ControlsGestar.class).loadDateByTextBox(date, hh, mm, dtpickerName);
    }

    @When("en el DTPicker: $dtpicker; cargo por calendario la fecha: $date.")
    public void loadDateByCalendarUI(String dtpicker, String date) throws Exception {
        getPage(ControlsGestar.class).loadDateByCalendarUI(date, dtpicker);
    }

    @When("en el select: $select; selecciono option: $option.")
    public void selectOption(String select, String option) throws Exception {
        getPage(ControlsGestar.class).selectOption(option, select);
    }

    @When("en el selector multiple: $multipleSelector; selecciono las opciones: $tableOptions.")
    public void selectMultipleOptions(String multipleSelector, ExamplesTable $tableOptions) throws Exception {
        ArrayList<String> options = new ArrayList();
        for (Map<String, String> row : $tableOptions.getRows()) {
            String option = row.get("options");
            options.add(option);
        }
        getPage(ControlsGestar.class).selectMultipleOptions(options, multipleSelector);
    }

    @When("en el selector multiple: $multipleSelecto; selecciono todas las opciones.")
    public void selectMultipleOptionsAll(String multipleSelector) throws Exception {
        ArrayList<String> options = new ArrayList();
        getPage(ControlsGestar.class).selectMultipleOptionsAll(multipleSelector);
    }

    @When("en el selector multiple: $multipleSelector; deselecciono las opciones: $tableOptions.")
    public void deselectMultipleOptions(String multipleSelector, ExamplesTable tableOptions) throws Exception {
        ArrayList<String> options = new ArrayList();
        for (Map<String, String> row : tableOptions.getRows()) {
            String option = row.get("options");
            options.add(option);
        }
        getPage(ControlsGestar.class).deselectMultipleOptions(options, multipleSelector);
    }

    @When("en el selector multiple: $multipleSelector; deselecciono todas las opciones.")
    public void deselectMultipleOptionsAll(String multipleSelector) throws Exception {
        ArrayList<String> options = new ArrayList();
        getPage(ControlsGestar.class).deselectMultipleOptionsAll(multipleSelector);
    }

    @When("en el control de autocompletado: $autoComplete; busco: $search.")
    public void searchOptionAutoComplete(String autoComplete, String search) throws Exception {
        getPage(ControlsGestar.class).searchOptionAutoComplete(search, autoComplete);
    }

    @When("en el control de autocompletado: $autoComplete; busqueda multiple: $searchOptions.")
    public void searchOptionAutoCompleteMultiple(String autoComplete, ExamplesTable searchOptions) throws Exception {
        ArrayList<String> options = new ArrayList();
        for (Map<String, String> row : searchOptions.getRows()) {
            String option = row.get("options");
            options.add(option);
        }
        getPage(ControlsGestar.class).searchOptionAutoCompleteMultiple(autoComplete, options);
    }

    @When("en el control lookUpBoxAccount: $control; busco: $search.")
    public void searchLookUpBoxAccount(String control, String search) throws Exception {
        getPage(ControlsGestar.class).searchLookUpBoxAccount(search, control);
    }

    @When("en el lookUpBoxAccount: $control; busco: $search.")
    public void searchLookUpBoxAccountDobleClick(String control, String search) throws Exception {
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


