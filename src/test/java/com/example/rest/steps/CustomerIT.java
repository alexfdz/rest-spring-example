package com.example.rest.steps;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@Cucumber.Options(format = {"html:target/cucumber-html-report" })
public class CustomerIT {
}
