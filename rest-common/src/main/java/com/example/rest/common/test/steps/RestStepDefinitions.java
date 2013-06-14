package com.example.rest.common.test.steps;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.example.rest.common.controller.CRUDController.VERSION_HEADER;
import java.util.Set;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.StringUtils;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@WebAppConfiguration
@ContextConfiguration("classpath:cucumber.xml")
public class RestStepDefinitions extends RestOperations{
	
	@When("^I post to \"([^\"]*)\" and media type \"([^\"]*)\" with:$")
	public void I_post_to_with(String endpoint, String mediaType, String content) throws Throwable {
		post(endpoint, mediaType, content);
	}
	
	@When("^I post to \"([^\"]*)\", version \"([^\"]*)\" and media type \"([^\"]*)\" with:$")
	public void I_post_to_version_and_media_type_with(String endpoint, String version, 
			String mediaType, String content) throws Throwable {
		MediaType requestedMediatype = MediaType.parseMediaTypes(mediaType).get(0);
	    context.perform(
	    		MockMvcRequestBuilders.post(endpoint)
				.content(content)
				.header(VERSION_HEADER, version)
				.contentType(requestedMediatype));
	}

	@When("^I get \"([^\"]*)\"$")
	public void I_get(String endpoint) throws Throwable {
		get(endpoint, MediaType.APPLICATION_JSON_VALUE);
	}

	@When("^I get \"([^\"]*)\" and accepted media type \"([^\"]*)\"$")
	public void I_get_and_accepted_media_type(String endpoint, String mediaType) throws Throwable {
		get(endpoint, mediaType);
	}

	@When("^I ask the options for \"([^\"]*)\"$")
	public void I_ask_the_options_for(String endpoint) throws Throwable {
		options(endpoint);
	}
	
	@When("^I put to \"([^\"]*)\" and media type \"([^\"]*)\" with:$")
	public void I_put_to_and_media_type_with(String endpoint, String mediaType, String content) throws Throwable {
		put(endpoint,mediaType, content);
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
		context.andExpect(content().string(is(isEmptyString())))
			.andExpect(status().is(equalTo(HttpStatus.NO_CONTENT.value())));
	}
	
	@Then("^the response status is ([^\"]*)$")
	public void the_status_is(String statusReason) throws Throwable {
		int statusCode = getHttpStatusCode(statusReason);
		context.andExpect(status().is(equalTo(statusCode)));
	}
	
	@Then("^the response media type is \"([^\"]*)\"$")
	public void the_response_format_is(String mediaType) throws Throwable {
		context.andExpect(content().contentType(
					MediaType.parseMediaTypes(mediaType).get(0)));
	}
	
	@Then("^the ([^\"]*) header is \"([^\"]*)\"$")
	public void the_header_is(String header, String value) throws Throwable {
		context.andExpect(header().string(header, is(value)));
	}
	
	@Then("^the ([^\"]*) header contains \"([^\"]*)\"$")
	public void the_Allow_header_contains(String header, final String values) throws Throwable {
		context.andExpect(header().string(header, new BaseMatcher<String>() {
			@Override
			public boolean matches(Object item) {
				Set<String> expectedAllowMethodsSet = StringUtils.commaDelimitedListToSet(values);
				Set<String> allowMethodsSet = StringUtils.commaDelimitedListToSet(item.toString());
				return allowMethodsSet.equals(expectedAllowMethodsSet);
			}
			@Override
			public void describeTo(Description description) {
			}}));
	}
	
	@Then("^the response contains a self relation link$")
	public void the_response_contains_a_self_relation_link() throws Throwable {
		context.andExpect(jsonPath("$.links[0].rel", is(Link.REL_SELF)));
	}

}
