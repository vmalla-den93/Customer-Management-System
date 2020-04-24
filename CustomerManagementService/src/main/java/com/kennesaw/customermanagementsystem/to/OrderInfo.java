 package com.kennesaw.customermanagementsystem.to;

import java.sql.Date;
import java.util.List;

public class OrderInfo {
	
	private int customerId;
	private Date orderDate;
	private List<ItemInfo> orderItems;
	
	public OrderInfo(){}

	public OrderInfo(int customerId, Date orderDate, List<ItemInfo> orderItems) {
		super();
		this.customerId = customerId;
		this.orderDate = orderDate;
		this.orderItems = orderItems;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId2) {
		this.customerId = customerId2;
	}


	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public List<ItemInfo> getorderItems() {
		return orderItems;
	}

	public void setorderItems(List<ItemInfo> orderItems) {
		this.orderItems = orderItems;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderInfo other = (OrderInfo) obj;
		if (customerId == 0) {
			if (other.customerId != 0)
				return false;
		} else if (customerId != other.customerId)
			return false;
		if (orderItems == null) {
			if (other.orderItems != null)
				return false;
		} else if (!orderItems.equals(other.orderItems))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderInfo [customerId=" + customerId + ", orderDate = " + orderDate + ",orderItems=" + orderItems + "]";
	}

}
