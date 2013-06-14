package com.example.rest.common.model;

import org.springframework.hateoas.ResourceSupport;

public abstract class Resource extends ResourceSupport{
	
	public abstract String getUniqueId();

}
