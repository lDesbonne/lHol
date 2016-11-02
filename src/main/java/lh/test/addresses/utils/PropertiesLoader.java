package lh.test.addresses.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Loads properties from properties file
 * @author Lennon
 *
 */
@Component
public class PropertiesLoader {
	final static Logger LOG = Logger.getLogger(PropertiesLoader.class);
	
	private Properties prop;
	
	public PropertiesLoader(){
		loadProperties();
	}
	
	private void loadProperties(){
		if(this.prop == null){
			this.prop = new Properties();
			InputStream in  = ClassLoader.getSystemClassLoader().getResourceAsStream("addresses.properties");
			
			try{
				this.prop.load(in);
				LOG.info("Successfully read properties file");
			}catch(IOException io){
				LOG.error("Unable to read properties file",io);
			}
		}
	}
	
	public String retrieve(String key){
		//loadProperties();
		return this.prop.getProperty(key);
	}

}
