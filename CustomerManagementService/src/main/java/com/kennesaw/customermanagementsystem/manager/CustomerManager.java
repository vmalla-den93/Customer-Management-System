package com.kennesaw.customermanagementsystem.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kennesaw.customermanagementsystem.repo.Repository;
import com.kennesaw.customermanagementsystem.to.CustomerInfo;
import com.kennesaw.customermanagementsystem.to.ErrorResponse;
import com.kennesaw.customermanagementsystem.to.ItemInfo;
import com.kennesaw.customermanagementsystem.to.OrderInfo;
import com.kennesaw.customermanagementsystem.to.CustomerManagementResponse;
import com.kennesaw.customermanagementsystem.util.Constants;

import io.micrometer.core.instrument.util.StringUtils;

@Component
public class CustomerManager {
	
	private static final Logger LOGGER = Logger.getLogger(CustomerManager.class.getName());
	
	@Autowired
	Repository repo;

	
	public ErrorResponse getErrorResponse(int code, String message) {
		return new ErrorResponse(code, message);
	}
	

	public CustomerManagementResponse addCustomer(CustomerInfo customer) {
		CustomerManagementResponse response = new CustomerManagementResponse();
		LOGGER.info("Begin to process customer");
		//If customerId is empty/null, then generate ID and add customer
		//Else update customer information with given ID
		
		try {
			if(!repo.isCustomerExists(customer.getEmailId())) {
				LOGGER.info("Adding new customer: " + customer.getName() );
				try {
					response.setCustomerInfo(customer);
					repo.addNewCustomer(customer);
				}
				catch(SQLException e) {
					LOGGER.info("Exception occurred in processCustomer method during insertion: " + e.getMessage());
					response.setErrorResponse(getErrorResponse(Constants.CODE_SERVICE_ERROR, Constants.MESSAGE_SERVICE_ERROR));
				}
			}
			else {
					LOGGER.info("Customer Already Exists: " + customer.getEmailId());
					response.setErrorResponse(getErrorResponse(Constants.DUPLICATE_RESOURCE_ERROR, Constants.MESSAGE_DUPLICATE_RESOURCE_ERROR));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LOGGER.info("End customer processing");
		return response;
	}
	
	public CustomerManagementResponse deleteCustomer(int customerId) {
		CustomerManagementResponse response = new CustomerManagementResponse();
		response.setCustomerId(customerId);
		boolean result = repo.deleteCustomer(customerId);
		if(!result) {response.setErrorResponse(getErrorResponse(Constants.CODE_SERVICE_ERROR, Constants.MESSAGE_SERVICE_ERROR));}
		return response;
	}
	

	public List<CustomerInfo> getCustomers() {
		// TODO Auto-generated method stub
		CustomerManagementResponse response = new CustomerManagementResponse();
		try {
			List<CustomerInfo> customersList = repo.getCustomers();
			response.setCustomerList(customersList);
			if(customersList.size()==0) {response.setErrorResponse(getErrorResponse(Constants.CODE_RESOURCE_NOT_AVAILABLE, Constants.MESSAGE_RESOURCE_NOT_AVAILABLE));}
		}
		catch(SQLException e) {
			LOGGER.info("Exception occurred during retrieveCustomers: " + e.getMessage());
			response.setErrorResponse(getErrorResponse(Constants.CODE_SERVICE_ERROR, Constants.MESSAGE_SERVICE_ERROR));
		}
		
		return response.getCustomerList();
	}
	
	public CustomerInfo getCustomer(int customerId) {
		// TODO Auto-generated method stub
		CustomerInfo customer = new CustomerInfo();
		try {
			 customer= repo.getCustomerInformation(customerId);
		}
		catch(SQLException e) {
			LOGGER.info("Exception occurred during retrieveCustomers: " + e.getMessage());
			
		}
		
		return customer;
	}

	public Object getCustomerOrders(int customerId) {
		// TODO Auto-generated method stub
		
		List<OrderInfo> orderList = new ArrayList<OrderInfo>();
		try {
			orderList = repo.getCustomerOrders(customerId);
			
		}
		catch(SQLException e) {
			LOGGER.info("Exception occurred during getCustomerOrders: " + e.getMessage());
			
		}
		
		return orderList;
	}

	public CustomerManagementResponse updateCustomer(CustomerInfo customer) {
		// TODO Auto-generated method stub
		CustomerManagementResponse response = new CustomerManagementResponse();
		LOGGER.info("Begin to update customer");
		//If customerId is empty/null, then generate ID and add customer
		//Else update customer information with given ID
		
		try {
			if(!repo.isOtherCustomerExists(customer.getCustomerId(), customer.getEmailId())) {
				LOGGER.info("Updating customer: " + customer );
				try {
					response.setCustomerInfo(customer);
					repo.updateCustomer(customer);
				}
				catch(SQLException e) {
					LOGGER.info("Exception occurred in processCustomer method during insertion: " + e.getMessage());
					response.setErrorResponse(getErrorResponse(Constants.CODE_SERVICE_ERROR, Constants.MESSAGE_SERVICE_ERROR));
				}
			}
			else {
					LOGGER.info("Customer Email Id Already Exists: " + customer.getEmailId());
					response.setErrorResponse(getErrorResponse(Constants.DUPLICATE_RESOURCE_ERROR, Constants.MESSAGE_DUPLICATE_RESOURCE_ERROR));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LOGGER.info("End customer processing");
		return response;
	}

	public CustomerManagementResponse addCustomerOrder(OrderInfo customerOrder) {
		// TODO Auto-generated method stub
		CustomerManagementResponse response = new CustomerManagementResponse();
		LOGGER.info("Begin Processng Customer Order:  " + customerOrder);
		try {
			repo.addCustomerOrder(customerOrder);
		} catch (SQLException e) {
			LOGGER.info("Exception in adding Customer Order:  " + customerOrder);
			e.printStackTrace();
		}
		return response;
	}

	public List<ItemInfo> getItems() {
		// TODO Auto-generated method stub
		List<ItemInfo> itemList = new ArrayList<ItemInfo>();
		try {
			itemList = repo.getItems();
			//response.setCustomerList(customersList);
			//if(customersList.size()==0) {response.setErrorResponse(getErrorResponse(Constants.CODE_RESOURCE_NOT_AVAILABLE, Constants.MESSAGE_RESOURCE_NOT_AVAILABLE));}
		}
		catch(SQLException e) {
			LOGGER.info("Exception occurred during retrieveOrders: " + e.getMessage());
			
		}
		
		return itemList;
	
	}
	
	

}
