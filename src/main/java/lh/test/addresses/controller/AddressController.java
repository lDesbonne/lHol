package lh.test.addresses.controller;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import lh.test.addresses.connection.DataCall;
import lh.test.addresses.data.DataObject;
import lh.test.addresses.error.DataError;
import lh.test.addresses.logic.AddressProcessor;

@RestController
public class AddressController {
	private static Logger LOG = Logger.getLogger(AddressController.class);
	
	@Autowired
	DataCall dataCall;
	
	@RequestMapping(value="/address/{pc}",method=RequestMethod.GET)
	String getAddress(@PathVariable("pc") String postcode){
		LOG.debug("address endpoint reached with postcode parameter: "+postcode);
		DataObject[] addr = dataCall.retrieve(postcode);
		
		if(addr != null){
			try{
				return AddressProcessor.processStreetName(addr);
			}catch(JSONException j){
				LOG.error("Failed to eparse data retrieved from call",j);
				DataError error = new DataError();
				error.setMessage("Failed to parse response data, see logs for more details");
				return error.toString();
			}
		}else{
			DataError error = new DataError();
			error.setMessage("Issue completing request, please see logs");
			return error.toString();
		}
		
	}

}
