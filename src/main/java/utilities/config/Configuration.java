package utilities.config;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Singleton class for working with configuration property file.
 */
public class Configuration {

	private static final String configFilePath = "src/main/resources/config/config.properties";
	
	private static Properties configProp;

	/**
	 * This method is used to load the configuration property file.
	 */
	private static synchronized void loadEnvironmentConfiguration() {
		try {
			configProp = new Properties();
			configProp.load(new FileInputStream(configFilePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to get the value of the required property from configuration file.
	 * 
	 * @param key - Key of the property
	 * @return Value of the property
	 */
	public static synchronized String getProperty(String key) {
		if(configProp == null)
			loadEnvironmentConfiguration();
		return configProp.getProperty(key);
	}

	/**
	 * This method is used to set a property in the configuration file.
	 * 
	 * @param key - Key of the property to set
	 * @param value - Value of the property to set
	 */
	public static synchronized void setProperty(String key, String value) {
		if(configProp == null)
			loadEnvironmentConfiguration();
		configProp.setProperty(key, value);
	}
}