package com.example.rest.model;

import org.springframework.hateoas.ResourceSupport;

public abstract class Resource extends ResourceSupport{
	
	public abstract String getUniqueId();

}
