package com.harriague.automate.core.conf;

public enum DeviceType {

	NO_TYPE(""), ANDROID("Android"), WINDOWS_WEB("windows.web");

	private String packageName;

	DeviceType(String packageName) {
		this.packageName = packageName;
	}

	public String getPackage() {

		return this.packageName;
	}

	public static DeviceType getDeviceTypeByPackage(String packageName) {
		for (int i = 0; i < DeviceType.values().length; i++) {
			if(DeviceType.values()[i].getPackage().equals(packageName)) {
				return DeviceType.values()[i];
			}
		}
		return null;
	}

}
