package com.example.rest.customer.steps;

import static com.example.rest.commons.model.LinkRelations.REL_COLLECTION;
import static com.example.rest.commons.model.LinkRelations.REL_UP;
import static com.example.rest.customer.controller.CustomerController.RequestMappings.CUSTOMER_CONTROLLER_URI;
import static com.example.rest.customer.controller.CustomerController.RequestMappings.SEND_COSTUMER_MESSAGE_URI;
import static com.example.rest.customer.controller.CustomerController.RequestMappings.SEND_MESSAGE_REL;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.springframework.hateoas.Link.REL_SELF;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.rest.commons.test.steps.RestOperations;
import com.example.rest.customer.dao.MockedCustomerDataAccess;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@WebAppConfiguration
@ContextConfiguration("classpath:cucumber.xml")
public class CustomerStepDefinitions extends RestOperations{
	
	@Autowired
	public MockedCustomerDataAccess customerDataAccess;
	
	@After
	public void cleanScenario(){
		customerDataAccess.reset();
	}
	
	@Given("^the system contains the following customer:$")
	public void the_system_contains_the_following_customer(String content) throws Throwable {
		 post(CUSTOMER_CONTROLLER_URI, MediaType.APPLICATION_JSON_VALUE, content);
	}

	@When("^I get the list of customers$")
	public void I_get_the_list_of_customers() throws Throwable {
		get(CUSTOMER_CONTROLLER_URI);
	}
	
	@When("^I get the list of customers and accepted media type \"([^\"]*)\"$")
	public void I_get_the_list_of_customers_accepted_media_type(String mediaType) throws Throwable {
		get(CUSTOMER_CONTROLLER_URI, mediaType);
	}

	@When("^I get a customer with id \"([^\"]*)\"$")
	public void I_get_a_customer_with_id(String customerId) throws Throwable {
		get(CUSTOMER_CONTROLLER_URI + "/" + customerId);
	}
	
	@When("^I get a customer with id \"([^\"]*)\" and accepted media type \"([^\"]*)\"$")
	public void I_get_a_customer_with_id_and_accepted_media_type(String customerId, String mediaType)
			throws Throwable {
		get(CUSTOMER_CONTROLLER_URI + "/" + customerId, mediaType);
	}

	@When("^I ask the options for customers$")
	public void I_ask_the_options_for_customers() throws Throwable {
		options(CUSTOMER_CONTROLLER_URI);
	}
	
	@When("^I ask the options for a customer with id \"([^\"]*)\"$")
	public void I_ask_the_options_for_a_customer_with_id(String customerId) throws Throwable {
		options(CUSTOMER_CONTROLLER_URI + "/" + customerId);
	}
	
	@When("^I create a customer with media type \"([^\"]*)\" and content:$")
	public void I_create_a_customer_with_media_type_and_content(String mediaType, 
			String content) throws Throwable {
	    post(CUSTOMER_CONTROLLER_URI, mediaType, content);
	}

	@When("^I update a customer with id \"([^\"]*)\", media type \"([^\"]*)\" and content:$")
	public void I_update_a_customer_with_id_media_type_and_content(String customerId, 
			String mediaType, String content) throws Throwable {
		put(CUSTOMER_CONTROLLER_URI + "/" + customerId, mediaType, content);
	}
	
	@Then("^the customer description is \"([^\"]*)\"$")
	public void the_customer_description_is(String customerDescription) throws Throwable {
		context.andExpect(jsonPath("$.customerDescription").value(customerDescription));
	}

	@Then("^the response contains a correct customer$")
	public void the_response_contains_a_correct_customer() throws Throwable {
		context.andExpect(jsonPath("$.links[?(@.rel=="+REL_SELF+")][0].href", is(notNullValue())))
			.andExpect(jsonPath("$.links[?(@.rel=="+REL_UP+")][0].href", endsWith(CUSTOMER_CONTROLLER_URI)))
			.andExpect(jsonPath("$.links[?(@.rel=="+REL_COLLECTION+")][0].href", endsWith(CUSTOMER_CONTROLLER_URI)))
			.andExpect(jsonPath("$.links[?(@.rel=="+SEND_MESSAGE_REL+")][0].href", endsWith(SEND_COSTUMER_MESSAGE_URI)))
			.andExpect(jsonPath("$.name", is(notNullValue())));
	}
}
