package com.example.rest.commons.model;

import java.util.ArrayList;
import java.util.List;

public class Endpoint {
	
	protected List<String> methods;
	protected List<String> params;
	protected List<String> headers;
	protected List<String> consumes;
	protected List<String> produces;
	
	public List<String> getMethods() {
		return methods;
	}
	public void setMethods(List<String> methods) {
		this.methods = methods;
	}
	public List<String> getParams() {
		return params;
	}
	public void setParams(List<String> params) {
		this.params = params;
	}
	public List<String> getHeaders() {
		return headers;
	}
	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}
	public List<String> getConsumes() {
		return consumes;
	}
	public void setConsumes(List<String> consumes) {
		this.consumes = consumes;
	}
	public List<String> getProduces() {
		return produces;
	}
	public void setProduces(List<String> produces) {
		this.produces = produces;
	}
	public void addMethods(List<String> methods) {
		if(this.methods == null){
			this.methods = new ArrayList<String>();
		}
		this.methods.addAll(methods);
	}
	public void addParams(List<String> params) {
		if(this.params == null){
			this.params = new ArrayList<String>();
		}
		this.params.addAll(params);
	}
	public void addHeaders(List<String> headers) {
		if(this.headers == null){
			this.headers = new ArrayList<String>();
		}
		this.headers.addAll(headers);
	}
	public void addConsumes(List<String> consumes) {
		if(this.consumes == null){
			this.consumes = new ArrayList<String>();
		}
		this.consumes.addAll(consumes);
	}
	public void addProduces(List<String> produces) {
		if(this.produces == null){
			this.produces = new ArrayList<String>();
		}
		this.produces.addAll(produces);
	}
}
