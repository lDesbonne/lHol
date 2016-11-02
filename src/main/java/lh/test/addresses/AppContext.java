package lh.test.addresses;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import lh.test.addresses.error.DataError;

/**
 * Configures the application context for the application
 *
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class AppContext {
	private static Logger LOG = Logger.getLogger(AppContext.class);
	
	@Bean
	public RestTemplate restTemplate(){
		//TODO configure proxy settings here if needed
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DataError());
		return new RestTemplate();
	}
	
	public static void main(String[] args){
		LOG.debug("Initializing address finder API");
		SpringApplication.run(AppContext.class, args);
	}
}
