package com.example.rest.customer.steps;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@Cucumber.Options(
		format = {"html:target/cucumber-html-report" },
		glue={"com.example.rest.customer.steps", 
				"com.example.rest.commons.test.steps"})
public class FeaturesRunnerIT {

}
