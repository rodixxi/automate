package com.harriague.automate.core.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

public class FileManager {

	private static final Logger log = Logger.getLogger(FileManager.class.getName());

	/**
	 * Creates a new file
	 *
	 * @param filePath String The path of the file
	 * @param fileName String The name of the file
	 * @return {@link File} the file created
	 */
	public static File createFile(String filePath, String fileName) {
		String file="";
		if(filePath==null) {
			file=fileName;
		} else {
			file=filePath+fileName;
		}

		File createdFile=new File(file);

		try {
			createdFile.createNewFile();
		} catch (IOException e) {
			log.info("Error creating file " + file);
			e.printStackTrace();
		}
		return createdFile;
	}

	public static File createFile(String fileName) {
		return createFile(null,fileName);
	}

	public static File ensureDirectory(String directoryPath) {
		File dir = new File(directoryPath);
		if (!dir.exists()) {
			log.info("Creating directory: " + directoryPath);
			boolean created = dir.mkdirs();
			if (!created) {
				log.error("Couldn't create the directory");
			}
		}
		return dir;
	}

	public static void saveFile(File srcFile, File file) throws IOException {
		org.apache.commons.io.FileUtils.copyFile(srcFile, file);
	}

	/**
	 * Writes a String content in a file
	 *
	 * @param file {@File file} to write in
	 * @param content String the content
	 */
	public static void writeFile(File file, String content) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(file, "UTF-8");
			writer.write(content);
			log.info("Writing file " + file.getName());
			writer.flush();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			log.warn("Error writing file "+file.getAbsolutePath());
			e.printStackTrace();
		}
	}
}
