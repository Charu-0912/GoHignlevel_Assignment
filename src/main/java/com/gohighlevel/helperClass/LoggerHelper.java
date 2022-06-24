package com.gohighlevel.helperClass;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerHelper {

	
	private static boolean root = false;

	@SuppressWarnings("rawtypes")
	public static Logger getLogger(Class clas) {
		if (root) {
			return Logger.getLogger(clas);
		} else {
			root = true;
			PropertyConfigurator.configure("./resources/log4j.properties");
			return Logger.getLogger(clas);
		}
	}
}
