/**
 * 
 */
package com.harriague.automate.core.report.si;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.net.InetAddress;


/**
 *
 */

public class OSInformation {

	
	public static String[] getSpecification() {
		String[] values = new String[4];
		try {
		values[0] = System.getenv("COMPUTERNAME");
		values[1] = InetAddress.getLocalHost().getHostAddress();
		values[2] = Information.getHKLM("ProductName", "", "SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion");
		values[3] = ((OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getArch().replaceAll("amd64", "64 bits");
		} catch (Exception e) {
		}
		return values;
	}

}

