package com.harriague.automate.core.report;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.harriague.automate.core.conf.Constants;
import com.harriague.automate.core.report.si.OSInformation;


/**
 * Create log report
 */
public class LogReport {
	private static final String REPORT_FILE_NAME="index.html";
	private static final String REPORT_TEMPLATE_FILE="/reportsTemplates/log_report.html";
	protected static final Logger log = Logger.getLogger("LogReport");
	protected static BufferedReader report = null;
	protected static File reportFile = null;
	protected static String[] systemInformationValues = null;

	/**
	 * Add a new report link
	 * @param reportName
	 * @param group
	 * @throws Exception
	 */
	public static void addLog(List<String> logs, String group) throws Exception {
		loadReportFile();
		for(String log : logs) {
			readFile(log , group, true);
			loadLogFile(log + ".txt");
		}
		if(report != null)
		    report.close();
	}

	public static void createLogReport() throws Exception {
		loadReportFile();
	}

	/**
	 * Get report file
	 * @return File report file
	 * @throws FileNotFoundException
	 */
	private static void loadReportFile() throws FileNotFoundException {
		File file=new File(Constants.LOG_FOLDER_PATH +"/"+REPORT_FILE_NAME);
		if(!file.exists()) {
			try {
				InputStream is = ScreenshotReport.class.getClass().getResourceAsStream(REPORT_TEMPLATE_FILE);
				if(is == null) {
				    log.warn("There is no report template at "+REPORT_TEMPLATE_FILE);
				}
				byte[] data = IOUtils.toByteArray(is);
				org.apache.commons.io.FileUtils.writeByteArrayToFile(file, data);
				report = new BufferedReader(new FileReader(file));
				reportFile = file;
				compliteSystemInformation();
				reportFile = file;
		        report = new BufferedReader(new FileReader(file));
			} catch (Exception e) {
				log.warn("Problem creating system information report file!!");
//				e.printStackTrace();
			}
		}
	}

	private static void compliteSystemInformation() {
		systemInformationValues = OSInformation.getSpecification();
		readFile(null, "%s", false);
	}

	private static void readFile(String reportName, String group, boolean addRow) {
		try {
			int i = 0;
			String line, input="";
			while((line=report.readLine())!=null) {
				if(line.contains(group)) {
					if(addRow) {
						line+="\n";
						line+="<div>" + reportName + "<a href=\""+ reportName +".html\"> Navegate</a><a href=\""+ reportName +".txt\" download=\"autoMATE - " + reportName + ".txt\"> Download</a></div><br>\n";
					} else {
						line = String.format(line, systemInformationValues[i]);
						i++;
					}
				}
				input+=line +"\n";
			}
			FileOutputStream outPut=new FileOutputStream(reportFile.getAbsolutePath());
			outPut.write(input.getBytes());
			outPut.close();
		} catch (Exception e) {
		}
	}

	/**
	 * Get report file
	 * @return File report file
	 * @throws FileNotFoundException
	 */
	private static void loadLogFile(String logFile) throws FileNotFoundException {
		File file=new File(Constants.LOG_FOLDER_PATH +"/"+logFile);
		BufferedReader log = null;
		if(file.exists()) {
			try {
				InputStream is = new FileInputStream(file);
				byte[] data = IOUtils.toByteArray(is);
				org.apache.commons.io.FileUtils.writeByteArrayToFile(file, data);
				log = new BufferedReader(new FileReader(file));

				String line, input="";
				input = "<html><a href=\"javascript:history.go(-1);\" > Back </a></html><br/>";
				while((line=log.readLine())!=null) {
					input+=line +"<br/>";
				}
				FileOutputStream outPut=new FileOutputStream(file.getAbsolutePath().replaceAll(".txt", ".html"));
				outPut.write(input.getBytes());
				outPut.close();
				log.close();
			} catch (Exception e) {
//				log.warn("Problem creating system information report file!!");
				e.printStackTrace();
			}
		}
	}
}
