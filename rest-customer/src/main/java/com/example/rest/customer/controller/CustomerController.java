package com.example.rest.customer.controller;

import static com.example.rest.customer.controller.CustomerController.RequestMappings.CUSTOMER_CONTROLLER_URI;
import static com.example.rest.customer.controller.CustomerController.RequestMappings.SEND_COSTUMER_MESSAGE_URI;
import static com.example.rest.customer.controller.CustomerController.RequestMappings.SEND_MESSAGE_REL;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.rest.commons.controller.CRUDController;
import com.example.rest.commons.exception.ResourceNotFoundException;
import com.example.rest.customer.dao.CustomerDataAccess;
import com.example.rest.customer.model.Customer;
import com.example.rest.customer.model.Message;

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
			throw new ResourceNotFoundException(
					"Customer with id \""+ id +"\" doesn't exist.");
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
	
	@RequestMapping(value="/{id}" + SEND_COSTUMER_MESSAGE_URI, method= RequestMethod.POST)
	public Customer sendMessage(@PathVariable final String id, @RequestBody Message message) throws Throwable {
		Customer customer = getById(id);
		customerDataAccess.sendMessage(id, message.getText());
		return customer;
	}
	
	@Override
	protected List<Link> getResourceLinks(Customer customer) throws Throwable{
		List<Link> links = super.getResourceLinks(customer);
		
		String customerId = customer.getUniqueId();
		Link sendMessageLink = linkTo(methodOn(getClass(), customerId).sendMessage(customerId, null)).withRel(SEND_MESSAGE_REL);
		links.add(sendMessageLink);
		return links;
	}
	
	protected void setCustomerDataAccess(CustomerDataAccess customerDataAccess) {
		this.customerDataAccess = customerDataAccess;
	}
	
	public interface RequestMappings {
		public static final String CUSTOMER_CONTROLLER_URI = "/customers";
		public static final String SEND_COSTUMER_MESSAGE_URI = "/sendMessage";
		
		public static final String SEND_MESSAGE_REL = "sendMessage";
	}
}