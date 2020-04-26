package com.customermanagementsystem.to;

import java.util.List;

public class CustomerManagementResponse {
	
	private int customerId;
	private CustomerInfo customerInfo;
	private OrderInfo orderInfo;
	private ErrorResponse errorResponse;
	private List<CustomerInfo> customerList;
	
	public CustomerManagementResponse() {}

	public CustomerManagementResponse(int customerId, CustomerInfo customerInfo, OrderInfo orderInfo,
			ErrorResponse errorResponse) {
		super();
		this.customerId = customerId;
		this.customerInfo = customerInfo;
		this.orderInfo = orderInfo;
		this.errorResponse = errorResponse;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}

	public void setErrorResponse(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId2) {
		this.customerId = customerId2;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerManagementResponse other = (CustomerManagementResponse) obj;
		if (customerId == 0) {
			if (other.customerId != 0)
				return false;
		} else if (customerId != other.customerId)
			return false;
		if (customerInfo == null) {
			if (other.customerInfo != null)
				return false;
		} else if (!customerInfo.equals(other.customerInfo))
			return false;
		if (errorResponse == null) {
			if (other.errorResponse != null)
				return false;
		} else if (!errorResponse.equals(other.errorResponse))
			return false;
		if (orderInfo == null) {
			if (other.orderInfo != null)
				return false;
		} else if (!orderInfo.equals(other.orderInfo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		if (customerId == 0) {
			return "CustomerManagementResponse ["+ customerList +"] ";
		}
		return "CustomerManagementResponse [customerId=" + customerId + ", customerInfo=" + customerInfo
				+ ", orderInfo=" + orderInfo + ", errorResponse=" + errorResponse + "]";
	}


	public List<CustomerInfo> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<CustomerInfo> customerList) {
		this.customerList = customerList;
	}

}
