package com.example.rest.steps;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.rest.controller.RequestMappings;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@WebAppConfiguration
@ContextConfiguration("classpath:cucumber.xml")
public class CustomerStepDefinitions extends RestOperations{
	
	@When("^I get the list of customers$")
	public void I_get_the_list_of_customers() throws Throwable {
		get(RequestMappings.CUSTOMER_CONTROLLER_URI);
	}
	
	@When("^I get the list of customers and accepted media type \"([^\"]*)\"$")
	public void I_get_the_list_of_customers_accepted_media_type(String mediaType) throws Throwable {
		get(RequestMappings.CUSTOMER_CONTROLLER_URI, mediaType);
	}

	@When("^I get a customer with id \"([^\"]*)\"$")
	public void I_get_a_customer_with_id(String customerId) throws Throwable {
		get(RequestMappings.CUSTOMER_CONTROLLER_URI + "/" + customerId);
	}
	
	@When("^I get a customer with id \"([^\"]*)\" and accepted media type \"([^\"]*)\"$")
	public void I_get_a_customer_with_id_and_accepted_media_type(String customerId, String mediaType)
			throws Throwable {
		get(RequestMappings.CUSTOMER_CONTROLLER_URI + "/" + customerId, mediaType);
	}

	@When("^I ask the options for customers$")
	public void I_ask_the_options_for_customers() throws Throwable {
		options(RequestMappings.CUSTOMER_CONTROLLER_URI);
	}
	
	@When("^I ask the options for a customer with id \"([^\"]*)\"$")
	public void I_ask_the_options_for_a_customer_with_id(String customerId) throws Throwable {
		options(RequestMappings.CUSTOMER_CONTROLLER_URI + "/" + customerId);
	}
	
	@When("^I create a customer with media type \"([^\"]*)\" and content:$")
	public void I_create_a_customer_with_media_type_and_content(String mediaType, 
			String content) throws Throwable {
	    post(RequestMappings.CUSTOMER_CONTROLLER_URI, mediaType, content);
	}

	@When("^I update a customer with id \"([^\"]*)\", media type \"([^\"]*)\" and content:$")
	public void I_update_a_customer_with_id_media_type_and_content(String customerId, 
			String mediaType, String content) throws Throwable {
		put(RequestMappings.CUSTOMER_CONTROLLER_URI + "/" + customerId, mediaType, content);
	}


	@Then("^the person customer description is \"([^\"]*)\"$")
	public void the_person_customer_description_is(String customerDescription) throws Throwable {
		context.andExpect(jsonPath("$.customerDescription").value(customerDescription));
	}

}
