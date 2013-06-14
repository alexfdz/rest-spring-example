package com.example.rest.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsNull.notNullValue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.example.rest.dao.MockedCustomerDataAccess;
import com.example.rest.model.Customer;
import com.example.rest.model.Message;


public class CustomerControllerTest{
	
	CustomerController controller;
	MockedCustomerDataAccess customerDataAccess;
	
	String mockedCustomerName = "Mark";
	String mockedCustomerMessageText = "Hello!";
	
	@Before
	public void setUp(){
		controller = new CustomerController();
		customerDataAccess = new MockedCustomerDataAccess();
		
		customerDataAccess.addCustomer(mockedCustomerName);
		controller.setCustomerDataAccess(customerDataAccess);
	}
	
	@Test
	public void given_a_correct_name_should_get_the_customer_entity() throws Throwable{
		Customer customer = controller.getById(mockedCustomerName);
		assertThat(customer, is(notNullValue()));
		assertThat(customer.getName(), is(mockedCustomerName));
	}
	
	@Test
	public void for_a_sent_message_I_should_see_it_in_the_messages_queue() throws Throwable{
		Message message = new Message();
		message.setText(mockedCustomerMessageText);
		
		controller.sendMessage(mockedCustomerName, message);
		
		List<String> customerMessages = customerDataAccess.getCustomersMessages().get(mockedCustomerName);
		assertThat(customerMessages, is(notNullValue()));
		assertThat(customerMessages, hasItem(mockedCustomerMessageText));
	}
}
