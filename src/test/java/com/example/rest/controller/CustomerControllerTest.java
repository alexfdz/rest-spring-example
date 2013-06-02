package com.example.rest.controller;

import static com.example.rest.dao.MockedCustomerDataAccess.MOCKED_CUSTOMER_EP18_UNAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

import org.junit.Before;
import org.junit.Test;

import com.example.rest.dao.MockedCustomerDataAccess;
import com.pressassociation.racing.publisher.customer.config._2013_02_05.Customer;

public class CustomerControllerTest{
	
	CustomerController controller;
	
	@Before
	public void setUp(){
		controller = new CustomerController();
		controller.customerDataAccess = new MockedCustomerDataAccess();
	}
	
	@Test
	public void given_a_correct_uname_should_get_the_customer_entity() throws Throwable{
		Customer customer = controller.getCustomerByUName(MOCKED_CUSTOMER_EP18_UNAME);
		assertThat(customer, is(notNullValue()));
		assertThat(customer.getUName(), is(MOCKED_CUSTOMER_EP18_UNAME));
	}
	
	
	
}
