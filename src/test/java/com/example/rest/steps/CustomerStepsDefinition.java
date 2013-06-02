package com.example.rest.steps;

import static com.example.rest.controller.RequestMappings.CUSTOMER_CONTROLLER_URI;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@WebAppConfiguration
@ContextConfiguration("classpath:cucumber.xml")
public class CustomerStepsDefinition {
	
	@Autowired
    private MockMvc mockMvc;
	
	private ResultActions currentResultAction;
	
	@When("^I search for all the exisiting resources and format \"([^\"]*)\"$")
	public void I_search_for_all_the_exisiting_resources_and_format(String mediaType) throws Throwable {
		MediaType requestedMediatype = MediaType.parseMediaTypes(mediaType).get(0);
		currentResultAction = mockMvc.perform(
				get(CUSTOMER_CONTROLLER_URI)
				.accept(requestedMediatype));
	}
	
	@When("^I search for a valid customer resource with id \"([^\"]*)\"$")
	public void I_search_for_a_valid_customer_resource_with_id(String customerId) throws Throwable {
		currentResultAction = mockMvc.perform(
				get(CUSTOMER_CONTROLLER_URI + "/" + customerId));
	}

	@Then("^the person customer description is \"([^\"]*)\"$")
	public void the_person_customer_description_is(String customerDescription) throws Throwable {
		currentResultAction
			.andExpect(jsonPath("$.customerDescription").value(customerDescription));
	}

	@Then("^the response fails with a not found error$")
	public void the_response_fails_with_a_not_found_error() throws Throwable {
		currentResultAction.andExpect(status().isNotFound());
	}

	@When("^I search for a valid customer resource with id \"([^\"]*)\" and format \"([^\"]*)\"$")
	public void I_search_for_a_valid_customer_resource_with_id_and_format(String customerId, String mediaType) throws Throwable {
		MediaType requestedMediatype = MediaType.parseMediaTypes(mediaType).get(0);
		currentResultAction = mockMvc.perform(
				get(CUSTOMER_CONTROLLER_URI + "/" + customerId)
				.accept(requestedMediatype));
	}

	@Then("^the response format is \"([^\"]*)\" and the status is successful$")
	public void the_response_format_is_and_the_status_is_successful(String mediaType) throws Throwable {
		currentResultAction
			.andExpect(content().contentType(MediaType.parseMediaTypes(mediaType).get(0)))
			.andExpect(status().isOk());
	}

	@Then("^the response fails with an Not acceptable error$")
	public void the_response_fails_with_an_Not_acceptable_error() throws Throwable {
		currentResultAction
			.andExpect(status().isNotAcceptable());
	}
	
	@Then("^I should get a successful status and (\\d+) different customers$")
	public void I_should_get_a_successful_status_and_different_customers(int customersSize) throws Throwable {
		currentResultAction
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(customersSize)));
	}
	
	@Then("^I should get a successful status and not empty response$")
	public void I_should_get_a_successful_status_and_not_empty_response() throws Throwable {
		currentResultAction
		.andExpect(status().isOk())
		.andExpect(content().string(notNullValue()));
	}
}
