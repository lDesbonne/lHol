package lh.test.addresses;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import lh.test.addresses.data.Address;
import static org.assertj.core.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Paths;

@RunWith(SpringRunner.class)
@JsonTest
@ContextConfiguration(classes = AppContext.class)
public class SerializationTest {
	private static Logger LOG = Logger.getLogger(SerializationTest.class);

	private Address addressFull;
	private Address partAddress;

	@Autowired
	private JacksonTester<Address> json;

	@Before
	public void setup() {
		addressFull = new Address();
		addressFull.setSummaryline("summary line data");
		addressFull.setOrganisation("Big Ideas");
		addressFull.setBuildingname("Freedom Palace");
		addressFull.setPremise("premise");
		addressFull.setStreet("Yellow Brick Road");
		addressFull.setDependentlocality("Borough");
		addressFull.setPosttown("Boroughton");
		addressFull.setCounty("BoroughShire");
		addressFull.setPostcode("BB45X");

		partAddress = new Address();
		partAddress.setPosttown("Little Metropolis");
		partAddress.setPostcode("MTR01S");
	}

	@Test
	public void testConversion() throws Exception {
		LOG.debug("Checking if full object is serializable to json");
		assertThat(this.json.write(addressFull))
				.isEqualToJson(ClassLoader.getSystemResourceAsStream("fullAddress.json"));
		LOG.debug("Checking if partially full object is serializable to json");
		assertThat(this.json.write(partAddress))
				.isEqualToJson(ClassLoader.getSystemResourceAsStream("partialAddress.json"));

	}
	
	@Test
	public void testGeneration() throws Exception{
		StringBuilder fullBuild = new StringBuilder();
		Files.lines(Paths.get(ClassLoader.getSystemResource("fullAddress.json").toURI()))
		.forEach(line -> fullBuild.append(line));
		String fullJson = fullBuild.toString();
		
		StringBuilder partBuild = new StringBuilder();
		Files.lines(Paths.get(ClassLoader.getSystemResource("partialAddress.json").toURI()))
		.forEach(line -> partBuild.append(line));
		String partJson = partBuild.toString();
		
		LOG.debug("Checking if json string can be parsed to Address object");
		assertThat(this.json.parse(fullJson).getObject().getCounty()).isEqualTo(addressFull.getCounty());
		assertThat(this.json.parse(partJson).getObject().getPostcode()).isEqualTo(partAddress.getPostcode());
		
	}


}
