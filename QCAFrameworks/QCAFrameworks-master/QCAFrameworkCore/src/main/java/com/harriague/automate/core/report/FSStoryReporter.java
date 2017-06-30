package com.harriague.automate.core.report;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.model.GivenStories;
import org.jbehave.core.model.Meta;
import org.jbehave.core.model.Narrative;
import org.jbehave.core.model.OutcomesTable;
import org.jbehave.core.model.Scenario;
import org.jbehave.core.model.Story;
import org.jbehave.core.model.StoryDuration;
import org.jbehave.core.reporters.StoryReporter;

import com.harriague.automate.core.conf.PropertiesKeys;
import com.harriague.automate.core.exceptions.PropertyException;
import com.harriague.automate.core.utils.ReadProperty;

public class FSStoryReporter implements StoryReporter {

    protected StoryReporter m_delegate;
    protected Logger m_log = Logger.getLogger(getClass().getName());
    private XSSFWorkbook m_workbook;
    protected Sheet m_sheet;
    protected int m_HSSFIndex;
    protected Font m_headerFont;
    protected Row m_rowhead;
    protected boolean m_success;
    final protected String SHEET_NAME = "Automation Report";
    final protected String SHEET_FOLDER = "target/site/Automate";
    protected String m_documentsName;
    protected FileInputStream m_inputFile;
    protected Map<String, Integer> m_headers;
    protected static boolean s_writtable;
    protected final ArrayList<String> NOT_CUSTOM_FIELDS = new ArrayList<String>() {
        protected static final long serialVersionUID = -5537260981649557805L;
        {
            add("story");
            add("status");
            add("failed step");
            add("comment");
        }
    };

    protected enum commonFields {
        story, status, failed_step, comment;
    }
    protected enum SupportedStatus {
        FAIL, PASS
    }

    public static int s_executedTests = 0;
    public static final int RESET_LIMIT = 15;

