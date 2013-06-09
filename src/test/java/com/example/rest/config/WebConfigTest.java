package com.example.rest.config;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfigTest extends WebMvcConfigurerAdapter {
	
	@Autowired
    private WebApplicationContext wac;

    @Bean
    @Scope("prototype")
    public MockMvc getMockMvc() {
        return webAppContextSetup(wac).dispatchOptions(true).build();
    }
}
