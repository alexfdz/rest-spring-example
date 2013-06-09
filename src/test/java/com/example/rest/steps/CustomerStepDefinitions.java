package com.example.rest.steps;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import cucumber.api.java.en.Then;

@WebAppConfiguration
@ContextConfiguration("classpath:cucumber.xml")
public class CustomerStepDefinitions {
	
	@Autowired
	protected StepDefinitionsContext context;

	@Then("^the person customer description is \"([^\"]*)\"$")
	public void the_person_customer_description_is(String customerDescription) throws Throwable {
		context.andExpect(jsonPath("$.customerDescription").value(customerDescription));
	}

}
