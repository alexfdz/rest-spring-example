package com.example.rest.steps;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import cucumber.api.java.en.Then;

@WebAppConfiguration
@ContextConfiguration("classpath:cucumber.xml")
public class RestStepDefinitions{
	
	@Autowired
	protected StepDefinitionsContext context;
	
	@Then("^the response is not empty$")
	public void the_response_is_not_empty() throws Throwable {
		context.andExpect(content().string(notNullValue()));
	}

	@Then("^the status is ([^\"]*)$")
	public void the_status_is(Integer status) throws Throwable {
		context.andExpect(status().is(equalTo(status)));
	}
	
	@Then("^the response format is \"([^\"]*)\"$")
	public void the_response_format_is(String mediaType) throws Throwable {
		context.andExpect(content().contentType(
					MediaType.parseMediaTypes(mediaType).get(0)));
	}
}
