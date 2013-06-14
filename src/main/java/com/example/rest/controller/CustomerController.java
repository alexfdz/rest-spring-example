package com.example.rest.controller;

import static com.example.rest.controller.RequestMappings.CUSTOMER_CONTROLLER_URI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.rest.dao.CustomerDataAccess;
import com.example.rest.exception.ResourceNotFoundException;
import com.example.rest.model.Customer;

@Controller
@ExposesResourceFor(Customer.class)
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
	
	@RequestMapping(value="/{id}/sendMessage", method= RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void sendMessage(@PathVariable final String id, @RequestParam String message) throws Throwable {
		customerDataAccess.sendMessage(id, message);
	}
	
	protected void setCustomerDataAccess(CustomerDataAccess customerDataAccess) {
		this.customerDataAccess = customerDataAccess;
	}
}