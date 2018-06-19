package com.harriague.automate.web.steps;

import com.harriague.automate.core.exceptions.AgentException;
import com.harriague.automate.core.steps.StepBase;
import com.harriague.automate.web.pages.ControlsGestar;
import com.harriague.automate.web.pages.FolderControlBar;
import com.harriague.automate.web.pages.GestarFolder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class entitiesImporterWR extends StepBase {

    String nameAux = "";

    @Given("abrir Ingreso de Entidades, y creo un nuevo formulario.")
    public void openAutomateForm() throws Exception {
        getPage(GestarFolder.class).OpenFolder("Ingreso de Entidades");
        creanteNewFileInFolder();
    }

    @When("crear arhivo nuevo.")
    public void creanteNewFileInFolder() throws Exception {
        getPage(FolderControlBar.class).creanteNewFileInFolder();
    }

    @When("Cargar archivos en csv $name")
    public void loadEntitiesInCsv(String name) throws Exception {
        List<CSVRecord> badRecords = new ArrayList<CSVRecord>();
        String[] HEADERS = {"ubicacion","entidad","codigoAlfanumerico","speakerType","Type","FWVersion","HWVersion","misc","serial","fecha","centroDeCosto","proveedor"};
        Reader in = new FileReader(name + ".csv");
        nameAux = name;
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader(HEADERS)
                .withDelimiter(';')
                .withFirstRecordAsHeader()
                .withIgnoreEmptyLines()
                .parse(in);
        for (CSVRecord record : records) {
            System.out.println(record.get("ubicacion") + " - " + record.get("entidad") + " - " + record.get("codigoAlfanumerico") + " - " + record.get("speakerType"));
            if (record.get("proveedor").isEmpty() || record.get("entidad").isEmpty() || record.get("codigoAlfanumerico").isEmpty() || record.get("centroDeCosto").isEmpty() || record.get("ubicacion").isEmpty()){
                badRecords.add(record);
            } else {
                try {
                    getPage(ControlsGestar.class).searchInLookUpBoxObjectDobleClick("html_provider", record.get("proveedor"));
                    getPage(ControlsGestar.class).searchInLookUpBoxObjectByTypeDobleClick("html_entitiesMaster", record.get("entidad"),record.get("entidad"));
                    getPage(ControlsGestar.class).inputToTextBox("attr_type", record.get("Type"));
                    getPage(ControlsGestar.class).inputToTextBox("attr_fw_version", record.get("FWVersion"));
                    getPage(ControlsGestar.class).inputToTextBox("attr_hw_version", record.get("HWVersion"));
                    getPage(ControlsGestar.class).inputToTextBox("attr_misc", record.get("misc"));
                    getPage(ControlsGestar.class).inputToTextBox("nroSerie", record.get("codigoAlfanumerico"));
                    getPage(ControlsGestar.class).selectOption("estado", "Reservado");
                    getPage(ControlsGestar.class).selectOption("propertyRelationship", "Prestamo");
                    getPage(ControlsGestar.class).selectOption("empPropietaria", "WindRiver");
                    getPage(ControlsGestar.class).searchInLookUpBoxObjectDobleClick("html_cco", record.get("centroDeCosto"));
                    getPage(ControlsGestar.class).searchInLookUpBoxLocation("location", record.get("ubicacion"));
                    getPage(ControlsGestar.class).htmlRawElementClick("html_entitiesCustoms", "#add");
                } catch (AgentException e){
                    badRecords.add(record);
                    getPage(ControlsGestar.class).changeFocusToParent();
                }

            }
            if (!badRecords.isEmpty()){
                FileWriter out = new FileWriter("benches_not_imported_"+nameAux+".csv");
                try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT
                        .withHeader(HEADERS)
                        .withDelimiter(';'))) {
                    for(CSVRecord badRecord : badRecords){
                        printer.printRecord(badRecord);
                    }
                }
            }
        }

    }

}
