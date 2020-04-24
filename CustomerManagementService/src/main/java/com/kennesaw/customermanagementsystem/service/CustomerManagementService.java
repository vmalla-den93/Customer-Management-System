package com.kennesaw.customermanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kennesaw.customermanagementsystem.manager.CustomerManager;
import com.kennesaw.customermanagementsystem.to.CustomerInfo;
import com.kennesaw.customermanagementsystem.to.CustomerManagementResponse;
import com.kennesaw.customermanagementsystem.to.OrderInfo;
import com.kennesaw.customermanagementsystem.util.Constants;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/cms")
public class CustomerManagementService {
	
	@Autowired
	CustomerManager manager;
	
	@ApiOperation(value = "helloWorld", notes = "Hello World",
			httpMethod = "GET", consumes = "application/json", produces = "application/json")
		    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Success", response = String.class),
	            @ApiResponse(code = 204, message = "Resource Unavailable"),
	            @ApiResponse(code = 400, message = "Bad Request"),
	            @ApiResponse(code = 401, message = "Unauthorized"),
	            @ApiResponse(code = 500, message = "Internal server error"),
	            @ApiResponse(code = 503, message = "Service Unavailable"),
	            @ApiResponse(code = 504, message = "Service Time Out")})
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, path = "/helloWorld")
	public @ResponseBody String getHelloWorld() {
		return "Hello, " + "world" ;
	}
	
	
	@ApiOperation(value = "getCustomers", notes = "getCustomers",
			httpMethod = "GET", consumes = "application/json", produces = "application/json")
		    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Success", response = CustomerManagementResponse.class),
	            @ApiResponse(code = 204, message = "Resource Unavailable"),
	            @ApiResponse(code = 400, message = "Bad Request"),
	            @ApiResponse(code = 401, message = "Unauthorized"),
	            @ApiResponse(code = 500, message = "Internal server error"),
	            @ApiResponse(code = 503, message = "Service Unavailable"),
	            @ApiResponse(code = 504, message = "Service Time Out")})
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, path = "/getCustomers")
	public @ResponseBody ResponseEntity<?> getCustomers() {
		return new ResponseEntity<>(manager.getCustomers(), new HttpHeaders(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "getCustomer", notes = "getCustomer",
			httpMethod = "GET", consumes = "application/json", produces = "application/json")
		    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Success", response = CustomerManagementResponse.class),
	            @ApiResponse(code = 204, message = "Resource Unavailable"),
	            @ApiResponse(code = 400, message = "Bad Request"),
	            @ApiResponse(code = 401, message = "Unauthorized"),
	            @ApiResponse(code = 500, message = "Internal server error"),
	            @ApiResponse(code = 503, message = "Service Unavailable"),
	            @ApiResponse(code = 504, message = "Service Time Out")})
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, path = "/getCustomer/{customerId}")
	public @ResponseBody ResponseEntity<?> getCustomer(@PathVariable(value = "customerId") int customerId) {
		return new ResponseEntity<>(manager.getCustomer(customerId), new HttpHeaders(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "getCustomerOrders", notes = "getCustomerOrders",
			httpMethod = "GET", consumes = "application/json", produces = "application/json")
		    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Success", response = CustomerManagementResponse.class),
	            @ApiResponse(code = 204, message = "Resource Unavailable"),
	            @ApiResponse(code = 400, message = "Bad Request"),
	            @ApiResponse(code = 401, message = "Unauthorized"),
	            @ApiResponse(code = 500, message = "Internal server error"),
	            @ApiResponse(code = 503, message = "Service Unavailable"),
	            @ApiResponse(code = 504, message = "Service Time Out")})
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, path = "/getCustomerOrders/{customerId}")
	public @ResponseBody ResponseEntity<?> getOrders(@PathVariable(value = "customerId") int customerId) {
		return new ResponseEntity<>(manager.getCustomerOrders(customerId), new HttpHeaders(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "addCustomer", notes = "addCustomer",
			httpMethod = "POST", consumes = "application/json", produces = "application/json")
		    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Success", response = CustomerManagementResponse.class),
	            @ApiResponse(code = 204, message = "Resource Unavailable"),
	            @ApiResponse(code = 400, message = "Bad Request"),
	            @ApiResponse(code = 401, message = "Unauthorized"),
	            @ApiResponse(code = 409, message = "Duplicate resource exists"),
	            @ApiResponse(code = 500, message = "Internal server error"),
	            @ApiResponse(code = 503, message = "Service Unavailable"),
	            @ApiResponse(code = 504, message = "Service Time Out")})
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST, path = "/addCustomer")
	public @ResponseBody ResponseEntity<?> addCustomer(@RequestBody CustomerInfo customer) {
	    CustomerManagementResponse response = manager.addCustomer(customer);
 	    if (response.getErrorResponse() != null && response.getErrorResponse().getCode() == Constants.DUPLICATE_RESOURCE_ERROR) {
 			return new ResponseEntity<>(response.getCustomerInfo(), new HttpHeaders(), HttpStatus.CONFLICT);		
 	    }
		return new ResponseEntity<>(response.getCustomerInfo(), new HttpHeaders(), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "updateCustomer", notes = "updateCustomer",
			httpMethod = "POST", consumes = "application/json", produces = "application/json")
		    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Success", response = CustomerManagementResponse.class),
	            @ApiResponse(code = 204, message = "Resource Unavailable"),
	            @ApiResponse(code = 400, message = "Bad Request"),
	            @ApiResponse(code = 401, message = "Unauthorized"),
	            @ApiResponse(code = 409, message = "Duplicate resource exists"),
	            @ApiResponse(code = 500, message = "Internal server error"),
	            @ApiResponse(code = 503, message = "Service Unavailable"),
	            @ApiResponse(code = 504, message = "Service Time Out")})
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST, path = "/updateCustomer")
	public @ResponseBody ResponseEntity<?> updateCustomer(@RequestBody CustomerInfo customer) {
	    CustomerManagementResponse response = manager.updateCustomer(customer);
 	    if (response.getErrorResponse() != null && response.getErrorResponse().getCode() == Constants.DUPLICATE_RESOURCE_ERROR) {
 			return new ResponseEntity<>(response.getCustomerInfo(), new HttpHeaders(), HttpStatus.CONFLICT);		
 	    }
		return new ResponseEntity<>(response.getCustomerInfo(), new HttpHeaders(), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "deleteCustomer", notes = "deleteCustomer",
			httpMethod = "DELETE", consumes = "application/json", produces = "application/json")
		    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Success", response = CustomerManagementResponse.class),
	            @ApiResponse(code = 204, message = "Resource Unavailable"),
	            @ApiResponse(code = 400, message = "Bad Request"),
	            @ApiResponse(code = 401, message = "Unauthorized"),
	            @ApiResponse(code = 500, message = "Internal server error"),
	            @ApiResponse(code = 503, message = "Service Unavailable"),
	            @ApiResponse(code = 504, message = "Service Time Out")})
	@CrossOrigin
	@RequestMapping(method = RequestMethod.DELETE, path = "/deleteCustomer/{customerId}")
	public @ResponseBody ResponseEntity<?> deleteCustomer(@PathVariable(value = "customerId") int customerId) {
		return new ResponseEntity<>(manager.deleteCustomer(customerId), new HttpHeaders(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "addCustomerOrder", notes = "addCustomerOrder",
			httpMethod = "POST", consumes = "application/json", produces = "application/json")
		    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Success", response = CustomerManagementResponse.class),
	            @ApiResponse(code = 204, message = "Resource Unavailable"),
	            @ApiResponse(code = 400, message = "Bad Request"),
	            @ApiResponse(code = 401, message = "Unauthorized"),
	            @ApiResponse(code = 409, message = "Duplicate resource exists"),
	            @ApiResponse(code = 500, message = "Internal server error"),
	            @ApiResponse(code = 503, message = "Service Unavailable"),
	            @ApiResponse(code = 504, message = "Service Time Out")})
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST, path = "/addCustomerOrder")
	public @ResponseBody ResponseEntity<?> addCustomerOrder(@RequestBody OrderInfo customerOrder) {
	    CustomerManagementResponse response = manager.addCustomerOrder(customerOrder);
		return new ResponseEntity<>(response.getCustomerInfo(), new HttpHeaders(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "getItems", notes = "getItems",
			httpMethod = "GET", consumes = "application/json", produces = "application/json")
		    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Success", response = CustomerManagementResponse.class),
	            @ApiResponse(code = 204, message = "Resource Unavailable"),
	            @ApiResponse(code = 400, message = "Bad Request"),
	            @ApiResponse(code = 401, message = "Unauthorized"),
	            @ApiResponse(code = 409, message = "Duplicate resource exists"),
	            @ApiResponse(code = 500, message = "Internal server error"),
	            @ApiResponse(code = 503, message = "Service Unavailable"),
	            @ApiResponse(code = 504, message = "Service Time Out")})
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, path = "/getItems")
	public @ResponseBody ResponseEntity<?> getItems() {
	    
		return new ResponseEntity<>(manager.getItems(), new HttpHeaders(), HttpStatus.OK);
	}
	

}
