package com.example.rest.dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.example.rest.exception.IllegalResourceContentException;
import com.example.rest.model.Customer;

@Component
public class MockedCustomerDataAccess implements CustomerDataAccess{
	
	public static final String MOCKED_CUSTOMER_EP94_NAME = "EP94";
	public static final String MOCKED_CUSTOMER_EP18_NAME = "EP18";
	
	private Map<String, Customer> customers;
	
	public MockedCustomerDataAccess(){
		customers = new HashMap<String, Customer>();
		customers.put(MOCKED_CUSTOMER_EP94_NAME, buildCostumer(MOCKED_CUSTOMER_EP94_NAME));
		customers.put(MOCKED_CUSTOMER_EP18_NAME, buildCostumer(MOCKED_CUSTOMER_EP18_NAME));
	}

	private Customer buildCostumer(String customerName){
		Customer customer = new Customer();
		customer.setName(customerName);
		customer.setCustomerDescription("description " + customerName);
		return customer;
	}
	
	@Override
	public Customer getById(String customerName) {
		return customers.get(customerName);
	}

	@Override
	public void create(Customer customer) {
		if(StringUtils.isEmpty(customer.getName())){
			throw new IllegalResourceContentException();
		}
		customers.put(customer.getName(), customer);
	}
	
	@Override
	public void update(Customer customer) {
		customers.put(customer.getName(), customer);
	}

	@Override
	public void delete(Customer customer) {
		customers.remove(customer.getName());
	}

	@Override
	public List<Customer> findAll() {
		return new ArrayList<Customer>(customers.values());
	}

	@Override
	public boolean customerExists(String customerName) {
		return customers.containsKey(customerName);
	}
}
