package com.example.rest.steps;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Set;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.StringUtils;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@WebAppConfiguration
@ContextConfiguration("classpath:cucumber.xml")
public class RestStepDefinitions{
	
	@Autowired
	protected StepDefinitionsContext context;
	
	@When("^I post to \"([^\"]*)\" and media type \"([^\"]*)\" with:$")
	public void I_post_to_with(String endpoint, String mediaType, String content) throws Throwable {
		MediaType requestedMediatype = MediaType.parseMediaTypes(mediaType).get(0);
	    context.perform(
				post(endpoint)
				.content(content)
				.contentType(requestedMediatype));
	}
	
	@When("^I get \"([^\"]*)\"$")
	public void I_get(String endpoint) throws Throwable {
		I_get_and_accepted_media_type(endpoint, MediaType.APPLICATION_JSON_VALUE);
	}

	@When("^I get \"([^\"]*)\" and accepted media type \"([^\"]*)\"$")
	public void I_get_and_accepted_media_type(String endpoint, String mediaType) throws Throwable {
		MediaType requestedMediatype = MediaType.parseMediaTypes(mediaType).get(0);
		context.perform(get(endpoint).accept(requestedMediatype));
	}

	@When("^I ask the options for \"([^\"]*)\"$")
	public void I_ask_the_options_for(String endpoint) throws Throwable {
		context.perform(options(endpoint));
	}
	
	@When("^I put to \"([^\"]*)\" and media type \"([^\"]*)\" with:$")
	public void I_put_to_and_media_type_with(String endpoint, String mediaType, String content) throws Throwable {
		MediaType requestedMediatype = MediaType.parseMediaTypes(mediaType).get(0);
	    context.perform(put(endpoint).content(content)
				.contentType(requestedMediatype));
    }

	@Then("^the JSON response should have \"([^\"]*)\" with the text \"([^\"]*)\"$")
	public void the_JSON_response_should_have_with_the_text(String jsonPath, String expectedContent) throws Throwable {
		context.andExpect(jsonPath(jsonPath, is(expectedContent)));
	}
	
	@Then("^the response is not empty$")
	public void the_response_is_not_empty() throws Throwable {
		context.andExpect(content().string(not(isEmptyString())));
	}
	
	@Then("^the response is empty$")
	public void the_response_is_empty() throws Throwable {
		context.andExpect(content().string(is(isEmptyString())));
	}

	@Then("^the status is ([^\"]*)$")
	public void the_status_is(Integer status) throws Throwable {
		context.andExpect(status().is(equalTo(status)));
	}
	
	@Then("^the response media type is \"([^\"]*)\"$")
	public void the_response_format_is(String mediaType) throws Throwable {
		context.andExpect(content().contentType(
					MediaType.parseMediaTypes(mediaType).get(0)));
	}
	
	@Then("^the \"([^\"]*)\" header is \"([^\"]*)\"$")
	public void the_header_is(String header, String value) throws Throwable {
		context.andExpect(header().string(header, is(value)));
	}
	
	@Then("^the Allow header contains \"([^\"]*)\"$")
	public void the_Allow_header_contains(final String allowedMethods) throws Throwable {
		context.andExpect(header().string("Allow", new BaseMatcher<String>() {
			@Override
			public boolean matches(Object item) {
				Set<String> expectedAllowMethodsSet = StringUtils.commaDelimitedListToSet(allowedMethods);
				Set<String> allowMethodsSet = StringUtils.commaDelimitedListToSet(item.toString());
				return allowMethodsSet.equals(expectedAllowMethodsSet);
			}
			@Override
			public void describeTo(Description description) {
			}}));
	}
}
