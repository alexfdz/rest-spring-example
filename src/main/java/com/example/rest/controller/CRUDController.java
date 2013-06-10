package com.example.rest.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.rest.model.Resource;

public abstract class CRUDController<T extends Resource> {
	
	protected static final HttpMethod[] COLLECTION_ALLOWED_METHODS = new HttpMethod[] {HttpMethod.GET, HttpMethod.POST};
	protected static final HttpMethod[] RESOURCE_ALLOWED_METHODS = new HttpMethod[] {HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE};

	//TODO: HATEOAS
	//TODO: VERSIONING
	//http://blog.furiousbob.com/2011/12/06/hateoas-restful-services-using-spring-3-1/
	
	public abstract List<T> findAll() throws Throwable;
	
	public abstract T getById(String id) throws Throwable;
	
	public abstract void update(T resource) throws Throwable;
	
	public abstract T create(T resource) throws Throwable;
	
	public abstract void delete(T resource) throws Throwable;
	
	public abstract String getUriRoot();
	
	@RequestMapping(method= RequestMethod.GET)
	public @ResponseBody List<T> getResources() throws Throwable {
		return findAll();
	}
	
	@RequestMapping(value="/{id}", method= RequestMethod.GET)
	@ResponseBody
	public T getResource(@PathVariable final String id) throws Throwable {
		return getById(id);
	}
	
	@RequestMapping(method= {RequestMethod.POST})
	@ResponseBody
	public ResponseEntity<T> createResource(@RequestBody T resource, 
			UriComponentsBuilder uriBuilder) throws Throwable {
		T createdResource = create(resource);
		return new ResponseEntity<T>(getPostHeaders(uriBuilder, createdResource), 
				HttpStatus.CREATED);
	}

	@RequestMapping(value="/{id}", method= {RequestMethod.PUT})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateResource(@RequestBody T resource) throws Throwable {
		update(resource);
	}
	
	@RequestMapping(method= {RequestMethod.DELETE})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteResource(@RequestBody T resource) throws Throwable {
		delete(resource);
	}
	
	@RequestMapping(method= {RequestMethod.HEAD})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void getResourceHead(@RequestBody T resource) throws Throwable {}
	
	@RequestMapping(value="/{id}", method= RequestMethod.OPTIONS)
	public ResponseEntity<Void> getResourceOptions() throws Throwable {
		HttpHeaders headers = new HttpHeaders();
		headers.setAllow(new HashSet<HttpMethod>(
				Arrays.asList(RESOURCE_ALLOWED_METHODS)));
		return new ResponseEntity<Void>(headers, HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method= RequestMethod.OPTIONS)
	public ResponseEntity<Void> getCollectionOptions() throws Throwable {
		HttpHeaders headers = new HttpHeaders();
		headers.setAllow(new HashSet<HttpMethod>(
				Arrays.asList(COLLECTION_ALLOWED_METHODS)));
		return new ResponseEntity<Void>(headers, HttpStatus.NO_CONTENT);
	}
	
	protected HttpHeaders getPostHeaders(UriComponentsBuilder uriBuilder, T resource){
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(getUri(uriBuilder, resource));
		return headers;
	}
	
	protected URI getUri(UriComponentsBuilder uriBuilder, T resource){
		return uriBuilder.path(getUriRoot() + "/{id}").buildAndExpand(resource.getId()).toUri();
	}
}