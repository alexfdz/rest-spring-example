package com.example.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.rest.dao.CustomerDataAccess;
import com.example.rest.model.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerDataAccess customerDataAccess;
	
	@RequestMapping(value="/{uName}", 
			method= RequestMethod.GET,
			produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public Customer getCustomerByUName(@PathVariable final String uName) throws Throwable {
		return customerDataAccess.getById(uName);
	}

	@RequestMapping(method= {RequestMethod.POST, RequestMethod.PUT},
		consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
		produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public Customer saveOrUpdateCustomer(@RequestBody Customer customer) throws Throwable {
		customerDataAccess.saveOrUpdate(customer);
		return customer;
	}
	
	@RequestMapping(method= {RequestMethod.DELETE},
			consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public void deleteCustomer(@RequestBody Customer customer) throws Throwable {
		customerDataAccess.delete(customer);
	}
}