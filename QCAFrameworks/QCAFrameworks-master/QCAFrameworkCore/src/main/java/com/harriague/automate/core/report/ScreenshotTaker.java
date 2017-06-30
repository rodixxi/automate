package com.harriague.automate.core.report;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import com.harriague.automate.core.agent.Agent;
import com.harriague.automate.core.app.Application;
import com.harriague.automate.core.device.Device;
import com.harriague.automate.core.device.impl.AndroidDeviceImpl;
import com.harriague.automate.core.utils.FileManager;

public class ScreenshotTaker {

	public static File takeScreenShot(String testName, Device device, Application application) {
		DateFormat dateFormat = new SimpleDateFormat("MM_dd_HH_mm_ss");
		String fileName=testName + "_" + dateFormat.format(new Date().getTime());
		File screenshotTaken = null;
		try {
			if(device instanceof AndroidDeviceImpl) {
				Agent agent = device.getAgent();
				File srcFiler= agent.getScreenshotAs(OutputType.FILE);
				String folder = com.harriague.automate.core.conf.Constants.SCREENSHOT_FOLDER_PATH;
				try {
					FileUtils.copyFile(srcFiler, new File(folder + "/" + fileName+".png"));
				}catch(Exception e){
				}
			} else {

				Toolkit tool = Toolkit.getDefaultToolkit();
				Dimension d = tool.getScreenSize();
				Rectangle rect = new Rectangle(d);
				Robot robot;

				robot = new Robot();

				String folder = com.harriague.automate.core.conf.Constants.SCREENSHOT_FOLDER_PATH;

				FileManager.ensureDirectory(folder);
				BufferedImage img = robot.createScreenCapture(rect);
				screenshotTaken = new File(folder + "/" + fileName+".png");
				ImageIO.write(img, "png", screenshotTaken);
			}
		} catch (AWTException | IOException e) {
			e.printStackTrace();
		}
		return screenshotTaken;
	}
}
