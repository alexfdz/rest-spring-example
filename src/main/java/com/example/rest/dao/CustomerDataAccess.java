package com.example.rest.dao;

import com.example.rest.model.Customer;

public interface CustomerDataAccess {
	
	public Customer getById(String id);
	
	public void saveOrUpdate(Customer customer);
	
	public void delete(Customer customer);

}
