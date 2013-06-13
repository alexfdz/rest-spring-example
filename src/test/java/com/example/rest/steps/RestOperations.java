package com.example.rest.steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class RestOperations{
	
	@Autowired
	protected StepDefinitionsContext context;
	
	protected void post(String endpoint, String mediaType, String content) throws Throwable {
		MediaType requestedMediatype = MediaType.parseMediaTypes(mediaType).get(0);
	    context.perform(
	    		MockMvcRequestBuilders.post(endpoint)
				.content(content)
				.contentType(requestedMediatype));
	}
	
	protected void get(String endpoint) throws Throwable {
		get(endpoint, MediaType.APPLICATION_JSON_VALUE);
	}

	protected void get(String endpoint, String mediaType) throws Throwable {
		MediaType requestedMediatype = MediaType.parseMediaTypes(mediaType).get(0);
		context.perform(MockMvcRequestBuilders.get(endpoint).accept(requestedMediatype));
	}

	protected void options(String endpoint) throws Throwable {
		context.perform(MockMvcRequestBuilders.options(endpoint));
	}
	
	protected void put(String endpoint, String mediaType, String content) throws Throwable {
		MediaType requestedMediatype = MediaType.parseMediaTypes(mediaType).get(0);
	    context.perform(MockMvcRequestBuilders.put(endpoint).content(content)
				.contentType(requestedMediatype));
    }
	
	protected int getHttpStatusCode(String reason){
		for(HttpStatus status : HttpStatus.values()){
			if(status.getReasonPhrase().equalsIgnoreCase(reason))
				return status.value();
		}
		return -1;
	}
}
