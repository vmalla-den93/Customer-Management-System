package test.com.kennesaw.customermanagementsystem.manager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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
import com.kennesaw.customermanagementsystem.to.OrderInfo;
import com.kennesaw.customermanagementsystem.to.ItemInfo;
import com.kennesaw.customermanagementsystem.util.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RewardsManagerIntegrationTest {

	private static final Logger LOGGER = Logger.getLogger(RewardsManagerIntegrationTest.class.getName());
	
	@Autowired
	CustomerManager manager;
	
	
	
	@Test
	public void test_AddCustomer_Success() {
		String name = "Spring Auto Test";
		String streetAddress = "localhost 8080";
		String city = "Keene";
		String state = "NH";
		String zipcode = "03441";
		String emailId = "springtest@localhost.com";
		CustomerInfo customer = new CustomerInfo(0, name, streetAddress, city, state, zipcode, emailId,0.0f);
		CustomerManagementResponse response = manager.addCustomer(customer);
		LOGGER.info("test_processCustomer_AddCustomer_Success: " + response.toString());
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getCustomerInfo());
		Assert.assertTrue(response.getCustomerInfo().getCustomerId() > 0);
	}
	
	@Test
	public void test_AddCustomer_Success2() {
		String name = "Spring Auto Test2";
		String streetAddress = "localhost 8080";
		String city = "Keene";
		String state = "NH";
		String zipcode = "03441";
		String emailId = "springtest2@localhost.com";
		CustomerInfo customer = new CustomerInfo(0, name, streetAddress, city, state, zipcode, emailId,0.0f);
		CustomerManagementResponse response = manager.addCustomer(customer);
		LOGGER.info("test_processCustomer_AddCustomer_Success: " + response.toString());
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getCustomerInfo());
		Assert.assertTrue(response.getCustomerInfo().getCustomerId() > 0);
	}
	
	@Test
	public void test_UpdateCustomer_Success() {
		String newcity = "New York";
		String newstate = "NY";
		String emailId = "springtest@localhost.com";
		CustomerInfo customer = manager.getCustomer(emailId);
		customer.setCity(newcity);
		customer.setState(newstate);
		CustomerManagementResponse response = manager.updateCustomer(customer);
		LOGGER.info("test_processCustomer_UpdateCustomer_Success: " + response.toString());
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getCustomerInfo());
		Assert.assertNull(response.getErrorResponse());
		Assert.assertTrue(response.getCustomerInfo().getCity().equalsIgnoreCase(newcity));
		Assert.assertTrue(response.getCustomerInfo().getState().equalsIgnoreCase(newstate));
	}
	
	@Test
	public void test_processCustomer_UpdateCustomer_Failure() {
		String newcity = "New York";
		String newstate = "NY";
		String emailId = "springtest2@localhost.com";
		String newemailId = "springtest@localhost.com";
		CustomerInfo customer = manager.getCustomer(emailId);
		customer.setCity(newcity);
		customer.setState(newstate);
		customer.setEmailId(newemailId);
		CustomerManagementResponse response = manager.updateCustomer(customer);
		LOGGER.info("test_processCustomer_UpdateCustomer_Failure: " + response.toString());
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getCustomerInfo());
		Assert.assertNotNull(response.getErrorResponse());
	}
	
	@Test
	public void test_processCustomer_addCustomerOrder_Success() {
		String emailId = "springtest@localhost.com";
		int customerId = manager.getCustomer(emailId).getCustomerId();
		Date orderDate = new Date(System.currentTimeMillis());
		OrderInfo customerOrder = new OrderInfo();
		List<ItemInfo> orderItems = new ArrayList<ItemInfo>();
		orderItems.add(new ItemInfo(3,"Shoes",7.99f));
		orderItems.add(new ItemInfo(6,"Hat",5.99f));
		
		customerOrder.setCustomerId(customerId);
		customerOrder.setOrderDate(orderDate);
		customerOrder.setorderItems(orderItems);
		
		CustomerManagementResponse response = manager.addCustomerOrder(customerOrder);
		LOGGER.info("test_processCustomer_addCustomerOrder_Success: " + response.toString());
		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getOrderInfo());
		Assert.assertNotNull(response.getErrorResponse());
		Assert.assertTrue(response.getOrderInfo().getorderItems().size() == 2);
	}
	
	@Test
	public void test_deleteCustomer_Success() {
		String emailId = "springtest@localhost.com";
		int customerId = manager.getCustomer(emailId).getCustomerId();
		CustomerManagementResponse response = manager.deleteCustomer(customerId);
		LOGGER.info("test_deleteCustomer_Success: " + response.toString());
		Assert.assertNotNull(response);
		Assert.assertNull(response.getCustomerInfo());
		Assert.assertNull(response.getOrderInfo());
		Assert.assertNull(response.getErrorResponse());
	}
	
	@Test
	public void test_deleteCustomer_Success2() {
		String emailId = "springtest2@localhost.com";
		int customerId = manager.getCustomer(emailId).getCustomerId();
		CustomerManagementResponse response = manager.deleteCustomer(customerId);
		LOGGER.info("test_deleteCustomer_Success: " + response.toString());
		Assert.assertNotNull(response);
		Assert.assertNull(response.getCustomerInfo());
		Assert.assertNull(response.getOrderInfo());
		Assert.assertNull(response.getErrorResponse());
	}
	
	@Test
	public void test_getCustomers_Success() {
		List<CustomerInfo> customers = manager.getCustomers();
		LOGGER.info("test_getCustomers_Success: " + customers);
		Assert.assertNotNull(customers);
		Assert.assertTrue(customers.size()>0);

	}
	

	
}
