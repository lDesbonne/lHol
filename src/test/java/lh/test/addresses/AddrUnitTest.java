package lh.test.addresses;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lh.test.addresses.utils.AddressUtils;
import lh.test.addresses.utils.PropertiesLoader;
import lh.test.addresses.utils.PropertiesReader;

/**
 * Unit tests 
 * @author Lennon
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=AppContext.class)
public class AddrUnitTest {
	@Autowired
	PropertiesLoader propRead;
	
	@Autowired
	AddressUtils utils;
	
	@Before
	public void setUp(){
		//propRead = Mockito.mock(PropertiesReader.class);
		//propRead = new PropertiesReader();
	}
	
	@Test
	public void testRead() throws Exception{
		String value = propRead.retrieve(PropertiesReader.URL_KEY);
		assertTrue(value.contains("http"));
	}
	
	@Test
	public void testPcValidation() throws Exception{
		String invalid = "SEW4 5TY";
		String valid = "W6 0LG";
		
		assertTrue(utils.checkValid(valid));
		assertFalse(utils.checkValid(invalid));
	}
}
