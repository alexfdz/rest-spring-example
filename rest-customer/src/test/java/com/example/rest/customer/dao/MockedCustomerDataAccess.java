package com.example.rest.customer.dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.example.rest.commons.exception.IllegalResourceContentException;
import com.example.rest.customer.model.Customer;

@Component
public class MockedCustomerDataAccess implements CustomerDataAccess{
	
	private Map<String, Customer> customers;
	
	private Map<String, List<String>> customersMessages;
	
	public MockedCustomerDataAccess(){
		customers = new HashMap<String, Customer>();
		customersMessages = new HashMap<String, List<String>>();
	}

	public Customer addCustomer(String customerName){
		Customer customer = new Customer();
		customer.setName(customerName);
		customer.setCustomerDescription("description " + customerName);
		this.create(customer);
		return customer;
	}
	
	@Override
	public Customer getById(String customerName) {
		return customers.get(customerName);
	}

	@Override
	public void create(Customer customer) {
		if(StringUtils.isEmpty(customer.getName())){
			throw new IllegalResourceContentException(
					"The customer name is mandatory.");
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
	
	public void reset(){
		customers = new HashMap<String, Customer>();
	}

	@Override
	public void sendMessage(String customerName, String message) {
		List<String> customerMessages = null;
		if(customersMessages.containsKey(customerName)){
			customerMessages = customersMessages.get(customerName);
		}else{
			customerMessages = new ArrayList<String>();
			customersMessages.put(customerName, customerMessages);
		}
		customerMessages.add(message);
	}
	
	public Map<String, List<String>> getCustomersMessages() {
		return customersMessages;
	}
}
