package com.example.rest.dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.example.rest.model.Customer;

@Component
public class MockedCustomerDataAccess implements CustomerDataAccess{
	
	private static final Logger log = Logger.getLogger(MockedCustomerDataAccess.class);
	public static final String MOCKED_CUSTOMER_EP94_UNAME = "EP94";
	public static final String MOCKED_CUSTOMER_EP18_UNAME = "EP18";
	
	private Map<String, Customer> customers;
	
	public MockedCustomerDataAccess(){
		customers = new HashMap<String, Customer>();
		customers.put(MOCKED_CUSTOMER_EP94_UNAME, buildCostumer(MOCKED_CUSTOMER_EP94_UNAME));
		customers.put(MOCKED_CUSTOMER_EP18_UNAME, buildCostumer(MOCKED_CUSTOMER_EP18_UNAME));
	}

	private Customer buildCostumer(String customerUName){
		Customer customer = new Customer();
		customer.setUName(customerUName);
		customer.setCustomerDescription("description " + customerUName);
		return customer;
	}
	
	@Override
	public Customer getById(String customerUName) {
		return customers.get(customerUName);
	}

	@Override
	public void saveOrUpdate(Customer customer) {
		customers.put(customer.getUName(), customer);
		log.info("Updated customer " + customer.getUName());
	}

	@Override
	public void delete(Customer customer) {
		customers.remove(customer.getUName());
		log.info("Deleted customer " + customer.getUName());
	}

	@Override
	public List<Customer> findAll() {
		return new ArrayList<Customer>(customers.values());
	}

	@Override
	public boolean customerExists(String customerUName) {
		return customers.containsKey(customerUName);
	}
}
