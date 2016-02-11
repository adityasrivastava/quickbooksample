package com.project.quickbook.oauth.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OAuthUtils {
	
	Properties prop = new Properties();
	public static final String PROP_FILE = "application.properties";
	
	public Properties readProperties()
	{
		try {
			Thread currentThread = Thread.currentThread();
			ClassLoader contextClassLoader = currentThread.getContextClassLoader();
			InputStream propertiesStream = contextClassLoader.getResourceAsStream(PROP_FILE);
			if (propertiesStream != null) {
				prop.load(propertiesStream);
				 System.out.println("Properties Loaded successfully");
				} else {
				  System.out.println("Properties File Not Found!");
				}
			return prop;
			} catch (IOException ex) {
				ex.printStackTrace(); 
				return prop; 
			}
	}

}
