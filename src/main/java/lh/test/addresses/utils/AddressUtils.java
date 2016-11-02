package lh.test.addresses.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressUtils {
	@Autowired
	public PropertiesLoader propLoad;
	
	public static String formatPc(String pc) {
		// remove whitespace
		String formatted = pc.replaceAll(" ", "").replaceAll("%20", "").toUpperCase();
		return formatted;
	}

	public boolean checkValid(String pc) {
		// implement regular expression matching
		String regex = propLoad.retrieve(PropertiesReader.PCREGEX_KEY);
		boolean match = pc.matches(regex);
		if(match){
			return true;
		}
		else{
			return false;
		}
	}
	
}
