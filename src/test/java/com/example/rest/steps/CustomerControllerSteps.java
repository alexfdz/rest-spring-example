package com.example.rest.steps;

import org.junit.BeforeClass;

public class CustomerControllerSteps {

//	private static ClientResponse clientResponse;

	@BeforeClass
	public static void before() {
//		clientResponse = null;
	}

//	@When("^I search for a valid person resource with id \"([^\"]*)\"$")
//	public void I_search_for_valid_person_resource(String id) {
//		clientResponse = StepUtils.loadPersonResource(id, "application/xml");
//	}
//
//	@When("^I search for a valid person resource with id \"([^\"]*)\" and format \"([^\"]*)\"$")
//	public void I_search_for_a_valid_person_resource_with_id_and_format(
//			String id, String format) throws Throwable {
//		clientResponse = StepUtils.loadPersonResource(id, format);
//	}
//
//	@Then("^the person resource family name is \"([^\"]*)\"$")
//	public void the_person_resource_family_name_is(final String familyName) {
//		final Person person = clientResponse.getEntity(Person.class);
//		assertThat(person.getFamilyName(), is(familyName));
//	}
//
//	@Then("^the response is successful$")
//	public void the_response_is_successful() {
//		assertThat(clientResponse.getStatus(), is(HttpServletResponse.SC_OK));
//	}
//
//	@Then("^the response fails with a not found error")
//	public void the_response_fails_with_a_not_found_error() {
//		assertThat(clientResponse.getStatus(),
//				is(HttpServletResponse.SC_NOT_FOUND));
//	}
//
//	@Then("^the response fails with an internal server error")
//	public void the_response_fails_with_an_internal_server_error() {
//		assertThat(clientResponse.getStatus(),
//				is(HttpServletResponse.SC_INTERNAL_SERVER_ERROR));
//	}
//
//	@Then("^the response fails with a bad request error")
//	public void the_response_fails_with_a_bad_request_error() {
//		assertThat(clientResponse.getStatus(),
//				is(HttpServletResponse.SC_BAD_REQUEST));
//	}
//
//	@When("^I submit a valid person resource with id \"([^\"]*)\" and name \"([^\"]*)\"$")
//	public void I_submit_a_valid_person_resource_with_id_and_name(String id,
//			String givenName) {
//		Person person = new Person();
//		person.setId(id);
//		person.setGivenName(givenName);
//		clientResponse = StepUtils.savePersonResource(person);
//	}
//
//	@When("^I submit an empty person resource$")
//	public void I_submit_an_empty_person_resource() {
//		clientResponse = StepUtils.savePersonResource(new Person());
//	}
}
