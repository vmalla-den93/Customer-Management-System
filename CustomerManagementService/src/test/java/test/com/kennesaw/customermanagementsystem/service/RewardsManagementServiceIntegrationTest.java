package test.com.kennesaw.customermanagementsystem.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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

import com.kennesaw.customermanagementsystem.Application;
import com.kennesaw.customermanagementsystem.service.CustomerManagementService;
import com.kennesaw.customermanagementsystem.to.CustomerInfo;
import com.kennesaw.customermanagementsystem.to.ErrorResponse;
import com.kennesaw.customermanagementsystem.to.ItemInfo;
import com.kennesaw.customermanagementsystem.to.OrderInfo;
import com.kennesaw.customermanagementsystem.to.CustomerManagementResponse;
import com.kennesaw.customermanagementsystem.util.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RewardsManagementServiceIntegrationTest {
	
	private static final Logger LOGGER = Logger.getLogger(RewardsManagementServiceIntegrationTest.class.getName());
	
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
	public void testGetCustomerAndPurchaseInfo_Success() {
		String vipId = "EXA6777";
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(httpHeaders);
		
		CustomerManagementResponse expectedResponse = getSuccessResponse(vipId);
		
		ResponseEntity<CustomerManagementResponse> responseEntity = 
				testRestTemplate.exchange(createURLWithPort("/rws/getCustomerAndPurchaseInfo?vipId=" + vipId),
						HttpMethod.GET, entity, CustomerManagementResponse.class);
		
		LOGGER.info("testGetCustomerAndPurchaseInfo_Success: " + responseEntity.getStatusCodeValue() + ":::" + responseEntity.getBody());
		LOGGER.info("testGetCustomerAndPurchaseInfo_Success expectedResponse: " + expectedResponse.toString());
		Assert.assertNotNull(responseEntity.getBody());
		Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
		Assert.assertTrue(responseEntity.getBody().getCustomerInfo().getFirstName().equals(expectedResponse.getCustomerInfo().getFirstName()));
	}
	
	@Test
	public void testGetCustomerAndPurchaseInfo_Failure_ResourceNotAvailable() {
		String vipId = "TEST";
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(httpHeaders);
		
		CustomerManagementResponse expectedResponse = getFailureResponse_204(vipId);
		
		ResponseEntity<CustomerManagementResponse> responseEntity = 
				testRestTemplate.exchange(createURLWithPort("/rws/getCustomerAndPurchaseInfo?vipId=" + vipId),
						HttpMethod.GET, entity, CustomerManagementResponse.class);
		
		LOGGER.info("testGetCustomerAndPurchaseInfo_Failure_ResourceNotAvailable: " + responseEntity.getStatusCodeValue() + ":::" + responseEntity.getBody());
		LOGGER.info("testGetCustomerAndPurchaseInfo_Failure_ResourceNotAvailable expectedResponse: " + expectedResponse.toString());
		Assert.assertNotNull(responseEntity.getBody());
		Assert.assertNotNull(responseEntity.getBody().getErrorResponse());
		Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
		Assert.assertTrue(responseEntity.getBody().getErrorResponse().getCode() == Constants.CODE_RESOURCE_NOT_AVAILABLE);
		Assert.assertTrue(responseEntity.getBody().getErrorResponse().getMessage().equals(Constants.MESSAGE_RESOURCE_NOT_AVAILABLE));
	}
	
	@Test
	public void testPostCustomer_AddCustomer_Success() {	
		CustomerInfo customerInfo = getCustomerInfo(null);
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(customerInfo, httpHeaders);
		
		CustomerManagementResponse expectedResponse = getSuccessResponse(null);
		
		ResponseEntity<CustomerManagementResponse> responseEntity = 
				testRestTemplate.exchange(createURLWithPort("/rws/postCustomer"),
						HttpMethod.POST, entity, CustomerManagementResponse.class);
		
		LOGGER.info("testPostCustomer_AddCustomer_Success: " + responseEntity.getStatusCodeValue() + ":::" + responseEntity.getBody());
		LOGGER.info("testPostCustomer_AddCustomer_Success expectedResponse: " + expectedResponse.toString());
		Assert.assertNotNull(responseEntity.getBody());
		Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
		Assert.assertTrue(responseEntity.getBody().getCustomerInfo().getFirstName().equals(expectedResponse.getCustomerInfo().getFirstName()));
	}
	
	@Test
	public void testPostCustomer_AddCustomer_BadRequest() {	
		CustomerInfo customerInfo = getCustomerInfo(null);
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(httpHeaders);
		
		ResponseEntity<CustomerManagementResponse> responseEntity = 
				testRestTemplate.exchange(createURLWithPort("/rws/postCustomer"),
						HttpMethod.POST, entity, CustomerManagementResponse.class);
		
		LOGGER.info("testPostCustomer_AddCustomer_BadRequest: " + responseEntity.getStatusCodeValue() + ":::" + responseEntity.getBody());
		Assert.assertNotNull(responseEntity.getBody());
		Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST));
	}
	
	@Test
	public void testPostCustomer_UpdateCustomer_Success() {	
		String customerId = "EXA6777";
		String city = "AUSTELL";
		CustomerInfo customerInfo = getCustomerInfo(customerId);
		customerInfo.setCity(city);
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(customerInfo, httpHeaders);
		
		CustomerManagementResponse expectedResponse = getSuccessResponse(customerId);
		expectedResponse.getCustomerInfo().setCity(city);
		
		ResponseEntity<CustomerManagementResponse> responseEntity = 
				testRestTemplate.exchange(createURLWithPort("/rws/postCustomer"),
						HttpMethod.POST, entity, CustomerManagementResponse.class);
		
		LOGGER.info("testPostCustomer_UpdateCustomer_Success: " + responseEntity.getStatusCodeValue() + ":::" + responseEntity.getBody());
		LOGGER.info("testPostCustomer_UpdateCustomer_Success expectedResponse: " + expectedResponse.toString());
		Assert.assertNotNull(responseEntity.getBody());
		Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
		Assert.assertTrue(responseEntity.getBody().getCustomerInfo().getCity().equals(expectedResponse.getCustomerInfo().getCity()));
	}
	
	@Test
	public void testPostCustomer_UpdateCustomer_Failure() {	
		String customerId = "TEST";
		String city = "AUSTELL";
		CustomerInfo customerInfo = getCustomerInfo(customerId);
		customerInfo.setCity(city);
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(customerInfo, httpHeaders);
		
		CustomerManagementResponse expectedResponse = getSuccessResponse(customerId);
		expectedResponse.getCustomerInfo().setCity(city);
		
		ResponseEntity<CustomerManagementResponse> responseEntity = 
				testRestTemplate.exchange(createURLWithPort("/rws/postCustomer"),
						HttpMethod.POST, entity, CustomerManagementResponse.class);
		
		LOGGER.info("testPostCustomer_UpdateCustomer_Failure: " + responseEntity.getStatusCodeValue() + ":::" + responseEntity.getBody());
		LOGGER.info("testPostCustomer_UpdateCustomer_Failure expectedResponse: " + expectedResponse.toString());
		Assert.assertNotNull(responseEntity.getBody());
		Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
		Assert.assertTrue(responseEntity.getBody().getCustomerInfo().getCity().equals(expectedResponse.getCustomerInfo().getCity()));
		Assert.assertNotNull(responseEntity.getBody().getErrorResponse());
	}
	
	@Test
	public void testDeleteCustomer_Success() {
		String vipId = "PXX6774";
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(httpHeaders);
		
		CustomerManagementResponse expectedResponse = new CustomerManagementResponse();
		expectedResponse.setCustomerId(vipId);
		
		ResponseEntity<CustomerManagementResponse> responseEntity = 
				testRestTemplate.exchange(createURLWithPort("/rws/deleteCustomer?vipId=" + vipId),
						HttpMethod.DELETE, entity, CustomerManagementResponse.class);
		
		LOGGER.info("testDeleteCustomer_Success: " + responseEntity.getStatusCodeValue() + ":::" + responseEntity.getBody());
		LOGGER.info("testDeleteCustomer_Success expectedResponse: " + expectedResponse.toString());
		Assert.assertNotNull(responseEntity.getBody());
		Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
		Assert.assertTrue(responseEntity.getBody().getCustomerId().equalsIgnoreCase(expectedResponse.getCustomerId()));
		Assert.assertNull(responseEntity.getBody().getErrorResponse());
	}
	
	@Test
	public void testDeleteCustomer_Failure() {
		String vipId = "PXX6774";
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(httpHeaders);
		
		CustomerManagementResponse expectedResponse = new CustomerManagementResponse();
		expectedResponse.setCustomerId(vipId);
		
		ResponseEntity<CustomerManagementResponse> responseEntity = 
				testRestTemplate.exchange(createURLWithPort("/rws/deleteCustomer?vipId=" + vipId),
						HttpMethod.DELETE, entity, CustomerManagementResponse.class);
		
		LOGGER.info("testDeleteCustomer_Failure: " + responseEntity.getStatusCodeValue() + ":::" + responseEntity.getBody());
		LOGGER.info("testDeleteCustomer_Failure expectedResponse: " + expectedResponse.toString());
		Assert.assertNotNull(responseEntity.getBody());
		Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
		Assert.assertTrue(responseEntity.getBody().getCustomerId().equalsIgnoreCase(expectedResponse.getCustomerId()));
		Assert.assertNotNull(responseEntity.getBody().getErrorResponse());
	}
	
	@Test
	public void testGenereateDailyPurchaseReport_Success() {		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(httpHeaders);
				
		ResponseEntity<CustomerManagementResponse> responseEntity = 
				testRestTemplate.exchange(createURLWithPort("/rws/genereateDailyPurchaseReport"),
						HttpMethod.GET, entity, CustomerManagementResponse.class);
		
		LOGGER.info("testGenereateDailyPurchaseReport_Success: " + responseEntity.getStatusCodeValue() + ":::" + responseEntity.getBody());
		Assert.assertNotNull(responseEntity.getBody());
		Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
		Assert.assertTrue(responseEntity.getBody().getPurchaseInfo().getPurchasedItems().size()>0);
	}
	
	@Test
	public void testGenereateDailyPurchaseReport_Failure_ResourceNotAvailable() {		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(httpHeaders);
				
		ResponseEntity<CustomerManagementResponse> responseEntity = 
				testRestTemplate.exchange(createURLWithPort("/rws/genereateDailyPurchaseReport"),
						HttpMethod.GET, entity, CustomerManagementResponse.class);
		
		LOGGER.info("testGenereateDailyPurchaseReport_Failure_ResourceNotAvailable: " + responseEntity.getStatusCodeValue() + ":::" + responseEntity.getBody());
		Assert.assertNotNull(responseEntity.getBody());
		Assert.assertNotNull(responseEntity.getBody().getErrorResponse());
		Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
		Assert.assertTrue(responseEntity.getBody().getErrorResponse().getCode() == Constants.CODE_RESOURCE_NOT_AVAILABLE);
		Assert.assertTrue(responseEntity.getBody().getErrorResponse().getMessage().equals(Constants.MESSAGE_RESOURCE_NOT_AVAILABLE));
	}
	
	public CustomerManagementResponse getSuccessResponse(String vipId) {
		CustomerManagementResponse response = new CustomerManagementResponse();
		response.setCustomerId(vipId);
		
		String firstName = "Eric";
		String lastName = "Acevedo";
		String streetAddress = "8888 VININGS VINTAGE WAY";
		String city="ATLANTA";
		String state="GA";
		String zipCode="30080";
		String birthday = "1999-02-18";
		Date birthDate = Date.valueOf(birthday);
		String goldStatusFlag="Y";
		int points=300;
		
		CustomerInfo customerInfo = new CustomerInfo(vipId, firstName, lastName, streetAddress, city,
			 state, zipCode, birthDate, goldStatusFlag, points);
		
		List<ItemInfo> itemInfo = new ArrayList<ItemInfo>();
		ItemInfo p1 = new ItemInfo("CHOCOLATE", new Float(2.5), "ICE CREAM", Date.valueOf("2020-02-19"), "N");
		ItemInfo p2 = new ItemInfo("VANILLA", new Float(1.5), "ICE CREAM", Date.valueOf("2020-01-19"), "Y");
		ItemInfo p3 = new ItemInfo("STRAWBERRY", new Float(3.0), "ICE CREAM", Date.valueOf("2020-02-17"), "N");
		ItemInfo p4 = new ItemInfo("VANILLA", new Float(3.5), "YOGURT", Date.valueOf("2020-01-29"), "Y");
		
		itemInfo.add(p1);
		itemInfo.add(p2);
		itemInfo.add(p3);
		itemInfo.add(p4);
		
		OrderInfo orderInfo = new OrderInfo(vipId, itemInfo);
		
		response.setCustomerInfo(customerInfo);
		response.setPurchaseInfo(orderInfo);
		return response;
	}
	
	public CustomerManagementResponse getFailureResponse_204(String vipId) {
		CustomerManagementResponse response = new CustomerManagementResponse();
		response.setCustomerId(vipId);
		response.setErrorResponse(new ErrorResponse(Constants.CODE_RESOURCE_NOT_AVAILABLE, Constants.MESSAGE_RESOURCE_NOT_AVAILABLE));
		return response;
	}
	
	public CustomerInfo getCustomerInfo(String vipId) {

		String firstName = "Eric";
		String lastName = "Acevedo";
		String streetAddress = "8888 VININGS VINTAGE WAY";
		String city="ATLANTA";
		String state="GA";
		String zipCode="30080";
		String birthday = "1999-02-18";
		Date birthDate = Date.valueOf(birthday);
		String goldStatusFlag="Y";
		int points=300;
		
		return new CustomerInfo(vipId, firstName, lastName, streetAddress, city,
			 state, zipCode, birthDate, goldStatusFlag, points);
	}

}
