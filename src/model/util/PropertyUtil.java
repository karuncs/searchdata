package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public final class PropertyUtil {
	
	private static String PROPERTY_FILE = "resource.properties";
	
	/**
	 * @param key
	 * @return
	 */
	public String getPropertyValue(String key){
		Properties defaultProps = new Properties();
		String value =  null;
		try {
			InputStream inputStream = this.getClass().getClassLoader()  
		                 .getResourceAsStream(PropertyUtil.PROPERTY_FILE); 
			defaultProps.load(inputStream);
		value = defaultProps.getProperty(key);
		inputStream.close();
		} catch (FileNotFoundException e) {
			System.err.println("File resource.property not found or key property unexistent");			
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("File resource.property not found or key property unexistent");			
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("File resource.property not found or key property unexistent");
			e.printStackTrace();
		}	
		return value;

	  }
	


	
	/**
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static Properties readProperties(File file) throws IOException {
		Properties properties = new Properties();
		FileInputStream is = new FileInputStream(file);
		properties.load(is);
		return properties;
	 }
	
}
