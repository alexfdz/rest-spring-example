package com.example.rest.controller;

import static com.example.rest.controller.RequestMappings.CUSTOMER_CONTROLLER_URI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.rest.dao.CustomerDataAccess;
import com.example.rest.exception.ResourceNotFoundException;
import com.example.rest.model.Customer;

@Controller
@RequestMapping(value= CUSTOMER_CONTROLLER_URI)
public class CustomerController extends CRUDController<Customer>{
	
	@Autowired
	protected CustomerDataAccess customerDataAccess;
	
	@Override
	public List<Customer> findAll() throws Throwable {
		return customerDataAccess.findAll();
	}

	@Override
	public Customer getById(String id) throws Throwable {
		if(customerDataAccess.customerExists(id)){
			return customerDataAccess.getById(id);
		}else{
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public void update(Customer customer) throws Throwable {
		customerDataAccess.update(customer);
	}

	@Override
	public Customer create(Customer customer) throws Throwable {
		customerDataAccess.create(customer);
		return customer;
	}

	@Override
	public void delete(Customer customer) throws Throwable {
		customerDataAccess.delete(customer);
	}

	@Override
	public String getUriRoot() {
		return CUSTOMER_CONTROLLER_URI;
	}
}