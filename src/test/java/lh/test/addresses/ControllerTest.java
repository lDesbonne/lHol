package lh.test.addresses;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import lh.test.addresses.connection.DataCall;
import lh.test.addresses.controller.AddressController;
import lh.test.addresses.data.Address;
import lh.test.addresses.data.DataObject;

import static org.mockito.BDDMockito.*;

/**
 * Provides integration tests
 * @author Lennon
 *
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes=AppContext.class)
@WebMvcTest(AddressController.class)
public class ControllerTest {
	public static Logger LOG = Logger.getLogger(ControllerTest.class);
	
//	@Autowired
//	private RestTemplate restTemplate;
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private DataCall dataService;
	
	@Test
	public void controllerTest() throws Exception{
		List<Address> addList = new ArrayList<Address>();
		Address address = new Address();
		address.setSummaryline("Hammersmith Grove, London W6 0LG");
		addList.add(address);
		given(this.dataService.retrieve("W60LG")).willReturn(addList.toArray(new DataObject[0]));
		this.mvc.perform(get("/address/W60LG")).andExpect(content().string("[{\"summaryline\":\"Hammersmith Grove, London W6 0LG\"}]"));
		
	}

}
