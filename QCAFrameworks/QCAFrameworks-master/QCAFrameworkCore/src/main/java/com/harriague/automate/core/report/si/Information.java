/**
 * 
 */
package com.harriague.automate.core.report.si;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.harriague.automate.core.utils.WinRegistry;


/**
 * @author ssacevex
 *
 */
public class Information {
	
	private static final String DEVELOPER = "http://54.172.129.190"; 
	private static final String PARTNER="https://mfs-partner.mcafee.com";
	private static final String PRODUCTION="http://54.172.243.161";

	public String executeCommand(String command) {
		 
		StringBuffer output = new StringBuffer();
 
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = 
                            new BufferedReader(new InputStreamReader(p.getInputStream()));
 
                        String line = "";			
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output.toString();
	}

	public static String endPoint(String src,String key,String token){
		String val="";
		String aux;
		try {
			BufferedReader bf = new BufferedReader(new FileReader(src));
			aux=bf.readLine();
			while(aux!=null){
				if(aux.startsWith(key)){
					int i = aux.indexOf(token)+1;
					val=aux.substring(i);
					if(PARTNER.equals(val)) {
						val="Partner (" + PARTNER + ")";
					}
					if(DEVELOPER.equals(val)) {
						val="Developer (" + DEVELOPER + ")";
					}
					if(PRODUCTION.equals(val)) {
						val="Production (" + PRODUCTION + ")";
					}
					aux=null;
				} else {
					aux=bf.readLine();
				}
			}
			bf.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return val;
	}	
	
	public static String getHKLM(String key, String x32, String x64) {
		String info = null;
		try {
			info = WinRegistry.readString(WinRegistry.HKEY_LOCAL_MACHINE, x64, key);
			info = (info == null) ? WinRegistry.readString(
					WinRegistry.HKEY_LOCAL_MACHINE, x32, key) : info;
		} catch (Exception e) {
		}
		return info;
	}
}
