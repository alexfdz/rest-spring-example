package com.example.rest.dao;

import java.util.List;

import com.example.rest.model.Customer;

public interface CustomerDataAccess {
	
	public Customer getById(String customerUName);
	
	public void saveOrUpdate(Customer customer);
	
	public void delete(Customer customer);
	
	public List<Customer> findAll();
	
	public boolean customerExists(String customerUName);

}
