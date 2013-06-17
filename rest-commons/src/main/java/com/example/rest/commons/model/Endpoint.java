package com.example.rest.commons.model;

import java.util.ArrayList;
import java.util.List;

public class Endpoint {
	
	protected List<String> methods;
	
	public List<String> getMethods() {
		return methods;
	}
	public void setMethods(List<String> methods) {
		this.methods = methods;
	}
	public void addMethods(List<String> methods) {
		if(this.methods == null){
			this.methods = new ArrayList<String>();
		}
		this.methods.addAll(methods);
	}
}
