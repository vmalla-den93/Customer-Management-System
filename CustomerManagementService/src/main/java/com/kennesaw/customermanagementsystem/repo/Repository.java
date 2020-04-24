package com.kennesaw.customermanagementsystem.repo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import com.kennesaw.customermanagementsystem.config.DatabaseConfig;
import com.kennesaw.customermanagementsystem.to.CustomerInfo;
import com.kennesaw.customermanagementsystem.to.ItemInfo;
import com.kennesaw.customermanagementsystem.to.OrderInfo;
import com.kennesaw.customermanagementsystem.util.Constants;

@Component
public class Repository {
	
	private static final Logger LOGGER = Logger.getLogger(Repository.class.getName());
	
	DatabaseConfig dbConfig = new DatabaseConfig();
	
	public CustomerInfo getCustomerInformation(int customerId) throws SQLException {
		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setCustomerId(customerId);

			Connection con = getDatabaseConnection();
			Statement stmt = getStatement(con);
			ResultSet rs = stmt.executeQuery(buildSelectSqlStatementForCustomerInfo(customerId));

			if(rs.next()){
				customerInfo = new CustomerInfo(rs.getInt("customer_id"), rs.getString("name"), rs.getString("street_address"),
                        rs.getString("city"), rs.getString("state"), rs.getString("zip_code"), rs.getString("email_Id"),rs.getFloat("orderTotal"));
			}
			closeDatabaseConnection(con, stmt);	
		return customerInfo;
	}
	
	public CustomerInfo getCustomerInformation(String emailId) throws SQLException {
		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setEmailId(emailId);

			Connection con = getDatabaseConnection();
			Statement stmt = getStatement(con);
			ResultSet rs = stmt.executeQuery(buildSelectSqlStatementForCustomerInfo(emailId));

			if(rs.next()){
				customerInfo = new CustomerInfo(rs.getInt("customer_id"), rs.getString("name"), rs.getString("street_address"),
                        rs.getString("city"), rs.getString("state"), rs.getString("zip_code"), rs.getString("email_Id"),rs.getFloat("orderTotal"));
			}
			closeDatabaseConnection(con, stmt);	
		return customerInfo;
	}
	
	public String buildSelectSqlStatementForCustomerInfo(){
		// TODO Auto-generated method stub
		String sql = "select ci.customer_id, ci.name, ci.street_address, ci.state, ci.city, ci.zip_code, ci.email_id, "
				     + " round((select sum(i.price) from cms.orders o, cms.items i " 
					 + " where o.customer_id = ci.customer_id and o.item_id = i.item_id),2) orderTotal "
					 + " from cms.customer_info ci";
		LOGGER.info("buildSelectSqlStatementForCustomerInfo: " + sql);
		return sql;
	}
	
	public String buildSelectSqlStatementForCustomerInfo(int customerId){
		// TODO Auto-generated method stub
		String sql = "select ci.customer_id, ci.name, ci.street_address, ci.state, ci.city, ci.zip_code, ci.email_id, "
				     + " round((select sum(i.price) from cms.orders o, cms.items i " 
					 + " where o.customer_id = ci.customer_id and o.item_id = i.item_id),2) orderTotal "
					 + " from cms.customer_info ci where ci.customer_id = " + customerId;
		LOGGER.info("buildSelectSqlStatementForCustomerInfo: " + sql);
		return sql;
	}
	
	public String buildSelectSqlStatementForCustomerInfo(String emailId){
		// TODO Auto-generated method stub
		String sql = "select ci.customer_id, ci.name, ci.street_address, ci.state, ci.city, ci.zip_code, ci.email_id, "
				     + " round((select sum(i.price) from cms.orders o, cms.items i " 
					 + " where o.customer_id = ci.customer_id and o.item_id = i.item_id),2) orderTotal "
					 + " from cms.customer_info ci where ci.email_id = '" + emailId + "'";
		LOGGER.info("buildSelectSqlStatementForCustomerInfo: " + sql);
		return sql;
	}
	
	public String buildInsertSqlStatementForCustomerInfo(CustomerInfo customer) {
		String sql = "INSERT INTO cms.customer_info  " 
	    + "(NAME, STREET_ADDRESS, CITY, STATE, ZIP_CODE, EMAIL_ID) VALUES ('" 
		+ customer.getName() + "', '"
		+ customer.getStreetAddress().toUpperCase() + "', '"
		+ customer.getCity().toUpperCase() + "', '"
		+ customer.getState().toUpperCase() + "', '"
		+ customer.getZipCode() + "', '"
		+ customer.getEmailId() + "' )";
		LOGGER.info("buildInsertSqlStatementForCustomerInfo: " + sql);
		return sql;
	}
	
