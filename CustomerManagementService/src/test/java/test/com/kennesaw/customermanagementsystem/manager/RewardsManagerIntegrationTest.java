package test.com.kennesaw.customermanagementsystem.manager;

import java.sql.Date;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kennesaw.customermanagementsystem.Application;
import com.kennesaw.customermanagementsystem.manager.CustomerManager;
import com.kennesaw.customermanagementsystem.to.CustomerInfo;
import com.kennesaw.customermanagementsystem.to.CustomerManagementResponse;
import com.kennesaw.customermanagementsystem.util.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RewardsManagerIntegrationTest {

	private static final Logger LOGGER = Logger.getLogger(RewardsManagerIntegrationTest.class.getName());
	
	@Autowired
	CustomerManager manager;
	
	@Test
	public void test_retrieveCustomerAndPurchaseInfo_Success() {
		int customerId = 1;
		CustomerManagementResponse response = manager.retrieveCustomerAndPurchaseInfo(customerId);
		LOGGER.info("test_retrieveCustomerAndPurchaseInfo_Success: " + response.getCustomerInfo().toString() + " purchase info: " + response.getPurchaseInfo().toString());
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getCustomerInfo());
		Assert.assertNotNull(response.getCustomerInfo().getCustomerId());
		Assert.assertTrue(response.getCustomerInfo().getCustomerId() == 1);
		Assert.assertNotNull(response.getPurchaseInfo());
		Assert.assertNotNull(response.getPurchaseInfo().getorderItems());
		Assert.assertTrue(response.getPurchaseInfo().getorderItems().size()>0);
		Assert.assertNull(response.getErrorResponse());
	}
	
	@Test
	public void test_retrieveCustomerAndPurchaseInfo_Failure() {
		int customerId = 2;
		CustomerManagementResponse response = manager.retrieveCustomerAndPurchaseInfo(customerId);
		LOGGER.info("test_retrieveCustomerAndPurchaseInfo_Failure: " + response.toString());
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getErrorResponse());
		Assert.assertTrue(response.getErrorResponse().getCode()==Constants.CODE_RESOURCE_NOT_AVAILABLE);
		Assert.assertTrue(response.getErrorResponse().getMessage().equals(Constants.MESSAGE_RESOURCE_NOT_AVAILABLE));
	}
	
	@Test
	public void test_generateVipId() {
		String firstName = "Mariana";
		String lastName = "Sayago";
		String generatedVipId = manager.generateCustomerId(firstName, lastName);
		LOGGER.info("test_generateVipId: " + generatedVipId);
		Assert.assertTrue(!generatedVipId.isEmpty());
		Assert.assertTrue(generatedVipId.startsWith("M"));
	}
	
	@Test
	public void test_processCustomer_AddCustomer_Success() {
		String firstName = "Mariana";
		String lastName = "Sayago";
		CustomerInfo customer = getValidCustomerInfo(2, firstName);
		CustomerManagementResponse response = manager.processCustomer(customer);
		LOGGER.info("test_processCustomer_AddCustomer_Success: " + response.toString());
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getCustomerId());
		Assert.assertNotNull(response.getCustomerInfo());
		Assert.assertNull(response.getErrorResponse());
	}
	
	@Test
	public void test_processCustomer_UpdateCustomer_Success() {
		String firstName = "Mariana";
		String lastName = "Sayago";
		String streetAddress = "2728 Paces Ferry Road";
		CustomerInfo customer = getValidCustomerInfo(4, firstName);
		customer.setStreetAddress(streetAddress);
		CustomerManagementResponse response = manager.processCustomer(customer);
		LOGGER.info("test_processCustomer_UpdateCustomer_Success: " + response.toString());
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getCustomerId());
		Assert.assertNotNull(response.getCustomerInfo());
		Assert.assertNull(response.getErrorResponse());
		Assert.assertTrue(response.getCustomerInfo().getStreetAddress().equalsIgnoreCase(streetAddress));
	}
	
	@Test
	public void test_processCustomer_UpdateCustomer_Failure() {
		String firstName = "Mariana";
		String lastName = "Sayago";
		String streetAddress = "9999 Paces Ferry Road";
		CustomerInfo customer = getValidCustomerInfo(1, firstName);
		customer.setStreetAddress(streetAddress);
		CustomerManagementResponse response = manager.processCustomer(customer);
		LOGGER.info("test_processCustomer_UpdateCustomer_Failure: " + response.toString());
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getCustomerId());
		Assert.assertNotNull(response.getCustomerInfo());
		Assert.assertNotNull(response.getErrorResponse());
	}
	
	@Test
	public void test_deleteCustomer_Success() {
		int customerId = 3;
		CustomerManagementResponse response = manager.deleteCustomer(customerId);
		LOGGER.info("test_deleteCustomer_Success: " + response.toString());
		Assert.assertNotNull(response);
		Assert.assertNull(response.getCustomerInfo());
		Assert.assertNull(response.getPurchaseInfo());
		Assert.assertNull(response.getErrorResponse());
	}
	
	@Test
	public void test_deleteCustomer_Failure() {
		int customerId = 3;
		CustomerManagementResponse response = manager.deleteCustomer(customerId);
		LOGGER.info("test_deleteCustomer_Failure: " + response.toString());
		Assert.assertNotNull(response);
		Assert.assertNull(response.getCustomerInfo());
		Assert.assertNull(response.getPurchaseInfo());
		Assert.assertNotNull(response.getErrorResponse());
	}
	
	@Test
	public void test_generateDailyPurchaseReport_Success() {
		CustomerManagementResponse response = manager.generateDailyPurchaseReport();
		LOGGER.info("test_generateDailyPurchaseReport_Success: " + response.toString());
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getPurchaseInfo());
		Assert.assertNotNull(response.getPurchaseInfo().getorderItems());
		Assert.assertTrue(response.getPurchaseInfo().getorderItems().size()>0);
		Assert.assertNull(response.getErrorResponse());
	}
	
	@Test
	public void test_generateDailyPurchaseReport_Failure() {
		CustomerManagementResponse response = manager.generateDailyPurchaseReport();
		LOGGER.info("test_generateDailyPurchaseReport_Failure: " + response.toString());
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getErrorResponse());
		Assert.assertTrue(response.getErrorResponse().getCode()==Constants.CODE_RESOURCE_NOT_AVAILABLE);
		Assert.assertTrue(response.getErrorResponse().getMessage().equals(Constants.MESSAGE_RESOURCE_NOT_AVAILABLE));
	}
	
	public CustomerInfo getValidCustomerInfo(int customerId, String name) {
		
		CustomerInfo customer = new CustomerInfo(customerId, name, "1234 Cumberland Parkway", "Atlanta",
				"GA", "30040", 0.0f);
		return customer;
	}
}
