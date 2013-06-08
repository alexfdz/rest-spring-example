package com.example.rest.steps;

import static com.example.rest.controller.RequestMappings.CUSTOMER_CONTROLLER_URI;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@WebAppConfiguration
@ContextConfiguration("classpath:cucumber.xml")
public class CustomerStepDefinitions {
	
	@Autowired
	protected StepDefinitionsContext context;
	
	@When("^I search for all the exisiting resources and format \"([^\"]*)\"$")
	public void I_search_for_all_the_exisiting_resources_and_format(String mediaType) throws Throwable {
		MediaType requestedMediatype = MediaType.parseMediaTypes(mediaType).get(0);
		context.perform(
				get(CUSTOMER_CONTROLLER_URI)
				.accept(requestedMediatype));
	}
	
	@When("^I search for a valid customer resource with id \"([^\"]*)\"$")
	public void I_search_for_a_valid_customer_resource_with_id(String customerId) throws Throwable {
		context.perform(
				get(CUSTOMER_CONTROLLER_URI + "/" + customerId));
	}

	@Then("^the person customer description is \"([^\"]*)\"$")
	public void the_person_customer_description_is(String customerDescription) throws Throwable {
		context.andExpect(jsonPath("$.customerDescription").value(customerDescription));
	}

	@When("^I search for a valid customer resource with id \"([^\"]*)\" and format \"([^\"]*)\"$")
	public void I_search_for_a_valid_customer_resource_with_id_and_format(String customerId, String mediaType) throws Throwable {
		MediaType requestedMediatype = MediaType.parseMediaTypes(mediaType).get(0);
		context.perform(
				get(CUSTOMER_CONTROLLER_URI + "/" + customerId)
				.accept(requestedMediatype));
	}
}
