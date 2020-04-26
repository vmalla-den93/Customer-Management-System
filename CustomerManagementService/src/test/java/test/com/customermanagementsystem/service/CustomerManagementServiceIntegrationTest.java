package test.com.customermanagementsystem.service;

import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.customermanagementsystem.Application;
import com.customermanagementsystem.manager.CustomerManager;
import com.customermanagementsystem.service.CustomerManagementService;
import com.customermanagementsystem.to.CustomerInfo;
import com.customermanagementsystem.to.CustomerManagementResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerManagementServiceIntegrationTest {
	
	private static final Logger LOGGER = Logger.getLogger(CustomerManagementServiceIntegrationTest.class.getName());
	
	@Autowired
	CustomerManagementService service;
	
	@LocalServerPort
	private int port;
	
	TestRestTemplate testRestTemplate = new TestRestTemplate();
	HttpHeaders httpHeaders = new HttpHeaders();
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
	@Test
	public void test_AddCustomer_Success() {	
		CustomerInfo customerInfo = getCustomerInfo(0);
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(customerInfo, httpHeaders);
		
	
		
		ResponseEntity<CustomerManagementResponse> responseEntity = 
				testRestTemplate.exchange(createURLWithPort("/cms/addCustomer"),
						HttpMethod.POST, entity, CustomerManagementResponse.class);
		
		LOGGER.info("test_AddCustomer_Success: " + responseEntity.getStatusCodeValue() + ":::" + responseEntity.getBody());
		LOGGER.info("test_AddCustomer_Success expectedResponse: " + customerInfo.toString());
		Assert.assertNotNull(responseEntity.getBody());
		Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
		Assert.assertTrue(responseEntity.getBody().getCustomerInfo().getEmailId().equals(customerInfo.getEmailId()));
	}
	
	@Test
	public void test_AddCustomer_BadRequest() {	
		CustomerInfo customerInfo = getCustomerInfo(0);
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(customerInfo, httpHeaders);
		

		ResponseEntity<CustomerInfo> responseEntity = 
				testRestTemplate.exchange(createURLWithPort("/cms/addCustomer"),
						HttpMethod.POST, entity, CustomerInfo.class);
		
		LOGGER.info("test_AddCustomer_Success: " + responseEntity.getStatusCodeValue() + ":::" + responseEntity.getBody());
		LOGGER.info("test_AddCustomer_Success expectedResponse: " + customerInfo.toString());
		Assert.assertNotNull(responseEntity.getBody());
		Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.CONFLICT));
		
	}
	
	@Test
	public void testGetCustomerInfo_Success() {
		
		CustomerManager manager = new CustomerManager();
		CustomerInfo customer = getCustomerInfo(0);
		
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(httpHeaders);
		
		CustomerInfo expectedResponse = manager.getCustomer(customer.getEmailId());
		
		ResponseEntity<CustomerInfo> responseEntity = 
				testRestTemplate.exchange(createURLWithPort("/cmd/getCustomer/" + expectedResponse.getCustomerId()),
						HttpMethod.GET, entity, CustomerInfo.class);
		
		LOGGER.info("testGetCustomerInfo_Success: " + responseEntity.getStatusCodeValue() + ":::" + responseEntity.getBody());
		LOGGER.info("testGetCustomerInfo_Success expectedResponse: " + expectedResponse.toString());
		Assert.assertNotNull(responseEntity.getBody());
		Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
		Assert.assertTrue(responseEntity.getBody().getEmailId().equals(expectedResponse.getEmailId()));
	}
	
	
	
	@Test
	public void testDeleteCustomer_Success() {
		CustomerManager manager = new CustomerManager();
		CustomerInfo customer = getCustomerInfo(0);
		CustomerInfo expectedResponse = manager.getCustomer(customer.getEmailId());
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(httpHeaders);
		
		
		ResponseEntity<CustomerManagementResponse> responseEntity = 
				testRestTemplate.exchange(createURLWithPort("/cms/deleteCustomer/"+customer.getCustomerId()),
						HttpMethod.DELETE, entity, CustomerManagementResponse.class);
		
		LOGGER.info("testDeleteCustomer_Success: " + responseEntity.getStatusCodeValue() + ":::" + responseEntity.getBody());
		LOGGER.info("testDeleteCustomer_Success expectedResponse: " + expectedResponse.toString());
		Assert.assertNotNull(responseEntity.getBody());
		Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));

	}
		
	public CustomerInfo getCustomerInfo(int customerId) {

		String name = "Spring Rest Test";
		String streetAddress = "2141 new market pkwy";
		String city="ATLANTA";
		String state="GA";
		String zipCode="30080";
		String emailId = "springtest@cms.com";
		
		return new CustomerInfo(customerId, name, streetAddress, city,
			 state, zipCode, emailId, 0.0f);
	}

}
