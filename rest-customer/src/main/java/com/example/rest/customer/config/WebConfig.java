package com.example.rest.customer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.rest.commons.config.CommonWebConfig;

@Configuration
@EnableWebMvc
@EnableEntityLinks
@ComponentScan(basePackages={
		"com.example.rest.customer", 
		"com.example.rest.commons"})
public class WebConfig extends CommonWebConfig{}
