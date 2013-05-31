package com.example.rest.dao;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.example.rest.model.Customer;

@Component
public class MockedCustomerDataAccess implements CustomerDataAccess{
	
	private static final Logger log = Logger.getLogger(MockedCustomerDataAccess.class);
	
	private Customer currentCustomer;
	
	public MockedCustomerDataAccess(){
		currentCustomer = new Customer();
		currentCustomer.setUName("uName");
	}

	@Override
	public Customer getById(String id) {
		return currentCustomer;
	}

	@Override
	public void saveOrUpdate(Customer customer) {
		currentCustomer = customer;
		log.info("Updated customer " + customer.getUName());
	}

	@Override
	public void delete(Customer customer) {
		log.info("Deleted customer " + customer.getUName());
	}
}
