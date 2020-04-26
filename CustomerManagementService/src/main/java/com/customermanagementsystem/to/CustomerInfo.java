package com.customermanagementsystem.to;

public class CustomerInfo {
	
	private int customerId;
	private String name;

	private String streetAddress;
	private String city;
	private String state;
	private String zipcode;
	private String emailId;
	private Float orderTotal;
	
	public CustomerInfo() {}

	public CustomerInfo(int customerId, String name, String streetAddress, String city,
			String state, String zipcode, String emailId, Float orderTotal) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.emailId = emailId;
		this.orderTotal = orderTotal;
		
	}

	
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipcode;
	}

	public void setZipCode(String zipcode) {
		this.zipcode = zipcode;
	}


	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerInfo other = (CustomerInfo) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (customerId == 0) {
			if (other.customerId != 0)
				return false;
		} else if (customerId != other.customerId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (streetAddress == null) {
			if (other.streetAddress != null)
				return false;
		} else if (!streetAddress.equals(other.streetAddress))
			return false;
		if (zipcode == null) {
			if (other.zipcode != null)
				return false;
		} else if (!zipcode.equals(other.zipcode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CustomerInfo [customerId=" + customerId + ", firstName=" + name
				+ ", streetAddress=" + streetAddress + ", city=" + city + ", state=" + state + ", zipcode=" + zipcode
				+ ", orderTotal=" + orderTotal + "]";
	}

	public Float getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(Float orderTotal) {
		this.orderTotal = orderTotal;
	}
	

}
