package lh.test.addresses.connection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lh.test.addresses.connection.template.DataRequest;
import lh.test.addresses.data.Address;
import lh.test.addresses.data.DataObject;
import lh.test.addresses.utils.AddressUtils;
import lh.test.addresses.utils.DataFormat;
import lh.test.addresses.utils.PropertiesLoader;
import lh.test.addresses.utils.PropertiesReader;

/**
 * Retrieves data from external address api's
 * 
 * @author Lennon
 *
 */
@Component
public class DataCall implements DataRequest {
	private static Logger LOG = Logger.getLogger(DataCall.class);

	@Autowired
	PropertiesLoader propReader;

	@Autowired
	RestTemplate restTemplate;

	// @Autowired
	// AddressUtils util;

	@Override
	public DataObject[] retrieve(Object postcode) {
		// TODO postcode validation requires further refinement
		// if(util.checkValid((String)postcode)){

		String url = propReader.retrieve(PropertiesReader.URL_KEY)
				.replace("{apikey}", propReader.retrieve(PropertiesReader.AK_KEY))
				.replace("{pc}", AddressUtils.formatPc((String) postcode))
				.replace("{format}", DataFormat.json.toString());

		LOG.info("Creating request to the following url: " + url);
		try {
			Address[] data = restTemplate.getForObject(url, Address[].class);
			return data;
		} catch (Exception e) {
			LOG.error("Issue retrieving data", e);
			return null;
		}

		// }else{
		// return null;
		// }

	}

}
