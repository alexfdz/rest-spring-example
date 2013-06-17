package com.example.rest.commons.controller;

import static com.example.rest.commons.model.LinkRelations.REL_COLLECTION;
import static com.example.rest.commons.model.LinkRelations.REL_UP;

import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
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

import com.example.rest.commons.model.Resource;

public abstract class CRUDController<T extends Resource> {
	
	public static final String VERSION_HEADER = "x-version";
	public static final HttpMethod[] COLLECTION_ALLOWED_METHODS = new HttpMethod[] {HttpMethod.GET, HttpMethod.POST};
	public static final HttpMethod[] RESOURCE_ALLOWED_METHODS = new HttpMethod[] {HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE};
	
	
	@Autowired 
	protected EntityLinks entityLinks;

	//TODO: Testing for commons
	//TODO: VERSIONING
	//TODO: ROOT rest service (catalog)
	//TODO: Restful documentation apiary - Spring generation
	//TODO: Support for AJAX - Cross browsing
	//TODO: AUTH
	//TODO: Cache headers
	
	public abstract List<T> findAll() throws Throwable;
	
	public abstract T getById(String id) throws Throwable;
	
	public abstract void update(T resource) throws Throwable;
	
	public abstract T create(T resource) throws Throwable;
	
	public abstract void delete(T resource) throws Throwable;
	
	@RequestMapping(method= RequestMethod.GET)
	public @ResponseBody List<T> getResources() throws Throwable {
		List<T> resources = findAll();
		return addResourcesLinks(resources);
	}
	
	@RequestMapping(value="/{id}", method= RequestMethod.GET)
	@ResponseBody
	public T getResource(@PathVariable final String id) throws Throwable {
		T resource = getById(id);
		return addResourceLinks(resource);
	}
	
	@RequestMapping(method= {RequestMethod.POST})
	@ResponseBody
	public ResponseEntity<T> createResource(@RequestBody T resource) throws Throwable {
		T createdResource = create(resource);
		addResourceLinks(createdResource);
		return new ResponseEntity<T>(createdResource, getPostHeaders(createdResource), 
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
	
	private HttpHeaders getPostHeaders(T resource){
		HttpHeaders headers = new HttpHeaders();
		String href = resource.getId().getHref();
		headers.setLocation(URI.create(href));
		return headers;
	}
	
	private List<T> addResourcesLinks(List<T> resources) throws Throwable{
		for (T resource : resources) {
			addResourceLinks(resource);
		}
		return resources;
	}
	
	private T addResourceLinks(T resource) throws Throwable{
		resource.add(getResourceLinks(resource));
		return resource;
	}
	
	protected List<Link> getResourceLinks(T resource)  throws Throwable{
		List<Link> links = new ArrayList<Link>();
		links.add(entityLinks.linkToSingleResource(getResourceClass(), resource.getUniqueId()));
		links.add(entityLinks.linkToCollectionResource(getResourceClass()).withRel(REL_UP));
		links.add(entityLinks.linkToCollectionResource(getResourceClass()).withRel(REL_COLLECTION));
		return links;
	}
	
	@SuppressWarnings("unchecked")
	private Class<T> getResourceClass(){
		return (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
}