    public FSStoryReporter(StoryReporter delegate) {
        m_delegate = delegate;
        m_HSSFIndex = 0;
        m_headers = new HashMap<String, Integer>();
        final String[] USER_HEADERS = getUserHeaders();
        final int len = USER_HEADERS.length;
        int x = 0;
        try {
            init();
        } catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
            m_log.error(e.getMessage());
        }
        while (x < len) {
            m_headers.put(USER_HEADERS[x].toLowerCase(), x);
            x++;
        }
    }

    public void storyNotAllowed(Story story, String filter) {
        m_delegate.storyNotAllowed(story, filter);

        setCell(commonFields.status.toString(), SupportedStatus.FAIL.toString());
        setCell(commonFields.comment.toString(), "Story not allowed");
        m_log.error("Story not allowed, filter: " + filter);
        m_success = false;
    }

    public void storyCancelled(Story story, StoryDuration storyDuration) {
        m_delegate.storyCancelled(story, storyDuration);
        setCell(commonFields.status.toString(), SupportedStatus.FAIL.toString());
        setCell(commonFields.comment.toString(), "Story cancelled");
        m_log.error("Story cancelled after " + storyDuration.getDurationInSecs() + " seconds");
        m_success = false;
    }

    public void beforeStory(Story story, boolean givenStory) {
        m_log.info("Running custom before story");
        final String name = story.getName();

        if (name != null && !name.equals("BeforeStories") && !name.equals("AfterStories")) {
            m_headerFont = m_workbook.createFont();
            m_rowhead = m_sheet.createRow(m_HSSFIndex);
            m_headerFont.setBold(false);
            s_writtable = true;
            String value = null;
            for (String key : m_headers.keySet()) {
                if (!NOT_CUSTOM_FIELDS.contains(key)) {
                    try {
                        value = getStoryValue(story, key);
                    } catch (IOException e) {
                        m_log.error(e.getMessage());
                    }
                    if (value != null) {
                        setCell(key, value);
                        value = null;
                    } else {
                        m_log.error("There was no '" + key + "' on story " + story.getName());
                    }
                }
            }
        } else {
            s_writtable = false;
        }


        s_executedTests++;
        m_success = true;
        setCell("story", story.getName());
        m_delegate.beforeStory(story, givenStory);
    }

    public void afterStory(boolean givenStory) {
        m_delegate.afterStory(givenStory);

        m_HSSFIndex++;
        if (m_success) {
            setCell(commonFields.status.toString(), SupportedStatus.PASS.toString());
        }
        writeDocument();
    }

    public void narrative(Narrative narrative) {
        m_delegate.narrative(narrative);
    }

    public void scenarioNotAllowed(Scenario scenario, String filter) {
        m_delegate.scenarioNotAllowed(scenario, filter);
        m_log.error("Scenario not allowed, filter: " + filter);
    }

    public void beforeScenario(String scenarioTitle) {
        m_delegate.beforeScenario(scenarioTitle);
    }

    public void scenarioMeta(Meta meta) {
        m_delegate.scenarioMeta(meta);
    }

    public void afterScenario() {
        m_delegate.afterScenario();
    }

    public void givenStories(GivenStories givenStories) {
        m_delegate.givenStories(givenStories);
    }

    public void givenStories(List<String> storyPaths) {
        m_delegate.givenStories(storyPaths);
    }

    public void beforeExamples(List<String> steps, ExamplesTable table) {
        m_delegate.beforeExamples(steps, table);
    }

    public void example(Map<String, String> tableRow) {
        m_delegate.example(tableRow);
    }

    public void afterExamples() {
        m_delegate.afterExamples();
    }

    public void beforeStep(String step) {
        m_delegate.beforeStep(step);
        m_log.info(step);
    }

    public void successful(String step) {
        m_delegate.successful(step);
    }

    public void ignorable(String step) {
        m_delegate.ignorable(step);
        m_log.info("Ignorable step: " + step);
    }

    public void pending(String step) {
        m_delegate.pending(step);
        m_success = false;
        setCell(commonFields.status.toString(), SupportedStatus.FAIL.toString());
        setCell(commonFields.comment.toString(), "pending step: " + step);
        setCell(commonFields.failed_step.toString(), step);
        m_log.info("Pending step: " + step);
    }

    public void notPerformed(String step) {
        m_delegate.notPerformed(step);
        m_log.warn("step not performed: " + step);
    }

    public void failed(String step, Throwable cause) {
        m_delegate.failed(step, cause);

        if (!step.contains("afterScenarioFailure")) {
            setCell(commonFields.status.toString(), SupportedStatus.FAIL.toString());
            setCell(commonFields.comment.toString(), cause.getCause().getMessage());
            setCell(commonFields.failed_step.toString(), step);
        }
        m_log.error("Fail step: " + step);
        m_log.error("Error message: " + cause.getMessage());
        m_log.error("Error stack trace: \n///////////////////////////////////////////", cause);
        m_log.error("///////////////////////////////////////////");
        m_success = false;
    }

    public void failedOutcomes(String step, OutcomesTable table) {
        m_delegate.failedOutcomes(step, table);
    }

    public void restarted(String step, Throwable cause) {
        m_delegate.restarted(step, cause);
        m_log.warn("Step restarted: " + step);
    }

    public void dryRun() {
        m_delegate.dryRun();
    }

    public void pendingMethods(List<String> methods) {
        m_delegate.pendingMethods(methods);
    }

    protected void init() throws EncryptedDocumentException, InvalidFormatException, IOException {
        File folder = new File(SHEET_FOLDER);
        m_workbook = null;
        if (folder.exists()) {
            File[] listOfFiles = folder.listFiles();
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    final String[] filename = file.getName().split("\\.(?=[^\\.]+$)"); // split
                                                                                       // filename
                                                                                       // from it's
                                                                                       // extension
                    if (filename[1].equalsIgnoreCase("xlsx")) {
                        m_documentsName = file.getPath();
                        m_inputFile = new FileInputStream(new File(m_documentsName));
                        m_workbook = new XSSFWorkbook(m_inputFile);
                        m_sheet = m_workbook.getSheetAt(0);
                        if(m_sheet == null) {
                            m_workbook.createSheet(SHEET_NAME);
                        }else {
                            m_workbook.setSheetName(0, SHEET_NAME);
                        }
                        m_sheet = m_workbook.getSheet(SHEET_NAME);
                        m_HSSFIndex = m_sheet.getLastRowNum() + 1;
                        break;
                    }
                }
            }
        }
        if (m_workbook == null) {
            m_documentsName = SHEET_FOLDER + "/Automation Report "
                    + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xlsx";
            m_HSSFIndex = 0;
            m_workbook = new XSSFWorkbook();
            m_sheet = m_workbook.createSheet(SHEET_NAME);
            XSSFFont headerFont = m_workbook.createFont();
            Row m_rowhead = m_sheet.createRow(m_HSSFIndex);
            headerFont.setBold(true);
            CellStyle style = m_workbook.createCellStyle();
            Cell currentCell = null;
            final String[] user_headers = getUserHeaders();
            int x = 0;
            final int len = user_headers.length;
            style.setFont(headerFont);
            style.setBorderBottom(BorderStyle.MEDIUM);
            style.setBorderTop(BorderStyle.MEDIUM);
            style.setBorderRight(BorderStyle.MEDIUM);
            style.setBorderLeft(BorderStyle.MEDIUM);
            while (x < len) {
                currentCell = m_rowhead.createCell(x);
                currentCell.setCellValue(user_headers[x]);
                x++;
                currentCell.setCellStyle(style);
            }
            File file = new File(m_documentsName);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        }
    }

    protected void writeDocument() {
        FileOutputStream fileOut;
        if (m_inputFile != null) {
            try {
                m_inputFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            m_inputFile = null;
        }
        try {
            fileOut = new FileOutputStream(m_documentsName);
            m_workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Cell setCell(String key, String value) {
        final String parsedKey = key.replace("_", " ");
        if (s_writtable && m_headers.containsKey(parsedKey)) {
            Cell currentCell = m_rowhead.createCell(m_headers.get(parsedKey));
            CellStyle style = m_rowhead.getSheet().getWorkbook().createCellStyle();
            currentCell.setCellValue(value);
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderTop(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            currentCell.setCellStyle(style);
            return currentCell;
        }
        return null;
    }

    protected String[] getUserHeaders() {
        String property = null;
        try {
            property = ReadProperty.getProperty(PropertiesKeys.EXCEL_REPORT_HEADERS);
        } catch (PropertyException e) {
            m_log.debug("User not using report.headers property");
        }
        return property == null ? NOT_CUSTOM_FIELDS.toArray(new String[NOT_CUSTOM_FIELDS.size()])
                : property.split(",");
    }

    protected String getStoryValue(Story story, String key) throws IOException {
        BufferedReader file = new BufferedReader(
                new FileReader(story.getPath().replaceFirst(".", "src/main/stories")));
        String line = null;
        String lowercaseKey = key.toLowerCase();
        final String endOfMetaData = "scenario:";
        final int LEN = key.length();
        final int BEGGINING = 0;
        String res = null;
        String buff = "";
        while ((line = file.readLine()) != null) {
            if (line.length() < LEN)
                continue;
            buff = line.substring(BEGGINING, LEN).toLowerCase();
            if (buff.equals(lowercaseKey)) {
                res = line.substring(lowercaseKey.length() + 1);
                break;
            }
            if (line.contains(endOfMetaData))
                break;
        }
        file.close();
        if(res == null) {
            m_log.warn(key+" was not found on "+story.getPath().replaceFirst(".", "src/main/stories"));
        }
        return res;
    }

    protected String getStoryMetaData(Story story, String key) throws IOException {
        BufferedReader file = new BufferedReader(
                new FileReader(story.getPath().replaceFirst(".", "src/main/stories")));
        String line = null;
        String lowercaseKey = key.toLowerCase();
        final String endOfMetaData = "Given ";
        final int BEGGINING = 1;
        final int LEN = key.length() + BEGGINING;
        String res = null;
        String buff = "";
        while ((line = file.readLine()) != null) {
            if (line.length() < LEN)
                continue;
            buff = line.substring(BEGGINING, LEN).toLowerCase();
            if (buff.equals(lowercaseKey)) {
                res = line.substring(lowercaseKey.length() + 2);
                break;
            }
            if (line.indexOf(endOfMetaData) == 0)
                break;
        }
        file.close();
        if(res == null) {
            m_log.warn(key+" was not found on "+story.getPath().replaceFirst(".", "src/main/stories"));
        }
        return res;
    }
}
