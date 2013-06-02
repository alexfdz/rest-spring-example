package com.example.rest.controller;

import static com.example.rest.controller.RequestMappings.CUSTOMER_CONTROLLER_URI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.rest.dao.CustomerDataAccess;
import com.example.rest.exception.ResourceNotFoundException;
import com.pressassociation.racing.publisher.customer.config._2013_02_05.Customer;

@Controller
@RequestMapping(value= CUSTOMER_CONTROLLER_URI, 
	produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class CustomerController {
	
	@Autowired
	protected CustomerDataAccess customerDataAccess;
	

	@RequestMapping(method= RequestMethod.GET)
	public @ResponseBody List<Customer> getCustomers() throws Throwable {
		return customerDataAccess.findAll();
	}
	
	@RequestMapping(value="/{uName}", method= RequestMethod.GET)
	public @ResponseBody Customer getCustomerByUName(@PathVariable final String uName) throws Throwable {
		if(customerDataAccess.customerExists(uName)){
			return customerDataAccess.getById(uName);
		}else{
			throw new ResourceNotFoundException();
		}
	}

	@RequestMapping(method= {RequestMethod.POST, RequestMethod.PUT})
	public @ResponseBody Customer saveOrUpdateCustomer(@RequestBody Customer customer) throws Throwable {
		customerDataAccess.saveOrUpdate(customer);
		return customer;
	}
	
	@RequestMapping(method= {RequestMethod.DELETE})
	public void deleteCustomer(@RequestBody Customer customer) throws Throwable {
		customerDataAccess.delete(customer);
	}
}