	public String buildUpdateSqlStatementForCustomerInfo(CustomerInfo customer) {
		String sql = "UPDATE cms.customer_info "
		+ "SET name = '"+customer.getName()+"', "
		+ "street_address = '"+customer.getStreetAddress().toUpperCase()+"', "
		+ "city = '"+customer.getCity().toUpperCase()+"', "
		+ "state = '"+customer.getState().toUpperCase()+"', "
		+ "zip_code = '"+customer.getZipCode() + "', "
		+ "email_id = '"+ customer.getEmailId() +"'"
		+ " WHERE customer_id = "+customer.getCustomerId();
		LOGGER.info("buildUpdateSqlStatementForCustomerInfo: " + sql);
		return sql;
	}
	
	public String buildSelectSqlStatementForOrderInfo(int customerId){
		String sql = "select items.available_item, items.price, items.type, purchased_items.purchased_date, purchased_items.pre_ordered_flag, purchased_items.customer_id\r\n" + 
				     "from rms.items JOIN rms.purchased_items ON items.item_id = purchased_items.item_id and purchased_items.customer_id = " + customerId ;
		LOGGER.info("buildSelectSqlStatementForOrderInfo: " + sql);
		return sql;
	}
	
	
	public String buildDeleteSqlStatementForOrderInfo(int customerId){
		String sql = "delete from cms.orders where customer_id = " + customerId;
		LOGGER.info("buildDeleteSqlStatementForOrderInfo: " + sql);
		return sql;
	}
	
	public String buildDeleteSqlStatementForCustomerInfo(int customerId){
		String sql = "delete from cms.customer_info where customer_id = "  + customerId;
		LOGGER.info("buildDeleteSqlStatementForCustomerInfo: " + sql);
		return sql;
	}
	
	
	private String buildSelectSqlStatementForCustomerOrders(int customerId) {
		// TODO Auto-generated method stub
		String sql = "select o.order_id, o.customer_id, o.order_date, i.item_id, i.item_name, i.price  "
				      + "from cms.orders o, cms.items i " 
				      + "where o.item_id = i.item_id and o.customer_id = "+customerId;
		LOGGER.info("buildSelectSqlStatementForOrders: " + sql);
		return sql;
	}

	public String buildSelectSqlStatementForItemInfo(){
		String sql = "select item_id, item_name, price from cms.items ";
		LOGGER.info("buildSelectSqlStatementForItemInfo: " + sql);
		return sql;
	}
	
	public String buildInsertSqlStatementForOrderInfo(int custId, Date orderDate, int itemId){
		String sql = "insert into cms.orders (customer_id, item_id, order_date)"
				      + " values ("+ custId + "," + itemId + ",'" + orderDate.toString() + "')";
		

		LOGGER.info("buildInsertSqlStatementForOrderInfo: " + sql);
		return sql;
	}
	
	
	private Connection getDatabaseConnection() {
		Connection con = null;
		con = dbConfig.getConnection();
		return con;
	}
	
	private Statement getStatement(Connection con) throws SQLException {
		Statement stmt = null;
		stmt = con.createStatement();
		return stmt;
	}
	
	private void closeDatabaseConnection(Connection con, Statement stmt) throws SQLException {
		stmt.close();
		con.close();
	}

	public OrderInfo getOrderInfo(int customerId) throws SQLException {
		
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setCustomerId(customerId);
		List<ItemInfo> purchaseList = new ArrayList<ItemInfo>();
		ItemInfo itemInfo = new ItemInfo();

			Connection con = getDatabaseConnection();
			Statement stmt = getStatement(con);
			ResultSet rs = stmt.executeQuery(buildSelectSqlStatementForOrderInfo(customerId));

			while(rs.next()){
				//itemInfo = new ItemInfo(rs.getString("available_item"), rs.getFloat("price"), rs.getString("type"), rs.getDate("purchased_date"), rs.getString("pre_ordered_flag"));
				purchaseList.add(itemInfo);
			}
			closeDatabaseConnection(con, stmt);
			orderInfo.setorderItems(purchaseList);
		return orderInfo;
	}
	
