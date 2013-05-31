package com.example.rest.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/test-context.xml"})
public class CustomerControllerTest {
	
	@Autowired
	private CustomerController customerController;
	
	@Test
	public void dummyTest(){
		
	}
	
}
