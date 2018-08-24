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

    @Given("switch to $url; url.")
    public void switchURL(String url) throws Exception {
        getPage(LoginPage.class).switchURL(url);
    }

    @Given("me conecto a gestar con el usuario: $user sin pass a la instancia $instance.")
    public void doLogin(String user, String instance) throws Exception {
        getPage(LoginPage.class).doLogin(user, instance);
    }

    @Given("me conecto a gestar con el usuario: $user; con la pass: $pass; a la instancia $instance.")
    public void doLoginWithPassAndTextInstance(String user, String pass, String instance) throws Exception {
        getPage(LoginPage.class).doLoginWithPassAndTextInstance(user, pass, instance);
    }

    @Given("abrir la carpeta de automate y creo un nuevo formulario.")
    public void openAutomateForm() throws Exception {
        getPage(GestarFolder.class).OpenFolder("autoMate");
        getPage(GestarFolder.class).OpenFolder("autoMATE - prueba");
        creanteNewFileInFolder();
    }

    @Given("abrir la carpeta: $folder.")
    public void openFolder(String folder) throws Exception {
        getPage(GestarFolder.class).OpenFolder(folder);
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

    @When("en el campo andjunto: $attachmentControlName; adjuntar el archivo de texto: $fileURL -")
    public void attachTextFileToAttatchmentControl(String attachmentControlName, String fileURL) throws Exception {
        getPage(ControlsGestar.class).attachTextFileToAttatchmentControl(attachmentControlName, fileURL);
    }

    /*
    @When("en el campo andjunto: $attachmentControlName; adjuntar el archivo: $fileURL -")
    public void attachFileToAttatchmentControlNew(String attachmentControlName, String fileURL) throws Exception {
        getPage(ControlsGestar.class).attachFileToAttatchmentControlNew(attachmentControlName, fileURL);
    }
*/
    @When("en el DTPickerOld: $dtpickerName; cargo manualmente la fecha: $date, con $hh:$mm.")
    public void loadDateByTextBox(String dtpickerName, String date, String hh, String mm) throws Exception {
        getPage(ControlsGestar.class).loadDateByTextBox(date, hh, mm, dtpickerName);
    }

    @When("en el DTPickerOld: $dtpicker; cargo por calendario la fecha: $date.")
    public void loadDateByCalendarUI(String dtpicker, String date) throws Exception {
        getPage(ControlsGestar.class).loadDateByCalendarUI(date, dtpicker);
    }

    @When("en el select: $selectName; selecciono option: $optionToSelect.")
    public void selectOption(String selectName, String optionToSelect) throws Exception {
        getPage(ControlsGestar.class).selectOption(selectName, optionToSelect);
    }

    @When("en el selector multiple: $multipleSelectorName; selecciono las opciones: $optionsTable.")
    public void selectMultipleOptions(String multipleSelectorName, ExamplesTable optionsTable) throws Exception {
        ArrayList<String> optionsToSelect = new ArrayList();
        for (Map<String, String> row : optionsTable.getRows()) {
            String option = row.get("options");
            optionsToSelect.add(option);
        }
        getPage(ControlsGestar.class).selectMultipleOptions(multipleSelectorName, optionsToSelect);
    }

    @When("en el selector multiple: $multipleSelectorName; selecciono todas las opciones.")
    public void selectMultipleOptionsAll(String multipleSelectorName) throws Exception {
        getPage(ControlsGestar.class).selectMultipleOptionsAll(multipleSelectorName);
    }

    @When("en el selector multiple: $multipleSelectorName; deselecciono las opciones: $optionsTable.")
    public void deselectMultipleOptions(String multipleSelectorName, ExamplesTable optionsTable) throws Exception {
        ArrayList<String> optionsToUnselect = new ArrayList();
        for (Map<String, String> row : optionsTable.getRows()) {
            String option = row.get("options");
            optionsToUnselect.add(option);
        }
        getPage(ControlsGestar.class).deselectMultipleOptions(multipleSelectorName, optionsToUnselect);
    }

    @When("en el selector multiple: $multipleSelectorName; deselecciono todas las opciones.")
    public void deselectMultipleOptionsAll(String multipleSelectorName) throws Exception {
        getPage(ControlsGestar.class).deselectMultipleOptionsAll(multipleSelectorName);
    }

    @When("en el control de autocompletado: $autoCompleteName; busco: $optionToSearch.")
    public void searchOptionAutoComplete(String autoCompleteName, String optionToSearch) throws Exception {
        getPage(ControlsGestar.class).searchOptionAutoComplete(autoCompleteName, optionToSearch);
    }

    @When("en el control de autocompletado: $autoCompleteName; busqueda multiple: $optionsToSearchTable.")
    public void searchOptionAutoCompleteMultiple(String autoCompleteName, ExamplesTable optionsToSearchTable) throws Exception {
        ArrayList<String> optionsToSearch = new ArrayList();
        for (Map<String, String> row : optionsToSearchTable.getRows()) {
            String option = row.get("options");
            optionsToSearch.add(option);
        }
        getPage(ControlsGestar.class).searchOptionAutoCompleteMultiple(autoCompleteName, optionsToSearch);
    }

    @When("en el control lookUpBoxAccount: $lookUpBoxAccountName; busco: $optionToSearch.")
    public void searchInLookUpBoxAccount(String lookUpBoxAccountName, String optionToSearch) throws Exception {
        getPage(ControlsGestar.class).searchInLookUpBoxAccount(lookUpBoxAccountName, optionToSearch);
    }

    @When("en el lookUpBoxAccount: $control; busco: $search.")
    public void searchInLookUpBoxAccountDobleClick(String control, String search) throws Exception {
        getPage(ControlsGestar.class).searchInLookUpBoxAccountDobleClick(control, search);
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