	public boolean addNewCustomer(CustomerInfo customer) throws SQLException{
		Connection con = getDatabaseConnection();
		Statement stmt = getStatement(con);
		int numberOfRows = stmt.executeUpdate(buildInsertSqlStatementForCustomerInfo(customer));
		closeDatabaseConnection(con, stmt);
		if(numberOfRows == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean updateCustomer(CustomerInfo customer) throws SQLException {
		Connection con = getDatabaseConnection();
		Statement stmt = getStatement(con);
		int numberOfRows = stmt.executeUpdate(buildUpdateSqlStatementForCustomerInfo(customer));
		closeDatabaseConnection(con, stmt);
		if(numberOfRows == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean deleteOrderInfo(int customerId) throws SQLException{
		Connection con = getDatabaseConnection();
		Statement stmt = getStatement(con);
		int numberOfRows = stmt.executeUpdate(buildDeleteSqlStatementForOrderInfo(customerId));
		closeDatabaseConnection(con, stmt);
		if(numberOfRows > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean deleteCustomerInfo(int customerId) throws SQLException{
		Connection con = getDatabaseConnection();
		Statement stmt = getStatement(con);
		int numberOfRows = stmt.executeUpdate(buildDeleteSqlStatementForCustomerInfo(customerId));
		closeDatabaseConnection(con, stmt);
		if(numberOfRows > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean deleteCustomer(int customerId) {
		boolean result = false;
		try {
			deleteOrderInfo(customerId);
			result = deleteCustomerInfo(customerId);
		}
		catch(SQLException e) {
			LOGGER.info("Exception occurred in deleteCustomer method: " + e.getMessage());
		}
		return result;
	}
	
	
	public List<CustomerInfo> getCustomers() throws SQLException{
		// TODO Auto-generated method stub
		List<CustomerInfo> customerList = new ArrayList<CustomerInfo>();
		CustomerInfo customerInfo = new CustomerInfo();
		Connection con = getDatabaseConnection();
		Statement stmt = getStatement(con);
		ResultSet rs = stmt.executeQuery(buildSelectSqlStatementForCustomerInfo());

		while(rs.next()){
			customerInfo = new CustomerInfo(rs.getInt("customer_id"), rs.getString("name"), rs.getString("street_address"),
					                         rs.getString("city"), rs.getString("state"), rs.getString("zip_code"), rs.getString("email_id"), rs.getFloat("orderTotal"));
			LOGGER.info("Customer Data :" + customerInfo);
			customerList.add(customerInfo);
		}
		closeDatabaseConnection(con, stmt);
		
		return customerList;
	}

	public boolean isCustomerExists(String emailId) throws SQLException {
		// TODO Auto-generated method stub
		CustomerInfo customer = getCustomerInformation(emailId);
		if (customer.getCustomerId() > 0)
			return true;
		else
			return false;
	}
	
	public boolean isOtherCustomerExists(int customerId, String emailId) throws SQLException {
		// TODO Auto-generated method stub
		CustomerInfo customer = getCustomerInformation(emailId);
		if ((customer.getCustomerId() > 0) && (customer.getCustomerId() != customerId))
			return true;
		else
			return false;
	}
	

	public List<OrderInfo> getCustomerOrders(int customerId) throws SQLException {
		// TODO Auto-generated method stub
		List<OrderInfo> orderList = new ArrayList<OrderInfo>();
		List<ItemInfo> itemList = null;
		OrderInfo orderInfo = new OrderInfo();
		ItemInfo itemInfo = new ItemInfo();
		Connection con = getDatabaseConnection();
		Statement stmt = getStatement(con);
		ResultSet rs = stmt.executeQuery(buildSelectSqlStatementForCustomerOrders(customerId));
		int prev_order_id = 0;
		Date orderDate = new Date(0);
		while(rs.next()){
			itemInfo = new ItemInfo(rs.getInt("item_id"), rs.getString("item_name"), rs.getFloat("price"));
			if (rs.getInt("order_id") != prev_order_id)
			{
				if(prev_order_id != 0) {
					orderInfo = new OrderInfo(customerId,orderDate, itemList);
					orderList.add(orderInfo);
					LOGGER.info("Order Info :" + orderInfo);
				}
				itemList = new ArrayList<ItemInfo>();	
			
			} 
			prev_order_id = rs.getInt("order_id");
			orderDate = rs.getDate("order_date");
			itemList.add(itemInfo);
		}
		
		if (prev_order_id > 0) {
			orderInfo = new OrderInfo(customerId,orderDate, itemList);
			orderList.add(orderInfo);
		}
		
		
		closeDatabaseConnection(con, stmt);
		
		return orderList;
	}

	public List<ItemInfo> getItems() throws SQLException {
		// TODO Auto-generated method stub
		List<ItemInfo> itemList = new ArrayList<ItemInfo>();
		ItemInfo itemInfo = new ItemInfo();
		Connection con = getDatabaseConnection();
		Statement stmt = getStatement(con);
		ResultSet rs = stmt.executeQuery(buildSelectSqlStatementForItemInfo());

		while(rs.next()){
			itemInfo = new ItemInfo(rs.getInt("item_id"), rs.getString("item_name"), rs.getFloat("price"));
			LOGGER.info("Customer Data :" + itemInfo);
			itemList.add(itemInfo);
		}
		closeDatabaseConnection(con, stmt);
		
		return itemList;
	}

	public void addCustomerOrder(OrderInfo customerOrder) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = getDatabaseConnection();
		Statement stmt = getStatement(con);
		int customerId = customerOrder.getCustomerId();
		Date orderDate = customerOrder.getOrderDate();
		for(ItemInfo orderItem : customerOrder.getorderItems()) {
			int numberOfRows = stmt.executeUpdate(buildInsertSqlStatementForOrderInfo(customerId, orderDate, orderItem.getItemId()));
			
		}
		closeDatabaseConnection(con, stmt);
		
		
	}


	

	




}
