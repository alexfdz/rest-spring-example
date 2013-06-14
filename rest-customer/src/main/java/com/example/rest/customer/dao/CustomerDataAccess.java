package com.example.rest.customer.dao;

import java.util.List;

import com.example.rest.customer.model.Customer;

public interface CustomerDataAccess {
	
	public Customer getById(String customerName);
	
	public void update(Customer customer);
	
	public void create(Customer customer);
	
	public void delete(Customer customer);
	
	public List<Customer> findAll();
	
	public boolean customerExists(String customerName);
	
	public void sendMessage(String customerName, String message);

}
