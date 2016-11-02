package lh.test.addresses.logic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.JSONException;

import lh.test.addresses.data.DataObject;

/**
 * Business logic 
 * @author Lennon
 *
 */
public class AddressProcessor {
	private static Logger LOG = Logger.getLogger(AddressProcessor.class);
	
	public static String processStreetName(DataObject[] data) throws JSONException{
		List<DataObject> result = Arrays.asList(data);
		Set<String> streets = new HashSet<String>();
		Set<String> name = new HashSet<String>();
		LOG.debug("Number of results retrieved: "+result.size());
		
		result.forEach(address -> {
				streets.add(address.toMap().get("Street"));
			
			if(address.toMap().containsKey("Name")){
					name.add(address.toMap().get("Name"));
				
			}
			
		});
		
		LOG.debug("Number of unique streets: "+streets.size());
		LOG.debug("Number of unique building names: "+name.size());
		StringBuilder resp = new StringBuilder();
		resp.append("{\"Street\":\""+ formatSetData(streets)+"\"");
		
		if(name.size() > 0){
			resp.append(",\"Name\":\""+ formatSetData(name)+"\"}");
		}else{
			resp.append("}");
		}
		return resp.toString();
	}
	
	public static String formatSetData(Set<String> set){
		StringBuilder buildResp = new StringBuilder();
		
		set.forEach(str -> buildResp.append(str+"|"));
		
		buildResp.deleteCharAt(buildResp.lastIndexOf("|"));
		return buildResp.toString();
	}
}
