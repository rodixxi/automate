package com.harriague.automate.core.report;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.harriague.automate.core.conf.Constants;

public class ScreenshotReport {
    private static final String REPORT_FILE_NAME = "index.html";
    private static final String REPORT_TEMPLATE_FILE = "/reportsTemplates/screenshot_report.html";
    protected static final Logger log = Logger.getLogger("ScreenshotReport");

    public static void addScreenshot(File screenshotFile) {
        File reportFile = getReportFile();

        BufferedReader file;
        if (!reportFile.exists())
            return;
        try {
            file = new BufferedReader(new FileReader(reportFile));

            String line, input = "";
            while ((line = file.readLine()) != null) {
                if (line.contains("screenshots")) {
                    line += "\n";
                    line += "<a href=\"" + screenshotFile.getName() + "\" target=\"_blank\">"
                            + screenshotFile.getName() + "</a><br>\n";
                }
                input += line + "\n";
            }

            FileOutputStream outPut = new FileOutputStream(reportFile.getAbsolutePath());
            outPut.write(input.getBytes());
            outPut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static File getReportFile() {
        File file = new File(Constants.SCREENSHOT_FOLDER_PATH + "/" + REPORT_FILE_NAME);
        if (!file.exists()) {
            try {
                InputStream is =
                        ScreenshotReport.class.getClass().getResourceAsStream(REPORT_TEMPLATE_FILE);
                byte[] data = IOUtils.toByteArray(is);
                org.apache.commons.io.FileUtils.writeByteArrayToFile(file, data);
            } catch (Exception e) {
                log.warn("Problem creating screenshot report file!!");
                log.warn("the template probably wasn't there: " + REPORT_TEMPLATE_FILE);
            }
        }
        return file;
    }
}
